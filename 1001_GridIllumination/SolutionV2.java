class SolutionV2 {
    /**
     * 2nd Version
     * 思路：为了更快速，根据lamps里的的灯，将其对应的：行、列、对角线、反对角线的计数器+1
     * 当查询时，只要查询元素的 行、列、对角线、反对角线 这4个计数器中有一个不为0，则该灯是亮的，即，ans=1
     * 然后对查询元素的周围9个格子，判断是否在lamps里，如果在，则将它对应的 行、列、对角线、反对角线 这4个计数器的值分别减1, 直到变为0.
     */
    int[][] directs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    Map<String, int[]> lampMap = new HashMap<String, int[]>(); // value = int[4], int[0]: 第几行，int[1]: 第几列; int[2]: 第几条对角线; int[3]: 第几条反对角线
    
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        int[] rows = new int[n];
        int[] columns = new int[n];
        int[] diagonals = new int[2*n-1]; // n * n 的网格有 2n-1 条对角线
        int[] backDiagonals = new int[2*n-1]; // n * n 的网格有 2n-1 条反对角线

        int l = lamps.length;
        for (int i = 0; i < l; i++) {
            int x = lamps[i][0];
            int y = lamps[i][1];
            String key = buildKey(x, y);
            if (lampMap.containsKey(key)) { // 防止重复点灯（导致计数器多加了一次1）
                continue;
            }
            int[] vArr = new int[4];
            vArr[0] = x;
            vArr[1] = y;
            vArr[2] = computeDiagonalNum(x, y, n);
            vArr[3] = computeBackDiagonalNum(x, y);

            // 计数器值 +1
            rows[vArr[0]]++;
            columns[vArr[1]]++;
            diagonals[vArr[2]]++;
            backDiagonals[vArr[3]]++;

            lampMap.put(key, vArr);
        }

        int ql = queries.length;
        int[] ans = new int[ql];
        Arrays.fill(ans, 0);

        for (int i = 0; i < ql; i++) {
            int x = queries[i][0];
            int y = queries[i][1];
            int a = computeDiagonalNum(x, y, n);
            int b = computeBackDiagonalNum(x, y);
            if (rows[x] > 0 || columns[y] > 0 || diagonals[a] > 0 || backDiagonals[b] > 0) {
                ans[i] = 1;
                // 每个计数器减1
                //// 遍历9个单元格
                for (int[] direct : directs) {
                    int x1 = direct[0] + x;
                    int y1 = direct[1] + y;
                    if ((x1 >= 0 && x1 < n) && (y1 >= 0 && y1 < n)) {
                        String lampKey = buildKey(x1, y1);
                        if (lampMap.containsKey(lampKey)) {
                            int[] lampV = lampMap.get(lampKey);
                            if (rows[lampV[0]] > 0) {
                                rows[lampV[0]]--;
                            }
                            if (columns[lampV[1]] > 0) {
                                columns[lampV[1]]--;
                            }
                            if (diagonals[lampV[2]] > 0) {
                                diagonals[lampV[2]]--;
                            }
                            if (backDiagonals[lampV[3]] > 0) {
                                backDiagonals[lampV[3]]--;
                            }
                            // lampKey这个灯已经被关闭，所以从lampMap中将其删除；否则，容易在再次查询到它的时候，会再次将4个计数器减1，但是这时候减去的不是由于它自身增加的量
                            lampMap.remove(lampKey);
                        }
                    }
                }
            }
        }

        return ans;
    }

    private int computeDiagonalNum(int x, int y, int n) {
        return n-1 + (y-x);
    }

    private int computeBackDiagonalNum(int x, int y) {
        return x + y;
    }

    private String buildKey(int i, int j) {
        return i + "-" + j;
    }
}
