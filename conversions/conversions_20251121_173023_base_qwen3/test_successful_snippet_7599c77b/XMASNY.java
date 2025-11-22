import java.util.Scanner;

public class XMASNY {
    private static String[] dayNames = {
        "Saturday ", "Sunday   ", "Monday   ", "Tuesday  ", 
        "Wednesday", "Thursday ", "Friday   "
    };
    
    private static int outMonth;
    private static int outDay;
    private static int outYear;
    private static String outDayname;
    private static String outFmt;
    
    private static int dMonth;
    private static int dDay;
    private static int dYear;
    private static int m;
    private static int y;
    private static int d;
    private static int d7;
    private static int j;
    private static int k;
    private static String dayName;
    
    public static void main(String[] args) {
        moveStringToCdate("25122021");
        showWeekday();
        moveStringToCdate("01012022");
        showWeekday();
    }
    
    private static void moveStringToCdate(String dateString) {
        dMonth = Integer.parseInt(dateString.substring(0, 2));
        dDay = Integer.parseInt(dateString.substring(2, 4));
        dYear = Integer.parseInt(dateString.substring(4));
    }
    
    private static void showWeekday() {
        outMonth = dMonth;
        outDay = dDay;
        outYear = dYear;
        zeller();
        outDayname = dayName;
        outFmt = String.format("%02d/%02d/%04d is a %s", outMonth, outDay, outYear, outDayname);
        System.out.println(outFmt);
    }
    
    private static void zeller() {
        m = dMonth;
        y = dYear;
        
        if (m <= 2) {
            m += 12;
            y -= 1;
        }
        
        j = y / 100;
        k = y - (j * 100);
        d = ((m + 1) * 26) / 10;
        d += k;
        k /= 4;
        d += k;
        d += 5 * j;
        j /= 4;
        d += j + dDay;
        d7 = d / 7;
        d = d - (d7 * 7) + 1;
        dayName = dayNames[d - 1];
    }
}