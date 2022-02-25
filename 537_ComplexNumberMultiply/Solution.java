class Solution {
    public String complexNumberMultiply(String num1, String num2) {
        int[] n1 = findRealAndImaginaryNumber(num1);
        int[] n2 = findRealAndImaginaryNumber(num2);

        int realNum = n1[0] * n2[0] - n1[1] * n2[1];
        int imaginaryNum = n1[0] * n2[1] + n1[1] * n2[0];
        
        StringBuilder sb = new StringBuilder();

        sb.append(realNum + "+");
        sb.append(imaginaryNum + "i");

        return sb.toString();
    }

    private int[] findRealAndImaginaryNumber(String s) {
        String[] strs = s.split("\\+");
        int[] result = new int[2];
        result[0] = changeStrToInt(strs[0]); // real number
        result[1] = changeStrToInt(strs[1].substring(0, strs[1].length()-1));

        return result;
    }

    private int changeStrToInt(String s) {
        char[] chars = s.toCharArray();
        int l = chars.length;
        boolean flag = false;
        int i = 0;
        if (chars[i] == '-') {
            flag = true;
            i++;
        }
        int res = 0;
        for (; i < l; i++) {
            res = res * 10 + (int)(chars[i] - '0');
        }
        if (flag) {
            res *= -1;
        }
        return res;
    }
}
