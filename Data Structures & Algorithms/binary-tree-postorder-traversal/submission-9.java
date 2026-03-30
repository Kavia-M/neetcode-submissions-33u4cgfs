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
class Solution_iterative_2 {
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

// Like Given Iterative DFS 2 solution
// Inspired from chatgpt's preorder solution
class Solution_iterative_inspired_from_chatgpt {
    public List<Integer> postorderTraversal(TreeNode root) {
        // we need to do preorder like thing but CURR, RIGHT, LEFT and reverse it to LEFT, RIGHT, CURR
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>(); // Deque can also be used

        st.push(root);

        while(!st.isEmpty()) {
            TreeNode curr = st.pop();
            if(curr!=null) {
                ans.add(curr.val);
                st.push(curr.left);
                st.push(curr.right); // this will be popped and processed first
            }
        }
        // it takes extra n space as it is not inplace
        ans = ans.reversed(); // valid from java 21, since this compiler used java 21 this can be used here otherwise Collections.reverse(ans); does inplace reversal
        return ans;
    }
}

// Morris Traversal Modified from PREORDER traversal
// ONLY ONE RULE, Curr, right, left and then reverse
// so here prev is left most node of the right subtree
// Add curr to ans before moving to right
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        TreeNode curr = root;

        while(curr != null) { // ensure we dont make leaf node's left or right (null) as curr

            if(curr.right == null) {
                // no right, we cant move to right as it does not exist, ADD CURR to ans, move to LEFT            
                ans.add(curr.val);
                curr = curr.left; // if left also null means we exit while (above curr is root node)
            }
            else { // we have RIGHT 
                // find predecessor THIS IS DONE 2 TIMES
                // One time predecessor's left is null, so we add curr as it's left
                // next time when we come to find predecessor we can find it's left is already curr
                // so we can confirm that the right subtree is fully procressed, curr already added to ans BEFORE RIGHT NODE PROCESSED, make this pedecessor's left to again null and move to curr's left
                // prev is left most node of right, can be right the node itself
                TreeNode prev = curr.right;
                while(prev.left!= null && prev.left!= curr) { // if left is null means we find left most node, left is curr means that is already visited predecessor
                    prev = prev.left; // traversal to find predecessor
                }
                if(prev.left == null) {
                    // not visited in predecessor search already
                    prev.left = curr;
                    // move curr to its right since we have not processed right fully
                    // add curr to ans before moving to right
                    ans.add(curr.val);
                    curr = curr.right;
                }
                else {
                    // means prev.left==curr
                    // curr's right is already processed once, now again processing and checking for predecessor
                    // how again curr is visited ? because after last time process we made the predecessor's left as curr
                    // using that naturally after procesing right subtree's last node (which has right as null) we naturally moved to curr using right subtree's last node (predecessor)'s left pointing to curr
                    // since right subtree's last right node already processed, this curr is already added to ans, we can move to LEFT subtree

                    // now we confirmed curr's right is processsed, curr value also added to answer BEFORE RIGHT IS PROCESSED, we can move to curr's left
                    
                    //  THIS section confirms right is processed fully, before right curr is added to ans, so no need to add curr to ans again
                    curr = curr.left;

                    // to avoid currupted tree make prev left to  null again (THOUGH THIS DOES NOT MAKE A LOOP IN SOLUTION)
                    prev.left = null;
                    // why no loop ? because we safely move to left part of curr, the prev lies in right subtree of curr so this prev's left whether currupted or not does not affect this solution
                    
                }
            }

        }
        // do not use ans = ans.reversed(); since this creates extra n space
        Collections.reverse(ans); // inplace O(n) time complexity no extra space
        return ans;
    }
}