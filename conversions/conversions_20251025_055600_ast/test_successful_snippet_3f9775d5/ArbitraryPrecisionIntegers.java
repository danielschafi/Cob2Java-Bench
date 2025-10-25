import java.math.BigInteger;

public class ArbitraryPrecisionIntegers {
    private static final int WINDOW_WIDTH = 20;
    private static BigInteger gmpNumber;
    private static BigInteger gmpBuild;
    private static long theInt;
    private static long theExponent;
    private static boolean cantUse;
    private static String numberString;
    private static long numberLength;
    private static long limitWidth;
    private static char[] numberBuffer = new char[WINDOW_WIDTH];

    public static void main(String[] args) {
        arbitraryMain();
    }

    private static void arbitraryMain() {
        initializeIntegers();
        System.out.print("10 ** 19        : ");
        theInt = 10;
        theExponent = 19;
        raisePowAccreteExponent();
        showAllOrPortion();
        cleanUp();

        initializeIntegers();
        System.out.print("12345 ** 9      : ");
        theInt = 12345;
        theExponent = 9;
        raisePowAccreteExponent();
        showAllOrPortion();
        cleanUp();

        initializeIntegers();
        System.out.print("5 ** 4 ** 3 ** 2: ");
        theInt = 3;
        theExponent = 2;
        raisePowAccreteExponent();
        theInt = 4;
        raisePowAccreteExponent();
        theInt = 5;
        raisePowAccreteExponent();
        showAllOrPortion();
        cleanUp();
    }

    private static void initializeIntegers() {
        gmpNumber = BigInteger.ZERO;
        gmpBuild = BigInteger.ZERO;
        cantUse = false;
    }

    private static void raisePowAccreteExponent() {
        if (cantUse) {
            System.err.println("Error: intermediate overflow occurred at " + theExponent);
            System.exit(1);
        }
        gmpNumber = BigInteger.ZERO;
        gmpBuild = BigInteger.valueOf(theInt);
        gmpNumber = gmpBuild.pow((int) theExponent);
        gmpBuild = BigInteger.ZERO;
        theExponent = gmpNumber.longValue();
        cantUse = gmpNumber.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0;
    }

    private static void showAllOrPortion() {
        numberLength = gmpNumber.toString().length();
        System.out.print("GMP length: " + numberLength + ", ");
        numberString = gmpNumber.toString();
        numberLength = numberString.length();
        System.out.println("strlen: " + numberLength);

        limitWidth = WINDOW_WIDTH;
        if (numberLength <= WINDOW_WIDTH) {
            limitWidth = numberLength;
            System.out.println(numberString.substring(0, (int) limitWidth));
        } else {
            System.out.print(numberString);
            numberLength -= WINDOW_WIDTH;
            numberLength = Math.max(0, numberLength);
            if (numberLength <= WINDOW_WIDTH) {
                limitWidth = numberLength;
            } else {
                System.out.print("...");
            }
            System.out.println(numberString.substring((int) (numberString.length() - limitWidth)));
        }
    }

    private static void cleanUp() {
        numberString = null;
        gmpNumber = BigInteger.ZERO;
        gmpBuild = BigInteger.ZERO;
        numberBuffer = new char[WINDOW_WIDTH];
        cantUse = false;
    }
}