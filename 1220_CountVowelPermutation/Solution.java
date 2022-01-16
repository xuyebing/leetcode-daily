class Solution {

    public int countVowelPermutation(int n) {

        long mod = 1000000007L;
        long[][] net = new long[5][n];
        for (int i = 0; i < 5; i++) { // initialize the 1st column.
            net[i][0] = 1;
        }
        for (int i = 1; i < n; i++) { // compute n column based on n-1 column.
            for (int j = 0; j < 5; j++) {
                switch (j) {
                    case 0: // a(i) = e(i-1)
                        net[j][i] = net[1][i-1]%mod;
                        break;
                    case 1: // e(i) = a(i-1) + i(i-1)
                        net[j][i] = (net[0][i-1] + net[2][i-1]) % mod;
                        break;
                    case 2: // i(i) = a(i-1) + e(i-1) + o(i-1) + u(i-1)
                        net[j][i] = (net[0][i-1] + net[1][i-1] + net[3][i-1] + net[4][i-1]) % mod;
                        break;
                    case 3: // o(i) = i(i-1) + u(i-1)
                        net[j][i] = (net[2][i-1] + net[4][i-1]) % mod;
                        break;
                    case 4: // u(i) = a(i-1)
                        net[j][i] = net[0][i-1] % mod;
                        break;
                }
            }
        }

        // result = sum (column i-1)
        long sum = 0;
        for (int j = 0; j < 5; j++) {
            sum = (sum + net[j][n-1]) % mod;
        }
         
        return  (int)sum;
    }
}
