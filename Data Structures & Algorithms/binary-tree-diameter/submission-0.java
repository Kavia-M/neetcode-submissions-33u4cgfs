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
class Solution {
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
