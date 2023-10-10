class Solution {
    public int sumDistance(int[] nums, String s, int d) {
        // 1. 根据s[i] 和 d，计算出每个nums[i] 移动后的距离；
        //    根据特性1（机器人无差别），机器人碰撞后可以视作不转向继续按原方向移动
        int len = nums.length;
        long[] n = new long[len];
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == 'L') {
                n[i] = nums[i] - d;
            } else {
                n[i] = nums[i] + d;
            }
        }
        // 2. 对移动结束后的数组 n 排序
        Arrays.sort(n);

        // 3. 按照公式计算总距离
        /** 公式： 设排好序后的数组为num, 另 Ai = num[i] - num[i-1] (i>=1) (即Ai为num中相邻两个数的差)
                    则有公式：sum = 求和(i * (n-i)* Ai) (1<= i < n)
         */
         long sum = 0;
         for (int i = 1; i < len; i++) {
            long tmp = (long)i * (long)(len-i) % 1000000007;
            tmp *= (n[i] - n[i-1]) % 1000000007;
            sum += tmp % 100000000  7;
            // option 1: sum += i * (len-i) * (n[i] - n[i-1]);
            // 由于答案可能很大，需要对sum每次计算后取模
            sum %= 1000000007;
        }

        return (int)sum;
    }
}
