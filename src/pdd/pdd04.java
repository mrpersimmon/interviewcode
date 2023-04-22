package pdd;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class pdd04 {
    /**
     * 1. 多多君和皮皮虾轮流进行金币的选取，由多多君先选
     * 2. 当金币数量是偶数时，可以选择拿走一半的金币，也可以只拿走一枚金币
     * 3. 当金币数量是奇数时，只能拿走一枚金币
     *
     * 多多君和皮皮虾两人都会选择自己能拿走最多金币的策略，请问两人分别可以拿走多少金币。
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] inputs = new int[n];
        // 存储先手能拿到的最大数量
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 1);
        map.put(3, 2);
        map.put(4, 3);
        for (int i = 0; i < n; i++) {
            int input = in.nextInt();
            int ans = cal(input, map);
        }
    }

    // 计算当前选手能拿到的最大数量
    private static int cal(int n, Map<Integer, Integer> map) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int mod = n % 4;
        if (mod == 0) {
            // 4 的倍数，先手始终拿 1 个，后手也只能拿一个，主动权永远在先手，
            // 先手能拿到的数量 1 + cal(n-2) 「先手能拿到的最大数量」
            map.put(n, 1 + cal(n - 2, map));
        } else if (mod == 2) {
            // 偶数，不是 4 的倍数，先手直接拿一半，后手只能拿一个，还是偶数，继续拿一半。
            // 所以，先手能拿到 n - cal(n / 2)「后手能拿到的最大数量」
            map.put(n, n - cal(n / 2, map));
        } else { // cal(n - 1) 后手能拿到的最大数量
            map.put(n, n - cal(n - 1, map));
        }
        return map.get(n);
    }


    private static int process(int n) {
        if (n == 1)
            return 1;
        if (n == 4 || (n & 3) == 2) {
            return n - process(n / 2);
        }
        return n - process(n - 1);
    }
}
