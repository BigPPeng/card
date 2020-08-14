### 解题思路
两个重复数字异或后结果为0
循环，计算0-n的异或值，顺便计算nums数组的异或值，两个异或值再异或，就为未出现的值
### 代码

```java
class Solution {
    public int missingNumber(int[] nums) {
        int num1 = 0,num2 = 0;
        for(int i=0; i<nums.length; i++){
            num1 ^= i+1;
            num2 ^= nums[i];
        }
        return num1 ^ num2;
    }
}
```