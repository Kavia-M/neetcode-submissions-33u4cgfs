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

 // https://www.geeksforgeeks.org/dsa/print-path-root-given-node-binary-tree/
 // https://takeuforward.org/plus/dsa/problems/print-root-to-note-path-in-bt?tab=editorial


class GET_ALL_PATHS_TO_NODES {
    private void getAllPaths(TreeNode node, List<List<Integer>> paths, List<Integer> currPath) {
        if(node==null)
            return;
        currPath.add(node.val);
        if(node.left==null && node.right==null) {
            // important point, here after steps we remove currPath integer elements, so save a copy of this currPath in paths
            paths.add(new ArrayList<>(currPath));
            currPath.remove(currPath.size() - 1);
            return;
        }
        getAllPaths(node.left, paths, currPath);
        getAllPaths(node.right, paths, currPath);

        currPath.remove(currPath.size() -1);      
    }
    public List<List<Integer>> allRootToLeaf(TreeNode root) {
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> currPath = new ArrayList<>();
        getAllPaths(root, paths, currPath);
        return paths;
    }
}

// given solution 1
class Solution_GIVEN_SOLUTION_1 {
    
    private boolean dfs(TreeNode node, int currSum, int targetSum) {
        // very very very important
        // HERE int is call by Value, so no need  to minus at end, because the caller function will retain it's original currsum
        if(node == null) return false;
        
        currSum += node.val;
        
        if(node.left == null && node.right == null) {
            return currSum == targetSum;
        }

        return dfs(node.left, currSum, targetSum) || dfs(node.right, currSum, targetSum);
    }
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // null root will be returned with false from dfs 
        return dfs(root, 0, targetSum);
    }
}

// given solution 1 modified since targetSum is not changing in overall all functions
class Solution_GIVEN_SOLUTION_1_modified {
    private int targetSum;
    private boolean dfs(TreeNode node, int currSum) {
        // very very very important
        // HERE int is call by Value, so no need  to minus at end, because the caller function will retain it's original currsum
        if(node == null) return false;
        
        currSum += node.val;
        
        if(node.left == null && node.right == null) {
            return currSum == targetSum;
        }

        return dfs(node.left, currSum) || dfs(node.right, currSum);
    }
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // null root will be returned with false from dfs 
        this.targetSum = targetSum;
        return dfs(root, 0);
    }
}

// GIVEN Solution 2, since target sum can be decreased and as we told the caller method always has its original target sum CALL BY VALUE, future function's decrement will not affect this
public class Solution_GIVEN_SOLUTION_2 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        targetSum -= root.val;
        if(root.left == null && root.right==null) {
            return targetSum == 0;
        }
        return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
    }
}

// Given solution 3, ITERATIVE
public class Solution_GIVEN_SOLUTION_3 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // we cannot push null to stack because we can we push targetSum - currVal ?
        if(root == null) return false;
        // 2 parallel stacks used instaed of pair in a stack
        Stack<TreeNode> st = new Stack<>();
        Stack<Integer> remainingTargetSt = new Stack<>();

        st.push(root);
        // after pushing each node, push the remaining target subtracting this node's value
        remainingTargetSt.push(targetSum - root.val);

        while(!st.isEmpty()) {
            TreeNode curr = st.pop();
            int currRemainingTarget = remainingTargetSt.pop();

            // if this is leaf node, it's remaining taget (that was pushed after considering this node's value should be zero)
            if(curr.left == null && curr.right == null && currRemainingTarget == 0)
                return true;
            
            // only non null can be added, for pre order right and then left is pushed
            if(curr.right != null) {
                st.push(curr.right);
                remainingTargetSt.push(currRemainingTarget - curr.right.val);
            }
            
            if(curr.left != null) {
                st.push(curr.left);
                remainingTargetSt.push(currRemainingTarget - curr.left.val);
            }
        }
        // we checked all leaf nodes
        return false;
    }
}

// Given solution 3, BFS, it is same as iterative DFS
// here in any orrder we can visit nodes, we need to keep the currSum and whether it is leaf node only
// we are not getting anything back from children
// so same iteratve DFS can be done in BFS usign Q instead of stack
public class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        // as we know we are not going to put null into Q we can use arraydequeue
        // instad of Q interface we use ArrayDeque because we can have offerFirst etc
        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        ArrayDeque<Integer> remainingTargetQ = new ArrayDeque<>();

        // add is same as addFirst First In First Out , offerFirst for null safety (though it is always safe)
        q.offerFirst(root);
        remainingTargetQ.offerFirst(targetSum - root.val);

        while(!q.isEmpty()) {
            TreeNode curr = q.pollFirst();
            int currRemainingTarget = remainingTargetQ.pollFirst();

            if(curr.left == null && curr.right == null && currRemainingTarget == 0)
                return true;
            
            // left and right for BFS
            if(curr.left !=  null) {
                q.offerFirst(curr.left);
                remainingTargetQ.offerFirst(currRemainingTarget - curr.left.val);
            }
            
            if(curr.right !=  null) {
                q.offerFirst(curr.right);
                remainingTargetQ.offerFirst(currRemainingTarget - curr.right.val);
            }
        }
        return false;
    }
}
