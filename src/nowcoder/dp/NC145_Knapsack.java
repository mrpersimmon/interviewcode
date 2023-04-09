package nowcoder.dp;

public class NC145_Knapsack {
    public static int knapsack (int V, int n, int[][] vw) {
        // write code here
        // n : 0 - n-1
        // v : 0 - V
        int[][] states = new int[n][V + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < V + 1; j++) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        states[0][vw[0][0]] = vw[0][1];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= V; j++) { // 不放 i 物品
                if (states[i - 1][j] >= 0) {
                    states[i][j] = states[i - 1][j];
                }
            }
            for (int j = 0; j <= V - vw[i][0]; j++) { // 放 i 物品
                if (states[i - 1][j] >= 0) {
                    // 放了 i 物品的重量
                    int cw = states[i - 1][j] + vw[i][1];
                    //
                    if (cw > states[i][j + vw[i][0]]) {
                        states[i][j + vw[i][0]] = cw;
                    }
                }
            }
        }
        int maxV = -1;
        for (int j = 0; j <= V; j++) {
            if (states[n - 1][j] > maxV) {
                maxV = states[n - 1][j];
            }
        }

        return maxV;
    }

    public static void main(String[] args) {
        int[][] vw = {{2,7},{0,9}};
        System.out.println(knapsack(10, 2, vw));
    }
}
