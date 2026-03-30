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
    // return true if path is there
    private boolean getPath(TreeNode node, ArrayList<TreeNode> path, TreeNode targetNode) {
        if(node == null) return false; // do not do the array anything

        // always add to path
        path.add(node);
        System.out.println(node + " " + targetNode);
        if(node.val == targetNode.val) {
            return true; // array is kept as it is
        }

        if(getPath(node.left, path, targetNode) || getPath(node.right, path, targetNode)) {
            return true; // array is kept as we get from any of the path with the target Node
        }

        // false case remove the current val and return, that will be in last of the array
        path.remove(path.size() - 1);
        return false;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> pathP = new ArrayList<>();
        ArrayList<TreeNode> pathQ = new ArrayList<>();

        if(!getPath(root, pathP, p) || !getPath(root, pathQ, q)) {
            return null; // if any of the p or q not present we get false
        } 
        int i = 0;
        for(i=0; i<pathP.size() && i<pathQ.size() && (pathP.get(i)).equals(pathQ.get(i)); i++);
        if(i-1 >= 0) return pathP.get(i-1);
        return null;
    }
}
