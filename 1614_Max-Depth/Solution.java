class Solution {
    public int maxDepth(String s) {
        Stack<Character> stack = new Stack<> ();
        int res = 0;
        int max = 0;
        int l = s.length();
        for (int i = 0; i < l; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
                res++;
                max = (max < res) ? res : max;
            } else if (c == ')') {
                stack.pop();
                res--;
            }
        }
        return max;
    }
}
