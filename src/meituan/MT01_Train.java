package meituan;

import java.util.Scanner;
import java.util.Stack;

/**
 * 一个栈，
 * 第 1 行，1 个整数 T，表示数据组数
 * 每组数据
 * 第 1 行，1 个整数 n，表示火车数量
 * 第 2 行，n 个整数，表示火车入栈顺序
 * 第 3 行，n 个整数，表示记录的火车出栈顺序
 *
 * 判断记录的出栈顺序是否正确，正确返回 Yes，不正确返回 No
 */
public class MT01_Train {
    /**
     * 思路：
     * 1. 准备一个辅助栈，两个下标分别访问两个序列；
     * 2. 辅助栈为空或者栈顶不等于出栈数组当前元素，就持续将入栈数组加入栈中；
     * 3. 栈顶等于出栈数组当前当前元素就出栈；
     * 4. 当入栈数组访问完，出栈数组无法依次弹出，就是不匹配，否则两个序列都访问完就是匹配的。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt(); // 火车数量
            in.nextLine();
            int[] pushArr = new int[n];
            int[] popArr = new int[n];
            for (int j = 0; j < n; j++) {
                pushArr[j] = in.nextInt(); // 入栈顺序
            }
//            in.nextLine();
            for (int j = 0; j < n; j++) {
                popArr[j] = in.nextInt(); // 出栈顺序
            }
            process(pushArr, popArr);
        }
    }

    private static void process(int[] pushArr, int[] popArr) {
        int n = pushArr.length;
        Stack<Integer> help = new Stack<>();
        int j = 0; // 遍历入栈数组的指针
        for (int i = 0; i < n; i++) { // 遍历出栈数组
            // 入栈条件：辅助栈没满，且 辅助数组为空，或者辅助栈顶不等于当前出栈数组元素
            while (j < n && (help.isEmpty() || help.peek() != popArr[i])) {
                help.push(pushArr[j]);
                j++;
            }
            if (help.peek() == popArr[i]) {
                help.pop();
            } else {
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }
}
