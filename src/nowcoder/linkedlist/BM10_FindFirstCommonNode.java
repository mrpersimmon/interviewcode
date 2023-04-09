package nowcoder.linkedlist;

public class BM10_FindFirstCommonNode {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        // 1. 定义两个指针
        ListNode p1 = pHead1, p2 = pHead2;
        // 2. 指针遍历两个链表
        while (p1 != p2) {
            // p1 到链表 1 终点就从链表 2 开始遍历，否则继续向后遍历
            p1 = (p1 == null) ? pHead2 : p1.next;
            // p2 到链表 2 终点就从链表 1 开始遍历，否则继续向后遍历
            p2 = (p2 == null) ? pHead1 : p2.next;
        }
        // 3. 相遇节点就是两个链表的第一个公共节点
        return p1;
    }
}
