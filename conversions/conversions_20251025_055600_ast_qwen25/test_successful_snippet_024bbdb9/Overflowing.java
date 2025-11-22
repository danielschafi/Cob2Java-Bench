import java.math.BigInteger;

public class Overflowing {
    public static void main(String[] args) {
        byte bit8Sized = 0;
        short bit16Sized = 0;
        int bit32Sized = 0;
        long bit64Sized = 0;
        short bit8Unsigned = 0;

        BigInteger nebulousSize = BigInteger.ZERO;
        int pictureSize = 0;

        bit32Sized = 0 - 2147483647;
        System.out.println(bit32Sized);

        try {
            bit32Sized = Math.subtractExact(bit32Sized, 1);
        } catch (ArithmeticException e) {
            System.out.println("32bit signed SIZE ERROR");
        }
        System.out.println(bit32Sized);
        System.out.println();

        try {
            bit8Unsigned = (short) Math.addExact(bit8Unsigned, -257);
        } catch (ArithmeticException e) {
            System.out.println("bit8-unsigned SIZE ERROR");
        }
        System.out.println(bit8Unsigned);

        bit8Unsigned = (short) -257;
        System.out.println("you asked for it: " + bit8Unsigned);
        System.out.println();

        pictureSize = 999;
        try {
            pictureSize = Math.addExact(pictureSize, 1);
        } catch (ArithmeticException e) {
            System.out.println("picture-sized SIZE ERROR");
        }
        System.out.println(pictureSize);

        pictureSize = 999;
        pictureSize = Math.addExact(pictureSize, 1);
        System.out.println("you asked for it: " + pictureSize);

        pictureSize = Math.addExact(pictureSize, 1);
        System.out.println("really? you want to keep doing this?: " + pictureSize);
        System.out.println();

        System.out.println("How many bytes in a C long? " + Long.BYTES + ", varies by platform");
        System.out.println("Regardless, ON SIZE ERROR will catch any invalid result");

        try {
            nebulousSize = nebulousSize.add(BigInteger.ONE).add(new BigInteger("ffffffffffffffff", 16));
        } catch (ArithmeticException e) {
            System.out.println("binary-c-long SIZE ERROR");
        }
        System.out.println(nebulousSize);

        try {
            nebulousSize = nebulousSize.add(BigInteger.ONE);
        } catch (ArithmeticException e) {
            System.out.println("binary-c-long size error");
        }
        System.out.println("error state is not persistent: " + nebulousSize);
    }
}