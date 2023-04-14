//package nowcoder.trie;
//
//import java.util.List;
//
//public class Template_Trie {
//    // TrieMap 和 TrieSet
//    // TrieMap<V> 的键必须是字符串类型，值的类型 V 可以随意
//
//
//}
//
//// TrieMap 类中一定需要记录 Trie 的根节点 root，以及 Trie 树中的所有节点数量用于实现 size() 方法
//
//class TrieMap<V> {
//    // ASCII 码个数
//    private static final int R = 256;
//    // 当前存在 Map 中的键值对个数
//    private int size = 0;
//
//    private static class TrieNode<V> {
//        V val = null;
//        TrieNode<V>[] children = new TrieNode[R];
//    }
//
//    // Trie 树的根节点
//    private TrieNode<V> root = null;
//
//    // 实现一个工具函数 getNode
//    // 从节点 node 开始搜索 key，如果存在返回对应节点，否则返回 null
//    private TrieNode<V> getNode(TrieNode<V> node, String key) {
//        TrieNode<V> p = node;
//        // 从节点 node 开始搜索 key
//        for (int i = 0; i < key.length(); i++) {
//            if (p == null)
//                // 无法向下搜索
//                return null;
//            // 向下搜索
//            char c = key.charAt(i);
//            p = p.children[c];
//        }
//        return p;
//    }
//
//
//    /***** 增、改 ******/
//
//    // 在 Map 中添加 key
//    public void put(String key, V val) {
//
//    }
//
//    /***** 删 *****/
//
//    // 删除键 key 以及对应的值
//    public void remove(String key) {
//    }
//
//    /***** 查 *****/
//
//    // 搜索 key 对应的值，不存在则返回 null
//    // get("the") -> 4
//    // get("tha") -> null
//    public V get(String key) {
//        // 从 root 开始搜索 key
//        TrieNode<V> x = getNode(root, key);
//        if (x == null || x.val == null) {
//            // x 为空或 x 的 val 字段为空都说明 key 没有对应的值
//            return null;
//        }
//        return x.val;
//    }
//
//    // 判断 key 是否存在在 Map 中
//    // containsKey("tea") -> false
//    // containsKey("team") -> true
//    // 就算 getNode(key) 的返回值 x 非空，也只能说字符串 key 是一个前缀；
//    // 除非 x.val 同时非空，才能判断键 key 存在。
//    public boolean containsKey(String key) {
//        return get(key) != null;
//    }
//
//    // 在 Map 的所有键中搜索 query 的最短前缀
//    // shortestPrefixOf("themxyz") -> "the"
//
//    // 其他想法：如果记录了 pass、end，第一个有 end 的就是最短前缀
//    public String shortestPrefixOf(String query) {
//        TrieNode<V> p = root;
//        // 从节点 node 开始搜索 key
//        for (int i = 0; i < query.length(); i++) {
//            if (p == null) {
//                // 无法向下搜索
//                return "";
//            }
//            if (p.val != null) {
//                // 找到一个键是 query 的前缀
//                return query.substring(0, i);
//            }
//            // 向下搜索
//            char c = query.charAt(i);
//            p = p.children[c];
//        }
//        // 如果 query 本身就是一个键
//        if (p != null && p.val != null) {
//            return query;
//        }
//        return "";
//    }
//
//    // 在 Map 的所有键中搜索 query 的最长前缀
//    // longestPrefixOf("themxyz") -> "them"
//    public String longestPrefixOf(String query) {
//        TrieNode<V> p = root;
//        // 记录前缀的最大长度
//        int max_len = 0;
//        // 从节点 node 开始搜索 key
//        for (int i = 0; i < query.length(); i++) {
//            if (p == null) {
//                // 无法向下搜索
//            }
//            if (p.val != null) {
//                // 找到
//            }
//        }
//    }
//
//    // 搜索所有前缀为 prefix 的键
//    // keysWithPrefix("th") -> ["that", "the", "them"]
//    public List<String> keysWithPrefix(String prefix);
//
//    // 判断是否存在前缀为 prefix 的键
//    // hasKeyWithPrefix("tha") -> true
//    // hasKeyWithPrefix("apple") -> false
//    public boolean hasKeyWithPrefix(String prefix) {
//        // 只要能找到 prefix 对应的节点，就是存在前缀
//        return getNode(root, prefix) != null;
//    }
//
//    // 通配符 . 匹配任意字符，搜索所有匹配的键
//    // keysWithPattern("t.a.") -> ["team", "that"]
//    public List<String> keysWithPattern(String pattern);
//
//    // 通配符 . 匹配任意字符，判断是否存在匹配的键
//    // hasKeyWithPattern(".ip") -> true
//    // hasKeyWithPattern(".i") -> false
//    public boolean hasKeyWithPattern(String pattern);
//
//    // 返回 Map 中键值对的数量
//    public int size() {
//        return size;
//    };
//}
