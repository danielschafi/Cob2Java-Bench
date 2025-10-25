import java.util.Random;

public class RANDOM {
    public static void main(String[] args) {
        int SampleSize = 1000;
        double Total = 0.0;
        double ArithMean = 0.0;
        double StdDev = 0.0;
        double Seed;
        long TI = System.currentTimeMillis();

        int Idx = 0;
        double Intermediate = 0.0;
        double[] Rnd = new double[SampleSize];

        Random random = new Random(TI);
        Seed = random.nextDouble();

        for (Idx = 0; Idx < SampleSize; Idx++) {
            Intermediate = (random.nextDouble() * 2.01);
            Rnd[Idx] = Intermediate;
        }

        for (Idx = 0; Idx < SampleSize; Idx++) {
            Total += Rnd[Idx];
        }

        ArithMean = Total / SampleSize;
        System.out.println("Mean: " + ArithMean);

        for (Idx = 0; Idx < SampleSize; Idx++) {
            Intermediate += Math.pow((Rnd[Idx] - ArithMean), 2);
        }
        StdDev = Intermediate / SampleSize;

        System.out.println("Std-Dev: " + StdDev);
    }
}