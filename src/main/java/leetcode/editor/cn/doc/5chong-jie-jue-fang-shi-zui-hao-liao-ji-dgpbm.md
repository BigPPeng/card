
### 1，位运算解决

这题说的是字符串```t```只比```s```多了一个字符，其他字符他们的数量都是一样的，如果我们把字符串s和t合并就会发现，除了那个多出的字符出现奇数次，其他的所有字符都是出现偶数次，看到这里我们很容易想到[136. 只出现一次的数字](https://leetcode-cn.com/problems/single-number/)。所以只要是第136题的解我们都可以拿来用。

<br>

所以这题最简单的一种解决方式就是使用异或运算，关于异或运算有下面几个规律

> a^a=0;   任何数字和自己异或都是0
> a^0=a;    任何数字和0异或还是他自己
> a^b^a=a^a^b     异或运算具有交换律

因为s和t合并之后，偶数个的字符通过异或都会变为0，奇数个的字符异或之后还是他自己，我们只需要把合并的字符全部异或一遍即可，代码如下

```
    public char findTheDifference(String s, String t) {
        char[] charArr = s.concat(t).toCharArray();
        char res = 0;
        for (char c : charArr) {
            res ^= c;
        }
        return res;
    }
```

看下运行结果

![image.png](https://pic.leetcode-cn.com/1608253161-nNhEbV-image.png)


<br>


### 2，纯数学的方式解决

既然字符串s比t少一个字符，我们先统计字符串s中每个字符的数量，然后减去字符串t中的每个字符，如果小于0，说明字符串s比t少的就是这个字符，直接返回即可，代码如下

```
    public char findTheDifference(String s, String t) {
        int count[] = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            if (--count[t.charAt(i) - 'a'] < 0)
                return t.charAt(i);
        }
        return 'a';
    }
```

<br>

### 3，使用集合set解决

把字符串s和t合并，然后遍历合并的每个字符，判断集合set中是否有这个字符，如果有就移除，否则就加入到集合set中。最后集合set中只有一个字符，这个字符就是我们要求的。

```
    public char findTheDifference(String s, String t) {
        Set<Character> set = new HashSet<>();
        char[] charArr = s.concat(t).toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            if (set.contains(charArr[i]))
                set.remove(charArr[i]);
            else
                set.add(charArr[i]);
        }
        return (char) set.toArray()[0];
    }
```

<br>

### 4，计算两字符串的差值

还可以用t中所有字符的和减去s中所有字符的和，最后结果就是要求的那个字符


```
    public char findTheDifference(String s, String t) {
        int distance = 0;
        for (int i = 0; i < s.length(); ++i) {
            distance -= s.charAt(i);
            distance += t.charAt(i);
        }
        distance += t.charAt(t.length() - 1);
        return (char) distance;
    }
```

<br>

### 5，一行代码搞定

还可以更简洁一些，使用Java代码，一行搞定

```
    public char findTheDifference(String s, String t) {
        return (char) (s + t).chars().reduce(0, (c, d) -> c ^ d);
    }
```



<br>

**如果觉得有用就给个赞吧，你的赞是给我最大的鼓励，也是我写作的最大动力**

