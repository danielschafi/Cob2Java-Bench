import java.lang.reflect.Method;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class ArbitraryPrecisionIntegers {
    
    private static class GmpNumber {
        public long mp_alloc;
        public long mp_size;
        public long mp_limb;
        
        public GmpNumber() {
            this.mp_alloc = 0;
            this.mp_size = 0;
            this.mp_limb = 0;
        }
    }
    
    private static final int WINDOW_WIDTH = 20;
    
    private static GmpNumber gmpNumber = new GmpNumber();
    private static GmpNumber gmpBuild = new GmpNumber();
    private static long theInt;
    private static long theExponent;
    private static boolean cantUse = false;
    private static String numberString;
    private static int numberLength;
    private static int limitWidth;
    private static byte[] numberBuffer = new byte[WINDOW_WIDTH];
    
    public static void main(String[] args) {
        arbitraryMain();
    }
    
    public static void arbitraryMain() {
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
    
    public static void initializeIntegers() {
        // Simulate __gmpz_init calls
        gmpNumber = new GmpNumber();
        gmpBuild = new GmpNumber();
    }
    
    public static void raisePowAccreteExponent() {
        if (cantUse) {
            System.err.println("Error: intermediate overflow occurred at " + theExponent);
            return;
        }
        
        // Simulate __gmpz_set_ui calls
        BigInteger bigInt = BigInteger.valueOf(theInt);
        gmpBuild.mp_limb = bigInt.longValue(); // Simplified
        
        // Simulate __gmpz_pow_ui calls
        BigInteger result = bigInt.pow((int) theExponent);
        gmpNumber.mp_limb = result.longValue(); // Simplified
        
        // Simulate __gmpz_get_ui calls
        theExponent = result.longValue(); // Simplified
        
        // Simulate __gmpz_fits_ulong_p calls
        validExponent = (result.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) <= 0) ? 1 : 0;
        cantUse = (validExponent == 0);
    }
    
    public static int validExponent = 1;
    
    public static void showAllOrPortion() {
        // Simulate __gmpz_sizeinbase calls
        String resultStr = String.valueOf(gmpNumber.mp_limb);
        numberLength = resultStr.length();
        System.out.print("GMP length: " + numberLength + ", ");
        
        // Simulate __gmpz_get_str calls
        numberString = resultStr;
        
        // Simulate strlen calls
        numberLength = numberString.length();
        System.out.println("strlen: " + numberLength);
        
        limitWidth = WINDOW_WIDTH;
        if (numberLength <= WINDOW_WIDTH) {
            limitWidth = numberLength;
            System.out.println(numberString.substring(0, limitWidth));
        } else {
            System.out.print(numberString);
            int remaining = numberLength - WINDOW_WIDTH;
            remaining = Math.max(0, remaining);
            if (remaining <= WINDOW_WIDTH) {
                limitWidth = remaining;
            } else {
                System.out.print("...");
            }
            // This part would involve more complex buffer handling
            // For simplicity, we just print the rest
            if (remaining > 0) {
                System.out.println(numberString.substring(numberLength - limitWidth));
            }
        }
    }
    
    public static void cleanUp() {
        numberString = null;
        // Reset structures
        gmpNumber = new GmpNumber();
        gmpBuild = new GmpNumber();
        cantUse = false;
    }
}