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

class Solution {
    
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

    private static <T extends Comparable<? super T>> boolean isSorted(Iterable<T> iterable) {
        Iterator<T> iter = iterable.iterator();
        if (!iter.hasNext()) return true;
    
        T prev = iter.next();
        while (iter.hasNext()) {
            T curr = iter.next();
            if (prev.compareTo(curr) > 0) return false;
            prev = curr;
        }
        return true;
    }
    
    public boolean isValidBST(TreeNode root) {
        List<Integer> inorder = inorderTraversal(root);

        return isSorted(inorder);
    }
}
