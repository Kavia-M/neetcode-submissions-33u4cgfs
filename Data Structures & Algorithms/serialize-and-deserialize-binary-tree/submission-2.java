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



public class Codec {
    
    private int postorderWithNumberOfNodes(TreeNode node, List<List<Integer>> postorderAndLeftCount) { // s stores val and left height
        // if(postorderAndLeftCount.size() < 2) 
        // {
        //     System.out.println("ERROR EROR");
        //     return -1;  // error
        // }
        if(node == null) {
            // s+="#";
            // s+="0";
            // s+="$";
            return 0;
        }
        int left = postorderWithNumberOfNodes(node.left, postorderAndLeftCount);
        int right = postorderWithNumberOfNodes(node.right, postorderAndLeftCount);
        postorderAndLeftCount.get(0).add(node.val);
        postorderAndLeftCount.get(1).add(left);
        return 1 + left + right;
    }

    private String listToString(List<List<Integer>> postorderAndLeftCount) {
        if(postorderAndLeftCount.size() < 2) return null;  // error
        String s = "";

        List<Integer> postorder = postorderAndLeftCount.get(0);
        List<Integer> leftNumberOfNodes = postorderAndLeftCount.get(1);

        if(postorder.isEmpty() || leftNumberOfNodes.isEmpty() || postorder.size() != leftNumberOfNodes.size()) return null; // error

        for(int i=0; i<postorder.size(); i++) {
            s+= postorder.get(i) + "#" + leftNumberOfNodes.get(i) + "*";
        }

        return s;
    }

    private List<List<Integer>> getpostorderAndLeftNumberOfNodes(String s) {
        List<List<Integer>> postorderLeftNumberOfNodes = new ArrayList<>();
        // 2 lists we need to add
        postorderLeftNumberOfNodes.add(new ArrayList<>());
        postorderLeftNumberOfNodes.add(new ArrayList<>());        
        int i = 0;
        while(s!=null && i<s.length()) {
            String val = "";
            for(;Character.isDigit(s.charAt(i)) || s.charAt(i) == '-'; i++) {
                val += s.charAt(i);
            }
            if(s.charAt(i) != '#') 
                return null; // error
            i++;
            try {
                postorderLeftNumberOfNodes.get(0).add(Integer.parseInt(val));
            } catch(NumberFormatException e) {
                    System.out.println(val + " is not valid integer");
            }
            String leftNumberOfNodes = "";
            for(;Character.isDigit(s.charAt(i)) || s.charAt(i) == '-'; i++) {
                leftNumberOfNodes += s.charAt(i);
            }
            if(s.charAt(i) != '*') return null; // error (replaced $ as it was not working)
            i++;
            try {
            postorderLeftNumberOfNodes.get(1).add(Integer.parseInt(leftNumberOfNodes));
            } catch(NumberFormatException e) {
                    System.out.println(leftNumberOfNodes + " is not valid integer");
            }
        }
        return postorderLeftNumberOfNodes;
    }

    private TreeNode buildTreeUsingList(List<Integer> postorder, List<Integer> leftNumberOfNodes) {
        if(postorder.isEmpty()) return null;
        int rootVal = postorder.get(postorder.size() - 1);
        postorder.remove(postorder.size() - 1);
        int size = leftNumberOfNodes.size();
        int leftNodesCount = 0;
        if(size >= 0) {
        leftNodesCount = leftNumberOfNodes.get(size - 1);
        leftNumberOfNodes.remove(size - 1);
        }


        List<Integer> leftpostorder = new ArrayList<>(postorder.subList(0, leftNodesCount)); // end exclusive
        List<Integer> rightpostorder = (leftNodesCount == postorder.size()) ? new ArrayList<>() : new ArrayList<>(postorder.subList(leftNodesCount, postorder.size()));
        List<Integer> leftLeftCount = new ArrayList<>(leftNumberOfNodes.subList(0, leftNodesCount)); // end exclusive
        List<Integer> rightLeftCount = (leftNodesCount == leftNumberOfNodes.size()) ? new ArrayList<>() : new ArrayList<>(leftNumberOfNodes.subList(leftNodesCount, leftNumberOfNodes.size()));
        TreeNode left = buildTreeUsingList(leftpostorder, leftLeftCount);
        TreeNode right = buildTreeUsingList(rightpostorder, rightLeftCount);

        return new TreeNode(rootVal, left, right);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<List<Integer>> postorderLeftNumberOfNodes = new ArrayList<>();
        // 2 lists we need to add
        postorderLeftNumberOfNodes.add(new ArrayList<>());
        postorderLeftNumberOfNodes.add(new ArrayList<>()); 
        int result = postorderWithNumberOfNodes(root, postorderLeftNumberOfNodes); // returns number nodes of root, which is not useful
        if(result < 0) return null; // error
        return listToString(postorderLeftNumberOfNodes);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        System.out.println(data);
        if(data == null || data.isEmpty()) return null;
        List<List<Integer>> postorderAndLeftNumberOfNodes = getpostorderAndLeftNumberOfNodes(data);
        
        System.out.println(postorderAndLeftNumberOfNodes.size());
        if(postorderAndLeftNumberOfNodes.size() < 2) return null; // error

        System.out.println(postorderAndLeftNumberOfNodes.get(0).toString());
        System.out.println(postorderAndLeftNumberOfNodes.get(1).toString());
        return buildTreeUsingList(postorderAndLeftNumberOfNodes.get(0), postorderAndLeftNumberOfNodes.get(1));
    }
}