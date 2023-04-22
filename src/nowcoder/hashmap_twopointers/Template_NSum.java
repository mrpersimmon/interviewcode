package nowcoder.hashmap_twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// 三数之和：https://www.nowcoder.com/practice/345e2ed5f81d4017bbb8cc6055b0b711
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
    public ArrayList<ArrayList<Integer>> twoSum(ArrayList<Integer> nums, int target) {
        // 解决的重点在于：当 sum == target 时，要跳过所有重复的元素。并且所有的分支都应该跳过重复的元素。
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Collections.sort(nums); // 排序
        int lo = 0, hi = nums.size() - 1; // 左右指针
        while (lo < hi) {
            int left = nums.get(lo), right = nums.get(hi);
            int sum = left + right;
            if (sum == target) {
                ans.add(new ArrayList<>(Arrays.asList(left, right)));
                while (lo < hi && nums.get(lo) == left) // 左指针去重
                    lo++;
                while (lo < hi && nums.get(hi) == right) // 右指针去重
                    hi--;
            } else if (sum < target) {
                while (lo < hi && nums.get(lo) == left)
                    lo++;
            } else if (sum > target) {
                while (lo < hi && nums.get(hi) == right)
                    hi--;
            }
        }
        return ans;
    }

    // ------------ threeSum ----------

    // 返回 nums 中从 start 开始的满足 target 的二元组。
    public ArrayList<ArrayList<Integer>> twoSum(int[] nums, int start, int target) {
        // 解决的重点在于：当 sum == target 时，要跳过所有重复的元素。并且所有的分支都应该跳过重复的元素。
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        // Arrays.sort(nums); // 排序，threeSum 传过来之前已经排过序了
        int lo = start, hi = nums.length - 1; // 左右指针
        while (lo < hi) {
            int left = nums[lo], right = nums[hi];
            int sum = left + right;
            if (sum == target) {
                ans.add(new ArrayList<>(Arrays.asList(left, right)));
                while (lo < hi && nums[lo] == left) // 左指针去重
                    lo++;
                while (lo < hi && nums[hi] == right) // 右指针去重
                    hi--;
            } else if (sum < target) {
                while (lo < hi && nums[lo] == left)
                    lo++;
            } else if (sum > target) {
                while (lo < hi && nums[hi] == right)
                    hi--;
            }
        }
        return ans;
    }

    public ArrayList<ArrayList<Integer>> threeSum(int[] nums, int target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        // 确定第一个数字之后，就可以使用 twoSum 计算 target - nums[i] 了
        for (int i = 0; i < nums.length; i++) { // 穷举 threeSum 的第一个数
            // 对 target - nums[i] 计算 twoSum
            ArrayList<ArrayList<Integer>> tuples = twoSum(nums, i + 1, target - nums[i]);
            // 如果存在满足条件的二元组，再加上 nums[i] 就是结果三元组
            for (ArrayList<Integer> tuple : tuples) {
                tuple.add(0, nums[i]);
                ans.add(tuple);
            }
            // 跳过第一个数字重复的情况，否则会出现重复结果
            while (i < nums.length - 1 && nums[i] == nums[i + 1])
                i++;
        }
        return ans;
    }

    // ---------- fourSum -----------

    public ArrayList<ArrayList<Integer>> fourSum(int[] nums, int target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums); // 数组进行排序
        for (int i = 0; i < nums.length; i++) { // 穷举 fourSum 的第一个数
            // 对 target - nums[i] 计算 threeSum
            ArrayList<ArrayList<Integer>> tuples = threeSum(nums, i + 1, target - nums[i]);
            for (ArrayList<Integer> tuple : tuples) {
                // 如果存在满足条件的三元组，再加上 nums[i] 就是结果四元组
                tuple.add(0, nums[i]);
                ans.add(tuple);
            }
            // fourSum 的第一个数不能重复
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) i++;
        }
        return ans;
    }

    // 从 nums[start] 开始，计算有序数组 nums 中所有和为 target 的三元组
    public ArrayList<ArrayList<Integer>> threeSum(int[] nums, int start, int target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        // Arrays.sort(nums);
        // 确定第一个数字之后，就可以使用 twoSum 计算 target - nums[i] 了
        for (int i = start; i < nums.length; i++) { // 穷举 threeSum 的第一个数
            // 对 target - nums[i] 计算 twoSum
            ArrayList<ArrayList<Integer>> tuples = twoSum(nums, i + 1, target - nums[i]);
            // 如果存在满足条件的二元组，再加上 nums[i] 就是结果三元组
            for (ArrayList<Integer> tuple : tuples) {
                tuple.add(0, nums[i]);
                ans.add(tuple);
            }
            // 跳过第一个数字重复的情况，否则会出现重复结果
            while (i < nums.length - 1 && nums[i] == nums[i + 1])
                i++;
        }
        return ans;
    }



    // ------------ nSum -----------------
    // 调用该函数之前，一定要对 nums 进行排序
    public ArrayList<ArrayList<Integer>> nSum(int[] nums, int n, int start, int target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (n < 2 || nums.length < n) // n 不应该小于 2，并且输入数组内的元素不应小于 n
            return ans;
        if (n == 2) { // 2Sum 是 base case
            int lo = start, hi = nums.length - 1;
            while (lo < hi) {
                int left = nums[lo], right = nums[hi];
                int sum = left + right;
                if (sum == target) {
                    ans.add(new ArrayList<>(Arrays.asList(left, right)));
                    while (lo < hi && nums[lo] == left) lo++;
                    while (lo < hi && nums[hi] == right) hi--;
                } else if (sum < target) {
                    while (lo < hi && nums[lo] == left) lo++;
                } else if (sum > target) {
                    while (lo < hi && nums[hi] == right) hi--;
                }
            }
        } else {
            // n > 2 时，递归计算 (n-1)Sum 的结果
            for (int i = 0; i < nums.length; i++) {
                ArrayList<ArrayList<Integer>> tuples = nSum(nums, n - 1, i + 1, target - nums[i]);
                for (ArrayList<Integer> tuple : tuples) {
                    // (n-1)Sum 加上 nums[i] 就是 nSum
                    tuple.add(0, nums[i]);
                    ans.add(tuple);
                }
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) i++;
            }
        }
        return ans;
    }
    /*
    * 我对咱们部门的工作内容，就是研发效能领域平台和工具的建设十分感兴趣，
    * 因为在之前的实习中，我也是参与公司的流水线管理平台相关工具的开发，
    * 比如简历中的历史镜像备份工具、服务资源告警工具、内存资源缩减工具那些。
    * 美团的工程基础设施在国内处于一流顶尖水平，
    * 所以我想在实习中去学习更优秀的研发理念和工程实践，
    * 也希望自己能够在实习中快速成长起来参与部门主要工具的开发，贡献自己的力量，实现双赢。
    * */

    public static void main(String[] args) {
        StringBuffer ans = new StringBuffer("ws");
        ans.insert(0, "a");
        System.out.println(ans.toString());
    }
}






















