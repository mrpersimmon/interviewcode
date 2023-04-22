package review.R210421;

import review.ListNode;

public class BM02_ReverseBetween {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null)
            return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        for (int i = 1; i < m; i++) {

        }
        return dummy.next;
    }
}
