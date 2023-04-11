package nowcoder.hashmap;

import java.util.Arrays;
import java.util.HashMap;

public class BM50_TwoSum {
    public int[] twoSum (int[] numbers, int target) {
        int[] res = new int[0];
        // 创建哈希表,两元组分别表示值、下标
        HashMap<Integer, Integer> map = new HashMap<>();
        // 在哈希表中查找 target - numbers[i]
        for (int i = 0; i < numbers.length; i++) {
            int tmp = target - numbers[i];
            // 若是没找到，将当前数组元素和下标计入哈希表
            if (!map.containsKey(tmp)) {
                map.put(numbers[i], i);
            } else {
                // 否则返回两个下标 +1
                return new int[] {map.get(tmp) + 1, i + 1};
            }
        }
        return res;
    }
}
