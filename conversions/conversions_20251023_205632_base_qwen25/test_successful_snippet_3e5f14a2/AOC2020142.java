import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020142 {
    private static final int MEM_SIZE = 512000;
    private static int fileStatus = 0;
    private static int recLen;
    private static String wsMask = "                                  ";
    private static int wsAddr;
    private static int wsVal;
    private static int wsAddrDec = 0;
    private static String wsAddrFloat = "                                  ";
    private static String wsAddrTmp = "                                  ";
    private static String wsAddrBin = "                                  ";
    private static int wsMemSize = 0;
    private static final int[][] wsMem = new int[MEM_SIZE][2];
    private static long result = 0;
    private static int wsD;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d14.input"))) {
            String inputRecord;
            while ((inputRecord = reader.readLine()) != null) {
                recLen = inputRecord.length();
                processRecord(inputRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        sumMemory();
        System.out.println(result);
    }

    private static void processRecord(String inputRecord) {
        if (inputRecord.substring(0, 4).equals("mask")) {
            wsMask = inputRecord.substring(7);
        } else {
            String[] parts = inputRecord.substring(4).split("=");
            wsAddr = Integer.parseInt(parts[0].trim());
            wsVal = Integer.parseInt(parts[1].trim());
            wsAddrDec = wsAddr;
            decToBin();
            applyMask();
            writeToAddr();
        }
    }

    private static void applyMask() {
        wsAddrFloat = wsAddrBin;
        for (int i = 0; i < 36; i++) {
            if (wsMask.charAt(i) != '0') {
                wsAddrFloat = wsAddrFloat.substring(0, i) + wsMask.charAt(i) + wsAddrFloat.substring(i + 1);
            }
        }
    }

    private static void decToBin() {
        wsAddrBin = "                                  ";
        for (int i = 35; i >= 0; i--) {
            wsD = wsAddrDec % 2;
            wsAddrDec /= 2;
            wsAddrBin = wsAddrBin.substring(0, i) + wsD + wsAddrBin.substring(i + 1);
        }
    }

    private static void binToDec() {
        wsAddrDec = 0;
        for (int i = 0; i < 36; i++) {
            wsAddrDec *= 2;
            if (wsAddrBin.charAt(i) == '1') {
                wsAddrDec += 1;
            }
        }
    }

    private static void writeToAddr() {
        int idx = 1;
        wsAddrTmp = wsAddrFloat;
        writeToAddrRecursive(idx);
    }

    private static void writeToAddrRecursive(int idx) {
        if (idx < 37) {
            if (wsAddrFloat.charAt(idx - 1) == 'X') {
                wsAddrTmp = wsAddrTmp.substring(0, idx - 1) + '0' + wsAddrTmp.substring(idx);
                writeToAddrRecursive(idx + 1);
                wsAddrTmp = wsAddrTmp.substring(0, idx - 1) + '1' + wsAddrTmp.substring(idx);
                writeToAddrRecursive(idx + 1);
            } else {
                writeToAddrRecursive(idx + 1);
            }
        } else {
            wsAddrBin = wsAddrTmp;
            binToDec();
            writeToMem();
        }
    }

    private static void writeToMem() {
        for (int j = 0; j < wsMemSize; j++) {
            if (wsMem[j][0] == wsAddrDec) {
                wsMem[j][1] = wsVal;
                return;
            }
        }
        wsMem[wsMemSize][0] = wsAddrDec;
        wsMem[wsMemSize][1] = wsVal;
        wsMemSize++;
    }

    private static void sumMemory() {
        for (int i = 0; i < MEM_SIZE; i++) {
            result += wsMem[i][1];
        }
    }
}