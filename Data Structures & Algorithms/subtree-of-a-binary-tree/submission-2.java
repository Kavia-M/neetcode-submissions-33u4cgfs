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
// O(n) for isSubtree
// inside it O(m) for isSameTree
// overall O(n*m) time complexity
// O(n) stack space in isSubtree, O(m) in isSameTree, at worst case both on stacks O(n+m)
class Solution_recurrsion {  
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

class Solution {
    private String preOrderString(TreeNode root) {
        String ans = "";
        Stack<TreeNode> st = new Stack<>();
        st.push(root);

        while(!st.isEmpty()) {
            TreeNode curr = st.pop();
            if(curr==null) {
                // do not skip null, add empty delimiters
                ans +="#";
            }
            ans += curr.val + "#";
            st.push(curr.right);
            st.push(curr.left);
        } 
        return ans;
    }
    private boolean isSubtree(TreeNode root, TreeNode subRoot) {
        String pattern = preOrderString(subRoot);
        String text = preOrderString(root);
        if(search_using_z_function(text, pattern).isEmpty()) return false;
        return true;
    }
    public List<Integer> search_using_z_function(String text, String pattern) {
        int L = 0, R = 0;
        int text_n = text.length();
        int pattern_n = pattern.length();

        String new_text = pattern + "$" + text;
        int new_n = new_text.length();
        Integer[] z = new Integer[new_n];
        Arrays.fill(z, 0);

        for(int i = 0; i<new_n; i++) {
            if(i > R) {
                // outside window
                while(i+z[i] < new_n && new_text.charAt(i + z[i]) == new_text.charAt(z[i]))
                    z[i]++;
                L = i;
                R = i + z[i] - 1; // i+Z[i] is breaking point that is R+1 is breakign point for this window
            }

            else {
                // if we are still in window
                // check the breaking point of the corresponding prefix's z is within the window limit

                if(i + z[i-L] <= R) {
                    // safely we can copy
                    z[i] = z[i-L];
                    // L and R should not be updated in the safe copy case because we have not discovered a new larger window
                }
                else {
                    //atleast upto R the characters match, breakign point is outside the return
                    z[i] = R-i+1; // +1 is added for breaking point, i+ z[i] always points to breaking point
                    
                    // again same like above in i>R section 
                    while(i+z[i] < new_n && new_text.charAt(i + z[i]) == new_text.charAt(z[i]))
                        z[i]++;
                    // in this part we can update L and R because the previous 
                    L = i;
                    R = i + z[i] - 1; // i+Z[i] is breaking point that is R+1 is breakign point for this window
                }
            }
        }
        // there is pattern also in the new_text, so minus (pattern_n + 1) (1 for the delimiter)
        List<Integer> ans = new ArrayList<>();
        for(int i=0; i<new_n; i++) {
            if(z[i] >= pattern_n)
                ans.add(i - pattern_n - 1);
        }
        return ans;
    }
}
