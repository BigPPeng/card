### 方法一：遍历合并

- 1ms
- 枚举起点 `i`、终点 `j`，两两判断是否可合并
- 若可合并则合入后者 `intervals[j]`，前者 `intervals[i]` 清空
- 时间复杂度分析
  - 最好 *O(n)*，从左到右相邻两个都存在可合并的情况
  - 最坏 *O(n^2)*，所有区间都不可合并
  - 加权平均时间复杂度 *O(n^2)*
    - 不考虑合并操作，只判断是否可以合并，并记录合并个数 `cnt`
      - 对于 `[i0, i1]` 与 `[j0, j1]` 的比较涉及 `i0` vs `j1`、`i1` vs `j0` 的两两比较
      - 假设两数比较的结果要么大于等，要么小于（暂不考虑等于的情况），则概率各为 *1/2*
      - 假设任意随机区间（`i0 <= i1` 区间有效，且经过 `Collections.shuffle()` 随机排序），可能存在合并的概率 *P* 为 *1/2 * 1/2 = 1/4*
      - 根据概率乘法
        - 对 `intervals[i]` 与 `intervals[i + 1]` 比较结果为可合并的概率为 *1/4*，需要遍历 `1` 个元素
        - 对 `intervals[i]` 与 `intervals[i + 2]` 比较结果为可合并的概率为 *3/4*1/4*，*3/4* 表示第 `i + 1` 不存在合并的概率，需要遍历 `2` 个元素
        - 对 `intervals[i]` 与 `intervals[i + 3]` 比较结果为可合并的概率为 *3/4*3/4*1/4*，需要遍历 `3` 个元素
        - 对 `intervals[i]` 与 `intervals[n - 1]` 比较结果为可合并的概率为 *(3/4)^{n - 2} * 1/4*，需要遍历 `n-1` 个元素
        - 对 `intervals[i]` **所有元素不可合并**的概率为 *(3/4)^{n - 1}*，需要遍历 `n-1` 个元素
        - 以此类推，将 **所有概率与遍历元素个数的乘积求和** *1/4 * 1 + 3/4*1/4*2 + ... + (3/4)^{n - 2} * 1/4 * (n-1) + (3/4)^{n - 1} * (n - 1)*，即为 **加权平均时间复杂度**
        - 结果实在是搞不出来了…求饶（求大佬指导），用大O表示应该还是 *O(n^2)* 级，但肯定会比 *O(n^2)*小，毕竟未必会最坏的嘛~
    - 考虑合并操作，且出现了合并情况时，越向后合并，则后者的区间涵盖越大，越有可能发生更多的合并，即 *P* 会越来越大，越后面的元素会越快
  - 综上，此方法较依赖于数据的分布，时间复杂度不稳定。

```java
public int[][] merge(int[][] intervals) {
    int len = intervals.length;
    if (len < 2) return intervals;

    int cnt = 0; // 合并次数
    for (int i = 0; i < len - 1; i++) {
        for (int j = i + 1; j < len; j++) {
            if (intervals[i][0] <= intervals[j][1] && intervals[i][1] >= intervals[j][0]) {
                intervals[j][0] = Math.min(intervals[j][0], intervals[i][0]);
                intervals[j][1] = Math.max(intervals[j][1], intervals[i][1]);
                intervals[i] = null; // 清空前者
                cnt++;
                break;
            }
        }
    }

    int[][] res = new int[len - cnt][2]; // len - cnt 合并后个数
    int ri = 0;
    for (int[] pair : intervals) {
        if (pair != null) res[ri++] = pair;
    }
    return res;
}
```

### 错误的解法！！分开排序

- **虽然显示AC，但如用例 `[[1,3],[2,6],[8,18],[12,10],[15,18]]` 过不了，LC测试用例不完善**
- ~~左、右区间分成两个数组分别排序~~（错误解法，不应分开）
- ~~逐个判断是否可合并~~
- ~~若可合并则合入后者~~
  - ~~此处不急于合并（`j` 在等待另一半），而是等待可成对后，再合入 `list`~~

```java
// 错误解法！！
public int[][] merge(int[][] intervals) {
    int n = intervals.length;
    int[] starts = new int[n], ends = new int[n];
    for (int i = 0; i < n; i++) {
        starts[i] = intervals[i][0];
        ends[i] = intervals[i][1];
    }
    Arrays.sort(starts);
    Arrays.sort(ends);

    List<int[]> list = new ArrayList<>();
    for (int i = 0, j = 0; i < n; i++) {
        if (i == n - 1 || starts[i + 1] > ends[i]) {
            // 下一个 start 左区间已无法涵盖当前 end 右区间
            // 已找到 j 的另一半 i
            list.add(new int[]{starts[j], ends[i]});
            j = i + 1;
        }
    }
    return list.toArray(new int[list.size()][]);
}
```

### 方法二：整体排序

- 7ms
- 整体按左区间升序排序
- 遍历并判断前后两者是否可合并
- 若可合并则合入后者
- 较「分开排序」慢的原因可参考 [Time Comparison of Arrays.sort(Object[]) and Arrays.sort(int[])](https://www.baeldung.com/arrays-sortobject-vs-sortint)
  - `Arrays.sort(Object[])` 使用插入排序和归并排序来提供 *O(nlog(n))* 的时间复杂度，但因为每次都需要调用对象间比较的方法，故会比快排慢
  - 原文里说的更详细，推荐阅读
  - 即便分开排序再后来我发现是错误的，但此处主要为了说明排序的效率而保留
- 示例 `[[1,3],[2,6],[8,10],[12,18],[15,18]]`

  [image.png](https://pic.leetcode-cn.com/0f8e9d1b4879dadea63b5060e2c7cf2a8f875f13bb3064b20188a59477652861-image.png)   [image.png](https://pic.leetcode-cn.com/96a1abad42d75f40776c456a72780fde27e1a458363261de84d39e0237be6875-image.png)   [image.png](https://pic.leetcode-cn.com/ef9fa26b2be46b6b6e90877107f363b403dd866eeabc36b7716f181c722b7633-image.png)   [image.png](https://pic.leetcode-cn.com/fb6f535a4bd8ff2fc8d109b7b5badd878cbcdc03e9309791cbc47f6b5e9b5f19-image.png)   [image.png](https://pic.leetcode-cn.com/ad76e37b813dd1487458b535b55ffadd207025395e18557b537460a816f79ce6-image.png)   [image.png](https://pic.leetcode-cn.com/87f1286f9f2e12a561b4c6877d98133af4d622673a0cd0101a3b4ae0faaa6c45-image.png)   [image.png](https://pic.leetcode-cn.com/74c5d1e5bb9abd4c6efa02a8d83e7afc5984c4e4574cc3fa81941bb4d93894ab-image.png)   [image.png](https://pic.leetcode-cn.com/6c9f223f12f90e75a75ee7125546ddce77e6ae5557b8062a54aa786157e3981c-image.png) 

```java
public int[][] merge(int[][] intervals) {
    int len = intervals.length;
    if (len < 1) return intervals;

    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

    List<int[]> list = new ArrayList<>(len);
    for (int i = 0; i < len - 1; i++) {
        if (intervals[i][1] >= intervals[i + 1][0]) {
            intervals[i + 1][0] = intervals[i][0];
            intervals[i + 1][1] = Math.max(intervals[i + 1][1], intervals[i][1]);
        } else list.add(intervals[i]);
    }
    list.add(intervals[len - 1]); // 别忘了最后一个

    return list.toArray(new int[list.size()][2]);
}
```