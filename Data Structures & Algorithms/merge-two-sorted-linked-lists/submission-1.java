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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // list 1 should contain minimum
        if(list1==null || list2.val < list1.val) {
            ListNode temp = list1;
            list1 = list2;
            list2 = temp;
        }
        ListNode head1 = list1, head2 = list2;
        // if(head1 == null) {
        //     return head1;
        // }
        while(head1!=null && head2!=null) {
            if((head1.next==null) || head2.val < head1.next.val) {
                ListNode t1 = head1.next;
                head1.next = head2;
                head2 = head2.next;
                head1.next.next = t1;
            }
            head1 = head1.next;
        }
        return list1;
    }
}