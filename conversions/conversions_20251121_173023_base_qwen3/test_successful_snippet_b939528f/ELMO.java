import java.util.Scanner;

public class ELMO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
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
        
        int input1 = scanner.nextInt();
        
        if (input1 == 10) {
            System.out.println("OK, BUON LAVORO :)");
            return;
        }
        
        if (input1 == 1) {
            System.out.println("PRIMO NUMERO");
            int a = scanner.nextInt();
            System.out.println("SECONDO NUMERO");
            int b = scanner.nextInt();
            int c = a + b;
            System.out.println("Computing");
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println(a + "+" + b + " FA...");
            System.out.println(c);
        } else if (input1 == 2) {
            System.out.println("PRIMO NUMERO");
            int a = scanner.nextInt();
            System.out.println("SECONDO NUMERO");
            int b = scanner.nextInt();
            System.out.println("Computing");
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println(a + "-" + b + " FA...");
            int c = a - b;
            System.out.println(c);
        } else if (input1 == 3) {
            System.out.println("PRIMO NUMERO");
            int a = scanner.nextInt();
            System.out.println("SECONDO NUMERO");
            int b = scanner.nextInt();
            int c = a * b;
            System.out.println("Computing");
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println(a + "x" + b + " FA...");
            System.out.println(c);
        } else if (input1 == 4) {
            System.out.println("PRIMO NUMERO");
            int a = scanner.nextInt();
            System.out.println("SECONDO NUMERO");
            int b = scanner.nextInt();
            double c = (double) a / b;
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println(a + ":" + b + " FA...");
            System.out.println(c);
        } else if (input1 == 5) {
            System.out.println("NUMERO DA ELEVARE");
            int a = scanner.nextInt();
            int c = a * a;
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
            int a = scanner.nextInt();
            double c = Math.sqrt(a);
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
            int a = scanner.nextInt();
            double c = Math.sin(a);
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
            int a = scanner.nextInt();
            double c = Math.cos(a);
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
            int a = scanner.nextInt();
            double c = Math.tan(a);
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println("A " + "FA...");
            System.out.println("LA TANGENTE DI " + a + " RISULTA...");
            System.out.println(c + " RAD");
        }
    }
}