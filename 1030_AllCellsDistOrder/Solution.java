class Solution {
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右
    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        // BFS 广度优先
        Queue<int[]> queue = new LinkedList<int[]>();
        int[] q0 = {rCenter, cCenter};
        queue.offer(q0);
        
        int[][] result = new int[rows * cols][2];
        int i = 0;
        result[i++] = q0;

        boolean[][] flag = new boolean[rows][cols];
        flag[rCenter][cCenter] = true;

        while (!queue.isEmpty()) {
            int[] q = queue.poll();
            for (int[] d : directions) {
                int x = q[0] + d[0];
                int y = q[1] + d[1];
                if (x>=0 && x < rows && y>=0 && y < cols && !flag[x][y]) {
                    int[] t = {x, y};
                    queue.offer(t);
                    result[i++] = t;
                    flag[x][y] = true;
                }
            }
        }

        return result;
    }
}
