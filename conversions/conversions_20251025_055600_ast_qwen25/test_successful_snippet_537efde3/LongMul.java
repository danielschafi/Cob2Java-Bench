import java.util.Arrays;

public class LongMul {
    private static final int IJ_LIM = 7;
    private static final int IR_LIM = 14;
    private static final String INPUT_STRING = "18,446,744,073,709,551,616";
    private static final int[][] A_TABLE = new int[IJ_LIM][1];
    private static final int[][] B_TABLE = new int[IJ_LIM][1];
    private static final int[][] IR_TABLE = new int[IJ_LIM][IR_LIM];
    private static final int[] S_TABLE = new int[IR_LIM];
    private static final int[] TEMP_RESULT = new int[1];
    private static final int[] CARRY = new int[1];
    private static final int[] REMAIN = new int[1];
    private static final int[] I = new int[1];
    private static final int[] J = new int[1];
    private static final int[] K = new int[1];

    public static void main(String[] args) {
        J[0] = 1;
        for (I[0] = 0; I[0] < IJ_LIM; I[0]++) {
            int start = J[0];
            int end = INPUT_STRING.indexOf(',', start);
            if (end == -1) {
                end = INPUT_STRING.length();
            }
            A_TABLE[I[0]][0] = Integer.parseInt(INPUT_STRING.substring(start, end));
            J[0] = end + 1;
        }
        System.arraycopy(A_TABLE, 0, B_TABLE, 0, IJ_LIM);
        intermediateCalc();
        sumIr();
        displayResult();
    }

    private static void intermediateCalc() {
        for (I[0] = IJ_LIM - 1; I[0] >= 0; I[0]--) {
            CARRY[0] = 0;
            for (J[0] = IJ_LIM - 1; J[0] >= 0; J[0]--) {
                TEMP_RESULT[0] = A_TABLE[I[0]][0] * B_TABLE[J[0]][0] + CARRY[0];
                CARRY[0] = TEMP_RESULT[0] / 1000;
                REMAIN[0] = TEMP_RESULT[0] % 1000;
                K[0] = I[0] + J[0];
                IR_TABLE[I[0]][K[0]] = REMAIN[0];
            }
            K[0]--;
            IR_TABLE[I[0]][K[0]] = CARRY[0];
        }
    }

    private static void sumIr() {
        CARRY[0] = 0;
        for (K[0] = IR_LIM - 1; K[0] >= 0; K[0]--) {
            TEMP_RESULT[0] = CARRY[0];
            for (I[0] = IJ_LIM - 1; I[0] >= 0; I[0]--) {
                TEMP_RESULT[0] += IR_TABLE[I[0]][K[0]];
            }
            CARRY[0] = TEMP_RESULT[0] / 1000;
            REMAIN[0] = TEMP_RESULT[0] % 1000;
            S_TABLE[K[0]] = REMAIN[0];
        }
    }

    private static void displayResult() {
        System.out.println("   " + INPUT_STRING);
        System.out.println(" * " + INPUT_STRING);
        System.out.print(" = ");
        for (K[0] = 0; K[0] < IR_LIM && S_TABLE[K[0]] == 0; K[0]++) {
        }
        if (S_TABLE[K[0]] < 100) {
            I[0] = 1;
            while (I[0] < 3 && S_TABLE[K[0]] < Math.pow(10, 3 - I[0])) {
                I[0]++;
            }
            System.out.print(String.valueOf(S_TABLE[K[0]]).substring(I[0] - 1) + ",");
            K[0]++;
        }
        for (; K[0] < IR_LIM; K[0]++) {
            System.out.print(S_TABLE[K[0]]);
            if (K[0] < IR_LIM - 1) {
                System.out.print(",");
            }
        }
        System.out.println();
    }
}