//给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。 
//
// 输入为三个整数：day、month 和 year，分别表示日、月、年。 
//
// 您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "F
//riday", "Saturday"}。 
//
// 
//
// 示例 1： 
//
// 输入：day = 31, month = 8, year = 2019
//输出："Saturday"
// 
//
// 示例 2： 
//
// 输入：day = 18, month = 7, year = 1999
//输出："Sunday"
// 
//
// 示例 3： 
//
// 输入：day = 15, month = 8, year = 1993
//输出："Sunday"
// 
//
// 
//
// 提示： 
//
// 
// 给出的日期一定是在 1971 到 2100 年之间的有效日期。 
// 
// Related Topics 数组 
// 👍 32 👎 0


package leetcode.editor.cn;

import java.util.Date;

//Java：一周中的第几天
public class P1185DayOfTheWeek {
    public static void main(String[] args) {
        Solution solution = new P1185DayOfTheWeek().new Solution();
        System.out.println(solution.dayOfTheWeek(31, 8, 2019).equals("Saturday"));
        System.out.println(solution.dayOfTheWeek(18, 7, 1999).equals("Sunday"));
        System.out.println(solution.dayOfTheWeek(15, 8, 1993).equals("Sunday"));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String dayOfTheWeek(int day, int month, int year) {
            int begin = -28800000;
            Date parse = new Date(year - 1900, month - 1, day, 0, 0, 0);
            long l = parse.getTime() - begin;
            int dayMi = 24 * 60 * 60 * 1000;
            long week = 7 * dayMi;
            long diff = l % week;
            long l1 = diff / dayMi;

            long x = l1 + 4;

            String[] a = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

            int x1 = (int)(x > 7 ? x - 7 : x);
            return a[x1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
