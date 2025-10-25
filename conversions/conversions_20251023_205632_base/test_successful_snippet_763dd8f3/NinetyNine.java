import java.util.Arrays;

public class NinetyNine {
    private int counter;
    private boolean noBottlesLeft;
    private boolean oneBottleLeft;
    private int tens;
    private int digits;
    private String[] afterTenArray = {"", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private String[] digitArray = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    public static void main(String[] args) {
        NinetyNine program = new NinetyNine();
        program.run();
    }

    public void run() {
        for (counter = 99; counter >= 0; counter--) {
            showNumber();
            System.out.print(" of beer on the wall\n");
            showNumber();
            System.out.print(" of beer\n");
            System.out.print("Take ");
            if (oneBottleLeft) {
                System.out.print("it ");
            } else {
                System.out.print("one ");
            }
            System.out.println("down and pass it round");
            counter--;
            showNumber();
            System.out.println(" of beer on the wall");
            counter++;
            System.out.println();
        }
        System.out.println("No more bottles of beer on the wall");
        System.out.println("No more bottles of beer");
        System.out.println("Go to the store and buy some more");
        System.out.println("Ninety Nine bottles of beer on the wall");
    }

    private void showNumber() {
        noBottlesLeft = (counter == 0);
        oneBottleLeft = (counter == 1);
        if (noBottlesLeft) {
            System.out.print("No more");
        } else {
            if (counter < 20) {
                System.out.print(digitArray[counter - 1]);
            } else {
                if (counter < 100) {
                    tens = counter / 10;
                    digits = counter % 10;
                    System.out.print(afterTenArray[tens - 1].trim() + (digits != 0 ? " " + digitArray[digits - 1].trim() : ""));
                }
            }
        }
        if (oneBottleLeft) {
            System.out.print(" bottle");
        } else {
            System.out.print(" bottles");
        }
    }
}