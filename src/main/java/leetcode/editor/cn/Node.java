package leetcode.editor.cn;

/**
 * Created by cuihp on 2020/7/1.
 */
public class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    public static void print(Node node) {
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
