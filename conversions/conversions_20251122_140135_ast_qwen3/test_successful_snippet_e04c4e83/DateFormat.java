public class DateFormat {
    private static final String[] DAYS_TABLE = {
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };
    
    private static final String[] MONTHS_TABLE = {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };
    
    public static void main(String[] args) {
        // Get current date string (YYYYMMDD format)
        java.time.LocalDate currentDate = java.time.LocalDate.now();
        String currentYear = String.format("%04d", currentDate.getYear());
        String currentMonth = String.format("%02d", currentDate.getMonthValue());
        String currentDay = String.format("%02d", currentDate.getDayOfMonth());
        
        // Display YYYY-MM-DD format
        System.out.println(currentYear + "-" + currentMonth + "-" + currentDay);
        
        // Get day of week (1=Sunday, 2=Monday, ..., 7=Saturday)
        int currentDayOfWeek = currentDate.getDayOfWeek().getValue();
        if (currentDayOfWeek == 7) {
            currentDayOfWeek = 1; // Adjust so Sunday = 1, Monday = 2, etc.
        } else {
            currentDayOfWeek++; // Adjust so Sunday = 1, Monday = 2, etc.
        }
        
        // Display formatted date with day and month names
        String dayName = DAYS_TABLE[currentDayOfWeek - 1];
        String monthName = MONTHS_TABLE[Integer.parseInt(currentMonth) - 1];
        
        System.out.println(dayName + ", " + monthName + " " + currentDay + ", " + currentYear);
    }
}