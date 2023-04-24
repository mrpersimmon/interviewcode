package nowcoder.trie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class LC677_MapSum {
    private TrieMap_lc677<Integer> map = null;
    public LC677_MapSum() {
        map = new TrieMap_lc677<>();
    }

    public void insert(String key, int val) {
        map.put(key, val);
    }

    public int sum(String prefix) {
        List<String> keys = map.keysWithPrefix(prefix);
        int res = 0;
        for (String key : keys) {
            res += map.get(key);
        }
        return res;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */

class TrieMap_lc677<V> {
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
    public TrieMap_lc677() {
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
        TrieNode<V> node = root;
        node.pass++;
        for (int i = 0; i < key.length(); i++) {
            char path = key.charAt(i);
            if (!node.children.containsKey(path)) {
                node.children.put(path, new TrieNode());
            }
            node = node.children.get(path);
            node.pass++;
        }
        node.val = val;
        node.end++;
    }

    public List<String> keysWithPrefix(String prefix) {
        List<String> ans = new LinkedList<>();
        if (prefix == null) {
            return ans;
        }
        TrieNode<V> p = getNode(root, prefix);
        traverse(p, new StringBuilder(prefix), ans);
        return ans;
    }

    public void traverse(TrieNode<V> node, StringBuilder path, List<String> ans) {
        if (node == null) {
            return;
        }
        if (node.val != null) {
            ans.add(path.toString());
        }
        for (char c : node.children.keySet()) {
            path.append(c);
            traverse(node.children.get(c), path, ans);
            path.deleteCharAt(path.length() - 1);
        }
    }

}