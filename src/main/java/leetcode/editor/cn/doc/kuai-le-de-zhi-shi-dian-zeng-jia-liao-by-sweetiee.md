

今天的题目是快乐数，今天你快乐了吗？

#### 链表找环

每个数字都会根据 *各位平方和* 指向另一个数字，所以从任意数字开始进行 *各位平方和* 的迭代操作，就相当于在链表上游走。如果 **无限循环** 但始终变不到 1，那说明肯定是链表游走到了环。

**为什么呢？** ~~（此处应该有图）~~

为什么无限循环就一定是走到了环了呢？而不是链表上的数字越变越大？这里我来给一个证明。

---

设 ![n=\overline{a_{1}a_{2}...a_{m}} ](./p__n=overline{a_{1}a_{2}...a_{m}}_.png) ，*a_{i}* 表示每一位上的数字，并且 *0 <= a_{i} <= 9*。我们有 ![squareSum(n)=\sum_{1}^{m}a_{i}^2 ](./p__squareSum_n_=sum_{1}^{m}_a_{i}^2_.png) 

我们需要证明:
![\lim_{n\to\infty}\frac{squareSum(n)}{n}<1 ](./p__lim_{n_to_infty}_frac{squareSum_n_}{n}___1_.png) 

以下是证明：

![\lim_{n\to\infty}\frac{squareSum(n)}{n} ](./p__lim_{n_to_infty}_frac{squareSum_n_}{n}__.png) 
![=\lim_{n\to\infty,m\to\infty}\frac{\sum_{1}^{m}a_{i}^2}{\overline{a_{1}a_{2}...a_{m}}} ](./p__=_lim_{n_to_infty,_m_to_infty}_frac{sum_{1}^{m}_a_{i}^2}{overline{a_{1}a_{2}...a_{m}}}__.png) 
![\leq\lim_{m\to\infty}\frac{m*9^2}{10^{m-1}} ](./p___leq_lim_{m_to_infty}_frac{m_*_9^2}{10^{m-1}}__.png) 
*  = 0*

注意最后一步放缩，分子向上放（*a_{i}<=9*），分母向下缩（*m* 位数肯定是大于等于 *m* 位数最小的那个数）。

好了，这就证明了不会有一个一直增长的链表，而且在 *n* 非常大的时候，通过 *squareSum(n)* 操作会急剧变小。

---

**链表找环** 是一个很经典的问题了，使用快慢指针可破解。

```Java []
public class Solution {
    public int squareSum(int n) {
        int sum = 0;
        while(n > 0){
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    public boolean isHappy(int n) {
        int slow = n, fast = squareSum(n);
        while (slow != fast){
            slow = squareSum(slow);
            fast = squareSum(squareSum(fast));
        };
        return slow == 1;
    }
}
```

还可以用快慢指针解决的热门题目：[287. 寻找重复数](https://leetcode-cn.com/problems/find-the-duplicate-number/)

---

 [image.png](https://pic.leetcode-cn.com/d499cf34e4d3290a43ae4a8e4064085d1e561ee64e7e116ddf1cd5d6f4209bc5-image.png)

除了链表找环，更进一步的是 **有向图找环**。相比于链表找环，每个点可能指向了多个其他的点。

**有向图找环** 比较经典的做法就是使用 **深度优先搜索**，从每个点出发，按有向图去游走，游走的时候记录当前走过的路径。如果在搜索的过程中遇到了曾经走过的路，那么就找到环了。写过 **深度优先搜索** 的童鞋应该可以很快的写出来，我这里就不贴代码了。

---

 [image.png](https://pic.leetcode-cn.com/d499cf34e4d3290a43ae4a8e4064085d1e561ee64e7e116ddf1cd5d6f4209bc5-image.png)

最近我遇到了一个比较奇怪的场景，用到了 **有向图找环** 这个性质。Java 对于 自定义类的对象的比较，可以实现 Comparable 接口，重写 compareTo 方法，或者 sort 的时候指定 Comparator。但是如果 compareTo 没有写好，会出现 `timsort comparison method violates its general contract` 这样的报错。

因为 Java 的 sort 底层从 jdk 1.7 开始是 TimSort，compareTo 需要满足以下性质：
1. **自反性**：*x, y* 的比较结果和 *y，x* 的比较结果相反。
2. **传递性**：*x>y*，*y>z*，则 *x>z*。
3. **对称性**：*x=y*，则 *x,z* 比较结果和 *y，z* 比较结果相同。

需要按上面 3 条排查一下 compareTo 方法的实现。

其中，如果排序是因为 **传递性** 出错的话，**肯定是因为数组里的元素比较大小的时候成环了**！也就是说数组里有若干个元素，![a_{r_{1}}<a_{r_{2}}<...<a_{r_{p}}<a_{r_{1}},r_{i}\in\[1,n),p<=n ](./p__a_{r_{1}}___a_{r_{2}}___...___a_{r_{p}}___a_{r_{1}},_r_{i}_in__1,n_,_p__=_n_.png) 

那么我怎么找到这个成环的 case，来找出 compareTo 方法的潜在问题呢？就是 **有向图找环**！在排序的时候把比较的元素和比较结果打印出来，再使用 **有向图找环** 就找到原因了！

---

 [image.png](https://pic.leetcode-cn.com/d499cf34e4d3290a43ae4a8e4064085d1e561ee64e7e116ddf1cd5d6f4209bc5-image.png)

Codeforces 上有一道比较有意思的题目 —— 939A Love Triangle，题意如下：

> 有一个 *n* 个元素的数组。*a[i] = x*，表示第 *i* 个人喜欢第 *x* 个人。判断这群人里有没有三角恋。

是不是觉得这道题很简单？不就是找个环？快慢指针搞起来？

no no no，这题用快慢指针就是杀鸡用牛刀。

**只需要判断每个人，他喜欢的那个人喜欢的那个人喜欢的那个人是不是自己**。

代码实现贼简单： *a[a[a[i]]]==i*

---

 [image.png](https://pic.leetcode-cn.com/d499cf34e4d3290a43ae4a8e4064085d1e561ee64e7e116ddf1cd5d6f4209bc5-image.png)

写这么多累了。。上面那道题还可以拓展继续聊的，以后抽空写个文章来说下~~~

---

> 向快乐出发 世界那么大
任风吹雨打 梦总会到达
向快乐出发 别害怕
幸福就像天边 灿烂的晚霞
一起来 向快乐出发 世界那么大
盛开的繁花 甜蜜的解答
向快乐出发 别害怕
不用言语表达 同样的想法
一起来吧

---
大佬们随手【[关注我](https://leetcode-cn.com/u/sweetiee/)】呀～