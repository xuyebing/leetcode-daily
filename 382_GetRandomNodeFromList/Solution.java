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

    ListNode head;
    Random random;
    public Solution(ListNode head) {
        this.head = head;
        random = new Random();
    }
    
    public int getRandom() {
        int i = 1;
        int res = Integer.MIN_VALUE;
        ListNode p = head;
        while (p != null) {
            int rv = random.nextInt(i);
            if (rv == 0) {
                res = p.val;
            }
            i++;
            p = p.next;
        }

        return res;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
