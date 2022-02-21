class Solution {
    public String pushDominoes(String dominoes) {
        // 使用一个栈：
        // 1. 如果遇到L：
        //    （1） 若栈空，则将从0 到 L下标位置的所有元素标记成L （L不入栈）
        //    （2） 若栈不空（栈里只会有R的索引），则从R开始到L截止，同时从开头和结尾往中间去对称更新R和L，直到相遇。即，对称轴左侧的全部为R，对称轴右侧的全部为L
        // 2. 如果遇到R：
        //    （1）若栈空，则将R的下标入栈
        //    （2）若栈不空（栈顶是上一个R的索引），则从栈顶R的位置到当前R的位置，全部标记成R，栈顶R出栈，然后将当前R的下标入栈
        // 遍历完成后，若栈不空，则将栈顶（栈里只能是R）下标到dominoes最后位置的元素全部标记成R
        // Time Complexity: O(n), Space Complexity: O(1) // 栈里最多只保存一个元素
        int l = dominoes.length();

        Stack<Integer> stack = new Stack<Integer>();
        char[] resChars = new char[l];
        int lastLIndex = -1;

        for (int i = 0 ; i < l; i++) {
            char c = dominoes.charAt(i);
            if (c == 'L') {
                if (stack.isEmpty()) {
                    int j = 0;
                    if (lastLIndex != -1) {
                        j = lastLIndex+1;
                    }
                    for (; j <= i; j++) {
                        resChars[j] = 'L';
                    }
                } else {
                    int rIndex = stack.pop();
                    int diff = i - rIndex;
                    int mid = diff/2;
                    if (diff % 2 == 0) {
                        for (int j = 0; j < mid; j++) {
                            resChars[rIndex+j] = 'R';
                        }
                        resChars[rIndex + mid] = '.';
                        for (int j = 0; j < mid; j++) {
                            resChars[i-j] = 'L';
                        }
                    } else {
                        for (int j = 0; j <= mid; j++) {
                            resChars[rIndex + j] = 'R';
                        }
                        for (int j = 0; j <= mid; j++) {
                            resChars[i - j] = 'L';
                        }
                    }
                }
                lastLIndex = i;
            } else if (c == 'R') {
                if (stack.isEmpty()) {
                    stack.push(i);
                    continue;
                } else {
                    int rIndex = stack.pop();
                    for (int j = rIndex; j < i; j++) {
                        resChars[j] = 'R';
                    }
                    stack.push(i);
                }
            } else { // char == '.'
                if (stack.isEmpty()) {
                    resChars[i] = '.';
                }
            }
        }
        if (!stack.isEmpty()) {
            int rIndex = stack.pop();
            for (int i = rIndex; i < l; i++) {
                resChars[i] = 'R';
            }
        }

        // Convert char array to String
        return new String(resChars);
    }
}
