//给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。 
//
// 要求返回这个链表的 深拷贝。 
//
// 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示： 
//
// 
// val：一个表示 Node1.val 的整数。
// random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为 null 。 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
// 
//
// 示例 2： 
//
// 
//
// 输入：head = [[1,1],[2,1]]
//输出：[[1,1],[2,1]]
// 
//
// 示例 3： 
//
// 
//
// 输入：head = [[3,null],[3,0],[3,null]]
//输出：[[3,null],[3,0],[3,null]]
// 
//
// 示例 4： 
//
// 输入：head = []
//输出：[]
//解释：给定的链表为空（空指针），因此返回 null。
// 
//
// 
//
// 提示： 
//
// 
// -10000 <= Node1.val <= 10000
// Node1.random 为空（null）或指向链表中的节点。
// 节点数目不超过 1000 。 
// 
// Related Topics 哈希表 链表


package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//Java：复制带随机指针的链表
public class P138CopyListWithRandomPointer{
    public static void main(String[] args) {
        Node1 node1 = new Node1(7);
        Node1 node2 = new Node1(13);
        Node1 node3 = new Node1(11);
        Node1 node4 = new Node1(10);
        Node1 node5 = new Node1(1);

        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;


        Node1.print(node1);
        Solution solution = new P138CopyListWithRandomPointer().new Solution();
        Node1 node = solution.copyRandomList(node1);
        Node1.print(node);


        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node1.
class Node1 {
    int val;
    Node1 next;
    Node1 random;

    public Node1(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node1 copyRandomList(Node1 head) {
        if (head == null) {
            return null;
        }
        Map<Node1,Node1> oldIndexNode = new HashMap<>(1000);


        Node1 temp = head.next;
        Node1 newHead = new Node1(head.val);
        oldIndexNode.put(head,newHead);
        Node1 newTemp = newHead;

        while (temp != null) {
            Node1 newNode = new Node1(temp.val);
            newTemp.next = newNode;
            newTemp = newTemp.next;
            temp = temp.next;
            oldIndexNode.put(temp,newNode);
        }
        temp = head;
        newTemp = newHead;
        while (temp != null) {
            if (temp.random != null) {
                newTemp.random = oldIndexNode.get(temp.random);
            }
            temp = temp.next;
            newTemp = newTemp.next;
        }
        return newHead;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
