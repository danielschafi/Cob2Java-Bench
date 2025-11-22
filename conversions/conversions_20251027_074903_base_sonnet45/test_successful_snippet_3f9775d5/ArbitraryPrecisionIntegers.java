import java.math.BigInteger;

public class ArbitraryPrecisionIntegers {
    private static final int WINDOW_WIDTH = 20;

    public static void main(String[] args) {
        // 10 ** 19
        System.out.print("10 ** 19        : ");
        BigInteger result1 = calculatePower(10, 19);
        showAllOrPortion(result1);

        // 12345 ** 9
        System.out.print("12345 ** 9      : ");
        BigInteger result2 = calculatePower(12345, 9);
        showAllOrPortion(result2);

        // 5 ** 4 ** 3 ** 2
        System.out.print("5 ** 4 ** 3 ** 2: ");
        BigInteger result3 = calculateChainedPower();
        showAllOrPortion(result3);
    }

    private static BigInteger calculatePower(long base, long exponent) {
        BigInteger bigBase = BigInteger.valueOf(base);
        BigInteger result = bigBase.pow((int) exponent);
        return result;
    }

    private static BigInteger calculateChainedPower() {
        // 3 ** 2
        BigInteger result = BigInteger.valueOf(3).pow(2);
        long exponent = result.longValue();

        // 4 ** (3 ** 2)
        result = BigInteger.valueOf(4).pow((int) exponent);
        exponent = result.longValue();

        // 5 ** (4 ** (3 ** 2))
        result = BigInteger.valueOf(5).pow((int) exponent);
        return result;
    }

    private static void showAllOrPortion(BigInteger number) {
        String numberString = number.toString();
        int numberLength = numberString.length();

        System.out.print("GMP length: " + numberLength + ", ");
        System.out.println("strlen: " + numberLength);

        if (numberLength <= WINDOW_WIDTH) {
            System.out.println(numberString);
        } else {
            System.out.print(numberString.substring(0, WINDOW_WIDTH));
            int remainingLength = numberLength - WINDOW_WIDTH;
            
            if (remainingLength <= WINDOW_WIDTH) {
                System.out.println(numberString.substring(numberLength - remainingLength));
            } else {
                System.out.print("...");
                System.out.println(numberString.substring(numberLength - WINDOW_WIDTH));
            }
        }
    }
}