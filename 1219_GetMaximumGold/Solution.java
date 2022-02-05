class Solution {

    int[][] directions = {{0,-1},{0,1},{1,0},{-1,0}};
    public int getMaximumGold(int[][] grid) {
        // 以每个黄金格为起点，计算从它开始能挖到的黄金最多的数量
        List<int[]> golds = new ArrayList<int[]>();
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    int[] t = {i, j};
                    golds.add(t);
                }
            }
        }

        int size = golds.size();
        int max = 0;
        for (int i = 0; i < size; i++) {
            int[] start = golds.get(i);

            Map<String, Boolean> flags = new HashMap<String, Boolean>();
            String s = buildStr(start[0], start[1]);
            flags.put(s, true);

            int sum = grid[start[0]][start[1]];
            int tmax = findCore(grid, start, flags, sum);
            if (max < tmax)
                max = tmax;
        }
        return max;
    }

    private int findCore(int[][] grid, int[] cur, Map<String, Boolean> flags, int sum) {
        int m = grid.length;
        int n = grid[0].length;
        int a = cur[0];
        int b = cur[1];
        LinkedList<int[]> queue = new LinkedList<int[]>();
        for (int i = 0; i < directions.length; i++) {
            int[] d = directions[i];
            int a1 = a + d[0];
            int b1 = b + d[1];
            if (a1 >= 0 && a1 < m && b1 >=0 && b1 < n) {
                if (grid[a1][b1] > 0 && flags.get(buildStr(a1,b1)) == null) {
                    queue.add(new int[]{a1,b1});
                }
            }
        }
        if (queue.isEmpty()) {
            return sum;
        } else {
            int max = sum;
            for (int i = 0; i < queue.size(); i++) {
                int[] ele = queue.get(i);
                int tmax = sum + grid[ele[0]][ele[1]];
                flags.put(buildStr(ele[0], ele[1]), true);
                int ret = findCore(grid, ele, flags, tmax);
                if (ret > max) {
                    max = ret;
                }
                flags.remove(buildStr(ele[0], ele[1]));
            }
            return max;
        }
    }

    private String buildStr(int i, int j) {
        return i + "-" + j;
    }
}
