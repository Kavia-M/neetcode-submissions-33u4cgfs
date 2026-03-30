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

// THIS WORKS FOR ANY BINARY TREE, not only BST
// https://takeuforward.org/plus/dsa/problems/print-root-to-note-path-in-bt?tab=editorial
class Solution_Binary_Tree {
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

// https://takeuforward.org/plus/dsa/problems/lca-in-bt?tab=editorial
// THIS works for any binary tree
// O(n) time complexity , O(n) skew tree worst case stack SPACE complexity in recursion
class Solution {
    // ASSUMPTION, both nodes are in tree, if we get only one as final result, it means the other is a subtree under that node
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        // ONE SMALL CHANGE, here the p and q are not pointing to exact nodes under trees, so we can compare their val
        if(root == null || p== null || q==null) 
            return null;
        // base case
        // if root is null or is equal to p or q anything
        if(root==null || root.val == p.val || root.val == q.val) {
            return root;
        }
        // each call returns a node, get a node from left and right, 
        // if any one is null, we got only one of the node, return the non null one
        // if both are non null, means both p and q are under this current root and is split as left and right, so this root is lowest common ancestor
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left == null) 
            return right;
        else if(right == null)
            return left;
        else // both non null
            return root;
    }
}
