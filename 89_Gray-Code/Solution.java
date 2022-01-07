class Solution {

    List<Integer> gc1 = new ArrayList<Integer>();

    public List<Integer> grayCode(int n) {
        gc1.add(0);
        gc1.add(1);

        if (n == 1) {
            return gc1;
        }

        List<Integer> res = new ArrayList<Integer>(gc1);
        for (int i = 1; i < n; i++) {
            List<Integer> tmp = new ArrayList<Integer> (res);
            int baseValue = 1 << i;
            int l = res.size();
            for (int j = l-1; j >= 0; j--) {
                int v = res.get(j);
                tmp.add(baseValue + v);
            }
            res = tmp;
        }
        return res;
    }
}
