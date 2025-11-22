import java.time.LocalTime;
import java.util.Random;

public class BogoSortProgram {
    private static final int ARRAY_SIZE = 10;
    private int[] itemTable = new int[ARRAY_SIZE];
    private Random random;
    private int shuffles = 0;
    private int sorted = 0;

    public static void main(String[] args) {
        BogoSortProgram program = new BogoSortProgram();
        program.controlParagraph();
    }

    private void controlParagraph() {
        long randomSeed = LocalTime.now().toNanoOfDay();
        random = new Random(randomSeed);
        
        itemTable[0] = (int)(random.nextDouble() * 1000);
        
        for (int arrayIndex = 1; arrayIndex < ARRAY_SIZE; arrayIndex++) {
            randomItemParagraph(arrayIndex);
        }
        
        System.out.print("BEFORE SORT:");
        for (int arrayIndex = 0; arrayIndex < ARRAY_SIZE; arrayIndex++) {
            showArrayParagraph(arrayIndex);
        }
        System.out.println();
        
        do {
            shuffleParagraph();
            isItSortedParagraph();
        } while (sorted != 1);
        
        System.out.print("AFTER SORT: ");
        for (int arrayIndex = 0; arrayIndex < ARRAY_SIZE; arrayIndex++) {
            showArrayParagraph(arrayIndex);
        }
        System.out.println();
        
        System.out.println(String.format("%d", shuffles).trim() + " SHUFFLES PERFORMED.");
    }

    private void randomItemParagraph(int arrayIndex) {
        itemTable[arrayIndex] = (int)(random.nextDouble() * 1000);
    }

    private void showArrayParagraph(int arrayIndex) {
        System.out.print(String.format("%4d", itemTable[arrayIndex]).trim());
    }

    private void shuffleParagraph() {
        for (int arrayIndex = 0; arrayIndex < ARRAY_SIZE; arrayIndex++) {
            shuffleItemsParagraph(arrayIndex);
        }
        shuffles++;
    }

    private void isItSortedParagraph() {
        sorted = 1;
        for (int arrayIndex = 0; arrayIndex < ARRAY_SIZE - 1 && sorted != 0; arrayIndex++) {
            itemInOrderParagraph(arrayIndex);
        }
    }

    private void shuffleItemsParagraph(int arrayIndex) {
        int randomIndex = (int)(random.nextDouble() * 10);
        int adjustedIndex = randomIndex + 1;
        if (adjustedIndex >= ARRAY_SIZE) {
            adjustedIndex = ARRAY_SIZE - 1;
        }
        
        int temporaryStorage = itemTable[arrayIndex];
        itemTable[arrayIndex] = itemTable[adjustedIndex];
        itemTable[adjustedIndex] = temporaryStorage;
    }

    private void itemInOrderParagraph(int arrayIndex) {
        int adjustedIndex = arrayIndex + 1;
        if (itemTable[arrayIndex] > itemTable[adjustedIndex]) {
            sorted = 0;
        }
    }
}