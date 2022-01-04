class Solution {
    public String modifyString(String s) {
        int l = s.length();
        char[] sc = s.toCharArray();
        for (int i = 0; i < l; i++) {
            if (sc[i] == '?') {
                char before = i > 0 ? sc[i-1] : 'A';
                char after = (i < l-1 && sc[i+1] != '?') ? sc[i+1] : 'A';
                sc[i] = findChar(before, after);
            }
        }
        return new String(sc);
    }

    private char findChar(char before, char after) {
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            if (c != before && c != after) {
                return c;
            }
        }
        return 'A'; // never reach here.
    }
}
