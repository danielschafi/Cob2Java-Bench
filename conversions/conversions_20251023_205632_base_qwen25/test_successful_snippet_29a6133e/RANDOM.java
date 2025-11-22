import java.util.Random;

public class RANDOM {
    public static void main(String[] args) {
        int Sample_Size = 1000;
        double Total = 0.0;
        double Arith_Mean = 0.0;
        double Std_Dev = 0.0;
        double Seed = 0.0;
        long TI = System.currentTimeMillis();

        int Idx = 0;
        double Intermediate = 0.0;
        double[] Rnd_Tbl = new double[Sample_Size];

        Random random = new Random(TI);

        for (Idx = 1; Idx <= Sample_Size; Idx++) {
            Intermediate = (random.nextDouble() * 2.01);
            Rnd_Tbl[Idx - 1] = Intermediate;
        }

        for (Idx = 1; Idx <= Sample_Size; Idx++) {
            Total = Total + Rnd_Tbl[Idx - 1];
        }

        Arith_Mean = Total / Sample_Size;
        System.out.println("Mean: " + Arith_Mean);

        for (Idx = 1; Idx <= Sample_Size; Idx++) {
            Intermediate = Intermediate + Math.pow(Rnd_Tbl[Idx - 1] - Arith_Mean, 2);
        }
        Std_Dev = Intermediate / Sample_Size;

        System.out.println("Std-Dev: " + Std_Dev);
    }
}