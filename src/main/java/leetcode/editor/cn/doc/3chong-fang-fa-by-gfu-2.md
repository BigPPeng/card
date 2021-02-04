# 最原始的思路
遍历整个数组，对记录每个数值出现的次数(利用`HashMap`，其中`key`为数值，`value`为出现次数)；
接着遍历`HashMap`中的每个`Entry`，寻找`value`值`> nums.length / 2`的`key`即可。
# 代码
```java [-stream]
class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Long> map = Arrays.stream(nums).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        long limit = nums.length >> 1;
        for (Map.Entry<Integer, Long> entry : map.entrySet())
            if (entry.getValue() > limit)
                return entry.getKey();
        return -1;
    }
}
```
```java [-merge]
class Solution {
    public int majorityElement(int[] nums) {
        int limit = nums.length >> 1;
        HashMap<Integer, Integer> map = new HashMap<>(limit);
        for (int num : nums)
            map.merge(num, 1, (o_val, n_val) -> o_val + n_val);
        for (Map.Entry<Integer, Integer> entry : map.entrySet())
            if (entry.getValue() > limit)
                return entry.getKey();
        return -1;
    }
}
```

# 排序思路
既然数组中有出现次数`> ⌊ n/2 ⌋`的元素，那排好序之后的数组中，**相同元素**总是**相邻**的。
即存在长度`> ⌊ n/2 ⌋`的一长串 由**相同元素**构成的**连续子数组**。
举个例子：
无论是`1 1 1 2 3`，`0 1 1 1 2`还是`-1 0 1 1 1`，数组中间的元素总是“多数元素”，毕竟它长度`> ⌊ n/2 ⌋`。

# 代码
```java [-Arrays.sort()]
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length >> 1];
    }
}
```
```java [-topK]
class Solution {
    public int majorityElement(int[] nums) {
        int len = (nums.length + 1) >> 1;
        PriorityQueue<Integer> pQueue = new PriorityQueue<>(len, Comparator.comparingInt(item -> -item));
        for (int num : nums) {
            pQueue.offer(num);
            if (pQueue.size() > len)
                pQueue.poll();
        }
        return pQueue.poll();
    }
}
```


# 摩尔投票法思路
候选人(`cand_num`)初始化为`nums[0]`，票数`count`初始化为`1`。
当遇到与`cand_num`相同的数，则票数`count = count + 1`，否则票数`count = count - 1`。
当票数`count`为`0`时，更换候选人，并将票数`count`重置为`1`。
遍历完数组后，`cand_num`即为最终答案。

为何这行得通呢？
投票法是遇到相同的则`票数 + 1`，遇到不同的则`票数 - 1`。
且“多数元素”的个数`> ⌊ n/2 ⌋`，其余元素的个数总和`<= ⌊ n/2 ⌋`。
因此“多数元素”的个数 `-` 其余元素的个数总和 的结果 肯定 `>= 1`。
这就相当于每个“多数元素”和其他元素 两两相互抵消，抵消到最后肯定还剩余**至少`1`个**“多数元素”。

无论数组是`1 2 1 2 1`，亦或是`1 2 2 1 1`，总能得到正确的候选人。

# 代码
```java [-Java]
class Solution {
    public int majorityElement(int[] nums) {
        int cand_num = nums[0], count = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (cand_num == nums[i])
                ++count;
            else if (--count == 0) {
                cand_num = nums[i];
                count = 1;
            }
        }
        return cand_num;
    }
}
```

# 进阶
理解了摩尔投票法后，良心推荐一道进阶的题目：[求众数Ⅱ](https://leetcode-cn.com/problems/majority-element-ii/)