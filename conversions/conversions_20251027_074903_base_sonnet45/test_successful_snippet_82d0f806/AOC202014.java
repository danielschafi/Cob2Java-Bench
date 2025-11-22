import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC202014 {
    private static String wsMask = "";
    private static long[] wsMem = new long[65536];
    
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d14.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line);
            }
            long result = sumMemory();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void processRecord(String inputRecord) {
        if (inputRecord.startsWith("mask")) {
            wsMask = inputRecord.substring(7).trim();
        } else {
            int equalPos = inputRecord.indexOf('=');
            String addrPart = inputRecord.substring(4, equalPos).trim();
            String valPart = inputRecord.substring(equalPos + 1).trim();
            
            int wsAddr = Integer.parseInt(addrPart.replaceAll("[^0-9]", ""));
            long wsVal = Long.parseLong(valPart.trim());
            
            String wsValBin = decToBin(wsVal);
            wsValBin = applyMask(wsValBin);
            long wsValDec = binToDec(wsValBin);
            
            wsMem[wsAddr] = wsValDec;
        }
    }
    
    private static String decToBin(long wsValDec) {
        StringBuilder wsValBin = new StringBuilder();
        for (int i = 35; i >= 0; i--) {
            long remainder = wsValDec % 2;
            wsValDec = wsValDec / 2;
            wsValBin.insert(0, remainder);
        }
        return wsValBin.toString();
    }
    
    private static String applyMask(String wsValBin) {
        StringBuilder result = new StringBuilder(wsValBin);
        for (int i = 0; i < 36; i++) {
            if (wsMask.charAt(i) != 'X') {
                result.setCharAt(i, wsMask.charAt(i));
            }
        }
        return result.toString();
    }
    
    private static long binToDec(String wsValBin) {
        long wsValDec = 0;
        for (int i = 0; i < 36; i++) {
            wsValDec = wsValDec * 2;
            if (wsValBin.charAt(i) == '1') {
                wsValDec = wsValDec + 1;
            }
        }
        return wsValDec;
    }
    
    private static long sumMemory() {
        long result = 0;
        for (int i = 0; i < 65536; i++) {
            result += wsMem[i];
        }
        return result;
    }
}