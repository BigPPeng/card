//给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。 
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。 
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。 
//
// 示例 1: 
//
// 输入: [1,2,3]
//输出: [1,2,4]
//解释: 输入数组表示数字 123。
// 
//
// 示例 2: 
//
// 输入: [4,3,2,1]
//输出: [4,3,2,2]
//解释: 输入数组表示数字 4321。
// 
// Related Topics 数组

 
package leetcode.editor.cn;

import java.util.Arrays;

//Java：加一
public class P66PlusOne{
    public static void main(String[] args) {
        Solution solution = new P66PlusOne().new Solution();
        solution.plusOne(new int[]{8,9,9,9});
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] plusOne(int[] digits) {
        int temp = 0;
        for (int i = digits.length - 1; i >= 0 ; i--) {
            int i1 = i == digits.length - 1 ? digits[i] + 1 + temp : digits[i] + temp;
            if (i1 >= 10) {
                digits[i] = i1 - 10;
                temp = 1;
                continue;
            }
            digits[i] = i1;
            temp = 0;
            break;
        }
        if (temp == 1) {
            int[] res = new int[digits.length+1];
            for (int i = 0; i < digits.length; i++) {
                res[i + 1] = digits[i];
            }
            res[0] = 1;
            return res;
        }
        return digits;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}