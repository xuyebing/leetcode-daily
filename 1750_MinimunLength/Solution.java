class Solution {
    public int minimumLength(String s) {
        int l = s.length();
        if (l == 1)
            return 1;
        
        int[] res = operateCore(s, 0, l-1);

        return res[1] - res[0] + 1;
    }

    private int[] operateCore(String s, int b, int e) {
        int[] res = new int[2];
        char bc = s.charAt(b);
        char ec = s.charAt(e);
        if (bc != ec) {
            res[0] = b;
            res[1] = e;
            return res;
        }
        // bc == ec
        int idx1 = -1;
        int i = b+1;
        for (; i < e; i++) {
            char t = s.charAt(i);
            if (t != bc) {
                idx1 = i;
                break;
            }
        }
        if (i == e) {
            // 表明最终的结果应该是0，为了达到这个目的，将res[1] - res[0] == -1. 则最终return res[1] - res[0] + 1 返回0.
            res[0] = b;
            res[1] = b-1; // 目的是为了使得res[1] - res[0] == -1.
            return res;
        }
        int idx2 = -1;
        int j = e-1;
        for (; j > b; j--) {
            char t = s.charAt(j);
            if (t != ec) {
                idx2 = j;
                break;
            }
        }
        if (j == b) { // 这里应该不会走到，因为和第31行情况一样，直接在35行就返回了
            res[0] = e;
            res[1] = e - 1;
        }
        if (idx1 == idx2) { // 最终return res[1] - res[0] + 1 返回1
            res[0] = idx1;
            res[1] = idx1;
            return res;
        } else {
            return operateCore(s, idx1, idx2);
        }
    }
}
