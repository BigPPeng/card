//给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。 
//
// 
//
// 示例： 
//
// 输入：
//
//   1
//    \
//     3
//    /
//   2
//
//输出：
//1
//
//解释：
//最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
// 
//
// 
//
// 提示： 
//
// 
// 树中至少有 2 个节点。 
// 本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 
//相同 
// 
// Related Topics 树


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：二叉搜索树的最小绝对差
public class P530MinimumAbsoluteDifferenceInBst{
    public static void main(String[] args) {
        Solution solution = new P530MinimumAbsoluteDifferenceInBst().new Solution();
        int minimumDifference = solution.getMinimumDifference(TreeNode.getTree(1,null,3,null,null,2));
        System.out.println(minimumDifference);


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
    public int getMinimumDifference(TreeNode root) {
        List<Integer> list = inorderTraversal(root);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < list.size(); i++) {
            int i1 = list.get(i) - list.get(i - 1);
            min = Math.min(min,i1);
        }
        return min;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversalTest(root,list);
        return list;
    }

    public void inorderTraversalTest(TreeNode root,List<Integer> res) {
        if (root == null)
            return;
        if (root.left != null) {
            inorderTraversalTest(root.left,res);
        }
        res.add(root.val);
        if (root.right != null) {
            inorderTraversalTest(root.right,res);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
