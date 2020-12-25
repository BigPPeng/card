//给定一个二叉树，检查它是否是镜像对称的。 
//
// 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
//     1
//   / \
//  2   2
// / \ / \
//3  4 4  3
// 
//
// 
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
// 
//
// 
//
// 进阶： 
//
// 你可以运用递归和迭代两种方法解决这个问题吗？ 
// Related Topics 树 深度优先搜索 广度优先搜索


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;

//Java：对称二叉树
public class P101SymmetricTree{
    public static void main(String[] args) {
        Solution solution = new P101SymmetricTree().new Solution();
        System.out.println(solution.isSymmetric(TreeNode.getTree(1,1,1)));
        System.out.println(solution.isSymmetric(TreeNode.getTree(1,1,1,2)));
        System.out.println(solution.isSymmetric(TreeNode.getTree(1,1,1,2,null,null,2)));
        System.out.println(solution.isSymmetric(TreeNode.getTree(1,2,2,null,3,null,3)));
        System.out.println(solution.isSymmetric(TreeNode.getTree(2,3,3,4,5,5,4,null,null,8,9,null,null,9,8)));
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

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        LinkedList<Node> linkedList = new LinkedList<>();
        linkedList.addLast(new Node(root,false));
        int level = 0;
        while (!linkedList.isEmpty()) {
            level++;
            int levelSize = linkedList.size();
            // 某一层非偶数个节点，直接返回false
            if (level != 1 && (levelSize % 2 != 0)) {
                return false;
            }
            Node[] levelVal = new Node[levelSize];
            for (int i = 0; i < levelSize; i++) {
                Node treeNode = linkedList.removeFirst();
                levelVal[i] = treeNode;
                if (treeNode.node.left != null) {
                    linkedList.addLast(new Node(treeNode.node.left,true));
                }
                if (treeNode.node.right != null) {
                    linkedList.addLast(new Node(treeNode.node.right,false));
                }
            }
            if (level != 1 && !isSymmetric(levelVal)){
                return false;
            }
        }
        return true;
    }


    class Node{
        TreeNode node;
        boolean left;

        public Node(TreeNode node, boolean left) {
            this.node = node;
            this.left = left;
        }
    }

    public boolean isSymmetric(Node[] levelVal) {
        int end = levelVal.length - 1;
        int start = 0;
        while (start < end) {
            if (levelVal[start].node.val != levelVal[end].node.val
                    || levelVal[start].left == levelVal[end].left) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}



//leetcode submit region end(Prohibit modification and deletion)

}
