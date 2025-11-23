public class XMASNY {
    private static final String[] DAYS = {
        "Saturday ", "Sunday   ", "Monday   ", "Tuesday  ", 
        "Wednesday", "Thursday ", "Friday   "
    };
    
    private static class Variables {
        private int dMonth;
        private int dDay;
        private int dYear;
        private int m;
        private int y;
        private int d;
        private int d7;
        private int j;
        private int k;
        private String dayName;
    }
    
    private static class OutFmt {
        private int outMonth;
        private int outDay;
        private int outYear;
        private String outDayname;
        
        @Override
        public String toString() {
            return String.format("%02d/%02d/%04d is a %s", 
                               outMonth, outDay, outYear, outDayname);
        }
    }
    
    public static void main(String[] args) {
        Variables vars = new Variables();
        OutFmt outFmt = new OutFmt();
        
        // Test case 1: 25/12/2021
        vars.dMonth = 12;
        vars.dDay = 25;
        vars.dYear = 2021;
        showWeekday(vars, outFmt);
        
        // Test case 2: 01/01/2022
        vars.dMonth = 1;
        vars.dDay = 1;
        vars.dYear = 2022;
        showWeekday(vars, outFmt);
    }
    
    private static void showWeekday(Variables vars, OutFmt outFmt) {
        outFmt.outMonth = vars.dMonth;
        outFmt.outDay = vars.dDay;
        outFmt.outYear = vars.dYear;
        zeller(vars);
        outFmt.outDayname = vars.dayName;
        System.out.println(outFmt);
    }
    
    private static void zeller(Variables vars) {
        vars.m = vars.dMonth;
        vars.y = vars.dYear;
        
        if (vars.m <= 2) {
            vars.m += 12;
            vars.y -= 1;
        }
        
        vars.j = vars.y / 100;
        vars.k = vars.y - vars.j * 100;
        vars.d = (vars.m + 1) * 26;
        vars.d /= 10;
        vars.d += vars.k;
        vars.k /= 4;
        vars.d += vars.k;
        vars.d += 5 * vars.j;
        vars.j /= 4;
        vars.d += vars.j + vars.dDay;
        vars.d7 = vars.d / 7;
        vars.d = vars.d - vars.d7 * 7 + 1;
        vars.dayName = DAYS[vars.d - 1];
    }
}