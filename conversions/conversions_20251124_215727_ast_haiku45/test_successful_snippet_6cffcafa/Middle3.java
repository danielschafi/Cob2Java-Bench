```java
public class Middle3 {
    private static int num;
    private static String numDisp;
    private static int div;
    private static int mod;
    private static int modDisp;
    private static int digitCounter;
    private static int digitDiv;
    private static int digitMod;
    private static int multiplier;
    private static String result;
    private static int[] items = {123, 12345, 1234567, 987654321, 10001, -10001, -123, -100, 100, -12345, 1, 2, -1, -10, 2002, -2002, 0};

    public static void main(String[] args) {
        for (int item = 0; item < items.length; item++) {
            num = items[item];
            numDisp = formatNumber(num);
            check();
            System.out.println(numDisp + " --> " + result);
            if (items[item] == 0) break;
        }
    }

    private static String formatNumber(int n) {
        return String.format("%9d", n);
    }

    private static void check() {
        if (num >= -99 && num <= 99) {
            result = "Number too small";
            return;
        }

        countDigits();
        digitDiv = digitCounter / 2;
        digitMod = digitCounter % 2;
        
        if (digitMod == 0) {
            result = "Even number of digits";
            return;
        }

        if (digitCounter > 3) {
            int exponent = ((digitCounter - 5) / 2) + 1;
            multiplier = (int) Math.pow(10, exponent);
            num = num / multiplier;
            div = num / 1000;
            mod = num % 1000;
            modDisp = mod;
        } else {
            modDisp = num;
        }
        
        result = String.format("%3d", modDisp).trim();
    }

    private static void countDigits() {
        digitCounter = 0;
        digitDiv = num;
        
        while (digitDiv != 0) {
            digitDiv = digitDiv / 10;
            digitMod = digitDiv % 10;
            digitCounter++;
        }
    }
}