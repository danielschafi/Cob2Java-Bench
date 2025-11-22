import java.util.Scanner;

public class ELMO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        double a = 0.0;
        double b = 0.0;
        double c = 0.0;
        long input1 = 0;
        String q = "Y";
        
        do {
            System.out.println("CALCULATOR");
            System.out.println("WHAT DO YOU WANT DO DO?");
            System.out.println("1 ADDITION");
            System.out.println("2 SUBTRACTION");
            System.out.println("3 MOLTIPLICATION");
            System.out.println("4 DIVISION");
            System.out.println("5 SQUARING");
            System.out.println("6 CUBING");
            System.out.println("7 SQUARE ROOT");
            System.out.println("8 CUBE ROOT");
            System.out.println("9 SINUS");
            System.out.println("10 COSINE");
            System.out.println("11 TANGENT");
            System.out.println("12 SIN^-1");
            System.out.println("13 COS^-1");
            System.out.println("14 TAN^-1");
            System.out.println("15 EXIT");
            System.out.println("CHOOSE AN OPTION");
            input1 = scanner.nextLong();
            scanner.nextLine();
            
            switch ((int)input1) {
                case 15:
                    System.out.println("OK, GOOD JOB :)");
                    scanner.close();
                    System.exit(0);
                    break;
                    
                case 1:
                    System.out.println("FIRST NUMBER");
                    a = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("SECOND NUMBER");
                    b = scanner.nextDouble();
                    scanner.nextLine();
                    c = a + b;
                    System.out.println("Computing");
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("(" + a + ")" + "+" + "(" + b + ")" + "RESULTS...");
                    System.out.println(c);
                    break;
                    
                case 2:
                    System.out.println("FIRST NUMBER");
                    a = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("SECOND NUMBER");
                    b = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Computing");
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("(" + a + ")" + "-" + "(" + b + ")" + " RESULTS...");
                    c = a - b;
                    System.out.println(c);
                    break;
                    
                case 3:
                    System.out.println("FIRST NUMBER");
                    a = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("SECOND NUMBER");
                    b = scanner.nextDouble();
                    scanner.nextLine();
                    c = a * b;
                    System.out.println("Computing");
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("(" + a + ")" + "X" + "(" + b + ")" + " RESULTS...");
                    System.out.println(c);
                    break;
                    
                case 4:
                    System.out.println("FIRST NUMBER");
                    a = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("SECOND NUMBER");
                    b = scanner.nextDouble();
                    scanner.nextLine();
                    c = a / b;
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("(" + a + ")" + ":" + "(" + b + ")" + " RESULTS...");
                    System.out.println(c);
                    break;
                    
                case 5:
                    System.out.println("NUMERO TO SQUARE");
                    a = scanner.nextDouble();
                    scanner.nextLine();
                    c = a * a;
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("(" + a + ")" + "^2" + " RESULTS...");
                    System.out.println(c);
                    break;
                    
                case 6:
                    System.out.println("NUMERO DA ELEVARE (AL CUBO)");
                    a = scanner.nextDouble();
                    scanner.nextLine();
                    c = a * a * a;
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("(" + a + ")" + "^3" + " FA...");
                    System.out.println(c);
                    break;
                    
                case 7:
                    System.out.println("SQUARE ROOT OF");
                    a = scanner.nextDouble();
                    scanner.nextLine();
                    c = Math.sqrt(a);
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("/" + "(" + a + ")" + " FA...");
                    System.out.println(c);
                    break;
                    
                case 8:
                    System.out.println("CUBE ROOT OF...");
                    a = scanner.nextDouble();
                    scanner.nextLine();
                    c = Math.pow(a, 0.33);
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("3/" + "(" + a + ")" + " RESULTS...");
                    System.out.println(c);
                    break;
                    
                case 9:
                    System.out.println("SIN OF...");
                    a = scanner.nextDouble();
                    scanner.nextLine();
                    c = Math.sin(a * 3.14159 / 180);
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");