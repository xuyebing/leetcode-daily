class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        PriorityQueue<int[]> candidates = new PriorityQueue<int[]>(k, (a,b) -> {
            return nums1[a[0]] + nums2[a[1]] - nums1[b[0]] - nums2[b[1]];
        });

        for (int i = 0; i < Math.min(m, k); i++) {
            candidates.offer(new int[] {i, 0});
        }

        int s = k;
        while (s-- > 0 && !candidates.isEmpty()) {
            int[] c = candidates.poll();
            List<Integer> tlist = new ArrayList<Integer>();
            tlist.add(nums1[c[0]]);
            tlist.add(nums2[c[1]]);
            result.add(tlist);
            if (c[1] + 1 < n) {
                candidates.offer(new int[]{c[0], c[1]+1});
            }
        }

        return result;
    }
}

