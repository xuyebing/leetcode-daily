class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        int len = candidates.length;
        int idx = Arrays.binarySearch(candidates, target);
        if (idx < 0) { // idx = - <insertion point> - 1
            idx = 0 - idx - 2; // i.e. idx should = <insertion point> - 1
        }
        if (idx < 0) { // means no one in candidates array is <= target
            return new ArrayList<>();
        }
        // when idx >= 0
        return combSumCore(candidates, idx, target);
    }

    private List<List<Integer>> combSumCore(int[] candidates, int e, int target) {
        List<List<Integer>> resList = new ArrayList<>();

        if (e == 0) {
            int mod = target%candidates[e];
            if (mod == 0) {
                int l = target/candidates[e];
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < l; i++) {
                    list.add(candidates[e]);
                }
                resList.add(list);
            }
            return resList;
        }
        // e > 0
        int ele = candidates[e];
        int mod = target%ele;
        int div = target/ele;

        if (div == 0) {
            return resList;
        }
        for (int i = 0; i <= div; i++) {
            int newTarget = target - i*ele;
            int newIdx = e-1;
            if (newTarget != target)
                newIdx = Arrays.binarySearch(candidates, 0, e, newTarget); // 只能在 candidates[0] to candidates[e-1]的范围内二分查找. 否则，会包含重复组合。
            
            if (newIdx < 0) { // idx = - <insertion point> - 1
                newIdx = 0 - newIdx - 2; // i.e. idx should = <insertion point> - 1
            }
            List<List<Integer>> rList = new ArrayList<>();
            if (newIdx >= 0) {
                rList = combSumCore(candidates, newIdx, newTarget);
            }
            int rSize = rList.size();
            for (int k = 0; k < rSize; k++) {
                List<Integer> list = rList.get(k);
                for (int j = 0; j < i; j++) {
                    list.add(ele);
                }
                resList.add(list);
            }
        }

        if (mod == 0) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < div; i++) {
                list.add(ele);
            }
            resList.add(list);
        }
        
        return resList;
    }
}
