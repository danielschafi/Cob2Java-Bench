import java.util.Scanner;

public class UNROMAN {
    private static final String[] ROMAN_TABLE = {"1000M", "0500D", "0100C", "0050L", "0010X", "0005V", "0001I"};
    private static final int[] ROMAN_VALUES = {1000, 500, 100, 50, 10, 5, 1};
    private static final char[] ROMAN_CHARS = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        
        while (!(input = scanner.nextLine()).isEmpty()) {
            char[] romanChars = new char[20];
            int[] romanDigits = new int[20];
            
            // Copy input to array
            for (int i = 0; i < input.length() && i < 20; i++) {
                romanChars[i] = input.charAt(i);
            }
            
            // Convert characters to digit indices
            for (int i = 0; i < 20; i++) {
                if (i >= input.length() || romanChars[i] == ' ') {
                    romanDigits[i] = 0;
                    break;
                }
                
                boolean found = false;
                for (int j = 0; j < ROMAN_CHARS.length; j++) {
                    if (romanChars[i] == ROMAN_CHARS[j]) {
                        romanDigits[i] = j + 1;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    romanDigits[i] = 0;
                }
            }
            
            int length = 0;
            for (int i = 0; i < 20; i++) {
                if (romanChars[i] == ' ' || i >= input.length()) break;
                length++;
            }
            
            int number = 0;
            int i = 0;
            
            while (i < length && romanDigits[i] != 0) {
                int j = romanDigits[i] - 1;
                int k = (i + 1 < length) ? romanDigits[i + 1] - 1 : -1;
                
                if (k >= 0 && ROMAN_VALUES[k] > ROMAN_VALUES[j]) {
                    number -= ROMAN_VALUES[j];
                } else {
                    number += ROMAN_VALUES[j];
                }
                
                i++;
            }
            
            System.out.println("----------");
            System.out.println("roman=" + input);
            System.out.printf("arabic=%5d%n", number);
            
            if (i < length || number == 0) {
                System.out.println("invalid/incomplete roman numeral at pos " + (i + 1) + 
                                 " found " + romanChars[i]);
            }
        }
        
        scanner.close();
    }
}