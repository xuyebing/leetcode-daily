class Solution {
    public String[] uncommonFromSentences(String s1, String s2) {
        String[] s1s = s1.split(" ");
        String[] s2s = s2.split(" ");

        Map<String, Integer> freq = new HashMap<>();

        for (String w : s1s) {
            int v = freq.getOrDefault(w, 0);
            freq.put(w, v+1);
        }

        for (String w: s2s) {
            int v = freq.getOrDefault(w, 0);
            freq.put(w, v+1);
        }

        Set<String> resSet = new HashSet<>();
        Set<String> k = freq.keySet();
        for (String w : k) {
            int v = freq.get(w);
            if (v == 1) {
                resSet.add(w);
            }
        }
        String[] res = new String[resSet.size()];
        resSet.toArray(res);
        return res;
    }
}
