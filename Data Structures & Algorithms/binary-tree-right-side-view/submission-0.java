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
class Solution {
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
