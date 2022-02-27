class Solution {
    public String optimalDivision(int[] nums) {
        int l = nums.length;
        if (l == 1) {
            return nums[0]+"";
        } else if (l == 2) {
            return nums[0]+"/"+nums[1];
        } else { // l >= 3
            StringBuilder sb = new StringBuilder();
            sb.append(nums[0]+"/(");
            for (int i = 1; i < l-1; i++) {
                sb.append(nums[i]+"/");
            }
            sb.append(nums[l-1]+")");

            return sb.toString();
        }
    }
}
