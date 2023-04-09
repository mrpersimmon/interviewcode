package nowcoder.linkedlist;

public class BM11_AddInList {
    public ListNode addInList(ListNode head1, ListNode head2) {
        // 1. 任意一个链表为空，返回另一个
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
        // 2. 反转两个链表
        head1 = reverse(head1);
        head2 = reverse(head2);
        // 3. 添加表头，设置进位
        ListNode ans = new ListNode(-1);
        ListNode pivot = ans;
        int carry = 0; // 进位
        // 4. 只要某个链表不为空，或者还有进位
        while (head1 != null || head2 != null || carry != 0) {
            // 链表不为空则取其值，否则设为 0
            int val1 = head1 == null ? 0 : head1.val;
            int val2 = head2 == null ? 0 : head2.val;
            // 相加
            int sum = val1 + val2 + carry;
            // 获取进位
            carry = sum / 10;
            // 获取节点值
            sum %= 10;
            // 结果链表添加节点
            pivot.next = new ListNode(sum);
            pivot = pivot.next;
            // 待相加链表节点不为空，则向下移动
            if (head1 != null) {
                head1 = head1.next;
            }
            if (head2 != null) {
                head2 = head2.next;
            }
        }
        return reverse(ans.next);
    }




    public ListNode addInList_me(ListNode head1, ListNode head2) {
        // write code here
        // 反转两条链表
        // 从最后一对开始计算，设置一个变量保存进位。
        // 1. 两条链表不为空，看最后有进位，就新建一个节点。
        // 2. 一条空，一条不空，最后一个不空的，遍历到尾节点，如果有进位，就新建一个节点。
        // 3. 反转最后的链表
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;

        ListNode ans = new ListNode(-1);

        ListNode pivot = ans;
        ListNode p1 = head1;
        ListNode p2 = head2;

        // 1. 反转
        p1 = reverse(p1);
        p2 = reverse(p2);

        int carry = 0;
        int sum = 0;
        // 2. 两个链表都存在
        while (p1 != null && p2 != null) {
            sum = p1.val + p2.val + carry;
            if (sum >= 10) {
                carry = 1; // sum / 10
                sum = sum % 10;
                pivot.next = new ListNode(sum);
            } else {
                carry = 0;
                pivot.next = new ListNode(sum);
            }
            p1 = p1.next;
            p2 = p2.next;
            pivot = pivot.next;
        }

        while (p1 != null) {
            sum = p1.val + carry;
            if (sum >= 10) {
                carry = 1; // sum / 10
                sum = sum % 10;
                pivot.next = new ListNode(sum);
            } else {
                carry = 0;
                pivot.next = new ListNode(sum);
            }
            p1 = p1.next;
            pivot = pivot.next;
        }

        while (p2 != null) {
            sum = p2.val + carry;
            if (sum >= 10) {
                carry = 1; // sum / 10
                sum = sum % 10;
                pivot.next = new ListNode(sum);
            } else {
                carry = 0;
                pivot.next = new ListNode(sum);
            }
            p2 = p2.next;
            pivot = pivot.next;
        }

        if (carry != 0) {
            pivot.next = new ListNode(1);
        }

        pivot = ans.next;
        ans.next = null;
        pivot = reverse(pivot);

        return pivot;
    }
    public ListNode reverse(ListNode head) {
        if (head == null)
            return head;
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
