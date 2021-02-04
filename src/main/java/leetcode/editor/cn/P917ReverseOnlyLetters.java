//给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入："ab-cd"
//输出："dc-ba"
// 
//
// 示例 2： 
//
// 输入："a-bC-dEf-ghIj"
//输出："j-Ih-gfE-dCba"
// 
//
// 示例 3： 
//
// 输入："Test1ng-Leet=code-Q!"
//输出："Qedo1ct-eeLg=ntse-T!"
// 
//
// 
//
// 提示： 
//
// 
// S.length <= 100 
// 33 <= S[i].ASCIIcode <= 122 
// S 中不包含 \ or " 
// 
// Related Topics 字符串 
// 👍 73 👎 0


package leetcode.editor.cn;

//Java：仅仅反转字母
public class P917ReverseOnlyLetters {
    public static void main(String[] args) {
        Solution solution = new P917ReverseOnlyLetters().new Solution();
        System.out.println("ab-cd".equals(solution.reverseOnlyLetters("dc-ba")));
        System.out.println("Test1ng-Leet=code-Q!".equals(solution.reverseOnlyLetters("Qedo1ct-eeLg=ntse-T!")));
        System.out.println("a-bC-dEf-ghIj".equals(solution.reverseOnlyLetters("j-Ih-gfE-dCba")));

        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseOnlyLetters(String S) {
            if (S == null || S.isEmpty())
                return S;
            char[] chars = S.toCharArray();
            int start = 0;
            int end = chars.length - 1;
            while (start < end) {
                while (start < end && start < chars.length && !isA_Z(chars[start]))
                    start++;

                while (start < end && end >= 0 && !isA_Z(chars[end]))
                    end--;

                if (start < end) {
                    char temp = chars[start];
                    chars[start] = chars[end];
                    chars[end] = temp;
                    start++;
                    end--;
                }
            }

            return new String(chars);
        }


        private boolean isA_Z(char a) {
            return (a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z');
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
