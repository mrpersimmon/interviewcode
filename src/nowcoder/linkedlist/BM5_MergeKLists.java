package nowcoder.linkedlist;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class BM5_MergeKLists {
    // 1. 从链表数组的首和尾开始，每次划分从中间开始划分，划分成两半，得到左边 n/2 个链表和右边 n/2 个链表。
    // 2. 继续不断递归划分，直到每部分链表数为 1。
    // 3. 将划分好的相邻两部分链表，按照两个有序链表合并的方式合并，合并好的两部分继续向上合并，直到最终合并成一个链表。

    // 合并两个有序链表
    public ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;

        ListNode pivot = new ListNode(-1);
        ListNode cur = pivot;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        if (list1 != null)
            cur.next = list1;
        else
            cur.next = list2;

        return pivot.next;
    }
    // 划分合并区间函数
    public ListNode divideMerge(ArrayList<ListNode> lists, int left, int right) {
        if (left > right)
            return null;
        else if (left == right) // 中间 1 个的情况
            return lists.get(left);
        // 从中间分成两段，再将合并好的两段合并
        int mid = (left + right) / 2;
        return merge(divideMerge(lists, left, mid), divideMerge(lists, mid, right));
    }

    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        return divideMerge(lists, 0, lists.size() - 1);
    }


    /**
     * 方法 2：优先级队列
     */
    public ListNode mergeKLists_2(ArrayList<ListNode> lists) {
        // 小根堆
        PriorityQueue<ListNode> pq = new PriorityQueue<>((l1, l2) -> l1.val - l2.val);
        // 遍历所有链表的头节点
        for (int i = 0; i < lists.size(); i++) {
            // 不为空加入小根堆
            if (lists.get(i) != null)
                pq.add(lists.get(i));
        }
        // 哨兵表头
        ListNode pivot = new ListNode(-1);
        ListNode cur = pivot;
        // 直到小根堆为空
        while (!pq.isEmpty()) {
            // 取出最小节点
            ListNode pollNode = pq.poll();
            // 连接
            cur.next = pollNode;
            cur = cur.next;
            // 如果当前最小节点的后序节点不为空，加入小根堆
            if (pollNode.next != null) {
                pq.add(pollNode.next);
            }
        }
        // 去掉哨兵节点
        return pivot.next;
    }

}
