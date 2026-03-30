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

 // ANYTHING USING recurrsion wil become depth 1st search

class Solution_DFS_recurrsion {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}

// Iterative DFS this uses Preorder
// since we pop the node and process it (swap childre)
// push the children into stack
// we can push children in any order because this does not affect ans as we only swap
class Solution_iterartive_DFS_1 {
    public TreeNode invertTree(TreeNode root) {
        // we can push null into stack and handle it in while loop
        Stack<TreeNode> st = new Stack<>();

        st.push(root);

        while(!st.isEmpty()) {
            TreeNode curr = st.pop();
            if(curr == null) continue; // since we push null also
            TreeNode temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;

            st.push(curr.left);
            st.push(curr.right);
        }

        // the root is unchanged
        return root;        
    }
}
class Solution {
    public TreeNode invertTree(TreeNode root) {
        // we can push null into stack and handle it in while loop
        Stack<TreeNode> st = new Stack<>();

        st.push(root);

        while(!st.isEmpty()) {
            TreeNode curr = st.pop();
            if(curr == null) continue; // since we push null also
            TreeNode temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;

            st.push(curr.right);
            st.push(curr.left);
        }

        // the root is unchanged
        return root;        
    }
}
