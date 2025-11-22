public class middle3 {
    private static int num;
    private static int numDisp;
    private static int div;
    private static int mod;
    private static int modDisp;
    private static int digitCounter;
    private static int digitDiv;
    private static boolean noMoreDigits;
    private static int digitMod;
    private static boolean isEven;
    private static int multiplier;
    private static final int[] items = {123, 12345, 1234567, 987654321, 10001, -10001, -123, -100, 100, -12345, 1, 2, -1, -10, 2002, -2002, 0};
    private static String result;

    public static void main(String[] args) {
        for (int item = 0; item < items.length; item++) {
            num = items[item];
            numDisp = num;
            check();
            System.out.println(numDisp + " --> " + result);
        }
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
            multiplier = (int) Math.pow(10, ((digitCounter - 5) / 2) + 1);
            num = num / multiplier;
            div = num / 1000;
            mod = num % 1000;
            modDisp = mod;
        } else {
            modDisp = num;
        }
        result = String.valueOf(modDisp);
    }

    private static void countDigits() {
        digitCounter = 0;
        digitDiv = Math.abs(num);
        noMoreDigits = false;
        while (!noMoreDigits) {
            digitMod = digitDiv % 10;
            digitDiv = digitDiv / 10;
            digitCounter++;
            if (digitDiv == 0) {
                noMoreDigits = true;
            }
        }
    }
}