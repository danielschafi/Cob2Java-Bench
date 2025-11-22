public class NinetyNine {
    private static int counter;
    private static final String[] ATENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] ADIGITS = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String NUMBER_NAME = new String(new char[15]);

    public static void main(String[] args) {
        for (counter = 99; counter != 0; counter--) {
            showNumber();
            System.out.print(" of beer on the wall");
            showNumber();
            System.out.print(" of beer");
            System.out.print("\nTake ");
            if (counter == 1) {
                System.out.print("it ");
            } else {
                System.out.print("one ");
            }
            System.out.print("down and pass it round");
            counter--;
            showNumber();
            System.out.print(" of beer on the wall\n");
            counter++;
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
            System.out.print(ADIGITS[counter]);
        } else if (counter < 100) {
            int tens = counter / 10;
            int digits = counter % 10;
            StringBuilder sb = new StringBuilder();
            sb.append(ATENS[tens]).append(" ").append(ADIGITS[digits]);
            System.out.print(sb.toString().trim());
        }
        if (counter == 1) {
            System.out.print(" bottle");
        } else {
            System.out.print(" bottles");
        }
    }
}