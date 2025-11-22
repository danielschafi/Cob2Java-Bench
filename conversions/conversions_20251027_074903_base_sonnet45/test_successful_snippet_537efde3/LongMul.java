import java.util.Arrays;

public class LongMul {
    private static final int IJ_LIM = 7;
    private static final int IR_LIM = 14;
    
    private String inputString = "18,446,744,073,709,551,616";
    private int[] a = new int[IJ_LIM];
    private int[] b = new int[IJ_LIM];
    private int[][] ir = new int[IJ_LIM][IR_LIM];
    private int[] s = new int[IR_LIM];
    private int tempResult = 0;
    private int carry = 0;
    private int remain = 0;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    
    public static void main(String[] args) {
        LongMul program = new LongMul();
        program.begin();
    }
    
    private void begin() {
        j = 0;
        String[] parts = inputString.split(",");
        for (i = 0; i < IJ_LIM && i < parts.length; i++) {
            a[i] = Integer.parseInt(parts[i]);
        }
        System.arraycopy(a, 0, b, 0, IJ_LIM);
        intermediateCalc();
        sumIr();
        displayResult();
    }
    
    private void intermediateCalc() {
        for (i = IJ_LIM - 1; i >= 0; i--) {
            carry = 0;
            for (j = IJ_LIM - 1; j >= 0; j--) {
                tempResult = a[i] * b[j] + carry;
                carry = tempResult / 1000;
                remain = tempResult % 1000;
                k = i + j;
                ir[i][k] = remain;
            }
            k = k - 1;
            ir[i][k] = carry;
        }
    }
    
    private void sumIr() {
        carry = 0;
        for (k = IR_LIM - 1; k >= 0; k--) {
            tempResult = carry;
            for (i = IJ_LIM - 1; i >= 0; i--) {
                tempResult = tempResult + ir[i][k];
            }
            carry = tempResult / 1000;
            remain = tempResult % 1000;
            s[k] = remain;
        }
    }
    
    private void displayResult() {
        System.out.println("   " + inputString);
        System.out.println(" * " + inputString);
        System.out.print(" = ");
        
        for (k = 0; k < IR_LIM && s[k] == 0; k++);
        
        if (k < IR_LIM && s[k] < 100) {
            String sVal = String.format("%03d", s[k]);
            i = 0;
            while (i < sVal.length() && sVal.charAt(i) == '0') {
                i++;
            }
            System.out.print(sVal.substring(i) + ",");
            k++;
        }
        
        for (; k < IR_LIM; k++) {
            System.out.print(String.format("%03d", s[k]));
            if (k < IR_LIM - 1) {
                System.out.print(",");
            }
        }
        System.out.println(" ");
    }
}