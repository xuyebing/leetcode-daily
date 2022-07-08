class Solution {
    public int minCostToMoveChips(int[] position) {
        // 思路：2步的移动cost为0，则意味着最终所有的筹码会0 cost的汇集到同一个奇数位 + 相邻的偶数位
        //     最终，就是看 奇数位 和 偶数位 上筹码的个数，将少数的筹码挪到多数的筹码上去。
        //     所以，return 结果也就是少数筹码的个数
        int oddNum = 0;
        int evenNum = 0;

        for (int i = 0; i < position.length; i++) {
            if (position[i] % 2 == 0) {
                evenNum++;
            } else {
                oddNum++;
            }
        }
        int min = evenNum < oddNum ? evenNum : oddNum;

        return min;
    }
}
