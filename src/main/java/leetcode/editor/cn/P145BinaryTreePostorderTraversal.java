//给定一个二叉树，返回它的 后序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]  
//   1
//    \
//     2
//    /
//   3 
//
//输出: [3,2,1] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Java：二叉树的后序遍历
public class P145BinaryTreePostorderTraversal{
    public static void main(String[] args) {
        Solution solution = new P145BinaryTreePostorderTraversal().new Solution();
        TreeNode tree = TreeNode.getTree(1, 2, 3, 4, 5, null, null, 6);
        List<Integer> list = solution.postorderTraversal(tree);
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
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> a = new ArrayList<>();
        TreeNode temp = root;
        TreeNode lastPrint = null;
        while (temp != null) {
            //a.add(temp.val);
            stack.add(temp);
            temp = temp.left;
            while (temp == null && !stack.isEmpty()) {
                temp = stack.pop();
                //temp = temp.right;
                if (temp.right != null && temp.right != lastPrint) {
                    stack.add(temp);
                    temp = temp.right;
                } else {
                    a.add(temp.val);
                    lastPrint = temp;
                    temp = null;
                }
            }
        }
        return a;



    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
