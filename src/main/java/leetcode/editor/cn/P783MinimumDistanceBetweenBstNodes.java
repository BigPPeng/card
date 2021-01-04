//给定一个二叉搜索树的根节点 root，返回树中任意两节点的差的最小值。
//
//
//
// 示例：
//
// 输入: root = [4,2,6,1,3,null,null]
//输出: 1
//解释:
//注意，root是树节点对象(TreeNode object)，而不是数组。
//
//给定的树 [4,2,6,1,3,null,null] 可表示为下图:
//
//          4
//        /   \
//      2      6
//     / \
//    1   3
//
//最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值。
//
//
//
// 注意：
//
//
// 二叉树的大小范围在 2 到 100。
// 二叉树总是有效的，每个节点的值都是整数，且不重复。
// 本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
//相同
//
// Related Topics 树 递归


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：二叉搜索树节点最小距离
public class P783MinimumDistanceBetweenBstNodes{
    public static void main(String[] args) {
        Solution solution = new P783MinimumDistanceBetweenBstNodes().new Solution();
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
    public int minDiffInBST(TreeNode root) {
        if (root == null || (root.right == null && root.left == null))
            return 0;


        ArrayList<Integer> values = new ArrayList<>();
        minDiffInBST(root, values);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < values.size(); i++) {
            int i1 = values.get(i) - values.get(i - 1);
            min = Math.min(min,i1);
        }
        return min;
    }

    public void minDiffInBST(TreeNode root, List<Integer> values) {
        if (root == null)
            return;
        if (root.left != null) {
            minDiffInBST(root.left,values);
        }
        values.add(root.val);
        if (root.right != null) {
            minDiffInBST(root.right,values);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
