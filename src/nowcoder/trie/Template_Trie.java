package nowcoder.trie;

import java.util.HashMap;

public class Template_Trie {

}


class TrieMap<V> {
    private static class TrieNode<V> {
        V val = null;
        int pass;
        int end;
        public HashMap<Integer, TrieNode<V>> nexts; // <字符 ASCII 码值，下级节点>

        public TrieNode() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }
    // 当前存在 Map 中的键值对个数
    private int size = 0;

    // Trie 树的根节点
    private TrieNode<V> root = null;

    // 返回 TrieMap 中键值对的个数
    public int size() {
        return size;
    }
    /*********** 增 ************/
    // 向 map 中添加或修改键值对
    public void put(String key, V val) {
        // TODO: !containsKey(key)
        if (key == null) {
            return;
        }
        TrieNode<V> node = root; // 从根节点开始
        node.pass++;

        int path = 0; // 字符路径
        for (int i = 0; i < key.length(); i++) { // 遍历字符串
            path = key.charAt(i); // 确定字符路径
            if (!node.nexts.containsKey(path)) { // 如果路径下面的节点是空的
                node.nexts.put(path, new TrieNode<>()); // 新建节点
            }
            node = node.nexts.get(path); // node 移动到路径另一端的节点
            node.pass++; // 经过的节点 pass + 1
        }
        // key 的路径已插入完成，将值 val 存入节点
        node.val = val;
        node.end++; // 最终节点 end + 1
    }

    // 搜索 key，如果存在返回对应节点，否则返回 null
    public TrieNode<V> getNode(String key) {
        TrieNode<V> node = root;
        // 从根节点开始搜索 key
        int path = 0;
        for (int i = 0; i < key.length(); i++) {
            path = key.charAt(i); // 确定路径
            if (!node.nexts.containsKey(path)){ // 路径下方节点不存在
                // 无法向下搜索
                return null;
            }
            // 向下搜索
            node = node.nexts.get(path);
        }
        return node;
    }

    // 搜索 key 对应的值，不存在则返回 null
//    public V get(String key) {
//
//    }
}
