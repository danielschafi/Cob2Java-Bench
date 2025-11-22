public class example2 {
    public static void main(String[] args) {
        String SampleData = "Stuff";
        String JustLetters = "ABC";
        int JustNums = 1234;
        int SignInt = -1234;
        double PayCheck = 0.00;
        int Ident = 0;
        String CustName = "                    ";
        int MOB = 0;
        int DOB = 0;
        int YOB = 0;
        int Num1 = 5;
        int Num2 = 4;
        int Num3 = 3;
        double Ans = 0.00;
        double Rem = 0.00;

        SampleData = "More Stuff";
        SampleData = String.valueOf(123);
        System.out.println(SampleData);
        System.out.println(PayCheck);
        String customerString = "123Bob Smith           12211974";
        Ident = Integer.parseInt(customerString.substring(0, 3));
        CustName = customerString.substring(3, 23).trim();
        MOB = Integer.parseInt(customerString.substring(23, 25));
        DOB = Integer.parseInt(customerString.substring(25, 27));
        YOB = Integer.parseInt(customerString.substring(27, 31));
        System.out.println(CustName);
        System.out.println(MOB + "/" + DOB + "/" + YOB);
        SampleData = "          ";
        System.out.println(SampleData);
        SampleData = "          ";
        System.out.println(SampleData);
        SampleData = "!!!!!!!!!!";
        System.out.println(SampleData);
        SampleData = "          ";
        System.out.println(SampleData);
        SampleData = "\"\"\"\"\"\"\"\"\"\"";
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
        Rem = (double) Num2 % Num1;
        Ans = (double) Num2 / Num1;
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