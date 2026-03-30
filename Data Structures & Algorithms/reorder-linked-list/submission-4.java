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
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // copied and modified from merge 2 sorted linked list

        ListNode head1 = list1, head2 = list2;

        while(head1!=null && head2!=null) {
        //  if((head1.next==null) || head2.val < head1.next.val) {
                // always merge with a node in list2
                ListNode t1 = head1.next;
                head1.next = head2;
                head2 = head2.next;
                head1.next.next = t1;
        //    }
            head1 = t1; // head 1 should move in list 1 only
        }
        return list1;
    }
    private ListNode reverse(ListNode head) {
        if(head == null) 
            return head;
        ListNode prev = null;
        while(head.next != null) {
            ListNode t = head.next;
            head.next = prev;
            prev = head;
            head = t; 
        }
        // last node is unmodified tillnow
        head.next = prev;
        return head;
    }
    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next; // now fast will stop soon
        // slow will be end of 1st half
        // slow.next is correctly 2nd half
        // in case of off number 2nd half have more nodes
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode meddile = slow.next;
        slow.next = null;
        ListNode reverseHalf = reverse(meddile);
        head = mergeTwoLists(head, reverseHalf);
    }
}

class Solution_own {
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // copied and modified from merge 2 sorted linked list

        ListNode head1 = list1, head2 = list2;

        while(head1!=null && head2!=null) {
        //  if((head1.next==null) || head2.val < head1.next.val) {
                // always merge with a node in list2
                ListNode t1 = head1.next;
                head1.next = head2;
                head2 = head2.next;
                head1.next.next = t1;
        //    }
            head1 = t1; // head 1 should move in list 1 only
        }
        return list1;
    }
    private ListNode reverse(ListNode head) {
        if(head == null) 
            return head;
        ListNode prev = null;
        while(head.next != null) {
            ListNode t = head.next;
            head.next = prev;
            prev = head;
            head = t; 
        }
        // last node is unmodified tillnow
        head.next = prev;
        return head;
    }
    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode meddile = slow;
        ListNode reverseHalf = reverse(meddile.next);
        slow.next = null;
        head = mergeTwoLists(head, reverseHalf);
    }
}

class Solution_recursion {
    public void reorderList(ListNode head) {
        if(head != null) {
            head = rec(head, head.next);
        }
    }
    private ListNode rec(ListNode front, ListNode back) {
        if(back == null) {
            return front;
        }
        // the returned one is actully front.next before swap
        // so front moves forward one step as the recursion returns
        // back is at end null, N-1, n-2 etc..  null is last call of recursion
        // n-1 in last before call, but in last before call only we get
        // front as head 1 from last call return

        front = rec(front, back.next); // front will keep moving
        // to the end of the already completed array formation
        // after this front and before front.next we attach the back

        // back will go to end as rec call is in 1st line of recursion call
        // back will return n-1, n-2 ... elements that is to be added next to front

        // if the end of the sequence so far (returned by rec) becomes null
        // it means sequence completed
        if(front == null) {
            return front; // or return null
        }

        // ataching back to next of front
        ListNode temp = null;
        // check if front is already attached to back or front back same (in case of odd length)
        if(front == back || front.next == back) {
            // means end of sequence
            // there will be a loop of last back to any node before that
            back.next = temp; // back.next = null; 
            // front will be anyways always before back
        }
        else {
            temp = front.next;
            front.next = back;
            back.next = temp;
        }
        return temp; // end of seq so far. if it is null, we finished

    }
}


class Solution_two_pointer {
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
