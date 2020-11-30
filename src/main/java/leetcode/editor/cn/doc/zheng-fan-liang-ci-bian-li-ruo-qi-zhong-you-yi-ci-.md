### 解题思路
正向遍历，若nums[i] > nums[i+1],则nums[i+1] = nums[i]
之所以需要反向遍历，是因为有可能{4,2,3}情况
若正向遍历，需要修改两次，变成{4，4，4}导致判断失败
而反向遍历的时候只需要把较大的数等于较小的数
若num[i] < num[i-1],则num[i-1] = num[i]，数组变为{2,2,3}，判断成功

### 代码

```java
class Solution {
    public boolean checkPossibility(int[] nums) {
        if (nums.length == 1) {
			return true;
		}
        //定义两个计数器，分别记录正反遍历所修改的次数
        int positive = 0,negative = 0;
		int[] num = new int[nums.length];
        //数组copy，不能使用 int[] num = nums;
		System.arraycopy(nums, 0, num, 0, nums.length);
		for (int i = 0; i < nums.length - 1 && positive < 2; i++) {
			if (nums[i] > nums[i+1]) {
				positive++;
				nums[i+1] = nums[i];
			}
		}
        //如果正反向遍历成功了，就无需反向遍历了
        if(positive>=2){
            for (int i = num.length - 1; i > 0 && negative < 2; i--) {
                if (num[i] < num[i-1]) {
                    negative++;
                    num[i-1] = num[i];
                }
            }
        }
		return negative <= 1;
    }
}
```