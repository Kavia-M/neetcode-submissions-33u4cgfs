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
    private int maxSubArray(List<Integer> nums) {

        int maxSum = nums.get(0);
        int curSum = 0;

        for(int n : nums){

            if(curSum < 0)
                curSum = 0;

            curSum += n;

            maxSum = Math.max(maxSum, curSum);
        }

        return maxSum;
    }
    List<Integer> inorderList = new ArrayList<>();
    private void inorder(TreeNode node) {
        if(node == null) return;
        inorder(node.left);
        inorderList.add(node.val);
        inorder(node.right);
    }
    public int maxPathSum(TreeNode root) {
        inorder(root);
        return maxSubArray(inorderList);
    }
}
