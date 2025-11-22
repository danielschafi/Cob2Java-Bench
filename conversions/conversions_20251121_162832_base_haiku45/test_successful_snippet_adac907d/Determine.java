public class Determine {
    public static void main(String[] args) {
        String lowerEightBit = "";
        String upperEightBit = "";
        int lowSlide = 0;
        int highSlide = 0;

        for (int tally = 1; tally <= 256; tally++) {
            char tx = (char) tally;
            
            if (Character.isLowerCase(tx)) {
                lowSlide++;
                lowerEightBit += tx;
            }
            
            if (Character.isUpperCase(tx)) {
                highSlide++;
                upperEightBit += tx;
            }
        }

        if (lowSlide == 0) {
            System.err.println("no lower case letters detected");
        } else {
            System.out.println(lowerEightBit.substring(0, lowSlide));
        }

        if (highSlide == 0) {
            System.err.println("no upper case letters detected");
        } else {
            System.out.println(upperEightBit.substring(0, highSlide));
        }
    }
}