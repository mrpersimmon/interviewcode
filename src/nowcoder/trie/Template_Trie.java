package nowcoder.trie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Template_Trie {
    public static void main(String[] args) {
        TrieMap<Integer> map = new TrieMap<>();
        map.put("them", 1);
        map.put("zip", 2);
        map.put("team",3);
        map.put("the", 4);
        map.put("app", 5);
        map.put("that", 6);
//        System.out.println(map.shortestPrefixOf("themxyc"));
//        System.out.println(map.longestPrefixOf("themxyz"));
//        List<String> th = map.keysWithPrefix("t");
//        for (String s : th) {
//            System.out.print(s + " ");
//        }
        List<String> pattern = map.keysWithPattern("t.a.");
        for (String s : pattern) {
            System.out.println(s);
        }
        System.out.println(map.hasKeyWithPattern(".ip"));
        System.out.println(map.hasKeyWithPattern(".i"));
    }
}


class TrieMap<V> {
    private static class TrieNode<V> {
        V val;
        int pass; // 表示有多少个单词共用这个节点
        int end; // 表示有多少个单词以这个节点结尾
        public HashMap<Integer, TrieNode<V>> nexts; // <字符路径(字符 ASCII 码值)，下级节点>

        public TrieNode() {
            V val = null;
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }
    // 当前存在 Map 中的键值对个数
    private int size = 0;

    // Trie 树的根节点
    private TrieNode<V> root = null;

    public TrieMap() {
        root = new TrieNode<>();
    }

    // 返回 TrieMap 中键值对的个数
    public int size() {
        return size;
    }

    /*********** 增/改 ************/

    // 向 map 中添加或修改键值对
    public void put(String key, V val) {
        if (!containsKey(key)) {
            size++;
        }
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
            node = node.nexts.get(path); // node 向下移动
            node.pass++; // 经过的节点 pass + 1
        }
        // key 的路径已插入完成，将值 val 存入节点
        node.val = val;
        node.end++; // 最终节点 end + 1
    }

    /*********** 查 ************/

    // 搜索 key，如果存在返回「对应节点」，否则返回 null
    private TrieNode<V> getNode(TrieNode<V> node, String key) {
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

    // 搜索 key 对应的值(val)，不存在则返回 null
    public V get(String key) {
        TrieNode<V> x = getNode(root, key);
        // x 为空或 x 的 val 字段为空都说明 key 没有对应的值
        if (x == null || x.val == null) {
            return null;
        }
        return x.val;
    }

    // 判断 key 是否存在于 Map 中
    public boolean containsKey(String key) {
        return get(key) != null;
    }

    // 判断是否存在前缀为 prefix 的键
    public boolean hasKeyWithPrefix(String prefix) {
        // 只要能找到 prefix 对应的节点，就是存在前缀
        return getNode(root, prefix) != null;
    }

    // 在所有「键」中寻找 query 的最短前缀
    public String shortestPrefixOf(TrieNode<V> node, String query) {
        int path = 0;
        for (int i = 0; i < query.length(); i++) {
            path = query.charAt(i);
            if (node == null) {
                // 无法向下搜索
                return "";
            }
            if (node.val != null) {
                // 找到一个『键』是 query 的前缀
                return query.substring(0, i);
            }
            node = node.nexts.get(path);
        }
        if (node != null && node.val != null) {
            // 如果 query 本身就是一个键
            return query;
        }
        return "";
    }

    // 在所有「键」中寻找 query 的最长前缀
    public String longestPrefixOf(TrieNode<V> node, String query) {
        // 记录前缀的最大长度
        int max_len = 0;

        int path = 0;
        for (int i = 0; i < query.length(); i++) {
            path = query.charAt(i);
            if (node == null) {
                // 无法向下搜索
                break;
            }
            if (node.val != null) {
                max_len = i; // 找到一个键是 query 的前缀，更新前缀的最大长度
            }
            node = node.nexts.get(path); // 向下搜索
        }
        if (node != null && node.val != null) {
            return query; // 如果 query 本身就是一个键，那么这就是最长前缀
        }
        return query.substring(0, max_len);
    }



    // ------------- 搜索所有前缀为 prefix 的键 -------------
    public List<String> keysWithPrefix(String prefix) {
        // 先利用 getNode 在 Trie 树中找到 prefix 对应的节点 x
        // 使用多叉树的遍历算法，遍历以 x 为根的这棵 Trie 树，找到所有的键值对
        List<String> res = new LinkedList<>();
        // 找到匹配 prefix 在 Trie 树中的那个节点
        TrieNode<V> x = getNode(root, prefix);
        if (x == null) // 不存在 prefix
            return res;
        // DFS 遍历以 x 为根的这棵 Trie 树
        traverse(x, new StringBuffer(prefix), res);
        return res;
    }

    // 遍历以 node 节点为根的 Trie 树，找到所有「键」
    private void traverse(TrieNode<V> node, StringBuffer path, List<String> res) {
        if (node == null) {
            // 到达 Trie 树底部叶子节点
            return;
        }
        if (node.val != null) {
            // 找到一个 key，添加到结果列表中
            res.add(path.toString());
        }
        // 回溯算法遍历框架
        for (int c : node.nexts.keySet()) {
            // 做选择
            path.append((char)c);
            traverse(node.nexts.get(c), path, res);
            // 撤销选择
            path.deleteCharAt(path.length() - 1);
        }
    }

    // ----------------------------------------------------

    // 通配符 . 匹配任意字符，搜索所有匹配的键
    // keysWithPattern("t.a.") -> ["team", "that"]
    public List<String> keysWithPattern(String pattern) {
        List<String> res = new LinkedList<>();
        traverse(root, new StringBuffer(), pattern, 0, res);
        return res;
    }

    // 遍历函数，尝试在「以 node 为根的 Trie 树中」匹配 pattern[i..]
    private void traverse(TrieNode<V> node, StringBuffer path, String pattern, int i, List<String> res) {
        if (node == null) {
            // 树枝不存在，即匹配失败
            return;
        }
        if (i == pattern.length()) {
            // pattern 匹配完成
            if (node.val != null) {
                // 如果这个节点存储着 val，则找到一个匹配的键
                res.add(path.toString());
            }
            return;
        }
        char c = pattern.charAt(i);
        if (c == '.') {
            // pattern[i] 是通配符，可以变化成任意字符
            // 多叉树（回溯算法）遍历框架
            for (int j : node.nexts.keySet()) {
                path.append((char)j);
                traverse(node.nexts.get(j), path, pattern, i + 1, res);
                path.deleteCharAt(path.length() - 1);
            }
        } else {
            // pattern[i] 是普通字符 c
            path.append(c);
            traverse(node.nexts.get((int)c), path, pattern, i + 1, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    // ----------------------------------------------------

    // 通配符 . 匹配任意字符，判断是否存在前缀为 prefix 的键
    // hasKeyWithPattern(".ip") -> true
    // hasKeyWithPattern(".i") -> false
    public boolean hasKeyWithPattern(String pattern) {
        // 从 root 节点开始匹配 pattern[0..]
        return hasKeyWithPattern(root, pattern, 0);
    }

    // 从 node 节点开始匹配 pattern[i..]，返回是否成功匹配
    private boolean hasKeyWithPattern(TrieNode<V> node, String pattern, int i) {
        if (node == null) {
            // 树枝不存在，即匹配失败
            return false;
        }
        if (i == pattern.length()) {
            // 模式串走到头了，看看匹配到的是否是一个键
            return node.val != null;
        }
        char c = pattern.charAt(i);
        // 没有遇到通配符
        if (c != '.') {
            // 从 node.nexts.get(c) 节点开始匹配 pattern[i+1..]
            return hasKeyWithPattern(node.nexts.get((int) c), pattern, i + 1);
        }
        // 遇到通配符
        for (int j : node.nexts.keySet()) {
            if (hasKeyWithPattern(node.nexts.get(j), pattern, i + 1)) {
                return true;
            }
        }
        // 都没有匹配
        return false;
    }

}
