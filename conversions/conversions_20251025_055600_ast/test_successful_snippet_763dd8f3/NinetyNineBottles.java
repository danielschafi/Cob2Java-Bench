import java.util.Arrays;

public class NinetyNineBottles {
    private static final String[] AFTER_TEN_ARRAY = {"", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] DIGIT_ARRAY = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    public static void main(String[] args) {
        for (int counter = 99; counter >= 0; counter--) {
            showNumber(counter);
            System.out.println(" of beer on the wall");
            showNumber(counter);
            System.out.println(" of beer");
            System.out.print("Take ");
            if (counter == 1) {
                System.out.print("it ");
            } else {
                System.out.print("one ");
            }
            System.out.println("down and pass it round");
            showNumber(counter - 1);
            System.out.println(" of beer on the wall");
            System.out.println();
        }
        System.out.println("No more bottles of beer on the wall");
        System.out.println("No more bottles of beer");
        System.out.println("Go to the store and buy some more");
        System.out.println("Ninety Nine bottles of beer on the wall");
    }

    private static void showNumber(int counter) {
        if (counter == 0) {
            System.out.print("No more");
        } else {
            if (counter < 20) {
                System.out.print(DIGIT_ARRAY[counter]);
            } else if (counter < 100) {
                int tens = counter / 10;
                int digits = counter % 10;
                System.out.print(AFTER_TEN_ARRAY[tens - 1] + (digits != 0 ? DIGIT_ARRAY[digits] : ""));
            }
        }
        if (counter == 1) {
            System.out.print(" bottle");
        } else {
            System.out.print(" bottles");
        }
    }
}