```java
public class BottlesOfBeerOnTheWall {
    private static int bottles = 0;
    private static int remainingBottles = 0;
    private static int counting = 0;
    private static int startPosition = 0;
    private static int positions = 0;

    public static void main(String[] args) {
        passAroundThoseBeers();
    }

    private static void passAroundThoseBeers() {
        for (bottles = 99; bottles >= -1; bottles--) {
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
                        counting = 0;
                        String bottlesStr = String.format("%02d", bottles);
                        for (char c : bottlesStr.toCharArray()) {
                            if (c == '0') {
                                counting++;
                            } else {
                                break;
                            }
                        }
                        startPosition = counting + 1;
                        positions = 2 - counting;

                        String bottlesDisplay = bottlesStr.substring(startPosition - 1, startPosition - 1 + positions);
                        System.out.println(bottlesDisplay + " bottles of beer on the wall, " + bottlesDisplay + " bottles of beer.");

                        counting = 0;
                        String remainingStr = String.format("%02d", remainingBottles);
                        for (char c : remainingStr.toCharArray()) {
                            if (c == '0') {
                                counting++;
                            } else {
                                break;
                            }
                        }
                        startPosition = counting + 1;
                        positions = 2 - counting;

                        String remainingDisplay = remainingStr.substring(startPosition - 1, startPosition - 1 + positions);
                        System.out.println("Take one down and pass it around, " + remainingDisplay + " bottles of beer on the wall.");
                    }
                    break;
            }
        }
    }
}