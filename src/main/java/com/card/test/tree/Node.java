package com.card.test.tree;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;

import java.util.Set;

public class Node {
    public char aChar;
    public Node left;
    public Node right;
    private boolean logicMove;
    private Set<Integer> moveCitys;

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

    public static void main(String[] args) {
        Node node  = new Node('C');
        node.setLogicMove(true);
        node.setMoveCitys(Sets.<Integer>newHashSet(2201,0201,1508,2005,801,2301,1105,2501,1604,901));
        System.out.println(JSON.toJSONString(node));

    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Set<Integer> getMoveCitys() {
        return moveCitys;
    }

    public void setMoveCitys(Set<Integer> moveCitys) {
        this.moveCitys = moveCitys;
    }

    public char getaChar() {
        return aChar;
    }

    public void setaChar(char aChar) {
        this.aChar = aChar;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public boolean isLogicMove() {
        return logicMove;
    }

    public void setLogicMove(boolean logicMove) {
        this.logicMove = logicMove;
    }
}
