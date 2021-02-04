//给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索 递归 
// 👍 888 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java：验证二叉搜索树
public class P98ValidateBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new P98ValidateBinarySearchTree().new Solution();
        TreeNode tree = TreeNode.getTree(5, 1, 4, null, null, 3, 6);
        boolean validBST = solution.isValidBST(tree);
        System.out.println(validBST);
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public boolean isValidBST(TreeNode root) {
            List<Integer> preOrder = new ArrayList<>();
            List<Integer> middleOrder = new ArrayList<>();
            order(root, preOrder, middleOrder);
            return isValidBST(preOrder, 0, preOrder.size(), middleOrder, 0, middleOrder.size());
        }

        private boolean isValidBST(List<Integer> preOrder, int preBegin, int preEnd, List<Integer> inorder, int inBegin, int inEnd) {
            if (preOrder.size() == 0)
                return true;
            if (preEnd - preBegin <= 1)
                return true;


            int rootValue = preOrder.get(preBegin);
            int leftRootIndex = findMiddleIndex(inorder, rootValue);
            if (leftRootIndex > inBegin) {
                int max = getMax(inorder, inBegin, leftRootIndex);
                if (max >= rootValue)
                    return false;
            }
            if (leftRootIndex < (inEnd - 1)) {
                int min = getMin(inorder, leftRootIndex + 1, inEnd);
                if (min <= rootValue)
                    return false;
            }

            boolean left = true;
            if (leftRootIndex > 0) {
                left = isValidBST(preOrder, preBegin + 1, leftRootIndex + 1, inorder, inBegin, leftRootIndex);
            }
            boolean right = true;
            if (leftRootIndex < (inEnd - 1)) {
                right = isValidBST(preOrder, leftRootIndex + 1, preEnd, inorder, leftRootIndex + 1, inEnd);
            }


            return left && right;
        }

        private int findMiddleIndex(List<Integer> middleOrder, int rootValue) {
            for (int i = 0; i < middleOrder.size(); i++) {
                if (middleOrder.get(i) == rootValue)
                    return i;
            }
            return -1;
        }


        public void order(TreeNode root, List<Integer> preOrder, List<Integer> middleOrder) {
            if (root == null)
                return;
            preOrder.add(root.val);
            order(root.left, preOrder, middleOrder);
            middleOrder.add(root.val);
            order(root.right, preOrder, middleOrder);
        }


        private int getMin(List<Integer> list, int begin, int end) {
            Integer min = null;
            for (int i = begin; i < end; i++) {
                if (min == null || list.get(i) < min)
                    min = list.get(i);
            }
            return min;
        }

        private int getMax(List<Integer> list, int begin, int end) {
            Integer max = null;
            for (int i = begin; i < end; i++) {
                if (max == null || list.get(i) > max)
                    max = list.get(i);
            }
            return max;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
