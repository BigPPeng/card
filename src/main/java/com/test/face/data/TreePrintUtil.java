package com.test.face.data;

import com.card.test.tree.Node;

import java.util.Stack;

/**
 * Created by cuihp on 2020/12/13.
 */
public class TreePrintUtil {
    public static void main(String[] args) {
        //preOrder(getTree());

       // middleOrder(getTree()); // 4 2 8 6 9 5 7 1 3


        postOrder(getTree());// 4 8 9 6 7 5 2 3 1
    }




    private static void preOrder(TreeNode node){
        if (node == null) {
            return;
        }
        TreeNode printTemp = node;
        Stack<TreeNode> stack = new Stack<>();
        while (printTemp != null) {
            System.out.println(printTemp.getValue());
            stack.push(printTemp);
            printTemp = printTemp.getLeft();
            while (printTemp == null && !stack.isEmpty()) {
                printTemp = stack.pop().getRight();
                if (printTemp != null) {
                    break;
                }
            }
        }
    }

    private static void preOrder2(Node node) {
        Stack<Node> stack = new Stack<>();
        Node temp = node;
        while (temp != null || !stack.empty()) {
            while (temp != null) {
                System.out.print(temp.aChar);
                stack.push(temp);
                temp = temp.left;
            }
            if (!stack.empty()) {
                temp = stack.pop();
                temp = temp.right;
            }
        }
    }

    private static void middleOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode printTemp = node;
        Stack<TreeNode> stack = new Stack<>();
        while (printTemp != null) {
            stack.push(printTemp);
            printTemp = printTemp.getLeft();
            while (printTemp == null && !stack.isEmpty()) {
                printTemp = stack.pop();
                System.out.println(printTemp.getValue());
                printTemp = printTemp.getRight();
            }
        }
    }


    private static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode printTemp = node;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode lastPrint = null;
        while (printTemp != null) {
            stack.push(printTemp);
            printTemp = printTemp.getLeft();
            while (printTemp == null && !stack.isEmpty()) {
                printTemp = stack.pop();
                if (printTemp.getRight() == null || printTemp.getRight() == lastPrint) {
                    System.out.println(printTemp.getValue());
                    lastPrint = printTemp;
                    printTemp = null;
                } else {
                    stack.push(printTemp);
                    printTemp = printTemp.getRight();
                }
            }

        }



    }


    private static TreeNode getTree() {
        TreeNode treeNode_1 = new TreeNode(1);
        TreeNode treeNode_2 = new TreeNode(2);
        TreeNode treeNode_3 = new TreeNode(3);
        treeNode_1.setLeft(treeNode_2);
        treeNode_1.setRight(treeNode_3);
        TreeNode treeNode_4 = new TreeNode(4);
        TreeNode treeNode_5 = new TreeNode(5);
        treeNode_2.setLeft(treeNode_4);
        treeNode_2.setRight(treeNode_5);
        TreeNode treeNode_6 = new TreeNode(6);
        TreeNode treeNode_7 = new TreeNode(7);
        treeNode_5.setLeft(treeNode_6);
        treeNode_5.setRight(treeNode_7);
        TreeNode treeNode_8 = new TreeNode(8);
        TreeNode treeNode_9 = new TreeNode(9);
        treeNode_6.setLeft(treeNode_8);
        treeNode_6.setRight(treeNode_9);
        return treeNode_1;
    }

}
