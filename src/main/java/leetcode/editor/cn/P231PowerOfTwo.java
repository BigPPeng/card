//给定一个整数，编写一个函数来判断它是否是 2 的幂次方。 
//
// 示例 1: 
//
// 输入: 1
//输出: true
//解释: 20 = 1 
//
// 示例 2: 
//
// 输入: 16
//输出: true
//解释: 24 = 16 
//
// 示例 3: 
//
// 输入: 218
//输出: false 
// Related Topics 位运算 数学


package leetcode.editor.cn;
//Java：2的幂
public class P231PowerOfTwo{
    public static void main(String[] args) {
        Solution solution = new P231PowerOfTwo().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        /**
         *
         * -8的二进制表示是其反码+1，反码即8的二进制取反，为:11110111,所以反码+1为：11111000。
         *
         * 8&-8即为 00001000 & 11111000 = 00001000 得到的就是8的二进制。
         *
         * 2的次幂在二进制中，肯定只有一位为1，其余为0.而它的负数表示，也刚好在这一位中为1，所以两个值&得到的刚好是它本身。
         *
         */
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & -n) == n;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
