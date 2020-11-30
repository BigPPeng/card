自己的分析思路和大家分享下，希望能抛砖引玉。


分析：
一，当数组长度小于3时，最多需要调整一次就能满足条件
二，当数组长度大于等于3时，出现前一个元素y大于后一个元素z时，
   如果y的前元素x不存在，让y=z即可；若x存在，会有以下情况

    x    y   z
    1    3   2
    2    3   1
    3    3   1
    2    3   2

要满足条件，需要如下调整：
1，当x<z,让y=z
2，当x>z,让z=y
3, 当x=z,让y=z
综合以上可以得到：当x存在且x>z，就让z=y，否则让y=z
当变更超过2次就不再满足条件

```
class Solution {
    public boolean checkPossibility(int[] nums) {
        if(nums.length < 3){
            return true;
        }
        int count = 0;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i] > nums[i+1]){
                count++;
                if(count > 1){
                    break;
                }
                if(i-1 >=0&&nums[i-1] > nums[i+1]){
                    nums[i+1] = nums[i];
                }else{
                    nums[i] = nums[i+1];
                }
            }
        }
        return count <= 1;
    }
}
```
