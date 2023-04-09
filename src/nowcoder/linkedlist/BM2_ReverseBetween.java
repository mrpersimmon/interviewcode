package nowcoder.linkedlist;

public class BM2_ReverseBetween {
    // 题目要求：将一个节点数为 size 链表 m 位置到 n 位置之间的区间反转。

    // step1: 在链表前加一个表头，后续返回时去掉即可。
    // 因为如果要从链表头的位置开始反转，在多了一个表头的情况下，就能保证第一个节点永远不会反转，不会到后面去。
    // step2: 使用两个指针，一个指向当前节点，一个指向前序节点。
    // step3: 依次遍历链表，到第 m 个位置。
    // step4: 对于从 m 到 n 这些个位置的节点，依次断掉指向后续的指针，反转指针方向。
    // step5: 返回时，去掉添加的表头。

    public ListNode reverseBetween(ListNode head, int m, int n) {
        // 加一个表头
        ListNode tmp = new ListNode(-1);
        tmp.next = head;
        // 前序节点
        ListNode pre = tmp;
        // 当前节点
        ListNode cur = head;
        // 找到 m 节点
        for (int i = 1; i < m; i++) {
            pre = cur;
            cur = cur.next;
        }
        // 后序节点
        ListNode next = null;
        // 从 m 反转到 n
        for (int i = m; i < n; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return tmp.next;
    }

}
