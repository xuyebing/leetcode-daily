class Solution {
    public int findLUSlength(String a, String b) {
        int la = a.length();
        int lb = b.length();

        if (la != lb) {
            return la > lb ? la : lb;
        } else { // la == lb
            if (a.equals(b)) {
                return -1;
            } else {
                return la;
            }
        }
    }
}
