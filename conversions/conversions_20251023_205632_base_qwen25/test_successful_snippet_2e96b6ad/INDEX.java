import java.util.Scanner;

public class INDEX {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        class Creater {
            char createrId;
            String createrName;
        }

        Creater creater = new Creater();
        int varStatusCode;
        final int DEFAULT_STATUS_CODE = 200;
        final int ERROR_CLIENT_CODE = 400;
        final int ERROR_SERVER_CODE = 500;
        final int NO_ERROR_CODE = 300;
        int loopCounter = 1;
        int varIdx = 0;

        System.out.print("What`s your Creater ID ? ");
        creater.createrId = scanner.next().charAt(0);

        System.out.print("What`s your Creater Name ? ");
        creater.createrName = scanner.next();

        System.out.println("Accept " + creater.createrId + " : " + creater.createrName);

        System.out.print("Type Code ");
        varStatusCode = scanner.nextInt();

        if (varStatusCode == DEFAULT_STATUS_CODE) {
            System.out.println("Status Code Accept");
        }
        if (varStatusCode == NO_ERROR_CODE) {
            System.out.println("Status Code No Error");
        }
        if (varStatusCode == ERROR_CLIENT_CODE) {
            System.out.println("Status Code Client Error");
        }
        if (varStatusCode == ERROR_SERVER_CODE) {
            System.out.println("Status Code Server Error");
        }

        System.out.print("Type Loop Counter ");
        loopCounter = scanner.nextInt();

        System.out.println("Start Loop Run");

        while (varIdx <= loopCounter) {
            System.out.println("Counter : " + varIdx);
            varIdx++;
        }

        System.out.println("Stop Loop Run");

        scanner.close();
    }
}