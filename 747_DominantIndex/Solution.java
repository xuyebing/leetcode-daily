class Solution {
    public int dominantIndex(int[] nums) {
        int l = nums.length;
        int max = nums[0];
        int maxIndex = 0;
        int doubleVMax = -1;

        int i = 1;
        while (i < l) {
            if (nums[i] > max) {
                doubleVMax = max * 2;

                max = nums[i];
                maxIndex = i;
            } else {
                // nums[i] * 2 might > doubleVMax
                if (nums[i] * 2 > doubleVMax) {
                    doubleVMax = nums[i] * 2;
                }
            }
            i++;
        }
        if (doubleVMax <= max)
            return maxIndex;
        else
            return -1;
    }
}
