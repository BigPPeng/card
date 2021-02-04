//给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。例如，如果路径为 0 -> 1 -> 1 ->
// 0 -> 1，那么它表示二进制数 01101，也就是 13 。 
//
// 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。 
//
// 返回这些数字之和。题目数据保证答案是一个 32 位 整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,0,1,0,1,0,1]
//输出：22
//解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
// 
//
// 示例 2： 
//
// 
//输入：root = [0]
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：1
// 
//
// 示例 4： 
//
// 
//输入：root = [1,1]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 树中的结点数介于 1 和 1000 之间。 
// Node1.val 为 0 或 1 。
// 
// Related Topics 树 
// 👍 90 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Java：从根到叶的二进制数之和
public class P1022SumOfRootToLeafBinaryNumbers{
    public static void main(String[] args) {
        Solution solution = new P1022SumOfRootToLeafBinaryNumbers().new Solution();
        TreeNode tree = TreeNode.getTree(1,0,1,0,1,0,1);
        int i = solution.sumRootToLeaf(tree);
        System.out.println(i);

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
    public int sumRootToLeaf(TreeNode root) {
        int[] sum = new int[1];
        travel2(root,sum, 0);
        return sum[0];
    }
    private void travel2(TreeNode root, int[] vals, int sum) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            sum = (sum << 1) + root.val;
            vals[0] = vals[0] + sum;
            return;
        }
        sum = (sum << 1) + root.val;
        if (root.left != null) {
            travel2(root.left,vals,sum);
        }
        if (root.right!= null) {
            travel2(root.right,vals,sum);
        }
    }

    private void travel(TreeNode root, List<Integer> s, LinkedList<Integer> vals) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            vals.addLast(root.val);
            s.add(getStr(vals));
            return;
        }
        vals.addLast(root.val);
        if (root.left != null) {
            travel(root.left,s,vals);
            vals.removeLast();
        }
        if (root.right!= null) {
            travel(root.right,s,vals);
            vals.removeLast();
        }
    }

    private int getStr(List<Integer> vals) {
        int j = 0;
        int sum = 0;
        for (int i = vals.size() - 1; i >= 0; i--) {
            Integer integer = vals.get(i);
            if (integer == 1) {
                int i1 = 1 << j;
                sum += i1;
            }
            j++;
        }
        return sum;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
