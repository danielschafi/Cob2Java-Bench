```java
public class NinetyNine {
    private static int counter;
    private static String[] atens = {
        "       ", "Twenty ", "Thirty ", "Forty  ", "Fifty  ", 
        "Sixty  ", "Seventy", "Eighty ", "Ninety ", "       "
    };
    
    private static String[] adigits = {
        "One      ", "Two      ", "Three    ", "Four     ", "Five     ",
        "Six      ", "Seven    ", "Eight    ", "Nine     ", "Ten      ",
        "Eleven   ", "Twelve   ", "Thirteen ", "Fourteen ", "Fifteen  ",
        "Sixteen  ", "Seventeen", "Eighteen ", "Nineteen ", "         "
    };
    
    public static void main(String[] args) {
        main100();
    }
    
    private static void main100() {
        for (counter = 99; counter >= 0; counter--) {
            showNumber();
            System.out.print(" of beer on the wall");
            System.out.println();
            
            showNumber();
            System.out.print(" of beer");
            System.out.println();
            
            System.out.print("Take ");
            if (counter == 1) {
                System.out.print("it ");
            } else {
                System.out.print("one ");
            }
            System.out.print("down and pass it round");
            System.out.println();
            
            counter--;
            showNumber();
            System.out.print(" of beer on the wall");
            System.out.println();
            
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
        } else {
            if (counter < 20) {
                System.out.print(adigits[counter - 1].trim());
            } else {
                if (counter < 100) {
                    int tens = counter / 10;
                    int digits = counter % 10;
                    String numberName = atens[tens].trim();
                    if (digits > 0) {
                        numberName += " " + adigits[digits - 1].trim();
                    }
                    System.out.print(numberName);
                }
            }
        }
        
        if (counter == 1) {
            System.out.print(" bottle");
        } else {
            System.out.print(" bottles");
        }
    }
}