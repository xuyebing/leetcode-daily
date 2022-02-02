class Solution {
    public String reversePrefix(String word, char ch) {
        int idx = findIdx(word, ch);
        if (idx <= 0)
            return word;
        String s1 = swap(word, 0, idx);
        StringBuilder sb = new StringBuilder(s1);
        sb.append(word.substring(idx+1));
        return sb.toString();
    }

    private int findIdx(String s, char c) {
        int l = s.length();
        
        int idx = -1;
        for (int i = 0; i < l; i++) {
            char tc = s.charAt(i);

            if (tc == c) {
                idx = i;
                break;
            }
        }

        return idx;
    }

    private String swap(String str, int s, int e) {
        if (s >= e) {
            return str;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = e; i >= s; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
}
