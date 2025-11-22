import java.util.Random;

public class BogoSortProgram {
    private int[] item = new int[10];
    private Random random;
    private int randomSeed;
    private int randomIndex;
    private int arrayIndex;
    private int adjustedIndex;
    private int temporaryStorage;
    private int shuffles;
    private int sorted;
    private String itemNoZeros;
    private String shufflesNoZeros;

    public BogoSortProgram() {
        random = new Random();
        shuffles = 0;
        sorted = 0;
    }

    public void controlParagraph() {
        randomSeed = (int) System.currentTimeMillis();
        random.setSeed(randomSeed);
        item[arrayIndex] = random.nextInt(1000);
        for (arrayIndex = 1; arrayIndex < 10; arrayIndex++) {
            randomItemParagraph();
        }
        System.out.print("BEFORE SORT:");
        for (arrayIndex = 0; arrayIndex < 10; arrayIndex++) {
            showArrayParagraph();
        }
        System.out.println();
        while (sorted != 1) {
            shuffleParagraph();
            isItSortedParagraph();
        }
        System.out.print("AFTER SORT: ");
        for (arrayIndex = 0; arrayIndex < 10; arrayIndex++) {
            showArrayParagraph();
        }
        System.out.println();
        shufflesNoZeros = String.format("%08d", shuffles);
        System.out.println(shufflesNoZeros + " SHUFFLES PERFORMED.");
    }

    private void randomItemParagraph() {
        item[arrayIndex] = random.nextInt(1000);
    }

    private void showArrayParagraph() {
        itemNoZeros = String.format("%04d", item[arrayIndex]);
        System.out.print(itemNoZeros);
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
            if (sorted == 0) {
                break;
            }
        }
    }

    private void shuffleItemsParagraph() {
        randomIndex = random.nextInt(10);
        adjustedIndex = randomIndex;
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

    public static void main(String[] args) {
        BogoSortProgram program = new BogoSortProgram();
        program.controlParagraph();
    }
}