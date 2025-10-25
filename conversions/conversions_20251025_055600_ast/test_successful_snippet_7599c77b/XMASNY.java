import java.text.DecimalFormat;

public class XMASNY {
    private static final String[] WEEKDAYS = {
        "Saturday ", "Sunday   ", "Monday   ", "Tuesday  ", "Wednesday", "Thursday ", "Friday   "
    };

    private static class OutFmt {
        private String outMonth;
        private String outDay;
        private String outYear;
        private String outDayName;

        public OutFmt() {
            this.outMonth = "00";
            this.outDay = "00";
            this.outYear = "0000";
            this.outDayName = "         ";
        }

        @Override
        public String toString() {
            DecimalFormat df = new DecimalFormat("00");
            return df.format(Integer.parseInt(outMonth)) + "/" +
                   df.format(Integer.parseInt(outDay)) + "/" +
                   outYear + " is a " + outDayName;
        }
    }

    private static class Variables {
        private String cDate;
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

        public Variables() {
            this.cDate = "00000000";
            this.dMonth = 0;
            this.dDay = 0;
            this.dYear = 0;
            this.m = 0;
            this.y = 0;
            this.d = 0;
            this.d7 = 0;
            this.j = 0;
            this.k = 0;
            this.dayName = "         ";
        }
    }

    public static void main(String[] args) {
        Variables variables = new Variables();
        OutFmt outFmt = new OutFmt();

        variables.cDate = "25122021";
        showWeekday(variables, outFmt);
        variables.cDate = "01012022";
        showWeekday(variables, outFmt);
    }

    private static void showWeekday(Variables variables, OutFmt outFmt) {
        variables.dMonth = Integer.parseInt(variables.cDate.substring(2, 4));
        variables.dDay = Integer.parseInt(variables.cDate.substring(0, 2));
        variables.dYear = Integer.parseInt(variables.cDate.substring(4, 8));
        zeller(variables);
        outFmt.outMonth = String.valueOf(variables.dMonth);
        outFmt.outDay = String.valueOf(variables.dDay);
        outFmt.outYear = String.valueOf(variables.dYear);
        outFmt.outDayName = variables.dayName;
        System.out.println(outFmt);
    }

    private static void zeller(Variables variables) {
        variables.m = variables.dMonth;
        variables.y = variables.dYear;
        if (variables.m <= 2) {
            variables.m += 12;
            variables.y -= 1;
        }
        variables.j = variables.y / 100;
        variables.k = variables.y - variables.j * 100;
        variables.d = (variables.m + 1) * 26 / 10;
        variables.d += variables.k;
        variables.d += variables.k / 4;
        variables.d += 5 * variables.j;
        variables.d += variables.j / 4;
        variables.d += variables.dDay;
        variables.d7 = variables.d / 7;
        variables.d = variables.d - variables.d7 * 7 + 1;
        variables.dayName = WEEKDAYS[variables.d - 1];
    }
}