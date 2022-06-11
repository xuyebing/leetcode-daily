class Solution {
    public int minFlipsMonoIncr(String s) {
        /**
        * 思路：每遍历到1，判断下以这个1为1序列的起点的话，需要反转的次数是多少。
        * 然后跟目前最小的反转次数比较，若更小，则更新最小值。
        * 另外，需要考虑全部是0 的情况的反转值。将它和最小值比较，得到最终的最小值。
        * Note: 计算某个1为1序列起点的反转次数 = 该1之前有多少个1 （需要把这些1转为0） + 该1 之后有多少个0（需要把这些0转为1）
        */
        int len = s.length();
        if (len == 1) {
            return 0;
        }
        int[] before1Num = new int[len]; // 记录每个索引位置之前有多少个1
        int[] after0Num = new int[len];  // 记录每个索引位置之后有多少个0

        before1Num[0] = 0;
        after0Num[len-1] = 0;
        for (int i = 1; i < len; i++) {
            if (s.charAt(i-1) == '1') {
                before1Num[i] = before1Num[i-1] + 1;
            } else {
                before1Num[i] = before1Num[i-1];
            }
        }
        for (int i = len-2; i >= 0; i--) {
            if (s.charAt(i+1) == '0') {
                after0Num[i] = after0Num[i+1] + 1;
            } else {
                after0Num[i] = after0Num[i+1];
            }
        }

        // check each 1
        int min = Integer.MAX_VALUE;
        int oneCount = 0; // 记录有多少个1, 也就是全部转为0需要的反转次数
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '1') {
                int tmp = before1Num[i] + after0Num[i];
                min = min > tmp ? tmp : min;
                oneCount++;
            }
        }
        min = min > oneCount ? oneCount : min;

        return min;
    }
}
