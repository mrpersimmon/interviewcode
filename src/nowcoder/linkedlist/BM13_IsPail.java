package nowcoder.linkedlist;

public class BM13_IsPail {
    public boolean isPail(ListNode head) {
        // 空链表为回文结构
        if (head == null)
            return true;
        // 准备快慢双指针
        ListNode slow = head;
        ListNode fast = head;
        // 双指针寻找中点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 中点处开始反转
        slow = reverse(slow);
        fast = head;
        while (slow != null) {
            // 比较快慢指针所指的值是否相等
            if (slow.val != fast.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }
    // 反转链表
    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        ListNode cur = head;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
