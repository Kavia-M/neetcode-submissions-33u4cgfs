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

//DFS
class Solution_my_sol {
    private int count = 0; // count of good nodes
    public int goodNodes(TreeNode root) {
        // 2 things while passing root, we can keep maxSoFar as IntMIN this is correct
        // because max so far is max before this node
        // but even if we pass root value itself, it also work because we check <= only (= there)

        countGoodNodes(root, Integer.MIN_VALUE);

        return count;
    }
    private void countGoodNodes(TreeNode node, int maxSoFar) {
        if(node == null) return;
        if(maxSoFar <= node.val) {
            count++;
            maxSoFar = node.val;
        }
        countGoodNodes(node.left, maxSoFar);
        countGoodNodes(node.right, maxSoFar);
    }
}

// same above using q
// bfs
class Solution_given_solution_1 {
    public int goodNodes(TreeNode root) {
        int count = 0;
        Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();
        // allows null
        q.add(new Pair<>(root, Integer.MIN_VALUE));

        while(!q.isEmpty()) {
            Pair<TreeNode, Integer> currPair = q.poll();
            TreeNode curr = currPair.getKey();
            int currMaxSoFar = currPair.getValue();

            if(curr == null) continue;
            
            if(currMaxSoFar <= curr.val) {
                count++;
                currMaxSoFar = curr.val;
            }

            q.add(new Pair<>(curr.left, currMaxSoFar));
            q.add(new Pair<>(curr.right, currMaxSoFar));
        }
        return count;
    }
}

//DFS GIVEN SOLUTION 1
// returns count of good nodes of this subtree
class Solution {
    public int goodNodes(TreeNode root) {
        // 2 things while passing root, we can keep maxSoFar as IntMIN this is correct
        // because max so far is max before this node
        // but even if we pass root value itself, it also work because we check <= only (= there)

        return countGoodNodes(root, Integer.MIN_VALUE);

    }
    private int countGoodNodes(TreeNode node, int maxSoFar) {
        if(node == null) return 0; // nul lmeans this is not goodNode

        int count = 0;
        if(maxSoFar <= node.val) {
            count++;
            maxSoFar = node.val;
        }
        return count + countGoodNodes(node.left, maxSoFar) + countGoodNodes(node.right, maxSoFar);
    }
}
