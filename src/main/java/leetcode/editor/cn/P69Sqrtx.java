//实现 int sqrt(int x) 函数。 
//
// 计算并返回 x 的平方根，其中 x 是非负整数。 
//
// 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。 
//
// 示例 1: 
//
// 输入: 4
//输出: 2
// 
//
// 示例 2: 
//
// 输入: 8
//输出: 2
//说明: 8 的平方根是 2.82842..., 
//     由于返回类型是整数，小数部分将被舍去。
// 
// Related Topics 数学 二分查找 
// 👍 586 👎 0


package leetcode.editor.cn;

//Java：x 的平方根
public class P69Sqrtx {
    public static void main(String[] args) {
        Solution solution = new P69Sqrtx().new Solution();
        System.out.println(solution.mySqrt(2147483647));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 两个int值乘积会越界int最大值
        public int mySqrt(int x) {
            if (x == 0)
                return 0;
            if (x <= 3)
                return 1;

            int max = x / 2;
            int min = 0;
            while ((max - min) > 1) {
                int middle = (max + min) / 2;

                int i = x / middle;
                if (i == middle)
                    return middle;
                if (i > middle) {
                    min = middle;
                } else {
                    max = middle;
                }
            }

            int i = x / max;
            return i >= max ? max : min;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
