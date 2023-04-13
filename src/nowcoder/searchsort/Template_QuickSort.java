package nowcoder.searchsort;

import java.util.Arrays;
import java.util.Random;

public class Template_QuickSort {
}

class Quick {
    public static void sort(int[] nums) {
        // 为了避免出现耗时的极端情况，先随机打乱
        shuffle(nums);
        sort(nums, 0, nums.length - 1);
    }

    private static void sort(int[] nums, int lo, int hi) {
        if (lo >= hi)
            return;
        // 对 nums[lo..hi] 进行切分
        // 是的 nums[lo..p-1] <= nums[p] < nums[p+1..hi]
        int p = partition(nums, lo, hi);
        sort(nums, lo, p - 1);
        sort(nums, p + 1, hi);
    }

    private static int partition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];
        // 定义 i, j 为开区间，
        // 定义 [lo, i) <= pivot; (j, hi] > pivot
        // 之后要正确维护这个边界区间的定义
        int i = lo + 1, j = hi;
        while (i <= j) {
            while (i < hi && nums[i] <= pivot) {
                i++; // 此 while 结束时，恰好 nums[i] > pivot
            }
            while (j > lo && nums[j] > pivot) {
                j--; // 此 while 结束时，恰好 nums[j] <= pivot
            }
            // 此时 [lo, j) <= pivot && (j, hi] > pivot
            if (i >= j)
                break;
            swap(nums, i, j);
        }
        // 将 pivot 放到合适的位置，即 pivot 左边元素较小，右边元素较大
        swap(nums, lo, j);
        return j;
    }

    private static void shuffle(int[] nums) {
        Random rand = new Random();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 生成 [i, n-1] 的随机数
            int r = i + rand.nextInt(n - i);
            swap(nums, i, r);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
