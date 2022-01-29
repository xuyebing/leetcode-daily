class Solution {
    public int numberOfWeakCharacters(int[][] p) {
        // 先按照 “攻击”属性从小到大排序，在相同攻击属性的列表里，再按照“防御”属性排序
        Map<Integer, int[]> map = buildMap(p);

        Set<Integer> kSet = map.keySet();
        Integer[] kArr = null;
        kArr = kSet.toArray(new Integer[0]);
        Arrays.sort(kArr);
        int l = kArr.length;
        if (l == 1) 
            return 0;
        
        int[] idxPoints = new int[l]; // 记录每一列目前挪动到的位置。
        Arrays.fill(idxPoints, -1);
        int res = 0;
        for (int i = 1; i < l; i++) {
            int att = kArr[i];
            // 找attack 相同的元素中，defense最大的，即 defI - last value in map.get(att) array
            int[] values = map.get(att);
            int vl = values.length;
            int defI = values[vl-1];

            for (int j = 0; j < i; j++) {
                int[] vsTmp = map.get(kArr[j]);
                int idx = Arrays.binarySearch(vsTmp, defI);
                int smallDefNum = 0; // vsTmp里小于defI的元素个数
                if (idx < 0) { // not found, return -InsertPoint - 1
                    smallDefNum = -idx - 1;
                } else {
                    // 找到第一个小于defI的元素索引位置
                    int x = idx - 1;
                    while(x >= 0) {
                        if (vsTmp[x] < defI) {
                            break;
                        }
                        x--;
                    }
                    if (x < 0) { // means 没有比defI小的元素
                        smallDefNum = 0;
                    } else {
                        smallDefNum = x+1; // x是索引位置，对应的元素数目应该是索引位置+1.
                    }
                }
                if (idxPoints[j] == -1) {
                    res += smallDefNum;
                    idxPoints[j] = smallDefNum - 1; // idxPoint[j] 记录j列已经计算过的最后一个元素的下标。
                } else {
                    if (smallDefNum > idxPoints[j] + 1) {
                        res += smallDefNum - (idxPoints[j] + 1); // smallDefNum 减去已经计数过的数量, 即(idxPoint[j] + 1);
                        idxPoints[j] = smallDefNum - 1;
                    }
                }
            }
        }
        return res;
    }

    private Map<Integer, int[]> buildMap (int[][] p) {
        int l = p.length;

        Map<Integer, List<Integer>> tmap = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < l; i++) {
            int k1 = p[i][0];
            int v1 = p[i][1];
            List<Integer> list = tmap.getOrDefault(k1, new ArrayList<Integer>());
            list.add(v1);
            tmap.put(k1, list);
        }

        Map<Integer, int[]> res = new HashMap<Integer, int[]>();
        Set<Integer> kSet = tmap.keySet();

        for (int k : kSet) {
            List<Integer> vlist = tmap.get(k);
            int len1 = vlist.size();
            int[] tArr = new int[len1];
            int i = 0;
            for (int ti : vlist) {
                tArr[i++] = ti;
            }
            Arrays.sort(tArr);
            res.put(k, tArr);
        }

        return res;
    }
}
