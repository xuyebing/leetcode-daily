class Solution {
    public int findKthPositive(int[] arr, int k) {
        int l = arr.length;
    
        int sumDiff = arr[0]-1;
        if (sumDiff >= k) {
            return k;
        }
        int i = 1;
        while (sumDiff < k && i < l) {
            sumDiff += arr[i] - arr[i-1] - 1;
            i++;
        }
        if (sumDiff >= k) {
            i--;
            int lastDiff = arr[i] - arr[i-1] - 1;
            int lastSumDiff = sumDiff - lastDiff;
            int gap = k - lastSumDiff;
            return arr[i-1] + gap;
        } else { // means while break reason is "i==l"
            i--;
            int gap = k - sumDiff;
            return arr[i] + gap;
        }
    }
}
