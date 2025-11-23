import java.util.*;

public class RunLengthEncoding {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        
        String encoded = encode(trim(inputStr));
        System.out.println("Encoded: " + trim(encoded));
        System.out.println("Decoded: " + trim(decode(encoded)));
    }
    
    public static String encode(String str) {
        int strLen = str.length();
        if (strLen == 0) return "";
        
        StringBuilder encoded = new StringBuilder();
        int encodedPos = 1;
        char currentChar = str.charAt(0);
        int numChars = 1;
        
        for (int i = 1; i < strLen; i++) {
            if (str.charAt(i) != currentChar) {
                encodedPos = addNumChars(encoded, encodedPos, currentChar, numChars);
                currentChar = str.charAt(i);
                numChars = 1;
            } else {
                numChars++;
            }
        }
        
        encodedPos = addNumChars(encoded, encodedPos, currentChar, numChars);
        return encoded.toString();
    }
    
    private static int addNumChars(StringBuilder str, int currentPos, char charToEncode, int numChars) {
        if (numChars > 1) {
            str.insert(currentPos - 1, String.format("%3d", numChars));
            currentPos += String.format("%3d", numChars).length();
        }
        str.insert(currentPos - 1, charToEncode);
        currentPos++;
        return currentPos;
    }
    
    public static String decode(String encoded) {
        StringBuilder decoded = new StringBuilder();
        int encodedPos = 0;
        int decodedPos = 0;
        int numOfChar = 0;
        
        while (encodedPos < encoded.length() && !encoded.substring(encodedPos, Math.min(encodedPos + 2, encoded.length())).equals("  ")) {
            if (Character.isDigit(encoded.charAt(encodedPos))) {
                numOfChar = numOfChar * 10 + Character.getNumericValue(encoded.charAt(encodedPos));
            } else {
                while (numOfChar > 0) {
                    decoded.insert(decodedPos, encoded.charAt(encodedPos));
                    decodedPos++;
                    numOfChar--;
                }
            }
            encodedPos++;
        }
        
        return decoded.toString();
    }
    
    private static String trim(String str) {
        return str.replaceAll("^\\s+|\\s+$", "");
    }
}