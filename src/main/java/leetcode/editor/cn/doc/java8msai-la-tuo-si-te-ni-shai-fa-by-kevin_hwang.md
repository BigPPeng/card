### 解题思路
首先本解法从[@cang-lang-a](/u/cang-lang-a/)同学的代码（https://leetcode-cn.com/problems/count-primes/solution/ting-shuo-zhe-jiao-e-la-duo-sai-shai-fa-zhi-xing-y/）优化而来，采用的算法是埃拉托斯特尼筛法（百度百科https://baike.baidu.com/item/%E5%9F%83%E6%8B%89%E6%89%98%E6%96%AF%E7%89%B9%E5%B0%BC%E7%AD%9B%E6%B3%95/374984）。

埃拉托斯特尼筛法的具体内容就是：
**要得到自然数n以内的全部素数，必须把不大于根号n的所有素数的倍数剔除，剩下的就是素数。**
同时考虑到大于2的偶数都不是素数，所以可以进一步优化成：
# 要得到自然数n以内的全部素数，必须把不大于根号n的所有素数的奇数倍剔除，剩下的奇数就是素数。

那么思路就很清晰了：
1、判断n是否大于2，如果大于2了那么至少有一个小于n的质数2。
2、遍历大于1小于n的所有奇数i，如果i是质数，并且i不大于根号n，那么将i的所有奇数倍都标记成合数，如果i是质数但是i大于根号n那么直接转步骤3。
3、标记完成后质数个数+1。
4、循环结束后返回质数个数。
### 代码

```java
class Solution {
    public int countPrimes(int n) {
        int result = 0, sqrt_n = (int) Math.sqrt(n);
        boolean[] b = new boolean[n];   // 初始化默认值都为 false，为质数标记
        if (2 < n) result++; // 如果大于 2 则一定拥有 2 这个质数
        for (int i = 3; i < n; i += 2) {  // 从 3 开始遍历，且只遍历奇数
            if (!b[i]) {  // 是质数
                if (i <= sqrt_n)//不大于根号n
                    for (int j = i; i * j < n; j += 2)
                        b[i * j] = true;    // 将当前质数的奇数倍都设置成非质数标记 true
                result++;   // 质数个数 +1
            }
        }
        return result;
    }
}
```