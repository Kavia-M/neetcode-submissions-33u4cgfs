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
class Solution_recurrsion_1 {
    List<Integer> ans = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null) return ans;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        ans.add(root.val);
        return ans;
    }
}

class Solution_recurrsion_2 {
    private List<Integer> ans = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        postorder(root);
        return ans;
    }
    private void postorder(TreeNode node) {
        if(node == null) return;
        postorder(node.left);
        postorder(node.right);
        ans.add(node.val);
    }
}

// Iterative solution inspired from Inorder and preorder traversal 
// this mimics recurrsion
// This is given by chatgpt

/*
Postorder = Left → Right → Node.
We push nodes while going left (like recursion calls).
When reaching null, we check the top node's right child.
If the right subtree is not processed, we move to it.
Otherwise both children are done, so we visit the node and pop it.
*/

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        TreeNode curr = root;
        TreeNode lastVisited = null;

        while (curr != null || !st.isEmpty()) {

            if (curr != null) {
                st.push(curr);       // function call
                curr = curr.left;    // go left first
            } else {

                TreeNode peek = st.peek();

                // if right subtree exists and not processed
                if (peek.right != null && lastVisited != peek.right) {
                    curr = peek.right;
                } else {
                    ans.add(peek.val);   // visit node
                    lastVisited = st.pop();
                }
            }
        }

        return ans;
    }
}
