import java.text.DecimalFormat;

public class example2 {
    public static void main(String[] args) {
        String SampleData = "Stuff";
        String JustLetters = "ABC";
        int JustNums = 1234;
        int SignInt = -1234;
        double PayCheck = 0.0;
        String Customer = "000          00000000000000000000";
        String Ident = Customer.substring(0, 3);
        String CustName = Customer.substring(3, 23);
        String DateOfBirth = Customer.substring(23, 31);
        String MOB = DateOfBirth.substring(0, 2);
        String DOB = DateOfBirth.substring(2, 4);
        String YOB = DateOfBirth.substring(4, 8);
        int Num1 = 5;
        int Num2 = 4;
        int Num3 = 3;
        double Ans = 0.0;
        double Rem = 0.0;

        SampleData = "More Stuff";
        SampleData = String.format("%10s", 123).replace(' ', '0');
        System.out.println(SampleData);
        System.out.println(String.format("%.2f", PayCheck));
        Customer = "123Bob Smith           12211974";
        CustName = Customer.substring(3, 23);
        MOB = Customer.substring(23, 25);
        DOB = Customer.substring(25, 27);
        YOB = Customer.substring(27, 31);
        System.out.println(CustName);
        System.out.println(MOB + "/" + DOB + "/" + YOB);
        SampleData = String.format("%10s", 0).replace(' ', '0');
        System.out.println(SampleData);
        SampleData = String.format("%10s", " ").replace(' ', ' ');
        System.out.println(SampleData);
        SampleData = String.format("%10s", "\uFFFD").replace(' ', '\uFFFD');
        System.out.println(SampleData);
        SampleData = String.format("%10s", "\u0000").replace(' ', '\u0000');
        System.out.println(SampleData);
        SampleData = String.format("%10s", "\"").replace(' ', '\"');
        System.out.println(SampleData);
        SampleData = String.format("%10s", "2222222222").replace(' ', '2');
        System.out.println(SampleData);

        Ans = Num1 + Num2;
        System.out.println(String.format("%.2f", Ans));
        Ans = Num2 - Num1;
        System.out.println(String.format("%.2f", Ans));
        Ans = Num1 * Num2;
        System.out.println(String.format("%.2f", Ans));
        Ans = (double) Num2 / Num1;
        System.out.println(String.format("%.2f", Ans));
        Ans = (double) Num2 / Num1;
        Rem = Num2 % Num1;
        System.out.println("Remainder " + String.format("%.2f", Rem));

        Ans = Num1 + Num2 + Num3;
        System.out.println(String.format("%.2f", Ans));
        Ans = Num1 + Num2 + Num3;
        System.out.println(String.format("%.2f", Ans));
        Ans = Num1 + Num2;
        System.out.println(String.format("%.2f", Ans));
        Ans = Num1 - Num2;
        System.out.println(String.format("%.2f", Ans));
        Ans = Num1 * Num2;
        System.out.println(String.format("%.2f", Ans));
        Ans = (double) Num1 / Num2;
        System.out.println(String.format("%.2f", Ans));
        Ans = Math.pow(Num1, 2);
        System.out.println(String.format("%.2f", Ans));
        Ans = (3 + 5) * 5;
        System.out.println(String.format("%.2f", Ans));
        Ans = 3 + 5 * 5;
        System.out.println(String.format("%.2f", Ans));
        DecimalFormat df = new DecimalFormat("#.##");
        Ans = Math.round((3.0 + 2.005) * 100.0) / 100.0;
        System.out.println(df.format(Ans));
    }
}