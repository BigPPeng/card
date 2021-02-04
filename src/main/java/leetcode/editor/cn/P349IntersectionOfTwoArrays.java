//给定两个数组，编写一个函数来计算它们的交集。 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2]
// 
//
// 示例 2： 
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[9,4] 
//
// 
//
// 说明： 
//
// 
// 输出结果中的每个元素一定是唯一的。 
// 我们可以不考虑输出结果的顺序。 
// 
// Related Topics 排序 哈希表 双指针 二分查找 
// 👍 309 👎 0


package leetcode.editor.cn;

import java.util.Arrays;

//Java：两个数组的交集
public class P349IntersectionOfTwoArrays{
    public static void main(String[] args) {
        Solution solution = new P349IntersectionOfTwoArrays().new Solution();
        int[] a = new int[]{2,34,5,6,7};
        Arrays.sort(a);

        int[] intersection = solution.intersection(new int[]{1, 8, 10, 4,3}, new int[]{2, 3, 5});

        System.out.println(Arrays.toString(intersection));

        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int min = Math.min(nums1.length,nums2.length);
        int[] res = new int[min];
        int k = 0;

        int i = 0;
        int j = 0;
        while (i < nums1.length && j< nums2.length) {
            int i1 = nums1[i];
            int i2 = nums2[j];
            if (i1 == i2) {
                if (k == 0 || i1 != res[k - 1])
                    res[k++] = i1;
                i++;
                j++;
                continue;
            }
            if (i1 < i2) {
                i++;
            } else {
                j++;
            }
        }
        return Arrays.copyOfRange(res, 0, k);
    }



}
//leetcode submit region end(Prohibit modification and deletion)

}
