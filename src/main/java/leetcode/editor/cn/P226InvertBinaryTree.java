//翻转一棵二叉树。
//
// 示例：
//
// 输入：
//
//      4
//   /   \
//  2     7
// / \   / \
//1   3 6   9
//
// 输出：
//
//      4
//   /   \
//  7     2
// / \   / \
//9   6 3   1
//
// 备注:
//这个问题是受到 Max Howell 的 原问题 启发的 ：
//
// 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
// Related Topics 树


package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

//Java：翻转二叉树
public class P226InvertBinaryTree{
    public static void main(String[] args) {
        Solution solution = new P226InvertBinaryTree().new Solution();
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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode remove = queue.remove();
            TreeNode oldLeft = remove.left;
            remove.left = remove.right;
            remove.right = oldLeft;

            if (remove.left != null) {
                queue.add(remove.left);
            }
            if (remove.right != null) {
                queue.add(remove.right);
            }
        }
        return root;

    }

    private TreeNode one(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode oldLeft = root.left;
        root.left = root.right;
        root.right = oldLeft;

        if (root.right != null) {
            invertTree(root.right);
        }
        if (root.left!= null) {
            invertTree(root.left);
        }
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
