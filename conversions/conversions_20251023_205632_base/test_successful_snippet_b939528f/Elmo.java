import java.util.Scanner;
import java.lang.Math;

public class Elmo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = 0;
        int b = 0;
        double c = 0;
        int input1 = 0;

        System.out.println("CALCOLATRICE");
        System.out.println("ATTENZIONE, IL RISULTATO NON HA SEGNI!");
        System.out.println("CHE VUOI FARE?");
        System.out.println("1 ADDIZIONE");
        System.out.println("2 SOTTRAZIONE");
        System.out.println("3 MOLTIPLICAZIONE");
        System.out.println("4 DIVISIONE");
        System.out.println("5 ELEVAZIONE ALLA SECONDA");
        System.out.println("6 RADICE QUADRATA");
        System.out.println("7 SENO");
        System.out.println("8 COSENO");
        System.out.println("9 TAN");
        System.out.println("10 ESCI");
        input1 = scanner.nextInt();

        if (input1 == 10) {
            System.out.println("OK, BUON LAVORO :)");
            return;
        }

        if (input1 == 1) {
            System.out.println("PRIMO NUMERO");
            a = scanner.nextInt();
            System.out.println("SECONDO NUMERO");
            b = scanner.nextInt();
            c = a + b;
            System.out.println("Computing");
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println(a + " + " + b + " FA...");
            System.out.println(c);
        } else if (input1 == 2) {
            System.out.println("PRIMO NUMERO");
            a = scanner.nextInt();
            System.out.println("SECONDO NUMERO");
            b = scanner.nextInt();
            System.out.println("Computing");
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println(a + " - " + b + " FA...");
            c = a - b;
            System.out.println(c);
        } else if (input1 == 3) {
            System.out.println("PRIMO NUMERO");
            a = scanner.nextInt();
            System.out.println("SECONDO NUMERO");
            b = scanner.nextInt();
            c = a * b;
            System.out.println("Computing");
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println(a + " x " + b + " FA...");
            System.out.println(c);
        } else if (input1 == 4) {
            System.out.println("PRIMO NUMERO");
            a = scanner.nextInt();
            System.out.println("SECONDO NUMERO");
            b = scanner.nextInt();
            c = (double) a / b;
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println(a + " : " + b + " FA...");
            System.out.println(c);
        } else if (input1 == 5) {
            System.out.println("NUMERO DA ELEVARE");
            a = scanner.nextInt();
            c = a * a;
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println(a + "^2" + " FA...");
            System.out.println(c);
        } else if (input1 == 6) {
            System.out.println("NUMERO DA RADICARE");
            a = scanner.nextInt();
            c = Math.sqrt(a);
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println("/" + a + " FA...");
            System.out.println(c);
        } else if (input1 == 7) {
            System.out.println("NUMERO DI CUI FARE SENO");
            a = scanner.nextInt();
            c = Math.sin(a);
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println("IL SENO DI " + a + " RISULTA...");
            System.out.println(c + " RAD");
        } else if (input1 == 8) {
            System.out.println("NUMERO DI CUI FARE IL COSENO");
            a = scanner.nextInt();
            c = Math.cos(a);
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println("IL COSENO DI " + a + " RISULTA...");
            System.out.println(c + " RAD");
        } else if (input1 == 9) {
            System.out.println("NUMERO DI CUI FARE LA TANGENTE");
            a = scanner.nextInt();
            c = Math.tan(a);
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println("LA TANGENTE DI " + a + " RISULTA...");
            System.out.println(c + " RAD");
        }
    }
}