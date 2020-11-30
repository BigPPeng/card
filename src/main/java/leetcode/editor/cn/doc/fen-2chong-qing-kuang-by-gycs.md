### 解题思路
其实说的简单点，有2种情况：
1. 升序序列中，突然有个数字小了，则nums[i]=nums[i-1]
2. 升序序列中，突然有个数字大了，则nums[i-1]=nums[i]

### 代码

```java
class Solution {
    public boolean checkPossibility(int[] nums) {
        int fix=0;
        for (int i=1;i<nums.length && fix<=1;i++){
            if (nums[i]>=nums[i-1]){
                continue;
            }
            fix++;
            if (i-2>=0&&(nums[i]<nums[i-2])){//突然有个数字小了
                nums[i]=nums[i-1];
            }else {//突然有个数字大了
                nums[i-1]=nums[i];
            }
        }
        return fix<=1;
    }
}
```