import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020141 {
    private static final int MEMORY_SIZE = 65536;
    private static long[] wsMem = new long[MEMORY_SIZE];
    private static String wsMask = "";
    private static long result = 0;
    private static long wsAddr = 0;
    private static long wsVal = 0;
    private static long wsValDec = 0;
    private static String wsValBin = "";

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

    private static void processRecord(String inputRecord) {
        if (inputRecord.startsWith("mask")) {
            wsMask = inputRecord.substring(7, 43);
        } else {
            int equalsIndex = inputRecord.indexOf('=');
            wsAddr = Long.parseLong(inputRecord.substring(4, equalsIndex).trim());
            wsVal = Long.parseLong(inputRecord.substring(equalsIndex + 1).trim());
            wsValDec = wsVal;
            decToBin();
            applyMask();
            binToDec();
            wsMem[(int)wsAddr] = wsValDec;
        }
    }

    private static void applyMask() {
        StringBuilder newValBin = new StringBuilder(wsValBin);
        for (int i = 0; i < 36; i++) {
            char maskChar = wsMask.charAt(i);
            if (maskChar != 'X') {
                newValBin.setCharAt(i, maskChar);
            }
        }
        wsValBin = newValBin.toString();
    }

    private static void decToBin() {
        StringBuilder binary = new StringBuilder();
        long tempVal = wsValDec;
        for (int i = 35; i >= 0; i--) {
            binary.insert(0, tempVal % 2);
            tempVal /= 2;
        }
        wsValBin = binary.toString();
    }

    private static void binToDec() {
        wsValDec = 0;
        for (int i = 0; i < 36; i++) {
            wsValDec = wsValDec * 2;
            if (wsValBin.charAt(i) == '1') {
                wsValDec += 1;
            }
        }
    }

    private static void sumMemory() {
        for (int i = 0; i < MEMORY_SIZE; i++) {
            result += wsMem[i];
        }
    }
}