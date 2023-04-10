class Solution {
    public boolean checkDistances(String s, int[] distance) {
        Map<Character, Integer> distMap = new HashMap<Character, Integer>();
        int l = s.length();
        for (int i = 0; i < l; i++) {
            char c = s.charAt(i);
            if (distMap.keySet().contains(c)) {
                int prevPosition = distMap.get(c);
                int distInS = i - 1 - prevPosition;
                distMap.put(c, distInS);
            } else {
                distMap.put(c, i);
            }
        }
        // compare distMap and distance;
        Set<Character> keys = distMap.keySet();
        for (char c : keys) {
            if (distMap.get(c) != distance[c-'a']) {
                return false;
            }
        }
        return true;
    }
}
