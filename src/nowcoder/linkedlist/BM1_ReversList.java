package nowcoder.linkedlist;




public class BM1_ReversList {
    public ListNode ReverseList(ListNode head) {
        if (head == null)
            return head;
        // 准备三个指针，pre，cur，next
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        // 反转
        while (cur != null) {
            next = cur.next; // 保存下一个
            cur.next = pre; // 反转节点 next
            pre = cur; // 移动节点
            cur = next;
        }
        return pre;
    }
}


