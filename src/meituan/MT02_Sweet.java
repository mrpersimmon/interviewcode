package meituan;

import java.util.Scanner;

/**
 * 从一堆 n 个编号分别为 1, 2, ..., n 的糖果中选择任意多个糖果作为奖励（每种编号的糖果各一个）。
 * 限制：如果选择了编号 i 的糖果，就不能选择编号为 i-1, i-2, i+1, i+2 的四个糖果了。
 * 每个糖果有一个美味值。
 * 求美味值之和最大。
 *
 * 输入：
 * 第一行 1 整数 n，表示糖果数量。
 * 第二行 n 个整数，表示糖果美味值。
 *
 * 输出：糖果美味值最大值。
 *
 * 7
 * 3 1 2 7 10 2 4
 *
 * 14
 */
public class MT02_Sweet {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        int[] sweet = new int[n];
        for (int i = 0; i < n; i++) {
            sweet[i] = in.nextInt();
        }
        process(sweet);
    }

    public static void process(int[] sweet) {
        int n = sweet.length;
        // 糖果数量 <= 3
        if (n <= 3) {
            int ans = sweet[0];
            for (int i = 1; i < n; i++) {
                ans = Math.max(ans, sweet[i]);
            }
            return;
        }
        // 糖果数量 > 3，糖果隔两天才能吃一粒
        // 0 -> 0, 1 -> max(0, 1), 2 -> max(1, 2)
        sweet[1] = Math.max(sweet[0], sweet[1]);
        sweet[2] = Math.max(sweet[1], sweet[2]);
        for (int i = 3; i < n; i++) { // 3 -> max(2, 3 + 0)
            sweet[i] = Math.max(sweet[i - 1], sweet[i] + sweet[i - 3]);
        }
        System.out.println(sweet[n - 1]);
    }

//    public static void process(int[] sweet) {
//        int n = sweet.length;
//        if (n <= 3) {
//            int ans = sweet[0];
//            for (int i = 1; i < n; i++) {
//                ans = Math.max(ans, sweet[i]);
//            }
//            System.out.println(ans);
//            return;
//        }
//        // 糖果数量多于 3
//        sweet[1] = Math.max(sweet[0], sweet[1]);
//        sweet[2] = Math.max(sweet[1], sweet[2]);
//        for (int i = 3; i < sweet.length; i++) {
//            sweet[i] = Math.max(sweet[i - 1], sweet[i] + sweet[i - 3]);
//        }
//
//        System.out.println(sweet[n - 1]);
//    }
}
