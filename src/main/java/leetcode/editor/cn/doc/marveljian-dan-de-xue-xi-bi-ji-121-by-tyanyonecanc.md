### 解题思路
第i天卖出的最大利润，应该是用第i天的价格减去0至i-1天内的最低价。基于此可以很容易写出暴力法。

但是暴力法每次都要从0至i-1中寻找最低价，这是非常冗余的。

我们可以把0至i-1天内的最低价用变量buyPrice记录下来，当计算到第i+1天卖出的最大利润时，我们需要的是0至i天内的最低价，其实就是`Math.min(buyPrice, prices[i])`，不需要再从头找了，只需要用第i天价格和0至i-1天内的最低价比较即可得到0至i天内的最低价。

这样只需一次遍历每一天的利润，即可得到最终的利润。

### 代码

```java
class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0, buyPrice = Integer.MAX_VALUE;
        for(int i : prices) {
            profit = Math.max(profit, i - buyPrice);
            buyPrice = Math.min(buyPrice, i);
        }
        return profit;
    }
}
```