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
class Solution {
    List<Integer> ans = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        Stack<Node> st = new Stack<>();

        st.push(root);

        while(!st.isEmpty()) {
            Node curr = st.pop();
            if(curr != null) {
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