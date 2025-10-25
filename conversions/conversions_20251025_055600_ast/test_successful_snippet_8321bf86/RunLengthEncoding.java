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
        int strLen = str.length();
        char currentChar = str.charAt(0);
        int numChars = 1;
        StringBuilder encoded = new StringBuilder();

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
        str.append(String.format("%03d", numChars)).append(charToEncode);
    }

    public static String decode(String encoded) {
        StringBuilder decoded = new StringBuilder();
        int encodedPos = 0;
        int numChars = 0;

        while (encodedPos < encoded.length() && !encoded.substring(encodedPos, Math.min(encodedPos + 2, encoded.length())).equals("  ")) {
            if (Character.isDigit(encoded.charAt(encodedPos))) {
                numChars = numChars * 10 + Character.getNumericValue(encoded.charAt(encodedPos));
            } else {
                for (int i = 0; i < numChars; i++) {
                    decoded.append(encoded.charAt(encodedPos));
                }
                numChars = 0;
            }
            encodedPos++;
        }
        return decoded.toString();
    }
}