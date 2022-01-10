class Solution {
    public boolean isAdditiveNumber(String num) {
        int l = num.length();
        if (l < 3) { // num as least contains 3 numbers.
            return false;
        }
        // find the 1st number, note, as the input at least contains 3 numbers, so the end index will < l/2.
        List<String> Values1st = findAvailableNumbList(num, 0, l/2);
        
        // for each available 1st number, find the possible 2nd number list.
        int size = Values1st.size();
        for (int i = 0; i < size; i++) {
            String s1 = Values1st.get(i);
            int l1 = s1.length();
            List<String> Values2nd = findAvailableNumbList(num, l1, l - l1);
            
            // for each pair <1st, 2nd>, check if the string is additive.
            for (String s2 : Values2nd) {
                int l2 = s2.length();
                boolean tmpRes = checkIfAdditive(num, s1, s2);
                if (tmpRes) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkIfAdditive(String num, String one, String two) {
        int l = num.length();
        int l1 = one.length();
        int l2 = two.length();
        int remainLen = l - l1 - l2;
        int max = (l1 > l2) ? l1 : l2;
        if (remainLen < max) {
            return false;
        }

        // add 2 strings
        String three = add2String(one, l1, two, l2);
        StringBuilder sb = new StringBuilder();
        sb.append(one);
        sb.append(two);
        sb.append(three);

        String addResult = sb.toString();
        if (num.contains(addResult)) {
            if (l == (l1 + l2 + three.length())) {
                // finish the whole string.
                return true;
            }
            // continue to next round comparison.
            String newNum = num.substring(l1);
            String newOne = two;
            String newTwo = three;
            return checkIfAdditive(newNum, newOne, newTwo);
        } else {
            return false;
        }
    }

    private String add2String(String a, int l1, String b, int l2) {
        if (l1 < l2) {
            return add2StringCore(b, l2, a, l1);
        } else {
            return add2StringCore(a, l1, b, l2);
        }
    }

    // b is longer than a, i.e. l2 >= l1
    private String add2StringCore(String b, int l2, String a, int l1) {
        StringBuilder sb = new StringBuilder(); // remember to reverse it before return.
        int carryOver = 0;
        for (int i = 1; i <= l1; i++) {
            int bi = b.charAt(l2-i) - '0';
            int ai = a.charAt(l1-i) - '0';
            int sum = ai + bi + carryOver;
            carryOver = sum/10;
            int c = sum%10;
            sb.append((char)('0' + c));
        }
        for (int i = l1+1; i <= l2; i++) {
            int bi = b.charAt(l2-i) - '0';
            int sum = bi + carryOver;
            carryOver = sum/10;
            int c = sum%10;
            sb.append((char)('0' + c));
        }
        if (carryOver == 1) {
            sb.append('1');
        }
        // reverse sb, then return
        return sb.reverse().toString();
    }

    private List<String> findAvailableNumbList (String num, int s, int e) {
        List<String> res = new ArrayList<String>();

        char c = num.charAt(s);
        if (c == '0') {
            res.add("0");
            return res;
        }

        StringBuilder tmp = new StringBuilder();
        for (int i = s; i < e; i++) {
            c = num.charAt(i);
            tmp.append(c);
            res.add(tmp.toString());
        }

        return res;
    }
}
