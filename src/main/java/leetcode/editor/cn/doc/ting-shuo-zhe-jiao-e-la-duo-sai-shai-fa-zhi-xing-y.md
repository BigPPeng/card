 [无标题.png](https://pic.leetcode-cn.com/0af815d562aac48a09b84409e603ac83fbc6c92b6938369b09f4cf19f69b8737-%E6%97%A0%E6%A0%87%E9%A2%98.png)

我也是看了题解才学习到的，感谢大神们。

```java
class Solution {
    public int countPrimes(int n) {
        int result = 0;
        boolean[] b = new boolean[n];   // 初始化默认值都为 false，为质数标记
        if(2 < n) result++; // 如果大于 2 则一定拥有 2 这个质数
        
        for(int i = 3; i < n; i += 2){  // 从 3 开始遍历，且只遍历奇数
            if(!b[i]){  // 是质数
                for(int j = 3; i * j < n; j += 2){
                    b[i * j] = true;    // 将当前质数的奇数倍都设置成非质数标记 true
                }
                result++;   // 质数个数 +1
            }
        }
        return result;
    }
}
```