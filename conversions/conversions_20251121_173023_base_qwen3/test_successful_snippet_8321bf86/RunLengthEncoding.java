import java.util.Scanner;

public class RunLengthEncoding {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        
        String encoded = encode(inputStr.trim());
        System.out.println("Encoded: " + encoded);
        System.out.println("Decoded: " + decode(encoded));
        
        scanner.close();
    }
    
    public static String encode(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        
        StringBuilder encoded = new StringBuilder();
        char currentChar = str.charAt(0);
        int numChars = 1;
        
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != currentChar) {
                appendNumberAndChar(encoded, currentChar, numChars);
                currentChar = str.charAt(i);
                numChars = 1;
            } else {
                numChars++;
            }
        }
        
        appendNumberAndChar(encoded, currentChar, numChars);
        return encoded.toString();
    }
    
    private static void appendNumberAndChar(StringBuilder encoded, char ch, int count) {
        if (count > 1) {
            encoded.append(count);
        }
        encoded.append(ch);
    }
    
    public static String decode(String encoded) {
        StringBuilder decoded = new StringBuilder();
        int i = 0;
        
        while (i < encoded.length()) {
            char c = encoded.charAt(i);
            
            if (Character.isDigit(c)) {
                int count = 0;
                while (i < encoded.length() && Character.isDigit(encoded.charAt(i))) {
                    count = count * 10 + (encoded.charAt(i) - '0');
                    i++;
                }
                
                if (i < encoded.length()) {
                    char ch = encoded.charAt(i);
                    for (int j = 0; j < count; j++) {
                        decoded.append(ch);
                    }
                    i++;
                }
            } else {
                decoded.append(c);
                i++;
            }
        }
        
        return decoded.toString();
    }
}