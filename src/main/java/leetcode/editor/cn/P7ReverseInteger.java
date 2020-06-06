//给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。 
//
// 示例 1: 
//
// 输入: 123
//输出: 321
// 
//
// 示例 2: 
//
// 输入: -123
//输出: -321
// 
//
// 示例 3: 
//
// 输入: 120
//输出: 21
// 
//
// 注意: 
//
// 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。 
// Related Topics 数学

 
package leetcode.editor.cn;

import org.apache.shiro.util.StringUtils;

//Java：整数反转
public class P7ReverseInteger{
    public static void main(String[] args) {
        Solution solution = new P7ReverseInteger().new Solution();
        solution.reverse(-2147483648);
//        System.out.println(solution.reverse(-123));
//        System.out.println(solution.reverse(120));
//        System.out.println(solution.reverse(1));

        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int reverse(int x) {
//        int max =  2147483647;
//        int min = -2147483648;
//        char[] chars = String.valueOf(x).toCharArray();
//        StringBuilder builder = new StringBuilder(chars.length);
//        boolean fu = false;
//        for (int i = chars.length - 1; i >= 0; i--) {
//            if (i == 0 && chars[i] == '-') {
//                fu = true;
//                break;
//            }
//            builder.append(chars[i]);
//        }
//        String s = builder.toString();
//        long result = Long.parseLong(s);
//        if (fu) {
//            result = result * -1;
//        }
//        if (result < min || result > max) {
//            return 0;
//        }
//        return (int) result;


        int max =  2147483647;
        int min = -2147483648;
        long result = 0;
        while (x != 0) {
            int i = x % 10;
            x = x / 10;
            result = result * 10 + i;
        }
        if (result < min || result > max) {
            return 0;
        }
        return (int) result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}