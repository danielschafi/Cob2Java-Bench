```java
import java.util.Scanner;

public class LongMul {
    private static final int IJ_LIM = 7;
    private static final int IR_LIM = 14;
    
    private String inputString = "18,446,744,073,709,551,616";
    private int[] aTable = new int[IJ_LIM];
    private int[] bTable = new int[IJ_LIM];
    private int[][] irTable = new int[IJ_LIM][IR_LIM];
    private int[] sTable = new int[IR_LIM];
    
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
            aTable[i] = Integer.parseInt(parts[i].trim());
        }
        
        for (i = 0; i < IJ_LIM; i++) {
            bTable[i] = aTable[i];
        }
        
        intermediateCalc();
        sumIr();
        displayResult();
    }
    
    private void intermediateCalc() {
        for (i = IJ_LIM - 1; i >= 0; i--) {
            carry = 0;
            for (j = IJ_LIM - 1; j >= 0; j--) {
                tempResult = aTable[i] * bTable[j] + carry;
                carry = tempResult / 1000;
                remain = tempResult % 1000;
                k = i + j;
                if (k < IR_LIM) {
                    irTable[i][k] = remain;
                }
            }
            k = k - 1;
            if (k >= 0 && k < IR_LIM) {
                irTable[i][k] = carry;
            }
        }
    }
    
    private void sumIr() {
        carry = 0;
        for (k = IR_LIM - 1; k >= 0; k--) {
            tempResult = carry;
            for (i = IJ_LIM - 1; i >= 0; i--) {
                tempResult = tempResult + irTable[i][k];
            }
            carry = tempResult / 1000;
            remain = tempResult % 1000;
            sTable[k] = remain;
        }
    }
    
    private void displayResult() {
        System.out.println("   " + inputString);
        System.out.println(" * " + inputString);
        System.out.print(" = ");
        
        k = 0;
        while (k < IR_LIM && sTable[k] == 0) {
            k++;
        }
        
        if (k < IR_LIM && sTable[k] < 100) {
            i = 1;
            String str = String.valueOf(sTable[k]);
            int leadingZeros = 0;
            for (int idx = 0; idx < str.length(); idx++) {
                if (str.charAt(idx) == '0') {
                    leadingZeros++;
                } else {
                    break;
                }
            }
            System.out.print(str.substring(leadingZeros) + ",");
            k++;
        }
        
        for (; k < IR_LIM; k++) {
            System.out.print(String.format("%03d", sTable[k]));
            if (k < IR_LIM - 1) {
                System.out.print(",");
            }
        }
        System.out.println();
    }
}