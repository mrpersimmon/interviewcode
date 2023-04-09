package nowcoder.searchsort;

public class BM20_InversePairs {
    public static int mod = 1000000007;
    public int inversePairs(int[] array) {
        int n = array.length;
        int[] help = new int[n];
        return mergeSort(0, n - 1, array, help);
    }

    // 计算逆序对的总数
    public int mergeSort(int left, int right, int[] data, int[] help) {
        // 停止划分
        if (left >= right) // base case
            return 0;
        // 取中间
        int mid = left + ((right - left) >> 1);
        // 左右划分合并
        int res = mergeSort(left, mid, data, help) + mergeSort(mid + 1, right, data, help);
        // 防止溢出
        res %= mod;
        int i = left, j = mid + 1; // 左、右指针
        // 将当前划分的数组全部填充到辅助数组中
        for (int k = left; k <= right; k++) {
            help[k] = data[k];
        }
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) { // 左指针到边界，后续只需右指针移动
                data[k] = help[j]; // 填充右边的值
                j++;
            } else if (j == right + 1 || help[i] <= help[j]) { // 如果右指针到边界，或者左指针的值 <= 右指针的值
                data[k] = help[i]; // 填充左边的值
                i++;
            } else { // 左右指针都没越界，左指针的值 > 右指针的值
                data[k] = help[j]; // 填充右边的值
                j++;
                // 符合逆序对的定义
                res += mid - i + 1;
            }
        }
        return res % mod;
    }

    public static void main(String[] args) {
        int[] test = new int[] {1, 2, 3, 4, 5, 6, 7, 0};
        System.out.println(InversePairs(test));
    }



//    public int mod = 1000000007;
    public static int InversePairs(int[] array) {
        int n = array.length;
        if (array == null || n < 2)
            return 0;

        int[] help = new int[n];
        return mergeSort_1(0, n - 1, array, help);
    }

    public static int mergeSort_1(int l, int r, int[] arr, int[] help) {
        if (l >= r) {
            return 0;
        }
        // 取中间
        int mid = l + ((r - l) >> 1);
        // 左右合并
        int ans = mergeSort_1(l, mid, arr, help) + mergeSort_1(mid + 1, r, arr, help);
        // 取模防止溢出
        ans %= mod;

        int i = l, j = mid + 1;
        // for (int k = 0; k < arr.length; k++) {
        //     help[k] = arr[k];
        // }

        for (int k = l; k <= r; k++) { // k 作为辅助数组的指针
            if (i == mid + 1) { // 左指针越界
                help[k] = arr[j++];
            } else if (j == r + 1) { // 右指针越界
                help[k] = arr[i++];
            } else if (arr[i] <= arr[j]) { // 左边值 <= 右边值
                help[k] = arr[i++];
            } else { // 左边值 > 右边值，满足逆序对的要求，统计逆序对个数
                help[k] = arr[j++];
                ans += mid - i + 1;
            }
        }
        for (int k = l; k <= r; k++) {
            arr[k] = help[k];
        }

        return ans % mod;
    }
}
