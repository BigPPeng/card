### 解题思路
(归并排序) 时间：O(nlogn)，空间O(1)时间：*O(nlogn)*，空间*O(1)*
自顶向下递归形式的归并排序，由于递归需要使用系统栈，递归的最大深度是 *logn*，所以需要额外 *O(logn)* 的空间。
所以我们需要使用自底向上非递归形式的归并排序算法。
基本思路是这样的，总共迭代 *logn* 次：

第一次，将整个区间分成连续的若干段，每段长度是2：*[a0,a1],*[a2,a3],*…[an−1,an−1]*， 然后将每一段内排好序，小数在前，大数在后；
第二次，将整个区间分成连续的若干段，每段长度是4：*[a0,…,a3]*,*[a4,…,a7]*,*…*[an−4,…,an−1]$，然后将每一段内排好序，这次排序可以利用之前的结果，相当于将左右两个有序的半区间合并，可以通过一次线性扫描来完成；
依此类推，直到每段小区间的长度大于等于 n 为止；

另外，当 n 不是2的整次幂时，每次迭代只有最后一个区间会比较特殊，长度会小一些，遍历到指针为空时需要提前结束。

时间复杂度分析：整个链表总共遍历 *logn* 次，每次遍历的复杂度是 $O(n)$，所以总时间复杂度是 *O(nlogn)*。
空间复杂度分析：整个算法没有递归，迭代时只会使用常数个额外变量，所以额外空间复杂度是 *O(1)*

参考大雪菜的讲解,原文链接如下，把C++的代码转化为Java代码，并且添加了注释。如果有不对的，请多指教。

\*\*\*是ac  wing。
> 作者：yxc
> 链接：https://www.***.com/solution/leetcode/content/408/


### 代码

```java
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
        // 归并排序
        int n = 0;
        // 走到null，刚好走链表的长度
        for(ListNode i = head; i != null; i=i.next) n++;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // 循环一下
        // 第一层循环，分块，从1个一块，2个一块，4个一块，直到n个一块，
        for(int i = 1; i < n; i = 2*i){
            ListNode begin = dummy;
            // 开始归并
            // j + i < n 表示只有一段就不归并了，因为已经是排好序的
            for(int j = 0; j + i < n; j = j + 2 * i){
                // 两块，找两块的起始节点
                // 开始都指向第一块的起点
                // 然后second走n步指向第二块的起点
                ListNode first = begin.next, second = first;
                for(int k = 0; k < i; k++) second = second.next;

                // 遍历第一块和第二块进行归并
                // 第一块的数量为i
                // 第二块的数量为i也可能小于i，所以循环条件要加一个second != null
                int f = 0, s = 0;
                while(f < i && s < i && second != null){
                    if(first.val < second.val){
                        begin.next = first;
                        begin = begin.next;
                        first = first.next;
                        f++;
                    }else{
                        begin.next = second;
                        begin = begin.next;
                        second = second.next;
                        s++;
                    }
                }
                // 归并之后可能又多余的没有处理
                while(f < i){
                    begin.next = first;
                    begin = begin.next;
                    first = first.next;
                    f++;

                }
                while(s < i && second != null){
                    begin.next = second;
                    begin = begin.next;
                    // second已经更新到下一块的起点了
                    second = second.next;
                    s++;
                }

                // 更新begin
                // begin.next 指向下一块的起点
                begin.next = second;
            }
        }
        return dummy.next;

    }
}
```
> 我的公众号：GitKid。 每日分享LeetCode, 让你走在路上坐在车上也能看算法题，欢迎大家扫码关注。

 [微信公众号.jpg](https://pic.leetcode-cn.com/11bf35f0eaa15ff494ea0193750c210c3b32bd9b27ced15e747d458217c1c455-%E5%BE%AE%E4%BF%A1%E5%85%AC%E4%BC%97%E5%8F%B7.jpg){:align=center}
