import java.util.Random;

public class RandomProgram {
    
    public static void main(String[] args) {
        int sampleSize = 1000;
        double total = 0.0;
        double arithMean = 0.0;
        double stdDev = 0.0;
        double seed;
        long ti;
        
        int idx;
        double intermediate = 0.0;
        double[] rndTbl = new double[sampleSize];
        
        ti = System.currentTimeMillis();
        Random random = new Random(ti);
        seed = random.nextDouble();
        
        for (idx = 0; idx < sampleSize; idx++) {
            intermediate = (random.nextDouble() * 2.01);
            rndTbl[idx] = intermediate;
        }
        
        for (idx = 0; idx < sampleSize; idx++) {
            total = total + rndTbl[idx];
        }
        
        arithMean = total / sampleSize;
        System.out.println("Mean: " + String.format("%.3f", arithMean));
        
        intermediate = 0.0;
        for (idx = 0; idx < sampleSize; idx++) {
            intermediate = intermediate + Math.pow((rndTbl[idx] - arithMean), 2);
        }
        
        stdDev = Math.sqrt(intermediate / sampleSize);
        System.out.println("Std-Dev: " + String.format("%.3f", stdDev));
    }
}