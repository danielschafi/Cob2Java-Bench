import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UNROMAN {
    private static final char[] inpRomCh = new char[20];
    private static final int[] inpRomDigit = new int[20];
    private static final int[] wsTblRomVal = {1000, 500, 100, 50, 10, 5, 1};
    private static final char[] wsTblRomCh = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
    private static String inpRoman;
    private static int i, j, k, l;
    private static int wsNumber;
    private static String wsNumberPic;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            inpRoman = reader.readLine();
            if (inpRoman == null || inpRoman.isEmpty()) {
                break;
            }
            
            // Clear inpRomanDigits
            for (int idx = 0; idx < 20; idx++) {
                inpRomDigit[idx] = 0;
            }

            // Convert Roman characters to digit indices
            i = 0;
            while (i < inpRoman.length() && inpRoman.charAt(i) != ' ') {
                char currentChar = inpRoman.charAt(i);
                boolean found = false;
                for (int rx = 0; rx < 7; rx++) {
                    if (wsTblRomCh[rx] == currentChar) {
                        inpRomDigit[i] = rx + 1;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    inpRomDigit[i] = 0;
                }
                i++;
            }

            l = i - 1;
            wsNumber = 0;
            i = 0;

            while (i < l && inpRomDigit[i] != 0) {
                j = inpRomDigit[i] - 1;
                k = inpRomDigit[i + 1] - 1;

                if (k >= 0 && wsTblRomVal[k] > wsTblRomVal[j]) {
                    wsNumber -= wsTblRomVal[j];
                } else {
                    wsNumber += wsTblRomVal[j];
                }
                i++;
            }

            wsNumberPic = String.format("%d", wsNumber);
            System.out.println("----------");
            System.out.println("roman=" + inpRoman);
            System.out.println("arabic=" + wsNumberPic);

            if (i < l || wsNumber == 0) {
                System.out.println("invalid/incomplete roman numeral at pos " + (i + 1) + " found " + inpRomCh[i]);
            }
        }
    }
}