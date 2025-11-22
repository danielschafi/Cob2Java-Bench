import java.util.Scanner;

public class INDEX {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String CREATER_ID;
        String CREATER_NAME;
        int VAR_STATUS_CODE;
        int DEFAULT_STATUS_CODE = 200;
        int ERROR_CLIENT_CODE = 400;
        int ERROR_SERVER_CODE = 500;
        int NO_ERROR_CODE = 300;
        int LOOP_COUNTER = 1;
        int VAR_IDX = 0;
        
        System.out.print("What`s your Creater ID ? ");
        CREATER_ID = scanner.nextLine();
        if (CREATER_ID.length() > 1) {
            CREATER_ID = CREATER_ID.substring(0, 1);
        }
        
        System.out.print("What`s your Creater Name ? ");
        CREATER_NAME = scanner.nextLine();
        if (CREATER_NAME.length() > 20) {
            CREATER_NAME = CREATER_NAME.substring(0, 20);
        }
        
        System.out.println("Accept " + CREATER_ID + " : " + CREATER_NAME);
        
        System.out.println("Type Code");
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
        
        System.out.println("Type Loop Counter ");
        LOOP_COUNTER = scanner.nextInt();
        
        System.out.println("Start Loop Run");
        
        while (VAR_IDX <= LOOP_COUNTER) {
            System.out.println("Counter : " + VAR_IDX);
            VAR_IDX = VAR_IDX + 1;
        }
        
        System.out.println("Stop Loop Run");
        
        scanner.close();
    }
}