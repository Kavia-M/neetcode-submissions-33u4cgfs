/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
*/

class Solution_recurrsion {
    List<Integer> ans = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        if(root == null) return ans;
        
        for(Node node : root.children) {
            postorder(node);
        }
        ans.add(root.val);
        return ans;
    }
}

// iterative
// Push curr, right, left and then reverse
class Solution_preorder_reverse {
    List<Integer> ans = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        Stack<Node> st = new Stack<>();

        st.push(root);

        while(!st.isEmpty()) {
            Node curr = st.pop();
            if(curr != null) { // we push in order so rightmost is processed 1st
                ans.add(curr.val);
                for(int i=0; i<curr.children.size(); i++) {
                    st.push(curr.children.get(i));
                }
            }
        }
        Collections.reverse(ans);
        return ans;
    } 
}

// Iterative soltution with visited
class Solution {
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        Stack<Pair<Node, Boolean>> st = new Stack<>();
        // Map.Entry<String, Integer> pair = new AbstractMap.SimpleEntry<>("Laptop", 599);
        // the above can also be used
        st.push(new Pair<>(root, false));

        while(!st.isEmpty()) {
            Pair<Node, Boolean> curr = st.pop();
            Node currNode= curr.getKey();
            boolean visited = curr.getValue();
            
            if(visited) {
                // node already visited, means 2nd time it is pushed to stack
                // and then children pushed
                // if we get this in pop, children already popped and processed as LIFO order and children not pushed back again after being processed (means children also pushed 2 times, finally popped) now top is in curr
                ans.add(currNode.val);
            }
            else {
                // 1st time it was pushed with false, now push with true and then children (with false)
                // NOTE : children should be pushed reverse order for last in 1st out in stack
                st.push(new Pair<>(currNode, true));
                for(int i = currNode.children.size() - 1 ; i>=0 ; i--) {
                    st.push(new Pair<>(currNode.children.get(i), false));
                }
            }
        }
        return ans;
    }
}
/*
class BINARY_TREE_POSTORDER {
    public List<Integer> postorderTraversal(TreeNode root) {
        // we need to do preorder like thing but CURR, RIGHT, LEFT and reverse it to LEFT, RIGHT, CURR
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>(); // Deque can also be used

        st.push(root);

        while(!st.isEmpty()) {
            TreeNode curr = st.pop();
            if(curr!=null) {
                ans.add(curr.val);
                st.push(curr.left);
                st.push(curr.right); // this will be popped and processed first
            }
        }
        // it takes extra n space as it is not inplace
        ans = ans.reversed(); // valid from java 21, since this compiler used java 21 this can be used here otherwise Collections.reverse(ans); does inplace reversal
        return ans;
    }
}
*/