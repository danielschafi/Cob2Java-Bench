import java.text.DecimalFormat;

public class example2 {
    public static void main(String[] args) {
        String SampleData = "Stuff";
        String JustLetters = "ABC";
        int JustNums = 1234;
        int SignInt = -1234;
        double PayCheck = 0.00;
        String Customer = "123Bob Smith           12211974";
        int Ident = Integer.parseInt(Customer.substring(0, 3));
        String CustName = Customer.substring(3, 23).trim();
        int MOB = Integer.parseInt(Customer.substring(23, 25));
        int DOB = Integer.parseInt(Customer.substring(25, 27));
        int YOB = Integer.parseInt(Customer.substring(27, 31));
        int Num1 = 5;
        int Num2 = 4;
        int Num3 = 3;
        double Ans = 0.00;
        double Rem = 0.00;

        SampleData = "More Stuff";
        SampleData = "123";
        System.out.println(SampleData);
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println(df.format(PayCheck));
        System.out.println(CustName);
        System.out.println(MOB + "/" + DOB + "/" + YOB);
        SampleData = "0";
        System.out.println(SampleData);
        SampleData = " ";
        System.out.println("[" + SampleData + "]");
        SampleData = new String(new char[10]).replace('\0', (char) 255);
        System.out.println("[" + SampleData + "]");
        SampleData = new String(new char[10]).replace('\0', (char) 0);
        System.out.println("[" + SampleData + "]");
        SampleData = "\"";
        System.out.println(SampleData);
        SampleData = "2222222222";
        System.out.println(SampleData);

        Ans = Num1 + Num2;
        System.out.println(Ans);
        Ans = Num2 - Num1;
        System.out.println(Ans);
        Ans = Num1 * Num2;
        System.out.println(Ans);
        Ans = (double) Num2 / Num1;
        System.out.println(Ans);
        Ans = (double) Num2 / Num1;
        Rem = Num2 % Num1;
        System.out.println("Remainder " + Rem);

        Ans = Num1 + Num2 + Num3;
        System.out.println(Ans);
        Ans = Num1 + Num2 + Num3;
        System.out.println(Ans);
        Ans = Num1 + Num2;
        System.out.println(Ans);
        Ans = Num1 - Num2;
        System.out.println(Ans);
        Ans = Num1 * Num2;
        System.out.println(Ans);
        Ans = (double) Num1 / Num2;
        System.out.println(Ans);
        Ans = Math.pow(Num1, 2);
        System.out.println(Ans);
        Ans = (3 + 5) * 5;
        System.out.println(Ans);
        Ans = 3 + 5 * 5;
        System.out.println(Ans);
        Ans = Math.round((3.0 + 2.005) * 100.0) / 100.0;
        System.out.println(Ans);
    }
}