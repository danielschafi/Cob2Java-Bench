public class NinetyNine {
    private static int counter;
    private static int tens;
    private static int digits;
    private static String[] atens = new String[10];
    private static String[] adigits = new String[20];
    private static String stringified;
    private static String outline;
    private static int n;
    private static int r;

    public static void main(String[] args) {
        setup();
    }

    private static void setup() {
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
                outline += " it";
            } else {
                outline += " one";
            }
            outline += " down and pass it round";
            System.out.println(outline);
            outline = "";
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
            stringified = adigits[counter] + "|";
        } else {
            if (counter < 100) {
                String numberName = atens[tens] + " " + adigits[digits];
                stringified = numberName.trim();
                n = counter / 10;
                r = counter % 10;
                if (r != 0) {
                    stringified = stringified.replaceFirst(" ", "-");
                }
                stringified = stringified.replaceFirst(" ", "|");
            }
        }
        if (counter == 1) {
            stringified += " bottle|";
        } else {
            stringified += " bottles|";
        }
    }

    static {
        atens[0] = "       ";
        atens[1] = "       ";
        atens[2] = "Twenty ";
        atens[3] = "Thirty ";
        atens[4] = "Forty ";
        atens[5] = "Fifty ";
        atens[6] = "Sixty ";
        atens[7] = "Seventy ";
        atens[8] = "Eighty ";
        atens[9] = "Ninety ";

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
    }
}