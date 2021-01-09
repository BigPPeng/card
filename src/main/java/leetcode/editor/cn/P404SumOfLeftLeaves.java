//计算给定二叉树的所有左叶子之和。 
//
// 示例： 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
//
//在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24 
//
// 
// Related Topics 树


package leetcode.editor.cn;

import java.util.List;

//Java：左叶子之和
public class P404SumOfLeftLeaves{
    public static void main(String[] args) {
        Solution solution = new P404SumOfLeftLeaves().new Solution();
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
    public int sumOfLeftLeaves(TreeNode root) {
        int[] a = new int[1];
        middle(root,a,false);
        return a[0];
    }

    public void middle(TreeNode root,int[] sum, boolean left) {
        if (root == null)
            return;
        if (root.left == null && root.right == null && left) {
            sum[0] += root.val;
            return;
        }

        if (root.left != null) {
            middle(root.left,sum,true);
        }
        //res.add(root);
        if (root.right != null) {
            middle(root.right,sum,false);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
