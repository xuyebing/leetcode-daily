class Solution {

    public int secondMinimum(int n, int[][] edges, int time, int change) {
        List<Integer>[] graphs = new List[n+1]; // 每个节点能到的节点的列表
        for (int i = 0; i < n+1; i++) {
            graphs[i] = new ArrayList<Integer>();
        }
        int l = edges.length;
        for (int i = 0; i < l; i++) {
            graphs[edges[i][0]].add(edges[i][1]);
            graphs[edges[i][1]].add(edges[i][0]);
        }
        // define path[i][2]: path[i][0]记录从1到i的最短路径长度（节点个数）；path[i][1]记录从1到i的第二短路径长度
        int[][] path = new int[n+1][2];
        for (int i = 1; i < n+1; i++) {
            // path[i][0] = Integer.MAX_VALUE;
            // path[i][1] = Integer.MAX_VALUE;
            Arrays.fill(path[i], Integer.MAX_VALUE);
        }
        path[1][0]=0;
        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[]{1, 0});

        while (path[n][1] == Integer.MAX_VALUE) {
            int[] tmp = queue.poll();
            int node = tmp[0], len = tmp[1];
            List<Integer> nextNodes = graphs[node];
            for (int nd : nextNodes) {
                if (len+1 < path[nd][0]) {
                    path[nd][0] = len + 1;
                    queue.offer(new int[]{nd, len+1});
                } else if (len+1 > path[nd][0] && len+1 < path[nd][1]) {
                    path[nd][1] = len+1;
                    queue.offer(new int[]{nd, len+1});
                }
            }
        }

        return computeTime2(path[n][1], time, change);
    }

    private int computeTime2(int num, int time, int change) {
        int sumTime = 0;
        for (int i = 1; i < num; i++) {
            sumTime += time;
            // compute waiting time at ith node
            int greenOrRed = sumTime / change;
            // greenOrRed%2 == 0, means green light, if greenOrRed % 2 ==1, means red light and needs to wait
            if (greenOrRed%2 == 1) {
                // waiting time
                int waitTime = change - sumTime % change;
                sumTime += waitTime;
            }
        }
        sumTime += time;
        return sumTime;
    }

    /* 1st Version, DFS */
    // public int secondMinimum(int n, int[][] edges, int time, int change) {
    //     // 根据推理，得出，只要路径上节点数目越多，则到达的时间必定越长。
    //     // 据此，题目变换为找到从1到n的所有路径中，节点数目第二短的路径。
    //     // 注意，除了根据edge找到所有路径外，还要额外考虑一条路径：最短路径+2个节点的路径（即，经过最短路径最后两个节点2次的路径）
    //     TreeMap<Integer, Integer> tMap = findPaths(n, edges); // tMap中Key为从1到n的路径的节点个数（长度），value为该长度下所有的路径的个数
        
    //     // find the 2nd shortest path
    //     int shortestPath = tMap.firstKey();
    //     tMap.remove(shortestPath);
    //     int secondShortestPath = tMap.firstKey();

    //     // 根据第二短的路径的节点个数来计算时间
    //     return computeTime(secondShortestPath, time, change);
    // }

    // private int computeTime(int num, int time, int change) {
    //     int sumTime = 0;
    //     for (int i = 1; i < num-1; i++) {
    //         sumTime += time;
    //         // compute waiting time at ith node
    //         int greenOrRed = sumTime / change;
    //         // greenOrRed%2 == 0, means green light, if greenOrRed % 2 ==1, means red light and needs to wait
    //         if (greenOrRed%2 == 1) {
    //             // waiting time
    //             int waitTime = change - sumTime % change;
    //             sumTime += waitTime;
    //         }
    //     }
    //     sumTime += time;
    //     return sumTime;
    // }

    // private TreeMap<Integer, Integer> findPaths(int n, int[][] edges) {
    //     TreeMap<Integer, Integer> tMap = new TreeMap<>();

    //     int l = edges.length;
    //     Map<Integer, Set<Integer>> map = new HashMap<>(); // key: 1个节点，value，和key节点联通的所有的节点集合

    //     for (int i = 0; i < l; i++) {
    //         Set<Integer> v1 = map.computeIfAbsent(edges[i][0], (k)-> new HashSet<Integer>());
    //         v1.add(edges[i][1]);
    //         Set<Integer> v2 = map.computeIfAbsent(edges[i][1], (k) -> new HashSet<Integer>());
    //         v2.add(edges[i][0]);
    //     }
    //     // DFS, find all the path from node1 to nodeN
    //     Stack<Integer> stack = new Stack<Integer>();
    //     stack.push(1);
    //     findPathsCore(n, 1, map, stack, tMap);
        
    //     // insert 额外考虑一条路径：最短路径+2个节点的路径（即，经过最短路径最后两个节点2次的路径）into tMap
    //     int shortestPath = tMap.firstKey();
    //     int anotherPath = shortestPath + 2;
    //     int anotherPathV = tMap.getOrDefault(anotherPath, 0);
    //     tMap.put(anotherPath, anotherPathV + 1);

    //     return tMap;
    // }

    // private void findPathsCore(int n, int curNode, Map<Integer, Set<Integer>> map, Stack<Integer> stack, TreeMap<Integer, Integer> tMap) {
    //     Set<Integer> values = map.get(curNode);
    //     for (int v : values) {
    //         if (stack.contains(v))
    //             continue;

    //         stack.push(v);
    //         if (v != n) {
    //             findPathsCore(n, v, map, stack, tMap);
    //         } else {
    //             int size = stack.size();
    //             // List<Integer> list = new ArrayList<Integer>(stack);
    //             // List<List<Integer>> res = tMap.computeIfAbsent(size, (k) -> new ArrayList<List<Integer>>());
    //             // res.add(list);
    //             int cv = tMap.getOrDefault(size, 0);
    //             tMap.put(size, cv+1);
    //         }
    //         stack.pop();
    //     }
    // }
}
