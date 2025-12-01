public class LongMul {
    private static final int IJ_LIM = 7;
    private static final int IR_LIM = 14;
    
    private String inputString = "18,446,744,073,709,551,616";
    private int[] a = new int[IJ_LIM + 1];
    private int[] b = new int[IJ_LIM + 1];
    private int[][] ir = new int[IJ_LIM + 1][IR_LIM + 1];
    private int[] s = new int[IR_LIM + 1];
    
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
        j = 1;
        
        for (i = 1; i <= IJ_LIM; i++) {
            unstring(i);
        }
        
        for (int idx = 1; idx <= IJ_LIM; idx++) {
            b[idx] = a[idx];
        }
        
        intermediateCalc();
        sumIr();
        displayResult();
    }
    
    private void unstring(int index) {
        String[] parts = inputString.split(",");
        if (index <= parts.length) {
            a[index] = Integer.parseInt(parts[index - 1]);
        }
    }
    
    private void intermediateCalc() {
        for (i = IJ_LIM; i >= 1; i--) {
            carry = 0;
            for (j = IJ_LIM; j >= 1; j--) {
                tempResult = a[i] * b[j] + carry;
                carry = tempResult / 1000;
                remain = tempResult % 1000;
                k = i + j;
                ir[i][k] = remain;
            }
            k--;
            ir[i][k] = carry;
        }
    }
    
    private void sumIr() {
        carry = 0;
        for (k = IR_LIM; k >= 1; k--) {
            tempResult = carry;
            for (i = IJ_LIM; i >= 1; i--) {
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
        
        for (k = 1; k <= IR_LIM && s[k] == 0; k++) {
        }
        
        if (s[k] < 100) {
            i = 1;
            String str = String.valueOf(s[k]);
            int leadingZeros = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') {
                    leadingZeros++;
                } else {
                    break;
                }
            }
            i = leadingZeros + 1;
            System.out.print(str.substring(i - 1) + ",");
            k++;
        }
        
        for (; k <= IR_LIM; k++) {
            System.out.print(s[k]);
            if (k < IR_LIM) {
                System.out.print(",");
            }
        }
        System.out.println();
    }
}