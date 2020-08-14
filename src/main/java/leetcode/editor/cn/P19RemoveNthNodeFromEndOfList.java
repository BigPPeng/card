//给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。 
//
// 示例： 
//
// 给定一个链表: 1->2->3->4->5, 和 n = 2.
//
//当删除了倒数第二个节点后，链表变为 1->2->3->5.
// 
//
// 说明： 
//
// 给定的 n 保证是有效的。 
//
// 进阶： 
//
// 你能尝试使用一趟扫描实现吗？ 
// Related Topics 链表 双指针


package leetcode.editor.cn;

import java.util.Stack;

//Java：删除链表的倒数第N个节点
public class P19RemoveNthNodeFromEndOfList{
    public static void main(String[] args) {
        Solution solution = new P19RemoveNthNodeFromEndOfList().new Solution();
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        head1.next = head2;
//        head2.next = head3;
//        head3.next = head4;
//        head4.next = head5;

        ListNode.print(head1);
        ListNode node = solution.removeNthFromEnd(head1, 2);
        ListNode.print(node);

        // TO TEST
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        while (n > 0) {
            first = first.next;
            n--;
        }
        if (first == null) {
            return head.next;
        }


        ListNode second = head;
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return head;
    }

    private ListNode one(ListNode head, int n) {
        Stack<ListNode> nodes = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            nodes.push(temp);
            temp = temp.next;
        }

        while (!nodes.isEmpty() && n > 1) {
            temp = nodes.pop();
            n--;
        }
        if (nodes.isEmpty()) {
            return temp;
        }

        if (!nodes.isEmpty()) {
            ListNode pop = nodes.pop();
            pop.next = null;
        }

        if (nodes.isEmpty()) {
            return temp;
        }

        ListNode temp1 = nodes.pop();
        temp1.next = temp;
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
