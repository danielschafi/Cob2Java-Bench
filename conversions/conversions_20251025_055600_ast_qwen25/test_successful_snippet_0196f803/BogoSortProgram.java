import java.util.Random;

public class BogoSortProgram {
    private int[] item = new int[10];
    private int randomSeed;
    private int randomIndex;
    private int arrayIndex;
    private int adjustedIndex;
    private int temporaryStorage;
    private int shuffles = 0;
    private int sorted;
    private Random random;

    public static void main(String[] args) {
        BogoSortProgram program = new BogoSortProgram();
        program.controlParagraph();
    }

    private void controlParagraph() {
        randomSeed = (int) System.currentTimeMillis();
        random = new Random(randomSeed);
        item[0] = random.nextInt();
        for (arrayIndex = 1; arrayIndex < 10; arrayIndex++) {
            randomItemParagraph();
        }
        System.out.print("BEFORE SORT:");
        for (arrayIndex = 0; arrayIndex < 10; arrayIndex++) {
            showArrayParagraph();
        }
        System.out.println();
        do {
            shuffleParagraph();
            isItSortedParagraph();
        } while (sorted != 1);
        System.out.print("AFTER SORT: ");
        for (arrayIndex = 0; arrayIndex < 10; arrayIndex++) {
            showArrayParagraph();
        }
        System.out.println();
        System.out.println(shuffles + " SHUFFLES PERFORMED.");
    }

    private void randomItemParagraph() {
        item[arrayIndex] = random.nextInt();
    }

    private void showArrayParagraph() {
        System.out.print(String.format("%4d", item[arrayIndex]));
    }

    private void shuffleParagraph() {
        for (arrayIndex = 0; arrayIndex < 10; arrayIndex++) {
            shuffleItemsParagraph();
        }
        shuffles++;
    }

    private void isItSortedParagraph() {
        sorted = 1;
        for (arrayIndex = 0; arrayIndex < 9; arrayIndex++) {
            itemInOrderParagraph();
            if (sorted == 0) break;
        }
    }

    private void shuffleItemsParagraph() {
        randomIndex = random.nextInt(10);
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