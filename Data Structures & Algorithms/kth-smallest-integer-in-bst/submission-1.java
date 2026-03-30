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

class Solution_recurrsive {
    // in order search
    private int globalK;
    private TreeNode kthSmallestNode(TreeNode root) {
         if(root == null) return root;
        if(globalK==0) return root;
        
        TreeNode left = kthSmallestNode(root.left);

        if(left != null) {
            return left;
        }
        // process node 
        globalK--;
        if(globalK==0) return root;
        return  kthSmallestNode(root.right);
    }
    public int kthSmallest(TreeNode root, int k) {
        globalK = k;
        TreeNode ans = kthSmallestNode(root);
        if(ans == null) return Integer.MIN_VALUE;
        return ans.val;
    }
}

// iterative Inorder DFS
class Solution {
    // in order search
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> st = new Stack<>();
        TreeNode curr = root;

        while(curr != null || !st.isEmpty()) {
            while(curr!=null) {
                st.push(curr);
                curr = curr.left;
            }
            // curr is null now
            curr = st.pop();

            // all left processed, now process this
            k--;
            if(k==0) return curr.val;

            // go to right
            curr = curr.right;
        }
        return Integer.MIN_VALUE;
    }
}
