/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// BFS using Q
class Solution_BFS {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();

        if(root == null) return levels;
        
        q.offer(root);

        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for(int i=0; i<size; i++) {
                // we always push non null, array deque wont accept null input
                TreeNode curr = q.poll();
                level.add(curr.val);

                if(curr.left != null) q.offer(curr.left);
                if(curr.right != null) q.offer(curr.right);
            }
            levels.add(level);
        }
        return levels;
    }
}

// GIVEN SOLUTION 1, DFS using depth (or level of each node), start from 0
class Solution_GIVEN_SOLUTION_1_with_small_change {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, 0, ans); // because index 0 is not present in array ans now
        return ans;
    }

    private void dfs(TreeNode node, int depth, List<List<Integer>> ans)
    {
        // int depth is call by value
        // ans is list passed by reference
        // the index using depth in the array gives the inner array for that level

        // at any time either level will be in ans or atleast previous level will be in answer

        // if size is this level, it means this level is not present as index in the ans list

        if(node == null) {
            // do not do anything, this is not a new level
            return;
        }

        if(ans.size() == depth) {
            // this level is not as an index. we add this level at end
            ans.add(new ArrayList<>()); // no need to mention Integer, it will take automatically
        }

        ans.get(depth).add(node.val); // get this level inner array and add value

        // call dfs for left and right, these children will have one depth more
        dfs(node.left, 1+depth, ans);
        dfs(node.right, 1+depth, ans);
    }
}

// DFS
class Solution_given_SOLUTION_1 {
    // since all function call shares the same list using call be reference
    // we can make it class variable accessible to all 
    private List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        dfs(root, 0);
        return ans;
    }
    private void dfs(TreeNode node, int depth) {
        if(node == null) return;

        if(ans.size() == depth) ans.add(new ArrayList<>());
        // if not present earliear means also we added inner list with this depth as index in above line
        ans.get(depth).add(node.val);

        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }
}

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>(); // allowing null

        q.add(root); // or offer

        while(!q.isEmpty()) {
            List<Integer> level = new ArrayList<>();

            // instead of savinfg q.size() in separate int, 
            // we make it in for loop initialization which happen only once
            for(int i = q.size(); i>0; i--) { // include q.size and not include 0, this is like 1 based indexing
                TreeNode curr = q.remove(); // or poll
                if(curr == null) continue;

                level.add(curr.val);
                q.add(curr.left);
                q.add(curr.right);
            }

            // actually the null check continue is only for the inner for loop
            // if all nodes are null in this level, we still have empty arraylist initialized before for loop and nothing added
            // do not include empty lists as inner lists in ans. empty list is not a level
            if(!level.isEmpty()) // or level.size() > 0
                levels.add(level);
        }
        return levels;
    }
}
