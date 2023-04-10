package nowcoder.linkedlist;

public class BM15_DeleteDuplicatesI {
    // 遍历删除
    public ListNode deleteDuplicates(ListNode head) {
        // 1. 空链表直接返回
        if (head == null)
            return head;
        // 2. 使用一个指针遍历链表，
        // 如果指针当前节点与下一个节点的值相同，我们就跳过下一个节点，
        // 当前节点直接连接下个节点的后一位。
        ListNode cur = head; // cur 遍历指针
        while (cur != null && cur.next != null) { // 当前节点和下一个节点不为空
            // 如果当前节点与下一个节点的值相同则忽略下一位
            if (cur.val == cur.next.val)
                cur.next = cur.next.next;
            else // 否则指针正常遍历
                cur = cur.next;
        }
        return head;
    }
}
