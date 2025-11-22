import java.util.Scanner;

public class INDEX {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        char createrId;
        String createrName;
        int varStatusCode;
        int defaultStatusCode = 200;
        int errorClientCode = 400;
        int errorServerCode = 500;
        int noErrorCode = 300;
        int loopCounter;
        int varIdx = 0;
        
        System.out.print("What's your Creater ID ? ");
        createrId = scanner.next().charAt(0);
        
        System.out.print("What's your Creater Name ? ");
        createrName = scanner.next();
        
        System.out.println("Accept " + createrId + " : " + createrName);
        
        System.out.println("Type Code");
        varStatusCode = scanner.nextInt();
        
        if (varStatusCode == defaultStatusCode) {
            System.out.println("Status Code Accept");
        }
        
        if (varStatusCode == noErrorCode) {
            System.out.println("Status Code No Error");
        }
        
        if (varStatusCode == errorClientCode) {
            System.out.println("Status Code Client Error");
        }
        
        if (varStatusCode == errorServerCode) {
            System.out.println("Status Code Server Error");
        }
        
        System.out.println("Type Loop Counter ");
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