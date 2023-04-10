package nowcoder.linkedlist;

public class BM16_DeleteDuplicatesII {
    // 给出一个升序排序的链表，删除链表中的所有重复出现的元素，只保留原链表中只出现一次的元素。
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return null;
        // 1. 增加一个虚拟头节点，便于可能出现的删除链表的第一个节点的情况。
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy; // 遍历链表的指针
        // 2. 遍历链表，每次比较相邻两个节点，
        // 如果遇到了两个相邻节点相同，则新开内循环将这一段所有相同的都遍历过去。
        while (cur.next != null && cur.next.next != null) { // cur 从 dummy 开始的缘故
            // 遇到相邻两个节点值相同
            if (cur.next.val == cur.next.next.val) {
                int temp = cur.next.val;
                // 将所有相同的都跳过
                while (cur.next != null && cur.next.val == temp)
                    // 3. 一连串相同节点的前一个节点直接连上后续第一个不相同值的节点
                    cur.next = cur.next.next;
            } else {

                cur = cur.next;
            }
        }
        // 4. 返回时去掉虚拟头结点
        return dummy.next;
    }
}
