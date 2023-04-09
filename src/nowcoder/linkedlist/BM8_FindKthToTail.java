package nowcoder.linkedlist;

public class BM8_FindKthToTail {
    // 1. 准备一个快指针，从链表头开始，在链表上先走 k 步。
    // 2. 准备慢指针指向原始链表头，代表当前节点，快慢指针之间的距离一直都是 k。
    // 3. 快慢指针同步移动，当快指针到达链表尾部的时候，慢指针正好到了倒数 k 个节点的位置。
    public ListNode FindKthToTail(ListNode pHead, int k) {
        ListNode fast = pHead;
        ListNode slow = pHead;
        // 快指针先行 k 步
        for (int i = 0; i < k; i++) {
            if (fast != null) {
                fast = fast.next;
            } else { // 达不到 k 步，说明链表过短。
                return slow = null;
            }
        }
        // 快慢指针同步，快指针先到底，慢指针指向倒数第 k 个
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode FindKthToTail_2(ListNode pHead, int k) {
        int n = 0;
        ListNode cur = pHead;
        // 遍历链表，统计链表长度
        while (cur != null) {
            n ++;
            cur = cur.next;
        }
        // 链表长度不足 k，返回 null
        if (n < k) {
            return null;
        }
        cur = pHead;
        // 遍历 n - k 次
        for (int i = 0; i < n - k; i++) {
            cur = cur.next;
        }
        return cur;
    }

}
