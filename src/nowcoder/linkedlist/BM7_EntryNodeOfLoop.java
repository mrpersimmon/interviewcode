package nowcoder.linkedlist;

public class BM7_EntryNodeOfLoop {
    // 判断有没有环
    public ListNode hasCycle(ListNode head) {
        // 先判断链表为空的情况
        if (head == null)
            return null;
        // 快慢双指针
        ListNode fast = head;
        ListNode slow = head;
        // 如果没环，快指针会先到链表尾
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // 相遇则有环，返回相遇的节点
            if (slow == fast) {
                return slow;
            }
        }
        // 到末尾说明没有换，返回 null
        return null;
    }

    public ListNode entryNodeOfLoop(ListNode pHead) {
        ListNode slow = hasCycle(pHead);
        // 没有环
        if (slow == null)
            return null;
        // 有环
        // 快指针回到表头
        ListNode fast = pHead;
        // 再次相遇即是环入口
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
