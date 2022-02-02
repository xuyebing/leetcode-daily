class Solution {
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int size = 4 * n * n;

        UnionFindSet ufs = new UnionFindSet(size);

        for (int i = 0; i < n; i++) {
            String s = grid[i];
            char[] cs = s.toCharArray();
            for (int j = 0; j < n; j++) {
                char c = cs[j];
                int x0 = 4 * (i*n + j);
                int x1 = 4 * (i*n + j) + 1;
                int x2 = 4 * (i*n + j) + 2;
                int x3 = 4 * (i*n + j) + 3;
                // 单元格内
                if (c == '\\') {
                    ufs.union(x0, x1);
                    ufs.union(x2, x3);
                } else if (c == '/') {
                    ufs.union(x0, x3);
                    ufs.union(x1, x2);
                } else {
                    ufs.union(x0, x1);
                    ufs.union(x1, x2);
                    ufs.union(x2, x3);
                }
                // 单元格间（朝下、朝右）
                //// 朝下
                if (i+1 < n) {
                    int x0next = 4 * ((i+1)*n + j);
                    ufs.union(x2, x0next);
                }
                //// 朝右
                if (j+1 < n) {
                    int x3next = 4 * (i*n + j+1) + 3;
                    ufs.union(x1, x3next);
                }
            }
        }
        return ufs.count;
    }
}

class UnionFindSet {
    
    int count;
    int[] parent;
    
    public UnionFindSet(int size) {
        count = size;
        parent = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    int find(int x) {
        while (x != parent[x]) {
            parent[x] = find(parent[x]);
            x = parent[x];
        }
        return parent[x];
    }

    void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py)
            return;

        parent[px] = py;
        count--;
    }
}
