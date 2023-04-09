package meituan;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 读入一行字符串，格式为 key1=val1;key2=val2 ..., 共计 n 个键值对。
 * 均仅包含大小写英文字母，数字与斜杠的非空字符串。
 * 输入字符串："SHELL=/bin/bash;HOME=/home/xiaomei;LOGNAME=xiaomei;"
 *
 * 接受 q 次询问，每次询问给出一个仅包含大小写英文字母、数字与斜杠的非空字符串。
 * 如果存在某对 key value 的 key 值与之相同，输出对应 value。
 * 如果存在多对 key value 的 key 值相同，输出编号最大的，也就是最后一对 value 值。
 * 如果一对也不存在，那么就输出 EMPTY
 *
 */
public class MT04_Device {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String inputStr = in.nextLine();
//        int q = in.nextInt();
//        in.nextLine();
//        String[] qs = new String[q];
//        for (int i = 0; i < q; i++) {
//            qs[i] = in.nextLine();
//        }
//        HashMap<String, String> map = new HashMap<>();
//        String[] strArr = inputStr.split(";");
//        for (String s : strArr) {
//            if (s == null || s.equals(""))
//                continue;
//            String[] split = s.split("=");
//            if (split.length == 2)
//                map.put(split[0], split[1]);
//        }
//        for (String qt : qs) {
//            System.out.println(map.getOrDefault(qt, "EMPTY"));
//        }
//    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 接收输入
        String inStr = in.nextLine();
        int inQ = in.nextInt();
        in.nextLine();
        String[] queryArr = new String[inQ];
        for (int i = 0; i < inQ; i++) {
             queryArr[i] = in.nextLine();
        }
        // 处理输入的字符串
        HashMap<String, String> map = new HashMap<>();
        String[] inStrArr = inStr.split(";");
        for (String s : inStrArr) {
            if (s == null || s.equals("")) {
                continue;
            }
            String[] split = s.split("=");
            if (split.length == 2)
                map.put(split[0], split[1]);
        }
        // 回答问询
        for (String q : queryArr) {
            System.out.println(map.getOrDefault(q, "EMPTY"));
        }
    }

}
