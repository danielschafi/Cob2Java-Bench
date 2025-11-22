```java
import java.util.Scanner;

public class INDEX {
    static class Creater {
        String id;
        String name;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Creater creater = new Creater();
        int varStatusCode;
        int defaultStatusCode = 200;
        int errorClientCode = 400;
        int errorServerCode = 500;
        int noErrorCode = 300;
        int loopCounter = 1;
        int varIdx = 0;
        
        System.out.print("What`s your Creater ID ? ");
        creater.id = scanner.nextLine();
        
        System.out.print("What`s your Creater Name ? ");
        creater.name = scanner.nextLine();
        
        System.out.println("Accept " + creater.id + " : " + creater.name);
        
        System.out.print("Type Code");
        System.out.println();
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
        
        System.out.print("Type Loop Counter ");
        System.out.println();
        loopCounter = scanner.nextInt();
        
        System.out.println("Start Loop Run");
        
        while (varIdx <= loopCounter) {
            System.out.println("Counter : " + varIdx);
            varIdx += 1;
        }
        
        System.out.println("Stop Loop Run");
        
        scanner.close();
    }
}