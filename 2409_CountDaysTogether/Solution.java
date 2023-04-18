class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        if (leaveAlice.compareTo(arriveBob) < 0 || leaveBob.compareTo(arriveAlice) < 0) {
            return 0;
        }
        if (arriveAlice.compareTo(arriveBob) < 0) {
            if (leaveBob.compareTo(leaveAlice) < 0) {
                return compute(arriveBob, leaveBob);
            } else {
                return compute(arriveBob, leaveAlice);
            }
        } else {
            if (leaveAlice.compareTo(leaveBob) < 0) {
                return compute(arriveAlice, leaveAlice);
            } else {
                return compute(arriveAlice, leaveBob);
            }
        }
    }
    private int compute(String startDate, String endDate) {
        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] starts = startDate.split("-");
        String[] ends = endDate.split("-");
        int startMonth = Integer.valueOf(starts[0]);
        int startDay = Integer.valueOf(starts[1]);
        int endMonth = Integer.valueOf(ends[0]);
        int endDay = Integer.valueOf(ends[1]);

        int result = 0;
        if (startMonth == endMonth) {
            result = endDay - startDay + 1;
        } else {
            for (int i = startMonth+1; i < endMonth; i++) {
                result += months[i-1];
            }
            result += months[startMonth-1] - startDay + 1; // common days in the start month.
            result += endDay; // common days in the end month.
        }
        return result;
    }
}
