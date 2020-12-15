package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by cuihp on 2020/5/22.
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode() {
    }

    TreeNode(int x) {
        val = x;
    }


    public static void levelPrint(TreeNode node){
        if (node == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll == null) {
                System.out.print(" NULL ");
                continue;
            } else {
                System.out.print(" "+poll.val+" ");
            }
            queue.add(poll.left);
            queue.add(poll.right);
        }
    }

    public static TreeNode getTree(Integer... values) {
        if (values.length == 0) {
            return null;
        }
        TreeNode head = new TreeNode(values[0]);
        if (values.length == 1) {
            return head;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        int index = 1;
        while (index < values.length) {
            TreeNode poll = queue.poll();
            Integer left = values[index];
            Integer right = index + 1 < values.length ? values[index + 1] : null;
            if (poll == null && left == null && right == null) {
                queue.add(null);
                queue.add(null);
                index += 2;
                continue;
            }
            if (poll == null && (left != null || right != null)) {
                throw new RuntimeException("错误的参数");
            }
            if (left != null) {
                TreeNode treeNode = new TreeNode(left);
                poll.left = treeNode;
                queue.add(treeNode);
            }
            if (right != null) {
                TreeNode treeNode = new TreeNode(right);
                poll.right = treeNode;
                queue.add(treeNode);
            }
            index += 2;
        }
        return head;
    }

    // region set/get

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    // endregion

}
