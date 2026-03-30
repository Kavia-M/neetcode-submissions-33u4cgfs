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
        if(head == null) 
            return head;
        
        ListNode first = head, second = head;
        
        for(int i=0; i<n;i++) {
            first = first.next;
        }

        // when 1st reaches null, second will stand in element to be removed
        // so we stop 1st in first.next = =null so second stands in before element
        // if first itself is null, second stands in head now, head is to be removed

        if(first == null) {
            return head.next;
        }

        while(first.next!=null) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;

        return head;

    }
}

class Solution_two_pass {
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
