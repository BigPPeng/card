//给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历） 
//
// 例如： 
//给定二叉树 [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其自底向上的层次遍历为： 
//
// [
//  [15,7],
//  [9,20],
//  [3]
//]
// 
// Related Topics 树 广度优先搜索


package leetcode.editor.cn;

import com.alibaba.fastjson.JSON;

import java.util.*;

//Java：二叉树的层次遍历 II
public class P107BinaryTreeLevelOrderTraversalIi{
    public static void main(String[] args) {
        Solution solution = new P107BinaryTreeLevelOrderTraversalIi().new Solution();
        TreeNode _2 = new TreeNode(3);
        TreeNode _22 = new TreeNode(20);
        _2.right = _22;
        TreeNode _3 = new TreeNode(9);
        _2.left = _3;
        TreeNode _4 = new TreeNode(15);
        _22.left = _4;
        TreeNode _5 = new TreeNode(7);
        _22.right = _5;
        List<List<Integer>> i = solution.levelOrderBottom(_2);
        System.out.println(JSON.toJSONString(i));
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res1 = new ArrayList<>();
        dfs(root,res1,1);
        Collections.reverse(res1);
        return res1;
    }

    private void dfs(TreeNode treeNode, List<List<Integer>> res1,int level) {
        if (treeNode == null) {
            return;
        }
        if (res1.size() < level) {
            res1.add(new ArrayList<>());
        }
        res1.get(level - 1).add(treeNode.val);
        dfs(treeNode.left,res1,level + 1);
        dfs(treeNode.right,res1,level + 1);
    }



    private List<List<Integer>> getListsTwo(TreeNode root) {
        List<List<Integer>> res1 = new ArrayList<>();
        if (root == null) {
            return res1;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> res = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode lastNode = queue.removeFirst();
                res.add(lastNode.val);
                if (lastNode.left != null) {
                    queue.addLast(lastNode.left);
                }
                if (lastNode.right != null) {
                    queue.addLast(lastNode.right);
                }
            }
            res1.add(res);
        }
        Collections.reverse(res1);
        return res1;
    }

    private List<List<Integer>> getListsOne(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        TreeNode lastNode = null;
        while (!queue.isEmpty()) {
            lastNode = queue.removeFirst();
            if (lastNode.left != null) {
                queue.addLast(lastNode.left);
            }
            if (lastNode.right != null) {
                queue.addLast(lastNode.right);
            }
        }

        ArrayList<TreeNode> all = new ArrayList<>();
        queue.clear();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.removeFirst();
            all.add(temp);
            if (lastNode == temp) {
                break;
            }
            if (temp == null) {
                queue.addLast(null);
                queue.addLast(null);
            } else {
                queue.addLast(temp.left);
                queue.addLast(temp.right);
            }
        }
        // 拆层
        List<List<TreeNode>> res = new ArrayList<>() ;
        int level = 0;
        int start = (1 << level) - 1;
        int size = all.size();
        while (start < size) {
            int end = (1 << (level + 1)) - 1;
            int min = Math.min(end, size);
            List<TreeNode> treeNodes = all.subList(start, min);
            res.add(treeNodes);
            level++;
            start = (1 << level) - 1;
        }

        // 反转
        List<List<Integer>> res1 = new ArrayList<>();
        for (int i = res.size() -1 ; i >= 0; i--) {
            List<TreeNode> treeNodes = res.get(i);
            List<Integer> re = new ArrayList<>();
            treeNodes.forEach((s) -> {
                if (s != null) {
                    re.add(s.val);
                }
            });
            res1.add(re);
        }

        return res1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
