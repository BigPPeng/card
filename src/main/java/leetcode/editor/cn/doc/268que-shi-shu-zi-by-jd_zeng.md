### 解题思路
    可以将数组元素存放到哈希表中，从0开始枚举到数组长度(nums.length)，看看什么数不在哈希表中，返回即可

### 代码

```java
class Solution {
    public int missingNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            set.add(nums[i]);
        }
        for(int i = 0; i<= nums.length; i++){
            if(!set.contains(i)){
                return i;
            }
        }
    return 0;
    }
}
```