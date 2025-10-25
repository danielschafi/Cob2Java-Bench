import java.util.Arrays;

public class NinetyNine {
    private static final String[] AFTER_TEN_ARRAY = {"", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] DIGIT_ARRAY = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String SPACES = "          ";

    public static void main(String[] args) {
        for (int counter = 99; counter >= 0; counter--) {
            String outline = "";
            String stringified = showNumber(counter);
            outline = stringified + " of beer on the wall";
            System.out.println(outline);
            outline = stringified + " of beer";
            System.out.println(outline);
            outline = "Take ";
            if (counter == 1) {
                outline += "it";
            } else {
                outline += "one";
            }
            outline += " down and pass it round";
            System.out.println(outline);
            outline = showNumber(counter - 1) + " of beer on the wall";
            System.out.println(outline);
            System.out.println();
        }
        System.out.println("No more bottles of beer on the wall");
        System.out.println("No more bottles of beer");
        System.out.println("Go to the store and buy some more");
        System.out.println("Ninety-Nine bottles of beer on the wall");
    }

    private static String showNumber(int counter) {
        if (counter == 0) {
            return "No more";
        }
        String stringified;
        if (counter < 20) {
            stringified = DIGIT_ARRAY[counter];
        } else {
            int tens = counter / 10;
            int digits = counter % 10;
            stringified = AFTER_TEN_ARRAY[tens - 1] + (digits != 0 ? "-" + DIGIT_ARRAY[digits] : "");
        }
        return stringified + (counter == 1 ? " bottle" : " bottles");
    }
}