### 解题思路
首先这组代码参考了官方答案下面的评论，如有侵权，我立即删除，同时我对部分代码将自己的理解写在了上面

### 代码

```java
class Solution {
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for(int i = 1;i < nums.length;i++){
            if(nums[i -1] <= nums[i]){  
                continue; 
            }
            count++;//这句代码不要放在条件判断的内部，一定要确保只要不满足非递减就必须执行这句代码
            if(i - 2 >= 0 && nums[i-2] > nums[i]){//这种情况是突然遇到了一个比前一个数小的数字，同时小于它前一个的前一个数字
                nums[i] = nums[i - 1];//这句代码是更新第i个位置的数据，保证其非递减行，但不要担心，因为在这之前，计数器已经+1了
            }
            else{//这种情况是突然遇到了一个比前一个数小的数字，但是大于它前一个的前一个数字
                nums[i-1] = nums[i];//这句代码是更新第i个位置的数据，保证其非递减行，但不要担心，因为在这之前，计数器已经+1了
            }
        }
        return count <= 1;

    }
}
```