package pdd;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class pdd02 {
    /**
     * 二维平面上有 n 个点，它们的横纵坐标均为正整数，且没有两个点有相同的横坐标。
     * 把所有点从左到右依次通过直线连接，形成的折线与横坐标可形成一片投影区间。
     * 若最多只能交换其中两点的纵坐标值，问如何交换可得到最大的投影面积。
     *
     * 输入描述第一行为坐标上点的个数n。接下来n行输入每个点的横纵坐标值。
     *
     * 输出描述需要交换两点的横坐标值，按增序排列，若无需交换则输出-1。
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        for ()
    }
    public static void cal(int[][] points) {
        if (points.length <= 1) {
            System.out.println(-1);
            return;
        }
        Arrays.sort(points, Comparator.comparingInt(p -> p[0]));
        // 保存每个点左右两边边长和，横坐标做高，纵坐标做底。
        int[] len = new int[points.length];
        len[0] = points[1][0] - points[0][0];
        len[len.length - 1] = points[len.length - 1][0] - points[len.length - 2][0];
        for (int i = 1; i < points.length - 1; i++) {
            len[i] = points[i + 1][0] - points[i - 1][0];
        }
        int max = -1;
        int res1 = 0;
        int res2 = 0;
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (len[i] * (points[j][1]- points[i][1]) + len[j] * (points[i][1] - points[j][1]) > max) {
                    max = len[i] * (points[j][1]- points[i][1]) + len[j] * (points[i][1] - points[j][1]);
                    res1 = points[i][0];
                    res2 = points[j][0];
                }
            }
        }
        System.out.println(max > 0 ? (res1 + " " + res2) : -1);
    }
}
