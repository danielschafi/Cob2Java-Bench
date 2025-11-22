import java.time.LocalTime;
import java.util.Random;

public class RANDOM {
    public static void main(String[] args) {
        int sampleSize = 1000;
        double total = 0.0;
        double arithMean = 0.0;
        double stdDev = 0.0;
        double intermediate = 0.0;
        double[] rndTbl = new double[sampleSize];
        
        int ti = LocalTime.now().toSecondOfDay() * 100 + LocalTime.now().getNano() / 10000000;
        Random random = new Random(ti);
        
        for (int idx = 0; idx < sampleSize; idx++) {
            intermediate = random.nextDouble() * 2.01;
            rndTbl[idx] = intermediate;
        }
        
        for (int idx = 0; idx < sampleSize; idx++) {
            total = total + rndTbl[idx];
        }
        
        arithMean = total / sampleSize;
        System.out.println("Mean: " + String.format("%.3f", arithMean));
        
        intermediate = 0.0;
        for (int idx = 0; idx < sampleSize; idx++) {
            intermediate = intermediate + Math.pow(rndTbl[idx] - arithMean, 2);
        }
        stdDev = intermediate / sampleSize;
        
        System.out.println("Std-Dev: " + String.format("%.3f", stdDev));
    }
}