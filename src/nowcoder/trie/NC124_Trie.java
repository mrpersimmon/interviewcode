package nowcoder.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NC124_Trie {
    /**
     * @param operators string字符串二维数组 the ops
     * @return string字符串一维数组
     */
    public String[] trieU (String[][] operators) {
        // write code here
        List<String> ans = new ArrayList<>();
        Trie trie = new Trie();
        for (String[] opera : operators) {
            if (opera[0].equals("1")) {
                trie.insert(opera[1]);
            } else if (opera[0].equals("2")) {
                trie.delete(opera[1]);
            } else if (opera[0].equals("3")) {
                ans.add(trie.search(opera[1]) ? "YES" : "NO");
            } else if (opera[0].equals("4")) {
                ans.add(String.valueOf(trie.prefixNumber(opera[1])));
            }
        }
        return ans.toArray(new String[0]);
    }
}



class Trie {
    private final TrieMap<Object> trieMap = new TrieMap<>();

    public void insert(String word) {
        trieMap.put(word, new Object());
    }

    public void delete(String word) {
        trieMap.remove(word);
    }

    public boolean search(String word) {
        return trieMap.containsKey(word);
    }

    public int prefixNumber(String pre) {
        return trieMap.countWordsStartingWith(pre);
    }
}

class TrieMap<V> {
    private static class TrieNode<V> {
        private V val;
        private int pass;
        private int end;
        private HashMap<Character, TrieNode<V>> children;
        public TrieNode() {
            val = null;
            pass = 0;
            end = 0;
            children = new HashMap<>();
        }
    }

    private int size = 0;

    private TrieNode<V> root = null;

    public TrieMap() {
        root = new TrieNode<>();
    }

    private TrieNode<V> getNode(String key, TrieNode<V> node) {
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
        TrieNode<V> p = getNode(key, root);
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

    public void remove(String key) {
        if (key == null) {
            return;
        }
        if (!containsKey(key)) {
            return;
        }
        size--;
        TrieNode<V> node = root;
        node.pass--;
        for (int i = 0; i < key.length(); i++) {
            char path = key.charAt(i);
            if (--node.children.get(path).pass == 0) {
                node.children.remove(path);
                return;
            }
            node = node.children.get(path);
        }
        node.end--;
    }

    public int countWordsStartingWith(String prefix) {
        if (prefix == null) {
            return 0;
        }
        TrieNode<V> node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char path = prefix.charAt(i);
            if(!node.children.containsKey(path)) {
                return 0;
            }
            node = node.children.get(path);
        }
        return node.pass;
    }

    public int size() {
        return size;
    }
}