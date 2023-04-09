package nowcoder.binarytree;

import java.util.ArrayList;

public class BM38_LowestCommonAncestor {

    public int lowestCommonAncestor(TreeNode root, int o1, int o2) {
        ArrayList<Integer> path1 = new ArrayList<>();
        ArrayList<Integer> path2 = new ArrayList<>();

        dfs(root, path1, o1);
        flag = false; // 重置 flag
        dfs(root, path2, o2);

        int ans = 0;
        for (int i = 0; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i).equals(path2.get(i))) {
                ans = path1.get(i);
            } else
                break;
        }
        return ans;
    }

    public boolean flag = false; // 判断是否找到了目标节点
    public void dfs(TreeNode node, ArrayList<Integer> path, int target) {
        if (flag || node == null) { // basecase
            return;
        }
        path.add(node.val); // 不满足 basecase，添加到路径中
        if (node.val == target) { // 找到了目标节点
            flag = true;
            return;
        }
        // 没找到目标节点
        dfs(node.left, path, target);
        dfs(node.right, path, target);
        if (flag) // 遍历完了全部，找到了直接返回
            return;
        path.remove(path.size() - 1); // 遍历完了全部，没找到，回溯，删除最后插入的节点。
    }

}

