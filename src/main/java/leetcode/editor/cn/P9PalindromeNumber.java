//判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。 
//
// 示例 1: 
//
// 输入: 121
//输出: true
// 
//
// 示例 2: 
//
// 输入: -121
//输出: false
//解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
// 
//
// 示例 3: 
//
// 输入: 10
//输出: false
//解释: 从右向左读, 为 01 。因此它不是一个回文数。
// 
//
// 进阶: 
//
// 你能不将整数转为字符串来解决这个问题吗？ 
// Related Topics 数学

 
package leetcode.editor.cn;
//Java：回文数
public class P9PalindromeNumber{
    public static void main(String[] args) {
        Solution solution = new P9PalindromeNumber().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int[] a = new int[20];
        int i = 0;
        while (x != 0) {
            a[i++] = x % 10;
            x = x / 10;
        }
        i--;
        while (x < i) {
            if (a[x] != a[i]) {
                return false;
            }
            x ++;
            i --;
        }
        return true;

//        String s = String.valueOf(x);
//        int begin = 0;
//        int end = s.length() - 1;
//        while (begin < end) {
//            if (s.charAt(begin) != s.charAt(end)) {
//                return false;
//            }
//            begin ++;
//            end --;
//        }
//        return true;

//        char[] chars = String.valueOf(x).toCharArray();
//        int begin = 0;
//        int end = chars.length - 1;
//        while (begin < end) {
//            if (chars[begin] != chars[end]) {
//                return false;
//            }
//            begin ++;
//            end --;
//        }
//        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}