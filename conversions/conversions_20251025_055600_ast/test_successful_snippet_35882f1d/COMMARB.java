import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class COMMARB {
    public static void main(String[] args) {
        String wsTodaysDate;
        String wsTimeHH, wsTimeMM, wsTimeSS;
        String wsEnd;

        int wsUnitsIn, wsUnitsNumeric;
        String wsUnitRateIn;
        String wsUnitRateDollars, wsUnitRateDecimal, wsUnitRateCents;
        int wsNumericUnitDollars, wsNumericUnitCents;
        double wsNumericUnitWhole;
        double wsCommRate = 0.09;
        double wsGrossAmount = 0;
        double wsCommAmount = 0;
        double wsNetProfit = 0;
        DecimalFormat wsFormattedAmountZ = new DecimalFormat("$###,###,###.00");
        DecimalFormat wsFormattedAmount = new DecimalFormat("$###,###,###.00");
        DecimalFormat wsFormattedUnits = new DecimalFormat("###,###,###,###");
        DecimalFormat wsFormattedComm = new DecimalFormat("0.00");

        Scanner scanner = new Scanner(System.in);

        System.out.println("START COMMCARB BY RYAN BROOKS");
        getDateAndTime();

        while (true) {
            System.out.println("ENTER THE UNIT PRICE FROM 000.00 TO 999.99 AND PRESS ENTER");
            wsUnitRateIn = scanner.nextLine();
            wsUnitRateDollars = wsUnitRateIn.substring(0, 3);
            wsUnitRateDecimal = wsUnitRateIn.substring(3, 4);
            wsUnitRateCents = wsUnitRateIn.substring(4, 6);
            if (isNumeric(wsUnitRateDollars) && isNumeric(wsUnitRateCents) && wsUnitRateDecimal.equals(".")) {
                break;
            }
        }

        while (true) {
            System.out.println("ENTER THE UNITS SOLD FROM 000 TO 999 AND PRESS ENTER");
            wsUnitsIn = Integer.parseInt(scanner.nextLine());
            if (wsUnitsIn >= 0 && wsUnitsIn <= 999) {
                break;
            }
        }

        wsNumericUnitDollars = Integer.parseInt(wsUnitRateDollars);
        wsNumericUnitCents = Integer.parseInt(wsUnitRateCents);
        wsNumericUnitWhole = wsNumericUnitDollars + wsNumericUnitCents / 100.0;
        wsUnitsNumeric = wsUnitsIn;

        System.out.println("______________________________");

        System.out.println("UNITS SOLD   : " + wsFormattedUnits.format(wsUnitsNumeric));

        System.out.println("RATE ENTERED : " + wsFormattedAmount.format(wsNumericUnitWhole));

        wsGrossAmount = wsUnitsNumeric * wsNumericUnitWhole;
        System.out.println("GROSS SALES  : " + wsFormattedAmountZ.format(wsGrossAmount));

        wsCommAmount = wsCommRate * wsGrossAmount;
        System.out.println("COMMISSION % : " + wsFormattedComm.format(wsCommRate * 100) + "%");
        System.out.println("COMMISSION   : " + wsFormattedAmountZ.format(wsCommAmount));

        wsNetProfit = wsGrossAmount - wsCommAmount;
        System.out.println("PROFIT AMOUNT: " + wsFormattedAmount.format(wsNetProfit));

        System.out.println("______________________________");
        System.out.print("PROGRAM ENDS");
        getDateAndTime();

        System.out.println("PRESS ANY KEY TO EXIT");
        wsEnd = scanner.nextLine();
    }

    private static void getDateAndTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String wsTodaysDate = dateFormat.format(new Date());
        String wsTime = timeFormat.format(new Date());
        String wsTimeHH = wsTime.substring(0, 2);
        String wsTimeMM = wsTime.substring(3, 5);
        String wsTimeSS = wsTime.substring(6, 8);

        System.out.println(" ON " + wsTodaysDate + " AT " + wsTimeHH + ":" + wsTimeMM + ":" + wsTimeSS);
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