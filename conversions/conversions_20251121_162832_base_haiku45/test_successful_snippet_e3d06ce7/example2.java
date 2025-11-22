import java.math.BigDecimal;
import java.math.RoundingMode;

public class example2 {
    
    static class Customer {
        int ident;
        String custName;
        int mob;
        int dob;
        int yob;
    }
    
    public static void main(String[] args) {
        String sampleData = "Stuff";
        String justLetters = "ABC";
        int justNums = 1234;
        int signInt = -1234;
        BigDecimal payCheck = new BigDecimal("0.00");
        Customer customer = new Customer();
        int num1 = 5;
        int num2 = 4;
        int num3 = 3;
        BigDecimal ans = new BigDecimal("0.00");
        BigDecimal rem = new BigDecimal("0.00");
        
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
        
        ans = new BigDecimal(num1 + num2);
        System.out.println(ans);
        
        ans = new BigDecimal(num2 - num1);
        System.out.println(ans);
        
        ans = new BigDecimal(num1 * num2);
        System.out.println(ans);
        
        ans = new BigDecimal(num2).divide(new BigDecimal(num1), 2, RoundingMode.DOWN);
        System.out.println(ans);
        
        BigDecimal divisor = new BigDecimal(num1);
        BigDecimal dividend = new BigDecimal(num2);
        ans = dividend.divide(divisor, 2, RoundingMode.DOWN);
        rem = dividend.remainder(divisor);
        System.out.println("Remainder " + rem);
        
        ans = new BigDecimal(num1 + num2 + num3);
        System.out.println(ans);
        
        ans = new BigDecimal(num1 + num2 + num3);
        System.out.println(ans);
        
        ans = new BigDecimal(num1 + num2);
        ans = new BigDecimal(num1 - num2);
        ans = new BigDecimal(num1 * num2);
        ans = new BigDecimal(num2).divide(new BigDecimal(num1), 2, RoundingMode.DOWN);
        System.out.println(ans);
        
        ans = new BigDecimal(num1 * num1);
        System.out.println(ans);
        
        ans = new BigDecimal((3 + 5) * 5);
        System.out.println(ans);
        
        ans = new BigDecimal(3 + 5 * 5);
        System.out.println(ans);
        
        ans = new BigDecimal("3.0").add(new BigDecimal("2.005"));
        ans = ans.setScale(2, RoundingMode.HALF_UP);
        System.out.println(ans);
    }
}