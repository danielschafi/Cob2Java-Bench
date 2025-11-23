public class LongMul {
    private static final int IJ_LIM = 7;
    private static final int IR_LIM = 14;
    
    public static void main(String[] args) {
        String inputString = "18,446,744,073,709,551,616";
        int[] a = new int[IJ_LIM];
        int[] b = new int[IJ_LIM];
        int[][] ir = new int[IJ_LIM][IR_LIM];
        int[] s = new int[IR_LIM];
        
        // Parse input string
        int j = 1;
        String[] parts = inputString.split(",");
        for (int i = 0; i < parts.length && i < IJ_LIM; i++) {
            a[i] = Integer.parseInt(parts[i]);
        }
        
        // Copy a to b
        System.arraycopy(a, 0, b, 0, IJ_LIM);
        
        // Perform intermediate calculation
        intermediateCalc(a, b, ir);
        
        // Sum ir
        sumIr(ir, s);
        
        // Display result
        displayResult(inputString, s);
    }
    
    private static void intermediateCalc(int[] a, int[] b, int[][] ir) {
        for (int i = IJ_LIM - 1; i >= 0; i--) {
            int carry = 0;
            for (int j = IJ_LIM - 1; j >= 0; j--) {
                int tempResult = a[i] * b[j] + carry;
                carry = tempResult / 1000;
                int remain = tempResult % 1000;
                int k = i + j;
                ir[i][k] = remain;
            }
            int k = i + IJ_LIM - 1;
            ir[i][k] = carry;
        }
    }
    
    private static void sumIr(int[][] ir, int[] s) {
        int carry = 0;
        for (int k = IR_LIM - 1; k >= 0; k--) {
            int tempResult = carry;
            for (int i = IJ_LIM - 1; i >= 0; i--) {
                tempResult += ir[i][k];
            }
            carry = tempResult / 1000;
            int remain = tempResult % 1000;
            s[k] = remain;
        }
    }
    
    private static void displayResult(String inputString, int[] s) {
        System.out.println("   " + inputString);
        System.out.println(" * " + inputString);
        System.out.print(" = ");
        
        int k = 0;
        while (k < IR_LIM && s[k] == 0) {
            k++;
        }
        
        if (k < IR_LIM) {
            if (s[k] < 100) {
                String sStr = String.valueOf(s[k]);
                int i = 0;
                while (i < sStr.length() && sStr.charAt(i) == '0') {
                    i++;
                }
                System.out.print(sStr.substring(i) + ",");
                k++;
            }
            
            for (; k < IR_LIM; k++) {
                System.out.print(s[k]);
                if (k < IR_LIM - 1) {
                    System.out.print(",");
                }
            }
        }
        System.out.println();
    }
}