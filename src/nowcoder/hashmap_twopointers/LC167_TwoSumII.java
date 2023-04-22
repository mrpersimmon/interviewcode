package nowcoder.hashmap_twopointers;
// https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/
public class LC167_TwoSumII {
    //题目要求：
    // 有序数组；
    // 空间复杂度要求为 O(1)，也就是不能用 HashMap；
    // index 从 1 开始
    public int[] twoSum(int[] nums, int target) {
        // 左右指针相向而行
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[] {left + 1, right + 1};
            } else if (sum < target) {
                left++; // 让 sum 大点儿
            } else if (sum > target) {
                right--; // 让 sum 小点儿
            }
        }
        return new int[]{-1, -1};
    }
}
