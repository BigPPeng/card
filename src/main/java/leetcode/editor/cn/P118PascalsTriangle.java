//给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。 
//
// 
//
// 在杨辉三角中，每个数是它左上方和右上方的数的和。 
//
// 示例: 
//
// 输入: 5
//输出:
//[
//     [1],
//    [1,1],
//   [1,2,1],
//  [1,3,3,1],
// [1,4,6,4,1]
//] 
// Related Topics 数组


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java：杨辉三角
public class P118PascalsTriangle{
    public static void main(String[] args) {
        Solution solution = new P118PascalsTriangle().new Solution();
        List<List<Integer>> generate = solution.generate(5);
        for (List<Integer> integers : generate) {
            System.out.println(Arrays.toString(integers.toArray()));
        }

        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> listList = new ArrayList<>(numRows);
        if (numRows == 0) {
            return listList;
        }
        List<Integer> list_1 = new ArrayList<>(1);
        list_1.add(1);
        listList.add(list_1);
        if (numRows == 1) {
            return listList;
        }
        List<Integer> list_2 = new ArrayList<>(2);
        list_2.add(1);
        list_2.add(1);
        listList.add(list_2);
        if (numRows == 2) {
            return listList;
        }
        for (int i = 3; i <= numRows ; i++) {
            List<Integer> last = listList.get(i - 1 - 1);
            List<Integer> temp = new ArrayList<>(i);
            for (int j = 0; j < i; j++) {
                if (j == 0) {
                    temp.add(1);
                    continue;
                }
                if (j == (i - 1)) {
                    temp.add(1);
                    continue;
                }
                int o1 = last.get(j - 1);
                int o2 = last.get(j);
                temp.add(o1 + o2);
            }
            listList.add(temp);
        }
        return listList;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
