package nowcoder.linkedlist;

public class BM6_HasCycle {
    // 1. 设置快慢两指针，初始都指向链表头；
    // 2. 遍历链表，快指针每次走两步，慢指针每次走一步；
    // 3. 如果快指针到了链表末尾，说明没有环，因为它每次走两步，所以要验证连续两步是否为 null；
    // 4. 如果链表有环，那快慢双指针会在环内循环，因为快指针每次走两步，因此快指针会在环内追到慢指针，二者相遇就代表有环。
    public boolean hasCycle(ListNode head) {
        // 先判断链表为空的情况
        if (head == null)
            return false;
        // 1. 设置快慢两指针，初始都指向链表头
        ListNode fast = head;
        ListNode slow = head;
        // 2. 遍历链表
        // 3. 如果没环，fast 会先到链表尾
        while (fast != null && fast.next != null) {
            // 快指针移动两步
            fast = fast.next.next;
            // 慢指针移动一步
            slow = slow.next;
            // 相遇则有环
            if (fast == slow)
                return true;
        }
        // 到末尾，则没有环
        return false;
    }
}
