import java.util.Scanner;

public class IsNumericTest {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        processPlain();
        processZeroFill();
        processTrim();
    }
    
    private static void processPlain() {
        System.out.print("(plain) Enter a value: ");
        String wsUserInput = scanner.nextLine();
        
        if (isNumeric(wsUserInput)) {
            System.out.println(wsUserInput + " is numeric!");
        } else {
            System.out.println(wsUserInput + " is not numeric.");
        }
    }
    
    private static void processZeroFill() {
        System.out.print("(right justify, zero fill) Enter another value: ");
        String wsUserInputJustified = scanner.nextLine();
        
        // Right justify and zero fill
        StringBuilder justified = new StringBuilder(wsUserInputJustified);
        while (justified.length() < 10) {
            justified.insert(0, '0');
        }
        wsUserInputJustified = justified.toString();
        
        if (isNumeric(wsUserInputJustified)) {
            System.out.println(wsUserInputJustified + " is numeric!");
        } else {
            System.out.println(wsUserInputJustified + " is not numeric.");
        }
    }
    
    private static void processTrim() {
        System.out.print("(trim) Enter a third value: ");
        String wsUserInput = scanner.nextLine();
        
        String trimmed = wsUserInput.trim();
        
        if (isNumeric(trimmed)) {
            System.out.println(trimmed + " is numeric!");
        } else {
            System.out.println(trimmed + " is not numeric.");
        }
    }
    
    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}