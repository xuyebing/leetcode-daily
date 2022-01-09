class Solution {
    public char slowestKey(int[] times, String keys) {
        int max = times[0];
        char res = keys.charAt(0);

        int l = times.length;
        for (int i = 1; i < l; i++) {
            int t = times[i] - times[i-1];
            char c = keys.charAt(i);
            if (t > max) {
                max = t;
                res = c;
            } else if (t == max) {
                if (c > res)
                    res = c;
            }
        }
        return res;
    }
}
