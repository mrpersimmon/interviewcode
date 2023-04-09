package meituan;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 现在有 n 个巧克力，每个巧克力都是厚度一样的正方形巧克力。
 * n 个巧克力的边长分别为 a1, a2, ..., an.
 * 因为都是厚度一致的正方形巧克力，我们认为第 i 个巧克力的重量为 ai^2。
 * 现准备一个合适大小的包来装尽可能多的巧克力。
 *
 * 输入：
 * 第一行 2 个整数 n 和 m，表示巧克力数量和询问数量。
 * 第二行 n 个整数，表示 n 块巧克力的边长。不能对巧克力进行拆分。
 * 第三行 m 个整数，第 i 个整数 qi 表示询问：如果选择一个能装 qi 重量的包，最多能装多少块巧克力？
 *
 * 输出：
 * 一行 m 个整数，分别表示每次询问的答案。
 *
 * 5 5
 * 1 2 2 4 5  (1 4 4 16 25)
 * 1 3 7 9 15
 */
public class MT03_Chocolate {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        in.nextLine();
        int[] len = new int[n];
        for (int i = 0; i < n; i++) {
            len[i] = in.nextInt();
        }
        long[] query = new long[m];
        for (int i = 0; i < m; i++) {
            query[i] = in.nextLong();
        }
        for (int i : len) {
            System.out.print(i + " ");
        }
        System.out.println();
        process(len, query);
    }

    public static void process(int[] len, long[] query) {
        int n = len.length;
        for (int i = 0; i < n; i++) {
            len[i] *= len[i]; // 边长数组 -> 重量数组
        }
        // 从小到大排序巧克力重量
        Arrays.sort(len);
        // 计算 i 之前的巧克力重量和
        for (int i = 1; i < n; i++) {
            len[i] += len[i - 1]; // 重量数组 -> 重量前缀和数组
        }
        boolean isFind = false;
        for (long q : query) {
            for (int j = n - 1; j >= 0; j--) {
                if (q >= len[j]) {
                    System.out.print(j + 1 + " ");
                    isFind = true;
                    break;
                }
            }
        }
        if (!isFind) {
            System.out.println(0);
        }
    }
//    public static void process(int[] len, long[] query) {
//        int n = len.length;
//        for (int i = 0; i < n; i++) {
//            len[i] *= len[i]; // 从长度转换成质量
//        }
//        Arrays.sort(len); // 对巧克力质量进行排序
//        // 计算巧克力质量前缀和
//        for (int i = 1; i < len.length; i++) {
//            len[i] += len[i - 1];
//        }
//        for (long q : query) {
//            boolean tag = false; // 是否有满足条件的巧克力
//            for (int j = len.length - 1; j >= 0; j--) { // 从后往前，可以最快找到满足条件的质量和
//                if (q >= len[j]) { // 第一个满足条件
//                    System.out.print(j + 1 + " "); // 返回巧克力数量（前缀和数组下标 + 1）
//                    tag = true; // 满足条件
//                    break;
//                }
//            }
//            if (!tag) { // 没有满足条件的巧克力
//                System.out.println(0); // 返回 0
//            }
//        }
//    }
}
