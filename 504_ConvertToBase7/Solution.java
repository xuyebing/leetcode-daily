class Solution {
    public String convertToBase7(int num) {
        StringBuilder sb = new StringBuilder();
        if (num == 0) {
            return "0";
        }
        boolean flag = false;
        if (num < 0) {
            flag = true;
            num = -1 * num;
        }
        while (num != 0) {
            int mod = num % 7;
            sb.append(mod+"");
            num /= 7;
        }
        if (flag)
            sb.append("-");
        sb = sb.reverse();
        
        return sb.toString();
    }
}
