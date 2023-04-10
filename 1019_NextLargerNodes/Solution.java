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
    public int[] nextLargerNodes(ListNode head) {
        int len = 0;
        ListNode t = head;

        while(t!=null) {
            len++;
            t=t.next;
        }
        int[] result = new int[len];

        int l = 0; // record the length of the list
        Stack<SNode> stack = new Stack<SNode>();
        
        t = head;
        while (t != null) {
            int curValue = t.val;
            SNode snode = new SNode(curValue, l);
            if (stack.isEmpty()) {
                stack.push(snode);
            } else {
                // stack is not null
                while (!stack.isEmpty()) {
                    SNode top = stack.peek();
                    if (top.value < curValue) {
                        result[top.index] = curValue;
                        stack.pop();
                        continue;
                    } else { // top >= curValue, add curValue into stack directly
                        break;
                    }
                }
                stack.push(snode);
            }
            t = t.next;
            l++;
        }
        return result;
    }
}

class SNode {
    int value;
    int index;
    SNode(int value, int index) {
        this.value = value;
        this.index = index;
    }
}
