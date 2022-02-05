class Solution {
    public int sumOfUnique(int[] nums) {
        int l = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < l; i++) {
            int v = nums[i];
            int cnt = map.getOrDefault(v, 0);
            map.put(v, cnt+1);
        }
        int sum = 0;
        Set<Integer> keySet = map.keySet();
        for (int k : keySet) {
            if (map.get(k) == 1) {
                sum += k;
            }
        }
        return sum;
    }
}
