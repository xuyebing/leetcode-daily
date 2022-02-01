class Solution {
    public String longestNiceSubstring(String s) {
        int maxLen=0;
        int maxIdx=0;

        int l = s.length();
        for (int i = 0; i < l; i++) {
            int lower = 0;
            int upper = 0;

            for (int j = i; j < l; j++) {
                char c = s.charAt(j);
                if (Character.isLowerCase(c)) {
                    lower |= 1 << (c-'a');
                } else {
                    upper |= 1 << (c - 'A');
                }
                if (lower == upper && (j - i + 1) > maxLen) {
                    maxLen = j - i + 1;
                    maxIdx = i;
                }
            }
        }

        return s.substring(maxIdx, maxIdx + maxLen);
    }
}
