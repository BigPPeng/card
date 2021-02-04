//给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。 
//
// 
//
// 示例 1： 
//
// 输入：
//    3
//   / \
//  9  20
//    /  \
//   15   7
//输出：[3, 14.5, 11]
//解释：
//第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
// 
//
// 
//
// 提示： 
//
// 
// 节点值的范围在32位有符号整数范围内。 
// 
// Related Topics 树


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Java：二叉树的层平均值
public class P637AverageOfLevelsInBinaryTree{
    public static void main(String[] args) {
        Solution solution = new P637AverageOfLevelsInBinaryTree().new Solution();
        TreeNode tree = TreeNode.getTree(3, 9, 20, null, null, 15, 7);
        List<Double> doubles = solution.averageOfLevels(tree);
        System.out.println(doubles);
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
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> level = new LinkedList<>();
        level.add(root);
        while (!level.isEmpty()){
            int size = level.size();
            double sum = 0.;
            for (int i = 0; i < size; i++) {
                TreeNode remove = level.remove();
                if (remove.left != null)
                    level.add(remove.left);
                if (remove.right != null)
                    level.add(remove.right);
                sum+=remove.val;
            }
            double agv = sum / size;
            res.add(agv);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
