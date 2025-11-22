public class XMASNY {
    
    private static final String[] DAYS = {
        "Saturday ",
        "Sunday   ",
        "Monday   ",
        "Tuesday  ",
        "Wednesday",
        "Thursday ",
        "Friday   "
    };
    
    private static class Variables {
        int dMonth;
        int dDay;
        int dYear;
        int m;
        int y;
        int d;
        int d7;
        int j;
        int k;
        String dayName;
    }
    
    private Variables vars = new Variables();
    
    public static void main(String[] args) {
        XMASNY program = new XMASNY();
        program.begin();
    }
    
    private void begin() {
        setCDate("25122021");
        showWeekday();
        setCDate("01012022");
        showWeekday();
    }
    
    private void setCDate(String date) {
        vars.dMonth = Integer.parseInt(date.substring(0, 2));
        vars.dDay = Integer.parseInt(date.substring(2, 4));
        vars.dYear = Integer.parseInt(date.substring(4, 8));
    }
    
    private void showWeekday() {
        int outMonth = vars.dMonth;
        int outDay = vars.dDay;
        int outYear = vars.dYear;
        zeller();
        String outDayname = vars.dayName;
        
        String output = String.format("%02d/%02d/%04d is a %s",
            outMonth, outDay, outYear, outDayname);
        System.out.println(output);
    }
    
    private void zeller() {
        vars.m = vars.dMonth;
        vars.y = vars.dYear;
        
        if (vars.m <= 2) {
            vars.m += 12;
            vars.y -= 1;
        }
        
        vars.j = vars.y / 100;
        vars.k = vars.y - vars.j * 100;
        
        vars.d = (vars.m + 1) * 26;
        vars.d = vars.d / 10;
        vars.d += vars.k;
        vars.k = vars.k / 4;
        vars.d += vars.k;
        vars.d = vars.d + 5 * vars.j;
        vars.j = vars.j / 4;
        vars.d = vars.d + vars.j + vars.dDay;
        
        vars.d7 = vars.d / 7;
        vars.d7 = vars.d7 * 7;
        vars.d = vars.d - vars.d7 + 1;
        
        vars.dayName = DAYS[vars.d - 1];
    }
}