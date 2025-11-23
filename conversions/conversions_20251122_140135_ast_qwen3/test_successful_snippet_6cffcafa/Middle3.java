public class Middle3 {
    private static int[] items = {123, 12345, 1234567, 987654321, 10001, -10001, -123, -100, 100, -12345, 1, 2, -1, -10, 2002, -2002, 0};
    private static String result;
    private static int num;
    private static int numDisp;
    private static int div;
    private static int mod;
    private static int modDisp;
    private static int digitCounter;
    private static int digitDiv;
    private static int digitMod;
    private static int multiplier;

    public static void main(String[] args) {
        for (int i = 0; i < items.length; i++) {
            num = items[i];
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
        int digitDivResult = digitCounter / 2;
        int digitModResult = digitCounter % 2;
        if (digitModResult == 0) {
            result = "Even number of digits";
            return;
        }

        if (digitCounter > 3) {
            multiplier = (int) Math.round(Math.pow(10, ((digitCounter - 5) / 2.0) + 1));
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
        while (digitDiv != 0) {
            digitDiv = digitDiv / 10;
            digitCounter++;
        }
    }
}