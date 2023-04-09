package nowcoder.framework.binarytree;

import nowcoder.linkedlist.ListNode;

public class test1 {
    public static void traverse(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        System.out.printf("节点 %s 在 %d 层", root.val, level);
        System.out.println();
        traverse(root.left, level + 1);
        traverse(root.right, level + 1);
    }

    public static int level = 1;

    public static void traverse(TreeNode root) {
        if (root == null)
            return;
        System.out.printf("节点 %s 在 %d 层", root.val, level);
        System.out.println();
        level++;
        traverse(root.left);
        traverse(root.right);
        level--;
    }

    public static int nodes = 0;

    public static void traverseNodes(TreeNode root) {
        if (root == null)
            return;
        nodes++;
        traverseNodes(root.left);
        traverseNodes(root.right);
        System.out.println();
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(0);
        a.left = new TreeNode(1);
        a.right = new TreeNode(2);
        a.left.left = new TreeNode(3);
//        traverse(a, 1);
        traverse(a);
    }
}
