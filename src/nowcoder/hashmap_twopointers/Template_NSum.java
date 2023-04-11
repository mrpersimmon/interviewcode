package nowcoder.hashmap_twopointers;

import java.util.Arrays;

public class Template_NSum {
    // 目标：返回「一对儿」 nums 中能够凑出 target 的两个元素的值。
    // 比如输入 nums = [1,3,5,6], target = 9，那么算法返回两个元素 [3,6]。
    public int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums); // 先排序
        int lo = 0, hi = nums.length - 1; // 左右指针
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            // 根据 sum 和 target 的比较，左右移动指针
            if (sum == target) {
                return new int[]{nums[lo], nums[hi]};
            } else if (sum < target) {
                lo++;
            } else if (sum > target) {
                hi--;
            }
        }
        return new int[] {-1, -1};
    }

    // nums 可能有多对儿元素之和都等于 target，返回所有和为 target 的元素对儿，不能重复。
}
