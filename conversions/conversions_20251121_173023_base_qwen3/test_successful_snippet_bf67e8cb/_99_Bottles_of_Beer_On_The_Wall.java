public class _99_Bottles_of_Beer_On_The_Wall {
    public static void main(String[] args) {
        int bottles = 99;
        int remainingBottles;
        
        for (int i = 99; i >= 0; i--) {
            bottles = i;
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
                    if (bottles < 10) {
                        System.out.print(bottles + " bottles of beer on the wall, ");
                        System.out.print(bottles + " bottles of beer.\n");
                        System.out.print("Take one down and pass it around, ");
                        System.out.print(remainingBottles + " bottles of beer on the wall.\n");
                    } else {
                        String bottlesStr = String.valueOf(bottles);
                        String remainingBottlesStr = String.valueOf(remainingBottles);
                        
                        int counting = 0;
                        for (int j = 0; j < bottlesStr.length(); j++) {
                            if (bottlesStr.charAt(j) == '0') {
                                counting++;
                            } else {
                                break;
                            }
                        }
                        int startPosition = counting + 1;
                        int positions = 2 - counting;
                        
                        System.out.print(bottlesStr.substring(startPosition - 1, startPosition - 1 + positions));
                        System.out.print(" bottles of beer on the wall, ");
                        System.out.print(bottlesStr.substring(startPosition - 1, startPosition - 1 + positions));
                        System.out.print(" bottles of beer.\n");
                        
                        counting = 0;
                        for (int j = 0; j < remainingBottlesStr.length(); j++) {
                            if (remainingBottlesStr.charAt(j) == '0') {
                                counting++;
                            } else {
                                break;
                            }
                        }
                        startPosition = counting + 1;
                        positions = 2 - counting;
                        
                        System.out.print("Take one down and pass it around, ");
                        System.out.print(remainingBottlesStr.substring(startPosition - 1, startPosition - 1 + positions));
                        System.out.print(" bottles of beer on the wall.\n");
                    }
                    break;
            }
        }
    }
}