import java.util.Scanner;

public class ELMO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double A, B, C;
        int INPUT1;
        String Q = "Y";

        while (true) {
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
            INPUT1 = scanner.nextInt();

            switch (INPUT1) {
                case 15:
                    System.out.println("OK, GOOD JOB :)");
                    System.exit(0);
                    break;

                case 1:
                    System.out.println("FIRST NUMBER");
                    A = scanner.nextDouble();
                    System.out.println("SECOND NUMBER");
                    B = scanner.nextDouble();
                    C = A + B;
                    System.out.println("Computing");
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("(" + A + ")" + "+" + "(" + B + ")" + "RESULTS...");
                    System.out.println(C);
                    break;

                case 2:
                    System.out.println("FIRST NUMBER");
                    A = scanner.nextDouble();
                    System.out.println("SECOND NUMBER");
                    B = scanner.nextDouble();
                    System.out.println("Computing");
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("(" + A + ")" + "-" + "(" + B + ")" + " RESULTS...");
                    C = A - B;
                    System.out.println(C);
                    break;

                case 3:
                    System.out.println("FIRST NUMBER");
                    A = scanner.nextDouble();
                    System.out.println("SECOND NUMBER");
                    B = scanner.nextDouble();
                    C = A * B;
                    System.out.println("Computing");
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("(" + A + ")" + "X" + "(" + B + ")" + " RESULTS...");
                    System.out.println(C);
                    break;

                case 4:
                    System.out.println("FIRST NUMBER");
                    A = scanner.nextDouble();
                    System.out.println("SECOND NUMBER");
                    B = scanner.nextDouble();
                    C = A / B;
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("(" + A + ")" + ":" + "(" + B + ")" + " RESULTS...");
                    System.out.println(C);
                    break;

                case 5:
                    System.out.println("NUMERO TO SQUARE");
                    A = scanner.nextDouble();
                    C = A * A;
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("(" + A + ")" + "^2" + " RESULTS...");
                    System.out.println(C);
                    break;

                case 6:
                    System.out.println("NUMERO DA ELEVARE (AL CUBO)");
                    A = scanner.nextDouble();
                    C = A * A * A;
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("(" + A + ")" + "^3" + " FA...");
                    System.out.println(C);
                    break;

                case 7:
                    System.out.println("SQUARE ROOT OF");
                    A = scanner.nextDouble();
                    C = Math.sqrt(A);
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("/" + "(" + A + ")" + " FA...");
                    System.out.println(C);
                    break;

                case 8:
                    System.out.println("CUBE ROOT OF...");
                    A = scanner.nextDouble();
                    C = Math.pow(A, 0.33);
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("3/" + "(" + A + ")" + " RESULTS...");
                    System.out.println(C);
                    break;

                case 9:
                    System.out.println("SIN OF...");
                    A = scanner.nextDouble();
                    C = Math.sin(A * 3.14159 / 180);
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("SIN OF " + "(" + A + ")" + " RESULTS...");
                    System.out.println(C + " DEG");
                    break;

                case 10:
                    System.out.println("COS OF");
                    A = scanner.nextDouble();
                    C = Math.cos(A * 3.14159 / 180);
                    System.out.println("Computing.");
                    System.out.println("Computing..");
                    System.out.println("Computing...");
                    System.out.println("Computing....");
                    System.out.println("Computing.....");
                    System.out.println("Computing......");
                    System.out.println("C