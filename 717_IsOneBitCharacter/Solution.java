class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int l = bits.length;
        if (l == 1) {
            return true;
        }
        // l >= 2 的情况
        // 1. 如果剩余的bits数组以0开头，若（1) 剩余数组的长度>1, 则跳过1位，继续递归; (2) 数组长度==1， 则返回true
        // 2. 如果剩余的bits数组以1开头，若（1）剩余数组的长度>2, 则跳过2位，继续递归；（2）数组长度<=2, 则返回false
        int i = 0;
        boolean result = false;
        while (i < l) {
            int curLen = l-i;
            if (bits[i] == 0) {
                if (curLen > 1) {
                    i++;
                    continue;
                } else { // means curLen == 1
                    result = true;
                    break;
                }
            } else { // means bits[i]==1
                if (curLen > 2) {
                    i += 2;
                    continue;
                } else { // means curLen <= 2
                    result = false;
                    break;
                }
            }
        }

        return result;
    }
}
