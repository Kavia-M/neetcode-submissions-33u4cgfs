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
