import java.util.Scanner;

public class RunLengthEncoding {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine().trim();
        
        String encoded = encode(inputStr);
        System.out.println("Encoded: " + encoded.trim());
        System.out.println("Decoded: " + decode(encoded).trim());
    }
    
    public static String encode(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        
        StringBuilder result = new StringBuilder();
        int strLen = str.length();
        char currentChar = str.charAt(0);
        int numChars = 1;
        
        for (int i = 1; i < strLen; i++) {
            if (str.charAt(i) != currentChar) {
                addNumChars(result, currentChar, numChars);
                currentChar = str.charAt(i);
                numChars = 1;
            } else {
                numChars++;
            }
        }
        
        addNumChars(result, currentChar, numChars);
        
        return result.toString();
    }
    
    private static void addNumChars(StringBuilder str, char charToEncode, int numChars) {
        str.append(numChars).append(charToEncode);
    }
    
    public static String decode(String encoded) {
        if (encoded == null || encoded.isEmpty()) {
            return "";
        }
        
        StringBuilder result = new StringBuilder();
        int numOfChar = 0;
        
        for (int encodedPos = 0; encodedPos < encoded.length(); encodedPos++) {
            char ch = encoded.charAt(encodedPos);
            
            if (Character.isDigit(ch)) {
                numOfChar = numOfChar * 10 + Character.getNumericValue(ch);
            } else {
                for (int i = 0; i < numOfChar; i++) {
                    result.append(ch);
                }
                numOfChar = 0;
            }
        }
        
        return result.toString();
    }
}