//给定一个二叉树，找出其最小深度。 
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。 
//
// 说明：叶子节点是指没有子节点的节点。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：root = [2,null,3,null,4,null,5,null,6]
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数的范围在 [0, 105] 内 
// -1000 <= Node1.val <= 1000
// 
// Related Topics 树 深度优先搜索 广度优先搜索


package leetcode.editor.cn;
//Java：二叉树的最小深度
public class P111MinimumDepthOfBinaryTree{
    public static void main(String[] args) {
        Solution solution = new P111MinimumDepthOfBinaryTree().new Solution();
        TreeNode _2 = new TreeNode(2);
        TreeNode _3 = new TreeNode(3);
        _2.left = _3;
        TreeNode _4 = new TreeNode(4);
        _3.left = _4;
        TreeNode _5 = new TreeNode(5);
        _4.left = _5;
        TreeNode _6 = new TreeNode(6);
        _5.left = _6;
        int i = solution.minDepth(_2);
        System.out.println(i);

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
    public int minDepth(TreeNode root) {
        return minDepthTr(root);
    }


    private int minDepthTr(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null)
            return 1;

        if (root.left == null)
            return minDepthTr(root.right) + 1;

        if (root.right == null)
            return minDepthTr(root.left) + 1;

        int left = minDepthTr(root.left);
        int right = minDepthTr(root.right);

        int min = Math.min(left,right);
        return min + 1;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}
