**分析**

- 根据题目特点，可以把数组中的`元素`与`索引`建立`一一对应`的关系。因为索引是确定的`0`到`n-1`,一个也不缺，而数组的元素不确定，少了哪个也不知道。
- 既然两者是一一对应的关系，那么我们对数组中的每个`元素`对应的`索引`做个标记；
- 然后再对`索引`进行一次遍历，那么`不存的元素`就不会对它`对应的索引`进行比较，由此可查找出这些`不存在的元素`。

**思路**

1. 遍历每个元素，对索引进行标记
    - 将对应索引位置的值变为负数；
2. 遍历下索引，看看哪些索引位置上的数不是负数的。
    - 位置上不是负数的索引，对应的元素就是不存在的。


**代码**

```java []
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //用来存放结果
        List<Integer> res = new ArrayList<>(); 
        //1. 遍历下数组的元素，对对应的索引位置的元素作标记
        int len = nums.length;
        for(int i = 0; i < len; i++){
            int num = Math.abs(nums[i]);  //由于数组的元素有可能被*-1，所以取绝对值
            int index = num - 1;
            if(nums[index] > 0){
                nums[index] *= -1;
            }
        }      
        // 寻找没有标记的索引位置
        for(int i = 0; i < len; i++){
            if(nums[i] > 0){
                int num = i + 1;  //将索引转化为对应的元素
                res.add(num);
            }     
        }
        return res;
    }
}
```
由于数组的元素不存在`0`，所以也就不用考虑`0`乘以`-1`的情况了。


![微信图片_20200920111253.jpg](https://pic.leetcode-cn.com/1600685773-NfJnYh-%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20200920111253.jpg)

