public class Determine {
    private static char tx;
    private static StringBuilder lower8bit = new StringBuilder();
    private static StringBuilder upper8bit = new StringBuilder();
    private static int lowSlide = 0;
    private static int highSlide = 0;

    public static void main(String[] args) {
        determining();
    }

    private static void determining() {
        lowSlide = 0;
        highSlide = 0;

        for (int tally = 1; tally <= 256; tally++) {
            tx = getChar(tally);
            
            if (Character.isLowerCase(tx)) {
                lowSlide++;
                if (lowSlide <= lower8bit.length()) {
                    lower8bit.setCharAt(lowSlide - 1, tx);
                } else {
                    lower8bit.append(tx);
                }
            }
            
            if (Character.isUpperCase(tx)) {
                highSlide++;
                if (highSlide <= upper8bit.length()) {
                    upper8bit.setCharAt(highSlide - 1, tx);
                } else {
                    upper8bit.append(tx);
                }
            }
        }

        if (lowSlide == 0) {
            System.err.println("no lower case letters detected");
        } else {
            System.out.println(lower8bit.substring(0, lowSlide));
        }

        if (highSlide == 0) {
            System.err.println("no upper case letters detected");
        } else {
            System.out.println(upper8bit.substring(0, highSlide));
        }
    }

    private static char getChar(int index) {
        if (index >= 1 && index <= 256) {
            return (char) index;
        }
        return '\0';
    }
}