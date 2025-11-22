public class NinetyNine {
    private static int counter;
    private static final String[] ATENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] ADIGITS = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static String stringified;
    private static String outline;

    public static void main(String[] args) {
        for (counter = 99; counter >= 0; counter--) {
            outline = "";
            showNumber();
            outline += stringified + " of beer on the wall";
            System.out.println(outline);
            
            outline = "";
            outline += stringified + " of beer";
            System.out.println(outline);
            
            outline = "Take";
            if (counter == 1) {
                outline += " it down and pass it round";
            } else {
                outline += " one down and pass it round";
            }
            System.out.println(outline);
            
            counter--;
            showNumber();
            outline += stringified + " of beer on the wall";
            System.out.println(outline);
            counter++;
            System.out.println();
        }
        
        System.out.println("No more bottles of beer on the wall");
        System.out.println("No more bottles of beer");
        System.out.println("Go to the store and buy some more");
        System.out.println("Ninety-Nine bottles of beer on the wall");
    }
    
    private static void showNumber() {
        if (counter == 0) {
            stringified = "No more|";
        } else if (counter < 20) {
            stringified = ADIGITS[counter] + "|";
        } else {
            int tens = counter / 10;
            int digits = counter % 10;
            StringBuilder sb = new StringBuilder();
            sb.append(ATENS[tens]).append(" ").append(ADIGITS[digits]);
            stringified = sb.toString().trim() + "|";
            if (digits != 0) {
                stringified = stringified.replaceFirst(" ", "-");
            }
        }
        
        if (counter == 1) {
            stringified += " bottle|";
        } else {
            stringified += " bottles|";
        }
    }
}