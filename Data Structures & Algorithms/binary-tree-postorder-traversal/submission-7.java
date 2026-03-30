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

class Solution_iterative {
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

// Iterative Simple 1 from Solution
/*
1st time pushing with visited false
2nd time pushing with visited true
*/
class Solution_iterative_1 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        Stack<Boolean> visited = new Stack<>();

        st.push(root); // NULL CAN ALSO BE PUSHED INTO STACK
        visited.push(false);

        while(!st.isEmpty()) { 
            TreeNode curr = st.pop();
            Boolean vis = visited.pop();

            // check non null
            if(curr != null) {
                if(vis == true) {
                    // already visited, right processed
                    // add to ans and move to next iteration
                    // in next iteration we will pop it and continue
                    ans.add(curr.val);
                }
                else {
                    // push back this node with visited true
                    // RIGHT LEFT with visited false
                    // because left will be popped out 1st
                    st.push(curr);
                    visited.push(true);
                    st.push(curr.right);
                    visited.push(false);
                    st.push(curr.left);
                    visited.push(false);
                }
            }
        }
        return ans;
    }
}

// Given Iterative DFS 2 solution
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        // we need to do preorder like thing but CURR, RIGHT, LEFT and reverse it to LEFT, RIGHT, CURR
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>(); // Deque can also be used

        TreeNode curr = root;

        while(curr!=null || !st.isEmpty()) {
            if(curr!=null) {
                ans.add(curr.val); // add curr
                st.push(curr.left); // store left for process later
                curr = curr.right; // move to right subtree
            }
            else {
                curr = st.pop(); // right is completed, processing the stored left nodes
            }
        }

        Collections.reverse(ans); // O(n) inplace reversal
        // ans = ans.reversed(); // from java 21 (use d - reversed)
        return ans;
    }
}