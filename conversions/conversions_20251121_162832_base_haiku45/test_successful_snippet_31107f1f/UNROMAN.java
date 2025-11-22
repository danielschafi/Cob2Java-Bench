import java.util.Scanner;

public class UNROMAN {
    static class TableEntry {
        int value;
        char ch;
        
        TableEntry(int value, char ch) {
            this.value = value;
            this.ch = ch;
        }
    }
    
    static TableEntry[] wsTableRoman = {
        new TableEntry(1000, 'M'),
        new TableEntry(500, 'D'),
        new TableEntry(100, 'C'),
        new TableEntry(50, 'L'),
        new TableEntry(10, 'X'),
        new TableEntry(5, 'V'),
        new TableEntry(1, 'I')
    };
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inpRoman;
        
        while (true) {
            inpRoman = scanner.nextLine();
            
            if (inpRoman.isEmpty() || inpRoman.equals(" ")) {
                break;
            }
            
            int[] inpRomDigit = new int[20];
            for (int idx = 0; idx < 20; idx++) {
                inpRomDigit[idx] = 0;
            }
            
            int i;
            for (i = 0; i < inpRoman.length() && i < 20; i++) {
                char ch = inpRoman.charAt(i);
                if (ch == ' ') {
                    break;
                }
                
                int found = -1;
                for (int rx = 0; rx < wsTableRoman.length; rx++) {
                    if (wsTableRoman[rx].ch == ch) {
                        found = rx + 1;
                        break;
                    }
                }
                
                if (found == -1) {
                    inpRomDigit[i] = 0;
                } else {
                    inpRomDigit[i] = found;
                }
            }
            
            int l = i;
            int wsNumber = 0;
            
            for (i = 0; i < l && inpRomDigit[i] != 0; i++) {
                int j = inpRomDigit[i] - 1;
                int k = (i + 1 < l) ? inpRomDigit[i + 1] - 1 : -1;
                
                if (k >= 0 && wsTableRoman[k].value > wsTableRoman[j].value) {
                    wsNumber = wsNumber - wsTableRoman[j].value;
                } else {
                    wsNumber = wsNumber + wsTableRoman[j].value;
                }
            }
            
            String wsNumberPic = String.format("%5d", wsNumber).replace(' ', ' ');
            if (wsNumber < 0) {
                wsNumberPic = String.format("%5d-", wsNumber);
            } else {
                wsNumberPic = String.format("%5d", wsNumber);
            }
            
            System.out.println("----------");
            System.out.println("roman=" + inpRoman);
            System.out.println("arabic=" + wsNumberPic.trim());
            
            if (i < l || wsNumber == 0) {
                char foundCh = (i < inpRoman.length()) ? inpRoman.charAt(i) : ' ';
                System.out.println("invalid/incomplete roman numeral at pos " + (i + 1) + " found " + foundCh);
            }
        }
        
        scanner.close();
    }
}