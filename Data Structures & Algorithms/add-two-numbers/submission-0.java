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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int unit = 1;
        int ans = 0;
        while(l1!=null) {
            ans += (l1.val * unit);
            unit*=10;
            l1 = l1.next;
        }
        unit = 1;
        while(l2!=null) {
            ans += (l2.val * unit);
            unit*=10;
            l2 = l2.next;
        }
        // created a node // atleast it should be zero
        ListNode head = new ListNode(0, null);
        ListNode prev = head; // can be null also
        // but to prevent 0 answer we do this
        ListNode curr = head;
        while(ans > 0) {
            // already created node we will update
            // and create a node for next number before next iteration
            curr.val = ans % 10;
            ans /= 10;
            ListNode temp = new ListNode(0, null);
            curr.next = temp;
            prev = curr;
            curr = temp; // curr = curr.next;
        }
        // after ending we haev one extra node, so 
        prev.next = null;
        return head;
    }
}
