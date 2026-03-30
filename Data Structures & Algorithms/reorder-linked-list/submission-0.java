/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public void reorderList(ListNode head) {
        List<ListNode> nodes = new ArrayList<>();

        ListNode curr = head;
        while(curr != null) {
            nodes.add(curr);
            curr = curr.next;
        }
        int i = 0, j = nodes.size()-1;
        while(i<j) {
            // while equal or greater stop
            // these array store pointers only
            // we have not copied
            nodes.get(i).next = nodes.get(j);
            i++;
            if(i<j) {
                nodes.get(j).next = nodes.get(i);
            }
            j--;
        }
        // i get incremented and point to last node
        // j get decremented at last
        nodes.get(i).next = null;
    }
}
