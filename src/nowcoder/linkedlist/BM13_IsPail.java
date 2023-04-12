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




    // ----- ------------
    public static boolean isPail_1 (ListNode head) {
        // write code here
        if (head == null)
            return true;

        // find mid
        ListNode left = head;

        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 反转右半段的链表
        ListNode right = reverse_1(slow);
        ListNode rightPivot = right; // 用于复原

        boolean ans = true;
        while(left != null && right != null) {
            if (left.val != right.val) {
                ans = false;
            }
            left = left.next;
            right = right.next;
        }
        reverse_1(rightPivot); // 恢复后半段链表
        return ans;
    }



    static ListNode reverse_1(ListNode head) {
        if (head == null)
            return head;
        ListNode pre = null, cur = head, nxt = head;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }


    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        a.next = new ListNode(2);
        a.next.next = new ListNode(2);
        a.next.next.next = new ListNode(1);
        ListNode p = a;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println(isPail_1(a));
        p = a;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
    }
}
