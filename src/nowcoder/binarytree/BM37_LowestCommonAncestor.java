package nowcoder.binarytree;

import java.util.ArrayList;

public class BM37_LowestCommonAncestor {
    /**
     * 题目描述：
     * 给定一棵二叉搜索树，找到该树中两个指定节点的最近公共祖先。
     *
     * 最近公共祖先定义：对于有根树 T 的两个节点 p、q，最近公共祖先 LCA(T, p, q) 表示一个节点 x，满足 x 是 p 和 q 的祖先且 x 的深度尽可能大。
     * 在这里，一个节点也可以是它自己的祖先。
     *
     * 二叉搜索树是若它的左子树不空，则左子树上所有节点的值均小于它的根节点的值；若它的右子树不为空，则右子树上所有节点的值均大于它的根节点的值。
     *
     * 所有节点的值都是唯一的。
     */
    /**
     * 提炼信息：
     * 1. 最近公共祖先的定义；
     * 2. 一个节点也是它自己的祖先；
     * 3. 二叉搜索树的定义；
     * 4. 所有节点的值都是唯一的，因此，可以通过节点值直接比较；
     * 5. p、q 为不同节点且存在于给定的二叉搜索树中。
     */

    // 返回最近公共祖先的节点值
    public int lowestCommonAncestor(TreeNode root, int p, int q) {
        // 找到 p q 两个节点：求根节点到 p q 两个节点的路径
        ArrayList<Integer> path_p = getPath(root, p);
        ArrayList<Integer> path_q = getPath(root, q);
        int ans = 0;
        // 比较两个路径，最后一个相同的节点就是最近公共祖先
        for (int i = 0; i < path_p.size() && i < path_q.size(); i++) {
            int x = path_p.get(i);
            int y = path_q.get(i);
            // 最后一个相同节点，就是最近公共祖先
            if (x == y) {
                ans = x;
            } else {
                break;
            }
        }
        return ans;
    }

    // 求 根节点 到 目标节点 的路径
    public ArrayList<Integer> getPath(TreeNode root, int target) {
        ArrayList<Integer> path = new ArrayList<>();
        TreeNode node = root;
        while (node.val != target) { // 所有节点值都是唯一的，直接通过节点值比较
            path.add(node.val);
            if (target < node.val) { // 目标值小于节点值，一定在左边
                node = node.left;
            } else { // 大于节点值，一定在右边
                node = node.right;
            }
        }
        // 找到了目标节点，加入到路径中
        path.add(node.val);
        return path;
    }

}

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
}
