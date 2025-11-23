import java.util.*;

public class RANDOM {
    private static final int Sample_Size = 1000;
    private static double Total = 0.0;
    private static double Arith_Mean = 0.0;
    private static double Std_Dev = 0.0;
    private static double Seed = 0.0;
    private static int TI = 0;
    private static int Idx = 0;
    private static double Intermediate = 0.0;
    private static double[] Rnd = new double[Sample_Size];

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        TI = (int) calendar.getTimeInMillis();
        Seed = Math.random() * TI;
        
        for (Idx = 1; Idx <= Sample_Size; Idx++) {
            Intermediate = (Math.random() * 2.01);
            Rnd[Idx - 1] = Intermediate;
        }
        
        for (Idx = 1; Idx <= Sample_Size; Idx++) {
            Total += Rnd[Idx - 1];
        }

        Arith_Mean = Total / Sample_Size;
        System.out.println("Mean: " + Arith_Mean);

        Intermediate = 0.0;
        for (Idx = 1; Idx <= Sample_Size; Idx++) {
            Intermediate += Math.pow(Rnd[Idx - 1] - Arith_Mean, 2);
        }
        
        Std_Dev = Intermediate / Sample_Size;
        System.out.println("Std-Dev: " + Std_Dev);

        System.exit(0);
    }
}