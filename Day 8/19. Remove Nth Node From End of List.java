class Solution {
    public int length(ListNode head)  {
         int cnt = 0;

        while(head!=null) {
            cnt++;
            head = head.next;
        }

        return cnt;
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = length(head);
        int st = len - n + 1;

        if(st == 1) {
            return head.next;
        }

        ListNode prev = null;
        ListNode curr = head;

        while(st > 1) {
            prev = curr;
            curr = curr.next;
            st--;
        }

        prev.next = curr.next;
        return head;
    }
}