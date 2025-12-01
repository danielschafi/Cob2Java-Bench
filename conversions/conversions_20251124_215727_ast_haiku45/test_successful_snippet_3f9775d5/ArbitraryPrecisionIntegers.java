import java.math.BigInteger;

public class ArbitraryPrecisionIntegers {
    private static final int WINDOW_WIDTH = 20;

    public static void main(String[] args) {
        test(10, 19, "10 ** 19        : ");
        test(12345, 9, "12345 ** 9      : ");
        testChained();
    }

    private static void test(long base, long exponent, String label) {
        System.out.print(label);
        BigInteger result = BigInteger.valueOf(base).pow((int) exponent);
        displayResult(result);
    }

    private static void testChained() {
        System.out.print("5 ** 4 ** 3 ** 2: ");
        BigInteger result = BigInteger.valueOf(3).pow(2);
        result = BigInteger.valueOf(4).pow(result.intValue());
        result = BigInteger.valueOf(5).pow(result.intValue());
        displayResult(result);
    }

    private static void displayResult(BigInteger result) {
        String resultStr = result.toString();
        int length = resultStr.length();

        System.out.print("GMP length: " + length + ", ");
        System.out.println("strlen: " + length);

        if (length <= WINDOW_WIDTH) {
            System.out.println(resultStr);
        } else {
            String firstPart = resultStr.substring(0, WINDOW_WIDTH);
            System.out.print(firstPart);

            int remaining = length - WINDOW_WIDTH;
            if (remaining <= WINDOW_WIDTH) {
                String lastPart = resultStr.substring(WINDOW_WIDTH);
                System.out.println(lastPart);
            } else {
                System.out.print("...");
                int offset = Math.max(WINDOW_WIDTH, remaining);
                String lastPart = resultStr.substring(offset);
                System.out.println(lastPart);
            }
        }
    }
}