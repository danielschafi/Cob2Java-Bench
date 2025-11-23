import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class COMMCARB {
    private static String wsTodaysDate;
    private static String wsTimeHH;
    private static String wsTimeMM;
    private static String wsTimeSS;
    private static String wsEnd;
    private static String wsUnitsIn;
    private static String wsUnitsNumeric;
    private static String wsUnitRateIn;
    private static String wsUnitRateDollars;
    private static String wsUnitRateDecimal;
    private static String wsUnitRateCents;
    private static String wsNumericUnitParts;
    private static String wsNumericUnitDollars;
    private static String wsNumericUnitCents;
    private static double wsNumericUnitWhole;
    private static final double wsCommRate = 0.09;
    private static double wsGrossAmount = 0.0;
    private static double wsCommAmount = 0.0;
    private static double wsNetProfit = 0.0;
    private static String wsFormattedAmountZ;
    private static String wsFormattedAmount;
    private static String wsFormattedUnits;
    private static String wsFormattedComm;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        System.out.println("START COMMCARB BY RYAN BROOKS");
        getDateTime();

        // Prompt the user for rate until numeric rate entered:
        while (!isNumeric(wsUnitRateDollars) || !isNumeric(wsUnitRateCents) || !wsUnitRateDecimal.equals(".")) {
            System.out.println("ENTER THE UNIT PRICE FROM 000.00 TO 999.99 AND PRESS ENTER");
            wsUnitRateIn = reader.readLine();
            wsUnitRateDollars = wsUnitRateIn.substring(0, 3);
            wsUnitRateDecimal = wsUnitRateIn.substring(3, 4);
            wsUnitRateCents = wsUnitRateIn.substring(4, 6);
        }

        // Prompt the user for units until numeric units entered:
        while (!isNumeric(wsUnitsIn)) {
            System.out.println("ENTER THE UNITS SOLD FROM 000 TO 999 AND PRESS ENTER");
            wsUnitsIn = reader.readLine();
        }

        // Convert the string data entered into numeric field:
        wsNumericUnitDollars = wsUnitRateDollars;
        wsNumericUnitCents = wsUnitRateCents;
        wsUnitsNumeric = wsUnitsIn;
        wsNumericUnitWhole = Double.parseDouble(wsNumericUnitDollars + "." + wsNumericUnitCents);

        System.out.println("______________________________");

        // Format units in and display:
        wsFormattedUnits = wsUnitsIn;
        System.out.println("UNITS SOLD   : " + wsFormattedUnits);

        // Format and display unit price:
        wsFormattedAmount = formatCurrency(wsNumericUnitWhole);
        System.out.println("RATE ENTERED : " + wsFormattedAmount);

        // Compute, format and display gross sales:
        wsGrossAmount = Integer.parseInt(wsUnitsIn) * wsNumericUnitWhole;
        wsFormattedAmountZ = formatCurrency(wsGrossAmount);
        System.out.println("GROSS SALES  : " + wsFormattedAmountZ);

        // Compute, format and display commission:
        wsCommAmount = wsCommRate * wsGrossAmount;
        wsFormattedAmountZ = formatCurrency(wsCommAmount);
        wsFormattedComm = String.format("%.2f", wsCommRate * 100);
        System.out.println("COMMISSION % : " + wsFormattedComm + "%");
        System.out.println("COMMISSION   : " + wsFormattedAmountZ);

        // Compute, format and display net profit:
        wsNetProfit = wsGrossAmount - wsCommAmount;
        wsFormattedAmount = formatCurrency(wsNetProfit);
        System.out.println("PROFIT AMOUNT: " + wsFormattedAmount);

        System.out.println("______________________________");
        System.out.print("PROGRAM ENDS");
        getDateTime();
        System.out.println("PRESS ANY KEY TO EXIT");
        wsEnd = reader.readLine();
    }

    private static void getDateTime() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        wsTodaysDate = String.format("%04d/%02d/%02d", date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        wsTimeHH = String.format("%02d", time.getHour());
        wsTimeMM = String.format("%02d", time.getMinute());
        wsTimeSS = String.format("%02d", time.getSecond());
        System.out.println(" ON " + wsTodaysDate + " AT " + wsTimeHH + ":" + wsTimeMM + ":" + wsTimeSS);
    }

    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String formatCurrency(double amount) {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        return df.format(amount);
    }
}