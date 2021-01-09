//给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。 
//
// 
//
// 示例 : 
//给定二叉树 
//
//           1
//         / \
//        2   3
//       / \     
//      4   5    
// 
//
// 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。 
//
// 
//
// 注意：两结点之间的路径长度是以它们之间边的数目表示。 
// Related Topics 树


package leetcode.editor.cn;
//Java：二叉树的直径
public class P543DiameterOfBinaryTree{
    public static void main(String[] args) {
        Solution solution = new P543DiameterOfBinaryTree().new Solution();
        TreeNode tree = TreeNode.getTree(1, 2, 3);
        int i = solution.diameterOfBinaryTree(tree);
        //System.out.println(i);


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
    public int diameterOfBinaryTree(TreeNode root) {
        int a[] = new int[1];
        int i = diameterOfBinaryTree(root, a);
        System.out.println(i);
        return a[0];
    }

    public int diameterOfBinaryTree(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }

        int left = diameterOfBinaryTree(root.left,max);
        int right = diameterOfBinaryTree(root.right,max);

        int tempMax = left + right;
        if (root.left != null)
            tempMax++;
        if (root.right != null)
            tempMax++;
        max[0] = max[0] > tempMax ? max[0] : tempMax;
        return Math.max(left,right) + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
