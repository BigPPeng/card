package leetcode.editor.cn;

/**
 * Created by cuihp on 2020/7/1.
 */
public class Node1 {
    int val;
    Node1 next;
    Node1 random;

    public Node1(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    public static void print(Node1 node) {
        while (node != null) {
            System.out.print(node.toString()+" ");
            node = node.next;
        }
        System.out.println();
    }

    @Override
    public String toString() {
        String s = val+"";
        if (random != null) {
            s += "_"+random.val;
        }
        return s;
    }
}
