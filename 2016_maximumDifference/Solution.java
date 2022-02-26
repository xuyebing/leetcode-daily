class Solution {
    public int maximumDifference(int[] nums) {
        // 用栈, 入栈条件：比栈顶元素小就不断入栈，一旦比栈顶元素大，就开始计算差值，然后和全局max比较。
        Stack<Integer> stack = new Stack<>();

        int l = nums.length;
        int max = -1;
        for (int i = 0; i < l; i++) {
            if (stack.isEmpty()) {
                stack.push(nums[i]);
            } else {
                int head = stack.peek();
                if (nums[i] <= head) {
                    stack.push(nums[i]);
                } else {
                    int diff = nums[i] - head;
                    if (max < diff) {
                        max = diff;
                    }
                }
            }
        }
        return max;
    }
}
