public class EULER {
    private static final int TABLE_LENGTH = 250;
    
    private static class Calc {
        int a;
        int b;
        int c;
        int d;
        long abcd;
        int fifthRootOffs;
    }
    
    private static long[] fifthPowerTable = new long[TABLE_LENGTH];
    private static boolean finishedSearching = false;
    
    public static void main(String[] args) {
        Calc calc = new Calc();
        finishedSearching = false;
        
        powersOfFiveTableInit();
        
        for (calc.a = 1; calc.a < TABLE_LENGTH; calc.a++) {
            for (calc.b = 1; calc.b < calc.a; calc.b++) {
                for (calc.c = 1; calc.c < calc.b; calc.c++) {
                    for (calc.d = 1; calc.d < calc.c; calc.d++) {
                        if (finishedSearching) {
                            return;
                        }
                        powerComputations(calc);
                    }
                }
            }
        }
    }
    
    private static void powerComputations(Calc calc) {
        calc.abcd = 0;
        
        calc.abcd = fifthPowerTable[calc.a - 1] +
                    fifthPowerTable[calc.b - 1] +
                    fifthPowerTable[calc.c - 1] +
                    fifthPowerTable[calc.d - 1];
        
        int index = binarySearch(fifthPowerTable, calc.abcd);
        
        if (index >= 0) {
            calc.fifthRootOffs = index + 1;
            displayPretty(calc);
            finishedSearching = true;
        }
    }
    
    private static int binarySearch(long[] array, long key) {
        int low = 0;
        int high = array.length - 1;
        
        while (low <= high) {
            int mid = (low + high) >>> 1;
            long midVal = array[mid];
            
            if (midVal < key) {
                low = mid + 1;
            } else if (midVal > key) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
    
    private static void displayPretty(Calc calc) {
        System.out.println(
            String.format("%3d", calc.a) + "^5 + " +
            String.format("%3d", calc.b) + "^5 + " +
            String.format("%3d", calc.c) + "^5 + " +
            String.format("%3d", calc.d) + "^5 = " +
            String.format("%3d", calc.fifthRootOffs) + "^5."
        );
    }
    
    private static void powersOfFiveTableInit() {
        for (int i = 1; i < TABLE_LENGTH; i++) {
            long power = i;
            fifthPowerTable[i - 1] = power * power * power * power * power;
        }
    }
}