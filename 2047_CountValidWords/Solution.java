class Solution {
    public int countValidWords(String sentence) {
        String[] raw = sentence.split(" ");
        int res = 0;
        for (String s : raw) {
            if (isValid(s))
                res++;
        }

        return res;
    }

    private boolean isValid(String s) {
        if (s.isEmpty())
            return false;
        int dashIdx = -1;
        int symbolIdx = -1;
        int l = s.length();
        if (l == 1) {
            char c = s.charAt(0);
            if ((c >= '0' && c <= '9') || c=='-') {
                return false;
            }
        } else {
            for (int i = 0; i < l; i++) {
                char c = s.charAt(i);
                if (c >= '0' && c <= '9')
                    return false;
                if (c == '-') {
                    if (dashIdx != -1)
                        return false;
                    dashIdx = i;
                }
                if (c == '.' || c == ',' || c=='!') {
                    if (symbolIdx != -1)
                        return false;
                    symbolIdx = i;
                }
            }

            if (dashIdx != -1) {
                if (dashIdx == 0 || dashIdx == l-1) {
                    return false;
                }
                char a = s.charAt(dashIdx-1);
                char b = s.charAt(dashIdx+1);
                if (!(a>='a' && a<='z') || !(b>='a' && b<='z')) {
                    return false;
                }
            }
            if (symbolIdx != -1) {
                if (symbolIdx != l-1)
                    return false;
            }
        }
        return true;
    }
}
