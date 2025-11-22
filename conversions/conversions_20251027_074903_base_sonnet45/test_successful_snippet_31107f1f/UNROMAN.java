import java.util.Scanner;

public class UNROMAN {
    private static class RomanEntry {
        int value;
        char character;
        
        RomanEntry(int value, char character) {
            this.value = value;
            this.character = character;
        }
    }
    
    private static final RomanEntry[] ROMAN_TABLE = {
        new RomanEntry(1000, 'M'),
        new RomanEntry(500, 'D'),
        new RomanEntry(100, 'C'),
        new RomanEntry(50, 'L'),
        new RomanEntry(10, 'X'),
        new RomanEntry(5, 'V'),
        new RomanEntry(1, 'I')
    };
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (scanner.hasNextLine()) {
            String inpRoman = scanner.nextLine();
            
            if (inpRoman.trim().isEmpty()) {
                break;
            }
            
            int[] inpRomanDigits = new int[20];
            int i;
            
            for (i = 0; i < inpRoman.length() && i < 20; i++) {
                char ch = inpRoman.charAt(i);
                if (ch == ' ') {
                    break;
                }
                
                int digitIndex = 0;
                boolean found = false;
                for (int rx = 0; rx < ROMAN_TABLE.length; rx++) {
                    if (ROMAN_TABLE[rx].character == ch) {
                        digitIndex = rx + 1;
                        found = true;
                        break;
                    }
                }
                inpRomanDigits[i] = found ? digitIndex : 0;
            }
            
            int l = i;
            int wsNumber = 0;
            
            for (i = 0; i < l && inpRomanDigits[i] != 0; i++) {
                int j = inpRomanDigits[i];
                int k = (i + 1 < 20) ? inpRomanDigits[i + 1] : 0;
                
                int currentValue = ROMAN_TABLE[j - 1].value;
                int nextValue = (k > 0) ? ROMAN_TABLE[k - 1].value : 0;
                
                if (nextValue > currentValue) {
                    wsNumber -= currentValue;
                } else {
                    wsNumber += currentValue;
                }
            }
            
            String wsNumberPic = String.format("%5d", wsNumber);
            if (wsNumber < 0) {
                wsNumberPic = String.format("%5d-", Math.abs(wsNumber));
            }
            
            System.out.println("----------");
            System.out.println("roman=" + inpRoman);
            System.out.println("arabic=" + wsNumberPic);
            
            if (i < l || wsNumber == 0) {
                char foundChar = (i < inpRoman.length()) ? inpRoman.charAt(i) : ' ';
                System.out.println("invalid/incomplete roman numeral at pos " + (i + 1) + 
                                   " found " + foundChar);
            }
        }
        
        scanner.close();
    }
}