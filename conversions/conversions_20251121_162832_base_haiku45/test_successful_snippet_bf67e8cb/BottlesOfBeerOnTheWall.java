```java
public class BottlesOfBeerOnTheWall {
    public static void main(String[] args) {
        int bottles = 99;
        int remainingBottles = 0;
        int counting = 0;
        int startPosition = 0;
        int positions = 0;
        
        while (bottles >= -1) {
            System.out.println();
            
            remainingBottles = bottles - 1;
            
            if (bottles == 0) {
                System.out.println("No more bottles of beer on the wall, no more bottles of beer.");
                System.out.println("Go to the store and buy some more, 99 bottles of beer on the wall.");
            } else if (bottles == 1) {
                System.out.println("1 bottle of beer on the wall, 1 bottle of beer.");
                System.out.println("Take one down and pass it around, no more bottles of beer on the wall.");
            } else if (bottles >= 2 && bottles <= 99) {
                String bottlesStr = String.format("%02d", bottles);
                counting = countLeadingZeroes(bottlesStr);
                startPosition = counting + 1;
                positions = 2 - counting;
                
                String bottlesDisplay = bottlesStr.substring(startPosition - 1, startPosition - 1 + positions);
                System.out.println(bottlesDisplay + " bottles of beer on the wall, " + bottlesDisplay + " bottles of beer.");
                
                String remainingStr = String.format("%02d", remainingBottles);
                counting = countLeadingZeroes(remainingStr);
                startPosition = counting + 1;
                positions = 2 - counting;
                
                String remainingDisplay = remainingStr.substring(startPosition - 1, startPosition - 1 + positions);
                System.out.println("Take one down and pass it around, " + remainingDisplay + " bottles of beer on the wall.");
            }
            
            bottles--;
            if (bottles < -1) break;
        }
    }
    
    private static int countLeadingZeroes(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '0') {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}