import java.util.Scanner;

public class IsNumericTest {
    
    private String wsUserInput;
    private String wsUserInputJustified;
    
    public IsNumericTest() {
        wsUserInput = "";
        wsUserInputJustified = "";
    }
    
    public static void main(String[] args) {
        IsNumericTest program = new IsNumericTest();
        program.mainProcedure();
    }
    
    private void mainProcedure() {
        processPlain();
        processZeroFill();
        processTrim();
    }
    
    private void processPlain() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("(plain) Enter a value: ");
        wsUserInput = scanner.nextLine();
        
        if (wsUserInput.length() > 10) {
            wsUserInput = wsUserInput.substring(0, 10);
        } else {
            wsUserInput = String.format("%-10s", wsUserInput);
        }
        
        if (isNumeric(wsUserInput)) {
            System.out.println(wsUserInput + " is numeric!");
        } else {
            System.out.println(wsUserInput + " is not numeric.");
        }
    }
    
    private void processZeroFill() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("(right justify, zero fill) Enter another value: ");
        String input = scanner.nextLine();
        
        if (input.length() > 10) {
            wsUserInputJustified = input.substring(input.length() - 10);
        } else {
            wsUserInputJustified = String.format("%10s", input);
        }
        
        wsUserInputJustified = wsUserInputJustified.replaceFirst("^\\s+", 
            new String(new char[wsUserInputJustified.length() - wsUserInputJustified.replaceFirst("^\\s+", "").length()]).replace('\0', '0'));
        
        if (isNumeric(wsUserInputJustified)) {
            System.out.println(wsUserInputJustified + " is numeric!");
        } else {
            System.out.println(wsUserInputJustified + " is not numeric.");
        }
    }
    
    private void processTrim() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("(trim) Enter a third value: ");
        wsUserInput = scanner.nextLine();
        
        if (wsUserInput.length() > 10) {
            wsUserInput = wsUserInput.substring(0, 10);
        } else {
            wsUserInput = String.format("%-10s", wsUserInput);
        }
        
        String trimmed = wsUserInput.trim();
        
        if (isNumeric(trimmed)) {
            System.out.println(trimmed + " is numeric!");
        } else {
            System.out.println(trimmed + " is not numeric.");
        }
    }
    
    private boolean isNumeric(String str) {
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