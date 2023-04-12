package nowcoder.searchsort;

// BM17 二分查找I https://www.nowcoder.com/practice/d3df40bd23594118b57554129cadf47b、
// LC34 在排序数组中查找元素的第一个和最后一个位置 https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
// 剑指 Offer 53 在排序数组中查找数字 I https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
public class Template_BinarySearch {

    // 寻找一个数，最基本的二分搜索
    int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    // 寻找左侧边界的二分搜索，升序数组中存在多个等于 target 的值，找到最左侧的
    int leftBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                right = mid - 1; // 收缩右侧边界
            } else if (nums[mid] < target) {
                left = mid + 1; // 搜索区间变为 [mid + 1, right]
            } else if (nums[mid] > target) {
                right = mid - 1; // 搜索区间变为 [left, mid - 1]
            }
        }
        // 越界检查
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }

        return left;
    }

    // 寻找右侧边界的二分搜索，升序数组中存在多个等于 target 的值，找到最右侧的
    int rightBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                left = mid + 1; // 搜索左边界
            } else if (nums[mid] < target) {
                left = mid + 1; // [mid + 1, right]
            } else if (nums[mid] > target) {
                right = mid - 1; // [left, mid - 1]
            }
        }
        // 越界检查
        if (right < 0 || nums[right] != target)
            return -1;
        return right;
    }

}
