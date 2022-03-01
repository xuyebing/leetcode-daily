class Solution {
    public String convert(String s, int n) {
        int l = s.length();
        if (l == 1 || n == 1) {
            return s;
        }

        char[] chars = s.toCharArray();
        
        /**
         * 组成第0行的下标是： 0, 2(n-1), 2(n-1) + 2(n-1), 2(n-1) + 2(n-1) + 2(n-1), ...
         * 组成第i行的下标是 (0< i < n-1): i, i + 2(n-1-i), i + 2(n-1) // 第1位加上2(n-1), i + 2(n-1-i) + 2(n-1) // 第2位加上2(n-1)
         *     即第i行的数由前两个数分别加上步长 2(n-1)来构成，第一位可以决定出第三位、第三位可以决定出第五位；第二位可以决定出第四位、第四位可以决定出第6位；以此类推
         * 组成最后一行的下标是：n-1, n-1 + 2(n-1), n-1 + 4(n-1), n-1 + 6(n-1), ...
         */
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            Queue<Integer> queue = new LinkedList<Integer>();
            if (i == 0 || i == n-1) {
                if (i < l)
                    queue.add(i);
                while (!queue.isEmpty()) {
                    int idx = queue.poll();
                    sb.append(s.charAt(idx));
                    int nextIdx = idx + 2*n - 2;
                    if (nextIdx < l) {
                        queue.add(nextIdx);
                    }
                }
            } else { // 0 < i < n-1;
                if (i < l)
                    queue.add(i);
                int secIdx = i + 2 * (n - 1 - i);
                if (secIdx < l) {
                    queue.add(secIdx);
                }
                while (!queue.isEmpty()) {
                    int idx = queue.poll();
                    sb.append(s.charAt(idx));
                    int nextIdx = idx + 2 * (n-1);
                    if (nextIdx < l) {
                        queue.add(nextIdx);
                    }
                }
            }
        }
        return sb.toString();
    }
}
