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


class Solution_recurrsion {
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

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // prevgropup is previous group end
        // next group is next group start
        ListNode dummy = new ListNode(0, head); 
        ListNode prevGroup = dummy;
        while(true) { // break if we have less than k
            ListNode kth = getKth(prevGroup, k);
            if(kth == null) {
                break;
            }

            ListNode nextGroup = kth.next; // 1st ndoe after the kth node
            // we need to join this nextgroup to end of this reversal
            ListNode temp = nextGroup;
            ListNode curr = prevGroup.next; // this window correctly
            for(int i =1; i<k; i++) { // happen k-1 times
                ListNode t = curr.next; // till k curr is not null
                curr.next = temp;
                temp = curr;
                curr = t;
            }
            // after this curr becomes next to the intended place
            // restore the before value of that which is temp
            curr.next = temp; // current is nothing but kth node
            if(curr == kth) {
                System.out.println("curr is kth");
            }
            // connect this the prevGropup
            // save prev node next. reuse temp
            temp = prevGroup.next;  // previously this was the 1st node in this current window
            // now current window reversed, so this is last of curr window
            // last of current window is the prevGroup for next iteration
            prevGroup.next = curr;
            prevGroup = temp;
        }

        // after while loop skip the dummy and return
        return dummy.next;
    }
    private static ListNode getKth(ListNode l, int k) {
        while(l!= null && k-- > 0) {
            l = l.next;
        }
        if(l!=null)
        System.out.println("kth " + l.val);
        return l;
    }
}
