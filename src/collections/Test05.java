package collections;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class Test05 {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("java", "nihao");
        map.put("go", "nihao");
        map.put("c++", "nihao");
//        TreeSet<String> treeSet = new TreeSet<>((o1, o2) -> o1.compareTo(o2));
//        treeSet.add("java");
//        treeSet.add("jva");
        String input = "olleh, woh era uoy!";
        StringBuilder s = new StringBuilder(input);
        int i = 0;
        int j = 0;
        while (i < input.length()) {
            char tmp = input.charAt(i);
            if (tmp < 'a' || tmp > 'z') {
                reverseString(s, j, i - 1);
                j = i + 1;
            }
            i++;
        }
        System.out.println(s.toString());
    }

    public static void reverseString(StringBuilder sb, int start, int end) {
        // System.out.println("ReverseWords.reverseString() called with: sb = [" + sb + "], start = [" + start + "], end = [" + end + "]");
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
        // System.out.println("ReverseWords.reverseString returned: sb = [" + sb + "]");
    }


}
