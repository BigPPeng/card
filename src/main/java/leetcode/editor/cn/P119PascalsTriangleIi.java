//给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。 
//
// 
//
// 在杨辉三角中，每个数是它左上方和右上方的数的和。 
//
// 示例: 
//
// 输入: 3
//输出: [1,3,3,1]
// 
//
// 进阶： 
//
// 你可以优化你的算法到 O(k) 空间复杂度吗？ 
// Related Topics 数组 
// 👍 223 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java：杨辉三角 II
public class P119PascalsTriangleIi {
    public static void main(String[] args) {
        Solution solution = new P119PascalsTriangleIi().new Solution();
        solution.getRow(1);
        solution.getRow(2);
        solution.getRow(3);
        solution.getRow(4);
        solution.getRow(5);
        solution.getRow(6);
        solution.getRow(7);
        solution.getRow(8);
        solution.getRow(9);
        solution.getRow(10);
        solution.getRow(11);
        solution.getRow(12);
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> getRow(int rowIndex) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            if (rowIndex == 0) {
                arrayList.add(1);
                return arrayList;
            }
            rowIndex = rowIndex + 1;// 参数从0开始
            int[] a = new int[rowIndex];
            a[0] = 1;

            for (int i = 1; i < rowIndex; i++) {
                cal(a, i + 1);
            }
            System.out.println(Arrays.toString(a));
            for (int i : a) {
                arrayList.add(i);
            }
            return arrayList;
        }


        private void cal(int[] last, int rowIndex) {
            for (int i = rowIndex - 1; i > 0; i--) {
                last[i] = last[i - 1] + last[i];
            }
        }



        private List<Integer> one(int rowIndex) {
            rowIndex = rowIndex + 1;

            int[] a = new int[rowIndex];
            a[0] = 1;
            int[] b = new int[rowIndex];
            for (int i = 1; i < rowIndex; i++) {
                if (i % 2 == 0) {
                    cal(a, b, i + 1);
                } else {
                    cal(b, a, i + 1);
                }
            }
//        System.out.println(rowIndex % 2);
            int[] res = (rowIndex % 2 == 0) || rowIndex == 1 ? a : b;
            int start = 0;
            int end = rowIndex - 1;
            while (start <= end) {
                res[end--] = res[start++];
            }

            System.out.println(Arrays.toString(res));

            List<Integer> res1 = new ArrayList<>(rowIndex);
            for (int re : res) {
                res1.add(re);
            }

            return res1;
        }


        private void cal(int[] base, int[] to, int row) {
            boolean b = row % 2 == 0;
            int calIndex = b ? row / 2 : (row / 2) + 1;
            for (int i = 0; i < calIndex; i++) {
                if (i == (calIndex - 1) && !b) {
                    to[i] = 2 * base[i - 1];
                    continue;
                }
                to[i] = i == 0 ? 1 : (base[i - 1] + base[i]);
            }
            //System.out.println(Arrays.toString(to));
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
