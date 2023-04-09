package tencent;

import java.util.ArrayList;

public class Main {

    public ListNode reorderList (ListNode head) {
        // write code here
        // 判断链表长度是否是奇数还是偶数
        ListNode node = head;
        ArrayList<ListNode> help = new ArrayList<>();
        while (node != null) {
            help.add(node);
            node = node.next;
        }
        for (int i = 0; i < help.size(); i++) {
            if ((i + 2) >= help.size())
                break;
            swap(help, i, i + 2);
        }
        for (ListNode n : help) {
            System.out.print(n.val + " ");
        }
        return null;
    }

    public void swap(ArrayList<ListNode> list, int a, int b) {
        ListNode tmp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, tmp);
    }

    public static void main(String[] args) {
        System.out.println(1 ^ 1 ^ 1 ^ 2);
    }
}
class ListNode {
    public int val;
    public ListNode next = null;
    public ListNode(int val) {
        this.val = val;
    }
}
