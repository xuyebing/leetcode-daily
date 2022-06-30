class Solution {

    private int modNum = 1000000007;

    public int numPrimeArrangements(int n) {
        // 翻译后，其实就是看n里面有多少个质数，然后求全排列。例如，加入有k个质数，则返回结果是k! * (n-k)!
        int num = 0;

        for (int i = 1; i <= n; i++) {
            if (isPrime(i)) {
                num++;
            }
        }

        long result1 = 1;
        for (int i = 1; i <= num; i++) {
            result1 *= i;
            result1 = result1 % modNum;
        }
        long result2 = 1;
        for (int i = 1; i <= n - num; i++) {
            result2 *= i;
            result2 = result2 % modNum;
        }
        return (int)(result1 * result2%modNum);
    }

    private boolean isPrime(int a) {
        if (a == 1)
            return false;
        for (int i = 2; i * i <= a; i++) {
            if (a % i == 0)
                return false;
        }
        return true;
    }
}
