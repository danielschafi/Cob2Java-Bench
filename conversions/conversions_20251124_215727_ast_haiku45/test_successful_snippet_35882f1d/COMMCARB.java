import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

public class COMMCARB {
    private static Scanner scanner = new Scanner(System.in);
    
    private static String wsToday = "";
    private static String wsTimeHH = "";
    private static String wsTimeMM = "";
    private static String wsTimeSS = "";
    private static String wsEnd = "";
    
    private static String wsUnitsIn = "";
    private static int wsUnitsNumeric = 0;
    
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
        mainline();
    }
    
    public static void mainline() {
        System.out.println("START COMMCARB BY RYAN BROOKS");
        getDateTime();
        
        boolean validRate = false;
        while (!validRate) {
            System.out.println("ENTER THE UNIT PRICE FROM 000.00 TO 999.99 AND PRESS ENTER");
            String input = scanner.nextLine();
            if (input.length() == 6 && input.charAt(3) == '.' && 
                isNumeric(input.substring(0, 3)) && isNumeric(input.substring(4, 6))) {
                wsUnitRateDollars = input.substring(0, 3);
                wsUnitRateDecimal = ".";
                wsUnitRateCents = input.substring(4, 6);
                validRate = true;
            }
        }
        
        boolean validUnits = false;
        while (!validUnits) {
            System.out.println("ENTER THE UNITS SOLD FROM 000 TO 999 AND PRESS ENTER");
            String input = scanner.nextLine();
            if (isNumeric(input) && input.length() <= 3) {
                wsUnitsIn = input;
                validUnits = true;
            }
        }
        
        wsNumericUnitDollars = Integer.parseInt(wsUnitRateDollars);
        wsNumericUnitCents = Integer.parseInt(wsUnitRateCents);
        wsUnitsNumeric = Integer.parseInt(wsUnitsIn);
        wsNumericUnitWhole = wsNumericUnitDollars + (wsNumericUnitCents / 100.0);
        
        System.out.println("______________________________");
        
        wsFormattedUnits = formatUnits(wsUnitsNumeric);
        System.out.println("UNITS SOLD   : " + wsFormattedUnits);
        
        wsFormattedAmount = formatCurrency(wsNumericUnitWhole);
        System.out.println("RATE ENTERED : " + wsFormattedAmount);
        
        wsGrossAmount = wsUnitsNumeric * wsNumericUnitWhole;
        wsFormattedAmountZ = formatCurrency(wsGrossAmount);
        System.out.println("GROSS SALES  : " + wsFormattedAmountZ);
        
        wsCommAmount = wsCommRate * wsGrossAmount;
        wsFormattedAmountZ = formatCurrency(wsCommAmount);
        wsFormattedComm = String.valueOf((int)(wsCommRate * 100));
        System.out.println("COMMISSION % : " + wsFormattedComm + "%");
        System.out.println("COMMISSION   : " + wsFormattedAmountZ);
        
        wsNetProfit = wsGrossAmount - wsCommAmount;
        wsFormattedAmount = formatCurrency(wsNetProfit);
        System.out.println("PROFIT AMOUNT: " + wsFormattedAmount);
        
        System.out.println("______________________________");
        System.out.print("PROGRAM ENDS");
        getDateTime();
        
        System.out.println("PRESS ANY KEY TO EXIT");
        scanner.nextLine();
    }
    
    public static void getDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        
        wsToday = now.format(dateFormatter);
        String timeStr = now.format(timeFormatter);
        String[] timeParts = timeStr.split(":");
        wsTimeHH = timeParts[0];
        wsTimeMM = timeParts[1];
        wsTimeSS = timeParts[2];
        
        System.out.println(" ON " + wsToday + " AT " + wsTimeHH + ":" + wsTimeMM + ":" + wsTimeSS);
    }
    
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    
    public static String formatCurrency(double amount) {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        return df.format(amount);
    }
    
    public static String formatUnits(int units) {
        DecimalFormat df = new DecimalFormat("#,##0");
        return df.format(units);
    }
}