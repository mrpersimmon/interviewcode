package nowcoder.searchsort;

public class S01_MergeSort {
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;

        process(arr, 0, arr.length - 1);
    }
    public static void process(int[] arr, int L, int R) {
        if (L == R) // base case
            return;
        int mid = L + ((R - L) >> 1); // 取中间
        process(arr, L, mid); // 划分左边
        process(arr, mid + 1, R); // 划分右边
        merge(arr, L, mid, R);
    }
    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1]; // 辅助数组
        int i = 0; // 辅助数组指针
        int p1 = L; // 左指针
        int p2 = M + 1; // 右指针
        while (p1 <= M && p2 <= R) { // 左右指针都不越界
            if (arr[p1] <= arr[p2]) {
                help[i] = arr[p1];
                p1++;
                i++;
            } else {
                help[i] = arr[p2];
                p2++;
                i++;
            }
        }
        // p2 指针越界
        while (p1 <= M)
            help[i++] = arr[p1++];
        // p1 指针越界
        while (p2 <= R)
            help[i++] = arr[p2++];
        // 将辅助数组元素填回到 arr[L..R] 中
        for (i = 0; i < help.length; i++)
            arr[L + i] = help[i];
    }

    public static void mergeSort_2(int left, int right, int[] data, int[] help) {
        if (left >= right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        mergeSort_2(left, mid, data, help);
        mergeSort_2(mid + 1, right, data, help);
        int i = left, j = mid + 1;
        for (int k = 0; k <= right; k++) {
            help[k] = data[k];
        }
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                data[k] = help[j++];
            } else if (j == right + 1 || help[i] <= help[j]) {
                data[k] = help[i++];
            } else {
                data[k] = help[j++];
            }
        }
    }
}
