public class NinetyNine {
    private static int counter;
    private static int tens;
    private static int digits;
    private static String[] atens = new String[10];
    private static String[] adigits = new String[20];
    private static String numberName;

    public static void main(String[] args) {
        atens[0] = "       ";
        atens[1] = "Twenty ";
        atens[2] = "Thirty ";
        atens[3] = "Forty  ";
        atens[4] = "Fifty  ";
        atens[5] = "Sixty  ";
        atens[6] = "Seventy";
        atens[7] = "Eighty ";
        atens[8] = "Ninety ";
        atens[9] = "       ";

        adigits[0] = "One";
        adigits[1] = "Two";
        adigits[2] = "Three";
        adigits[3] = "Four";
        adigits[4] = "Five";
        adigits[5] = "Six";
        adigits[6] = "Seven";
        adigits[7] = "Eight";
        adigits[8] = "Nine";
        adigits[9] = "Ten";
        adigits[10] = "Eleven";
        adigits[11] = "Twelve";
        adigits[12] = "Thirteen";
        adigits[13] = "Fourteen";
        adigits[14] = "Fifteen";
        adigits[15] = "Sixteen";
        adigits[16] = "Seventeen";
        adigits[17] = "Eighteen";
        adigits[18] = "Nineteen";
        adigits[19] = "       ";

        for (counter = 99; counter != 0; counter--) {
            showNumber();
            System.out.print(" of beer on the wall");
            showNumber();
            System.out.print(" of beer");
            System.out.print("Take ");
            if (counter == 1) {
                System.out.print("it ");
            } else {
                System.out.print("one ");
            }
            System.out.println("down and pass it round");
            counter--;
            showNumber();
            System.out.print(" of beer on the wall");
            counter++;
            System.out.println();
        }

        System.out.println("No more bottles of beer on the wall");
        System.out.println("No more bottles of beer");
        System.out.println("Go to the store and buy some more");
        System.out.println("Ninety Nine bottles of beer on the wall");
    }

    private static void showNumber() {
        if (counter == 0) {
            System.out.print("No more");
        } else if (counter < 20) {
            System.out.print(adigits[counter - 1]);
        } else {
            tens = counter / 10;
            digits = counter % 10;
            numberName = atens[tens] + " " + adigits[digits - 1];
            System.out.print(numberName.trim());
        }
        if (counter == 1) {
            System.out.print(" bottle");
        } else {
            System.out.print(" bottles");
        }
    }
}