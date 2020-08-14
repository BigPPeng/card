//给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。 
//
// 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组： 
//
// 
// A.length >= 3 
// 在 0 < i < A.length - 1 条件下，存在 i 使得：
// 
// A[0] < A[1] < ... A[i-1] < A[i] 
// A[i] > A[i+1] > ... > A[A.length - 1] 
// 
// 
// 
//
// 
//
// 
//
// 
//
// 示例 1： 
//
// 输入：[2,1]
//输出：false
// 
//
// 示例 2： 
//
// 输入：[3,5,5]
//输出：false
// 
//
// 示例 3： 
//
// 输入：[0,3,2,1]
//输出：true 
//
// 
//
// 提示： 
//
// 
// 0 <= A.length <= 10000 
// 0 <= A[i] <= 10000 
// 
//
// 
//
// 
// Related Topics 数组


package leetcode.editor.cn;
//Java：有效的山脉数组
public class P941ValidMountainArray{
    public static void main(String[] args) {
        Solution solution = new P941ValidMountainArray().new Solution();
        System.out.println(solution.validMountainArray(new int[]{0,3,2,1}));
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean validMountainArray(int[] a) {
        if (a.length < 3) {
            return false;
        }
        int left = 0;
        while (left <= a.length - 2 && a[left] < a[left + 1])
            left++;
        if (left == a.length - 1)
            return false;

        int right = a.length - 1;
        while (right >= 1 && a[right] < a[right - 1])
            right--;

        return right != 0 && left == right;

    }

        private boolean one(int[] a) {
            int index = -1;
            for (int i = 1; i < a.length; i++) {
                if (a[i] == a[i - 1]) {
                    return false;
                }
                if (a[i] < a[i -1]) {
                    index = i;
                    break;
                }
            }
            if (index == -1 || index == 1) {
                return false;
            }
            if (index == a.length -1) {
                return true;
            }
            for (int i = index + 1; i < a.length; i++) {
                if (a[i] >= a[i -1]) {
                    return false;
                }
            }

            return true;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
