class Solution {
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int numEnclaves(int[][] grid) {
        // 广度优先；另外，使用一个额外的空间保存已经走过的1的点
        Queue<int[]> queue = new LinkedList<int[]>();
        Set<String> setOne = new HashSet<String>();
        // 将grid的四个边界上的所有1入队
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < n; i++) {
            if (grid[0][i] == 1) {
                queue.offer(new int[]{0, i});
                setOne.add(buildStr(0,i));
            }
            if (m>1) {
                if (grid[m-1][i] == 1) {
                    queue.offer(new int[]{m-1, i});
                    setOne.add(buildStr(m-1, i));
                }
            }
        }
        for (int i = 1; i < m-1; i++) {
            if (grid[i][0] == 1) {
                queue.offer(new int[]{i, 0});
                setOne.add(buildStr(i, 0));
            }
            if (grid[i][n-1] == 1) {
                queue.offer(new int[]{i, n-1});
                setOne.add(buildStr(i, n-1));
            }
        }

        // 开始广度遍历，直到队列为空
        while (!queue.isEmpty()) {
            int[] element = queue.poll();
            int x = element[0];
            int y = element[1];
            for (int[] d : directions) {
                int x1 = x + d[0];
                int y1 = y + d[1];
                if (x1 >= 0 && x1 < m && y1 >= 0 && y1 < n) {
                    if (grid[x1][y1] == 1 && !setOne.contains(buildStr(x1, y1))) {
                        queue.offer(new int[]{x1, y1});
                        setOne.add(buildStr(x1, y1));
                    }
                }
            }
        }

        // 遍历整个grid，统计总共多少个1，与setOne.size 做差（即总数减去所有能离开的1的数目，剩余的就是无法离开的1的个数）
        int sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    sum++;
                }
            }
        }
        return sum - setOne.size();
    }

    private String buildStr (int i, int j) {
        return i + "-" + j;
    }
}
