public class BottlesOfBeer {
    public static void main(String[] args) {
        int bottles;
        int remainingBottles;
        int counting;
        int startPosition;
        int positions;
        
        for (bottles = 99; bottles >= 0; bottles--) {
            System.out.println();
            remainingBottles = bottles - 1;
            
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
                        String bottlesStr = String.format("%02d", bottles);
                        counting = 0;
                        for (int i = 0; i < bottlesStr.length(); i++) {
                            if (bottlesStr.charAt(i) == '0') {
                                counting++;
                            } else {
                                break;
                            }
                        }
                        startPosition = counting;
                        positions = 2 - counting;
                        String bottlesDisplay = bottlesStr.substring(startPosition, startPosition + positions);
                        
                        String remainingStr = String.format("%02d", remainingBottles);
                        counting = 0;
                        for (int i = 0; i < remainingStr.length(); i++) {
                            if (remainingStr.charAt(i) == '0') {
                                counting++;
                            } else {
                                break;
                            }
                        }
                        startPosition = counting;
                        positions = 2 - counting;
                        String remainingDisplay = remainingStr.substring(startPosition, startPosition + positions);
                        
                        System.out.println(bottlesDisplay + " bottles of beer on the wall, " + bottlesDisplay + " bottles of beer.");
                        System.out.println("Take one down and pass it around, " + remainingDisplay + " bottles of beer on the wall.");
                    }
                    break;
            }
        }
    }
}