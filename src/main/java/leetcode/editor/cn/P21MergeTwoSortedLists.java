//将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 示例： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
// 
// Related Topics 链表


package leetcode.editor.cn;

//Java：合并两个有序链表
public class P21MergeTwoSortedLists {
    public static void main(String[] args) {
        Solution solution = new P21MergeTwoSortedLists().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            ListNode head = null;
            ListNode temp = null;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    if (head == null) {
                        head = l1;
                        temp = l1;
                    } else {
                        temp.next = l1;
                        temp = temp.next;
                    }
                    l1 = l1.next;
                } else {
                    if (head == null) {
                        head = l2;
                        temp = l2;
                    } else {
                        temp.next = l2;
                        temp = temp.next;
                    }
                    l2 = l2.next;
                }
            }

            temp.next = (l1 == null) ? l2 : l1;
            return head;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
