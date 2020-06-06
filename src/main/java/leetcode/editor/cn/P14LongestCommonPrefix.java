//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 示例 1: 
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
// 
//
// 示例 2: 
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
// 
//
// 说明: 
//
// 所有输入只包含小写字母 a-z 。 
// Related Topics 字符串

 
package leetcode.editor.cn;
//Java：最长公共前缀
public class P14LongestCommonPrefix{
    public static void main(String[] args) {
        Solution solution = new P14LongestCommonPrefix().new Solution();
        System.out.println("want:fl res:"+solution.longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println("want:fl res:"+solution.longestCommonPrefix(new String[]{"flower","","flight"}));
        System.out.println("want: res:"+solution.longestCommonPrefix(new String[]{"dog","racecar","car"}));
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs[0].isEmpty()) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        StringBuilder str = new StringBuilder();
        boolean b;
        for (int i = 0; i < strs[0].length(); i++) {
            char temp = strs[0].charAt(i);
            b = false;
            for (int i1 = 1; i1 < strs.length; i1++) {
                if (i >= strs[i1].length() || strs[i1].charAt(i) != temp) {
                    b = true;
                    break;
                }
            }
            if (b) {
                break;
            } else {
                str.append(temp);
            }
        }
        return str.toString();



//        StringBuilder str = new StringBuilder();
//        int index = 0;
//        boolean end = false;
//        while (true) {
//            if (strs[0].isEmpty() || index > (strs[0].length() - 1)) {
//                break;
//            }
//            char temp = strs[0].charAt(index);
//            for (int i = 0; i < strs.length; i++) {
//                if (strs[i].isEmpty() || index > (strs[i].length() - 1)) {
//                    end = true;
//                    break;
//                }
//                if (i == 0) {
//                    continue;
//                }
//                if (strs[i].charAt(index) != temp) {
//                    end = true;
//                    break;
//                }
//            }
//            if (end) {
//                break;
//            } else {
//                str.append(temp);
//                index++ ;
//            }
//        }
//        return str.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}