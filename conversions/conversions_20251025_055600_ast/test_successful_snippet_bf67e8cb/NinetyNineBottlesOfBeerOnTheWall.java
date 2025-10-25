import java.text.DecimalFormat;

public class NinetyNineBottlesOfBeerOnTheWall {
    public static void main(String[] args) {
        int bottles = 99;
        int remainingBottles;
        int counting;
        int startPosition;
        int positions;
        DecimalFormat df = new DecimalFormat("00");

        while (bottles >= 0) {
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
                    counting = 0;
                    String bottlesStr = df.format(bottles);
                    for (char c : bottlesStr.toCharArray()) {
                        if (c == '0') {
                            counting++;
                        } else {
                            break;
                        }
                    }
                    startPosition = counting + 1;
                    positions = 2 - counting;
                    System.out.println(bottlesStr.substring(startPosition - 1, startPosition + positions - 1) + " bottles of beer on the wall, " + bottlesStr.substring(startPosition - 1, startPosition + positions - 1) + " bottles of beer.");
                    counting = 0;
                    String remainingBottlesStr = df.format(remainingBottles);
                    for (char c : remainingBottlesStr.toCharArray()) {
                        if (c == '0') {
                            counting++;
                        } else {
                            break;
                        }
                    }
                    startPosition = counting + 1;
                    positions = 2 - counting;
                    System.out.println("Take one down and pass it around, " + remainingBottlesStr.substring(startPosition - 1, startPosition + positions - 1) + " bottles of beer on the wall.");
                    break;
            }
            bottles--;
        }
    }
}