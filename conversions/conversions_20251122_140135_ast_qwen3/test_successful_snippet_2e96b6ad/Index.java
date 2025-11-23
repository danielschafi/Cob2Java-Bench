import java.util.Scanner;

public class Index {
    private static Scanner scanner = new Scanner(System.in);
    
    // Working Storage Section
    private static char CREATER_ID;
    private static String CREATER_NAME = "";
    private static int VAR_STATUS_CODE;
    private static final int DEFAULT_STATUS_CODE = 200;
    private static final int ERROR_CLIENT_CODE = 400;
    private static final int ERROR_SERVER_CODE = 500;
    private static final int NO_ERROR_CODE = 300;
    private static int LOOP_COUNTER = 1;
    private static int VAR_IDX = 0;
    
    public static void main(String[] args) {
        // CREATER Accept
        System.out.print("What's your Creater ID ? ");
        CREATER_ID = scanner.next().charAt(0);
        
        System.out.print("What's your Creater Name ? ");
        CREATER_NAME = scanner.next();
        
        System.out.println("Accept " + CREATER_ID + " : " + CREATER_NAME);
        
        System.out.println("Type Code");
        VAR_STATUS_CODE = scanner.nextInt();
        
        // StatusCode = 200
        if (VAR_STATUS_CODE == DEFAULT_STATUS_CODE) {
            System.out.println("Status Code Accept");
        }
        // StatusCOde = 300
        if (VAR_STATUS_CODE == NO_ERROR_CODE) {
            System.out.println("Status Code No Error");
        }
        // StatusCode = 400
        if (VAR_STATUS_CODE == ERROR_CLIENT_CODE) {
            System.out.println("Status Code Client Error");
        }
        // StatusCode - 500
        if (VAR_STATUS_CODE == ERROR_SERVER_CODE) {
            System.out.println("Status Code Server Error");
        }
        
        System.out.println("Type Loop Counter ");
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