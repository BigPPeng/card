//给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 例如，给定如下二叉搜索树: root = [6,2,8,0,4,7,9,null,null,3,5] 
//
// 
//
// 
//
// 示例 1: 
//
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
//输出: 6 
//解释: 节点 2 和节点 8 的最近公共祖先是 6。
// 
//
// 示例 2: 
//
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
//输出: 2
//解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。 
//
// 
//
// 说明: 
//
// 
// 所有节点的值都是唯一的。
// p、q 为不同节点且均存在于给定的二叉搜索树中。
// 
// Related Topics 树


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：二叉搜索树的最近公共祖先
public class P235LowestCommonAncestorOfABinarySearchTree{
    public static void main(String[] args) {
        Solution solution = new P235LowestCommonAncestorOfABinarySearchTree().new Solution();
        TreeNode _5 = new TreeNode(5);
        TreeNode _3 = new TreeNode(3);
        TreeNode _6 = new TreeNode(6);
        _5.left = _3;
        _5.right = _6;

        TreeNode _2 = new TreeNode(2);
        TreeNode _4 = new TreeNode(4);
        _3.left  = _2;
        _3.right = _4;

        TreeNode _1 = new TreeNode(1);
        _2.left = _1;
        TreeNode treeNode = solution.lowestCommonAncestor(_5, _1, _4);
        System.out.println(treeNode != null ? treeNode.val : "NULL");
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        // 所有左子树
        List<TreeNode> left = new ArrayList<>();
        middle(root.left,left);
        int p_index_left = findIndex(left,p.val);
        int q_index_left = findIndex(left,q.val);
        // 全在左子树，则递归左子树
        if (p_index_left >= 0 && q_index_left >= 0) {
            return lowestCommonAncestor(root.left,p,q);
        }
        // 全在右子树，则递归右子树
        if (p_index_left < 0 && q_index_left < 0 && root.val != p.val && root.val != q.val) {
            return lowestCommonAncestor(root.right,p,q);
        }
        return root;
    }

    private int findIndex(List<TreeNode> res, int val) {
        for (int i = 0; i < res.size(); i++) {
            if (res.get(i).val == val)
                return i;
        }
        return -1;
    }

    public void middle(TreeNode root,List<TreeNode> res) {
        if (root == null)
            return;
        if (root.left != null) {
            middle(root.left,res);
        }
        res.add(root);
        if (root.right != null) {
            middle(root.right,res);
        }
    }





}
//leetcode submit region end(Prohibit modification and deletion)

}
