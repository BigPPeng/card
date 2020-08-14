//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针


package leetcode.editor.cn;

import java.util.Arrays;

//Java：移动零
public class P283MoveZeroes{
    public static void main(String[] args) {
        Solution solution = new P283MoveZeroes().new Solution();

        int[] nums = {1};
        System.out.println(Arrays.toString(nums));
        solution.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void moveZeroes(int[] nums) {
        int zero_index = -1;
        int next_not_zero = 0;
        while (next_not_zero < nums.length) {
            if (nums[next_not_zero] != 0 && zero_index == -1) {
                next_not_zero++;
                continue;
            }

            if (nums[next_not_zero] != 0) {
                nums[zero_index++] = nums[next_not_zero];
                nums[next_not_zero] = 0;
                next_not_zero++;
                continue;
            }

            if (zero_index == -1) {
                zero_index = next_not_zero;
            }
            next_not_zero++;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
