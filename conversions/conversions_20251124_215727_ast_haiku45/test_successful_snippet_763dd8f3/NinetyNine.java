public class NinetyNine {
    private int counter;
    private int tens;
    private int digits;
    private String numberName;
    
    private static final String[] atens = {
        "       ", "Twenty ", "Thirty ", "Forty  ", "Fifty  ",
        "Sixty  ", "Seventy", "Eighty ", "Ninety ", "       "
    };
    
    private static final String[] adigits = {
        "One      ", "Two      ", "Three    ", "Four     ", "Five     ",
        "Six      ", "Seven    ", "Eight    ", "Nine     ", "Ten      ",
        "Eleven   ", "Twelve   ", "Thirteen ", "Fourteen ", "Fifteen  ",
        "Sixteen  ", "Seventeen", "Eighteen ", "Nineteen ", "         "
    };
    
    public NinetyNine() {
        this.counter = 0;
        this.tens = 0;
        this.digits = 0;
        this.numberName = "";
    }
    
    private void updateTensAndDigits() {
        this.tens = this.counter / 10;
        this.digits = this.counter % 10;
    }
    
    private void showNumber() {
        if (counter == 0) {
            System.out.print("No more");
        } else if (counter < 20) {
            System.out.print(adigits[counter].trim());
        } else if (counter < 100) {
            updateTensAndDigits();
            numberName = (atens[tens].trim() + " " + adigits[digits].trim()).trim();
            System.out.print(numberName);
        }
        
        if (counter == 1) {
            System.out.print(" bottle");
        } else {
            System.out.print(" bottles");
        }
    }
    
    private void main() {
        for (counter = 99; counter >= 0; counter--) {
            showNumber();
            System.out.println(" of beer on the wall");
            showNumber();
            System.out.println(" of beer");
            System.out.print("Take ");
            if (counter == 1) {
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
    
    public static void main(String[] args) {
        NinetyNine program = new NinetyNine();
        program.main();
    }
}