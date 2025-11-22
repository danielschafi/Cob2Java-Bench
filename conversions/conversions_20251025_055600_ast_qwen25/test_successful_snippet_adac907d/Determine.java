import java.io.IOException;

public class Determine {
    public static void main(String[] args) {
        char tx;
        char[] lower8bit = new char[256];
        char[] upper8bit = new char[256];
        int lowSlide = 0;
        int highSlide = 0;

        for (int tally = 1; tally <= 256; tally++) {
            tx = (char) tally;
            if (Character.isLowerCase(tx)) {
                lowSlide++;
                lower8bit[lowSlide - 1] = tx;
            }
            if (Character.isUpperCase(tx)) {
                highSlide++;
                upper8bit[highSlide - 1] = tx;
            }
        }
        if (lowSlide == 0) {
            System.err.println("no lower case letters detected");
        } else {
            System.out.println(new String(lower8bit, 0, lowSlide));
        }
        if (highSlide == 0) {
            System.err.println("no upper case letters detected");
        } else {
            System.out.println(new String(upper8bit, 0, highSlide));
        }
    }
}