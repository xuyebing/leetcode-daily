class Solution {
    public int findCenter(int[][] edges) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 2; i++) {
            int count = map.getOrDefault(edges[i][0], 0);
            map.put(edges[i][0], count+1);
            int c2 = map.getOrDefault(edges[i][1], 0);
            map.put(edges[i][1], c2+1);
        }
        Set<Integer> keySet = map.keySet();
        int res = -1;
        for (int k : keySet) {
            int v = map.get(k);
            if (v == 2) {
                res = k;
                break;
            }
        }
        return res;
    }
}
