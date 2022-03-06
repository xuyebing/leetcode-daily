class Solution {
    public List<Integer> goodDaysToRobBank(int[] s, int t) {
        List<Integer> res = new ArrayList<Integer>();
        
        int l = s.length;
        if (l < 2 * t + 1) {
            return res;
        }
        if (t == 0) {
            for (int i = 0; i < l; i++) {
                res.add(i);
            }
            return res;
        }
        // 思路： step#1: 开始找左半边（非递增半边），如果遍历完整个数组都没有找到，返回res；
        //     若找到非递增半边 （设此时找到的最右侧索引为i）：
        //    判断从i到i+time 是否为右半边（非递减序列）
        //        (a) 如果不是，找到右侧第一个出现递减的起始下标（假设为j），从j开始，继续step#1.
        //        (b) 如果是，则将i加入res，然后：
        //            (A) 循环判断 security[i+1] == security[i] && security[i+1+t] >= security[i+t]
        //               (X) 若是，则将i+1加入 res，然后将i++，继续走到（A）
        //               (Y) 若不是，则跳出循环，然后设置 startIdx = i + 1，继续走step#1.
        int lStartIdx = 0;
        while (true) {
            int i = findLeft(s, lStartIdx, t);
            if (i == -1) {
                break;
            }
            int rIdx = confirmRight(s, i, t);
            if (rIdx == -1) {
                break;
            } else if (rIdx == 0) {
                // 说明i就是一个好日子
                res.add(i);
                while ((s[i] == s[i+1]) && (i+1+t < l) && (s[i+t] <= s[i+1+t])) {
                    res.add(++i);
                }
                if (s[i] != s[i+1]) {
                    lStartIdx = i+1;
                } else if (i+1+t >= l) {
                    break;
                } else { // 说明 s[i+t] > s[i+1+t]
                    lStartIdx = i+1;
                }
            } else {
                lStartIdx = rIdx;
            }
        }
        
        return res;
    }

    private int findLeft(int[] s, int startIdx, int t) {
        int l = s.length;
        if (startIdx + t > l-1) {
            return -1;
        }
        boolean flag = false;
        int nextIdx = -1;
        for (int i = 0; i < t; i++) {
            if (s[startIdx+i] < s[startIdx+i+1]) {
                flag = true;
                nextIdx = startIdx+i+1;
                break;
            } else {
                continue;
            }
        }
        if (flag) {
            return findLeft(s, nextIdx, t);
        } else {
            return startIdx + t;
        }
    }

    private int confirmRight(int[] s, int startIdx, int t) {
        int l = s.length;
        if (startIdx + t > l-1) {
            return -1; // -1 表示没有找到右侧，且数组遍历完毕，直接返回最终结果、整个程序结束。
        }
        boolean flag = false;
        int nextIdx = -1;
        for (int i = 0; i < t; i++) {
            if (s[startIdx+i] <= s[startIdx+i+1]) {
                continue;
            } else {
                flag = true;
                nextIdx = startIdx+i+1-t;
                break;
            }
        }
        if (flag) {
            return nextIdx; // 表示右侧不满足条件，从nextIdx 重新开始左半侧的搜索
        } else {
            return 0; // 表示右半侧满足条件。
        }
    }
}
