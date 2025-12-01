import java.util.Random;

public class RANDOM {
    private static int sampleSize = 1000;
    private static double total = 0.0;
    private static double arithMean = 0.0;
    private static double stdDev = 0.0;
    private static double seed = 0.0;
    private static long ti = 0;
    private static int idx = 0;
    private static double intermediate = 0.0;
    private static double[] rnd = new double[99999];
    
    public static void main(String[] args) {
        mainProgram();
    }
    
    public static void mainProgram() {
        ti = System.currentTimeMillis();
        Random random = new Random(ti);
        seed = random.nextDouble();
        
        for (idx = 1; idx <= sampleSize; idx++) {
            intermediate = random.nextDouble() * 2.01;
            rnd[idx - 1] = intermediate;
        }
        
        total = 0.0;
        for (idx = 1; idx <= sampleSize; idx++) {
            total = total + rnd[idx - 1];
        }
        
        arithMean = total / sampleSize;
        System.out.println("Mean: " + String.format("%.3f", arithMean));
        
        intermediate = 0.0;
        for (idx = 1; idx <= sampleSize; idx++) {
            intermediate = intermediate + Math.pow((rnd[idx - 1] - arithMean), 2);
        }
        
        stdDev = intermediate / sampleSize;
        stdDev = Math.sqrt(stdDev);
        
        System.out.println("Std-Dev: " + String.format("%.3f", stdDev));
    }
}