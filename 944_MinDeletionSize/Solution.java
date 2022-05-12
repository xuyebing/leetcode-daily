class Solution {
    public int minDeletionSize(String[] strs) {
        int result = 0;

        int n = strs.length;
        int m = strs[0].length();

        for (int i = 0; i < m; i++) {
            boolean flag = false;
            for (int j = 1; j < n; j++) {
                if (strs[j].charAt(i) < strs[j-1].charAt(i)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                result++;
            }
        }

        return result;
    }
}
