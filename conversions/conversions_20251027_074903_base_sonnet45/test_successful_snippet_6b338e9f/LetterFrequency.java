import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LetterFrequency {
    
    private static class LetterCnt {
        int aCnt = 0;
        int bCnt = 0;
        int cCnt = 0;
        int dCnt = 0;
        int eCnt = 0;
        int fCnt = 0;
        int gCnt = 0;
        int hCnt = 0;
        int iCnt = 0;
        int jCnt = 0;
        int kCnt = 0;
        int lCnt = 0;
        int mCnt = 0;
        int nCnt = 0;
        int oCnt = 0;
        int pCnt = 0;
        int qCnt = 0;
        int rCnt = 0;
        int sCnt = 0;
        int tCnt = 0;
        int uCnt = 0;
        int vCnt = 0;
        int wCnt = 0;
        int xCnt = 0;
        int yCnt = 0;
        int zCnt = 0;
    }
    
    public static void main(String[] args) {
        LetterCnt letterCnt = new LetterCnt();
        String recordName;
        boolean eof = false;
        
        try (BufferedReader reader = new BufferedReader(new FileReader("File.txt"))) {
            while (!eof) {
                recordName = reader.readLine();
                if (recordName == null) {
                    eof = true;
                } else {
                    String upperRecord = recordName.toUpperCase();
                    
                    for (char c : upperRecord.toCharArray()) {
                        switch (c) {
                            case 'A': letterCnt.aCnt++; break;
                            case 'B': letterCnt.bCnt++; break;
                            case 'C': letterCnt.cCnt++; break;
                            case 'D': letterCnt.dCnt++; break;
                            case 'E': letterCnt.eCnt++; break;
                            case 'F': letterCnt.fCnt++; break;
                            case 'G': letterCnt.gCnt++; break;
                            case 'H': letterCnt.hCnt++; break;
                            case 'I': letterCnt.iCnt++; break;
                            case 'J': letterCnt.jCnt++; break;
                            case 'K': letterCnt.kCnt++; break;
                            case 'L': letterCnt.lCnt++; break;
                            case 'M': letterCnt.mCnt++; break;
                            case 'N': letterCnt.nCnt++; break;
                            case 'O': letterCnt.oCnt++; break;
                            case 'P': letterCnt.pCnt++; break;
                            case 'Q': letterCnt.qCnt++; break;
                            case 'R': letterCnt.rCnt++; break;
                            case 'S': letterCnt.sCnt++; break;
                            case 'T': letterCnt.tCnt++; break;
                            case 'U': letterCnt.uCnt++; break;
                            case 'V': letterCnt.vCnt++; break;
                            case 'W': letterCnt.wCnt++; break;
                            case 'X': letterCnt.xCnt++; break;
                            case 'Y': letterCnt.yCnt++; break;
                            case 'Z': letterCnt.zCnt++; break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
        
        System.out.println("Letter Frequency Distribution");
        System.out.println("-----------------------------");
        System.out.println(String.format("A : %5d          N : %5d", letterCnt.aCnt, letterCnt.nCnt));
        System.out.println(String.format("B : %5d          O : %5d", letterCnt.bCnt, letterCnt.oCnt));
        System.out.println(String.format("C : %5d          P : %5d", letterCnt.cCnt, letterCnt.pCnt));
        System.out.println(String.format("D : %5d          Q : %5d", letterCnt.dCnt, letterCnt.qCnt));
        System.out.println(String.format("E : %5d          R : %5d", letterCnt.eCnt, letterCnt.rCnt));
        System.out.println(String.format("F : %5d          S : %5d", letterCnt.fCnt, letterCnt.sCnt));
        System.out.println(String.format("G : %5d          T : %5d", letterCnt.gCnt, letterCnt.tCnt));
        System.out.println(String.format("H : %5d          U : %5d", letterCnt.hCnt, letterCnt.uCnt));
        System.out.println(String.format("I : %5d          V : %5d", letterCnt.iCnt, letterCnt.vCnt));
        System.out.println(String.format("J : %5d          W : %5d", letterCnt.jCnt, letterCnt.wCnt));
        System.out.println(String.format("K : %5d          X : %5d", letterCnt.kCnt, letterCnt.xCnt));
        System.out.println(String.format("L : %5d          Y : %5d", letterCnt.lCnt, letterCnt.yCnt));
        System.out.println(String.format("M : %5d          Z : %5d", letterCnt.mCnt, letterCnt.zCnt));
    }
}