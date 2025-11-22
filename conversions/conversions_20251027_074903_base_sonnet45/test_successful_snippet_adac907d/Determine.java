public class Determine {
    public static void main(String[] args) {
        char tx;
        StringBuilder lower8bit = new StringBuilder();
        StringBuilder upper8bit = new StringBuilder();
        
        int lowSlide = 0;
        int highSlide = 0;
        
        for (int tally = 1; tally <= 256; tally++) {
            tx = (char) tally;
            
            if (Character.isLowerCase(tx)) {
                lowSlide++;
                lower8bit.append(tx);
            }
            
            if (Character.isUpperCase(tx)) {
                highSlide++;
                upper8bit.append(tx);
            }
        }
        
        if (lowSlide == 0) {
            System.err.println("no lower case letters detected");
        } else {
            System.out.println(lower8bit.toString());
        }
        
        if (highSlide == 0) {
            System.err.println("no upper case letters detected");
        } else {
            System.out.println(upper8bit.toString());
        }
    }
}