class Solution {
    public int minimumDifference(int[] nums, int k) {
        // 对nums 排序，这样相邻的k个数的最小差值就是可能的最小差值的候选，进行一边o(n)遍历即可
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int l = nums.length;
        for (int i = 0; i <= l - k; i++) {
            int tmpDiff = nums[i+k-1] - nums[i];
            if (min > tmpDiff) {
                min = tmpDiff;
            }
        }
        return min;
    }
}
