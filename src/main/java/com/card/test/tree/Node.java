package com.card.test.tree;

public class Node {
    public char aChar;
    public Node left;
    public Node right;

    public Node(char aChar) {
        this.aChar = aChar;
    }

    public Node(char aChar, Node left) {
        this.aChar = aChar;
        this.left = left;
    }

    public Node(char aChar, Node left, Node right) {
        this.aChar = aChar;
        this.left = left;
        this.right = right;
    }
}
