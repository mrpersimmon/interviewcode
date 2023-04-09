import java.util.Arrays;
import java.util.HashMap;

public class BM40_reConstructBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] vin) {
        // base case：每次遍历，pre vin 都不能为空
        if (pre == null || pre.length == 0 || vin == null || vin.length == 0) {
            return null;
        }
        // 前序遍历的第一个元素，就是树的根节点
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < vin.length; i++) {
            // 找到 中序遍历 中的 前序遍历 的第一个元素
            if (vin[i] == pre[0]) {
                // Arrays.copyOfRange(arr, start, dest) 含头不含尾
                // 构建左子树
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(vin, 0, i));
                // 构建右子树
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(vin, i + 1, vin.length));
                break;
            }
        }
        return root;
    }


    public TreeNode reConstructBinaryTree2(int[] pre, int[] vin) {
        if (pre == null || pre.length == 0 || vin == null || vin.length == 0) {
            return null;
        }
        HashMap<Integer, Integer> inMap = new HashMap<>(); // 记录中序遍历元素及对应的索引
        for (int i = 0; i < vin.length; i++) {
            inMap.put(vin[i], i);
        }
        return buildTree(inMap, pre, 0, pre.length - 1, vin, 0, vin.length - 1);
    }

    private TreeNode buildTree(HashMap<Integer, Integer> inMap, int[] pre, int preStart, int preEnd, int[] vin, int vinStart, int vinEnd) {
        if (vinStart > vinEnd) {
            return null;
        }
        int rootVal = pre[preStart]; // 当前遍历中的根节点值
        int index = inMap.get(rootVal); // 中序遍历中根节点的索引
        int leftSize = index - vinStart; // 左子树节点个数
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(inMap, pre, preStart + 1, preStart + leftSize, vin, vinStart, index - 1);
        root.right = buildTree(inMap, pre, preStart + leftSize + 1, preEnd, vin, index + 1, vinEnd);
        return root;
    }
}
