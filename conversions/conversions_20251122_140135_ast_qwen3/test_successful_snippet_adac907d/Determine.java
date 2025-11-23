import java.io.PrintStream;
import java.util.Arrays;

public class Determine {
    private static final char[] LOWER_8BIT = new char[256];
    private static final char[] UPPER_8BIT = new char[256];
    private static int lowSlide = 0;
    private static int highSlide = 0;

    public static void main(String[] args) {
        determining();
    }

    public static void determining() {
        lowSlide = 0;
        highSlide = 0;
        for (int tally = 1; tally <= 256; tally++) {
            char tx = charAt(tally);
            if (Character.isLowerCase(tx)) {
                lowSlide++;
                LOWER_8BIT[lowSlide - 1] = tx;
            }
            if (Character.isUpperCase(tx)) {
                highSlide++;
                UPPER_8BIT[highSlide - 1] = tx;
            }
        }
        if (lowSlide == 0) {
            System.err.println("no lower case letters detected");
        } else {
            System.out.print(new String(LOWER_8BIT, 0, lowSlide));
        }
        if (highSlide == 0) {
            System.err.println("no upper case letters detected");
        } else {
            System.out.print(new String(UPPER_8BIT, 0, highSlide));
        }
    }

    private static char charAt(int index) {
        return (char)(index & 0xFF);
    }
}