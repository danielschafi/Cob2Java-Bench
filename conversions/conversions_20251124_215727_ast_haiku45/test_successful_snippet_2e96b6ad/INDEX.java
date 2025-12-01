import java.util.Scanner;

public class INDEX {
    static class Creater {
        String creater_id;
        String creater_name;
    }
    
    static Creater creater = new Creater();
    static int var_status_code;
    static final int DEFAULT_STATUS_CODE = 200;
    static final int ERROR_CLIENT_CODE = 400;
    static final int ERROR_SERVER_CODE = 500;
    static final int NO_ERROR_CODE = 300;
    static int loop_counter = 1;
    static int var_idx = 0;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("What`s your Creater ID ? ");
        creater.creater_id = scanner.nextLine();
        
        System.out.print("What`s your Creater Name ? ");
        creater.creater_name = scanner.nextLine();
        
        System.out.println("Accept " + creater.creater_id + " : " + creater.creater_name);
        
        System.out.print("Type Code");
        System.out.println();
        var_status_code = scanner.nextInt();
        
        if (var_status_code == DEFAULT_STATUS_CODE) {
            System.out.println("Status Code Accept");
        }
        if (var_status_code == NO_ERROR_CODE) {
            System.out.println("Status Code No Error");
        }
        if (var_status_code == ERROR_CLIENT_CODE) {
            System.out.println("Status Code Client Error");
        }
        if (var_status_code == ERROR_SERVER_CODE) {
            System.out.println("Status Code Server Error");
        }
        
        System.out.print("Type Loop Counter ");
        loop_counter = scanner.nextInt();
        
        System.out.println("Start Loop Run");
        
        var_idx = 0;
        while (var_idx <= loop_counter) {
            System.out.println("Counter : " + var_idx);
            var_idx = var_idx + 1;
        }
        
        System.out.println("Stop Loop Run");
        
        scanner.close();
    }
}