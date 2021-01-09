//给定一个二叉树，返回所有从根节点到叶子节点的路径。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//
// 输入:
//
//   1
// /   \
//2     3
// \
//  5
//
//输出: ["1->2->5", "1->3"]
//
//解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3 
// Related Topics 树 深度优先搜索


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Java：二叉树的所有路径
public class P257BinaryTreePaths{
    public static void main(String[] args) {
        Solution solution = new P257BinaryTreePaths().new Solution();
        TreeNode _1 = new TreeNode(1);
        TreeNode _2 = new TreeNode(2);
        TreeNode _3 = new TreeNode(3);
        _1.left = _2;
        _1.right = _3;
        _2.right = new TreeNode(5);
        List<String> list = solution.binaryTreePaths(_1);
        System.out.println(list);
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> s = new ArrayList<>();
        LinkedList<Integer> vals = new LinkedList<>();
        travel(root,s,vals);
        return s;
    }

    private void travel(TreeNode root,List<String> s, LinkedList<Integer> vals) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            vals.addLast(root.val);
            s.add(getStr(vals));
            return;
        }
        vals.addLast(root.val);
        if (root.left != null) {
            travel(root.left,s,vals);
            vals.removeLast();
        }
        if (root.right!= null) {
            travel(root.right,s,vals);
            vals.removeLast();
        }
    }

    private String getStr(LinkedList<Integer> vals) {
        if (vals.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder().append(vals.get(0));
        for (int i = 1; i < vals.size(); i++) {
            builder.append("->").append(vals.get(i));
        }
        return builder.toString();
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}
