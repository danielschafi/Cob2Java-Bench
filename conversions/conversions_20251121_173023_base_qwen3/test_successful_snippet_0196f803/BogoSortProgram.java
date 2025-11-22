import java.util.Random;

public class BogoSortProgram {
    private static int[] item = new int[10];
    private static int randomSeed;
    private static int arrayIndex;
    private static int adjustedIndex;
    private static int temporaryStorage;
    private static int shuffles = 0;
    private static int sorted = 0;
    private static Random random = new Random();

    public static void main(String[] args) {
        randomSeed = (int) System.currentTimeMillis();
        random.setSeed(randomSeed);
        item[0] = random.nextInt(1000);
        for (arrayIndex = 1; arrayIndex < 10; arrayIndex++) {
            item[arrayIndex] = random.nextInt(1000);
        }
        System.out.print("BEFORE SORT:");
        for (arrayIndex = 0; arrayIndex < 10; arrayIndex++) {
            System.out.printf("%4d", item[arrayIndex]);
        }
        System.out.println();
        do {
            shuffleItems();
            shuffles++;
            sorted = 1;
            for (arrayIndex = 0; arrayIndex < 9; arrayIndex++) {
                adjustedIndex = arrayIndex + 1;
                if (item[arrayIndex] > item[adjustedIndex]) {
                    sorted = 0;
                    break;
                }
            }
        } while (sorted != 1);
        System.out.print("AFTER SORT: ");
        for (arrayIndex = 0; arrayIndex < 10; arrayIndex++) {
            System.out.printf("%4d", item[arrayIndex]);
        }
        System.out.println();
        System.out.println(shuffles + " SHUFFLES PERFORMED.");
    }

    private static void shuffleItems() {
        for (arrayIndex = 0; arrayIndex < 10; arrayIndex++) {
            int randomIndex = random.nextInt(10);
            adjustedIndex = randomIndex + 1;
            temporaryStorage = item[arrayIndex];
            item[arrayIndex] = item[adjustedIndex];
            item[adjustedIndex] = temporaryStorage;
        }
    }
}