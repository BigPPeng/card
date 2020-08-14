🙋今日打卡 100/100

### 一、合并 2 个区间
2 个区间的关系有以下 6 种，但是其实可以变成上面 3 种情况（只需要假设 **第一个区间的起始位置 ![\leq ](./p__leq_.png)  第二个区间的起始位置**，如果不满足这个假设，交换这两个区间）。这 3 种情况的合并的逻辑都很好写。

 [image.png](https://pic.leetcode-cn.com/91d75169b1cdb15560d361f8cb7050adfe7906c955afbe8846b92d1beba8a0d7-image.png)


### 二、合并 n 个区间
先根据区间的起始位置排序，再进行  *n -1* 次 **两两合并**。



代码：

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval: intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }
}
```


### ❤️ 大佬们随手赏个「爱心赞」吧，如果能随手关注下我的公众号【[甜姨的奇妙冒险](https://pic.leetcode-cn.com/304599b006dd41bcf2042715f31a2dc4fbdc4cf9748a11a81d8978ea1e839956-wxgzh.jpeg)】和 知乎专栏【[甜姨的力扣题解](https://zhuanlan.zhihu.com/c_1224355183452614656)】就更好了啊 ▄█▔▉●

### 🔥昨天的打卡题「01 矩阵」，已在公众号和专栏更新，一文秒懂多源BFS，求戳！👆



