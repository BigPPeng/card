#### 方法一：队列

我们只会考虑最近 3000 毫秒到现在的 `ping` 数，因此我们可以使用队列存储这些 `ping` 的记录。当收到一个时间 `t` 的 `ping` 时，我们将它加入队列，并且将所有在时间 `t - 3000` 之前的 `ping` 移出队列。

```Java [sol1]
class RecentCounter {
    Queue<Integer> q;
    public RecentCounter() {
        q = new LinkedList();
    }

    public int ping(int t) {
        q.add(t);
        while (q.peek() < t - 3000)
            q.poll();
        return q.size();
    }
}
```

```Python [sol1]
class RecentCounter(object):
    def __init__(self):
        self.q = collections.deque()

    def ping(self, t):
        self.q.append(t)
        while self.q[0] < t-3000:
            self.q.popleft()
        return len(self.q)
```

**复杂度分析**

* 时间复杂度：*O(Q)*，其中 *Q* 是 `ping` 的次数。

* 空间复杂度：*O(W)*，其中 *W = 3000* 是队列中最多存储的 `ping` 的记录数目。