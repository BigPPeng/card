//根据一棵树的中序遍历与后序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
// Related Topics 树 深度优先搜索 数组 
// 👍 432 👎 0


package leetcode.editor.cn;

import java.util.Arrays;

//Java：从中序与后序遍历序列构造二叉树
public class P106ConstructBinaryTreeFromInorderAndPostorderTraversal{
    public static void main(String[] args) {
        Solution solution = new P106ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();
        TreeNode treeNode = solution.buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3});
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0)
            return null;
        int rootVal = postorder[postorder.length - 1];// 根节点

        TreeNode treeNode = new TreeNode(rootVal);
        int leftRootIndex = getLeftIndex(inorder,rootVal);
        if (leftRootIndex > 0) {
            int[] inOrderLeft =  Arrays.copyOfRange(inorder, 0, leftRootIndex);
            int[] postOrderLeft =  Arrays.copyOfRange(postorder, 0, leftRootIndex);
            treeNode.left = buildTree(inOrderLeft,postOrderLeft);
        }
        if (leftRootIndex < (inorder.length - 1)) {
            int[] inOrderRight =  Arrays.copyOfRange(inorder, leftRootIndex + 1, inorder.length);
            int[] postOrderRight =  Arrays.copyOfRange(postorder, leftRootIndex, postorder.length - 1);
            treeNode.right = buildTree(inOrderRight,postOrderRight);
        }
        return treeNode;
    }
    private int getLeftIndex(int[] inorder, int rootVal) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootVal)
                return i;
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
