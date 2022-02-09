class Solution {
    public List<String> simplifiedFractions(int n) {
        List<String> result = new ArrayList<>();
        if (n == 1) {
            return result;
        }
        for (int i = 1; i < n; i++) {
            if (!findCommonFactor(i, n)) {
                result.add(i+"/"+n);
            }
        }
        List<String> subResult = simplifiedFractions(n-1);
        result.addAll(subResult);
        return result;
    }

    private boolean findCommonFactor(int a, int b) {
        for (int i = 2; i <= a; i++) {
            if ((a%i==0) && (b%i==0)) {
                return true;
            }
        }
        return false;
    }
}
