package geektime;

public class GK39_8Queens {
    /**
     * 回溯思想：
     *  枚举所有的解，找到满足期望的解。
     *  为了有规律地枚举所有可能的解，避免遗漏和重复，我们把问题求解的过程分为多个阶段。
     *  每个阶段，我们都会面对一个岔路口，
     *  我们先随意选一条路走，当发现这条路走不通的时候（不符合期望的解），
     *  就回退到上一个岔路口，另选一种走法继续走。
     */

    /**
     * 8 皇后问题
     * 一个 8*8 的棋盘，往里放 8 个棋子（皇后），
     * 每个棋子所在的行、列、对角线都不能有另一个棋子。
     * <p>
     * 期望找到所有满足这种要求的放棋子方式。
     * <p>
     * 解决方案：
     * 把问题划分成 8 个阶段，依次将 8 个棋子放到第一行、第二行、第三行...第八行。
     * 在放置的过程中，我们不停地检查当前的方法，是否满足要求。
     * 如果满足，则跳到下一行继续放置棋子；
     * 如果不满足，那就再换一种方法，继续尝试。
     */
    static int[] result = new int[8]; //全局或成员变量，下标表示行，值表示 queen 存储在那一列。
    static int count = 0;
    public static void cal8Queens(int row) { // 调用方式：cal8Queens(0);
        if (row == 8) { // 8 个棋子都放好了，打印结果 base case
            count++;
            printQueens(result);
            System.out.println(count);
            return; // 8 行棋子都放好了，已经没法再往下递归了，所以就 return
        }
        for (int column = 0; column < 8; column++) { // 每一行都有 8 种放法
            if (isOk(row, column)) { // 有些方法不满足要求
                result[row] = column; // 第 row 行的棋子放到了 column 列
                cal8Queens(row + 1); // 考察下一行
            }
        }
    }

    private static boolean isOk(int row, int column) { // 判断 row 行 column 列放置是否合适
        int leftup = column - 1, rightup = column + 1; // 左边，右边
        for (int i = row - 1; i >= 0; i--) { // 逐行往上考察每一行
            if (result[i] == column) return false; // 第 i 行的 column 列有棋子吗？

            if (leftup >= 0) { // 考察左上对角线：第 i 行 leftup 列有棋子吗？
                if (result[i] == leftup) return false;
            }

            if (rightup < 8) { // 考察右上对角线：第 i 行 rightup 列有棋子吗？
                if (result[i] == rightup) return false;
            }
            leftup--; rightup++;
        }
        return true;
    }

    private static void printQueens(int[] result) { // 打印出一个二维矩阵
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (result[row] == column)
                    System.out.print("Q ");
                else
                    System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        cal8Queens(0);
    }

}
