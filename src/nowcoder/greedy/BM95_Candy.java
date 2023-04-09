package nowcoder.greedy;

/**
 * 分糖果
 * 1. 每个孩子不管得分多少，起码分到一个糖果。
 * 2. 任意两个相邻的孩子之间，得分较多的孩子必须拿多一些糖果。（若相同则无此限制）
 *
 * 给定一个数组 arr 代表得分数组，请返回最少需要多少糖果。
 */
public class BM95_Candy {
    // 两次遍历，从左到右，从右到左。
    public int candy(int[] score) {
        if (score.length <= 1) {
            return score.length;
        }
        int[] help = new int[score.length]; // 辅助数组
        // 1. 初始化辅助数组，将元素都设置为 1
        for (int i = 0; i < help.length; i++) {
            help[i] = 1;
        }
        // 2. 从左到右遍历数组，如果右边比左边大，右边糖果设置为左边糖果数+1，否则保持不变。
        for (int i = 1; i < score.length; i++) {
            if (score[i] > score[i - 1]) {
                help[i] = help[i - 1] + 1;
            }
        }
        // 3. 从右到左遍历数组，如果左边比右边分数高 且 左边的糖果数 <= 右边的糖果数。左边的糖果数设置成右边糖果数+1，否则保持不变。
        for (int i = score.length - 2; i >= 0; i--) {
            if (score[i] > score[i + 1] && help[i] <= help[i + 1]) {
                help[i] = help[i + 1] + 1;
            }
        }
        // 4. 辅助数组元素的和就是最终答案。
        int ans = 0;
        for (int i : help) {
            ans += i;
        }
        return ans;
    }
}
