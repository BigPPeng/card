//给定两个字符串 s 和 t，它们只包含小写字母。 
//
// 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。 
//
// 请找出在 t 中被添加的字母。 
//
// 
//
// 示例 1： 
//
// 输入：s = "abcd", t = "abcde"
//输出："e"
//解释：'e' 是那个被添加的字母。
// 
//
// 示例 2： 
//
// 输入：s = "", t = "y"
//输出："y"
// 
//
// 示例 3： 
//
// 输入：s = "a", t = "aa"
//输出："a"
// 
//
// 示例 4： 
//
// 输入：s = "ae", t = "aea"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 1000 
// t.length == s.length + 1 
// s 和 t 只包含小写字母 
// 
// Related Topics 位运算 哈希表 
// 👍 225 👎 0


package leetcode.editor.cn;

//Java：找不同
public class P389FindTheDifference {
    public static void main(String[] args) {
        Solution solution = new P389FindTheDifference().new Solution();
        char theDifference = solution.findTheDifference("aa", "aaa");
        System.out.println(theDifference);
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public char findTheDifference(String s, String t) {
            return (char) (s + t).chars().reduce(0, (a, b) -> a ^ b);
        }

        private char _1(String s, String t) {
            int[] _s = new int[26];
            for (int i = 0; i < s.length(); i++) {
                int index = s.charAt(i) - 'a';
                _s[index] = _s[index] + 1;
            }
            int[] _t = new int[26];
            for (int i = 0; i < t.length(); i++) {
                int index = t.charAt(i) - 'a';
                _t[index] = _t[index] + 1;
            }

            for (int i = 0; i < 26; i++) {
                if (_s[i] != _t[i])
                    return (char) ('a' + i);
            }
            return ' ';
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
