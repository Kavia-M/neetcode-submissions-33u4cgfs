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
class Solution_iteration {
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

// Morris Traversal
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        TreeNode curr = root;

        while(curr != null) { // ensure we dont make leaf node's left or right (null) as curr
            System.out.println(curr.val);
            if(curr.left == null) {
                // no left, visit curr, move to right
                ans.add(curr.val);
                curr = curr.right; // if right also null means we exit while (above curr is root node)
            }
            else { // we have LEFT 
                // find predecessor THIS IS DONE 2 TIMES
                // One time predecessor's right is null, so we add curr as it's right
                // next time when we come to find predecessor we can find it's right is already curr
                // so we can confirm that the left is fully procressed, now we can add curr to ans and make this pedecessor's right to again null and move to curr's right
                // right most node of left, can be left the node itself
                TreeNode prev = curr.left;
                while(prev.right!= null && prev.right!= curr) { // if right is null means we find right most node, right is curr means that is already visited predecessor
                    prev = prev.right; // traversal to find predecessor
                }
                if(prev.right == null) {
                    // not visited in predecessor search already
                    prev.right = curr;
                    // move curr to its left since we have not processed left fully
                    curr = curr.left;
                }
                else {
                    // means prev.right==curr
                    // curr's left is already processed once, now again processing and checking for predecessor
                    // how again curr is visited ? because after last time process we made the predecessor's right as curr
                    // usign that naturally after procesing left subtree's last node (which has left as null) we naturally moved to curr using left subtree's last node (predecessor)'s right pointing to curr
                    // since left subtree's last right node already processed we can now add the curr node to ans

                    ans.add(curr.val);

                    // now we confirmed curr's left is processsed, curr value also added to answer, we can move to curr's right
                    curr = curr.right;

                    // to avoid currupted tree make prev right to  null again (THOUGH THIS DOES NOT MAKE A LOOP IN SOLUTION)
                    prev.right = null;
                    // why no loop ? because we safely move to right part fo curr, the prev lies in left subtree of curr so this prev's right whether currupted or not does nto affect this solution
                    
                }
            }

        }
        return ans;
    }
}