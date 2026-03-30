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

// PLEASE TAKE REFERENCE FROM DIAMETER QUESTION

// n2 because isBalanced is called n times, inside it height also called n times
class Solution_brute_force {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        if(!isBalanced(root.left)) return false;
        if(!isBalanced(root.right)) return false;
        return Math.abs(height(root.left) - height(root.right)) <= 1;
    }
    private int height(TreeNode node) {
        if(node == null) return 0;
       
        return 1 + Math.max(height(node.left), height(node.right));
    }
}

class Solution_recurrsion {
    private boolean balanced = true;
    public boolean isBalanced(TreeNode root) {
        height(root);
        return balanced;
    }
    private int height(TreeNode node) {
        if(node == null) {
            // already balanced is true only
            return 0;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        if(Math.abs(leftHeight - rightHeight) > 1) this.balanced = false;

        return 1 + Math.max(leftHeight, rightHeight);
    } 
}

// GIVEN GIVEN RECURRSION DFS
class Solution {

    public boolean isBalanced(TreeNode root) {
        return dfs(root)[0] == 1; // check 0 index element in array is 1(true)
    }

    // 2 element in int array, [is_balanced, height] -> is_balanced 0(false) or 1(true)
    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{1, 0}; // balanced and height is 0
        }

        int[] left = dfs(root.left); // recurrsion, dsf called for all nodes O(n) times
        int[] right = dfs(root.right);

        // CASCADING is_balance so root will finally know if anything below it is not balanced
        // current node is balanced only if it's left is balanced, right is balanced and it itself follows the rule
        boolean balanced = (left[0] == 1 && right[0] == 1) && 
                            (Math.abs(left[1] - right[1]) <= 1);
        int height = 1 + Math.max(left[1], right[1]);

        // converting boolean to 0 or 1 for int array format
        return new int[]{balanced ? 1 : 0, height};
    }
}

// FROM MAX diameter given sol in that ques
class Solution_Iterative_FROM_DIAMETER_QUESTION {
    public boolean isBalanced(TreeNode root) {
        // Map of node and its height
        Map<TreeNode, Integer> map = new HashMap<>(); // we cannot keep this inside the stack, because we need to store the values of the children nodes somewhere to process present node
        map.put(null, 0); // VERY IMPORTANT, this will be useful for any null nodes (root or leaf's left and right or any node that is null)
        // height of null node is 0
        Stack<TreeNode> st = new Stack<>();

        if(root != null)
            st.push(root); // visited false (root is not in map now)
        
        while(!st.isEmpty()) {
            TreeNode curr = st.peek(); // if we pop we have to push before left or right being pushed

            // push all left node 1st, after all left is processed then push right
            if(curr.left != null && !map.containsKey(curr.left)) {
                st.push(curr.left); // NOT VISITED
            }
            else if(curr.right != null && !map.containsKey(curr.right)) { // ELSE if very important. above, this, below - 3 sections are continuous
                st.push(curr.right); // NOT VISITED
            }
            else {
                // BOTH CHILDREN ARE VISITED or null
                curr = st.pop(); // pop the peek node
                // both right and left already visited, and are processed
                // null also in map
                int leftHeight = map.get(curr.left);
                int rightHeight = map.get(curr.right);
                int height = 1 + Math.max(leftHeight, rightHeight);
                
                if(Math.abs(leftHeight - rightHeight) > 1) {
                    return false;
                }

                map.put(curr, height); // height of root
            }
        }
        return true;
    }
}

