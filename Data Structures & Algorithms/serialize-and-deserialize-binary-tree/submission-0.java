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
