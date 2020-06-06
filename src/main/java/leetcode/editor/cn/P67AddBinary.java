//给你两个二进制字符串，返回它们的和（用二进制表示）。 
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1: 
//
// 输入: a = "11", b = "1"
//输出: "100" 
//
// 示例 2: 
//
// 输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
// Related Topics 数学 字符串

 
package leetcode.editor.cn;
//Java：二进制求和
public class P67AddBinary{
    public static void main(String[] args) {
        Solution solution = new P67AddBinary().new Solution();
        System.out.println(solution.addBinary("11","1"));
        System.out.println(solution.addBinary("1010","1011"));

        System.out.println(solution.addBinary("10101111111","0"));



        System.out.println(solution.addBinary("10101111111","10110000"));
        long x = 0b10101111111 + 0b10110000;
        System.out.println(Long.toBinaryString(x));

        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String addBinary(String a, String b) {
        if (a.length() == 0 || a.equals("0")) {
            return b;
        }
        if (b.length() == 0 || b.equals("0")) {
            return a;
        }
        int arrayLength = a.length() > b.length() ? a.length() : b.length();
        int[] result = new int[arrayLength + 1];

        int aIndex = a.length() - 1;
        int bIndex = b.length() - 1;
        int resIndex = arrayLength;
        int temp = 0;// 进位的值
        for (int i = arrayLength; i >= 0; i--) {
            char aa = aIndex >= 0 ? a.charAt(aIndex--) : '0';
            int aaa = aa == '0' ? 0 : 1;
            char bb = bIndex >= 0 ? b.charAt(bIndex--) : '0';
            int bbb = bb == '0' ? 0 : 1;
            int sum = aaa + bbb + temp;
            if (sum == 0 || sum == 1) {
                result[resIndex--] = sum;
                temp = 0;
            } else {
                result[resIndex--] = sum - 2;
                temp = 1;
            }
        }
        StringBuilder builder = new StringBuilder(arrayLength + 1);
        boolean begin = true;
        for (int i = 0; i < result.length; i++) {
            if (result[i] == 0 && begin) {
                continue;
            }
            begin = false;
            builder.append(result[i]);
        }
        return builder.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}