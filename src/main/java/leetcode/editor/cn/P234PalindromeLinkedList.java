//请判断一个链表是否为回文链表。 
//
// 示例 1: 
//
// 输入: 1->2
//输出: false 
//
// 示例 2: 
//
// 输入: 1->2->2->1
//输出: true
// 
//
// 进阶： 
//你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 链表 双指针


package leetcode.editor.cn;
//Java：回文链表
public class P234PalindromeLinkedList{
    public static void main(String[] args) {
        Solution solution = new P234PalindromeLinkedList().new Solution();
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
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode high = head;
        ListNode low = head;
        while (high.next != null) {
            if (high.next.next == null) {
                break;
            }
            high = high.next.next;
            low = low.next;
        }
        ListNode node = reverseList(null,low.next);
        while (head != null && node != null) {
            if (head.val != node.val) {
                return false;
            }
            head = head.next;
            node = node.next;
        }
        return true;
    }



    private ListNode reverseList(ListNode resHead, ListNode next) {
        if (next == null) {
            return resHead;
        }
        ListNode node = next.next;
        next.next = resHead;
        return reverseList(next, node);
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
