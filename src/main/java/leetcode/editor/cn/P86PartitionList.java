//给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。 
//
// 你应当保留两个分区中每个节点的初始相对位置。 
//
// 示例: 
//
// 输入: head = 1->4->3->2->5->2, x = 3
//输出: 1->2->2->4->3->5
// 
// Related Topics 链表 双指针


package leetcode.editor.cn;
//Java：分隔链表
public class P86PartitionList{
    public static void main(String[] args) {
        ListNode list = ListNode.getList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Solution solution = new P86PartitionList().new Solution();
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
    public ListNode partition(ListNode head, int x) {
        ListNode b = null;
        ListNode b_ = null;
        ListNode eg = null;
        ListNode eg_ = null;

        ListNode temp = head;
        while (temp != null) {
            if (temp.val < x) {
                if (b == null) {
                    b = temp;
                    b_ = temp;
                } else {
                    b.next = temp;
                    b = b.next;
                }
            } else {
                if (eg == null) {
                    eg = temp;
                    eg_ = temp;
                } else {
                    eg.next = temp;
                    eg = eg.next;
                }
            }
            temp = temp.next;
        }
        if (eg != null) {
            eg.next = null;
        }
        if (b != null) {
            b.next = eg_;
        }
        return b_ == null ? eg_ : b_;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
