//给定一个二叉树，计算 整个树 的坡度 。 
//
// 一个树的 节点的坡度 定义即为，该节点左子树的节点之和和右子树节点之和的 差的绝对值 。如果没有左子树的话，左子树的节点之和为 0 ；没有右子树的话也是一
//样。空结点的坡度是 0 。 
//
// 整个树 的坡度就是其所有节点的坡度之和。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3]
//输出：1
//解释：
//节点 2 的坡度：|0-0| = 0（没有子节点）
//节点 3 的坡度：|0-0| = 0（没有子节点）
//节点 1 的坡度：|2-3| = 1（左子树就是左子节点，所以和是 2 ；右子树就是右子节点，所以和是 3 ）
//坡度总和：0 + 0 + 1 = 1
// 
//
// 示例 2： 
//
// 
//输入：root = [4,2,9,3,5,null,7]
//输出：15
//解释：
//节点 3 的坡度：|0-0| = 0（没有子节点）
//节点 5 的坡度：|0-0| = 0（没有子节点）
//节点 7 的坡度：|0-0| = 0（没有子节点）
//节点 2 的坡度：|3-5| = 2（左子树就是左子节点，所以和是 3 ；右子树就是右子节点，所以和是 5 ）
//节点 9 的坡度：|0-7| = 7（没有左子树，所以和是 0 ；右子树正好是右子节点，所以和是 7 ）
//节点 4 的坡度：|(3+5+2)-(9+7)| = |10-16| = 6（左子树值为 3、5 和 2 ，和是 10 ；右子树值为 9 和 7 ，和是 1
//6 ）
//坡度总和：0 + 0 + 0 + 2 + 7 + 6 = 15
// 
//
// 示例 3： 
//
// 
//输入：root = [21,7,14,1,1,2,2,3,3]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目的范围在 [0, 104] 内 
// -1000 <= Node1.val <= 1000
// 
// Related Topics 树 深度优先搜索 递归


package leetcode.editor.cn;
//Java：二叉树的坡度
public class P563BinaryTreeTilt{
    public static void main(String[] args) {
        Solution solution = new P563BinaryTreeTilt().new Solution();
        TreeNode tree = TreeNode.getTree(21,7,14,1,1,2,2,3,3);
        int tilt = solution.findTilt(tree);
        System.out.println(tilt);
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
    public int findTilt(TreeNode root) {
        int[] a = new int[1];
        findTilt(root,a);
        return a[0];
    }


    public int findTilt(TreeNode root, int[] a) {
        if (root == null) {
            return 0;
        }
        if (root.right == null && root.left == null)
            return root.val;

        int leftSum = root.left != null ? findTilt(root.left,a) : 0;
        int rightSum = root.right != null ? findTilt(root.right,a) : 0;
        int i = leftSum - rightSum;
        i = i > 0 ? i : -i;
        a[0] += i;
        return leftSum + rightSum + root.val;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
