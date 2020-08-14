### 解题思路
首先利用函数对数组进行排序，一步完成
紧接着调用ArrayList，注意泛型为Integer
利用if语句判断接下来要添加的元素在数组中是否存在，如不存在，用list.add添加
最后因为默认排序是从小到大排序
所以我们在输出的时候首先要判断数组内是否存在3个或3个以上不重复的元素，如果小于3个
也就是说不存在第3大的数字，那就输出最后1个（list.size()-1）,否则输出倒数第3个

### 代码

```java
class Solution {
    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0;i < nums.length;i++){
            if(!list.contains(nums[i])){
                list.add(nums[i]);
            }
        }
        if(list.size() < 3){
            return list.get(list.size()-1);
        }
        return list.get(list.size()-3);

    }
}
```