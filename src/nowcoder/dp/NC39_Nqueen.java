package nowcoder.dp;

/**
 * 在 n * n 棋盘上摆 n 个皇后。
 *
 * 要求：
 * 任何皇后不同行、不同列、也不在同一条斜线上。
 *
 * 求给一个整数 n，返回 n 皇后的摆法数。
 */
public class NC39_Nqueen {

    public int count = 0;
    public int Nqueen(int n) {
        int[] result = new int[n];
        process(0, n, result);
        return count;
    }

    public void process(int row, int n, int[] result) {
        if (row == n) { // 摆满了 n 行
            count++;
            return;
        }
        for (int column = 0; column < n; column++) { // 每一行有 n 种放置情况
            if (isOk(row, column, n, result)) {
                result[row] = column; // 在 row 行 column 列放置棋子
                process(row + 1, n, result);
            }
        }
    }

    public boolean isOk(int row, int column, int n, int[] result) {
        int leftup = column - 1, rightup = column + 1;
        for (int i = row - 1; i >= 0; i--) { // 从当前行向上逐层判断
            if (result[i] == column) return false; // i 行 column 列出现了棋子 false
            if (leftup >= 0) {
                if (result[i] == leftup) return false; // 左对角线存在棋子 false
            }
            if (rightup < n) {
                if (result[i] == rightup) return false; // 右对角线存在棋子 false
            }
            leftup--; rightup++;
        }
        return true;
    }
}
