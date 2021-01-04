//给定一个二叉树，检查它是否是镜像对称的。 
//
// 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
//     1
//   / \
//  2   2
// / \ / \
//3  4 4  3
// 
//
// 
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
// 
//
// 
//
// 进阶： 
//
// 你可以运用递归和迭代两种方法解决这个问题吗？ 
// Related Topics 树 深度优先搜索 广度优先搜索


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;

//Java：对称二叉树
public class P101SymmetricTree{
    public static void main(String[] args) {
        Solution solution = new P101SymmetricTree().new Solution();
        System.out.println(solution.isSymmetric(TreeNode.getTree(1,1,1)));
        System.out.println(solution.isSymmetric(TreeNode.getTree(1,1,1,2)));
        System.out.println(solution.isSymmetric(TreeNode.getTree(1,1,1,2,null,null,2)));
        System.out.println(solution.isSymmetric(TreeNode.getTree(1,2,2,null,3,null,3)));
        System.out.println(solution.isSymmetric(TreeNode.getTree(2,3,3,4,5,5,4,null,null,8,9,null,null,9,8)));
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

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root,root);
    }

    public boolean isSymmetric(TreeNode first,TreeNode second) {
        if (first == null && second == null) {
            return true;
        }
        if (first != null && second != null) {
            if (first.val != second.val) {
                return false;
            }
            return isSymmetric(first.left,second.right) && isSymmetric(first.right,second.left);
        }
        return false;
    }
}



//leetcode submit region end(Prohibit modification and deletion)

}
