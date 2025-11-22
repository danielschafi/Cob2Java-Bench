public class XMASNY {
    static class Weekdays {
        static final String[] DAYS = {
            "Saturday ",
            "Sunday   ",
            "Monday   ",
            "Tuesday  ",
            "Wednesday",
            "Thursday ",
            "Friday   "
        };
    }
    
    static class Variables {
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
    
    public static void main(String[] args) {
        Variables vars = new Variables();
        
        setDate(vars, "25122021");
        showWeekday(vars);
        
        setDate(vars, "01012022");
        showWeekday(vars);
    }
    
    static void setDate(Variables vars, String dateStr) {
        vars.dMonth = Integer.parseInt(dateStr.substring(0, 2));
        vars.dDay = Integer.parseInt(dateStr.substring(2, 4));
        vars.dYear = Integer.parseInt(dateStr.substring(4, 8));
    }
    
    static void showWeekday(Variables vars) {
        zeller(vars);
        String outFmt = String.format("%02d/%02d/%04d is a %s",
            vars.dMonth, vars.dDay, vars.dYear, vars.dayName);
        System.out.println(outFmt);
    }
    
    static void zeller(Variables vars) {
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
        
        int kDiv4 = vars.k / 4;
        vars.d += kDiv4;
        
        vars.d = vars.d + 5 * vars.j;
        
        int jDiv4 = vars.j / 4;
        vars.d = vars.d + jDiv4 + vars.dDay;
        
        vars.d7 = vars.d / 7;
        vars.d = vars.d - (7 * vars.d7) + 1;
        
        vars.dayName = Weekdays.DAYS[vars.d - 1];
    }
}