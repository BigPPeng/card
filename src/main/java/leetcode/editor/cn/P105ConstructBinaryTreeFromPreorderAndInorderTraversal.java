//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组 
// 👍 820 👎 0


package leetcode.editor.cn;

import java.util.Arrays;

//Java：从前序与中序遍历序列构造二叉树
public class P105ConstructBinaryTreeFromPreorderAndInorderTraversal{
    public static void main(String[] args) {
        Solution solution = new P105ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
        TreeNode treeNode = solution.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0)
            return null;
        int rootVal = preorder[0];
        TreeNode treeNode = new TreeNode(rootVal);
        int leftRootIndex = getLeftIndex(inorder,rootVal);
        if (leftRootIndex > 0) {
            int[] inOrderLeft =  Arrays.copyOfRange(inorder, 0, leftRootIndex);
            int[] preOrderLeft =  Arrays.copyOfRange(preorder, 1, leftRootIndex + 1);
            treeNode.left = buildTree(preOrderLeft,inOrderLeft);
        }
        if (leftRootIndex < (inorder.length - 1)) {
            int[] inOrderRight =  Arrays.copyOfRange(inorder, leftRootIndex + 1, inorder.length);
            int[] preOrderRight =  Arrays.copyOfRange(preorder, leftRootIndex + 1, preorder.length);
            treeNode.right = buildTree(preOrderRight,inOrderRight);
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
