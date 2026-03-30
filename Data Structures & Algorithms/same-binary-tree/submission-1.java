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
    public boolean isSameTree(TreeNode p, TreeNode q) {
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
}

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Stack<Map.Entry<TreeNode, TreeNode>> st = new Stack<>();

        st.push(new AbstractMap.SimpleEntry<>(p, q));

        // capture only falses
        while(!st.isEmpty()) {
            Map.Entry<TreeNode, TreeNode> nodes = st.pop();
            TreeNode node1 = nodes.getKey(), node2 = nodes.getValue();

            if(node1 == null && node2 == null) continue;
            if(node1 == null || node2 == null || node1.val != node2.val) return false;

            st.push(new AbstractMap.SimpleEntry<>(node1.left, node2.left));
            st.push(new AbstractMap.SimpleEntry<>(node1.right, node2.right));
        }
        return true;
    }
}
