class Solution {
    // using stack
    Stack<String> stack = new Stack<>();
    public String simplifyPath(String path) {
        int l = path.length();
        for (int i = 0; i < l; i++) {
            char c = path.charAt(i);
            if (c == '/') {
                if (stack.empty()) {
                    stack.push(String.valueOf(c));
                } else {
                    String head = stack.peek();
                    if (head.equals("/")) {
                        continue;
                    } else {
                        stack.push(String.valueOf(c));
                    }
                }
            } else if (c == '.') {
                // assert: stack.peek() should 100% be '/'
                // judge if it's '.' or '..' or '...' or '..a....'
                i++;
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                while (i < l && path.charAt(i) != '/') {
                    sb.append(path.charAt(i++));
                }
                if (i == l) {
                    opStack(sb);
                } else {
                    i--;
                    opStack(sb);
                }
            } else {
                // alphabet, number, or '_'
                i++;
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                while (i < l && path.charAt(i) != '/') {
                    sb.append(path.charAt(i++));
                }
                if (i == l) {
                    stack.push(sb.toString());
                } else {
                    i--;
                    stack.push(sb.toString());
                }
            }
        }
        if (stack.size() > 1 && stack.peek().equals("/")) {
            stack.pop(); // pop the last '/' in the path.
        }

        return printStack();
    }

    private String printStack() {
        Iterator<String> iter = stack.iterator();
        StringBuilder res = new StringBuilder();
        while (iter.hasNext()) {
            res.append(iter.next());
        }
        return res.toString();
    }

    private void opStack(StringBuilder sb) {
        if (sb.length() >= 3) {
            stack.push(sb.toString());
        } else if (sb.length() == 2) {
            String tmp = sb.toString();
            if (tmp.equals("..")) {
                if (stack.size() > 1) {
                    stack.pop();
                    stack.pop();
                }
            } else {
                stack.push(tmp);
            }
        } else { // sb.length == 1 && sb.toString must be "."
            if (stack.size() > 1) {
                stack.pop();
            }
        }
    }
}
