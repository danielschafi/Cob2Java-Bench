public class BottlesOfBeer {
    public static void main(String[] args) {
        int bottles = 99;
        
        while (bottles >= 0) {
            System.out.println();
            
            int remainingBottles = bottles - 1;
            
            switch (bottles) {
                case 0:
                    System.out.println("No more bottles of beer on the wall, no more bottles of beer.");
                    System.out.println("Go to the store and buy some more, 99 bottles of beer on the wall.");
                    break;
                case 1:
                    System.out.println("1 bottle of beer on the wall, 1 bottle of beer.");
                    System.out.println("Take one down and pass it around, no more bottles of beer on the wall.");
                    break;
                default:
                    if (bottles >= 2 && bottles <= 99) {
                        String bottlesStr = String.valueOf(bottles);
                        int counting = 0;
                        
                        // Count leading zeros
                        for (int i = 0; i < bottlesStr.length(); i++) {
                            if (bottlesStr.charAt(i) == '0') {
                                counting++;
                            } else {
                                break;
                            }
                        }
                        
                        int startPosition = counting + 1;
                        int positions = 2 - counting;
                        
                        String displayBottles = bottlesStr.substring(startPosition - 1, startPosition - 1 + positions);
                        System.out.print(displayBottles + " bottles of beer on the wall, ");
                        System.out.print(displayBottles + " bottles of beer.");
                        
                        // Reset counting
                        counting = 0;
                        
                        // Count leading zeros in remainingBottles
                        String remainingBottlesStr = String.valueOf(remainingBottles);
                        for (int i = 0; i < remainingBottlesStr.length(); i++) {
                            if (remainingBottlesStr.charAt(i) == '0') {
                                counting++;
                            } else {
                                break;
                            }
                        }
                        
                        startPosition = counting + 1;
                        positions = 2 - counting;
                        
                        String displayRemaining = remainingBottlesStr.substring(startPosition - 1, startPosition - 1 + positions);
                        System.out.println();
                        System.out.println("Take one down and pass it around, " + displayRemaining + " bottles of beer on the wall.");
                    }
                    break;
            }
            
            bottles--;
        }
    }
}