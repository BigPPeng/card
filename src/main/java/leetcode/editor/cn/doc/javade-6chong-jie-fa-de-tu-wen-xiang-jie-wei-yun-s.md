
##### 1，暴力求解
首先最容易想到的就是暴力求解，从1开始一个个查找，没找到就直接返回
```Java []
    public int firstMissingPositive(int[] nums) {
        for (int i = 1; i <= nums.length; i++) {
            boolean has = false;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == i) {
                    has = true;
                    break;
                }
            }
            if (!has) {
                //没有找到这个数，直接返回
                return i;
            }
        }
        return nums.length + 1;
    }
```

##### 2，先排序，再查找
暴力求解运行效率毕竟不高，我们还可以先对数组进行排序，然后再使用二分法再一个个查找
```Java []
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);//先排序
        for (int i = 1; i <= len; i++) {
            int res = binarySearch(nums, i);
            //一个个查找，如果没找到就返回
            if (res == -1)
                return i;
        }
        return len + 1;
    }

    //二分法查找，找到就返回位置下标，没找到就返回-1。
    private int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
```

##### 3，使用集合set解决
还一种比较简单的方式是，把原数组的值全部存放到集合set中，然后再从1开始循环，判断这个数是否存在集合中，如果不存在直接返回
```
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        Set<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        for (int i = 1; i <= len; i++) {
            if (!hashSet.contains(i))
                return i;
        }
        return len + 1;
    }
```

##### 4，存放对应的位置
我们还可以把每个元素存放到对应的位置，比如1存放到数组的第一个位置，3存放到数组的第3个位置，
如果是非正数或者大于数组的长度的值，我们不做处理，最后在遍历一遍数组，如果位置不正确，说明这个位置没有这个数，我们就直接返回，我们画个图看一下
 [image.png](https://pic.leetcode-cn.com/c21399184919c59c38cb34f180d2d729eca6219117cceef3f00e77427df7cd5d-image.png){:width=500}
{:align=center}


 [image.png](https://pic.leetcode-cn.com/6fd89f64a49743c5d9234fc4fc8822b226bb1c003bc5acf465e702f648f05270-image.png){:width=500}
{:align=center}


 [image.png](https://pic.leetcode-cn.com/ea566e34cd0e41162e70121d955289ab3bf0398fb8945fecc9fe0915a9725cc7-image.png){:width=500}
{:align=center}



```Java []
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            //如果在指定的位置就不需要修改
            if (i + 1 == nums[i])
                continue;
            int x = nums[i];
            if (x >= 1 && x <= nums.length && x != nums[x - 1]) {
                swap(nums, i, x - 1);
                i--;//抵消上面的i++，如果交换之后就不++；
            }
        }
        //最后在执行一遍循环，查看对应位置的元素是否正确，如果不正确直接返回
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i])
                return i + 1;
        }
        return nums.length + 1;
    }

    //交换两个数的值
    public void swap(int[] A, int i, int j) {
        if (i != j) {
            A[i] ^= A[j];
            A[j] ^= A[i];
            A[i] ^= A[j];
        }
    }
```

##### 5，位运算求解
位运算的实现原理和上面第4种答案类似，只不过这里使用的是位运算的方式来解决的。我们知道在java中一个int类型占4个字节是32位，我们可以申请一个数组，1到32我们可以存放到数组的第一个元素中，33到64可以存放到第2个元素中……，有的同学可能好奇，一个数字怎么可能存放32个数呢。因为一个int类型数字是32位的，也就是由32个0和1组成，我们只要统计1在存储中的位置即可，我们来看下代码。
```Java []
    public int firstMissingPositive(int[] nums) {
        int length = nums.length;
        int bit[] = new int[(length - 1) / 32 + 1];
        for (int i = 0; i < nums.length; i++) {
            int digit = nums[i];
            //数组必须在1到length之间才有效
            if (digit >= 1 && digit <= length) {
                int index = (digit - 1) / 32;
                bit[index] |= (1 << ((digit - 1) % 32));
            }
        }
        //最后在执行一遍循环，查看对应位置的元素是否正确，如果不正确直接返回
        for (int i = 0; i < nums.length; i++) {
            if ((bit[i / 32] & (1 << (i % 32))) == 0)
                return i + 1;
        }
        return length + 1;
    }
```
其中位运算击败了100%的用户
 [image.png](https://pic.leetcode-cn.com/1ab92b5eb8a7a1de193bd161d071745faec096eff9656b55639806ae73da5198-image.png)

##### 6，如果某个元素的值是无效的，他会让数组后面的元素填补过来，然后再判断。注释写在代码中了，有兴趣的也可以看下。
```Java []
    public int firstMissingPositive(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int index = nums[start] - 1;
            if (index == start) {
                //存放的位置正确
                start++;
            } else if (index < 0 || index > end || nums[start] == nums[index]) {
                //前面两个表示数字不在存放位置范围内，就让数组end位置的元素把这个无效的
                // 元素覆盖掉，后面一个表示的是index这个位置存放正确了就不需要在存放了
                nums[start] = nums[end--];
            } else {
                //把start对应的元素存放到正确的位置
                nums[start] = nums[index];
                nums[index] = index + 1;
            }
        }
        return start + 1;
    }
```

