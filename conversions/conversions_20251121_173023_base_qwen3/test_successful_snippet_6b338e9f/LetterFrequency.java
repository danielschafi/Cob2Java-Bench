import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LetterFrequency {
    private static int aCnt = 0;
    private static int bCnt = 0;
    private static int cCnt = 0;
    private static int dCnt = 0;
    private static int eCnt = 0;
    private static int fCnt = 0;
    private static int gCnt = 0;
    private static int hCnt = 0;
    private static int iCnt = 0;
    private static int jCnt = 0;
    private static int kCnt = 0;
    private static int lCnt = 0;
    private static int mCnt = 0;
    private static int nCnt = 0;
    private static int oCnt = 0;
    private static int pCnt = 0;
    private static int qCnt = 0;
    private static int rCnt = 0;
    private static int sCnt = 0;
    private static int tCnt = 0;
    private static int uCnt = 0;
    private static int vCnt = 0;
    private static int wCnt = 0;
    private static int xCnt = 0;
    private static int yCnt = 0;
    private static int zCnt = 0;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("File.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String upperLine = line.toUpperCase();
                for (int i = 0; i < upperLine.length(); i++) {
                    char ch = upperLine.charAt(i);
                    switch (ch) {
                        case 'A': aCnt++; break;
                        case 'B': bCnt++; break;
                        case 'C': cCnt++; break;
                        case 'D': dCnt++; break;
                        case 'E': eCnt++; break;
                        case 'F': fCnt++; break;
                        case 'G': gCnt++; break;
                        case 'H': hCnt++; break;
                        case 'I': iCnt++; break;
                        case 'J': jCnt++; break;
                        case 'K': kCnt++; break;
                        case 'L': lCnt++; break;
                        case 'M': mCnt++; break;
                        case 'N': nCnt++; break;
                        case 'O': oCnt++; break;
                        case 'P': pCnt++; break;
                        case 'Q': qCnt++; break;
                        case 'R': rCnt++; break;
                        case 'S': sCnt++; break;
                        case 'T': tCnt++; break;
                        case 'U': uCnt++; break;
                        case 'V': vCnt++; break;
                        case 'W': wCnt++; break;
                        case 'X': xCnt++; break;
                        case 'Y': yCnt++; break;
                        case 'Z': zCnt++; break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Letter Frequency Distribution");
        System.out.println("-----------------------------");
        System.out.printf("A : %5d          N : %5d%n", aCnt, nCnt);
        System.out.printf("B : %5d          O : %5d%n", bCnt, oCnt);
        System.out.printf("C : %5d          P : %5d%n", cCnt, pCnt);
        System.out.printf("D : %5d          Q : %5d%n", dCnt, qCnt);
        System.out.printf("E : %5d          R : %5d%n", eCnt, rCnt);
        System.out.printf("F : %5d          S : %5d%n", fCnt, sCnt);
        System.out.printf("G : %5d          T : %5d%n", gCnt, tCnt);
        System.out.printf("H : %5d          U : %5d%n", hCnt, uCnt);
        System.out.printf("I : %5d          V : %5d%n", iCnt, vCnt);
        System.out.printf("J : %5d          W : %5d%n", jCnt, wCnt);
        System.out.printf("K : %5d          X : %5d%n", kCnt, xCnt);
        System.out.printf("L : %5d          Y : %5d%n", lCnt, yCnt);
        System.out.printf("M : %5d          Z : %5d%n", mCnt, zCnt);
    }
}