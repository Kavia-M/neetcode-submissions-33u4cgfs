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

// Diameter at any one node in middle can be more than that of root
// Diameter of any node is depth of left subtree + depth of right subtree
class Solution_with_assumption {
    private int max_dia = -1; // if root is null -1 is ans (a rule we ourself define)
    // only root there 0 is answer
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return -1;
        int dia = height(root.left) + height(root.right);
        return Math.max(Math.max(dia, diameterOfBinaryTree(root.left)), diameterOfBinaryTree(root.right));
    }
    public int height(TreeNode node) {
        if(node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));    
    }
}

class Solution_Brute_Force {
    // O(n2) 
    // calculating 'int dia' is O(n) becaue height is O(n) . O(n+n) = (n)
    // diameterOfBinaryTree function is called for each node - n times
    // inside diameterOfBinaryTree, we have O(n)
    // n*O(n) = O(n2) that is order n square

    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0; // if root is null 0 is ans (without assumption or self defined rule)
        int dia = height(root.left) + height(root.right); // until this part it is O(n)
        return Math.max(Math.max(dia, diameterOfBinaryTree(root.left)), diameterOfBinaryTree(root.right));
    }
    public int height(TreeNode node) {
        if(node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));    
    }
}

// In Brute force we can see height is calculated n2 times, many times repeated
// DFS of calculate Height only, add a step in between to get max of diameter
class Solution_DFS_recurrsive {
    private int maxDia = 0; // worst case root null means also return 0
    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return maxDia;
    }

    public int height(TreeNode node) {
        if(node == null) return 0;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        // Post order processing
        maxDia = Math.max(maxDia, (leftHeight + rightHeight));
        return 1 + Math.max(leftHeight, rightHeight);  // no need to call again
    }
}

// each node pushed 2 times
// visited is checked with if it is presented in map
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        int maxDia = 0; // worst case root null means also return 0
        Map<TreeNode, Integer> map = new HashMap<>(); // we cannot keep this inside the stack, because we need to store the values of the children nodes somewhere to process present node
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        // visited false (root is not in map now)
        while(!st.isEmpty()) {
            TreeNode curr = st.pop();

            if(curr != null) {
                // if it is visited
                if(map.containsKey(curr)) {
                    // already visited, both right and left are processed
                    int leftHeight = (curr.left != null && map.containsKey(curr.left)) ? map.get(curr.left) : 0;
                    // no need to check if map.containsKey(curr.left) , it will be always there due to our algo
                    int rightHeight = (curr.right != null) ? map.get(curr.right) : 0;

                    maxDia = Math.max(maxDia, (leftHeight + rightHeight));
                    map.put(curr, 1 + Math.max(leftHeight, rightHeight)); // height of root
                }
                else {
                    // not visited
                    st.push(curr);
                    map.put(curr, 0); // visited
                    st.push(curr.left);
                    st.push(curr.right); 
                }
            }
        }
        return maxDia;

    }
}

// Iterative Simple 1 from Solution
/*
1st time pushing with visited false
2nd time pushing with visited true
*/
class NORMAL_POST_ORDER_Solution_iterative_1 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        Stack<Boolean> visited = new Stack<>();

        st.push(root); // NULL CAN ALSO BE PUSHED INTO STACK
        visited.push(false);

        while(!st.isEmpty()) { 
            TreeNode curr = st.pop();
            Boolean vis = visited.pop();

            // check non null
            if(curr != null) {
                if(vis == true) {
                    // already visited, right processed
                    // add to ans and move to next iteration
                    // in next iteration we will pop it and continue
                    ans.add(curr.val);
                }
                else {
                    // push back this node with visited true
                    // RIGHT LEFT with visited false
                    // because left will be popped out 1st
                    st.push(curr);
                    visited.push(true);
                    st.push(curr.right);
                    visited.push(false);
                    st.push(curr.left);
                    visited.push(false);
                }
            }
        }
        return ans;
    }
}