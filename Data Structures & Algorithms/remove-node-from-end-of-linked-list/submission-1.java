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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        ListNode curr = head;
        while(curr != null) {
            len++;
            curr = curr.next;
        }
        if(n > len) {
            // index out of bound, cannot remove anything
            return head;
        }
        // index to remove
        int ind = len - n; // zero based index // one based means do +1
        curr = head;
        if(ind == 0) {
            // remove head
            return head.next;
        }
        for(int i=0; i<ind-1; i++) { // ind-1 times moved
            curr = curr.next;
        }
        curr.next = curr.next.next;
        return head;
    }
}
