//给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：6
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,4]
//输出：24
// 
//
// 示例 3： 
//
// 
//输入：nums = [-1,-2,-3]
//输出：-6
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 104 
// -1000 <= nums[i] <= 1000 
// 
// Related Topics 数组 数学 
// 👍 273 👎 0


package leetcode.editor.cn;

import edu.emory.mathcs.backport.java.util.Arrays;

//Java：三个数的最大乘积
public class P628MaximumProductOfThreeNumbers{
    public static void main(String[] args) {
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int negativeCount = 0;
        int positiveCount = 0;
        boolean hasZero = false;
        for (int num : nums) {
            if (num > 0) {
                positiveCount++;
                continue;
            }
            if (num < 0) {
                negativeCount++;
                continue;
            }
            hasZero = true;
        }

        // 没有负数
        int length = nums.length;
        if (negativeCount == 0) {
            return nums[length - 1] * nums[length - 2] * nums[length - 3];// 三个最大的正数乘积
        }
        // 没有正数
        if (positiveCount == 0) {
            return nums[length - 1] * nums[length - 2] * nums[length - 3];// 三个最大的负数乘积
        }
        // 小于2个正数
        if (positiveCount <= 2) {
            return nums[length - 1] * nums[0] * nums[1];
        }


        return 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
