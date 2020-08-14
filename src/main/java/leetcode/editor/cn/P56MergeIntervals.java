//给出一个区间的集合，请合并所有重叠的区间。 
//
// 示例 1: 
//
// 输入: [[1,3],[2,6],[8,10],[15,18]]
//输出: [[1,6],[8,10],[15,18]]
//解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2: 
//
// 输入: [[1,4],[4,5]]
//输出: [[1,5]]
//解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。 
// Related Topics 排序 数组


package leetcode.editor.cn;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java：合并区间
public class P56MergeIntervals{
    public static void main(String[] args) {
        Solution solution = new P56MergeIntervals().new Solution();
        int[][] merge = solution.merge(new int[][]{{3,5}, {0,0},{4,4},{0,2},{5,6},{4,5},{3,5},{1,3},{4,6},{4,6},{3,4}});
        System.out.println(JSON.toJSONString(merge));
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals.length == 1) {
            return intervals;
        }

        while (mergeOne(intervals)) {}

        int newLength = 0;
        for (int[] interval : intervals) {
            if (interval != null) {
                newLength ++;
            }
        }

        int[][] res = new int[newLength][];
        int begin = 0;
        for (int[] interval : intervals) {
            if (interval == null)
                continue;
            res[begin++] = interval;
        }
        return res;
    }

    private boolean mergeOne(int[][] intervals) {
        boolean doMerge = false;
        for (int i = 0; i < intervals.length; i++) {
            int[] temp1 = intervals[i];
            if (temp1 == null) {
                continue;
            }
            for (int j = 0; j < intervals.length; j++) {
                int[] temp2 = intervals[j];
                if (temp2 == null) {
                    continue;
                }
                if (i == j) {
                    continue;
                }
                if (!(temp1[temp1.length - 1] < temp2[0] || temp1[0] > temp2[temp2.length - 1])) {
                    if (temp1[temp1.length - 1] < temp2[temp2.length - 1]) {
                        temp1[temp1.length - 1] = temp2[temp2.length - 1];
                    }
                    if (temp1[0] > temp2[0]) {
                        temp1[0] = temp2[0];
                    }
                    intervals[j] = null;
                    doMerge = true;
                }
            }
        }
        return doMerge;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
