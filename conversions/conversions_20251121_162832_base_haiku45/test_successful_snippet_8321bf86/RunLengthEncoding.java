import java.util.Scanner;

public class RunLengthEncoding {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        scanner.close();
        
        String encoded = encode(inputStr.trim());
        System.out.println("Encoded: " + encoded.trim());
        System.out.println("Decoded: " + decode(encoded).trim());
    }
    
    public static String encode(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        
        StringBuilder encoded = new StringBuilder();
        int strLen = str.length();
        char currentChar = str.charAt(0);
        int numChars = 1;
        
        for (int i = 1; i < strLen; i++) {
            if (str.charAt(i) != currentChar) {
                addNumChars(encoded, currentChar, numChars);
                currentChar = str.charAt(i);
                numChars = 1;
            } else {
                numChars++;
            }
        }
        
        addNumChars(encoded, currentChar, numChars);
        
        return encoded.toString();
    }
    
    private static void addNumChars(StringBuilder str, char charToEncode, int numChars) {
        str.append(numChars);
        str.append(charToEncode);
    }
    
    public static String decode(String encoded) {
        StringBuilder decoded = new StringBuilder();
        int numOfChar = 0;
        
        for (int i = 0; i < encoded.length(); i++) {
            char c = encoded.charAt(i);
            
            if (Character.isDigit(c)) {
                numOfChar = numOfChar * 10 + Character.getNumericValue(c);
            } else {
                for (int j = 0; j < numOfChar; j++) {
                    decoded.append(c);
                }
                numOfChar = 0;
            }
        }
        
        return decoded.toString();
    }
}