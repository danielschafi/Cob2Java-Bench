import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020141 {
    private static final int MEM_SIZE = 65536;
    private static final int MASK_LENGTH = 36;
    
    private static String[] memory = new String[MEM_SIZE];
    private static String mask = "";
    private static long result = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d14.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        sumMemory();
        System.out.println(result);
    }
    
    private static void processRecord(String record) {
        if (record.startsWith("mask")) {
            mask = record.substring(7);
        } else {
            int equalsIndex = record.indexOf('=');
            String addrStr = record.substring(4, equalsIndex).trim();
            String valStr = record.substring(equalsIndex + 1).trim();
            
            int addr = Integer.parseInt(addrStr);
            long val = Long.parseLong(valStr);
            
            String binaryVal = decimalToBinary(val);
            applyMask(binaryVal);
            long convertedVal = binaryToDecimal(maskedBinary);
            memory[addr] = String.valueOf(convertedVal);
        }
    }
    
    private static String maskedBinary = "";
    
    private static void applyMask(String binaryVal) {
        maskedBinary = "";
        for (int i = 0; i < MASK_LENGTH; i++) {
            char maskChar = mask.charAt(i);
            if (maskChar == 'X') {
                maskedBinary += binaryVal.charAt(i);
            } else {
                maskedBinary += maskChar;
            }
        }
    }
    
    private static String decimalToBinary(long decimal) {
        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < MASK_LENGTH; i++) {
            binary.insert(0, decimal % 2);
            decimal /= 2;
        }
        return binary.toString();
    }
    
    private static long binaryToDecimal(String binary) {
        long decimal = 0;
        for (int i = 0; i < MASK_LENGTH; i++) {
            decimal = decimal * 2;
            if (binary.charAt(i) == '1') {
                decimal++;
            }
        }
        return decimal;
    }
    
    private static void sumMemory() {
        for (int i = 0; i < MEM_SIZE; i++) {
            if (memory[i] != null) {
                result += Long.parseLong(memory[i]);
            }
        }
    }
}