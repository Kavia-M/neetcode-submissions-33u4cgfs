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
class Solution_iterative {
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

// MORRIS TRAVERSAL
class Solution {
    // in order search
    public int kthSmallest(TreeNode root, int k) {
        TreeNode curr = root;

        while(curr != null) {
            if(curr.left == null) {
                // process curr
                k--;
                if(k==0) return curr.val;
                curr = curr.right;
                continue;
            }
            // curr left is not null, we have to go to left
            TreeNode prev = curr.left;
            while(prev.right != null && prev.right != curr) {
                prev = prev.right;
            }
            if(prev.right == null) {
                prev.right = curr;
                curr = curr.left; // we have set the pointer, we can go to left subtree now
            }
            else { // prev.right == curr
                prev.right = null;
                // process curr
                k--;
                if(k==0) return curr.val;
                // we can move curr to it's right side
                curr = curr.right;
            }
        }
        return Integer.MIN_VALUE;
    }
}
