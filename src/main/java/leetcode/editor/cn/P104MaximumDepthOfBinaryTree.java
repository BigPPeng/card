//给定一个二叉树，找出其最大深度。 
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例： 
//给定二叉树 [3,9,20,null,null,15,7]， 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
// Related Topics 树 深度优先搜索


package leetcode.editor.cn;

import java.util.Stack;

//Java：二叉树的最大深度
public class P104MaximumDepthOfBinaryTree{
    public static void main(String[] args) {
        Solution solution = new P104MaximumDepthOfBinaryTree().new Solution();
        TreeNode tree = TreeNode.getTree(3,9,20,null,null,15,7);
        int i = solution.maxDepth(tree);
        System.out.println();
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
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        return print(root, 0);
    }

    int print(TreeNode root,int maxHigh) {
        if (root == null) {
            return maxHigh;
        }
        maxHigh++;
        int left = print(root.left, maxHigh);
        int right = print(root.right, maxHigh);
//        System.out.print(root.val + " ");
        return left > right ? left : right;
    }






//    private void preOrder(TreeNode node) {
//        Stack<TreeNode> stack = new Stack<>();
//        TreeNode temp = node;
//        while (temp != null || !stack.isEmpty()) {
//            while (temp != null) {
//                System.out.println(temp.val);
//                stack.push(temp);
//                temp = temp.left;
//            }
//            while (!stack.isEmpty()) {
//                temp = stack.pop();
//                temp = temp.right;
//            }
//        }
//    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
