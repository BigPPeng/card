//给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看
//做它自身的一棵子树。 
//
// 示例 1: 
//给定的树 s: 
//
// 
//     3
//    / \
//   4   5
//  / \
// 1   2
// 
//
// 给定的树 t： 
//
// 
//   4 
//  / \
// 1   2
// 
//
// 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。 
//
// 示例 2: 
//给定的树 s： 
//
// 
//     3
//    / \
//   4   5
//  / \
// 1   2
//    /
//   0
// 
//
// 给定的树 t： 
//
// 
//   4
//  / \
// 1   2
// 
//
// 返回 false。 
// Related Topics 树


package leetcode.editor.cn;
//Java：另一个树的子树
public class P572SubtreeOfAnotherTree{
    public static void main(String[] args) {
        Solution solution = new P572SubtreeOfAnotherTree().new Solution();
        TreeNode tree = TreeNode.getTree(3,4,5,1,2,null,null,null,null,0);
        TreeNode tree1 = TreeNode.getTree(4,1,2);

//        System.out.println(solution.isSame(tree,tree1));

        System.out.println(solution.isSubtree(tree,tree1));
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (isSame(s,t))
            return true;
        if (s.left != null && isSubtree(s.left,t))
            return true;
        if (s.right != null && isSubtree(s.right,t))
            return true;
        return false;
    }


    public boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t != null)
            return false;
        if (s != null && t == null)
            return false;
        if (s == null && t == null)
            return true;
        if (t.val != s.val)
            return false;
        boolean leftSame = isSame(s.left,t.left);
        boolean rightSame = isSame(s.right,t.right);
        return leftSame && rightSame;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
