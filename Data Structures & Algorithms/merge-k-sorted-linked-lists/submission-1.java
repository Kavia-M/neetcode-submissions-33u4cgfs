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


/*
// geeks for geeks solution for merge 2 sorted lists

Node* sortedMerge(Node* a, Node* b)  
{  
    Node* res=NULL;
    if(a==NULL) return b;
    if(b==NULL) return a;
    if(a->data<=b->data){
        res=a;
        res->next=sortedMerge(a->next,b);
    }
     else if(a->data>b->data){
        res=b;
        res->next=sortedMerge(a,b->next);
    }
    return res;
}  

*/
class Solution_recurrsion {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = null;
        int ind = -1;
        for(int i=0; i<lists.length; i++ ) {
            if(lists[i] != null && (ans==null || lists[i].val <= ans.val)) {
                ans = lists[i];
                ind = i;
            }
        }
        if(ind<0) {
            return ans;
        }
        // else  list[i] != null
        lists[ind] = lists[ind].next;
        
        ans.next=mergeKLists(lists);

        return ans;
    }
}

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // FROM PREVIOUS QUESTION 
        // list 1 should contain minimum
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        if(list2.val < list1.val) {
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
    public ListNode mergeKLists(ListNode[] lists) {
        for(int i = 1; i < lists.length; i++) {
            lists[0] = mergeTwoLists(lists[0], lists[i]);
        }
        return lists.length > 0 ? lists[0] : null;
    }
}
