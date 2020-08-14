//给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。 
//
// 
//
// 示例 1: 
//
// 输入: [1,2,0]
//输出: 3
// 
//
// 示例 2: 
//
// 输入: [3,4,-1,1]
//输出: 2
// 
//
// 示例 3: 
//
// 输入: [7,8,9,11,12]
//输出: 1
// 
//
// 
//
// 提示： 
//
// 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。 
// Related Topics 数组


package leetcode.editor.cn;
//Java：缺失的第一个正数
public class P41FirstMissingPositive{
    public static void main(String[] args) {
        Solution solution = new P41FirstMissingPositive().new Solution();
        System.out.println(solution.firstMissingPositive(new int[]{1,2,0}));
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int firstMissingPositive(int[] nums) {
        int[] bitSet = new int[(nums.length - 1) / 32 + 1];
        for (int num : nums) {
            if (num < 1 || num > nums.length)
                continue;
            int index = (num - 1) / 32;
            bitSet[index] |= (1 << ((num - 1) % 32));
        }
        for (int i = 0; i < nums.length; i++) {
            if ((bitSet[i/32] & (1 << (i % 32))) == 0) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
