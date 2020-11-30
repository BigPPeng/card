//给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。 
//
// 示例: 
//
// 输入: 38
//输出: 2 
//解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
// 
//
// 进阶: 
//你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？ 
// Related Topics 数学


package leetcode.editor.cn;

//Java：各位相加
public class P258AddDigits {
    public static void main(String[] args) {
        Solution solution = new P258AddDigits().new Solution();
        System.out.println(solution.addDigits(38));
        System.out.println(solution.addDigits(32));
        System.out.println(solution.addDigits(18));
        System.out.println(solution.addDigits(88));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int addDigits(int num) {
            while (num > 10) {
                num = sumAll(num);
            }
            return num;
        }

        private int sumAll(int num) {
            String s = num + "";
            int sum = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                sum += charToInt(c);
            }
            return sum;
        }


        private int charToInt(char c) {
            switch (c) {
                case '0':
                    return 0;
                case '1':
                    return 1;
                case '2':
                    return 2;
                case '3':
                    return 3;
                case '4':
                    return 4;
                case '5':
                    return 5;
                case '6':
                    return 6;
                case '7':
                    return 7;
                case '8':
                    return 8;
                case '9':
                    return 8;
            }
            return 0;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
