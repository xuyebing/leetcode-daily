class Solution {

    int n;
    int[][] graph;
    int[][][] dp;

    public int catMouseGame(int[][] graph) {
        this.n = graph.length;
        this.graph = graph;
        dp = new int[n][n][2*n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 2*n; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return getResult(1, 2, 0);
    }

    private int getResult(int mouse, int cat, int turns) {
        if (turns == 2 * n) {
            return 0;
        }
        if (dp[mouse][cat][turns] < 0) {
            if (mouse == 0) {
                dp[mouse][cat][turns] = 1;
            } else if (cat == mouse) {
                dp[mouse][cat][turns] = 2;
            } else {
                getNextResult(mouse, cat, turns);
            }
        }
        return dp[mouse][cat][turns];
    }

    private void getNextResult(int m, int c, int t) {
        int curMove = t%2 == 0 ? m : c;
        int defaultResult = curMove == m ? 2 : 1;
        int result = defaultResult;
        int[] nextNodes = graph[curMove];
        for (int next : nextNodes) {
            if (curMove == c && next == 0) {
                continue;
            }
            int nextMouse = curMove == m ? next : m;
            int nextCat = curMove == c ? next : c;
            int nextResult = getResult(nextMouse, nextCat, t+1);
            if (nextResult != defaultResult) {
                result = nextResult;
                if (result != 0) {
                    break;
                }
            }
        }
        dp[m][c][t] = result;
    }
}
