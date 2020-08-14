//编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。 
//
// 示例1: 
//
// 
// 输入：[1, 2, 3, 3, 2, 1]
// 输出：[1, 2, 3]
// 
//
// 示例2: 
//
// 
// 输入：[1, 1, 1, 1, 2]
// 输出：[1, 2]
// 
//
// 提示： 
//
// 
// 链表长度在[0, 20000]范围内。 
// 链表元素在[0, 20000]范围内。 
// 
//
// 进阶： 
//
// 如果不得使用临时缓冲区，该怎么解决？ 
// Related Topics 链表


package leetcode.editor.cn;

import com.google.common.collect.Sets;

import java.util.Set;

//Java：移除重复节点
public class PRemoveDuplicateNodeLcci{
    public static void main(String[] args) {
        Solution solution = new PRemoveDuplicateNodeLcci().new Solution();
        ListNode list = ListNode.getList(17, 23, 15, 30, 21, 5, 20, 14, 5, 9, 22, 6, 22, 20, 14, 12, 4, 21, 27, 5, 4, 21, 27, 0, 14, 21, 17, 27, 6, 12, 28, 17, 29, 8, 17, 13, 7, 26, 7, 31, 27, 8, 31, 19, 5, 23, 9, 0, 22, 0, 26, 30, 14, 10, 6, 8, 16, 24, 15, 21, 2, 3, 5, 15);
//        ListNode list = ListNode.getList(31,31,2,2);
        ListNode.print(list);
        ListNode.print(solution.removeDuplicateNodes(list));
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
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int[] var = new int[20000/32 + 1];
        var[head.val / 32] |= 1 << (head.val % 32);
        ListNode last = head;
        ListNode next = head.next;
        while (next != null) {
            if ((var[next.val / 32] & (1 << (next.val % 32))) != 0) {
                last.next = next.next;
                next = next.next;
                continue;
            }
            var[next.val / 32] |= 1 << (next.val % 32);
            last = next;
            next = next.next;
        }
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
