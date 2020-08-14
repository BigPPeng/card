//反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。 
//
// 说明: 
//1 ≤ m ≤ n ≤ 链表长度。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL, m = 2, n = 4
//输出: 1->4->3->2->5->NULL 
// Related Topics 链表


package leetcode.editor.cn;
//Java：反转链表 II
public class P92ReverseLinkedListIi{
    public static void main(String[] args) {
        Solution solution = new P92ReverseLinkedListIi().new Solution();
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        print(head1);
        ListNode node = solution.reverseBetween(head1, 4, 5);
//        Solution.NodePair pair = solution.reverse(head1);
        print(node);
//        System.out.println(node);
//        System.out.println(pair.newHead.val);
//        System.out.println(pair.newEnd.val);

        // TO TEST
    }


    static void print(ListNode head2) {
        ListNode temp = head2;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n) {
            return head;
        }
        ListNode segment_1_end = head;
        ListNode segment_2_head = head;


        int index = 1;
        ListNode temp = head;
        while (temp != null) {
            if (index == m - 1) {
                segment_1_end = temp;
            }
            if (index == m) {
                segment_2_head = temp;
                break;
            }
            temp = temp.next;
            index++;
        }
        if (segment_1_end != segment_2_head) {
            segment_1_end.next = null;
        }

        ListNode tempHead = segment_2_head;
        ListNode reverseNext = segment_2_head.next;
        int count = n - m;
        while (reverseNext != null && count > 0) {
            ListNode temp2 = reverseNext.next;
            // 交换
            reverseNext.next = tempHead;
            segment_2_head.next = temp2;
            // 更新
            tempHead = reverseNext;
            reverseNext = temp2;
            count--;
        }

        if (m == 1) {
            return tempHead;
        }
        segment_1_end.next = tempHead;
        return head;
    }

    private ListNode one(ListNode head, int m, int n) {
        if (m == n) {
            return head;
        }
        ListNode segment_1_head = head;
        ListNode segment_1_end = head;

        ListNode segment_2_head = head;
        ListNode segment_2_end = head;

        ListNode segment_3_head = null;


        int index = 1;
        ListNode temp = head;
        while (temp != null) {
            if (index == m - 1) {
                segment_1_end = temp;
            }
            if (index == m) {
                segment_2_head = temp;
            }
            if (index == n) {
                segment_2_end = temp;
            }
            if (index == n + 1) {
                segment_3_head = temp;
                break;
            }
            temp = temp.next;
            index++;
        }

        if (segment_1_end != segment_2_head) {
            segment_1_end.next = null;
        }
        segment_2_end.next = null;
        NodePair reverse = reverse(segment_2_head);
        if (m != 1) {
            segment_1_end.next = reverse.newHead;
        } else {
            segment_1_head = reverse.newHead;
        }
        reverse.newEnd.next = segment_3_head;
        return segment_1_head;
    }

    public NodePair reverse(ListNode head) {
        NodePair pair = new NodePair();
        if (head == null || head.next == null) {
            pair.newHead = head;
            pair.newEnd = head;
            return pair;
        }
        ListNode tempHead = head;
        ListNode reverseNext = head.next;
        while (reverseNext != null) {
            ListNode temp = reverseNext.next;
            // 交换
            reverseNext.next = tempHead;
            head.next = temp;
            // 更新
            tempHead = reverseNext;
            reverseNext = temp;
        }
        pair.newHead = tempHead;
        pair.newEnd = head;
        return pair;
    }

    class NodePair {
        ListNode newHead;
        ListNode newEnd;
    }


}
//leetcode submit region end(Prohibit modification and deletion)




}
