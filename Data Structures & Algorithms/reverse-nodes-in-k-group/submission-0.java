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
    public ListNode reverseList(ListNode head) {
        if(head == null) 
            return null;
        ListNode temp = null;
        while(head.next != null) {
            ListNode t = head.next;
            head.next = temp;
            temp = head;
            head = t;
        }
        head.next = temp;
        return head;
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        if(head == null) return null;
        if(k<=0) return head;

        boolean flag = false;

        for(int i=1; i<k; i++) // k-1 times
        {
            if(curr==null) {  // before next in ith step, so we check k-2 elements (upto k-1 th element) in start
                flag = true;
                break;
            }
            curr = curr.next;
        }
        if(flag == true || curr == null) return head; // current is kth element, 
        // kth element not being checked in pre break stmt, so now we check
        
        ListNode head2 = curr.next;
        curr.next = null;

        // reverse these nodes
        ListNode rev = reverseList(head);
        head.next = reverseKGroup(head2, k);

        return rev;
    }
}
