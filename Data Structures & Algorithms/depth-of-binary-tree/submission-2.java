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

class Solution {
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


