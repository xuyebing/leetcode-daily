class Solution {

    public static String[] days = new String[] {"Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
    
    public static int[] daysInMonth = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public String dayOfTheWeek(int day, int month, int year) {
        // 1971/1/1 was Friday

        int sumDays = 0;
        for (int i = 1971; i < year; i++) {
            if(isLeapYear(i)) {
                sumDays += 366;
            } else {
                sumDays += 365;
            }
        }
        
        for (int i = 0; i < month-1; i++) {
            sumDays += daysInMonth[i];
        }
        if (isLeapYear(year) && month > 2) {
            sumDays += 1;
        }
        sumDays += day-1;

        int mod = sumDays % 7;
        return days[mod];
    }

    private boolean isLeapYear(int year) {
        boolean v1 = (year%4==0) && (year%100!=0);
        boolean v2 = year%400==0;

        return v1 || v2;
    }
}
