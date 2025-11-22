```java
public class NinetyNine {
    private static int counter;
    private static String[] atens = {
        "       ",
        "Twenty",
        "Thirty",
        "Forty ",
        "Fifty ",
        "Sixty ",
        "Seventy",
        "Eighty",
        "Ninety",
        "       "
    };
    
    private static String[] adigits = {
        "One      ",
        "Two      ",
        "Three    ",
        "Four     ",
        "Five     ",
        "Six      ",
        "Seven    ",
        "Eight    ",
        "Nine     ",
        "Ten      ",
        "Eleven   ",
        "Twelve   ",
        "Thirteen ",
        "Fourteen ",
        "Fifteen  ",
        "Sixteen  ",
        "Seventeen",
        "Eighteen ",
        "Nineteen ",
        "         "
    };
    
    private static String stringified;
    private static String outline;
    private static int n;
    private static int r;
    
    public static void main(String[] args) {
        main100();
    }
    
    private static void main100() {
        for (counter = 99; counter >= 0; counter--) {
            outline = "";
            showNumber();
            outline = stringified.trim() + " of beer on the wall";
            System.out.println(outline);
            
            outline = "";
            outline = stringified.trim() + " of beer";
            System.out.println(outline);
            
            outline = "";
            outline = "Take ";
            if (counter == 1) {
                outline += "it down and pass it round";
            } else {
                outline += "one down and pass it round";
            }
            System.out.println(outline);
            
            outline = "";
            counter--;
            showNumber();
            outline = stringified.trim() + " of beer on the wall";
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
        String numberName;
        
        if (counter == 0) {
            stringified = "No more";
        } else if (counter < 20) {
            stringified = adigits[counter - 1].trim();
        } else if (counter < 100) {
            int tens = counter / 10;
            int digits = counter % 10;
            
            numberName = atens[tens].trim();
            if (digits != 0) {
                numberName += "-" + adigits[digits - 1].trim();
            }
            stringified = numberName;
        }
        
        if (counter == 1) {
            stringified += " bottle";
        } else {
            stringified += " bottles";
        }
    }
}