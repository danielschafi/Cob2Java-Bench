public class Overflowing {
    private static int bit8Sized;
    private static short bit16Sized;
    private static int bit32Sized;
    private static long bit64Sized;
    private static int bit8Unsigned;
    private static long nebulousSize;
    private static int pictureSize;

    public static void main(String[] args) {
        // 32 bit signed integer
        bit32Sized = 0 - 2147483647;
        System.out.println(bit32Sized);

        try {
            bit32Sized = bit32Sized - 1;
        } catch (ArithmeticException e) {
            System.out.println("32bit signed SIZE ERROR");
        }
        // value was unchanged due to size error trap and trigger
        System.out.println(bit32Sized);
        System.out.println();

        // 8 bit unsigned, size tested, invalid results discarded
        try {
            bit8Unsigned = 0 + (-257);
        } catch (ArithmeticException e) {
            System.out.println("bit8-unsigned SIZE ERROR");
        }
        System.out.println(bit8Unsigned);

        // programmers can ignore the safety features
        bit8Unsigned = -257;
        System.out.println("you asked for it: " + bit8Unsigned);
        System.out.println();

        // fixed size
        pictureSize = 999;
        try {
            pictureSize = pictureSize + 1;
        } catch (ArithmeticException e) {
            System.out.println("picture-sized SIZE ERROR");
        }
        System.out.println(pictureSize);

        // programmers doing the following, inadvertently,
        //   do not stay employed at banks for long
        pictureSize = 999;
        pictureSize = pictureSize + 1;
        // intermediate goes to 1000, left end truncated on storage
        System.out.println("you asked for it: " + pictureSize);

        pictureSize = pictureSize + 1;
        System.out.println("really? you want to keep doing this?: " + pictureSize);
        System.out.println();

        // C values are undefined by spec, only minimums givens
        System.out.println("How many bytes in a C long? " + 
                          (Long.SIZE / 8) + ", varies by platform");
        System.out.println("Regardless, ON SIZE ERROR will catch any invalid result");

        // on a 64bit machine, C long of 8 bytes
        try {
            nebulousSize = 0xFFFFFFFFFFFFFFFFL + 1;
        } catch (ArithmeticException e) {
            System.out.println("binary-c-long SIZE ERROR");
        }
        System.out.println(nebulousSize);
        // value will still be in initial state, GnuCOBOL initializes to 0
        // value now goes to 1, no size error, that ship has sailed
        try {
            nebulousSize = nebulousSize + 1;
        } catch (ArithmeticException e) {
            System.out.println("binary-c-long size error");
        }
        System.out.println("error state is not persistent: " + nebulousSize);
    }
}