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
class Solution_iterative {
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

// This solution is given by ChatGPT
// Why we use this ?
/*
    since it preorder, curr node, left, right
    means left is processed before right
    so pop is happen LIFO 
    so last in should be left 
    so push rigth and then left
*/
class Solution_iterative_simple {
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        TreeNode curr = root;
        // ANY THING NON NULL only though technically it can allow adding null in Stack container wrapper in java
        if(curr!= null)
            st.push(curr); // 1st func call
        while(!st.isEmpty()) {
            curr = st.pop();
            // visit it and push it's right and left (only if not null)
            ans.add(curr.val);
            
            if(curr.right != null)
                st.push(curr.right);
            if(curr.left != null)
                st.push(curr.left);
        }
        
        return ans;
    }
}

// Morris Traversal Modified from Inorder traversal
// ONLY ONE RULE, before moving to left, visit this node and add to ans
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        TreeNode curr = root;

        while(curr != null) { // ensure we dont make leaf node's left or right (null) as curr

            if(curr.left == null) {
                // no left, we cant move to left as it does not exist, ADD CURR to ans, move to right            
                ans.add(curr.val);
                curr = curr.right; // if right also null means we exit while (above curr is root node)
            }
            else { // we have LEFT 
                // find predecessor THIS IS DONE 2 TIMES
                // One time predecessor's right is null, so we add curr as it's right
                // next time when we come to find predecessor we can find it's right is already curr
                // so we can confirm that the left is fully procressed, curr already added to ans BEFORE LEFT NODE PROCESSED, make this pedecessor's right to again null and move to curr's right
                // prev is right most node of left, can be left the node itself
                TreeNode prev = curr.left;
                while(prev.right!= null && prev.right!= curr) { // if right is null means we find right most node, right is curr means that is already visited predecessor
                    prev = prev.right; // traversal to find predecessor
                }
                if(prev.right == null) {
                    // not visited in predecessor search already
                    prev.right = curr;
                    // move curr to its left since we have not processed left fully
                    // add curr to ans before moving to left
                    ans.add(curr.val);
                    curr = curr.left;
                }
                else {
                    // means prev.right==curr
                    // curr's left is already processed once, now again processing and checking for predecessor
                    // how again curr is visited ? because after last time process we made the predecessor's right as curr
                    // using that naturally after procesing left subtree's last node (which has left as null) we naturally moved to curr using left subtree's last node (predecessor)'s right pointing to curr
                    // since left subtree's last right node already processed, this curr is already added to ans, we can move to right

                    // now we confirmed curr's left is processsed, curr value also added to answer BEFORE LEFT IS PROCESSED, we can move to curr's right
                    
                    //  THIS section confirms left is processed fully, before left curr is added to ans, so no need to add curr to ans again
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