首先，判断是否存在重复元素的最好方法有两种，1、O(nlogn)排序，2、集合（不存在相同元素）
1、排序后判断相邻元素是否重复
```
class Solution {
    public boolean containsDuplicate(int[] nums) {
        int n=nums.length;
        Arrays.sort(nums);//排序复杂度O(nlogn)
        for(int i=0;i<n-1;i++){
            if(nums[i]==nums[i+1])
                return true;
        }
        return false;
    }
}
```
2、使用SET的特性
```
class Solution {
    public boolean containsDuplicate(int[] nums) {
        int num = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) {
                return true;
            }
            set.add(i);
        }
        return false;
    }
}
```
3、观察答案里面有个特殊的解法
其采取类似普通的探查方法，但是其在探查的过程中引入上下界，如果待判断元素在上下界中则进行线性探查，
不再这拓宽上下界，不用进行探查（类似剪枝，剪去无需判断的元素）
但是！此方法的**效率是不稳定的**，其依赖于数组内元素的分布情况，在极端情况下其会**退化为普通的探查**
代码如下
```
class Solution {
    public boolean containsDuplicate(int[] nums) {
        int length = nums.length;
        if(length<=1) return false;
        int min = nums[0];
        int max = nums[0];
        for(int i=1;i<length;i++){
            if(nums[i]>max){
                max = nums[i];
                continue;
            }
            if(nums[i]<min){
                min = nums[i];
                continue;
            }
            if(nums[i]==max || nums[i]==min){
                return true;
            }
            for(int j=0;j<i;j++){
                if(nums[j]==nums[i]) return true;
            }
        }
        return false;
    }
}
```


