package nowcoder.trie;


import java.util.HashMap;


class LC211_WordDictionary {
    private final TrieMap_lc211<Object> map;
    public LC211_WordDictionary() {
        map = new TrieMap_lc211<>();
    }

    public void addWord(String word) {
        map.put(word, new Object());
    }

    public boolean search(String word) {
        return map.hasKeyWithPattern(word);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

class TrieMap_lc211<V> {
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
    public TrieMap_lc211() {
        root = new TrieNode();
    }

    private TrieNode<V> getNode(TrieNode<V> node, String key) {
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




    public boolean hasKeyWithPattern(String pattern) {
        if (pattern == null) {
            return false;
        }
        return traverse(root, pattern, 0);
    }

    private boolean traverse(TrieNode<V> node, String pattern, int i) {
        if (node == null) {
            return false;
        }
        if (i == pattern.length()) {
            return node.val != null;
        }
        char path = pattern.charAt(i);
        if (path != '.') {
            return traverse(node.children.get(path), pattern, i + 1);
        }
        for (char c : node.children.keySet()) {
            if (traverse(node.children.get(c), pattern, i + 1)) {
                return true;
            }
        }
        return false;
    }

}