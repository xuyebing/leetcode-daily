class Solution {

    Stack<Integer> stack = new Stack<Integer>();
    int k = 0;
    int count = 0;
    boolean flag = false;
    public String getPermutation(int n, int k) {
        this.k = k;

        List<Integer> vlist = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++)
            vlist.add(i);

        getPermutationCore(vlist);

        StringBuilder sb = new StringBuilder();
        for (int a : stack) {
            sb.append(""+a);
        }
        return sb.toString();
    }

    private void getPermutationCore(List<Integer> vlist) {
        if (flag) {
            return;
        }
        if (vlist.size()==1) {
            stack.push(vlist.get(0));
            count++;
            if (count == k) {
                flag = true;
                return;
            }
            stack.pop();
        } else {
            int l = vlist.size();
            for (int i = 0; i < l; i++) {
                Integer value = vlist.get(i);
                stack.push(value);
                List<Integer> tlist = new ArrayList<Integer>(vlist);
                tlist.remove(value);
                getPermutationCore(tlist);
                if (flag) {
                    return;
                }
                stack.pop();
            }
        }
    } 
}
