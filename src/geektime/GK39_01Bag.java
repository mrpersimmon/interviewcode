package geektime;

/**
 * 0-1 背包问题
 * 题目描述：
 * 我们有一个背包，背包总的承载重量是 W kg。
 * 现有 n 个物品，每个物品重量不等且不可分割。
 * 求在不超过背包装载重量前提下，如何让背包中物品的总重量最大？
 * -------
 * 思路：
 * 对于每个物品来说，都有两种选择，装或不装。
 * 对于 n 个物品来说，总的装法就有 2^n 种。
 * 去掉总重量超过 W kg，从剩下的装法中选择总重量最接近 Wkg 的。
 * 关键：如何不重复地穷举出 2^n 中装法？
 * ----
 * 解决方案：回溯
 * 把物品依次排列，整个问题分解为 n 个阶段，每个阶段对应一个物品如何选择。
 * 对第一个物品进行处理，装或者不装，再递归处理剩下的物品。
 *
 * 技巧：搜索剪枝
 * 当发现已选择的物品的重量超过 Wkg 之后，停止继续探测剩下的物品。
 */
public class GK39_01Bag {
    private int maxW = Integer.MIN_VALUE; // 存储背包中物品总重量的最大值
    public int Knapsack(int[] items, int n, int w) {
        boolean[][] mem = new boolean[n][w];
        process(0, 0, items, n, w, mem);
        return maxW;
    }
    /**
     * 假设背包可承受重量 100，物品个数 10，物品重量存储在数组 a 中，
     * 可以这样调用函数 process(0, 0, a, 10, 100)
     * @param i 表示考察到哪个物品了
     * @param cw 表示当前已经装进去的物品重量和
     * @param items 表示每个物品的重量
     * @param n 表示物品个数
     * @param w 背包重量
     */
    public void process(int i, int cw, int[] items, int n, int w, boolean[][] mem) {
        // cw == w 表示已经装满了； i == n 表示已经考察完所有的物品
        if (cw == w || i == n) { // base case
            if (cw > maxW)
                maxW = cw;
            return;
        }
        if (mem[i][cw]) return; // 重复状态
        mem[i][cw] = true; // 记录状态
        process(i + 1, cw, items, n, w, mem); // 不装这个物品
        // 装这个物品
        if (cw + items[i] <= w) { // 如果装入不超过 w，就可以装
            process(i + 1, cw + items[i], items, n, w, mem);
        }
    }

    /**
     * dp 思路：
     * 把整个求解过程分为 n 个阶段，每个阶段会决策一个物品是否放到背包中。
     * 每个物品决策（放入或不放入）后，背包中的物品的重量会有多种情况，
     * 也就是说，会达到多种不同的状态，对应到递归树中，就是有很多不同的节点。
     *
     * 我们把每一层重复的状态（节点）合并，只记录不同的状态，
     * 然后基于上一层的状态集合，来推导下一层的状态集合。
     * 我们可以通过合并每一层重复的状态，这样就保证每一层不同状态的个数都不会超过 w 个（w 表示背包的承载重量）。
     * 于是，我们就成功避免了每层状态个数的指数级增长。
     *
     *
     */
    // n 物品个数
    // w 重量上限
    public static int knapsack_dp(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w + 1];
        states[0][0] = true; // 第一行的数据要特殊处理，可以利用哨兵优化
        states[0][weight[0]] = true;
        for (int i = 1; i < n; i++) { // 动态规划状态转移
            for (int j = 0; j <= w; j++) { // 不选择第 i 个物品
                if (states[i-1][j] == true)
                    states[i][j] = states[i-1][j]; // 向上依赖
            }
            for (int j = 0; j <= w - weight[i]; j++) { // 选择第 j 个物品
                if (states[i - 1][j] == true)
                    states[i][j + weight[i]] = true;
            }
        }
        for (int wi = w; wi >= 0; wi--) { // 输出结果
            if (states[n-1][wi] == true) return wi;
        }
        return 0;
    }

    public static int knapsack_dp_2(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1];
        states[0] = true;
        states[items[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int j = w - items[i]; j >= 0; j--) { // 把第 i 个物品放入背包
                if (states[j] == true)
                    states[j + items[i]] = true;
            }
        }
        for (int i = w; i >= 0; i--) {
            if (states[i] == true) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] weight = {2, 2, 4, 6, 3};
        System.out.println(knapsack_dp(weight, 5, 9));
    }
}
