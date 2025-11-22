public class TauFunction {
    private static int total;
    private static int n;
    private static int p;
    private static int pSquared;
    private static int fCount;
    private static int i;
    private static StringBuilder outStr;
    private static int outPtr;

    public static void main(String[] args) {
        outStr = new StringBuilder();
        outPtr = 1;
        
        for (i = 1; i <= 100; i++) {
            showTau();
        }
        
        if (outPtr > 1) {
            System.out.println(outStr.toString().trim());
        }
    }

    private static void showTau() {
        n = i;
        tau();
        
        String outItm = String.format("%3d", total).replace(' ', ' ');
        outStr.append(outItm);
        outPtr += 3;
        
        if (outPtr >= 61) {
            System.out.println(outStr.toString());
            outStr = new StringBuilder();
            outPtr = 1;
        }
    }

    private static void tau() {
        total = 1;
        
        while (isNEven()) {
            powerOf2();
        }
        
        pSquared = 0;
        for (p = 3; pSquared <= n; p += 2) {
            oddFactor();
        }
        
        if (n > 1) {
            total *= 2;
        }
    }

    private static boolean isNEven() {
        return (n % 2) == 0;
    }

    private static void powerOf2() {
        total += 1;
        n = n / 2;
    }

    private static void oddFactor() {
        pSquared = p * p;
        fCount = 1;
        
        while (true) {
            int remainder = n % p;
            if (remainder == 0) {
                n = n / p;
                fCount += 1;
            } else {
                break;
            }
        }
        
        total *= fCount;
    }
}