package nowcoder.greedy;

import java.util.Arrays;

/**
 *
 */
public class BM96_MinmumNumberOfHost {
    public int minmumNumberOfHost(int n, int[][] startEnd) {
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < startEnd.length; i++) {
            start[i] = startEnd[i][0];
            end[i] = startEnd[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int ans = 0; // 需要的主持人数量
        int j = 0; // 遍历结束时间数组指针
        for (int i = 0; i < start.length; i++) {
            if (start[i] < end[j]) {
                ans++;
            } else {
                j++;
            }
        }
        return ans;
    }
}
