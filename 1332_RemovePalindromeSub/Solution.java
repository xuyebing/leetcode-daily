class Solution {
    public int removePalindromeSub(String s) {
        int l = s.length();
        if (isPalindrome(s, 0, l-1)) {
            return 1;
        }
        return 2;
    }

    private boolean isPalindrome(String s, int b, int e) {
        while (b < e) {
            if (s.charAt(b++) != s.charAt(e--)) {
                return false;
            }
        }
        return true;
    }
}
