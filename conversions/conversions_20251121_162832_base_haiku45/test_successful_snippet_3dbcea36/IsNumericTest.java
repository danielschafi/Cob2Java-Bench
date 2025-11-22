import java.util.Scanner;

public class IsNumericTest {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        processPlain();
        processZeroFill();
        processTrim();
        System.exit(0);
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
        String wsUserInput = scanner.nextLine();
        
        String wsUserInputJustified = rightJustify(wsUserInput, 10);
        wsUserInputJustified = wsUserInputJustified.replace(' ', '0');
        
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
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            try {
                Double.parseDouble(str);
                return true;
            } catch (NumberFormatException ex) {
                return false;
            }
        }
    }
    
    private static String rightJustify(String str, int length) {
        if (str.length() >= length) {
            return str.substring(str.length() - length);
        }
        StringBuilder sb = new StringBuilder();
        int spaces = length - str.length();
        for (int i = 0; i < spaces; i++) {
            sb.append(' ');
        }
        sb.append(str);
        return sb.toString();
    }
}