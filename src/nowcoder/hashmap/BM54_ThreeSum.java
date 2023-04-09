package nowcoder.hashmap;

import java.util.ArrayList;
import java.util.Arrays;

public class BM54_ThreeSum {
    // 1. 排除边界特殊情况；
    // 2. 要求三元组内部必须有序，因此对原数组使用 sort 函数排序；
    // 3. 得到有序数组后，遍历该数组，对于每个遍历到元素，假设它是三元组中最小的一个，那么另外两个一定在后面。
    // 4. 需要三个数相加为 0，则另外两个数相加应该为上述第一个数的相反数，我们可以利用双指针在剩余的子数组中找有没有这样的数对。
    //    双指针指向剩余子数组的首尾，如果二者相加为目标值，那么就可以记录，而且二者中间数字相加可能还会有。
    // 5. 如果二者相加大于目标值，说明右指针太大了，那么就将其左移缩小；
    //    相反如果二者相加小于目标值，说明左指针太小了，将其右移扩大，直到两指针相遇，剩余子数组找完了。
    // 注意：对于三个数字都要判断是否相邻有重复的情况，要去重。
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int n = num.length;
        // 1. 边界：不够三元组
        if (n < 3) {
            return ans;
        }
        // 2. 对原数组排序
        Arrays.sort(num);
        // 3. 遍历数组
        for (int i = 0; i < n - 2; i++) {
            // 去重
            if (i != 0 && num[i] == num[i - 1])
                continue;
            // 剩余子数组首尾双指针
            int left = i + 1;
            int right = n - 1;
            // 当前目标值：当前值的相反数（-a）
            int target = -num[i];
            // 双指针
            while (left < right) {
                // 双指针指向的两值相加为 target，则可以与 num[i] 组成 0
                if (num[left] + num[right] == target) {
                    ArrayList<Integer> tmp = new ArrayList<>();
                    tmp.add(num[i]);
                    tmp.add(num[left]);
                    tmp.add(num[right]);
                    ans.add(tmp);
                    while (left + 1 < right && num[left] == num[left + 1])
                        left++;// 去重
                    while (right - 1 > left && num[right] == num[right - 1])
                        right--; // 去重
                    // 双指针向中间收缩
                    left++;
                    right--;
                }
                // 双指针的值之和大于目标值，右指针值太大了，向左移动
                else if (num[left] + num[right] > target) {
                    right--;
                }
                // 双指针的值之和小于目标值，左指针值太小了，向右移动
                else
                    left++;
            }
        }
        return ans;
    }
}
