package nowcoder.linkedlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class BM12_SortInList {
    // PriorityQueue
    public static ListNode sortInList (ListNode head) {
        if (head == null)
            return head;
        // write code here
        PriorityQueue<ListNode> queue = new PriorityQueue<>((l1, l2) -> l1.val - l2.val);
        ListNode pivot = new ListNode(-1);
//        pivot.next = head;
        ListNode cur = head;
        ListNode next = null;
        queue.add(cur);
        while (cur != null) {
            next = cur.next;
            cur.next = null;
            queue.add(cur);
            cur = next;
        }
        cur = pivot;
        while (!queue.isEmpty()) {
            next = queue.poll();
            cur.next = next;
            cur = cur.next;
        }

        return pivot.next;
    }

    // MergeSort
    public ListNode sortInList2(ListNode head) {
        // 链表为空或只有一个元素，直接就是有序的，返回即可。
        if (head == null || head.next == null)
            return head;
        // 构建三个指针
        ListNode left = head;
        ListNode mid = head.next;
        ListNode right = head.next.next;
        // 右边的指针到达链表终点时，中间的指针指向该段链表的中间节点
        while (right != null && right.next != null) {
            right = right.next.next;
            mid = mid.next;
            left = left.next;
        }
        // 中间指针的前序指针指向左段的最后一个节点，从这里断开
        left.next = null;
        // 分成两段排序，合并排好序的两段
        return merge(sortInList2(head), sortInList2(mid));
    }

    ListNode merge(ListNode head1, ListNode head2) {
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
        ListNode pivot = new ListNode(-1);
        ListNode p = pivot;
        while(head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                p.next = head1;
                head1 = head1.next;
            } else {
                p.next = head2;
                head2 = head2.next;
            }
            p = p.next;
        }
        if (head1 != null)
            p.next = head1;
        else
            p.next = head2;
        return pivot.next;
    }

    // Arrays.sort
    public ListNode sortInList3(ListNode head) {
        ArrayList<Integer> nums = new ArrayList<>();
        ListNode p = head;
        // 遍历链表，将节点值加入数组
        while (p != null) {
            nums.add(p.val);
            p = p.next;
        }
        p = head; // 重置下标
        // 对数组排序
        Collections.sort(nums);
        // 遍历数组
        for (int i = 0; i < nums.size(); i++) {
            p.val = nums.get(i);
            p = p.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(-1);
        a.next = new ListNode(0);
        a.next.next = new ListNode(-2);
        a.next.next.next = null;
        a = sortInList(a);
        while (a != null) {
            System.out.println(a.val);
            a = a.next;
        }
    }
}
