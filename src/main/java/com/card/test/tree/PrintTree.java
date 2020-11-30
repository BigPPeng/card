package com.card.test.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by cuihp on 2020/4/13.
 */
public class PrintTree {

    public static void main(String[] args) {
        Node tree = getTree();
        preOrder(tree);// 前序遍历递归
        System.out.println();
        preOrder2(tree);// 前序遍历非递归
        System.out.println();
        middleOrder(tree);// 中序遍历递归
        System.out.println();
        middleOrder2(tree);// 中序遍历非递归
        System.out.println();
        postOrder(tree);// 后序遍历递归
        System.out.println();
        postOrder3(tree);// 后序遍历非递归
        System.out.println();
        levelPrint(tree);
    }



    private static void levelPrint(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            if (poll == null) {
                continue;
            }
            System.out.print(poll.aChar + " ");
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
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

    private static void middleOrder2(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node temp = head;
        while (temp != null || !stack.empty()) {
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
            if (!stack.empty()) {
                temp = stack.pop();
                System.out.print(temp.aChar);
                temp = temp.right;
            }
        }
    }

    private static void postOrder3(Node head) {
        Stack<Node> stack = new Stack<>();
        Node temp = head;
        Node last = null;
        while (temp != null || !stack.empty()) {
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
            if (!stack.empty()) {
                temp = stack.pop();
                if (temp.right == null || temp.right == last) {
                    System.out.print(temp.aChar);
                    last = temp;
                    temp = null;
                } else {
                    stack.push(temp);
                    temp = temp.right;
                }
            }
        }
    }

    private static void preOrder(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.aChar);
        preOrder(head.left);
        preOrder(head.right);
    }

    private static void middleOrder(Node head) {
        if (head == null) {
            return;
        }
        middleOrder(head.left);
        System.out.print(head.aChar);
        middleOrder(head.right);
    }

    private static void postOrder(Node head) {
        if (head == null) {
            return;
        }
        postOrder(head.left);
        postOrder(head.right);
        System.out.print(head.aChar);
    }


    private static Node getTree() {
        Node head = new Node('a');
        Node f_l_1 = new Node('b');
        Node f_r_2 = new Node('c');
        head.left = f_l_1;
        head.right = f_r_2;
        Node s_l_1 = new Node('d');
        Node s_r_2 = new Node('e');
        f_l_1.left = s_l_1;
        f_l_1.right = s_r_2;
        Node s_l_3 = new Node('f');
        Node s_r_4 = new Node('g');
        f_r_2.left = s_l_3;
        f_r_2.right = s_r_4;

        Node t_l_1 = new Node('h');
        Node t_r_2 = new Node('i');
        s_l_1.left = t_l_1;
        s_l_1.right = t_r_2;

        return head;
    }


}
