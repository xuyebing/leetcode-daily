class Solution {
    public int minJumps(int[] arr) {
        if (arr.length == 1)
            return 0;
        // 1. 将所有相同的value 找出来，存在map中, key: 数值, value: 这个数值所在的下标列表
        int l = arr.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < l; i++) {
            int k = arr[i];
            List<Integer> values = map.computeIfAbsent(k, (k1) -> new ArrayList<Integer>());
            values.add(i);
        }

        /* 3rd version */
        Queue<int[]> queue = new ArrayDeque<int[]>();
        int[] a = new int[]{0, 0};
        queue.add(a);
        boolean[] exists = new boolean[l];
        exists[0]=true;

        while (!queue.isEmpty()) {
            int[] t = queue.poll();
            int idx = t[0], step = t[1];
            if (idx == l-1)
                return step;
            if (map.containsKey(arr[idx])) {
                List<Integer> vs = map.get(arr[idx]);
                for(int i : vs) {
                    if (!exists[i]) {
                        queue.add(new int[]{i, step+1});
                        exists[i] = true;
                    }
                }
                map.remove(arr[idx]);
            }
            if (idx+1 < l && !exists[idx+1]) {
                queue.add(new int[]{idx+1, step+1});
                exists[idx+1] = true;
            }
            if (idx-1 >= 0 && !exists[idx-1]) {
                queue.add(new int[]{idx-1, step+1});
                exists[idx-1] = true;
            }
        }
        return -1;

        /* 1st version */
//        boolean[] exists = new boolean[l];
//        // start from index 0
//        int v = arr[0];
//        List<Integer> vs = new ArrayList<>(map.get(v));
//        exists[0] = true;
//        int count = 1;
//        vs.remove((Integer)0); // remove index 0;
//        List<Integer> nextLayer = vs;
//        nextLayer.add(1); // add index 1 since it's 1 step away from index 0;
//        int jump = 1;
//        int[] minJump = new int[l];
//        minJump[0] = 0;
//
//        while (count<l) {
//            List<Integer> copyList = new ArrayList<Integer>();
//            while (!nextLayer.isEmpty()) {
//                int idx = nextLayer.remove(nextLayer.size()-1); // check the last index.
//                if (exists[idx])
//                    continue;
//                exists[idx] = true;
//                count++;
//                minJump[idx] = jump;
//                if (idx == l-1)
//                    return jump;
//                List<Integer> vls = new ArrayList<>(map.get(arr[idx]));
//                vls.remove((Integer)idx);
//                if (idx >= 1)
//                    vls.add(idx-1);
//                if (idx <= l-2)
//                    vls.add(idx+1);
//                copyList.addAll(vls);
//            }
//            nextLayer = copyList;
//            copyList = null;
//            jump++;
//        }
//        return minJump[l-1];
        
        /* 2nd version */
        // boolean[] exists = new boolean[l];
        // Queue<int[]> queue = new ArrayDeque<int[]>();
        // queue.offer(new int[]{0, 0});
        // exists[0] = true;
        // while (!queue.isEmpty()) {
        //     int[] idxStep = queue.poll();
        //     int idx = idxStep[0], step = idxStep[1];
        //     if (idx == l-1) {
        //         return step;
        //     }
        //     int v = arr[idx];
        //     step++;
        //     if (map.containsKey(v)) {
        //         List<Integer> vlist = map.get(v);
        //         for (int t : vlist) {
        //             if (!exists[t]) {
        //                 queue.offer(new int[]{t, step});
        //                 exists[t] = true;
        //             }
        //         }
        //         map.remove(v);
        //     }
        //     if (idx+1 < l && !exists[idx+1]) {
        //         queue.offer(new int[]{idx+1, step});
        //         exists[idx+1] = true;
        //     }
        //     if (idx - 1 >=0 && !exists[idx-1]) {
        //         queue.offer(new int[]{idx-1, step});
        //         exists[idx-1] = true;
        //     }
        // }
        // return -1;
    }
}
