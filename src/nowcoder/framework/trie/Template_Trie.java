package nowcoder.framework.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Template_Trie {
//    public static void main(String[] args) {
//        TrieMap<Integer> map = new TrieMap<>();
//        map.put("them", 1);
//        map.put("zip", 2);
//        map.put("team",3);
//        map.put("the", 4);
//        map.put("app", 5);
//        map.put("that", 6);
////        System.out.println(map.countWordsEqualTo("them"));
////        System.out.println(map.countWordsStartingWith("th"));
////        System.out.println(map.shortestPrefixOf("themxyc"));
////        System.out.println(map.longestPrefixOf("themxyz"));
////        List<String> th = map.keysWithPrefix("th");
////        for (String s : th) {
////            System.out.print(s + " ");
////        }
////        List<String> pattern = map.keysWithPattern("t.a.");
////        for (String s : pattern) {
////            System.out.println(s);
////        }
//        System.out.println(map.hasKeyWithPattern(".ip"));
//        System.out.println(map.hasKeyWithPattern(".i"));
//
////        map.remove("that");
////        System.out.println(map.get("the"));
//    }
    public static void main(String[] args) {
        String[][] operators = {{"1", "qwer"}, {"1","qwe"}, {"3", "qwer"}, {"4","q"}, {"2","qwer"}, {"3","qwer"}, {"4","q"}};
        String[] ans = trieU(operators);
        for (String a : ans) {
            System.out.println(a);
        }

    }
    public static String[] trieU (String[][] operators) {
        // write code here
        List<String> ans = new ArrayList<>();
        Trie trie = new Trie();
        for (String[] op : operators) {
            if (op[0].equals("1")) {
                trie.insert(op[1]);
            } else if (op[0].equals("2")) {
                trie.delete(op[1]);
            } else if (op[0].equals("3")) {
                ans.add(trie.search(op[1]) ? "YES" : "NO");
            } else if (op[0].equals("4")) {
                ans.add(String.valueOf(trie.prefixNumber(op[1])));
            }
        }
        return ans.toArray(new String[0]);
    }
}

class TrieMap<V> {
    private static class TrieNode<V> {
        V val;
        int pass; // 表示有多少个单词共用这个节点
        int end; // 表示有多少个单词以这个节点结尾
        HashMap<Character, TrieNode<V>> children; // <字符路径(字符 ASCII 码值)，下级节点>

        public TrieNode() {
            val = null;
            pass = 0;
            end = 0;
            children = new HashMap<>();
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
        if (key == null) {
            return;
        }
        if (!containsKey(key)) {
            size++;
        }
        TrieNode<V> node = root; // 从根节点开始
        node.pass++; // 根节点 pass++
        for (int i = 0; i < key.length(); i++) { // 遍历字符串
            char path = key.charAt(i); // 确定字符路径
            if (!node.children.containsKey(path)) { // 如果路径下方节点不存在
                node.children.put(path, new TrieNode<>()); // 新建节点
            }
            node = node.children.get(path); // 节点存在，node 向下移动
            node.pass++; // 经过的节点 pass++
        }
        // key 的路径已插入完成，将值 val 存入节点
        node.val = val;
        node.end++; // 最后的节点 end++
    }

    /*********** 删 ************/
    // 在 map 中删除 key
    public void remove(String key) {
        if (key == null) {
            return;
        }
        if (!containsKey(key)) { // 判断 key 是否在 Trie 树中
            return;
        }
        size--; // 键值对数量 - 1
        TrieNode<V> node = root; // 从根节点开始
        node.pass--; // 根节点 pass - 1
        for (int i = 0; i < key.length(); i++) {
            char path = key.charAt(i); // 确定字符路径
            if (--node.children.get(path).pass == 0) { // 如果路径下面的节点 pass - 1 == 0
                node.children.remove(path); // 移除该节点，后续节点会被 gc 掉
                return; // 直接返回
            }
            node = node.children.get(path); // node 向下移动
        }
        node.end--; // 最终节点 end - 1
    }



    /*********** 查 ************/

    // 从 node 节点搜索 key，如果存在返回「对应节点」，否则返回 null
    private TrieNode<V> getNode(TrieNode<V> node, String key) {
        TrieNode<V> p = node; // 从节点 node 开始搜索 key
        for (int i = 0; i < key.length(); i++) {
//            方法 1：
//            char path = key.charAt(i); // 确定路径
//            if (!p.children.containsKey(path)){ // 路径下方节点不存在
//                // 无法向下搜索
//                return null;
//            }
//            p = p.children.get(path);// 向下搜索
//            方法 2：
            if (p == null) {
                return null; // 无法向下搜索
            }
            char path = key.charAt(i); // 确定路径
            p = p.children.get(path);// 向下搜索
        }
        return p;
    }

    // 搜索 key 对应的值(val)，不存在则返回 null
    // get("the") -> 4
    // get("tha") -> null
    public V get(String key) {
        TrieNode<V> x = getNode(root, key);
        // x 为空或 x 的 val 字段为空都说明 key 没有对应的值
        if (x == null || x.val == null) {
            return null;
        }
        return x.val;
    }

    // 判断 key 是否存在于 Map 中
    // containsKey("tea") -> false
    // containsKey("team") -> true
    public boolean containsKey(String key) {
        return get(key) != null;
    }

    // 判断是否存在前缀为 prefix 的键
    // hasKeyWithPrefix("tha") -> true
    // hasKeyWithPrefix("apple") -> false
    public boolean hasKeyWithPrefix(String prefix) {
        // 只要能找到 prefix 对应的节点，就是存在前缀
        return getNode(root, prefix) != null;
    }

    // 在 Map 的所有键中搜索 query 的最短前缀
    // shortestPrefixOf("themxyz") -> "the"
    // 在第一次遇到存有 val 的节点的时候返回即可
    // for 循环之后还要额外检查最后一个节点。
    public String shortestPrefixOf(String query) {
        TrieNode<V> p = root;
        for (int i = 0; i < query.length(); i++) {
            if (p == null) {
                return ""; // 无法向下搜索
            }
            if (p.val != null) {
                return query.substring(0, i); // 找到一个『键』是 query 的前缀
            }
            char path = query.charAt(i);
            p = p.children.get(path);
        }
        if (p != null && p.val != null) {
            return query; // 如果 query 本身就是一个键
        }
        return "";
    }

    // 在 Map 的所有键中搜索 query 的最长前缀
    // longestPrefixOf("themxyz") -> "them"
    public String longestPrefixOf(String query) {
        int maxLen = 0; // 记录前缀的最大长度
        TrieNode<V> p = root;
        for (int i = 0; i < query.length(); i++) {
            if (p == null) {
                break; // 无法向下搜索
            }
            if (p.val != null) {
                maxLen = i; // 找到一个键是 query 的前缀，更新前缀的最大长度
            }
            char path = query.charAt(i); // 向下搜索
            p = p.children.get(path);
        }
        if (p != null && p.val != null) {
            return query; // 如果 query 本身就是一个键，那么这就是最长前缀
        }
        return query.substring(0, maxLen);
    }

    // ------------- 搜索所有前缀为 prefix 的键 -------------
    // keysWithPrefix("th") -> ["that", "the", "them"]
    // 先利用 getNode 在 Trie 树中找到 prefix 对应的节点 x
    // 使用多叉树的遍历算法，遍历以 x 为根的这棵 Trie 树，找到所有的键值对
    public List<String> keysWithPrefix(String prefix) {
        List<String> res = new LinkedList<>();
        // 找到匹配 prefix 在 Trie 树中的那个节点
        TrieNode<V> p = getNode(root, prefix);
        if (p == null) // 不存在 prefix
            return res;
        // 回溯遍历以 x 为根的这棵 Trie 树
        traverse(p, new StringBuffer(prefix), res);
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
        // c 选择，node.children.keySet() 选择列表
        for (char c : node.children.keySet()) {
            // 做选择
            path.append(c);
            traverse(node.children.get(c), path, res);
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
            for (char j : node.children.keySet()) {
                path.append(j); // 做选择
                traverse(node.children.get(j), path, pattern, i + 1, res);
                path.deleteCharAt(path.length() - 1); // 撤销选择
            }
        } else {
            // pattern[i] 是普通字符 c
            path.append(c); // 做选择
            traverse(node.children.get(c), path, pattern, i + 1, res);
            path.deleteCharAt(path.length() - 1); // 撤销选择
        }
    }

    // ----------------------------------------------------

    // 通配符 . 匹配任意字符，判断是否存在匹配的键
    // hasKeyWithPattern(".ip") -> true
    // hasKeyWithPattern(".i") -> false
    public boolean hasKeyWithPattern(String pattern) {
        // 从 root 节点开始匹配 pattern[0..]
        return traverse(root, pattern, 0);
    }

    // 从 node 节点开始匹配 pattern[i..]，返回是否成功匹配
    private boolean traverse(TrieNode<V> node, String pattern, int i) {
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
            // 从 node.children.get(c) 节点开始匹配 pattern[i+1..]
            return traverse(node.children.get(c), pattern, i + 1);
        }
        // 遇到通配符
        for (char j : node.children.keySet()) {
            // pattern[j] 可以变化成任意字符，尝试所有可能，只要遇到一个匹配成功就返回
            if (traverse(node.children.get(j), pattern, i + 1)) {
                return true;
            }
        }
        // 都没有匹配
        return false;
    }

    // 查询 TrieMap 中指定 key 出现的次数
    public int countWordsEqualTo(String key) {
        if (key == null) {
            return 0;
        }
        if (!containsKey(key)) {
            return 0;
        }
        TrieNode<V> node = root; // 从根节点开始
        for (int i = 0; i < key.length(); i++) { // 遍历字符串
            char path = key.charAt(i); // 确定字符路径
            if (!node.children.containsKey(path)) { // 如果路径下方不存在节点，返回 0
                return 0;
            }
            node = node.children.get(path); // 节点存在，向下移动
        }
        return node.end; // 返回 end 值
    }

    // 查询 TrieMap 中以 prefix 为前缀的 key 的个数
    public int countWordsStartingWith(String prefix) {
        if (prefix == null)
            return 0;
        TrieNode<V> node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char path = prefix.charAt(i);
            if (!node.children.containsKey(path)) {
                return 0;
            }
            node = node.children.get(path);
        }
        return node.pass; // 返回 pass 值
    }
}

class TrieSet<V> {
    // 底层用一个 TrieMap，键就是 TrieSet，值仅仅起到占位的作用
    // 值的类型可以随便设置，参考 Java 标准库设置为 Object
    private final TrieMap<Object> map = new TrieMap<>();

    public void add(String key) {
        map.put(key, new Object());
    }

    public void remove(String key) {
        map.remove(key);
    }

    public boolean contains(String key) {
        return map.containsKey(key);
    }

    public String shortestPrefixOf(String query) {
        return map.shortestPrefixOf(query);
    }

    public String longestPrefixOf(String query) {
        return map.longestPrefixOf(query);
    }

    public List<String> keysWithPrefix(String prefix) {
        return map.keysWithPrefix(prefix);
    }

    public boolean hasKeyWithPrefix(String prefix) {
        return map.hasKeyWithPrefix(prefix);
    }

    public List<String> keysWithPattern(String pattern) {
        return map.keysWithPattern(pattern);
    }

    public boolean hasKeyWithPattern(String pattern) {
        return map.hasKeyWithPattern(pattern);
    }

    public int size() {
        return map.size();
    }

    public int countWordEqualTo(String key) {
        return map.countWordsEqualTo(key);
    }

    public int countWordsStartingWith(String prefix) {
        return map.countWordsStartingWith(prefix);
    }
}


class Trie {
    TrieSet set = new TrieSet();

    public void insert(String word) {
        set.add(word);
    }

    public void delete(String word) {
        set.remove(word);
    }

    public boolean search(String word) {
        return set.contains(word);
    }

    public int prefixNumber(String pre) {
        return set.countWordsStartingWith(pre);
    }
}

