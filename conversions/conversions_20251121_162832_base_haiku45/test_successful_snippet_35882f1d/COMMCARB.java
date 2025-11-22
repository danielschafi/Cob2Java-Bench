```java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class COMMCARB {
    private static Scanner scanner = new Scanner(System.in);
    
    private static String wsUnitsIn = "";
    private static int wsUnitsNumeric = 0;
    
    private static String wsUnitRateDollars = "";
    private static String wsUnitRateDecimal = "";
    private static String wsUnitRateCents = "";
    
    private static int wsNumericUnitDollars = 0;
    private static int wsNumericUnitCents = 0;
    private static BigDecimal wsNumericUnitWhole = BigDecimal.ZERO;
    
    private static BigDecimal wsCommRate = new BigDecimal("0.09");
    
    private static BigDecimal wsGrossAmount = BigDecimal.ZERO;
    private static BigDecimal wsCommAmount = BigDecimal.ZERO;
    private static BigDecimal wsNetProfit = BigDecimal.ZERO;
    
    private static String wsEnd = "";
    
    public static void main(String[] args) {
        System.out.println("START COMMCARB BY RYAN BROOKS");
        getDateTime();
        
        // PROMPT THE USER FOR RATE UNTIL NUMERIC RATE ENTERED
        boolean validRate = false;
        while (!validRate) {
            System.out.println("ENTER THE UNIT PRICE FROM 000.00 TO 999.99 AND PRESS ENTER");
            String input = scanner.nextLine();
            
            if (input.length() == 7 && input.charAt(3) == '.') {
                wsUnitRateDollars = input.substring(0, 3);
                wsUnitRateDecimal = input.substring(3, 4);
                wsUnitRateCents = input.substring(4, 7);
                
                if (isNumeric(wsUnitRateDollars) && isNumeric(wsUnitRateCents) && wsUnitRateDecimal.equals(".")) {
                    validRate = true;
                }
            }
        }
        
        // PROMPT THE USER FOR UNITS UNTIL NUMERIC UNITS ENTERED
        boolean validUnits = false;
        while (!validUnits) {
            System.out.println("ENTER THE UNITS SOLD FROM 000 TO 999 AND PRESS ENTER");
            wsUnitsIn = scanner.nextLine();
            
            if (isNumeric(wsUnitsIn) && wsUnitsIn.length() <= 3) {
                validUnits = true;
            }
        }
        
        // CONVERT THE STRING DATA ENTERED INTO NUMERIC FIELD
        wsNumericUnitDollars = Integer.parseInt(wsUnitRateDollars);
        wsNumericUnitCents = Integer.parseInt(wsUnitRateCents);
        wsUnitsNumeric = Integer.parseInt(wsUnitsIn);
        wsNumericUnitWhole = new BigDecimal(wsNumericUnitDollars + "." + wsUnitRateCents);
        
        System.out.println("______________________________");
        
        // FORMAT UNITS IN AND DISPLAY
        System.out.println("UNITS SOLD   : " + formatUnits(wsUnitsIn));
        
        // FORMAT AND DISPLAY UNIT PRICE
        System.out.println("RATE ENTERED : " + formatAmount(wsNumericUnitWhole));
        
        // COMPUTE, FORMAT AND DISPLAY GROSS SALES
        wsGrossAmount = new BigDecimal(wsUnitsNumeric).multiply(wsNumericUnitWhole);
        System.out.println("GROSS SALES  : " + formatAmount(wsGrossAmount));
        
        // COMPUTE, FORMAT AND DISPLAY COMMISSION
        wsCommAmount = wsCommRate.multiply(wsGrossAmount);
        BigDecimal commPercent = wsCommRate.multiply(new BigDecimal("100"));
        System.out.println("COMMISSION % : " + commPercent.setScale(0, RoundingMode.HALF_UP) + "%");
        System.out.println("COMMISSION   : " + formatAmount(wsCommAmount));
        
        // COMPUTE, FORMAT AND DISPLAY NET PROFIT
        wsNetProfit = wsGrossAmount.subtract(wsCommAmount);
        System.out.println("PROFIT AMOUNT: " + formatAmount(wsNetProfit));
        
        System.out.println("______________________________");
        System.out.print("PROGRAM ENDS");
        getDateTime();
        
        System.out.println("PRESS ANY KEY TO EXIT");
        wsEnd = scanner.nextLine();
        
        scanner.close();
    }
    
    private static void getDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        
        String date = now.format(dateFormatter);
        String time = now.format(timeFormatter);
        String[] timeParts = time.split(":");
        
        System.out.println(" ON " + date + " AT " + timeParts[0] + ":" + timeParts[1] + ":" + timeParts[2]);
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
    
    private static String formatUnits(String units) {
        try {
            int value = Integer.parseInt(units);
            return String.format("%,d", value);
        } catch (NumberFormatException e) {
            return units;
        }
    }
    
    private static String formatAmount(BigDecimal amount) {
        return String.format("$%,.2f", amount);
    }
}