//统计所有小于非负整数 n 的质数的数量。 
//
// 
//
// 示例 1： 
//
// 输入：n = 10
//输出：4
//解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
// 
//
// 示例 2： 
//
// 输入：n = 0
//输出：0
// 
//
// 示例 3： 
//
// 输入：n = 1
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 5 * 106 
// 
// Related Topics 哈希表 数学


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：计数质数
public class P204CountPrimes{
    public static void main(String[] args) {
        Solution solution = new P204CountPrimes().new Solution();
        System.out.println(solution.countPrimes(10));
        System.out.println(solution.countPrimes(4));
        System.out.println(solution.countPrimes(17));
        System.out.println(solution.countPrimes(2));
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int countPrimes(int n) {
        int result = 0, sqrt_n = (int) Math.sqrt(n);
        boolean[] b = new boolean[n];   // 初始化默认值都为 false，为质数标记
        if (2 < n) result++; // 如果大于 2 则一定拥有 2 这个质数
        for (int i = 3; i < n; i += 2) {  // 从 3 开始遍历，且只遍历奇数
            if (!b[i]) {  // 是质数
                if (i <= sqrt_n)//不大于根号n
                    for (int j = i; i * j < n; j += 2)
                        b[i * j] = true;    // 将当前质数的奇数倍都设置成非质数标记 true
                result++;   // 质数个数 +1
            }
        }
        return result;
    }









    private boolean isPrime(int n) {
        boolean flag = true;
        if(n == 1 || (n % 2 == 0 && n !=2)) flag = false;
        else for(int j = 3; j <= Math.sqrt(n); j+=2) {
            if(n%j == 0) flag = false;
        }
        return flag;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}
