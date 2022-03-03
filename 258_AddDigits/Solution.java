class Solution {
    // Version 1
    // public int addDigits(int num) {
    //     int res = -1;
    //     while (true) {
    //         int sum = 0;
    //         while (num > 0) {
    //             sum += num%10;
    //             num /= 10;
    //         }
    //         if (sum < 10) {
    //             res = sum;
    //             break;
    //         }
    //         num = sum;
    //     }
    //     return res;
    // }

    // Version 2
    // 思路：通过观察发现如下规律
    // 0-> 0; 1 -> 1; 2 -> 2; ...; 9 -> 9
    //       10 -> 1; 11 -> 2; ...; 18 -> 9
    //       19 -> 1; 20 -> 2; ...; 27 -> 9
    //       28 -> 1; 29 -> 2; ...; 36 -> 9
    //       37 -> 1; 38 -> 2; ...; 45 -> 9
    // 规律：除了0以外，所有数模9的结果就是最终计算的结果，其中模9等于0的结果为9.
    public int addDigits(int num) {
        if (num == 0) {
            return 0;
        }
        int res = num % 9;
        if (res == 0) {
            res = 9;
        }
        return res;
    }
}
