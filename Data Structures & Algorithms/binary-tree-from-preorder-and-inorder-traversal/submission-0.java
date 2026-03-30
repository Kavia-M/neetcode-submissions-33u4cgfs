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
    // private int getIndex(int[] arr, int target) {
    //         return IntStream.range(0, len)
    //         .filter(i -> arr[i] == target)
    //         .findFirst() // first occurrence (WE KNOW UNIQUE NUMBERS IN TREE)
    //         .orElse(-1); // No element found
    // }

    private TreeNode buildTreeUsingList(List<Integer> preorder, List<Integer> inorder) {
        if(inorder.isEmpty()) return null;
        int rootVal = preorder.get(0);
        preorder.remove(0);
        int ind = inorder.indexOf(rootVal);
        List<Integer> leftVals = inorder.subList(0, ind); // end exclusive
        List<Integer> rightVals = inorder.subList(ind+1, inorder.size());
        TreeNode left = buildTreeUsingList(preorder, leftVals);
        TreeNode right = buildTreeUsingList(preorder, rightVals);

        return new TreeNode(rootVal, left, right);
    } 
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        List<Integer> preorderList = Arrays.stream(preorder).boxed().collect(Collectors.toList());
        List<Integer> inorderList = Arrays.stream(inorder).boxed().collect(Collectors.toList());
        return buildTreeUsingList(preorderList, inorderList);
    }
}
