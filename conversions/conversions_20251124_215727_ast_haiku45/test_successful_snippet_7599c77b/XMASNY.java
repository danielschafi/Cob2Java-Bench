public class XMASNY {
    private static String[] days = {
        "Saturday ",
        "Sunday   ",
        "Monday   ",
        "Tuesday  ",
        "Wednesday",
        "Thursday ",
        "Friday   "
    };
    
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
    
    private static int outMonth;
    private static int outDay;
    private static int outYear;
    private static String outDayName;
    
    public static void main(String[] args) {
        begin();
    }
    
    private static void begin() {
        String cdate = "25122021";
        parseCDate(cdate);
        showWeekday();
        
        cdate = "01012022";
        parseCDate(cdate);
        showWeekday();
    }
    
    private static void parseCDate(String cdate) {
        dMonth = Integer.parseInt(cdate.substring(0, 2));
        dDay = Integer.parseInt(cdate.substring(2, 4));
        dYear = Integer.parseInt(cdate.substring(4, 8));
    }
    
    private static void showWeekday() {
        outMonth = dMonth;
        outDay = dDay;
        outYear = dYear;
        zeller();
        outDayName = dayName;
        displayOutput();
    }
    
    private static void zeller() {
        m = dMonth;
        y = dYear;
        
        if (m <= 2) {
            m += 12;
            y -= 1;
        }
        
        j = y / 100;
        k = y - j * 100;
        
        d = (m + 1) * 26;
        d = d / 10;
        d = d + k;
        
        k = k / 4;
        d = d + k;
        
        d = d + 5 * j;
        
        j = j / 4;
        d = d + j + dDay;
        
        d7 = d / 7;
        d7 = 7 * d7;
        d = d - d7 + 1;
        
        dayName = days[d - 1];
    }
    
    private static void displayOutput() {
        String month = String.format("%02d", outMonth);
        String day = String.format("%02d", outDay);
        String year = String.format("%04d", outYear);
        System.out.println(month + "/" + day + "/" + year + " is a " + outDayName);
    }
}