import java.util.Random;

public class RANDOM {
    private static final int SAMPLE_SIZE = 1000;
    private static double total = 0.0;
    private static double arithmeticMean = 0.0;
    private static double standardDeviation = 0.0;
    private static double seed;
    private static long ti;
    private static int idx = 0;
    private static double intermediate = 0.0;
    private static double[] rnd = new double[SAMPLE_SIZE];

    public static void main(String[] args) {
        ti = System.currentTimeMillis();
        seed = new Random(ti).nextDouble();
        
        for (idx = 0; idx < SAMPLE_SIZE; idx++) {
            intermediate = Math.random() * 2.01;
            rnd[idx] = intermediate;
        }
        
        for (idx = 0; idx < SAMPLE_SIZE; idx++) {
            total += rnd[idx];
        }
        
        arithmeticMean = total / SAMPLE_SIZE;
        System.out.println("Mean: " + arithmeticMean);
        
        intermediate = 0.0;
        for (idx = 0; idx < SAMPLE_SIZE; idx++) {
            intermediate += Math.pow(rnd[idx] - arithmeticMean, 2);
        }
        
        standardDeviation = intermediate / SAMPLE_SIZE;
        System.out.println("Std-Dev: " + standardDeviation);
    }
}