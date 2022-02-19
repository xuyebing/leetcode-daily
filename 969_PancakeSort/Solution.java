class Solution {

    public List<Integer> pancakeSort(int[] arr) {
        int l = arr.length;
        List<Integer> result = sortCore(arr, l);

        return result;
    }

    private List<Integer> sortCore(int[] arr, int l) {
        List<Integer> res = new ArrayList<Integer>();
        if (l == 1) {
            return res;
        }
        int target = l;
        if (arr[target-1] == target) {
            // target元素已经在最终的位置，不需要翻转, 直接去看target-1长度的数组
            res = sortCore(arr, target-1);
        } else {
            // 寻找target元素所在的位置，然后进行两次反转 
            // 1） 从target元素所在位置反转一次，使得target元素到第一个位置;(如果target已经在第一个位置，则跳过)
            // 2）从l位置反转一次，使得target元素到l-1位置，即target的最终位置.
            int t = findIndex(arr, l);
            if (t != 0) {
                res.add(t+1);
                flipArray(arr, 0, t);
            }
            res.add(l);
            flipArray(arr, 0, l-1);
            List<Integer> res2 = sortCore(arr, target-1);
            res.addAll(res2);
        }
        return res;
    }

    private void flipArray(int[] arr, int s, int e) {
        int mid = (e-s)/2;
        for (int i = 0; i <= mid; i++) {
            int t = arr[s+i];
            arr[s+i] = arr[e-i];
            arr[e-i] = t;
        }
    }

    private int findIndex(int[] arr, int l) {
        int j = -1;
        for (int i = 0; i < l; i++) {
            if (arr[i] == l) {
                j = i;
                break;
            }
        }
        return j;
    }
}
