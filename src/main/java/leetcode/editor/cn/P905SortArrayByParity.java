//给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。 
//
// 你可以返回满足此条件的任何数组作为答案。 
//
// 
//
// 示例： 
//
// 输入：[3,1,2,4]
//输出：[2,4,3,1]
//输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 5000 
// 0 <= A[i] <= 5000 
// 
// Related Topics 数组 
// 👍 188 👎 0


package leetcode.editor.cn;

import edu.emory.mathcs.backport.java.util.Arrays;

//Java：按奇偶排序数组
public class P905SortArrayByParity {
    public static void main(String[] args) {
        Solution solution = new P905SortArrayByParity().new Solution();
        System.out.println(Arrays.toString(solution.sortArrayByParity(new int[]{3, 1, 2, 4, 10})));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] sortArrayByParity(int[] A) {
            if (A == null || A.length <= 1)
                return A;
            int start = 0;
            int end = A.length - 1;
            while (start <= end) {
                while (start < A.length && start <= end && ((A[start] % 2) == 0)) {
                    start++;
                }
                while (end >= 0 && end >= start && ((A[end] % 2) != 0)) {
                    end--;
                }
                if (start < end) {
                    int temp = A[start];
                    A[start] = A[end];
                    A[end] = temp;
                }
            }

            return A;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
