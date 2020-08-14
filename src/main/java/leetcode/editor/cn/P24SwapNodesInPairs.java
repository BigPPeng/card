//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例: 
//
// 给定 1->2->3->4, 你应该返回 2->1->4->3.
// 
// Related Topics 链表


package leetcode.editor.cn;
//Java：两两交换链表中的节点
public class P24SwapNodesInPairs{
    public static void main(String[] args) {
        Solution solution = new P24SwapNodesInPairs().new Solution();
        ListNode list = ListNode.getList(1,2);
        ListNode.print(list);
        ListNode node = solution.swapPairs(list);
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
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = null;
        ListNode first = head;
        ListNode second = head.next;
        ListNode tempEnd = head.next;
        while (true) {
            if (newHead == null) {
                newHead = second;
                first.next = second.next;
                second.next = first;
                tempEnd = first;
            } else {
                tempEnd.next = second;
                first.next = second.next;
                second.next = first;
                tempEnd = first;
            }
            first = first.next;
            if (first == null || first.next == null) {
                break;
            }
            second = first.next;
        }
        return newHead;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
