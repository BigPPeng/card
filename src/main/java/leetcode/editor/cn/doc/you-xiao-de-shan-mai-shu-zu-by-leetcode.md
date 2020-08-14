#### 方法一：线性扫描

我们从数组的最左侧开始扫描，直到找到第一个不满足 `A[i] < A[i + 1]` 的 `i`，那么 `i` 就是这个数组的最高点。如果 `i = 0` 或者不存在这样的 `i`（即整个数组都是单调递增的），那么就返回 `false`。否则从 `i` 开始继续扫描，判断接下来的的位置 `j` 是否都满足 `A[j] > A[j + 1]`，若都满足就返回 `true`，否则返回 `false`。

```Java [sol1]
class Solution {
    public boolean validMountainArray(int[] A) {
        int N = A.length;
        int i = 0;

        // walk up
        while (i+1 < N && A[i] < A[i+1])
            i++;

        // peak can't be first or last
        if (i == 0 || i == N-1)
            return false;

        // walk down
        while (i+1 < N && A[i] > A[i+1])
            i++;

        return i == N-1;
    }
}
```

```Python [sol1]
class Solution(object):
    def validMountainArray(self, A):
        N = len(A)
        i = 0

        # walk up
        while i+1 < N and A[i] < A[i+1]:
            i += 1

        # peak can't be first or last
        if i == 0 or i == N-1:
            return False

        # walk down
        while i+1 < N and A[i] > A[i+1]:
            i += 1

        return i == N-1
```

**复杂度分析**

* 时间复杂度：*O(N)*，其中 *N* 是数组 `A` 的长度。

* 空间复杂度：*O(1)*。