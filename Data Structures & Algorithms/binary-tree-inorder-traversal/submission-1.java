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
class Solution_recurrsion {
    private List<Integer> ans = new ArrayList<>(); 
    public List<Integer> inorderTraversal(TreeNode root) {
        inorder(root);
        return ans;
    }
    private void inorder(TreeNode root) {
        if(root == null) return;
        inorder(root.left);
        ans.add(root.val);
        inorder(root.right);
    }
}

// Solution with Iteration
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        TreeNode curr = root; 
        // we are going to process Node which is same liek reccursion fucntion
        // stack will contain the nodes are the to be processed after processign this node
        // stack is liek function calls in recursion
        while(curr!=null || !st.isEmpty()) {
            while(curr!=null) {
                // curr will call left 1st
                // that left will call its left and so on till end
                // this is like func call, so callign func is put into stack for process later
                st.push(curr);
                curr = curr.left;
            }

            // while loop get finished when no left exists, curr becomes null
            // so in this time we will process the node we put into stack
            // the called function returned to the calling func now
            curr = st.pop(); // since curr always has element we process now
            // visit this curr now as left is processed
            // inorder left, curr, right
            ans.add(curr.val); // visiting

            // after visiting move to right tree and process that
            curr = curr.right; // this becomes curr in next iteration and processed same way
        }

        // all processed and stack empty means func calls finished
        return ans;
    }
}