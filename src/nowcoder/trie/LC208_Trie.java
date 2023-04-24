package nowcoder.trie;

import java.util.HashMap;

// https://leetcode.cn/problems/implement-trie-prefix-tree/
class LC208_Trie {
    private final TrieMap_lc208<Object> map;
    public LC208_Trie() {
        map = new TrieMap_lc208<>();
    }

    public void insert(String word) {
        map.put(word, new Object());
    }

    public boolean search(String word) {
        return map.containsKey(word);
    }

    public boolean startsWith(String prefix) {
        return map.hasKeyWithPrefix(prefix);
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

class TrieMap_lc208<V> {
    private static class TrieNode<V> {
        int pass;
        int end;
        V val;
        HashMap<Character, TrieNode<V>> children;

        public TrieNode() {
            pass = 0;
            end = 0;
            val = null;
            children = new HashMap<>();
        }
    }

    private int size = 0;

    private TrieNode<V> root = null;

    public TrieMap_lc208() {
        root = new TrieNode<>();
    }

    public TrieNode<V> getNode(TrieNode<V> node, String key) {
        if (key == null) {
            return null;
        }
        TrieNode<V> p = node;
        for (int i = 0; i < key.length(); i++) {
            if (p == null) {
                return null;
            }
            char path = key.charAt(i);
            p = p.children.get(path);
        }
        return p;
    }

    public V get(String key) {
        if (key == null) {
            return null;
        }
        TrieNode<V> p = getNode(root, key);
        if (p == null || p.val == null) {
            return null;
        }
        return p.val;
    }

    public boolean containsKey(String key) {
        return get(key) != null;
    }


    // 修改或更新
    public void put(String key, V val) {
        if (key == null) {
            return;
        }
        if (!containsKey(key)) {
            size++;
        }
        TrieNode<V> node = root;
        node.pass++;
        for (int i = 0; i < key.length(); i++) {
            char path = key.charAt(i);
            if (!node.children.containsKey(path)) {
                node.children.put(path, new TrieNode<>());
            }
            node = node.children.get(path);
            node.pass++;
        }
        node.val = val;
        node.end++;
    }

    public boolean hasKeyWithPrefix(String prefix) {
        return getNode(root, prefix) != null;
    }
}