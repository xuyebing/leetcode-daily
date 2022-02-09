class Solution {
    public int countKDifference(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums) {
            int v = map.getOrDefault(i, 0);
            map.put(i, v+1);
        }

        int sum = 0;
        for (int i : nums) {
            int a = i+k;
            int va = map.getOrDefault(a, 0);
            int b = i-k;
            int vb = map.getOrDefault(b, 0);

            sum += va;
            sum += vb;
        }

        return sum/2;
    }
}
