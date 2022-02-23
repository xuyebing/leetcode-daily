class Solution {
    public String reverseOnlyLetters(String s) {
        int l = s.length();
        if (l == 1) {
            return s;
        }
        char[] cs = s.toCharArray();
        int i = 0;
        int j = l-1;
        while (i < j) {
            while (i < l && !isAlphabet(cs[i])) {
                i++;
            }
            while (j >= 0 && !isAlphabet(cs[j])) {
                j--;
            }
            if (i < j) {
                char tmp = cs[i];
                cs[i] = cs[j];
                cs[j] = tmp;
            }
            i++;
            j--;
        }
        return new String(cs);
    }

    private boolean isAlphabet(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}
