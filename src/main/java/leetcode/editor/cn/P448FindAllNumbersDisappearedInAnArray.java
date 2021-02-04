//给定一个范围在 1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。 
//
// 找到所有在 [1, n] 范围之间没有出现在数组中的数字。 
//
// 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。 
//
// 示例: 
//
// 
//输入:
//[4,3,2,7,8,2,3,1]
//
//输出:
//[5,6]
// 
// Related Topics 数组 
// 👍 540 👎 0


package leetcode.editor.cn;

import java.util.*;

//Java：找到所有数组中消失的数字
public class P448FindAllNumbersDisappearedInAnArray {
    public static void main(String[] args) {
        Solution solution = new P448FindAllNumbersDisappearedInAnArray().new Solution();
        List<Integer> disappearedNumbers = solution.findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1});
        System.out.println(disappearedNumbers);
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i] < 0 ? -nums[i] : nums[i];
                int index = num - 1;
                nums[index] = nums[index] > 0 ? -nums[index] : nums[index];
            }
            ArrayList<Integer> integers = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0)
                    integers.add(i + 1);
            }
            return integers;
        }

        private List<Integer> one(int[] nums) {
            Set<Integer> returnRes = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                returnRes.add(i + 1);
            }
            for (int num : nums) {
                returnRes.remove(num);
            }
            return new ArrayList<>(returnRes);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
