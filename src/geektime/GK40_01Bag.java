package geektime;

public class GK40_01Bag {
    private int maxV = Integer.MIN_VALUE;
    // n 物品数量
    // w 背包最大容量
    // items 物品重量数组
    // value 物品价值数组
    // 求在满足背包重量限制的前提下，求最大价值
    public void knapsack(int[] items, int[] value, int n, int w) {

    }

    public void process(int i, int cw, int cv, int[] items, int[] value, int n, int w) {
        if (i == n || cw == w) {
            if (cv > maxV) {
                maxV = cv;
            }
            return;
        }
        // 不装背包
        process(i + 1, cw, cv, items, value, n, w);
        // 装背包
        if (cw + items[i] <= w) {
            process(i + 1, cw + items[i], cv + value[i], items, value, n, w);
        }
    }


    public static int knapsack_dp(int[] weight, int[] value, int n, int w) {
        int[][] states = new int[n][w + 1];
        for (int i = 0; i < n; i++) { // 初始化
            for (int j = 0; j < w + 1; j++) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        states[0][weight[0]] = value[0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= w; j++) { // 不选择 i  物品
                if (states[i-1][j] >= 0) {
                    states[i][j] = states[i-1][j];
                }
            }
            for (int j = 0; j <= w - weight[i]; j++) { // 选择 i 物品
                if (states[i-1][j] >= 0) {
                    int v = states[i - 1][j] + value[i];
                    if (v > states[i][j + weight[i]]) {
                        states[i][j + weight[i]] = v;
                    }
                }
            }
        }
        int maxvalue = -1;
        for (int j = 0; j <= w; j++) {
            if (states[n-1][j] > maxvalue) // 最后一行
                maxvalue = states[n - 1][j];
        }
        return maxvalue;
    }

    public static void main(String[] args) {
        int[] w = {2, 0};
        int[] v = {7, 9};
        System.out.println(knapsack_dp(w, v, 2, 10));
    }
}
