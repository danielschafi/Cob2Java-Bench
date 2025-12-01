import java.util.Scanner;

public class UNROMAN {
    private static final int[] romanValues = {1000, 500, 100, 50, 10, 5, 1};
    private static final char[] romanChars = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
    
    private int i, j, k, l;
    private char[] inpRomCh;
    private int[] inpRomDigit;
    private int wsNumber;
    
    public UNROMAN() {
        inpRomCh = new char[20];
        inpRomDigit = new int[20];
    }
    
    public static void main(String[] args) {
        UNROMAN program = new UNROMAN();
        program.run();
    }
    
    private void run() {
        Scanner scanner = new Scanner(System.in);
        String inpRoman;
        
        while (true) {
            inpRoman = scanner.nextLine();
            
            if (inpRoman.equals(" ") || inpRoman.isEmpty()) {
                break;
            }
            
            for (int idx = 0; idx < inpRomDigit.length; idx++) {
                inpRomDigit[idx] = 0;
            }
            
            for (i = 0; i < inpRoman.length() && inpRoman.charAt(i) != ' '; i++) {
                inpRomCh[i] = inpRoman.charAt(i);
                inpRomDigit[i] = 0;
                
                for (int rx = 0; rx < romanChars.length; rx++) {
                    if (romanChars[rx] == inpRomCh[i]) {
                        inpRomDigit[i] = rx + 1;
                        break;
                    }
                }
            }
            
            l = i - 1;
            wsNumber = 0;
            
            for (i = 0; i <= l && inpRomDigit[i] != 0; i++) {
                j = inpRomDigit[i] - 1;
                int k_idx = (i + 1 <= l) ? inpRomDigit[i + 1] - 1 : -1;
                
                if (k_idx >= 0 && romanValues[k_idx] > romanValues[j]) {
                    wsNumber = wsNumber - romanValues[j];
                } else {
                    wsNumber = wsNumber + romanValues[j];
                }
            }
            
            String wsNumberPic = formatNumber(wsNumber);
            
            System.out.println("----------");
            System.out.println("roman=" + inpRoman);
            System.out.println("arabic=" + wsNumberPic);
            
            if (i < l || wsNumber == 0) {
                System.out.println("invalid/incomplete roman numeral at pos " + i + " found " + (i < inpRoman.length() ? inpRoman.charAt(i) : ' '));
            }
        }
        
        scanner.close();
    }
    
    private String formatNumber(int num) {
        String result = String.valueOf(num);
        if (num < 0) {
            result = String.format("%5d-", num);
        } else {
            result = String.format("%5d", num);
        }
        return result;
    }
}