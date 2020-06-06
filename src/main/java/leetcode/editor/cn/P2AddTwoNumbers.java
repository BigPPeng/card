//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。 
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 示例： 
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
// 
// Related Topics 链表 数学

 
package leetcode.editor.cn;
//Java：两数相加
public class P2AddTwoNumbers{
    public static void main(String[] args) {
        Solution solution = new P2AddTwoNumbers().new Solution();
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int temp = 0;
        ListNode first = null;
        ListNode tempNode = null;
        while (l1 != null || l2 != null || temp == 1) {
            int a = 0;
            if (l1 != null) {
                a = l1.val;
                l1 = l1.next;
            }
            int b = 0;
            if (l2 != null) {
                b = l2.val;
                l2 = l2.next;
            }
            int sum = a + b + temp;
            ListNode second = new ListNode(sum >= 10 ? sum - 10 : sum);
            temp = sum >= 10 ? 1 : 0;
            if (first == null) {
                first = second;
                tempNode = first;
            }  else {
                tempNode.next = second;
                tempNode = second;
            }
        }
        return first;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
}
}