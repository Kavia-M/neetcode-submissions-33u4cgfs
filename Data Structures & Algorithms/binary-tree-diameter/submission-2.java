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

// Diameter at any one node in middle can be more than that of root
// Diameter of any node is depth of left subtree + depth of right subtree
class Solution_with_assumption {
    private int max_dia = -1; // if root is null -1 is ans (a rule we ourself define)
    // only root there 0 is answer
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return -1;
        int dia = height(root.left) + height(root.right);
        return Math.max(Math.max(dia, diameterOfBinaryTree(root.left)), diameterOfBinaryTree(root.right));
    }
    public int height(TreeNode node) {
        if(node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));    
    }
}

class Solution_Brute_Force {
    // O(n2) 
    // calculating 'int dia' is O(n) becaue height is O(n) . O(n+n) = (n)
    // diameterOfBinaryTree function is called for each node - n times
    // inside diameterOfBinaryTree, we have O(n)
    // n*O(n) = O(n2) that is order n square

    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0; // if root is null 0 is ans (without assumption or self defined rule)
        int dia = height(root.left) + height(root.right); // until this part it is O(n)
        return Math.max(Math.max(dia, diameterOfBinaryTree(root.left)), diameterOfBinaryTree(root.right));
    }
    public int height(TreeNode node) {
        if(node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));    
    }
}

// In Brute force we can see height is calculated n2 times, many times repeated
// DFS of calculate Height only, add a step in between to get max of diameter
class Solution {
    private int maxDia = 0; // worst case root null means also return 0
    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return maxDia;
    }

    public int height(TreeNode node) {
        if(node == null) return 0;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        // Post order processing
        maxDia = Math.max(maxDia, (leftHeight + rightHeight));
        return 1 + Math.max(leftHeight, rightHeight);  // np need to call again
    }
}