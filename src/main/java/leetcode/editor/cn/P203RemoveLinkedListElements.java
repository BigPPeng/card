//删除链表中等于给定值 val 的所有节点。 
//
// 示例: 
//
// 输入: 1->2->6->3->4->5->6, val = 6
//输出: 1->2->3->4->5
// 
// Related Topics 链表


package leetcode.editor.cn;
//Java：移除链表元素
public class P203RemoveLinkedListElements{
    public static void main(String[] args) {
        Solution solution = new P203RemoveLinkedListElements().new Solution();
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
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode temp = head;
        while (temp != null && temp.val == val) {
            temp = temp.next;
            head = head.next;
        }
        if (temp == null) {
            return null;
        }

        ListNode next = temp.next;
        while (next != null) {
            if (next.val == val) {
                next = next.next;
                temp.next = next;
                continue;
            }
            temp = temp.next;
            next = next.next;
        }
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
