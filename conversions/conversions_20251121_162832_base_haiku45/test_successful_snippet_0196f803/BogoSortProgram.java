import java.util.Random;

public class BogoSortProgram {
    private static final int ARRAY_SIZE = 10;
    private int[] item = new int[ARRAY_SIZE];
    private long randomSeed;
    private int randomIndex;
    private int arrayIndex;
    private int adjustedIndex;
    private int temporaryStorage;
    private long shuffles = 0;
    private int sorted;
    private Random random;

    public static void main(String[] args) {
        BogoSortProgram program = new BogoSortProgram();
        program.controlParagraph();
    }

    private void controlParagraph() {
        randomSeed = System.currentTimeMillis();
        random = new Random(randomSeed);
        
        item[0] = (int) (random.nextDouble() * 1000);
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
        
        String shufflesNoZeros = String.format("%d", shuffles).replaceAll("^0+", "");
        if (shufflesNoZeros.isEmpty()) shufflesNoZeros = "0";
        System.out.println(shufflesNoZeros + " SHUFFLES PERFORMED.");
    }

    private void randomItemParagraph() {
        item[arrayIndex] = (int) (random.nextDouble() * 1000);
    }

    private void showArrayParagraph() {
        String itemNoZeros = String.format("%d", item[arrayIndex]).replaceAll("^0+", "");
        if (itemNoZeros.isEmpty()) itemNoZeros = "0";
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
            if (sorted == 0) break;
        }
    }

    private void shuffleItemsParagraph() {
        randomIndex = (int) (random.nextDouble() * 10);
        adjustedIndex = randomIndex + 1;
        if (adjustedIndex >= ARRAY_SIZE) {
            adjustedIndex = adjustedIndex % ARRAY_SIZE;
        }
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