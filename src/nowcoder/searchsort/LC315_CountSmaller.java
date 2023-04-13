package nowcoder.searchsort;
// LC315 计算右侧小于当前元素的个数 https://leetcode.cn/problems/count-of-smaller-numbers-after-self/

import java.util.LinkedList;
import java.util.List;

/**
 * 使用 merge 函数合并两个有序数组的时候，
 * 其实是可以知道一个元素 nums[i] 后边有多少个元素比 nums[i] 小的。
 *
 * 在对 nums[lo..hi] 合并的过程中，每当执行 nums[p] = temp[i] 时，
 * 可以确定 temp[i] 这个元素后面比它小的元素个数为 j - (mid + 1)。
 *
 * nums[lo..hi] 本身只是一个子数组，这个子数组之后还会被执行 merge，其中元素的位置还是会改变。
 * 但是，这是其他递归节点需要考虑的问题。
 * 我们只要在 merge 函数中做一些手脚，叠加每次 merge 时记录的结果即可。
 */
public class LC315_CountSmaller {

    private class Pair {
        int val, id;

        Pair(int val, int id) {
            this.val = val;// 记录数组的元素值
            this.id = id;// 记录元素在数组中的原始索引
        }
    }
    private Pair[] temp;// 归并排序所用的辅助数组
    private int[] count;// 记录每个元素后面比自己小的元素个数

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        count = new int[n];
        temp = new Pair[n];
        Pair[] arr = new Pair[n];
        // 记录元素原始的索引位置，以便在 count 数组中更新结果
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i], i);
        }
        // 执行归并排序，结果记录在 count 数组中
        sort(arr, 0, n - 1);
        List<Integer> ans = new LinkedList<>();
        for (int c : count)
            ans.add(c);
        return ans;
    }

    private void sort(Pair[] arr, int lo, int hi) {
        if (lo == hi)
            return;
        int mid = lo + ((hi - lo) >> 1);
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    private void merge(Pair[] arr, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            temp[i] = arr[i];
        }
        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                arr[p] = temp[j++];
            } else if (j == hi + 1) {
                arr[p] = temp[i++];
                // 更新 count 数组 (右边都没了，证明现在左边剩下的每一个值都比右边大)
                count[arr[p].id] += j - (mid + 1);
            } else if (temp[i].val > temp[j].val) {
                arr[p] = temp[j++];
            } else { // 注意：相等的时候要先放左边，否则会出错
                arr[p] = temp[i++];
                count[arr[p].id] += j - (mid + 1);
            }
        }
    }
}




