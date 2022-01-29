class Solution {
    Queue<Element> queue = new LinkedList<Element>();

    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;

        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++)
            Arrays.fill(res[i], -1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    res[i][j] = 0;
                    Element ele = new Element(i, j, 0);
                    queue.offer(ele);
                }
            }
        }

        while (!queue.isEmpty()) {
            Element ele = queue.poll();
            int x = ele.x, y = ele.y;
            // up
            if (x-1 >=0) {
                operate(x-1, y, res, ele);
            }
            // down
            if (x+1 <= m-1) {
                operate(x+1, y, res, ele);
            }
            // left
            if (y-1 >=0) {
                operate(x, y-1, res, ele);
            }
            // right
            if (y+1 <= n-1) {
                operate(x, y+1, res, ele);
            }
        }

        return res;        
    }

    private void operate(int x, int y, int[][] res, Element ele) {
         if (res[x][y] == -1) {
            res[x][y] = ele.h + 1;
            queue.offer(new Element(x, y, res[x][y]));
        } else if (ele.h+1 < res[x][y]) {
            res[x][y] = ele.h+1;
            queue.offer(new Element(x, y, res[x][y]));
        }
    }

}

class Element {
    int x;
    int y;
    int h;

    Element(int x, int y, int h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }
}
