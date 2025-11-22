import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CobQalq {
    private static BigDecimal calc1 = BigDecimal.ZERO;
    private static BigDecimal calc2 = BigDecimal.ZERO;
    private static String calcjob = "A";
    private static BigDecimal calcsum = BigDecimal.ZERO;
    private static int userSelection = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        mainProgram();
    }

    private static void mainProgram() {
        selection0();
    }

    private static void selection0() {
        userSelection = 0;
        System.out.println("--------------");
        System.out.println("Cobol Qalq 0.2");
        System.out.println("--------------");
        
        while (userSelection <= 0) {
            System.out.println("MENU");
            System.out.println("------------------------");
            System.out.println("    ENTER YOUR CHOICE");
            System.out.println("    1 : Calculate");
            System.out.println("    2 : Information");
            System.out.println("    3 : Exit application");
            
            try {
                userSelection = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                userSelection = 0;
            }

            switch (userSelection) {
                case 1:
                    selection1();
                    return;
                case 2:
                    selection2();
                    return;
                case 3:
                    selection3();
                    return;
                default:
                    selection0Error();
                    break;
            }
        }
    }

    private static void selection0Error() {
        System.out.println(" ");
        System.out.println("!!!ERROR in input");
        selection0();
    }

    private static void selection1Error() {
        System.out.println(" ");
        System.out.println("!!!ERROR in input");
        selection1();
    }

    private static void selection1() {
        userSelection = 0;
        calc1 = BigDecimal.ZERO;
        calc2 = BigDecimal.ZERO;
        calcjob = "A";
        
        System.out.println(" ");
        System.out.println("---------");
        System.out.println("Calculate");
        System.out.println("---------");
        System.out.println("Enter number");
        
        try {
            calc1 = new BigDecimal(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            calc1 = BigDecimal.ZERO;
        }
        
        System.out.println("Select calculation: +,-,/,*");
        calcjob = scanner.nextLine().trim();
        
        System.out.println("Enter number");
        try {
            calc2 = new BigDecimal(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            calc2 = BigDecimal.ZERO;
        }

        switch (calcjob) {
            case "+":
                calcsum = calc1.add(calc2);
                break;
            case "-":
                calcsum = calc1.subtract(calc2);
                break;
            case "*":
                calcsum = calc1.multiply(calc2);
                break;
            case "/":
                if (calc2.compareTo(BigDecimal.ZERO) != 0) {
                    calcsum = calc1.divide(calc2, 2, RoundingMode.HALF_UP);
                } else {
                    calcsum = BigDecimal.ZERO;
                }
                break;
            default:
                selection1Error();
                return;
        }

        System.out.println("Resault: " + calcsum);

        userSelection = 0;
        while (userSelection <= 0) {
            System.out.println(" ");
            System.out.println("MENU");
            System.out.println("---------------------------");
            System.out.println("    ENTER YOUR CHOICE");
            System.out.println("    1 : Calculate again");
            System.out.println("    2 : Return to main menu");
            System.out.println("    3 : Exit application");
            
            try {
                userSelection = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                userSelection = 0;
            }

            switch (userSelection) {
                case 1:
                    selection1();
                    return;
                case 2:
                    selection0();
                    return;
                case 3:
                    selection3();
                    return;
                default:
                    selection1Error();
                    return;
            }
        }
    }

    private static void selection2() {
        userSelection = 0;
        System.out.println("-----------------------");
        System.out.println("Application information");
        System.out.println("-----------------------");
        System.out.println("Application: Cobol Qalq 0.2");
        System.out.println("Made with: Ubuntu 16.04 and GnuCobol(OpenCobol) 2.2");
        System.out.println("---------------------------------------------------");
        System.out.println("MIT License");
        System.out.println("Copyright (c) 2018 Christer Stig Åke Landstedt");
        System.out.println(" ");
        System.out.println("Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the \"Software\"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,and to permit persons to whom the Software is furnished to do so, subject to the following conditions:");
        System.out.println(" ");
        System.out.println("The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.");
        System.out.println(" ");
        System.out.println("THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.");
        selection0();
    }

    private static void selection3() {
        userSelection = 0;
        scanner.close();
        System.exit(0);
    }
}