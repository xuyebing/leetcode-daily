class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, List<Integer>> sameValueMap = new HashMap<>();
        int l = nums.length;
        for (int i = 0; i < l; i++) {
            int t = nums[i];
            List<Integer> locations = sameValueMap.computeIfAbsent(t, (a) -> new ArrayList<Integer>());
            int s = locations.size();
            if (s > 0) {
                int lastLocation = locations.get(s-1);
                if (abs(i, lastLocation) <= k) {
                    return true;
                }
            }
            locations.add(i);
        }
        return false;
    }

    private int abs(int a, int b) {
        if (a > b)
            return a-b;
        else
            return b-a;
    }
}
