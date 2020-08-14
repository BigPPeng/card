## 概述
___
归并排序：指的是将两个已经排序的序列合并成一个序列的操作，因此采用分治法解决该问题。

 **分治法关键有两点:**

- 分割：把当前序列平均分割成两半；
- 合并：在保持元素顺序的同时将上一步得到的子序列合并到一起；

 **迭代法步骤如下:**

- 将序列每相邻两个数字进行归并操作，形成**N/2**个序列，排序后每个序列包含2个元素；
- 若此时序列数不是1个则将上述序列再次归并，形成**N/4**个序列，排序后每个序列包含4个元素；
- 重复步骤2，直到所有元素排序完毕，即序列数为1；

PS：上述假设N为偶数，N为奇数时，最后一个序列元素个数少一个

 **分隔：根据步长分隔链表**

```
private ListNode split(ListNode head, int step) {
    if (head == null) return null;

    for (int i = 1; head.next != null && i < step; i++) {
        head = head.next;
    }

    ListNode right = head.next;
    head.next = null;
    return right;
}
```

 **合并：合并两个有序链表**
```
private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode sentry = new ListNode(-1);
    ListNode curr = sentry;

    while (l1 != null && l2 != null) {
        if (l1.val < l2.val) {
            curr.next = l1;
            l1 = l1.next;
        } else {
            curr.next = l2;
            l2 = l2.next;
        }

        curr = curr.next;
    }

    curr.next = l1 != null ? l1 : l2;
    return sentry.next;
}
```

**完整代码**
```
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 获取链表长度
        int len = listNodeLength(head);

        // 哨兵节点，也有叫傀儡节点（处理链表问题的一般技巧）
        ListNode sentry = new ListNode(-1);
        sentry.next = head;

        // 循环 log n 次
        for (int i = 1; i < len; i <<= 1) {
            ListNode prev = sentry;
            ListNode curr = sentry.next;
            // 循环 n 次
            while (curr != null) {
                ListNode left = curr;
                ListNode right = split(left, i);
                curr = split(right, i);
                prev.next = mergeTwoLists(left, right);

                while (prev.next != null) {
                    prev = prev.next;
                }
            }
        }

        return sentry.next;
    }

    // 根据步长分隔链表
    private ListNode split(ListNode head, int step) {
        if (head == null) return null;

        for (int i = 1; head.next != null && i < step; i++) {
            head = head.next;
        }

        ListNode right = head.next;
        head.next = null;
        return right;
    }

    // 获取链表的长度
    private int listNodeLength(ListNode head) {
        int length = 0;
        ListNode curr = head;

        while (curr != null) {
            length++;
            curr = curr.next;
        }

        return length;
    }

    // 合并两个有序链表
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode sentry = new ListNode(-1);
        ListNode curr = sentry;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }

            curr = curr.next;
        }

        curr.next = l1 != null ? l1 : l2;
        return sentry.next;
    }
}
```
