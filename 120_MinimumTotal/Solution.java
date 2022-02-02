class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        int[] f = new int[n];
        List<Integer> lastLevel = triangle.get(n-1); // 拿最底层
        for (int i = 0; i < n; i++) {
            f[i] = lastLevel.get(i); // 初始化f为最底层的值
        }

        for (int i = n-2; i >= 0; i--) {
            List<Integer> level = triangle.get(i);
            int l = level.size();
            for (int j = 0; j < l; j++) {
                f[j] = min(f[j], f[j+1]) + level.get(j);
            }
        }

        return f[0];
    }

    private int min(int a, int b) {
        return (a < b) ? a : b;
    }
}
