```java
public class Overflowing {
    private byte bit8Sized;
    private short bit16Sized;
    private int bit32Sized;
    private long bit64Sized;
    private short bit8Unsigned;
    private long nebulousSize;
    private int pictureSize;

    public static void main(String[] args) {
        Overflowing program = new Overflowing();
        program.run();
    }

    public void run() {
        bit32Sized = 0 - 2147483647;
        System.out.println(bit32Sized);

        try {
            bit32Sized = bit32Sized - 1;
        } catch (ArithmeticException e) {
            System.out.println("32bit signed SIZE ERROR");
        }
        System.out.println(bit32Sized);
        System.out.println(" ");

        try {
            bit8Unsigned = (short) (0 + (-257));
            if (bit8Unsigned < 0 || bit8Unsigned > 255) {
                System.out.println("bit8-unsigned SIZE ERROR");
                bit8Unsigned = 0;
            }
        } catch (Exception e) {
            System.out.println("bit8-unsigned SIZE ERROR");
        }
        System.out.println(bit8Unsigned);

        bit8Unsigned = (short) (-257 & 0xFF);
        System.out.print("you asked for it: ");
        System.out.println(bit8Unsigned);
        System.out.println(" ");

        pictureSize = 999;
        try {
            int temp = pictureSize + 1;
            if (temp > 999 || temp < -999) {
                System.out.println("picture-sized SIZE ERROR");
            } else {
                pictureSize = temp;
            }
        } catch (Exception e) {
            System.out.println("picture-sized SIZE ERROR");
        }
        System.out.println(pictureSize);

        pictureSize = 999;
        pictureSize = pictureSize + 1;
        System.out.print("you asked for it: ");
        System.out.println(pictureSize);

        pictureSize = pictureSize + 1;
        System.out.print("really? you want to keep doing this?: ");
        System.out.println(pictureSize);
        System.out.println(" ");

        System.out.print("How many bytes in a C long? ");
        System.out.print(8);
        System.out.println(", varies by platform");
        System.out.println("Regardless, ON SIZE ERROR will catch any invalid result");

        try {
            long hexValue = 0xFFFFFFFFFFFFFFFFL;
            long result = 1 + hexValue;
            if (result < Long.MIN_VALUE || result > Long.MAX_VALUE) {
                System.out.println("binary-c-long SIZE ERROR");
            } else {
                nebulousSize = result;
            }
        } catch (Exception e) {
            System.out.println("binary-c-long SIZE ERROR");
        }
        System.out.println(nebulousSize);

        try {
            nebulousSize = nebulousSize + 1;
        } catch (Exception e) {
            System.out.println("binary-c-long size error");
        }
        System.out.print("error state is not persistent: ");
        System.out.println(nebulousSize);
    }
}