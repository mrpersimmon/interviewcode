package nowcoder.twopointers;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 给出一组区间，请合并所有重叠的区间。
 * 请保证合并后的区间按区间起点升序排列。
 * 关联题目：
 *  greedy > BM95_Candy
 *  greedy > BM96_MinmumNumberOfHost
 */
public class NC37_MergeInterval {
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> ans = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return ans;
        }
        intervals.sort(new StartComparator());
        Interval pivot = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) { // 遍历数组
            Interval cur = intervals.get(i);
            // 如果 pivot 的 end 值小于 cur 的 start 值，直接加入 ans。
            if (pivot.end < cur.start) {
               ans.add(pivot);
               pivot = cur; // pivot 移向下一个区间
            } else {
                // 如果 pivot 的 end 值大于 cur 的 start 值，那么，需要进行合并。
                // 合并的条件是前后两个 interval 谁的 end 值大，谁作为新区间的 end。
                pivot.end = Math.max(pivot.end, cur.end);
                // 合并后的区间需要等待下一轮的判断，不能加入到 ans 中。
            }
        }
        // 需要把最后一个 interval 加入 ans 中。
        ans.add(pivot);
        return ans;
    }
    public class StartComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval o1, Interval o2) {
            return o1.start - o2.start;
        }
    }
}

class Interval {
    int start;
    int end;
    Interval() {
        start = 0;
        end = 0;
    }
    Interval(int s, int e) {
        start = s;
        end = e;
    }
}
