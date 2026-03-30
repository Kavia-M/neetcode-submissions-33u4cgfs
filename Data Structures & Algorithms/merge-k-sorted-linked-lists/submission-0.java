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
class Solution {
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
