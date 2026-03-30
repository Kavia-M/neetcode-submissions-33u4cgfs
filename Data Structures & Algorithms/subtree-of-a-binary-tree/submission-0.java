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

// FROM PREVIOUS SAME TREE QUESTION
class Solution {  
    private boolean isSameTree(TreeNode p, TreeNode q) {
        // both are null
        if(p == null && q==null) 
            return true;
        // any one null (both can't be null here because it is already checked)
        if(p==null || q==null)
            return false;
        
        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);
        boolean val = p.val == q.val;

        return left && right && val; 
    }
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // SUBTREE is smaller or equal one
        if(root == null)
            return isSameTree(root, subRoot); // there it will check for null, null case
        if(subRoot == null)
            // null can be subtree of any tree
            return true;
        
        // We will return true 1st, if no true atlast return false
        if(root.val == subRoot.val && isSameTree(root, subRoot)) {
            return true;
        } 
        else {
            // VAL not equal or isSameTree is false -> check left and right
            if(isSubtree(root.left, subRoot))
                    return true;
            if(isSubtree(root.right, subRoot))
                    return true;
        }
        return false;
    }
}
