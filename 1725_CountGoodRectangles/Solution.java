class Solution {
    public int countGoodRectangles(int[][] rectangles) {
        int l = rectangles.length;
        int[] maxLen = new int[l];

        for (int i = 0; i < l; i++) {
            int min = min(rectangles[i][0], rectangles[i][1]);
            maxLen[i] = min;
        }

        int max = 0;
        for (int i = 0; i < l; i++) {
            if (max < maxLen[i]) {
                max = maxLen[i];
            }
        }

        int count = 0;
        for (int i = 0; i < l; i++) {
            if (maxLen[i] == max)
                count++;
        }
        return count;
    }

    int min (int a,  int b) {
        return (a < b) ? a : b;
    }
}
