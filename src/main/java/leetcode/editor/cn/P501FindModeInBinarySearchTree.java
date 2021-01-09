//给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。 
//
// 假定 BST 有如下定义： 
//
// 
// 结点左子树中所含结点的值小于等于当前结点的值 
// 结点右子树中所含结点的值大于等于当前结点的值 
// 左子树和右子树都是二叉搜索树 
// 
//
// 例如： 
//给定 BST [1,null,2,2], 
//
//    1
//    \
//     2
//    /
//   2
// 
//
// 返回[2]. 
//
// 提示：如果众数超过1个，不需考虑输出顺序 
//
// 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内） 
// Related Topics 树


package leetcode.editor.cn;

import java.util.*;

//Java：二叉搜索树中的众数
public class P501FindModeInBinarySearchTree{
    public static void main(String[] args) {
        Solution solution = new P501FindModeInBinarySearchTree().new Solution();
        TreeNode _1 = new TreeNode(1);
        TreeNode _2 = new TreeNode(2);
        _1.right = _2;
        _2.left = new TreeNode(2);
        System.out.println(Arrays.toString(solution.findMode(_1)));
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int[] findMode(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        middle(root,res);
        Collections.sort(res);
        Set<Integer> res1 = new HashSet<>();
        int maxCount = 0;
        int nowValue = res.get(0);
        int nowValueCount = 0;
        for (Integer re : res) {
            if (re == nowValue) {
                nowValueCount++;
            } else {
                nowValue = re;
                nowValueCount = 1;
            }
            if (nowValueCount == maxCount) {
                res1.add(re);
            }
            if (nowValueCount > maxCount) {
                maxCount++;
                res1.clear();
                res1.add(re);
            }
        }

        int[] a = new int[res1.size()];
        int i=0;
        for (Integer integer : res1) {
            a[i++] = integer;
        }
        return a;
    }

    private int[] plan_A(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        middle(root,res);
        //Collections.sort(res);
        Map<Integer,Integer> count = new HashMap<>();
        for (Integer re : res) {
            Integer integer = count.get(re);
            if (integer == null) {
                count.put(re,1);
            } else {
                count.put(re,++integer);
            }
        }
        int maxCount = 0;
        List<Integer> max = new ArrayList<>();
        for (Map.Entry<Integer, Integer> integerIntegerEntry : count.entrySet()) {
            Integer value = integerIntegerEntry.getValue();
            if (maxCount == 0 || value >= maxCount) {
                if (value == maxCount) {
                    max.add(integerIntegerEntry.getKey());
                } else {
                    maxCount = value;
                    max.clear();
                    max.add(integerIntegerEntry.getKey());
                }
            }
        }

        int[] a = new int[max.size()];
        for (int i = 0; i < max.size(); i++) {
            a[i] = max.get(i);
        }
        return a;
    }

    public void middle(TreeNode root,List<Integer> res) {
        if (root == null)
            return;
        if (root.left != null) {
            middle(root.left,res);
        }
        res.add(root.val);
        if (root.right != null) {
            middle(root.right,res);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
