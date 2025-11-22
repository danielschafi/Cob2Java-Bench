import java.util.Scanner;

public class cobqalq {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        double calc1 = 0.0;
        double calc2 = 0.0;
        String calcjob = "A";
        double calcsum = 0.0;
        int userSelection = 0;
        
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
                System.out.print("Choice: ");
                
                try {
                    userSelection = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    userSelection = 0;
                }
                
                switch (userSelection) {
                    case 1:
                        calculate(calc1, calc2, calcjob, calcsum, userSelection);
                        break;
                    case 2:
                        information();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println(" ");
                        System.out.println("!!!ERROR in input");
                        break;
                }
            }
        }
    }
    
    private static void calculate(double calc1, double calc2, String calcjob, double calcsum, int userSelection) {
        calc1 = 0.0;
        calc2 = 0.0;
        calcjob = "A";
        
        System.out.println(" ");
        System.out.println("---------");
        System.out.println("Calculate");
        System.out.println("---------");
        System.out.print("Enter number: ");
        try {
            calc1 = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(" ");
            System.out.println("!!!ERROR in input");
            return;
        }
        
        System.out.print("Select calculation: +,-,/,*: ");
        calcjob = scanner.nextLine();
        
        System.out.print("Enter number: ");
        try {
            calc2 = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(" ");
            System.out.println("!!!ERROR in input");
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
                    return;
                }
                break;
            default:
                System.out.println(" ");
                System.out.println("!!!ERROR in input");
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
            System.out.print("Choice: ");
            
            try {
                userSelection = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                userSelection = 0;
            }
            
            switch (userSelection) {
                case 1:
                    calculate(calc1, calc2, calcjob, calcsum, userSelection);
                    return;
                case 2:
                    return;
                case 3:
                    System.exit(0);
                default:
                    System.out.println(" ");
                    System.out.println("!!!ERROR in input");
                    break;
            }
        }
    }
    
    private static void information() {
        System.out.println("-----------------------");
        System.out.println("Application information");
        System.out.println("-----------------------");
        System.out.println("Application: Cobol Qalq 0.2");
        System.out.println("Made with: Ubuntu 16.04 and GnuCobol(OpenCobol) 2.2");
        System.out.println("---------------------------------------------------");
        System.out.println("MIT License");
        System.out.println("Copyright (c) 2018 Christer Stig Åke Landstedt");
        System.out.println(" ");
        System.out.println("Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the \"Software\"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:");
        System.out.println(" ");
        System.out.println("The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.");
        System.out.println(" ");
        System.out.println("THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.");
    }
}