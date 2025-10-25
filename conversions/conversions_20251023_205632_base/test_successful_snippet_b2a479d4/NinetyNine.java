import java.util.Arrays;

public class NinetyNine {
    private int counter;
    private boolean noBottlesLeft;
    private boolean oneBottleLeft;
    private int tens;
    private int digits;
    private String[] afterTenArray = {"", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private String[] digitArray = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private String numberName;
    private String stringified;
    private String outline;

    public static void main(String[] args) {
        NinetyNine program = new NinetyNine();
        program.run();
    }

    public void run() {
        for (counter = 99; counter >= 0; counter--) {
            outline = "";
            showNumber();
            outline += stringified + " of beer on the wall";
            System.out.println(outline);
            outline = "";
            outline += stringified + " of beer";
            System.out.println(outline);
            outline = "Take ";
            if (counter == 1) {
                outline += "it";
            } else {
                outline += "one";
            }
            outline += " down and pass it round";
            System.out.println(outline);
            outline = "";
            showNumber();
            outline += stringified + " of beer on the wall";
            System.out.println(outline);
            System.out.println();
        }
        System.out.println("No more bottles of beer on the wall");
        System.out.println("No more bottles of beer");
        System.out.println("Go to the store and buy some more");
        System.out.println("Ninety-Nine bottles of beer on the wall");
    }

    private void showNumber() {
        noBottlesLeft = counter == 0;
        oneBottleLeft = counter == 1;
        if (noBottlesLeft) {
            stringified = "No more|";
        } else {
            if (counter < 20) {
                stringified = digitArray[counter - 1] + "|";
            } else {
                if (counter < 100) {
                    numberName = "";
                    tens = counter / 10;
                    digits = counter % 10;
                    numberName += afterTenArray[tens - 1];
                    if (digits != 0) {
                        numberName += "-" + digitArray[digits - 1];
                    }
                    stringified = numberName + "|";
                }
            }
        }
        if (oneBottleLeft) {
            stringified += " bottle|";
        } else {
            stringified += " bottles|";
        }
    }
}