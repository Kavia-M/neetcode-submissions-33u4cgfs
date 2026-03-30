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
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;

        return 1 + Math.max(maxDepth(root.left) , maxDepth(root.right));
    }
}


// Iterative with PRE NULL CHECK
class Solution_Iterative_1 {
    public int maxDepth(TreeNode root) {
        
        // push only non null nodes

        Stack<Map.Entry<TreeNode, Integer>> st = new Stack<>();

        if(root == null) return 0;

        int ans = 0;
        st.push(new AbstractMap.SimpleEntry<>(root, 1));
        
        while(!st.isEmpty()) {
            Map.Entry<TreeNode, Integer> curr = st.pop();
            TreeNode currNode = curr.getKey();
            int currDepth = curr.getValue();
            ans = Math.max(ans, currDepth);

            if(currNode.left != null)
                st.push(new AbstractMap.SimpleEntry<>(currNode.left, 1 + currDepth));
                
            if(currNode.right != null)
                st.push(new AbstractMap.SimpleEntry<>(currNode.right, 1 + currDepth));
        }
        return ans;
    }
}

// Iterative stack allowing NULL
class Solution {
    public int maxDepth(TreeNode root) {
        
        // push only non null nodes

        Stack<Pair<TreeNode, Integer>> st = new Stack<>();
        st.push(new Pair<>(root, 1)); // if root is not null it's depth is 1
        int ans = 0;
        
        while(!st.isEmpty()) {
            Pair<TreeNode, Integer> curr = st.pop();
            TreeNode currNode = curr.getKey();
            int currDepth = curr.getValue();

            if(currNode == null)
                continue;
            
            ans = Math.max(ans, currDepth);

            // if these child nodes are not null their depth will be  1 + currDepth
            st.push(new Pair<>(currNode.left, 1 + currDepth));
            st.push(new Pair<>(currNode.right, 1 + currDepth));
        }
        return ans;
    }
}



