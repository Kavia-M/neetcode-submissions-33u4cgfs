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

class Solution_Simple_combining_other_answers {
    
    private List<Integer> ans = new ArrayList<>(); 
    
    private List<Integer> inorderTraversal(TreeNode root) {
        inorder(root);
        return ans;
    }
    private void inorder(TreeNode root) {
        if(root == null) return;
        inorder(root.left);
        ans.add(root.val);
        inorder(root.right);
    }

    private static <T extends Comparable<? super T>> boolean isStrictlySorted(Iterable<T> iterable) {
        Iterator<T> iter = iterable.iterator();
        if (!iter.hasNext()) return true;
    
        T prev = iter.next();
        while (iter.hasNext()) {
            T curr = iter.next();
            if (prev.compareTo(curr) >= 0) return false;
            prev = curr;
        }
        return true;
    }
    
    public boolean isValidBST(TreeNode root) {
        List<Integer> inorder = inorderTraversal(root);

        return isStrictlySorted(inorder);
    }
} // O(n) space complexity worst case skew tree in recursion, O(n) time complexity for inorder and then sort check, each O(n)


// USING Morris Traversal
// Inorder morris
// Only one thing, while processing the node we check if it is less than the upcoming node in inorder traversal

// Morris Traversal
class Solution {
    public boolean isValidBST(TreeNode root) {
        int previous = Integer.MIN_VALUE;
        TreeNode curr = root;

        while(curr != null) { // ensure we dont make leaf node's left or right (null) as curr
            if(curr.val <= previous) return false;
            if(curr.left == null) {
                // no left, visit curr, move to right
                previous = curr.val;
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

                    previous = curr.val;

                    // now we confirmed curr's left is processsed, curr value also added to answer, we can move to curr's right
                    curr = curr.right;

                    // to avoid currupted tree make prev right to  null again (THOUGH THIS DOES NOT MAKE A LOOP IN SOLUTION)
                    prev.right = null;
                    // why no loop ? because we safely move to right part fo curr, the prev lies in left subtree of curr so this prev's right whether currupted or not does nto affect this solution
                    
                }
            }

        }
        return true;
    }
}