class Solution {
    public String longestDiverseString(int a, int b, int c) {
        // a, b, c总共有4种大小情况
        /**
         * 1. x > y > z (a, b, c 均不等)
         * 2. x == y > z (有2个值相等，且大于第三个值)
         * 3. x == y < z (有2个值相等，且小于第三个值)
         * 4. x == y == z (3个值均相等)
         * 对于情况2 和 4， 一种正确答案是轮流遍历3个字符直道所有剩余数目为0；
         * 对于情况1 和 3， 需要想办法让它们变成情况 2 和 4，然后按照情况2、4来处理
         */
         char c1 = 'a', c2 = 'b', c3 = 'c';
         int max = maxV(a, b, c);
         int cnt1 = max;
         int cnt2 = 0;
         int cnt3 = 0;
         if (a == max) {
             c1 = 'a';
             if (b >= c) {
                 c2 = 'b';
                 c3 = 'c';
                 cnt2 = b;
                 cnt3 = c;
             } else {
                 c2 = 'c';
                 c3 = 'b';
                 cnt2 = c;
                 cnt3 = b;
             }
         } else if (b == max) {
             c1 = 'b';
             if (a >= c) {
                 c2 = 'a';
                 c3 = 'c';
                 cnt2 = a;
                 cnt3 = c;
             } else {
                 c2 = 'c';
                 c3 = 'a';
                 cnt2 = c;
                 cnt3 = a;
             }
         } else {
             c1 = 'c';
             if (a >= b) {
                 c2 = 'a';
                 c3 = 'b';
                 cnt2 = a;
                 cnt3 = b;
             } else {
                 c2 = 'b';
                 c3 = 'a';
                 cnt2 = b;
                 cnt3 = a;
             }
         }
         StringBuilder sb = new StringBuilder();
         // 情况 1 或 3
        if (cnt1 > cnt2) {
            int i = 0;
            while (cnt1 > cnt2 && (cnt2 > 0 || cnt3 > 0)) {
                sb.append("" + c1);
                cnt1 -= 1;
                if (cnt1 > 0) {
                    sb.append("" + c1);
                    cnt1 -= 1;
                }
                if (cnt2 > cnt3) {
                    sb.append("" + c2);
                    cnt2 -= 1;
                } else {
                    if (cnt2 > 0 && i%2 == 0) {
                        sb.append("" + c2);
                        cnt2 -= 1;
                    } else if (cnt3 > 0 && i%2 == 1) {
                        sb.append("" + c3);
                        cnt3 -=1;
                    }
                    i++;
                }
            }
            if (cnt1 > cnt2) {
                if (cnt1 == 1) {
                    sb.append("" + c1);
                    cnt1--;
                } else {
                    sb.append("" + c1 + c1);
                    cnt1 -= 2;
                }
            } else {
                // 情况2或者情况4
                String subStr = constructStr(c1, cnt1, c2, cnt2, c3, cnt3);
                sb.append(subStr);
            }
        } else { // 情况2 或者 4
            String subStr = constructStr(c1, cnt1, c2, cnt2, c3, cnt3);
            sb.append(subStr);
        }
        return sb.toString();
    }

    private String constructStr(char c1, int cnt1, char c2, int cnt2, char c3, int cnt3) {
        StringBuilder sb = new StringBuilder();
        while (cnt1 > 0) {
            sb.append(""+c1);
            cnt1--;
            if (cnt2 > 0) {
                sb.append(""+c2);
                cnt2--;
            }
            if (cnt3 > 0) {
                sb.append(""+c3);
                cnt3--;
            }
        }
        while (cnt2 > 0) {
            sb.append(""+c2);
            cnt2--;
            if (cnt3 > 0) {
                sb.append(""+c3);
                cnt3--;
            }
        }
        while (cnt3 > 0) {
            sb.append(""+c3);
            cnt3--;
        }
        return sb.toString();
    }


    private int maxV(int a, int b, int c) {
        int max = a;
        if (b > max)
            max = b;
        if (c > max)
            max = c;

        return max;
    }
}
