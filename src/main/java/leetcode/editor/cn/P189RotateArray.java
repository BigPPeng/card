//给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。 
//
// 示例 1: 
//
// 输入: [1,2,3,4,5,6,7] 和 k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右旋转 1 步: [7,1,2,3,4,5,6]
//向右旋转 2 步: [6,7,1,2,3,4,5]
//向右旋转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 输入: [-1,-100,3,99] 和 k = 2
//输出: [3,99,-1,-100]
//解释: 
//向右旋转 1 步: [99,-1,-100,3]
//向右旋转 2 步: [3,99,-1,-100] 
//
// 说明: 
//
// 
// 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 
// 要求使用空间复杂度为 O(1) 的 原地 算法。 
// 
// Related Topics 数组


package leetcode.editor.cn;

import java.util.Arrays;

//Java：旋转数组
public class P189RotateArray{
    public static void main(String[] args) {
        Solution solution = new P189RotateArray().new Solution();
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(nums));
        solution.rotate(nums,1);
        System.out.println(Arrays.toString(nums));
        solution.rotate(nums,1);
        System.out.println(Arrays.toString(nums));
        solution.rotate(nums,1);
        System.out.println(Arrays.toString(nums));

        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public void rotate(int[] nums, int k) {
            testTwo(nums, k);
        }

        private void testThree(int[] nums, int k) {
            k = k % nums.length;
            while (k > 0) {
                int temp = nums[nums.length - 1];
                System.arraycopy(nums, 0, nums, 1, nums.length - 1);
                nums[0] = temp;
                k--;
            }
        }

        private void testTwo(int[] nums, int k) {
            k = k % nums.length;
            int[] temp = new int[k];

//            System.arraycopy(nums, nums.length - 1 - k, temp, nums.length - 1 - k - (nums.length - 1 - k), k - (nums.length - 1 - k));

            for (int i = 0; i < k; i++) {
                temp[k - i - 1] = nums[nums.length - i - 1];
            }
//            System.arraycopy(nums, nums.length - k - 1, temp, 0, k);

            System.arraycopy(nums, 0, nums, k, nums.length - k);
//
//            int index = nums.length - 1;
//            while (index - k >= 0) {
//                nums[index] = nums[index - k];
//                index--;
//            }
            System.arraycopy(temp, 0, nums, 0, temp.length);
        }


        private void testOne(int[] nums, int k) {
            k = k % nums.length;
            int[] temp = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                int targetIndex = i + k;
                int index = targetIndex >= nums.length ? targetIndex - nums.length : targetIndex;
                temp[index] = nums[i];
            }
            System.arraycopy(temp, 0, nums, 0, temp.length);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
