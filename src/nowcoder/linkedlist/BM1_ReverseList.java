package nowcoder.linkedlist;

// https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca

public class BM1_ReverseList {
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

    // --------------- 递归实现反转链表 ---------------
    // 递归算法，最重要的就是明确递归函数的定义
    // 定义：输入一个节点 head，将「以 head 为起点」的链表反转，并返回反转之后的头节点。
    public ListNode reverse(ListNode head) {
        if(head == null || head.next == null) { // base case
            return head;
        }
        ListNode last = reverse(head);
        head.next.next = head;
        head.next = null;
        return last;
    }
}


