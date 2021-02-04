//给你两个数组，arr1 和 arr2， 
//
// 
// arr2 中的元素各不相同 
// arr2 中的每个元素都出现在 arr1 中 
// 
//
// 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末
//尾。 
//
// 
//
// 示例： 
//
// 
//输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//输出：[2,2,2,1,4,3,3,9,6,7,19]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr1.length, arr2.length <= 1000 
// 0 <= arr1[i], arr2[i] <= 1000 
// arr2 中的元素 arr2[i] 各不相同 
// arr2 中的每个元素 arr2[i] 都出现在 arr1 中 
// 
// Related Topics 排序 数组 
// 👍 153 👎 0


package leetcode.editor.cn;

import java.util.*;

//Java：数组的相对排序
public class P1122RelativeSortArray{
    public static void main(String[] args) {
        Solution solution = new P1122RelativeSortArray().new Solution();
        int[] ints = solution.relativeSortArray(new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19}, new int[]{2, 1, 4, 3, 9, 6});
        System.out.println(Arrays.toString(ints));
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int i1 = arr1.length;
        int[] sum = new int[arr1.length];
        int sumIndex = 0;


        List<Integer> diff = new ArrayList<>();
        Set<Integer> arr2_set = new HashSet<>();
        for (int i : arr2) {
            arr2_set.add(i);
        }
        for (int i : arr1) {
            if (!arr2_set.contains(i)) {
                diff.add(i);
            }
        }
        Collections.sort(diff);

        Map<Integer,Integer> countMap = new HashMap<>();
        for (int i2 : arr1) {
            countMap.merge(i2, 1, (a, b) -> a + b);
        }

        for (int i : arr2) {
            Integer count = countMap.get(i);
            for (int j = 0; j < count; j++) {
                sum[sumIndex++] = i;
            }
        }
        for (int i : diff) {
            sum[sumIndex++]=i;
        }
        return sum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
