class Solution {
    public List<Integer> luckyNumbers (int[][] matrix) {
        // 思路：先找到每一行的最小元素 和 每一列的最大元素，time complexity = O(mn);
        //     然后，由于矩阵中的数字各不相同，只需要求2者的交集，然后返回
        Set<Integer> rows = new HashSet<Integer>();
        Set<Integer> cols = new HashSet<Integer>();
        
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            int min = matrix[i][0];
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                }
            }
            rows.add(min);
        }
        for (int j = 0; j < n; j++) {
            int max = matrix[0][j];
            for (int i = 1; i < m; i++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
            cols.add(max);
        }

        // 求rows, cols的交集
        rows.retainAll(cols);
        return rows.stream().collect(Collectors.toList());
    }
}
