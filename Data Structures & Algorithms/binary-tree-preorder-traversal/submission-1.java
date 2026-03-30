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
class Solution_recursion {
    ArrayList<Integer> ans = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) return ans;

        ans.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);

        return ans;
    }
}

// this Solution is moderation of the Inorder iterative solution which mimics recursion
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        TreeNode curr = root;

        while(curr!=null || !st.isEmpty()) {
            while(curr!=null) {
                // curr will call left 1st
                // that left will call its left and so on till end
                // this is like func call, so callign func is put into stack for process later
                // THIS IS PRE ORDER, so while pushing itself we have to visit and then move to next func call which is adding new elemets in stack
                ans.add(curr.val);
                st.push(curr);
                curr = curr.left;
            }

            // while loop get finished when no left exists, curr becomes null
            // so in this time we will process the node we put into stack
            // the called function returned to the calling func now
            curr = st.pop();
            // the above is already added to ans while pushing itself

            // after this move to right tree and process that
            curr = curr.right; // this becomes curr in next iteration and processed same way
        }

        // all processed and stack empty means func calls finished
        return ans;
    }
}
    