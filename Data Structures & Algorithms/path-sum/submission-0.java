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
class Solution {
    
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