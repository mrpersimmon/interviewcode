package nowcoder.linkedlist;

// https://www.nowcoder.com/practice/b49c3dc907814e9bbfa8437c251b028e
public class BM3_ReverseKGroup {
    // 将给出的链表中的节点每 k 个一组翻转，返回翻转后的链表
    // 如果链表中的节点数不是 k 的倍数，将最后剩下的节点保持原样
    // 你不能更改节点中的值，只能更改节点本身。

    // 解决步骤
    // 1. 每次从进入函数的头节点遍历链表 k 次，分出一组，若后续不足 k 个节点，不用反转直接返回。
    // 2. 从进入函数的头结点开始，依次反转接下来的一组链表。
    // 3. 这一组经过反转后，原来的头变成了尾，后面接下一组的反转结果，下一组采用上述递归继续。
    public ListNode reverseKGroup (ListNode head, int k) {
        if (head == null)
            return head;
        ListNode tmp = new ListNode(-1);
        tmp.next = head;
        process(tmp.next, k);
        return tmp.next;
    }

    public ListNode process(ListNode head, int k) {
        ListNode tail = head;
        // 遍历 k 次，找到结尾节点
        for (int i = 0; i < k; i++) {
            // 如果不足 k 个节点，直接返回，不反转
            if (tail == null) {
                return head;
            }
            tail = tail.next;
        } // tail 此时是下一组的第一个节点
        // 反转节点
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        while (cur != tail) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head.next = process(tail, k); // head 是当前组的最后一个节点
        return pre; // 反转后的头
    }

    // 递归实现反转链表
    // 反转以 a 为头节点的链表，其实就是 「反转 a 到 null 之间的节点」
    ListNode reverse(ListNode a) {
        ListNode pre, cur, nxt;
        pre = null; cur = a; nxt = a;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    // 反转 a 到 b 之间的节点 [a, b)
    ListNode reverse(ListNode a, ListNode b) {
        ListNode pre = null, cur = a, nxt = a;
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    // 递归反转 k 个一组
    // 1. 先反转以 head 开头的 k 个元素
    // 2. 将 k + 1 个元素作为 head 递归调用 reverseKGroup 函数
    // 3. 将上述两个过程的结果连接起来
    ListNode reverseKGroup_Recursion(ListNode head, int k) {
        if (head == null)
            return null;
        // 区间 [a, b) 包含 k 个待反转元素
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            // 不足 k 个，不需要反转，base case
            if (b == null)
                return head;
            b = b.next;
        }
        // 反转前 k 个元素
        ListNode newHead = reverse(a, b);
        // 递归反转后续链表并连接起来
        a.next = reverseKGroup_Recursion(b, k);
        return newHead;
    }


}
