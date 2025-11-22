import java.util.Scanner;

public class INDEX {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char CREATER_ID = ' ';
        String CREATER_NAME = new String(new char[20]);
        int VAR_STATUS_CODE = 0;
        final int DEFAULT_STATUS_CODE = 200;
        final int ERROR_CLIENT_CODE = 400;
        final int ERROR_SERVER_CODE = 500;
        final int NO_ERROR_CODE = 300;
        int LOOP_COUNTER = 1;
        int VAR_IDX = 0;

        System.out.print("What`s your Creater ID ? ");
        CREATER_ID = scanner.next().charAt(0);

        System.out.print("What`s your Creater Name ? ");
        CREATER_NAME = scanner.next();

        System.out.println("Accept " + CREATER_ID + " : " + CREATER_NAME);

        System.out.print("Type Code");
        VAR_STATUS_CODE = scanner.nextInt();

        if (VAR_STATUS_CODE == DEFAULT_STATUS_CODE) {
            System.out.println("Status Code Accept");
        }
        if (VAR_STATUS_CODE == NO_ERROR_CODE) {
            System.out.println("Status Code No Error");
        }
        if (VAR_STATUS_CODE == ERROR_CLIENT_CODE) {
            System.out.println("Status Code Client Error");
        }
        if (VAR_STATUS_CODE == ERROR_SERVER_CODE) {
            System.out.println("Status Code Server Error");
        }

        System.out.print("Type Loop Counter ");
        LOOP_COUNTER = scanner.nextInt();

        System.out.println("Start Loop Run");

        while (VAR_IDX <= LOOP_COUNTER) {
            System.out.println("Counter : " + VAR_IDX);
            VAR_IDX++;
        }

        System.out.println("Stop Loop Run");

        scanner.close();
    }
}