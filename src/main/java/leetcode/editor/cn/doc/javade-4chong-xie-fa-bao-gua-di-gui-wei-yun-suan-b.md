
##### 1，set去重
我们最容易想到的就是set集合去重，从链表的头开始遍历，如果在set集合中有出现重复的元素，我们直接过滤掉
```
    public ListNode removeDuplicateNodes(ListNode head) {
        Set<Integer> set = new HashSet<>();
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            set.add(cur.val);
            if (set.contains(cur.next.val))
                cur.next = cur.next.next;
            else
                cur = cur.next;
        }
        return head;
    }
```
##### 2，双指针
这个也是最low的解决方式，使用两个while循环，一个指向一个固定的值比如m，另一个从m的下一个节点开始扫描，如果遇到和m相同的结点，直接过滤掉，这种两个循环的方式效率很差，看看即可
```
    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur;
            while (temp.next != null) {
                if (temp.next.val == cur.val) {
                    temp.next = temp.next.next;
                } else {
                    temp = temp.next;
                }
            }
            cur = cur.next;
        }
        return head;
    }
```
##### 3，递归的方式
我们还可以参照第一种答案把它改为递归的方式
```
    public ListNode removeDuplicateNodes(ListNode head) {
        return removeDuplicateNodesHelper(head, new HashSet<>());
    }

    public ListNode removeDuplicateNodesHelper(ListNode head, Set<Integer> set) {
        if (head == null)
            return null;
        if (set.contains(head.val))
            return removeDuplicateNodesHelper(head.next, set);
        set.add(head.val);
        head.next = removeDuplicateNodesHelper(head.next, set);
        return head;
    }
```
##### 4，位运算
上面3种方式效率都不高，一直在想能不能找一个效率更高的解决方式，又认真看了一下题，提示中有这样一句话“**链表元素在[0, 20000]范围内**”，于是想了想能不能使用位运算的方式来解决，结果出乎意料，执行时间击败了93.73的用户，内存消耗击败了100%的用户
 [image.png](https://pic.leetcode-cn.com/669f5c44bc5790542abeecd3d95f871013259e360f7b54eb37c2574aa0e59cec-image.png)

```
    public ListNode removeDuplicateNodes(ListNode head) {
        int[] bits = new int[20000 / 32 + 1];
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            bits[cur.val / 32] |= 1 << (cur.val % 32);
            if ((bits[cur.next.val / 32] & (1 << (cur.next.val % 32))) != 0)
                cur.next = cur.next.next;
            else
                cur = cur.next;
        }
        return head;
    }
```

##### 查看更多答案，可扫码关注我微信公众号“**[数据结构和算法](https://img-blog.csdnimg.cn/20190515124616751.jpg)**”