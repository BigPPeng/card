//给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。 
//
// 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点
//。 
//
// 示例 1: 
//
// 
//输入: 
//	Tree 1                     Tree 2                  
//          1                         2                             
//         / \                       / \                            
//        3   2                     1   3                        
//       /                           \   \                      
//      5                             4   7                  
//输出: 
//合并后的树:
//	     3
//	    / \
//	   4   5
//	  / \   \ 
//	 5   4   7
// 
//
// 注意: 合并必须从两个树的根节点开始。 
// Related Topics 树


package leetcode.editor.cn;
//Java：合并二叉树
public class P617MergeTwoBinaryTrees{
    public static void main(String[] args) {
        Solution solution = new P617MergeTwoBinaryTrees().new Solution();
        TreeNode tree1 = TreeNode.getTree(1, 3, 2, 5);
        TreeNode tree2 = TreeNode.getTree(2,1,3,null,4,null,7);
        TreeNode treeNode = solution.mergeTrees(tree1, tree2);
        System.out.println(treeNode.val);
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
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return null;


        int t1_ = t1 != null ? t1.val : 0;
        int t2_ = t2 != null ? t2.val : 0;
        int sum = t1_ + t2_;
        TreeNode node = new TreeNode(sum);
        TreeNode t1_left = t1 == null ? null : t1.left;
        TreeNode t2_left = t2 == null ? null : t2.left;
        if (t1_left != null || t2_left != null) {
            node.left = mergeTrees(t1_left,t2_left);
        }

        TreeNode t1_right = t1 == null ? null : t1.right;
        TreeNode t2_right = t2 == null ? null : t2.right;
        if (t1_right != null || t2_right != null) {
            node.right = mergeTrees(t1_right,t2_right);
        }

        return node;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
