package geektime;

/**
 * 一个 n*n 的矩阵 w[n][n]。矩阵存储都是正整数。
 * 棋子起始位置在左上角，终止位置在右下角。
 * 我们将棋子从左上角移动到右下角。
 * 每次只能向右或向下移动一位。
 * 从左上到右下，会有很多不同的路径可以走。
 * 把每条路径经过的数字加起来看作路径的长度。
 * 求：从左上角移动到右下角的最短路径长度是多少？
 */
public class GK41_MinDistBT {
    public static int minDist = Integer.MAX_VALUE;
    // w 棋盘，
    public void minDistBT(int i, int j, int dist, int[][] w, int n) {
        if (i == n || j == n) { // base case
            if (dist < minDist) {
                minDist = dist;
            }
            return;
        }
        // 向下走
        if (i < n) {
            minDistBT(i + 1, j, dist + w[i][j], w, n);
        }
        // 向右走
        if (j < n) {
            minDistBT(i, j + 1, dist + w[i][j], w, n);
        }
    }
    public static void minDist_dp(int i, int j, int[][] w, int n) {
        int[][] states = new int[n][n];
//        for (int k = 0; k < n; k++) {
//            for (int l = 0; l < n; l++) {
//                states[k][l] = -1;
//            }
//        }
        states[0][0] = w[0][0];
        for (int k = 1; k < n; k++) {
            states[0][k] = states[0][k - 1] + w[0][k];
            states[k][0] = states[k - 1][0] + w[k][0];
        }
        for (int p = 1; p < n; p++) {
            for (int q = 1; q < n; q++) {
                states[p][q] = w[p][q] + Math.min(states[p][q - 1], states[p - 1][q]);
            }
        }
        minDist = states[n - 1][n - 1];
    }
    public static int minDistDP(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        int sum = 0;
        for (int j = 0; j < n; ++j) { // 初始化 states 的第一行数据
            sum += matrix[0][j];
            states[0][j] = sum;
        }
        sum = 0;
        for (int i = 0; i < n; ++i) { // 初始化 states 的第一列数据
            sum += matrix[i][0];
            states[i][0] = sum;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                states[i][j] =
                        matrix[i][j] + Math.min(states[i][j-1], states[i-1][j]);
            }
        }
        return states[n-1][n-1];
    }

    public static void main(String[] args) {
        int[][] w = {
                {1, 3, 5, 9},
                {2, 1, 3, 4},
                {5, 2, 6, 7},
                {6, 8, 4, 3}
        };
        minDist_dp(0, 0, w, 4);
        System.out.println(minDist);
        System.out.println(minDistDP(w,4));
    }
}
