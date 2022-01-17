class Solution {
    public int findMinDifference(List<String> timePoints) {
        int l = timePoints.size();
        PriorityQueue<String> pq = new PriorityQueue<>(l);

        for (String t : timePoints) {
            pq.add(t);
        }
        // go through pq, remember to check the last value with the 1st value
        String firstTime = pq.poll();
        String prev = firstTime;
        int min = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            String t = pq.poll();
            int v = computeTimeDiff(t, prev);
            if (min > v) {
                min = v;
            }
            prev = t;
        }
        // compute the diff from prev to firstTime, since clock it's a circle
        int tmp = computeTimeDiff(prev, firstTime);
        int m1 = 24*60 - tmp; // the timeDiff from prev to firstTime = 24 * 60 mins - timeDiff from firstTime to prev
        if (min > m1) { 
            min = m1;
        }
        return min;
    }

    private int computeTimeDiff(String big, String small) {
        String[] bs = big.split(":");
        String[] ss = small.split(":");

        int hourDiff = toInt(bs[0]) - toInt(ss[0]);
        int minDiff = toInt(bs[1]) - toInt(ss[1]);

        return hourDiff * 60 + minDiff;
    }

    private int toInt(String s) {
        int res = 0;
        int l = s.length();
        for (int i = 0; i < l; i++) {
            int t = (int)(s.charAt(i) - '0');
            res = res*10 + t;
        }
        return res;
    }
}
