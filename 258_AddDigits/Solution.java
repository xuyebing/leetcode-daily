class Solution {
    public int addDigits(int num) {
        int res = -1;
        while (true) {
            int sum = 0;
            while (num > 0) {
                sum += num%10;
                num /= 10;
            }
            if (sum < 10) {
                res = sum;
                break;
            }
            num = sum;
        }
        return res;
    }
}
