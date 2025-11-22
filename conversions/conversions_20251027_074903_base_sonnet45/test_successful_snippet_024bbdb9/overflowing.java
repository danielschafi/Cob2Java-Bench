public class overflowing {
    private static byte bit8Sized;
    private static short bit16Sized;
    private static int bit32Sized;
    private static long bit64Sized;
    private static short bit8Unsigned;
    private static long nebulousSize;
    private static int pictureSize;

    public static void main(String[] args) {
        boolean sizeError;

        bit32Sized = 0 - 2147483647;
        System.out.println(bit32Sized);

        sizeError = false;
        try {
            int temp = Math.subtractExact(bit32Sized, 1);
            bit32Sized = temp;
        } catch (ArithmeticException e) {
            sizeError = true;
            System.out.println("32bit signed SIZE ERROR");
        }
        System.out.println(bit32Sized);
        System.out.println();

        sizeError = false;
        try {
            int temp = 0 + (-257);
            if (temp < 0 || temp > 255) {
                throw new ArithmeticException();
            }
            bit8Unsigned = (short) temp;
        } catch (ArithmeticException e) {
            sizeError = true;
            System.out.println("bit8-unsigned SIZE ERROR");
        }
        System.out.println(bit8Unsigned & 0xFF);

        bit8Unsigned = (short) (-257 & 0xFF);
        System.out.println("you asked for it: " + (bit8Unsigned & 0xFF));
        System.out.println();

        pictureSize = 999;
        sizeError = false;
        try {
            int temp = pictureSize + 1;
            if (temp < -999 || temp > 999) {
                throw new ArithmeticException();
            }
            pictureSize = temp;
        } catch (ArithmeticException e) {
            sizeError = true;
            System.out.println("picture-sized SIZE ERROR");
        }
        System.out.println(formatPictureSize(pictureSize));

        pictureSize = 999;
        pictureSize = pictureSize + 1;
        pictureSize = truncateToPictureSize(pictureSize);
        System.out.println("you asked for it: " + formatPictureSize(pictureSize));

        pictureSize = pictureSize + 1;
        pictureSize = truncateToPictureSize(pictureSize);
        System.out.println("really? you want to keep doing this?: " + formatPictureSize(pictureSize));
        System.out.println();

        System.out.println("How many bytes in a C long? " + 8 + ", varies by platform");
        System.out.println("Regardless, ON SIZE ERROR will catch any invalid result");

        sizeError = false;
        try {
            long temp = Math.addExact(0xFFFFFFFFFFFFFFFFL, 1);
            nebulousSize = temp;
        } catch (ArithmeticException e) {
            sizeError = true;
            System.out.println("binary-c-long SIZE ERROR");
        }
        System.out.println(nebulousSize);

        sizeError = false;
        try {
            long temp = Math.addExact(nebulousSize, 1);
            nebulousSize = temp;
        } catch (ArithmeticException e) {
            sizeError = true;
            System.out.println("binary-c-long size error");
        }
        System.out.println("error state is not persistent: " + nebulousSize);
    }

    private static int truncateToPictureSize(int value) {
        String str = String.format("%04d", Math.abs(value));
        str = str.substring(str.length() - 3);
        int result = Integer.parseInt(str);
        return value < 0 ? -result : result;
    }

    private static String formatPictureSize(int value) {
        if (value >= 0) {
            return String.format("%03d", value);
        } else {
            return String.format("-%03d", Math.abs(value));
        }
    }
}