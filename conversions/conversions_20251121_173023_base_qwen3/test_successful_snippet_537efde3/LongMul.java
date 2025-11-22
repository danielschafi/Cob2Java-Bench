import java.math.BigInteger;
import java.util.Arrays;

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
        int j = 0;
        for (int i = 0; i < IJ_LIM; i++) {
            int start = j;
            while (j < inputString.length() && inputString.charAt(j) != ',') {
                j++;
            }
            a[i] = Integer.parseInt(inputString.substring(start, j));
            if (j < inputString.length()) {
                j++;
            }
        }
        
        // Copy a to b
        System.arraycopy(a, 0, b, 0, IJ_LIM);
        
        // Intermediate calculation
        for (int i = IJ_LIM - 1; i >= 0; i--) {
            int carry = 0;
            for (int jIdx = IJ_LIM - 1; jIdx >= 0; jIdx--) {
                int tempResult = a[i] * b[jIdx] + carry;
                carry = tempResult / 1000;
                int remain = tempResult % 1000;
                int k = i + jIdx;
                ir[i][k] = remain;
            }
            int k = i + IJ_LIM - 1;
            ir[i][k] = carry;
        }
        
        // Sum ir
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
        
        // Display result
        System.out.println("   " + inputString);
        System.out.println(" * " + inputString);
        System.out.print(" = ");
        
        // Find first non-zero element
        int k = 0;
        while (k < IR_LIM && s[k] == 0) {
            k++;
        }
        
        if (k < IR_LIM) {
            // Handle leading zeros in first non-zero element
            String firstElement = String.valueOf(s[k]);
            int leadingZeros = 0;
            for (int idx = 0; idx < firstElement.length(); idx++) {
                if (firstElement.charAt(idx) == '0') {
                    leadingZeros++;
                } else {
                    break;
                }
            }
            if (leadingZeros > 0 && leadingZeros < firstElement.length()) {
                System.out.print(firstElement.substring(leadingZeros) + ",");
                k++;
            } else if (leadingZeros == 0) {
                System.out.print(firstElement + ",");
                k++;
            }
            
            // Print remaining elements
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