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

class Solution {
    private int count = 0; // count of good nodes
    public int goodNodes(TreeNode root) {
        // 2 things while passing root, we can keep maxSoFar as IntMIN this is correct
        // because max so far is max before this node
        // but even if we pass root value itself, it also work because we check <= only (= there)

        countGoodNodes(root, Integer.MIN_VALUE);

        return count;
    }
    private void countGoodNodes(TreeNode node, int maxSoFar) {
        if(node == null) return;
        if(maxSoFar <= node.val) {
            count++;
            maxSoFar = node.val;
        }
        countGoodNodes(node.left, maxSoFar);
        countGoodNodes(node.right, maxSoFar);
    }


}
