import java.util.Scanner;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class COMMCARB {
    
    private static final double WS_COMM_RATE = 0.09;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int wsUnitsNumeric = 0;
        double wsNumericUnitWhole = 0.0;
        double wsGrossAmount = 0.0;
        double wsCommAmount = 0.0;
        double wsNetProfit = 0.0;
        
        System.out.println("START COMMCARB BY RYAN BROOKS");
        getDateTime();
        
        // PROMPT THE USER FOR RATE UNTIL NUMERIC RATE ENTERED
        boolean validRate = false;
        while (!validRate) {
            System.out.println("ENTER THE UNIT PRICE FROM 000.00 TO 999.99 AND PRESS ENTER");
            String wsUnitRateIn = scanner.nextLine();
            
            if (wsUnitRateIn.length() == 6 && wsUnitRateIn.charAt(3) == '.') {
                String dollars = wsUnitRateIn.substring(0, 3);
                String cents = wsUnitRateIn.substring(4, 6);
                
                if (isNumeric(dollars) && isNumeric(cents)) {
                    int wsNumericUnitDollars = Integer.parseInt(dollars);
                    int wsNumericUnitCents = Integer.parseInt(cents);
                    wsNumericUnitWhole = wsNumericUnitDollars + (wsNumericUnitCents / 100.0);
                    validRate = true;
                }
            }
        }
        
        // PROMPT THE USER FOR UNITS UNTIL NUMERIC UNITS ENTERED
        boolean validUnits = false;
        while (!validUnits) {
            System.out.println("ENTER THE UNITS SOLD FROM 000 TO 999 AND PRESS ENTER");
            String wsUnitsIn = scanner.nextLine();
            
            if (wsUnitsIn.length() == 3 && isNumeric(wsUnitsIn)) {
                wsUnitsNumeric = Integer.parseInt(wsUnitsIn);
                validUnits = true;
            }
        }
        
        System.out.println("______________________________");
        
        // FORMAT UNITS IN AND DISPLAY
        DecimalFormat unitsFormat = new DecimalFormat("###,###,###,###");
        String wsFormattedUnits = unitsFormat.format(wsUnitsNumeric);
        System.out.println("UNITS SOLD   : " + wsFormattedUnits);
        
        // FORMAT AND DISPLAY UNIT PRICE
        DecimalFormat amountFormat = new DecimalFormat("$###,###,###.00");
        String wsFormattedAmount = amountFormat.format(wsNumericUnitWhole);
        System.out.println("RATE ENTERED : " + wsFormattedAmount);
        
        // COMPUTE, FORMAT AND DISPLAY GROSS SALES
        wsGrossAmount = wsUnitsNumeric * wsNumericUnitWhole;
        String wsFormattedAmountZ = formatAmountZ(wsGrossAmount);
        System.out.println("GROSS SALES  : " + wsFormattedAmountZ);
        
        // COMPUTE, FORMAT AND DISPLAY COMMISSION
        wsCommAmount = WS_COMM_RATE * wsGrossAmount;
        wsFormattedAmountZ = formatAmountZ(wsCommAmount);
        long wsFormattedComm = Math.round(WS_COMM_RATE * 100);
        System.out.println("COMMISSION % : " + wsFormattedComm + "%");
        System.out.println("COMMISSION   : " + wsFormattedAmountZ);
        
        // COMPUTE, FORMAT AND DISPLAY NET PROFIT
        wsNetProfit = wsGrossAmount - wsCommAmount;
        wsFormattedAmount = amountFormat.format(wsNetProfit);
        System.out.println("PROFIT AMOUNT: " + wsFormattedAmount);
        
        System.out.println("______________________________");
        System.out.print("PROGRAM ENDS");
        getDateTime();
        
        System.out.println("PRESS ANY KEY TO EXIT");
        scanner.nextLine();
        
        scanner.close();
    }
    
    private static boolean isNumeric(String str) {
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
    
    private static String formatAmountZ(double amount) {
        if (amount == 0.0) {
            return "$           .00";
        }
        DecimalFormat df = new DecimalFormat("$###,###,###.00");
        String formatted = df.format(amount);
        if (formatted.equals("$.00")) {
            return "$           .00";
        }
        return formatted;
    }
    
    private static void getDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = new DateTimeFormatter() {
            public String format(LocalDateTime dt) {
                return String.format("%4d/%02d/%02d", dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth());
            }
        };
        String date = String.format("%4d/%02d/%02d", now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        String time = String.format("%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond());
        System.out.println(" ON " + date + " AT " + time);
    }
}