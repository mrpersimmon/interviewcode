package nowcoder.linkedlist;

public class BM9_RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 添加表头
        ListNode pivot = new ListNode(-1);
        pivot.next = head;
        // 前序指针
        ListNode pre = pivot;
        // 慢指针
        ListNode slow = head;
        // 快指针
        ListNode fast = head;
        // 快指针先走 n 步
        while (n != 0) { // 题目保证了 n 一定有效
            fast = fast.next;
            n--;
        }
        // 三指针同步前移，快指针到达末尾，慢指针就到了倒数第 n 个位置
        while (fast != null) {
            fast = fast.next;
            pre = pre.next;
            slow = slow.next;
        }
        // 删除慢指针节点
        pre.next = slow.next;
        // 去掉表头返回
        return pivot.next;
    }
}
