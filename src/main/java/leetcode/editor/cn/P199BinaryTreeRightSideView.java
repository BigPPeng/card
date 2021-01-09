//给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。 
//
// 示例: 
//
// 输入: [1,2,3,null,5,null,4]
//输出: [1, 3, 4]
//解释:
//
//   1            <---
// /   \
//2     3         <---
// \     \
//  5     4       <---
// 
// Related Topics 树 深度优先搜索 广度优先搜索


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Java：二叉树的右视图
public class P199BinaryTreeRightSideView{
    public static void main(String[] args) {
        Solution solution = new P199BinaryTreeRightSideView().new Solution();
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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        LinkedList<TreeNode> link = new LinkedList<>();
        link.addLast(root);
        while (!link.isEmpty()) {
            int i = link.size();
            for (int j = 0; j < i; j++) {
                TreeNode treeNode = link.removeFirst();
                if (treeNode.left != null)
                    link.addLast(treeNode.left);
                if (treeNode.right != null)
                    link.addLast(treeNode.right);
                if (j == (i - 1)) {
                    list.add(treeNode.val);
                }
            }
        }

        return list;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
