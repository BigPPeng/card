//在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。 
//
// 示例 1: 
//
// 输入: 4->2->1->3
//输出: 1->2->3->4
// 
//
// 示例 2: 
//
// 输入: -1->5->3->4->0
//输出: -1->0->3->4->5 
// Related Topics 排序 链表


package leetcode.editor.cn;
//Java：排序链表
public class P148SortList{
    public static void main(String[] args) {
        Solution solution = new P148SortList().new Solution();
        ListNode list1 = ListNode.getList(1,7,4,9,10,122,23,24,26,10,12,21);
        ListNode.print(list1);
        ListNode node = solution.sortList(list1);
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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int length = length(head);
        return sort(head, length);
    }


    public ListNode sort(ListNode head, int length) {
        if (length == 1) {
            return head;
        }
        if (length == 2) {
            ListNode spilt = spilt(head, 1);
            return mergeSort(head, spilt);
        }
        int i = length / 2;
        int j = length - i;
        ListNode spilt = spilt(head, i);

        ListNode sort1 = sort(head, i);
        ListNode sort2 = sort(spilt, j);
        return mergeSort(sort1, sort2);
    }

    public int length(ListNode node) {
        if (node == null) {
            return 0;
        }
        ListNode temp = node;
        int count = 0;
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }

    public ListNode mergeSort(ListNode first, ListNode second) {
        if (first == null && second == null) {
            return null;
        }
        ListNode temp = new ListNode(-1);
        ListNode head = temp;
        while (first != null && second != null) {
            if (first.val < second.val) {
                temp.next = first;
                temp = temp.next;
                first = first.next;
            } else {
                temp.next = second;
                temp = temp.next;
                second = second.next;
            }
        }
        if (first != null) {
            temp.next = first;
        }
        if (second != null) {
            temp.next = second;
        }
        return head.next;
    }


    public ListNode spilt(ListNode listNode, int firstLength) {
        if (listNode == null
                || listNode.next == null
                || firstLength == 0) {
            return listNode;
        }

        ListNode temp = listNode;
        ListNode pre;
        while (firstLength > 1 && temp != null) {
            temp = temp.next;
            firstLength--;
        }
        if (temp == null) {
            return null;
        }
        pre = temp.next;
        temp.next = null;
        return pre;
    }




    private ListNode bubbleSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = head.next;
        ListNode headTemp = head;
        headTemp.next = null;
        while (temp != null) {
            ListNode next = temp.next;
            temp.next = null;

            ListNode headSort = headTemp;
            ListNode headSortLast = headTemp;
            while (headSort != null && temp.val > headSort.val) {
                if (headSort == headSortLast) {
                    headSort = headSort.next;
                    continue;
                }
                headSort = headSort.next;
                headSortLast = headSortLast.next;
            }
            if (headSort == headTemp) {
                temp.next = headSort;
                headTemp = temp;
            } else {
                headSortLast.next = temp;
                temp.next = headSort;
            }
            temp = next;
        }
        return headTemp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
