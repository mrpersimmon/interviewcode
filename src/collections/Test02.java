package collections;


import java.util.ArrayList;
import java.util.List;

public class Test02 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        // add 添加单个元素
        list.add("N1");
        list.add("N2");
        list.add("N3");
        System.out.println("list=" + list);
        // remove 删除指定元素
        list.remove(0); // 删除第一个元素
        list.remove("N2"); // 删除某个指定元素
        System.out.println("list=" + list);
        // contains 查找元素是否存在
        System.out.println(list.contains("N3"));
        // size 获取元素个数
        System.out.println(list.size());
        // isEmpty 判断是否为空
        System.out.println(list.isEmpty());
        // clear 清空
        list.clear();
        System.out.println("list=" + list);
        // addAll 添加多个元素
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("N4");
        list2.add("N5");
        list.addAll(list2);
        System.out.println("list=" + list);
        // containsAll 查找多个元素是否都存在
        System.out.println(list.containsAll(list2));
        // removeAll 删除多个元素
        list.add("N6");
        list.removeAll(list2);
        System.out.println("list=" + list);
    }
}

