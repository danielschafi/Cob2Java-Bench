import java.util.Random;

public class BogoSortProgram {
    private static final int ARRAY_SIZE = 10;
    private static int[] item = new int[ARRAY_SIZE];
    private static int randomSeed;
    private static int randomIndex;
    private static int arrayIndex;
    private static int adjustedIndex;
    private static int temporaryStorage;
    private static int shuffles = 0;
    private static int sorted = 1;
    private static String itemNoZeros;
    private static String shufflesNoZeros;
    
    public static void main(String[] args) {
        // Initialize random seed from system time
        randomSeed = (int) System.currentTimeMillis();
        
        // Generate random numbers for first element
        item[0] = (int) (Math.random() * 1000);
        
        // Fill the rest of the array with random numbers
        for (arrayIndex = 1; arrayIndex < ARRAY_SIZE; arrayIndex++) {
            item[arrayIndex] = (int) (Math.random() * 1000);
        }
        
        // Display before sorting
        System.out.print("BEFORE SORT: ");
        for (arrayIndex = 0; arrayIndex < ARRAY_SIZE; arrayIndex++) {
            System.out.printf("%4d", item[arrayIndex]);
        }
        System.out.println();
        
        // Perform bogo sort
        do {
            shuffleItems();
            shuffles++;
            sorted = 1;
            for (arrayIndex = 0; arrayIndex < ARRAY_SIZE - 1; arrayIndex++) {
                if (item[arrayIndex] > item[arrayIndex + 1]) {
                    sorted = 0;
                    break;
                }
            }
        } while (sorted != 1);
        
        // Display after sorting
        System.out.print("AFTER SORT:  ");
        for (arrayIndex = 0; arrayIndex < ARRAY_SIZE; arrayIndex++) {
            System.out.printf("%4d", item[arrayIndex]);
        }
        System.out.println();
        
        // Display number of shuffles
        shufflesNoZeros = String.format("%8d", shuffles);
        System.out.println(shufflesNoZeros + " SHUFFLES PERFORMED.");
    }
    
    private static void shuffleItems() {
        Random rand = new Random();
        for (arrayIndex = 0; arrayIndex < ARRAY_SIZE; arrayIndex++) {
            randomIndex = rand.nextInt(ARRAY_SIZE);
            adjustedIndex = randomIndex + 1;
            
            temporaryStorage = item[arrayIndex];
            item[arrayIndex] = item[adjustedIndex];
            item[adjustedIndex] = temporaryStorage;
        }
    }
}