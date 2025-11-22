import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormat {
    private static final String[] DAYS = {
        "", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };
    
    private static final String[] MONTHS = {
        "", "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");
        
        String currentYear = date.format(yearFormatter);
        String currentMonth = date.format(monthFormatter);
        String currentDay = date.format(dayFormatter);
        
        System.out.println(currentYear + "-" + currentMonth + "-" + currentDay);
        
        int dayOfWeek = date.getDayOfWeek().getValue();
        int monthValue = date.getMonthValue();
        
        System.out.println(DAYS[dayOfWeek] + ", " + 
                          MONTHS[monthValue] + " " + 
                          currentDay + ", " + 
                          currentYear);
    }
}