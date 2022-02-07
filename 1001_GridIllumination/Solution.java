class Solution {

    int[][] directs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    Map<String, Set<String>> map = new HashMap<String, Set<String>>();
    Map<String, Set<String>> lampMap = new HashMap<String, Set<String>>();
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        buildMap(lamps, n);

        int l = queries.length;
        int[] ans = new int[l];
        Arrays.fill(ans, 0); // 将ans 初始化为0

        for (int i = 0; i < l; i++) {
            int x = queries[i][0];
            int y = queries[i][1];
            String key = buildKey(x, y);
            if (map.containsKey(key)) {
                Set<String> vs = map.get(key);
                if (!vs.isEmpty()) {
                    // x,y 位置被照亮
                    ans[i] = 1;
                    // 清除9个单元格
                    for (int[] direct : directs) {
                        int x1 = direct[0] + x;
                        int y1 = direct[1] + y;
                        if ((x1 >= 0 && x1 < n) && (y1 >= 0 && y1 < n)) {
                            String lampKey = buildKey(x1, y1);
                            if (lampMap.containsKey(lampKey)) {
                                Set<String> values = lampMap.get(lampKey);
                                for(String vlight : values) {
                                    Set<String> vlightSet = map.get(vlight);
                                    vlightSet.remove(lampKey);
                                }
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }

    private void buildMap(int[][] lamps, int n) {
        int l = lamps.length;
        for (int i = 0; i < l; i++) {
            int x = lamps[i][0];
            int y = lamps[i][1];
            String key = buildKey(x, y);
            Set<String> set = lampMap.getOrDefault(key, new HashSet<String>());
            // 照亮同一行
            for (int j = 0; j < n; j++) {
                String tkey = buildKey(x, j);
                set.add(tkey);
                Set<String> lightSet = map.getOrDefault(tkey, new HashSet<String>());
                lightSet.add(key);
                map.put(tkey, lightSet);
            }
            // 照亮同一列
            for (int j = 0; j < n; j++) {
                String tkey = buildKey(j, y);
                set.add(tkey);
                Set<String> lightSet = map.getOrDefault(tkey, new HashSet<String>());
                lightSet.add(key);
                map.put(tkey, lightSet);
            }
            // 照亮斜对角线
            int x1 = x-1, y1 = y-1;
            while (x1>=0 && y1 >=0) {
                String tkey = buildKey(x1, y1);
                set.add(tkey);
                Set<String> lightSet = map.getOrDefault(tkey, new HashSet<String>());
                lightSet.add(key);
                map.put(tkey, lightSet);
                x1--;
                y1--;
            }
            x1 = x+1; y1 = y+1;
            while (x1 < n && y1 < n) {
                String tkey = buildKey(x1, y1);
                set.add(tkey);
                Set<String> lightSet = map.getOrDefault(tkey, new HashSet<String>());
                lightSet.add(key);
                map.put(tkey, lightSet);
                x1++;
                y1++;
            }
            // 照亮反斜对角线
            x1 = x-1; y1 = y+1;
            while (x1>=0 && y1 < n) {
                String tkey = buildKey(x1, y1);
                set.add(tkey);
                Set<String> lightSet = map.getOrDefault(tkey, new HashSet<String>());
                lightSet.add(key);
                map.put(tkey, lightSet);
                x1--;
                y1++;
            }
            x1 = x+1; y1 = y-1;
            while (x1 < n && y1 >= 0) {
                String tkey = buildKey(x1, y1);
                set.add(tkey);
                Set<String> lightSet = map.getOrDefault(tkey, new HashSet<String>());
                lightSet.add(key);
                map.put(tkey, lightSet);
                x1++;
                y1--;
            }
            // 将结果保存回 lampMap
            lampMap.put(key, set);
        }
    }

    private String buildKey(int i, int j) {
        return i + "-" + j;
    }
}
