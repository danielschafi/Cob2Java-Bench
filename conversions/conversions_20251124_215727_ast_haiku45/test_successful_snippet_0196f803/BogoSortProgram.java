import java.util.Random;

public class BogoSortProgram {
    private int[] item = new int[11];
    private long randomSeed;
    private int randomIndex;
    private int arrayIndex;
    private int adjustedIndex;
    private int temporaryStorage;
    private long shuffles = 0;
    private int sorted;
    private Random random;

    public BogoSortProgram() {
        random = new Random();
    }

    public static void main(String[] args) {
        BogoSortProgram program = new BogoSortProgram();
        program.controlParagraph();
    }

    private void controlParagraph() {
        randomSeed = System.currentTimeMillis();
        random.setSeed(randomSeed);
        item[1] = (int) (random.nextDouble() * 1000);

        for (arrayIndex = 2; arrayIndex <= 10; arrayIndex++) {
            randomItemParagraph();
        }

        System.out.print("BEFORE SORT:");
        for (arrayIndex = 1; arrayIndex <= 10; arrayIndex++) {
            showArrayParagraph();
        }
        System.out.println();

        do {
            shuffleParagraph();
            isItSortedParagraph();
        } while (sorted != 1);

        System.out.print("AFTER SORT: ");
        for (arrayIndex = 1; arrayIndex <= 10; arrayIndex++) {
            showArrayParagraph();
        }
        System.out.println();

        String shufflesStr = String.format("%d", shuffles).trim();
        System.out.println(shufflesStr + " SHUFFLES PERFORMED.");
    }

    private void randomItemParagraph() {
        item[arrayIndex] = (int) (random.nextDouble() * 1000);
    }

    private void showArrayParagraph() {
        System.out.print(String.format("%d", item[arrayIndex]));
    }

    private void shuffleParagraph() {
        for (arrayIndex = 1; arrayIndex <= 10; arrayIndex++) {
            shuffleItemsParagraph();
        }
        shuffles++;
    }

    private void isItSortedParagraph() {
        sorted = 1;
        for (arrayIndex = 1; arrayIndex <= 9; arrayIndex++) {
            itemInOrderParagraph();
            if (sorted == 0 || arrayIndex == 10) {
                break;
            }
        }
    }

    private void shuffleItemsParagraph() {
        randomIndex = (int) (random.nextDouble() * 10);
        adjustedIndex = randomIndex + 1;
        temporaryStorage = item[arrayIndex];
        item[arrayIndex] = item[adjustedIndex];
        item[adjustedIndex] = temporaryStorage;
    }

    private void itemInOrderParagraph() {
        adjustedIndex = arrayIndex + 1;
        if (item[arrayIndex] > item[adjustedIndex]) {
            sorted = 0;
        }
    }
}