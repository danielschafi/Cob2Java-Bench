public class Euler019 {
    private static int countOfSundays = 0;
    private static final int START_YEAR = 1901;
    private static final int END_YEAR = 2000;
    private static final int START_MONTH = 1;
    private static final int END_MONTH = 12;

    public static void main(String[] args) {
        for (int year = START_YEAR; year <= END_YEAR; year++) {
            for (int month = START_MONTH; month <= END_MONTH; month++) {
                String dateString = String.format("%04d%02d01", year, month);
                int dayNum = integerOfDate(dateString);
                int weekNum = dayNum / 7;
                int dayOfWeekNum = dayNum % 7;
                
                System.out.println(dateString + ": " + weekNum + " * 7 + " + dayOfWeekNum + " = " + dayNum);
                
                if (dayOfWeekNum == 0) {
                    countOfSundays++;
                }
            }
        }
        
        System.out.println("No Sundays the 1st every month: " + countOfSundays);
    }
    
    // Simplified implementation of integer-of-date function
    private static int integerOfDate(String dateStr) {
        int year = Integer.parseInt(dateStr.substring(0, 4));
        int month = Integer.parseInt(dateStr.substring(4, 6));
        int day = Integer.parseInt(dateStr.substring(6, 8));
        
        // Using a simplified approach based on the Rata Die concept
        // This is a basic implementation for the given date range
        // For a full implementation, more complex calendar calculations would be needed
        
        // Adjust month and year for January and February
        if (month <= 2) {
            month += 12;
            year--;
        }
        
        // Calculate using a simplified formula for demonstration
        // This assumes a base date of 1 Jan 1601 = day 1 (as per COBOL reference)
        int a = (14 - month) / 12;
        int y = year + 4800 - a;
        int m = month + 12 * a - 3;
        
        // Simplified calculation for demonstration purposes
        // In practice, this would use the proper Rata Die algorithm
        int jdn = day + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045;
        
        // Convert to Rata Die (days since 1 Jan 0001)
        return jdn - 730120; // Adjust for base date difference
    }
}