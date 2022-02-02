// 这个版本内存超过限制
class Solution {
    int mod = 1000000007;
    public int nthMagicalNumber(int n, int a, int b) {
        // 使用大顶堆，size=n
        // 1. 先用a 的1到n倍数来初始化堆
        // 2. 然后遍历b的1到n倍数，当b的k倍数小于堆顶时，将k*b 塞入堆；否则，停止遍历。
        // 3. 将堆顶元素返回。
        TreeSet<Long> treeSet = new TreeSet<Long>();
        for (int i = 1; i <= n; i++) {
            long v = ((long)i) * a;
            treeSet.add(v);
        }

        for (int i = 1; i <= n; i++) {
            long lastKey = treeSet.last();
            long v = ((long)i) * b;
            if (v >= lastKey) {
                break;
            } else {
                if (!treeSet.contains(v)) {
                    treeSet.remove(lastKey);
                    treeSet.add(v);
                }
            }
        }
        long res = treeSet.last() % mod;

        return (int) res;
    }
}
