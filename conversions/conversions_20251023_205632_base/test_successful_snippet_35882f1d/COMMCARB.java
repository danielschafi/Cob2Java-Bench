import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class COMMCARB {
    public static void main(String[] args) {
        String wsTodaysDate;
        String wsTimeHh;
        String wsTimeMm;
        String wsTimeSs;
        String wsEnd;

        String wsUnitsIn;
        int wsUnitsNumeric;

        String wsUnitRateIn;
        String wsUnitRateDollars;
        String wsUnitRateDecimal;
        String wsUnitRateCents;

        int wsNumericUnitDollars;
        int wsNumericUnitCents;
        double wsNumericUnitWhole;

        double wsCommRate = 0.09;

        double wsGrossAmount = 0;
        double wsCommAmount = 0;
        double wsNetProfit = 0;

        String wsFormattedAmountZ;
        String wsFormattedAmount;
        String wsFormattedUnits;
        String wsFormattedComm;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        DecimalFormat decimalFormat = new DecimalFormat("$#,###,###.00");
        DecimalFormat unitFormat = new DecimalFormat("###,###,###");
        DecimalFormat commFormat = new DecimalFormat("0.00");

        Scanner scanner = new Scanner(System.in);

        System.out.println("START COMMCARB BY RYAN BROOKS");
        wsTodaysDate = dateFormat.format(new Date());
        wsTimeHh = timeFormat.format(new Date()).substring(0, 2);
        wsTimeMm = timeFormat.format(new Date()).substring(3, 5);
        wsTimeSs = timeFormat.format(new Date()).substring(6, 8);
        System.out.println(" ON " + wsTodaysDate + " AT " + wsTimeHh + ":" + wsTimeMm + ":" + wsTimeSs);

        do {
            System.out.println("ENTER THE UNIT PRICE FROM 000.00 TO 999.99 AND PRESS ENTER");
            wsUnitRateIn = scanner.nextLine();
            wsUnitRateDollars = wsUnitRateIn.substring(0, 3);
            wsUnitRateDecimal = wsUnitRateIn.substring(3, 4);
            wsUnitRateCents = wsUnitRateIn.substring(4, 6);
        } while (!isNumeric(wsUnitRateDollars) || !isNumeric(wsUnitRateCents) || !wsUnitRateDecimal.equals("."));

        do {
            System.out.println("ENTER THE UNITS SOLD FROM 000 TO 999 AND PRESS ENTER");
            wsUnitsIn = scanner.nextLine();
        } while (!isNumeric(wsUnitsIn));

        wsNumericUnitDollars = Integer.parseInt(wsUnitRateDollars);
        wsNumericUnitCents = Integer.parseInt(wsUnitRateCents);
        wsUnitsNumeric = Integer.parseInt(wsUnitsIn);
        wsNumericUnitWhole = wsNumericUnitDollars + wsNumericUnitCents * 0.01;

        System.out.println("______________________________");

        wsFormattedUnits = unitFormat.format(wsUnitsNumeric);
        System.out.println("UNITS SOLD   : " + wsFormattedUnits);

        wsFormattedAmount = decimalFormat.format(wsNumericUnitWhole);
        System.out.println("RATE ENTERED : " + wsFormattedAmount);

        wsGrossAmount = wsUnitsNumeric * wsNumericUnitWhole;
        wsFormattedAmountZ = decimalFormat.format(wsGrossAmount);
        System.out.println("GROSS SALES  : " + wsFormattedAmountZ);

        wsCommAmount = wsCommRate * wsGrossAmount;
        wsFormattedAmountZ = decimalFormat.format(wsCommAmount);
        wsFormattedComm = commFormat.format(wsCommRate * 100);
        System.out.println("COMMISSION % : " + wsFormattedComm + "%");
        System.out.println("COMMISSION   : " + wsFormattedAmountZ);

        wsNetProfit = wsGrossAmount - wsCommAmount;
        wsFormattedAmount = decimalFormat.format(wsNetProfit);
        System.out.println("PROFIT AMOUNT: " + wsFormattedAmount);

        System.out.println("______________________________");
        System.out.print("PROGRAM ENDS");
        wsTodaysDate = dateFormat.format(new Date());
        wsTimeHh = timeFormat.format(new Date()).substring(0, 2);
        wsTimeMm = timeFormat.format(new Date()).substring(3, 5);
        wsTimeSs = timeFormat.format(new Date()).substring(6, 8);
        System.out.println(" ON " + wsTodaysDate + " AT " + wsTimeHh + ":" + wsTimeMm + ":" + wsTimeSs);

        System.out.println("PRESS ANY KEY TO EXIT");
        wsEnd = scanner.nextLine();
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}