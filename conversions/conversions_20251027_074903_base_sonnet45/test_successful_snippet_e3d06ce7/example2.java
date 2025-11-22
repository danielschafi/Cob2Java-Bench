import java.math.BigDecimal;
import java.math.RoundingMode;

public class example2 {
    public static void main(String[] args) {
        String SampleData = "Stuff     ";
        String JustLetters = "ABC";
        String JustNums = "1234";
        String SignInt = "-1234";
        String PayCheck = "000000";
        
        String Ident = "000";
        String CustName = "                    ";
        String MOB = "00";
        String DOB = "00";
        String YOB = "0000";
        
        int Num1 = 5;
        int Num2 = 4;
        int Num3 = 3;
        BigDecimal Ans = new BigDecimal("0.00");
        BigDecimal Rem = new BigDecimal("0.00");
        
        SampleData = moveToX("More Stuff", 10);
        SampleData = moveToX("123", 10);
        System.out.println(SampleData);
        System.out.println(PayCheck);
        
        String Customer = "123Bob Smith           12211974";
        Ident = Customer.substring(0, 3);
        CustName = Customer.substring(3, 23);
        MOB = Customer.substring(23, 25);
        DOB = Customer.substring(25, 27);
        YOB = Customer.substring(27, 31);
        
        System.out.println(CustName);
        System.out.println(MOB + "/" + DOB + "/" + YOB);
        
        SampleData = "0000000000";
        System.out.println(SampleData);
        
        SampleData = "          ";
        System.out.println(SampleData);
        
        SampleData = "\uFFFF\uFFFF\uFFFF\uFFFF\uFFFF\uFFFF\uFFFF\uFFFF\uFFFF\uFFFF";
        System.out.println(SampleData);
        
        SampleData = "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000";
        System.out.println(SampleData);
        
        SampleData = "\"\"\"\"\"\"\"\"\"\"";
        System.out.println(SampleData);
        
        SampleData = "2222222222";
        System.out.println(SampleData);
        
        Ans = new BigDecimal(Num1 + Num2).setScale(2, RoundingMode.HALF_UP);
        System.out.println(formatDecimal(Ans, 2, 2, true));
        
        Ans = new BigDecimal(Num2 - Num1).setScale(2, RoundingMode.HALF_UP);
        System.out.println(formatDecimal(Ans, 2, 2, true));
        
        Ans = new BigDecimal(Num1 * Num2).setScale(2, RoundingMode.HALF_UP);
        System.out.println(formatDecimal(Ans, 2, 2, true));
        
        Ans = new BigDecimal(Num2).divide(new BigDecimal(Num1), 2, RoundingMode.DOWN);
        System.out.println(formatDecimal(Ans, 2, 2, true));
        
        int quotient = Num2 / Num1;
        int remainder = Num2 % Num1;
        Ans = new BigDecimal(quotient).setScale(2, RoundingMode.HALF_UP);
        Rem = new BigDecimal(remainder).setScale(2, RoundingMode.HALF_UP);
        System.out.println("Remainder " + formatDecimal(Rem, 1, 2, false));
        
        Ans = new BigDecimal(Num1 + Num2 + Num3).setScale(2, RoundingMode.HALF_UP);
        System.out.println(formatDecimal(Ans, 2, 2, true));
        
        Ans = new BigDecimal(Num1 + Num2 + Num3).setScale(2, RoundingMode.HALF_UP);
        System.out.println(formatDecimal(Ans, 2, 2, true));
        
        Ans = new BigDecimal(Num1 + Num2).setScale(2, RoundingMode.HALF_UP);
        Ans = new BigDecimal(Num1 - Num2).setScale(2, RoundingMode.HALF_UP);
        Ans = new BigDecimal(Num1 * Num2).setScale(2, RoundingMode.HALF_UP);
        Ans = new BigDecimal(Num1).divide(new BigDecimal(Num2), 2, RoundingMode.DOWN);
        System.out.println(formatDecimal(Ans, 2, 2, true));
        
        Ans = new BigDecimal(Math.pow(Num1, 2)).setScale(2, RoundingMode.HALF_UP);
        System.out.println(formatDecimal(Ans, 2, 2, true));
        
        Ans = new BigDecimal((3 + 5) * 5).setScale(2, RoundingMode.HALF_UP);
        System.out.println(formatDecimal(Ans, 2, 2, true));
        
        Ans = new BigDecimal(3 + 5 * 5).setScale(2, RoundingMode.HALF_UP);
        System.out.println(formatDecimal(Ans, 2, 2, true));
        
        Ans = new BigDecimal("3.0").add(new BigDecimal("2.005")).setScale(2, RoundingMode.HALF_UP);
        System.out.println(formatDecimal(Ans, 2, 2, true));
    }
    
    private static String moveToX(String value, int length) {
        if (value.length() >= length) {
            return value.substring(0, length);
        } else {
            return String.format("%-" + length + "s", value);
        }
    }
    
    private static String formatDecimal(BigDecimal value, int intDigits, int decDigits, boolean signed) {
        String sign = "";
        BigDecimal absValue = value.abs();
        
        if (signed && value.compareTo(BigDecimal.ZERO) < 0) {
            sign = "-";
        } else if (signed) {
            sign = "+";
        }
        
        String formatted = String.format("%0" + (intDigits + decDigits + 1) + "." + decDigits + "f", absValue);
        return sign + formatted;
    }
}