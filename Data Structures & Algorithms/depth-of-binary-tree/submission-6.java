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
class Solution_Iterative_DFS_1 {
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
class Solution_Iterative_DFS_2 {
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

// Iterative BFS. BFS can be iterative and Queue only
// Allowing NULL in Queue use LinkedList not arraydeque
class Solution_BFS_with_null {
    public int maxDepth(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();

        // after processing one level that is adding it's children we update this
        int level = 0;

        // these will raise errors if q is full but here we are not setting any q size so it won't be full
        q.add(root);

        while(!q.isEmpty()) {
            // pop out all the elements because these elements are in same level, can't maintain level if we pop up later like normal BFS
            // in BFS we use q, in q removing now or later always gives front element
            // Using size = queue.size() lets us process nodes level by level.

            // DO NOT REMOVE UNTIL q empty because inside for loop we are adding to q. 
            int size = q.size();
            for(int i = 0; i<size; i++) {
                // remove throws error on empty q
                TreeNode curr = q.remove();
                if(curr == null) continue;

                q.add(curr.left);
                q.add(curr.right);
            }
            // that node fully processed
            level++;
        }
        return level-1; // the CONTINUE STMT is for 'for' loop only, for a null layer it enters the while loop and level++, so last lavel is null level, so correct level is level-1
    }
}

// BFS without null
class Solution {
    public int maxDepth(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();

        // after processing one level that is adding it's children we update this
        int level = 0;

        if(root != null)
            // these will raise errors if q is full but here we are not setting any q size so it won't be full
            q.add(root);

        while(!q.isEmpty()) {
            // pop out all the elements because these elements are in same level, can't maintain level if we pop up later like normal BFS
            // in BFS we use q, in q removing now or later always gives front element
            // Using size = queue.size() lets us process nodes level by level.

            // DO NOT REMOVE UNTIL q empty because inside for loop we are adding to q. 
            int size = q.size();
            for(int i = 0; i<size; i++) {
                // remove throws error on empty q
                TreeNode curr = q.remove();

                if(curr.left!=null) q.add(curr.left);
                if(curr.right!=null) q.add(curr.right);
            }
            // that node fully processed
            level++;
        }
        return level;
    }
}



