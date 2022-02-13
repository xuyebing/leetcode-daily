class Solution {
    public int maxNumberOfBalloons(String text) {
        // 思路：统计b, a, l, o, n 这5个字母的出现次数，然后返回 min{b_num, a_num, l_num/2, o_num/2, n}
        int bNum = 0, aNum = 0, lNum = 0, oNum = 0, nNum = 0;

        int l = text.length();
        for (int i = 0; i < l; i++) {
            char c = text.charAt(i);
            switch (c) {
                case 'b':
                    bNum++;
                    break;
                case 'a':
                    aNum++;
                    break;
                case 'l':
                    lNum++;
                    break;
                case 'o':
                    oNum++;
                    break;
                case 'n':
                    nNum++;
                    break;
                default:
                    break;
            }
        }

        int min = bNum;
        if (min > aNum) {
            min = aNum;
        }
        if (min > lNum/2) {
            min = lNum/2;
        }
        if (min > oNum/2) {
            min = oNum/2;
        }
        if (min > nNum) {
            min = nNum;
        }
        return min;
    }
}
