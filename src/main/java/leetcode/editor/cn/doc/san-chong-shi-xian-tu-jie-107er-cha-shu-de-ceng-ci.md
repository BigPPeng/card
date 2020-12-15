## BFS实现
BFS遍历比较直观，就是按层遍历   
 [1.jpg](https://pic.leetcode-cn.com/1599365588-WrromC-1.jpg)


我们将我们一层遍历得到的结果放到一个数组中，等这一层遍历完后，将这个数组放入到最终结果集```res```中。   
因为题目要求是反序输出，所以我们只需将```res```翻转一下就可以了。  

我们看下如何实现这个BFS遍历
 [2.jpg](https://pic.leetcode-cn.com/1599365598-bXCiWu-2.jpg)


如上图，首先创建一个队列，将根节点```root```放入队列中，然后不断循环遍历这个队列。   
对于树的第一层，其长度为```1```，而此时根节点也放到队列中了，所以队列的长度也是```1```，我们取出一个元素，将其放入临时数组中。   
如果这个节点的左右孩子不为空，则将他们也放入队列中。   
 [3.jpg](https://pic.leetcode-cn.com/1599365607-Bstamx-3.jpg)


上图是处理第二层的过程，类似于第一层，由于第一层遍历完后，队列里有两个元素了，所以第二次遍历的时候，可以确定队列长度是```2```，于是执行两次，将```2```和```3```取出，放入临时数组中，之后再将```2```和```3```的两个孩子也放入队列中。   
处理第三层时，队列长度就是```4```了，于是取出```4```个元素，再次处理。


代码实现:
```java []
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root==null) {
            return new ArrayList<List<Integer>>();
        }
        //用来存放最终结果
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        //创建一个队列，将根节点放入其中
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            //每次遍历的数量为队列的长度
            int size = queue.size();
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            //将这一层的元素全部取出，放入到临时数组中，如果节点的左右孩子不为空，继续放入队列
            for(int i=0;i<size;++i) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if(node.left!=null) {
                    queue.offer(node.left);
                }
                if(node.right!=null) {
                    queue.offer(node.right);
                }
            }
            res.add(tmp);
        }
        //翻转最终结果并返回
        Collections.reverse(res);
        return res;
    }
}
```
```python []
class Solution(object):
    def levelOrderBottom(self, root):
        if not root:
            return []
        # 创建一个队列，将根节点放入其中    
        queue = [root]
        # 用来存放最终结果
        res = []
        while queue:
            # 每次遍历的数量为队列的长度
            size = len(queue)
            tmp = []
            # 将这一层的元素全部取出，放入到临时数组中，如果节点的左右孩子不为空，继续放入队列
            for _ in xrange(size):
                node = queue.pop(0)
                tmp.append(node.val)
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)
            res.append(tmp)
        # 翻转最终结果并返回
        return res[::-1]
```


## BFS实现-2
第一种方式中，我们将最终结果集定义成数组，等所有元素都放置完后，再翻转一下数组。   
我们可以将其结构改成链表，以后每层遍历完将结果放入到链表的第一位，这样就自动完成了翻转的功能了。


代码实现:
```java []
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root==null) {
            return new ArrayList<List<Integer>>();
        }
        //改用链表实现，每次都插入到第一位
        LinkedList<List<Integer>> res = new LinkedList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            for(int i=0;i<size;++i) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if(node.left!=null) {
                    queue.offer(node.left);
                }
                if(node.right!=null) {
                    queue.offer(node.right);
                }
            }
            //每次都保存到链表的第一位，这样自带了翻转的功能
            res.add(0,tmp);
        }
        return res;
    }
}
```
```python []
class Solution(object):
    def levelOrderBottom(self, root):
        if not root:
            return []
        queue = [root]
        # 改用双端队列实现，每次都插入到第一位
        res = collections.deque()
        while queue:
            size = len(queue)
            tmp = []
            for _ in xrange(size):
                node = queue.pop(0)
                tmp.append(node.val)
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)
            # 每次插入到第一位，这样自带了翻转的功能       
            res.appendleft(tmp)
        return list(res)
```


## DFS实现
DFS实现就不那么直观了，我们可以把二叉树结构改变一下，想象成一个三角形的结构
我们在递归遍历时，将第一层的元素放入到第一层的数组中，将第二层的的元素放入到第二层，第三层的元素放入第三层。
第三层的```4```是在```5```之前被遍历的，所以放入到第三层数组时，```4```就在```5```前面，同理```6```是在```7```前面被遍历的，所以放入到第三层时```6```就在```7```前面。
 [4.jpg](https://pic.leetcode-cn.com/1599365665-xRxWHw-4.jpg)




这次我们不用创建队列了，直接在最终结果集```res```上操作，```res```是个二维数组，集合中嵌套集合，但现在没有任何嵌套的集合。    
 [5.jpg](https://pic.leetcode-cn.com/1599365673-vLWiKf-5.jpg)


如上图所示，一开始```res```是空的，当遍历到第一层时候，我们创建一个集合，放入```res```，然后将第一层的```1```，放到```res[0]```中。   
遍历到第二层时，再创建一个集合放入```res```，然后将```2```放入```res[1]```中。   
遍历到第三层时，同样再创建一个集合放入```res```，将```4```放入```res[2]```中。   
当我们再回到第三层，遍历到```5```时，就不用再创建新集合了，直接将```5```放到到```res[2]```中即可。  
之后，再将```3```放到```res[1]```   
```6```、```7```放入```res[2]```中。    

完整的遍历过程如下图：   
 [6.gif](https://pic.leetcode-cn.com/1599365681-cWijgt-6.gif)


代码实现:
```java []
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root==null) {
            return new ArrayList<List<Integer>>();
        }
        //用来存放最终结果
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        dfs(root,res,1);
        Collections.reverse(res);
        return res;
    }

    private void dfs(TreeNode root,ArrayList<List<Integer>> res,int index) {
        if(root==null) {
            return;
        }
        //如果index大于res大小，说明这一层没有对应的集合，需要新创建
        if(index>res.size()) {
            res.add(new ArrayList<Integer>());
        }
        //将当前层的元素直接放到对应层的末尾即可
        res.get(index-1).add(root.val);
        //继续遍历左右节点，同时将层高+1
        dfs(root.left,res,index+1);
        dfs(root.right,res,index+1);
    }
}
```
```python []
class Solution(object):
    def levelOrderBottom(self, root):
        if not root:
            return []
        # 用来存放最终结果    
        res = []
        def dfs(root,index):
            if not root:
                return
            # 如果index大于res大小，说明这一层没有对应的集合，需要新创建    
            if index>len(res):
                res.append([])
            # 将当前层的元素直接放到对应层的末尾即可    
            res[index-1].append(root.val)
            # 继续遍历左右节点，同时将层高+1
            dfs(root.left,index+1)
            dfs(root.right,index+1)
        dfs(root,1)
        return res[::-1]
```

**欢迎关注 👉👉👉  [【公众号】](https://oscimg.oschina.net/oscnet/95a30f75-0d64-4b3c-8747-db31496b46dd.jpg) 👈👈👈**   

**如果能再点个赞👍👍 就更感激啦💓💓**
