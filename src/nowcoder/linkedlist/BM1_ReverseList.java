package nowcoder.linkedlist;

// https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca
// https://www.nowcoder.com/practice/b58434e200a648c589ca2063f1faf58c
public class BM1_ReverseList {
    public ListNode ReverseList(ListNode head) {
        if (head == null)
            return head;
        // 准备三个指针，pre，cur，next
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        // 反转
        while (cur != null) {
            next = cur.next; // 保存下一个
            cur.next = pre; // 反转节点 next
            pre = cur; // 移动节点
            cur = next;
        }
        return pre;
    }

    // --------------- 递归实现反转链表 ---------------
    // 递归算法，最重要的就是明确递归函数的定义
    // 定义：输入一个节点 head，将「以 head 为起点」的链表反转，并返回反转之后的头节点。
    public ListNode reverse(ListNode head) {
        if(head == null || head.next == null) { // base case
            return head;
        }
        ListNode last = reverse(head);
        head.next.next = head;
        head.next = null;
        return last;
    }


    // --------------- 递归实现反转前 N 个节点 -----------
    // 定义：反转以 head 为起点的 n 个节点，返回新的头节点
    ListNode successor = null; // 后驱节点
    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
    }

    // ------------ 递归实现反转指定区间中的链表元素 --------

    ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) { // base case
            return reverseN(head, n); // 相当于反转前 n 个元素
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }
    // m != 1，将 head 的索引看作 1，那么我们就是从第 m 个元素开始反转；
    // 如果把 head.next 的索引看作 1 呢？那么相对于 head.next，反转的区间应该是从第 m - 1 个元素开始的；

}


