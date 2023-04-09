package meituan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {

    }

    public static int solution5(int[] score, int k) {
        if(score.length == 1)
            return score[0];
        int[][] res = new int[score.length][k + 1];
        for(int i = 0; i < k +1; i++){
            res[0][i] = score[0];
        }
        res[1][0] = Math.max(score[0], score[1]);
        for(int i = 2; i < score.length; i++) {
            res[i][0] = Math.max(res[i-1][0], res[i-2][0] + score[i]);//今天不吃，用昨天的最大值，和前天的最大值+今天吃
        }
        for(int i = 1; i < score.length; i++) {
            for (int j = 1; j <= k; j++) {
                res[i][j] = Math.max(res[i-1][j-1] + score[i], //用次数
                        Math.max(res[i-1][j], (i >= 2 ? res[i-2][j] : 0) + score[i]));// 不用次数
            }
        }
        return res[score.length-1][k];
    }

    public static void solution1(){

    }

    /**
     * 第四题
     *
     * @param str 最初始的字符串
     * @param strArr 询问数组
     */
    public static void solution4(String str, String[] strArr) {
        String[] tempArr = str.split(";");
        Map<String, String> map = new HashMap<>();
        for (String temp : tempArr) {
            if(temp == null || temp.equals(""))
                continue;
            String[] temp1 = temp.split("=");
            if(temp1.length == 2) {
                map.put(temp1[0], temp1[1]);
            }
        }
        for (String temp: strArr) {
            System.out.println(map.getOrDefault(temp, "EMPTY"));
        }
    }

    /**
     *
     * @param score
     * @param query
     */
    public static void solution3(long[] score, long[] query) {
        for (int i = 0; i < score.length; i++) {
            // 质量是边长的平方
            score[i] *= score[i];
        }
        Arrays.sort(score);
        for(int i = 1; i < score.length; i++) {// i 之前的所有糖果质量和
            score[i] += score[i - 1];
        }
        for (long l : query) {
            boolean tag = false;
            for (int j = score.length - 1; j >= 0; j--) {
                if (l >= score[j]) {
                    System.out.println(j + 1);
                    tag = true;
                    break;
                }
            }
            if (!tag)
                System.out.println(0);
        }
    }


    /**
     * 第二题
     *
     * @param score 甜蜜值数组
     */
    public static void solution2(int[] score){
        if(score.length <= 3){
            int res = score[0];
            for(int i = 1; i < score.length; i++){
                res = Math.max(res, score[i]);
            }
            System.out.println(res);
            return;
        }
        score[1] = Math.max(score[0], score[1]);
        score[2] = Math.max(score[1], score[2]);

        for(int i = 3; i < score.length; i++){
            score[i] = Math.max(score[i-1], score[i] + score[i-3]);
        }
        System.out.println(score[score.length-1]);

    }

}

