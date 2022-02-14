class Solution {
    public int singleNonDuplicate(int[] nums) {
        // 思路：要保证 time complexity = O(log n), 那只能是二分查找。
        // 二分查找的关键点，是确定下一步去哪一半接着寻找。
        // 有题目可知，所有的数都会出现两次，只有一个数会出现一次。那么，可以根据左右两半的奇偶性来判断接下来去哪一半，即：每次二分后，去奇数半边接着二分查找
        int l = nums.length;

        return findCore(nums, 0, l-1);
    }

    private int findCore(int[] nums, int s, int e) {
        if (s == e) {
            return nums[s];
        }
        int mid = s + (e-s)/2;
        if (nums[mid-1] != nums[mid]) {
            if (nums[mid] != nums[mid+1]) {
                return nums[mid];
            } else {
                int leftHalf = mid - s;
                if (leftHalf % 2 == 0) {
                    // 左半边是偶数，则去右半边继续二分
                    return findCore(nums, mid+2, e);
                } else {
                    // 左半边是奇数，则去左半边继续二分
                    return findCore(nums, s, mid-1);
                }
            }
        } else {
            int leftHalf = mid-1 - s;
            if (leftHalf % 2 == 0) {
                // 左半边是偶数，则去右半边继续二分
                return findCore(nums, mid+1, e);
            } else {
                // 左半边是奇数，则去左半边继续二分
                return findCore(nums, s, mid-2);
            }
        }
    }
}
