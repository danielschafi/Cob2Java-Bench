import java.util.Scanner;

public class ELMO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        long A = 0;
        long B = 0;
        long C = 0;
        long INPUT1 = 0;
        
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
        INPUT1 = scanner.nextLong();
        
        if (INPUT1 == 10) {
            System.out.println("OK, BUON LAVORO :)");
            System.exit(0);
        }
        
        if (INPUT1 == 1) {
            System.out.println("PRIMO NUMERO");
            A = scanner.nextLong();
            System.out.println("SECONDO NUMERO");
            B = scanner.nextLong();
            C = A + B;
            System.out.println("Computing");
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println(A + "+" + B + " FA...");
            System.out.println(C);
        } else if (INPUT1 == 2) {
            System.out.println("PRIMO NUMERO");
            A = scanner.nextLong();
            System.out.println("SECONDO NUMERO");
            B = scanner.nextLong();
            System.out.println("Computing");
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println(A + "-" + B + " FA...");
            C = A - B;
            System.out.println(C);
        } else if (INPUT1 == 3) {
            System.out.println("PRIMO NUMERO");
            A = scanner.nextLong();
            System.out.println("SECONDO NUMERO");
            B = scanner.nextLong();
            C = A * B;
            System.out.println("Computing");
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println(A + "x" + B + " FA...");
            System.out.println(C);
        } else if (INPUT1 == 4) {
            System.out.println("PRIMO NUMERO");
            A = scanner.nextLong();
            System.out.println("SECONDO NUMERO");
            B = scanner.nextLong();
            C = A / B;
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println(A + ":" + B + " FA...");
            System.out.println(C);
        } else if (INPUT1 == 5) {
            System.out.println("NUMERO DA ELEVARE");
            A = scanner.nextLong();
            C = A * A;
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println(A + "^2" + " FA...");
            System.out.println(C);
        } else if (INPUT1 == 6) {
            System.out.println("NUMERO DA RADICARE");
            A = scanner.nextLong();
            C = (long) Math.sqrt(A);
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println("/" + A + " FA...");
            System.out.println(C);
        } else if (INPUT1 == 7) {
            System.out.println("NUMERO DI CUI FARE SENO");
            A = scanner.nextLong();
            C = (long) Math.sin(A);
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println("IL SENO DI " + A + " RISULTA...");
            System.out.println(C + " RAD");
        } else if (INPUT1 == 8) {
            System.out.println("NUMERO DI CUI FARE IL COSENO");
            A = scanner.nextLong();
            C = (long) Math.cos(A);
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println("IL COSENO DI " + A + " RISULTA...");
            System.out.println(C + " RAD");
        } else if (INPUT1 == 9) {
            System.out.println("NUMERO DI CUI FARE LA TANGENTE");
            A = scanner.nextLong();
            C = (long) Math.tan(A);
            System.out.println("Computing.");
            System.out.println("Computing..");
            System.out.println("Computing...");
            System.out.println("Computing....");
            System.out.println("Computing.....");
            System.out.println("Computing......");
            System.out.println("A " + "FA...");
            System.out.println("LA TANGENTE DI " + A + " RISULTA...");
            System.out.println(C + " RAD");
        }
        
        scanner.close();
    }
}