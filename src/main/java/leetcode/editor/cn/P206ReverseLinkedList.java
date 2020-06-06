//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表


package leetcode.editor.cn;

import static leetcode.editor.cn.P206ReverseLinkedList.printll;

//Java：反转链表
public class P206ReverseLinkedList{
    public static void main(String[] args) {
        Solution solution = new P206ReverseLinkedList().new Solution();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        P206ReverseLinkedList.printll(node1);
        ListNode node = solution.reverseList(node1);
        System.out.println();
        P206ReverseLinkedList.printll(node);
        // TO TEST
    }

    public static void printll(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        return reverseList(null,head);
    }


    private ListNode reverseList(ListNode resHead, ListNode next) {
        if (next == null) {
            return resHead;
        }
        ListNode node = next.next;
        next.next = resHead;
        return reverseList(next, node);
    }



    private ListNode one(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode temp = head.next;
        ListNode first = head;
        ListNode next;
        first.next = null;
        while (temp != null) {
            next = temp.next;
            temp.next = first;
            first = temp;
            temp = next;
        }
        return first;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
