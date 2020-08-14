//给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。 
//
// 
//
// 示例 1: 
//
// 输入: [3,0,1]
//输出: 2
// 
//
// 示例 2: 
//
// 输入: [9,6,4,2,3,5,7,0,1]
//输出: 8
// 
//
// 
//
// 说明: 
//你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现? 
// Related Topics 位运算 数组 数学


package leetcode.editor.cn;
//Java：缺失数字
public class P268MissingNumber{
    public static void main(String[] args) {
        Solution solution = new P268MissingNumber().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int missingNumber(int[] nums) {
        // 异或运算的属性
        // a = b ^ c ^ d;
        // b = a ^ c ^ d;
        // ===>
        // 0 ^ 1 ^ 2 ^ ...^ x ^ ... ^ n = X
        // x = X ^ 0 ... ^ x -1 ^ x + 1 ^.... n
        int a = 0;
        int b = 0;
        for (int i = 0; i < nums.length; i++) {
            a ^= i + 1;
            b ^= nums[i];
        }
        return a ^ b;
    }

        private int one(int[] nums) {
            int sum = (nums.length * (nums.length + 1)) / 2;
            int sumAll = 0;
            for (int i = 0; i < nums.length; i++) sumAll += nums[i];
            return sum - sumAll;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
