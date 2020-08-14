//编写一个程序，找到两个单链表相交的起始节点。 
//
// 如下面的两个链表： 
//
// 
//
// 在节点 c1 开始相交。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, s
//kipB = 3
//输出：Reference of the node with value = 8
//输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1
//,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
// 
//
// 
//
// 示例 2： 
//
// 
//
// 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB =
// 1
//输出：Reference of the node with value = 2
//输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4
//]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
// 
//
// 
//
// 示例 3： 
//
// 
//
// 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
//输出：null
//输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而
// skipA 和 skipB 可以是任意值。
//解释：这两个链表不相交，因此返回 null。
// 
//
// 
//
// 注意： 
//
// 
// 如果两个链表没有交点，返回 null. 
// 在返回结果后，两个链表仍须保持原有的结构。 
// 可假定整个链表结构中没有循环。 
// 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。 
// 
// Related Topics 链表


package leetcode.editor.cn;
//Java：相交链表
public class P160IntersectionOfTwoLinkedLists{
    public static void main(String[] args) {
        Solution solution = new P160IntersectionOfTwoLinkedLists().new Solution();
        ListNode a = ListNode.getList(0, 9, 1);
        ListNode b = ListNode.getList(3);
        ListNode node0 = new ListNode(0);
        ListNode node9 = new ListNode(9);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node4 = new ListNode(4);
//        node0.next = node9;
//        node9.next = node1;
//        node1.next = node2;
//        node2.next = node4;
        ListNode node3 = new ListNode(3);
//        node3.next = node2;
        ListNode intersectionNode = solution.getIntersectionNode(node3, node3);
        System.out.println(intersectionNode == null ? "null" : intersectionNode.val);
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {


    /**
     * 1、如果两个链表存在一个为null，则不相交。
     * 2、遍历两个链表，得到长度。并且的到尾指针。
     *      直接判断尾结点是否是同一个节点，提前判断范湖
     * 3、长链表后移差值个位置，如果两个链表有交点，则必在长链表与短链表相同长度的位置以后
     */

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int headALength = 1;
        ListNode tempA = headA;
        while (tempA.next != null) {
            tempA = tempA.next;
            headALength++;
        }

        int headBLength = 1;
        ListNode tempB = headB;
        while (tempB.next != null) {
            tempB = tempB.next;
            headBLength++;
        }
        if (tempA != tempB) {
            return null;
        }
        int difference = headBLength - headALength;
        tempA = headA;
        tempB = headB;
        if (difference >= 0) {
            while (difference > 0) {
                tempB = tempB.next;
                difference--;
            }
        } else {
            difference = -difference;
            while (difference > 0) {
                tempA = tempA.next;
                difference--;
            }
        }
        while (tempA != null && tempB != null) {
            if (tempA == tempB) {
                return tempA;
            }
            tempA = tempA.next;
            tempB = tempB.next;
        }
        return null;
    }


//    private ListNode re(ListNode head,ListNode next) {
//        if (next == null) {
//            return head;
//        }
//        ListNode temp = next.next;
//        next.next = head;
//        return re(next,temp);
//    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
