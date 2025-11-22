import java.io.IOException;

public class Determine {
    public static void main(String[] args) {
        char[] tx = new char[1];
        char[] lower8bit = new char[256];
        char[] upper8bit = new char[256];
        int lowSlide = 0;
        int highSlide = 0;

        for (int tally = 1; tally <= 256; tally++) {
            tx[0] = (char) tally;
            if (Character.isLowerCase(tx[0])) {
                lowSlide++;
                lower8bit[lowSlide - 1] = tx[0];
            }
            if (Character.isUpperCase(tx[0])) {
                highSlide++;
                upper8bit[highSlide - 1] = tx[0];
            }
        }
        if (lowSlide == 0) {
            try {
                System.err.write("no lower case letters detected\n".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(new String(lower8bit, 0, lowSlide));
        }
        if (highSlide == 0) {
            try {
                System.err.write("no upper case letters detected\n".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(new String(upper8bit, 0, highSlide));
        }
    }
}