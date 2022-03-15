class Solution {
    int[] nums;
    int maxOr = -1;
    int cnt = 0;

    public int countMaxOrSubsets(int[] nums) {
        this.nums = nums;
        dfs(0, 0);

        return cnt;
    }

    private void dfs(int l, int orVal) {
        if (l == nums.length) {
            if (orVal > maxOr) {
                maxOr = orVal;
                cnt = 1;
            } else if (orVal == maxOr) {
                cnt++;
            }
            return;
        }
        dfs(l+1, orVal);
        dfs(l+1, orVal | nums[l]);
    }
}
