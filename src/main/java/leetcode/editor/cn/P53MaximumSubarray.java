//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 示例: 
//
// 输入: [-2,1,-3,4,-1,2,1,-5,4],
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
// 
//
// 进阶: 
//
// 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。 
// Related Topics 数组 分治算法 动态规划

 
package leetcode.editor.cn;
//Java：最大子序和
public class P53MaximumSubarray{
    public static void main(String[] args) {
        Solution solution = new P53MaximumSubarray().new Solution();
        int i = solution.maxSubArray(new int[]{-1});
        System.out.println(i);
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxSubArray(int[] nums) {
        return three(nums);
    }

        private int three(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int max = nums[0];
            int tempMax = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (tempMax > 0) {
                    tempMax = tempMax + nums[i];
                } else {
                    tempMax = nums[i];
                }
                max = Math.max(tempMax,max);
            }
            return max;
        }

        /**
         * 动态规划解法
         */
        private int two(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (dp[i - 1] > 0) {
                    dp[i] = dp[i-1] + nums[i];
                } else {
                    dp[i] = nums[i];
                }
            }
            int max = dp[0];
            for (int i = 1; i < dp.length; i++) {
                max = Math.max(max,dp[i]);
            }
            return max;
        }

        private int one(int[] nums) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                int temp = nums[i];
                int sum = nums[i];
                for (int i1 = i + 1; i1 < nums.length; i1++) {
                    sum += nums[i1];
                    temp = Math.max(temp,sum);
                }
                max = Math.max(temp,max);
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}