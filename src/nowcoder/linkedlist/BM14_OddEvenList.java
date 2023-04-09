package nowcoder.linkedlist;

public class BM14_OddEvenList {
    public ListNode oddEvenList(ListNode head) {
        // 1. 如果链表为空，不用重排
        if (head == null)
            return head;
        // 2.1 奇数位节点指针指向表头
        ListNode odd = head;
        // 2.2 偶数位节点指针指向第二个节点，可能为空
        ListNode even = head.next;
        // 2.2 偶数位头节点的哨兵节点，保留住偶数位头节点
        ListNode evenPivot = even;
        // 3. 偶数位节点及后序节点不为空，可以继续循环
        while (even != null && even.next != null) {
            // 3.1 奇数位节点指针连接偶数位节点的后一个
            odd.next = even.next;
            // 3.2 奇数位指针到下一个奇数位节点
            odd = odd.next;
            // 3.3 偶数位指针节点连接奇数位节点的后一个
            even.next = odd.next;
            // 3.4 偶数位指针进入下一个偶数位节点
            even = even.next;
        }
        // 4. 偶数位节点的头连在奇数位链表尾节点的后面
        odd.next = evenPivot;
        return head;
    }
}
