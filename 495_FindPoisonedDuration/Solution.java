class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int res = 0;

        int l = timeSeries.length;
        for (int i = 0; i < l; i++) {
            int b1 = timeSeries[i];
            int e1 = b1 + duration - 1;
            i++;
            while (i < l && timeSeries[i] <= e1) {
               e1 = timeSeries[i] + duration - 1;
               i++;
            }
            i--;
            res += e1 - b1 + 1;
        }

        return res;
    }
}
