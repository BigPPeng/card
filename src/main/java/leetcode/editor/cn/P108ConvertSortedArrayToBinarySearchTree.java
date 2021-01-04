//将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。 
//
// 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。 
//
// 示例: 
//
// 给定有序数组: [-10,-3,0,5,9],
//
//一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
//
//      0
//     / \
//   -3   9
//   /   /
// -10  5
// 
// Related Topics 树 深度优先搜索


package leetcode.editor.cn;

import java.util.Arrays;

//Java：将有序数组转换为二叉搜索树
public class P108ConvertSortedArrayToBinarySearchTree{
    public static void main(String[] args) {
        Solution solution = new P108ConvertSortedArrayToBinarySearchTree().new Solution();
        int[] a = new int[]{-10,-3,0,5,9};
        TreeNode treeNode = solution.sortedArrayToBST(a);
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
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        if (nums.length == 2) {
            TreeNode _1 = new TreeNode(nums[0]);
            _1.right = new TreeNode(nums[1]);
            return _1;
        }
        if (nums.length == 3) {
            TreeNode _1 = new TreeNode(nums[1]);
            _1.left = new TreeNode(nums[0]);
            _1.right = new TreeNode(nums[2]);
            return _1;
        }
        int middleIndex = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums, 0, middleIndex);
        int[] right = Arrays.copyOfRange(nums, middleIndex+1, nums.length);
        TreeNode treeNode = new TreeNode(nums[middleIndex]);
        treeNode.left = sortedArrayToBST(left);
        treeNode.right = sortedArrayToBST(right);
        return treeNode;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
