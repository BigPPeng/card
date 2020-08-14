package leetcode.editor.cn;

/**
 * Created by cuihp on 2020/5/25.
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    static void print(ListNode head2) {
        if (head2 == null) {
            System.out.println("null");
        }
        ListNode temp = head2;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }


    static ListNode getList(int... a) {
        if (a.length == 0) {
            return null;
        }
        ListNode node = null;
        ListNode temp = null;
        for (int i : a) {
            if (node == null) {
                node = new ListNode(i);
                temp = node;
            } else {
                temp.next = new ListNode(i);
                temp = temp.next;
            }
        }
        return node;
    }
}
