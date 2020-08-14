//合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。 
//
// 示例: 
//
// 输入:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//输出: 1->1->2->3->4->4->5->6 
// Related Topics 堆 链表 分治算法


package leetcode.editor.cn;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

//Java：合并K个排序链表
public class P23MergeKSortedLists{
    public static void main(String[] args) {
        Solution solution = new P23MergeKSortedLists().new Solution();
//        ListNode merge = solution.merge(list1, list2);
//        ListNode.print(merge);
        ListNode list = ListNode.getList(1, 2);
        ListNode list1 = ListNode.getList(2, 3);
        ListNode list2 = ListNode.getList(4, 5);
        ListNode list3 = ListNode.getList(4, 5);
        ListNode list4 = ListNode.getList(4, 5);
        ListNode list5 = ListNode.getList(4, 5, 8);
        ListNode[] a = new ListNode[]{list,list1,list2,list3,list4,list5};
        ListNode node = solution.mergeKLists(a);
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
    public ListNode mergeKLists(ListNode[] lists) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
//
//        ListNode list = ListNode.getList(1, 2);
//        ListNode list1 = ListNode.getList(2, 3);
//        ListNode list2 = ListNode.getList(4, 5);
//        ListNode[] a = new ListNode[]{list,list1,list2};

        return forkJoinPool.invoke(new Merge(0,lists.length - 1,lists));
    }

    class Merge extends RecursiveTask<ListNode> {
        int begin;
        int end;
        ListNode[] lists;

        public Merge(ListNode[] lists) {
            this.lists = lists;
        }

        public Merge(int begin, int end, ListNode[] lists) {
            this.begin = begin;
            this.end = end;
            this.lists = lists;
        }

        @Override
        protected ListNode compute() {
            if ((end - begin + 1) > 3) {
                int middleIndex =  (end - begin + 1) / 2;

                Merge merge1 = new Merge(begin,middleIndex,lists);
                Merge merge2 = new Merge(middleIndex+1,end,lists);
                merge1.fork();
                merge2.fork();
                return merge(merge1.join(), merge2.join());
            } else {
                if (lists.length == 1) {
                    return lists[begin];
                } else if (lists.length == 2) {
                    return merge(lists[begin],lists[end]);
                } else {
                    return merge(lists[begin],merge(lists[begin + 1],lists[end]));
                }
            }
        }

        public ListNode merge(ListNode one, ListNode two) {
            if (one == null) {
                return two;
            }
            if (two == null) {
                return one;
            }
            ListNode newHead = null;
            ListNode temp = null;
            while (one != null && two != null) {
                if (newHead == null) {
                    if (one.val < two.val) {
                        newHead = one;
                        one = one.next;
                    } else {
                        newHead = two;
                        two = two.next;
                    }
                    temp = newHead;
                    continue;
                }
                if (one.val < two.val) {
                    temp.next = one;
                    temp = temp.next;
                    one = one.next;
                } else {
                    temp.next = two;
                    temp = temp.next;
                    two = two.next;
                }
            }
            if (one != null) {
                temp.next = one;
            } else {
                temp.next = two;
            }
            return newHead;
        }

    }


    private ListNode two(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        if (lists.length == 2) {
            return merge(lists[0],lists[1]);
        }
        ListNode res = lists[0];
        for (int i = 1; i < lists.length; i++) {
            res = merge(res,lists[i]);
        }
        return res;
    }


    public ListNode merge(ListNode one, ListNode two) {
        if (one == null) {
            return two;
        }
        if (two == null) {
            return one;
        }
        ListNode newHead = null;
        ListNode temp = null;
        while (one != null && two != null) {
            if (newHead == null) {
                if (one.val < two.val) {
                    newHead = one;
                    one = one.next;
                } else {
                    newHead = two;
                    two = two.next;
                }
                temp = newHead;
                continue;
            }
            if (one.val < two.val) {
                temp.next = one;
                temp = temp.next;
                one = one.next;
            } else {
                temp.next = two;
                temp = temp.next;
                two = two.next;
            }
        }
        if (one != null) {
            temp.next = one;
        } else {
            temp.next = two;
        }
        return newHead;
    }


    private ListNode one(ListNode[] lists) {
        ListNode newHead = new ListNode(0);
        ListNode now = newHead;
        while (!isAllNull(lists)) {
            Integer min = getMin(lists);
            if (min == null) {
                break;
            }
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) {
                    continue;
                }
                if (lists[i].val == min) {
                    now.next = lists[i];
                    now = now.next;
                    lists[i] = lists[i].next;
                }
            }
        }
        return newHead.next;
    }

    private Integer getMin(ListNode[] heads) {
        Integer min = null;
        for (ListNode head : heads) {
            if (head == null) {
                continue;
            }
            if (min == null || min > head.val) {
                min = head.val;
            }
        }
        return min;
    }

    private boolean isAllNull(ListNode[] heads) {
        for (ListNode head : heads) {
            if (head != null) {
                return false;
            }
        }
        return false;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
