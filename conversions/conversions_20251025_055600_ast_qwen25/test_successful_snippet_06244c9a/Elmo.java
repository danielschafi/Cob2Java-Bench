import java.util.Scanner;

public class Elmo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double sleepSec;
        double a;
        double b;
        double c;
        double d;
        int input1;
        String q = "Y";

        while (!q.equalsIgnoreCase("YES") && !q.equalsIgnoreCase("Y") && !q.equalsIgnoreCase("y") && !q.equalsIgnoreCase("yes") && !q.equalsIgnoreCase("Yes")) {
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
            input1 = scanner.nextInt();

            switch (input1) {
                case 15:
                    System.out.println("OK, GOOD JOB :)");
                    return;
                case 1:
                    System.out.println("FIRST NUMBER");
                    a = scanner.nextDouble();
                    System.out.println("SECOND NUMBER");
                    b = scanner.nextDouble();
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
                    System.out.println("SECOND NUMBER");
                    b = scanner.nextDouble();
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
                    System.out.println("SECOND NUMBER");
                    b = scanner.nextDouble();
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
                    System.out.println("SECOND NUMBER");
                    b = scanner.nextDouble();
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
                    c = a * a;
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("(" + a + ")^2" + " RESULTS...");
                    System.out.println(c);
                    break;
                case 6:
                    System.out.println("NUMERO DA ELEVARE (AL CUBO)");
                    a = scanner.nextDouble();
                    c = a * a * a;
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("(" + a + ")^3" + " FA...");
                    System.out.println(c);
                    break;
                case 7:
                    System.out.println("SQUARE ROOT OF");
                    a = scanner.nextDouble();
                    c = Math.sqrt(a);
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("/(" + a + ")" + " FA...");
                    System.out.println(c);
                    break;
                case 8:
                    System.out.println("CUBE ROOT OF...");
                    a = scanner.nextDouble();
                    c = Math.pow(a, 1.0 / 3.0);
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("3/(" + a + ")" + " RESULTS...");
                    System.out.println(c);
                    break;
                case 9:
                    System.out.println("SIN OF...");
                    a = scanner.nextDouble();
                    c = Math.sin(a * Math.PI / 180);
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("SIN OF " + "(" + a + ")" + " RESULTS...");
                    System.out.println(c + " DEG");
                    break;
                case 10:
                    System.out.println("COS OF");
                    a = scanner.nextDouble();
                    c = Math.cos(a * Math.PI / 180);
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("COS OF" + "(" + a + ")" + "RESULTS");
                    System.out.println(c + " DEGREES");
                    break;
                case 11:
                    System.out.println("TAN OF...");
                    a = scanner.nextDouble();
                    c = Math.tan(a * Math.PI / 180);
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("TAN OF" + "(" + a + ")" + " RESULTS...");
                    System.out.println(c + " DEGREES");
                    break;
                case 12:
                    System.out.println("SIN ^-1 OF...");
                    a = scanner.nextDouble();
                    c = Math.asin(a) * 180 / Math.PI;
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("IL SIN^-1 OF " + "(" + a + ")" + " RESULTS...");
                    System.out.println(c + " DEGREES");
                    break;
                case 13:
                    System.out.println("COS ^-1 OF...");
                    a = scanner.nextDouble();
                    c = Math.acos(a) * 180 / Math.PI;
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("IL COS^-1 OF " + "(" + a + ")" + " RESULTS...");
                    System.out.println(c + " DEGREES");
                    break;
                case 14:
                    System.out.println("TAN^-1 OF...");
                    a = scanner.nextDouble();
                    c = Math.atan(a) * 180 / Math.PI;
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("TAN^-1 OF... " + "(" + a + ")" + " RESULTS...");
                    System.out.println(c + " GRADI");
                    break;
            }

            if (input1 != 15) {
                System.out.println("DO YOU WANT TO DO OTHER CALCULATIONS?");
                q = scanner.next();
            } else {
                System.out.println("OK, GOOD JOB