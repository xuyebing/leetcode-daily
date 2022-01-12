class Solution {
    public boolean increasingTriplet(int[] nums) {
        int l = nums.length;
        if (l < 3) {
            return false;
        }

        Pair p1 = find1stUp(nums, 0);
        if (p1 == null) {
            return false;
        }

        while (true) {
            int n1 = p1.bi+1;
            if (n1 >= l) {
                break;
            }
            // begin judge
            if (nums[n1] > p1.b) {
                return true;
            } else if (nums[n1] > p1.a) {
                p1.b = nums[n1];
                p1.bi = n1;
            } else { // nums[n1] <= p1.a
                // Find next Up trend
                Pair p2 = find1stUp(nums, n1);
                if (p2 == null) {
                    return false;
                }
                // begin compare p1 and p2
                if (p2.b > p1.b) {
                    return true;
                } else { // do not need to keep p1, replace p1 with p2 (i.e. p2 will be the new p1)
                    p1 = p2;
                }
            }
        }

        return false;
    }

    private Pair find1stUp(int[] nums, int s) {
        int l = nums.length;
        for (int i = s; i < l-1; i++) {
            if (nums[i] < nums[i+1]) {
                return new Pair(nums[i], nums[i+1], i, i+1);
            }
        }
        return null;
    }
}

class Pair {
    int a;
    int b;
    int ai;
    int bi;

    Pair(int a, int b, int ai, int bi) {
        this.a = a;
        this.b = b;
        this.ai = ai;
        this.bi = bi;
    }
}
