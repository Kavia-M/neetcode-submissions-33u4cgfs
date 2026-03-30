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

public class Solution_given_sol_recurrsion {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head;
        int group = 0;
        while (cur != null && group < k) {
            cur = cur.next;
            group++;
        }

        if (group == k) {
            // usually we use temp = null for the 1st node next to point
            // reverse means 1st becomes last. its next should be null
            // but here since 1st node last should point to this reverseGroup result
            cur  = reverseKGroup(cur, k); 
            // we just reused curr node
            while (group-- > 0) { // do group = k times
                ListNode tmp = head.next; // this is node t to keep the 2nd node
                head.next = cur;
                cur = head;
                head = tmp;
            }
            head = cur;
        }
        return head; // if head is null we get it as it is
    }
}

public class Solution_my_version {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head;
        int group = 0;
        while (cur != null && group < k) {
            cur = cur.next;
            group++;
        }

        if (group == k) {
            // usually we use temp = null for the 1st node next to point
            // reverse means 1st becomes last. its next should be null
            // but here since 1st node last should point to this reverseGroup result
            ListNode temp  = reverseKGroup(cur, k); 
            // we just reused curr node
            group--; // usually we do head.next!=null means one less than actual length
            while (group-- > 0) { // do group = k times
                ListNode t = head.next; // this is node t to keep the 2nd node
                head.next = temp; 
                temp = head;
                head = t;
            }
            head.next = temp; // instead of head = temp we do this since we did group-- before itself
        }
        return head; // if head is null we get it as it is
    }
}


class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null) 
            return null;
        ListNode temp = null;
        while(head != null) {
            ListNode t = head.next;
            head.next = temp;
            temp = head;
            head = t;
        }
        head = temp;
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
