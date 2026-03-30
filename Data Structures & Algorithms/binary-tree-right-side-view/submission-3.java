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

// USE reference of last quiestion levels in binary tree
class Solution_BFS {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        
        // include non null only in Queue
        Queue<TreeNode> q = new ArrayDeque<>();

        if(root == null) return ans;

        q.offer(root);

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i < size; i++) {
                // if it is last one in this level add to answer
                TreeNode curr = q.poll();
                // no need null check

                if(i==size-1) {
                    ans.add(curr.val);
                }

                // add only non null children
                if(curr.left!=null) q.offer(curr.left);
                if(curr.right!=null) q.offer(curr.right);
            }
        }
        return ans;
    }
}

// DFS (SOLUTION 1) ans inspired from last question (levels)
// difference here is we visit right and then left in recurrsion
// in last question if the size of the outer array is equal to current depth. 
// then this current node is the 1st node in that level so we created a new list
// this time if this is 1st node we add it into ans list (1st node of 1 level will be right since we visit right and then left)
class Solution_given_SOLUTION_1 {
    // since all function call shares the same list using call be reference
    // we can make it class variable accessible to all 
    private List<Integer> ans = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return ans;
    }
    private void dfs(TreeNode node, int depth) {
        if(node == null) return;

        if(ans.size() == depth) ans.add(node.val);

        dfs(node.right, depth + 1);
        dfs(node.left, depth + 1);
    }
}

// MOdified DFS captures all nodes in level, and updates if that level already present (given in common pitfalls)
class Solution_DFS_2 {
    // since all function call shares the same list using call be reference
    // we can make it class variable accessible to all 
    private List<Integer> ans = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return ans;
    }
    private void dfs(TreeNode node, int depth) {
        if(node == null) return;

        if(ans.size() == depth) ans.add(node.val);
        else // already present
            ans.set(depth, node.val);

        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }
}

// MOdified DFS captures all nodes in level, and updates if that level already present (given in common pitfalls)
// Exception handling
class Solution {
    // since all function call shares the same list using call be reference
    // we can make it class variable accessible to all 
    private List<Integer> ans = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return ans;
    }
    private void dfs(TreeNode node, int depth) {
        if(node == null) return;

        try {
            ans.set(depth, node.val);
        }
        catch(IndexOutOfBoundsException e) {
            // index out of bound means we need to add to list , 1st element in this level
            ans.add(node.val);
        }
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }
}