import java.math.BigDecimal;
import java.math.RoundingMode;

public class example2 {
    static String sampleData = "Stuff";
    static String justLetters = "ABC";
    static int justNums = 1234;
    static int signInt = -1234;
    static BigDecimal payCheck = BigDecimal.ZERO;
    
    static class Customer {
        int ident;
        String custName;
        int mob;
        int dob;
        int yob;
    }
    
    static Customer customer = new Customer();
    
    static int num1 = 5;
    static int num2 = 4;
    static int num3 = 3;
    static BigDecimal ans = BigDecimal.ZERO;
    static BigDecimal rem = BigDecimal.ZERO;
    
    public static void main(String[] args) {
        sampleData = "More Stuff";
        sampleData = "123";
        System.out.println(sampleData);
        System.out.println(payCheck);
        
        String customerStr = "123Bob Smith           12211974";
        customer.ident = Integer.parseInt(customerStr.substring(0, 3));
        customer.custName = customerStr.substring(3, 23);
        customer.mob = Integer.parseInt(customerStr.substring(23, 25));
        customer.dob = Integer.parseInt(customerStr.substring(25, 27));
        customer.yob = Integer.parseInt(customerStr.substring(27, 31));
        
        System.out.println(customer.custName);
        System.out.println(customer.mob + "/" + customer.dob + "/" + customer.yob);
        
        sampleData = "";
        System.out.println(sampleData);
        
        sampleData = " ";
        System.out.println(sampleData);
        
        sampleData = "\u00FF";
        System.out.println(sampleData);
        
        sampleData = "\u0000";
        System.out.println(sampleData);
        
        sampleData = "\"";
        System.out.println(sampleData);
        
        sampleData = "2222222222";
        System.out.println(sampleData);
        
        ans = BigDecimal.valueOf(num1 + num2);
        System.out.println(ans);
        
        ans = BigDecimal.valueOf(num2 - num1);
        System.out.println(ans);
        
        ans = BigDecimal.valueOf(num1 * num2);
        System.out.println(ans);
        
        ans = BigDecimal.valueOf(num2).divide(BigDecimal.valueOf(num1), 2, RoundingMode.HALF_UP);
        System.out.println(ans);
        
        BigDecimal divisor = BigDecimal.valueOf(num1);
        BigDecimal dividend = BigDecimal.valueOf(num2);
        ans = dividend.divide(divisor, 2, RoundingMode.HALF_UP);
        rem = dividend.remainder(divisor);
        System.out.println("Remainder " + rem);
        
        ans = BigDecimal.valueOf(num1 + num2 + num3);
        System.out.println(ans);
        
        ans = BigDecimal.valueOf(num1 + num2 + num3);
        System.out.println(ans);
        
        ans = BigDecimal.valueOf(num1).add(BigDecimal.valueOf(num2));
        ans = BigDecimal.valueOf(num1).subtract(BigDecimal.valueOf(num2));
        ans = BigDecimal.valueOf(num1).multiply(BigDecimal.valueOf(num2));
        ans = BigDecimal.valueOf(num1).divide(BigDecimal.valueOf(num2), 2, RoundingMode.HALF_UP);
        System.out.println(ans);
        
        ans = BigDecimal.valueOf(num1).pow(2);
        System.out.println(ans);
        
        ans = BigDecimal.valueOf((3 + 5) * 5);
        System.out.println(ans);
        
        ans = BigDecimal.valueOf(3 + 5 * 5);
        System.out.println(ans);
        
        ans = new BigDecimal("3.0").add(new BigDecimal("2.005")).setScale(2, RoundingMode.HALF_UP);
        System.out.println(ans);
    }
}