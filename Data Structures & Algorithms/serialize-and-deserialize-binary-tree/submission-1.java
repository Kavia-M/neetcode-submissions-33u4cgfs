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

public class Codec_wrong {
    
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

    private void getPreorderInorder(TreeNode node, List<List<Integer>> ans) {
        if(node == null) return;
        // preorder is 0th index inner list
        // inorder is 1st index inner list

        ans.get(0).add(node.val);
        getPreorderInorder(node.left, ans);
        ans.get(1).add(node.val);
        getPreorderInorder(node.right, ans); 
    }

    private String listToString(List<Integer> list) {
        String s = "";
        for(int i : list) {
            s += i + "#";
        }
        return s;
    }

    private List<Integer> stringToList(String s) {
        List<Integer> list = new ArrayList<>();
        String number = "";
        // for(char c : str.toCharArray()) // can also be used but extra space
        for(int i = 0; i<s.length(); i++) {
            char c = s.charAt(i);
            // if (c >= '0' && c <= '9') // can also be used
            if(Character.isDigit(c)) {
                number+=c;
            }
            else {
                try {
                    list.add(Integer.parseInt(number));
                }
                catch(NumberFormatException e) {
                    
                    System.out.println(number + " is not valid integer");
                }
                number = "";
            }
        }
        return list;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<List<Integer>> preorderInorder = new ArrayList<>();
        // 2 lists we need to add
        preorderInorder.add(new ArrayList<>());
        preorderInorder.add(new ArrayList<>());
        getPreorderInorder(root, preorderInorder);
        // if(preorderInorder.size() < 2) return null; // exception

        String preorder = listToString(preorderInorder.get(0));
        String inorder = listToString(preorderInorder.get(1));
        System.out.println(inorder + " " + preorder);
        return preorder + "$" + inorder;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.isEmpty()) return null;
        int ind = data.indexOf("$");
        if(ind == -1) return null; // error
        String preorder = data.substring(0, ind); // end exclusive
        String inorder = (ind == data.length() -1) ? "" : data.substring(ind+1, data.length());

        List<Integer> preorderList = stringToList(preorder), inorderList = stringToList(inorder);
        return buildTreeUsingList(preorderList, inorderList);
    }
}


public class Codec {
    
    private int preorderWithNumberOfNodes(TreeNode node, List<List<Integer>> preorderAndLeftCount) { // s stores val and left height
        // if(preorderAndLeftCount.size() < 2) 
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
        int left = preorderWithNumberOfNodes(node.left, preorderAndLeftCount);
        int right = preorderWithNumberOfNodes(node.right, preorderAndLeftCount);
        preorderAndLeftCount.get(0).add(node.val);
        preorderAndLeftCount.get(1).add(left);
        return 1 + left + right;
    }

    private String listToString(List<List<Integer>> preorderAndLeftCount) {
        if(preorderAndLeftCount.size() < 2) return null;  // error
        String s = "";

        List<Integer> preorder = preorderAndLeftCount.get(0);
        List<Integer> leftNumberOfNodes = preorderAndLeftCount.get(1);

        if(preorder.isEmpty() || leftNumberOfNodes.isEmpty() || preorder.size() != leftNumberOfNodes.size()) return null; // error

        for(int i=0; i<preorder.size(); i++) {
            s+= preorder.get(i) + "#" + leftNumberOfNodes.get(i) + "*";
        }

        return s;
    }

    private List<List<Integer>> getPreorderAndLeftNumberOfNodes(String s) {
        List<List<Integer>> preorderLeftNumberOfNodes = new ArrayList<>();
        // 2 lists we need to add
        preorderLeftNumberOfNodes.add(new ArrayList<>());
        preorderLeftNumberOfNodes.add(new ArrayList<>());        
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
                preorderLeftNumberOfNodes.get(0).add(Integer.parseInt(val));
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
            preorderLeftNumberOfNodes.get(1).add(Integer.parseInt(leftNumberOfNodes));
            } catch(NumberFormatException e) {
                    System.out.println(leftNumberOfNodes + " is not valid integer");
            }
        }
        return preorderLeftNumberOfNodes;
    }

    private TreeNode buildTreeUsingList(List<Integer> preorder, List<Integer> leftNumberOfNodes) {
        if(preorder.isEmpty()) return null;
        int rootVal = preorder.get(preorder.size() - 1);
        preorder.remove(preorder.size() - 1);
        int size = leftNumberOfNodes.size();
        int leftNodesCount = 0;
        if(size >= 0) {
        leftNodesCount = leftNumberOfNodes.get(size - 1);
        leftNumberOfNodes.remove(size - 1);
        }


        List<Integer> leftPreorder = new ArrayList<>(preorder.subList(0, leftNodesCount)); // end exclusive
        List<Integer> rightPreorder = (leftNodesCount == preorder.size()) ? new ArrayList<>() : new ArrayList<>(preorder.subList(leftNodesCount, preorder.size()));
        List<Integer> leftLeftCount = new ArrayList<>(leftNumberOfNodes.subList(0, leftNodesCount)); // end exclusive
        List<Integer> rightLeftCount = (leftNodesCount == leftNumberOfNodes.size()) ? new ArrayList<>() : new ArrayList<>(leftNumberOfNodes.subList(leftNodesCount, leftNumberOfNodes.size()));
        TreeNode left = buildTreeUsingList(leftPreorder, leftLeftCount);
        TreeNode right = buildTreeUsingList(rightPreorder, rightLeftCount);

        return new TreeNode(rootVal, left, right);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<List<Integer>> preorderLeftNumberOfNodes = new ArrayList<>();
        // 2 lists we need to add
        preorderLeftNumberOfNodes.add(new ArrayList<>());
        preorderLeftNumberOfNodes.add(new ArrayList<>()); 
        int result = preorderWithNumberOfNodes(root, preorderLeftNumberOfNodes); // returns number nodes of root, which is not useful
        if(result < 0) return null; // error
        return listToString(preorderLeftNumberOfNodes);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        System.out.println(data);
        if(data == null || data.isEmpty()) return null;
        List<List<Integer>> preorderAndLeftNumberOfNodes = getPreorderAndLeftNumberOfNodes(data);
        
        System.out.println(preorderAndLeftNumberOfNodes.size());
        if(preorderAndLeftNumberOfNodes.size() < 2) return null; // error

        System.out.println(preorderAndLeftNumberOfNodes.get(0).toString());
        System.out.println(preorderAndLeftNumberOfNodes.get(1).toString());
        return buildTreeUsingList(preorderAndLeftNumberOfNodes.get(0), preorderAndLeftNumberOfNodes.get(1));
    }
}