package nowcoder.string;

public class BM86_BigNumAdd {
//public static void main(String[] args) {
//    System.out.println(solve("1", "99"));
//}

    public static String solve(String s, String t) {
        // 1. 其中一个为空，返回另一个
        if (s.length() <= 0)
            return t;
        if (t.length() <= 0)
            return s;
        // 2. 设置辅助参数
        int carry = 0; // 设置进位
        int i = s.length() - 1; // s 下标
        int j = t.length() - 1; // t 下标
        char[] help = new char[Math.max(i, j) + 1]; // 取最大的字符串长度作为辅助字符数组的长度
        int p = help.length - 1; // 辅助数组下标
        // 3. 只要某个字符串还有字符
        while (i != -1 || j != -1) {
            // 下标不为 -1，取出字符并转换成数字，否则为 0。
            int a = i == -1 ? 0 : s.charAt(i) - '0';
            int b = j == -1 ? 0 : t.charAt(j) - '0';
            // 相加
            int sum = a + b + carry;
            // 获取进位
            carry = sum / 10;
            // 获取放入辅助数组的值，取模去掉十位
            sum %= 10;
            // 将数值转换成字符
            help[p] = (char)(sum + '0');
            p--;
            // 下标不为 -1，向前移动
            if (i != -1)
                i--;
            if (j != -1)
                j--;
        }
        // 4. 将辅助字符数组转换成字符串
        String output = String.valueOf(help);
        // 如果进位还存在，在字符串前增加一个字符 '1'
        if (carry == 1)
            output = '1' + output;
        return output;
    }
}
