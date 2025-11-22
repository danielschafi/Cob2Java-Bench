import java.text.DecimalFormat;

public class XMASNY {
    private static final String[] WEEKDAYS = {
        "Saturday ", "Sunday   ", "Monday   ", "Tuesday  ", "Wednesday", "Thursday ", "Friday   "
    };

    private static class CDATE {
        int D_MONTH;
        int D_DAY;
        int D_YEAR;
    }

    private static class ZELLER_DATA {
        int M;
        int Y;
        int D;
        int D7;
        int J;
        int K;
        String DAY_NAME;
    }

    private static final DecimalFormat TWO_DIGITS = new DecimalFormat("00");
    private static final DecimalFormat FOUR_DIGITS = new DecimalFormat("0000");

    public static void main(String[] args) {
        CDATE cdate = new CDATE();
        cdate.D_MONTH = 12;
        cdate.D_DAY = 25;
        cdate.D_YEAR = 2021;
        showWeekday(cdate);

        cdate.D_MONTH = 1;
        cdate.D_DAY = 1;
        cdate.D_YEAR = 2022;
        showWeekday(cdate);
    }

    private static void showWeekday(CDATE cdate) {
        ZELLER_DATA zellerData = new ZELLER_DATA();
        zellerData.D_MONTH = cdate.D_MONTH;
        zellerData.D_DAY = cdate.D_DAY;
        zellerData.D_YEAR = cdate.D_YEAR;
        zeller(zellerData);
        String outFmt = TWO_DIGITS.format(zellerData.D_MONTH) + "/" +
                        TWO_DIGITS.format(zellerData.D_DAY) + "/" +
                        FOUR_DIGITS.format(zellerData.D_YEAR) + " is a " +
                        zellerData.DAY_NAME;
        System.out.println(outFmt);
    }

    private static void zeller(ZELLER_DATA zellerData) {
        int m = zellerData.D_MONTH;
        int y = zellerData.D_YEAR;
        if (m <= 2) {
            m += 12;
            y -= 1;
        }
        int j = y / 100;
        int k = y - j * 100;
        int d = (m + 1) * 26 / 10;
        d += k;
        d += k / 4;
        d += 5 * j;
        d += j / 4;
        d += zellerData.D_DAY;
        d /= 7;
        d = d * 7;
        d = zellerData.D_DAY - d + 1;
        zellerData.DAY_NAME = WEEKDAYS[d % 7];
    }
}