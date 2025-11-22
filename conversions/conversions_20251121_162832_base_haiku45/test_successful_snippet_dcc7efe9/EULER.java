public class EULER {
    private static final int TABLE_LENGTH = 250;
    private int searchingFlag = 0;
    private int[] fifthPowerTable = new int[TABLE_LENGTH];
    
    private int a;
    private int b;
    private int c;
    private int d;
    private long abcd;
    private int fifthRootOffs;
    private int powerCounter;
    
    public static void main(String[] args) {
        EULER euler = new EULER();
        euler.mainParagraph();
    }
    
    private void mainParagraph() {
        searchingFlag = 0;
        powersOfFiveTableInit();
        
        for (a = 1; a < TABLE_LENGTH; a++) {
            if (searchingFlag == 1) break;
            for (b = 1; b <= a; b++) {
                if (searchingFlag == 1) break;
                for (c = 1; c <= b; c++) {
                    if (searchingFlag == 1) break;
                    for (d = 1; d <= c; d++) {
                        if (searchingFlag == 1) break;
                        powerComputations();
                    }
                }
            }
        }
    }
    
    private void powerComputations() {
        abcd = 0;
        
        abcd = fifthPowerTable[a - 1] + 
               fifthPowerTable[b - 1] + 
               fifthPowerTable[c - 1] + 
               fifthPowerTable[d - 1];
        
        for (int powerIndex = 0; powerIndex < TABLE_LENGTH; powerIndex++) {
            if (fifthPowerTable[powerIndex] == abcd) {
                fifthRootOffs = powerIndex + 1;
                String output = String.format("%3d^5 + %3d^5 + %3d^5 + %3d^5 = %3d^5.", 
                    a, b, c, d, fifthRootOffs);
                System.out.println(output);
                searchingFlag = 1;
                return;
            }
        }
    }
    
    private void powersOfFiveTableInit() {
        for (powerCounter = 1; powerCounter <= TABLE_LENGTH; powerCounter++) {
            long value = (long) powerCounter;
            fifthPowerTable[powerCounter - 1] = (int) (value * value * value * value * value);
        }
    }
}