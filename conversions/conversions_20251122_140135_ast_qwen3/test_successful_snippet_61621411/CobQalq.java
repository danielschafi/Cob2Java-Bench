import java.util.Scanner;

public class CobQalq {
    private static double calc1 = 0.0;
    private static double calc2 = 0.0;
    private static String calcjob = "A";
    private static double calcsum = 0.0;
    private static int userSelection = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MAIN_PROGRAM();
    }

    public static void MAIN_PROGRAM() {
        while (true) {
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
                    userSelection = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    userSelection = 0;
                }

                switch (userSelection) {
                    case 1:
                        SELECTION1();
                        break;
                    case 2:
                        SELECTION2();
                        break;
                    case 3:
                        SELECTION3();
                        return;
                    default:
                        SELECTION0ERROR();
                        break;
                }
            }
        }
    }

    public static void SELECTION0ERROR() {
        System.out.println(" ");
        System.out.println("!!!ERROR in input");
    }

    public static void SELECTION1ERROR() {
        System.out.println(" ");
        System.out.println("!!!ERROR in input");
    }

    public static void SELECTION1() {
        userSelection = 0;
        calc1 = 0.0;
        calc2 = 0.0;
        calcjob = "A";
        
        System.out.println(" ");
        System.out.println("---------");
        System.out.println("Calculate");
        System.out.println("---------");
        System.out.println("Enter number");
        try {
            calc1 = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            SELECTION1ERROR();
            return;
        }
        
        System.out.println("Select calculation: +,-,/,*");
        calcjob = scanner.nextLine();
        
        System.out.println("Enter number");
        try {
            calc2 = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            SELECTION1ERROR();
            return;
        }

        switch (calcjob) {
            case "+":
                calcsum = calc1 + calc2;
                break;
            case "-":
                calcsum = calc1 - calc2;
                break;
            case "*":
                calcsum = calc1 * calc2;
                break;
            case "/":
                if (calc2 != 0) {
                    calcsum = calc1 / calc2;
                } else {
                    System.out.println("Division by zero error!");
                    SELECTION1ERROR();
                    return;
                }
                break;
            default:
                SELECTION1ERROR();
                return;
        }

        System.out.println("Resault: " + calcsum);

        while (userSelection <= 0) {
            System.out.println(" ");
            System.out.println("MENU");
            System.out.println("---------------------------");
            System.out.println("    ENTER YOUR CHOICE");
            System.out.println("    1 : Calculate again");
            System.out.println("    2 : Return to main menu");
            System.out.println("    3 : Exit application");
            try {
                userSelection = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                userSelection = 0;
            }

            switch (userSelection) {
                case 1:
                    SELECTION1();
                    break;
                case 2:
                    MAIN_PROGRAM();
                    return;
                case 3:
                    SELECTION3();
                    return;
                default:
                    SELECTION1ERROR();
                    break;
            }
        }
    }

    public static void SELECTION2() {
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
        System.out.println("Permission is hereby granted, free of charge, to any ");
        System.out.println("person obtaining a copy of this software and ");
        System.out.println("associated documentation files (the \"Software\"), ");
        System.out.println("to deal in the Software without restriction, ");
        System.out.println("including without limitation the rights ");
        System.out.println("to use, copy, modify, merge, publish, distribute, ");
        System.out.println("sublicense, and/or sell copies of the Software,");
        System.out.println("and to permit persons to whom the Software is ");
        System.out.println("furnished to do so, subject to the following ");
        System.out.println("conditions:");
        System.out.println(" ");
        System.out.println("The above copyright notice and this permission notice ");
        System.out.println("shall be included in all copies or substantial ");
        System.out.println("portions of the Software.");
        System.out.println(" ");
        System.out.println("THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY ");
        System.out.println("OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT ");
        System.out.println("LIMITED TO THE WARRANTIES OF MERCHANTABILITY, ");
        System.out.println("FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. ");
        System.out.println("IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS ");
        System.out.println("BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER ");
        System.out.println("LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR ");
        System.out.println("OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION ");
        System.out.println("WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE ");
        System.out.println("SOFTWARE.");
    }

    public static void SELECTION3() {
        System.exit(0);
    }
}