import java.text.DecimalFormat;

public class DowTest {
    public static void main(String[] args) {
        String wsInpDate = "00001225";
        int wsDow;
        for (int wsInpYear = 2008; wsInpYear <= 2121; wsInpYear++) {
            wsInpDate = String.format("%04d%02d%02d", wsInpYear, 12, 25);
            wsDow = toDow(wsInpDate);
            if (wsDow == 1) {
                System.out.println("year=" + wsInpYear);
            }
        }
    }

    public static int toDow(String lkipDate) {
        int lkipDateYear = Integer.parseInt(lkipDate.substring(0, 4));
        int lkipDateMonth = Integer.parseInt(lkipDate.substring(4, 6));
        int lkipDateDay = Integer.parseInt(lkipDate.substring(6, 8));

        int wmsMonth;
        int wmsYear;
        if (lkipDateMonth < 3) {
            wmsMonth = lkipDateMonth + 12;
            wmsYear = lkipDateYear - 1;
        } else {
            wmsMonth = lkipDateMonth;
            wmsYear = lkipDateYear;
        }

        int wmsSum = lkipDateDay + 2 * wmsMonth + wmsYear + (6 * (wmsMonth + 1)) / 10 + wmsYear / 4 - wmsYear / 100 + wmsYear / 400 + 1;
        return wmsSum % 7 + 1;
    }
}