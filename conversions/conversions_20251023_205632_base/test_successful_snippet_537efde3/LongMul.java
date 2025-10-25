import java.util.Arrays;

public class LongMul {
    private static final int IJ_LIM = 7;
    private static final int IR_LIM = 14;
    private static final String INPUT_STRING = "18,446,744,073,709,551,616";
    private static final int[] A_TABLE = new int[IJ_LIM];
    private static final int[] B_TABLE = new int[IJ_LIM];
    private static final int[][] IR_TABLE = new int[IJ_LIM][IR_LIM];
    private static final int[] S_TABLE = new int[IR_LIM];
    private static final int[] DISPLAY = new int[3];
    private static final int[] BINARY = new int[3];

    public static void main(String[] args) {
        BINARY[1] = 1;
        for (int i = 0; i < IJ_LIM; i++) {
            int j = BINARY[1];
            int endIndex = INPUT_STRING.indexOf(',', j);
            if (endIndex == -1) {
                endIndex = INPUT_STRING.length();
            }
            A_TABLE[i] = Integer.parseInt(INPUT_STRING.substring(j, endIndex));
            B_TABLE[i] = A_TABLE[i];
            BINARY[1] = endIndex + 1;
        }
        intermediateCalc();
        sumIr();
        displayResult();
    }

    private static void intermediateCalc() {
        for (int i = IJ_LIM - 1; i >= 0; i--) {
            BINARY[2] = 0;
            for (int j = IJ_LIM - 1; j >= 0; j--) {
                int tempResult = A_TABLE[i] * B_TABLE[j] + BINARY[2];
                BINARY[2] = tempResult / 1000;
                int remain = tempResult % 1000;
                int k = i + j;
                IR_TABLE[i][k] = remain;
            }
            BINARY[0] = i + j;
            IR_TABLE[i][BINARY[0]] = BINARY[2];
        }
    }

    private static void sumIr() {
        BINARY[2] = 0;
        for (int k = IR_LIM - 1; k >= 0; k--) {
            int tempResult = BINARY[2];
            for (int i = IJ_LIM - 1; i >= 0; i--) {
                tempResult += IR_TABLE[i][k];
            }
            BINARY[2] = tempResult / 1000;
            int remain = tempResult % 1000;
            S_TABLE[k] = remain;
        }
    }

    private static void displayResult() {
        System.out.printf("   %s%n", INPUT_STRING);
        System.out.printf(" * %s%n", INPUT_STRING);
        System.out.print(" = ");
        int k = 0;
        while (k < IR_LIM && S_TABLE[k] == 0) {
            k++;
        }
        if (S_TABLE[k] < 100) {
            int i = 1;
            while (i < 3 && S_TABLE[k] % (int) Math.pow(10, 3 - i) == 0) {
                i++;
            }
            System.out.printf("%d", S_TABLE[k]);
            if (k < IR_LIM - 1) {
                System.out.print(",");
            }
            k++;
        }
        for (; k < IR_LIM; k++) {
            System.out.printf("%d", S_TABLE[k]);
            if (k < IR_LIM - 1) {
                System.out.print(",");
            }
        }
        System.out.println();
    }
}