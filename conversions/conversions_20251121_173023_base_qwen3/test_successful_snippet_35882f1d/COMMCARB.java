import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class COMMCARB {
    private static Scanner scanner = new Scanner(System.in);
    
    // Working storage variables
    private static String wsTodaysDate = "";
    private static String wsTimeHH = "";
    private static String wsTimeMM = "";
    private static String wsTimeSS = "";
    private static String wsEnd = "";
    
    private static int wsUnitsIn = 0;
    private static int wsUnitsNumeric = 0;
    
    private static String wsUnitRateIn = "";
    private static String wsUnitRateDollars = "";
    private static String wsUnitRateDecimal = "";
    private static String wsUnitRateCents = "";
    
    private static int wsNumericUnitDollars = 0;
    private static int wsNumericUnitCents = 0;
    private static double wsNumericUnitWhole = 0.0;
    
    private static double wsCommRate = 0.09;
    
    private static double wsGrossAmount = 0.0;
    private static double wsCommAmount = 0.0;
    private static double wsNetProfit = 0.0;
    
    private static String wsFormattedAmountZ = "";
    private static String wsFormattedAmount = "";
    private static String wsFormattedUnits = "";
    private static String wsFormattedComm = "";
    
    public static void main(String[] args) {
        System.out.println("START COMMCARB BY RYAN BROOKS");
        getDateTime();
        
        // Prompt user for rate until numeric rate entered
        while (!isNumeric(wsUnitRateDollars) || !isNumeric(wsUnitRateCents) || !wsUnitRateDecimal.equals(".")) {
            System.out.println("ENTER THE UNIT PRICE FROM 000.00 TO 999.99 AND PRESS ENTER");
            wsUnitRateIn = scanner.nextLine();
            if (wsUnitRateIn.length() >= 6) {
                wsUnitRateDollars = wsUnitRateIn.substring(0, 3);
                wsUnitRateDecimal = wsUnitRateIn.substring(3, 4);
                wsUnitRateCents = wsUnitRateIn.substring(4, 6);
            }
        }
        
        // Prompt user for units until numeric units entered
        while (!isNumeric(String.valueOf(wsUnitsIn))) {
            System.out.println("ENTER THE UNITS SOLD FROM 000 TO 999 AND PRESS ENTER");
            wsUnitsIn = Integer.parseInt(scanner.nextLine());
        }
        
        // Convert string data entered into numeric field
        wsNumericUnitDollars = Integer.parseInt(wsUnitRateDollars);
        wsNumericUnitCents = Integer.parseInt(wsUnitRateCents);
        wsUnitsNumeric = wsUnitsIn;
        wsNumericUnitWhole = wsNumericUnitDollars + (wsNumericUnitCents / 100.0);
        
        System.out.println("______________________________");
        
        // Format units in and display
        wsFormattedUnits = String.format("%,d", wsUnitsIn);
        System.out.println("UNITS SOLD   : " + wsFormattedUnits);
        
        // Format and display unit price
        wsFormattedAmount = formatCurrency(wsNumericUnitWhole);
        System.out.println("RATE ENTERED : " + wsFormattedAmount);
        
        // Compute, format and display gross sales
        wsGrossAmount = wsUnitsIn * wsNumericUnitWhole;
        wsFormattedAmountZ = formatCurrency(wsGrossAmount);
        System.out.println("GROSS SALES  : " + wsFormattedAmountZ);
        
        // Compute, format and display commission
        wsCommAmount = wsCommRate * wsGrossAmount;
        wsFormattedAmountZ = formatCurrency(wsCommAmount);
        wsFormattedComm = String.format("%.2f", wsCommRate * 100);
        System.out.println("COMMISSION % : " + wsFormattedComm + "%");
        System.out.println("COMMISSION   : " + wsFormattedAmountZ);
        
        // Compute, format and display net profit
        wsNetProfit = wsGrossAmount - wsCommAmount;
        wsFormattedAmount = formatCurrency(wsNetProfit);
        System.out.println("PROFIT AMOUNT: " + wsFormattedAmount);
        
        System.out.println("______________________________");
        System.out.print("PROGRAM ENDS");
        getDateTime();
        System.out.println("PRESS ANY KEY TO EXIT");
        wsEnd = scanner.nextLine();
    }
    
    private static void getDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        
        wsTodaysDate = now.format(dateFormatter);
        wsTimeHH = now.format(timeFormatter).substring(0, 2);
        wsTimeMM = now.format(timeFormatter).substring(3, 5);
        wsTimeSS = now.format(timeFormatter).substring(6, 8);
        
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
        return String.format("$%,.2f", amount);
    }
}