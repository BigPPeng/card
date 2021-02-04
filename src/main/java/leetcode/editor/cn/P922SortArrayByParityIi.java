//给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。 
//
// 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。 
//
// 你可以返回任何满足上述条件的数组作为答案。 
//
// 
//
// 示例： 
//
// 输入：[4,2,5,7]
//输出：[4,5,2,7]
//解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
// 
//
// 
//
// 提示： 
//
// 
// 2 <= A.length <= 20000 
// A.length % 2 == 0 
// 0 <= A[i] <= 1000 
// 
//
// 
// Related Topics 排序 数组 
// 👍 188 👎 0


package leetcode.editor.cn;

import java.util.Arrays;

//Java：按奇偶排序数组 II
public class P922SortArrayByParityIi {
    public static void main(String[] args) {
        Solution solution = new P922SortArrayByParityIi().new Solution();
        int[] ints = solution.sortArrayByParityII(new int[]{4,2,5,7});
        System.out.println(Arrays.toString(ints));

        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] sortArrayByParityII(int[] A) {
            int i = 0;
            int j = 1;
            while (i < A.length && j < A.length) {
                while (i < A.length && A[i] % 2 == 0)
                    i += 2;
                while (j < A.length && A[j] % 2 != 0)
                    j += 2;
                if (i < A.length && j < A.length)
                    swap(A, i, j);
            }
            return A;
        }

        private int[] getInts_3(int[] A) {
            for (int i = 0; i < A.length; i++) {
                boolean b1 = i % 2 == 0;
                boolean b2 = A[i] % 2 == 0;
                if (b1 == b2) {
                    continue;
                }
                // 偶数位放置奇数
                if (b1 && !b2) {
                    for (int j = i + 1; j < A.length; j += 2) {
                        if (j % 2 != 0 && A[j] % 2 == 0) {
                            swap(A, i, j);
                        }
                    }
                }
                // 奇数位放置偶数
                if (!b1 && b2) {
                    for (int j = i + 1; j < A.length; j += 2) {
                        if (j % 2 == 0 && A[j] % 2 != 0) {
                            swap(A, i, j);
                        }
                    }
                }
            }
            return A;
        }

        private int[] getInts_2(int[] A) {
            int[] res = new int[A.length];
            int a = 0;
            int b = 1;
            for (int aA : A) {
                if (aA % 2 == 0) {
                    res[a] = aA;
                    a += 2;
                } else {
                    res[b] = aA;
                    b += 2;
                }
            }
            return res;
        }

        private int[] getInts_1(int[] A) {
            int a[] = new int[A.length / 2];// 奇数位错误
            int b[] = new int[A.length / 2];// 偶数位错误
            int aIndex = 0;
            int aNextIndex = 0;
            int bIndex = 0;
            int bNextIndex = 0;

            for (int i = 0; i < A.length; i++) {
                boolean b1 = i % 2 == 0;
                boolean b2 = A[i] % 2 == 0;
                if (b1 == b2) {
                    continue;
                }
                // 偶数位放置奇数
                if (b1 && !b2) {
                    if (aIndex < aNextIndex) {
                        int i1 = a[aIndex];
                        swap(A, i, i1);
                        aIndex++;
                    } else {
                        b[bNextIndex++] = i;
                    }
                }
                // 奇数位放置偶数
                if (!b1 && b2) {
                    if (bIndex < bNextIndex) {
                        int i1 = b[bIndex];
                        swap(A, i, i1);
                        bIndex++;
                    } else {
                        a[aNextIndex++] = i;
                    }
                }

            }


            return A;
        }

        private void swap(int[] a, int i, int j) {
            a[i] = a[i] ^ a[j];
            a[j] = a[i] ^ a[j];
            a[i] = a[i] ^ a[j];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
