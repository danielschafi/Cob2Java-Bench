public class Overflowing {
    public static void main(String[] args) {
        byte bit8Sized = 0;
        short bit16Sized = 0;
        int bit32Sized = 0;
        long bit64Sized = 0;
        short bit8Unsigned = 0;
        
        long nebulousSize = 0;
        
        int pictureSized = 0;
        
        // 32 bit signed integer
        bit32Sized = (int) (0 - 2147483647);
        System.out.println(bit32Sized);
        
        int temp32 = bit32Sized - 1;
        if (temp32 < Integer.MIN_VALUE || temp32 > Integer.MAX_VALUE) {
            System.out.println("32bit signed SIZE ERROR");
        } else {
            bit32Sized = temp32;
        }
        System.out.println(bit32Sized);
        System.out.println();
        
        // 8 bit unsigned, size tested, invalid results discarded
        int tempUnsigned = 0 + (-257);
        if (tempUnsigned < 0 || tempUnsigned > 255) {
            System.out.println("bit8-unsigned SIZE ERROR");
        } else {
            bit8Unsigned = (short) tempUnsigned;
        }
        System.out.println(bit8Unsigned);
        
        // programmers can ignore the safety features
        bit8Unsigned = (short) (-257);
        System.out.print("you asked for it: ");
        System.out.println(bit8Unsigned & 0xFF);
        System.out.println();
        
        // fixed size
        pictureSized = 999;
        int tempPicture = pictureSized + 1;
        if (tempPicture < -999 || tempPicture > 999) {
            System.out.println("picture-sized SIZE ERROR");
        } else {
            pictureSized = tempPicture;
        }
        System.out.println(pictureSized);
        
        // programmers doing the following, inadvertently
        pictureSized = 999;
        pictureSized = pictureSized + 1;
        System.out.print("you asked for it: ");
        System.out.println(pictureSized % 1000);
        
        pictureSized = pictureSized + 1;
        System.out.print("really? you want to keep doing this?: ");
        System.out.println(pictureSized % 1000);
        System.out.println();
        
        // C values are undefined by spec, only minimums givens
        System.out.print("How many bytes in a C long? ");
        System.out.println("8, varies by platform");
        System.out.println("Regardless, ON SIZE ERROR will catch any invalid result");
        
        // on a 64bit machine, C long of 8 bytes
        long tempNebulous = 0xFFFFFFFFFFFFFFFFL + 1;
        if (tempNebulous < Long.MIN_VALUE || tempNebulous > Long.MAX_VALUE) {
            System.out.println("binary-c-long SIZE ERROR");
        } else {
            nebulousSize = tempNebulous;
        }
        System.out.println(nebulousSize);
        
        // value now goes to 1, no size error
        tempNebulous = nebulousSize + 1;
        if (tempNebulous < Long.MIN_VALUE || tempNebulous > Long.MAX_VALUE) {
            System.out.println("binary-c-long size error");
        } else {
            nebulousSize = tempNebulous;
        }
        System.out.print("error state is not persistent: ");
        System.out.println(nebulousSize);
    }
}