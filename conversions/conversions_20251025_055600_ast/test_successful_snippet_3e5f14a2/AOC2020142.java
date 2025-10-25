import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020142 {
    private static final int MEM_SIZE = 512000;
    private static final int ADDR_LENGTH = 36;
    private static final int MAX_LINE_LENGTH = 99;

    private int fileStatus;
    private int recLen;
    private String wsMask = new String(new char[ADDR_LENGTH]).replace('\0', ' ');
    private long wsAddr;
    private long wsVal;
    private long wsAddrDec;
    private String wsAddrFloat = new String(new char[ADDR_LENGTH]).replace('\0', ' ');
    private String wsAddrTmp = new String(new char[ADDR_LENGTH]).replace('\0', ' ');
    private String wsAddrBin = new String(new char[ADDR_LENGTH]).replace('\0', ' ');
    private int wsMemSize;
    private long[] wsMemAddr = new long[MEM_SIZE];
    private long[] wsMemVal = new long[MEM_SIZE];
    private long result;
    private int i;
    private int j;
    private int idx;

    public static void main(String[] args) {
        AOC2020142 program = new AOC2020142();
        program.main();
    }

    private void main() {
        try (BufferedReader br = new BufferedReader(new FileReader("d14.input"))) {
            while ((recLen = br.readLine().length()) != -1) {
                String inputRecord = br.readLine();
                if (inputRecord.substring(0, 4).equals("mask")) {
                    wsMask = inputRecord.substring(7, 43);
                } else {
                    String[] parts = inputRecord.substring(4).split("=");
                    wsAddr = Long.parseLong(parts[0].trim());
                    wsVal = Long.parseLong(parts[1].trim());
                    wsAddrDec = wsAddr;
                    decToBin();
                    applyMask();
                    writeToAddr();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        sumMemory();
        System.out.println(result);
    }

    private void applyMask() {
        wsAddrFloat = wsAddrBin;
        for (i = 0; i < ADDR_LENGTH; i++) {
            if (wsMask.charAt(i) != '0') {
                wsAddrFloat = wsAddrFloat.substring(0, i) + wsMask.charAt(i) + wsAddrFloat.substring(i + 1);
            }
        }
    }

    private void decToBin() {
        wsAddrBin = new String(new char[ADDR_LENGTH]).replace('\0', ' ');
        for (i = ADDR_LENGTH - 1; i >= 0; i--) {
            long remainder = wsAddrDec % 2;
            wsAddrBin = wsAddrBin.substring(0, i) + remainder + wsAddrBin.substring(i + 1);
            wsAddrDec /= 2;
        }
    }

    private void writeToAddr() {
        idx = 1;
        wsAddrTmp = wsAddrFloat;
        writeToAddrRecursive();
    }

    private void writeToAddrRecursive() {
        if (idx < ADDR_LENGTH) {
            if (wsAddrFloat.charAt(idx) == 'X') {
                wsAddrTmp = wsAddrTmp.substring(0, idx) + '0' + wsAddrTmp.substring(idx + 1);
                idx++;
                writeToAddrRecursive();
                idx--;
                wsAddrTmp = wsAddrTmp.substring(0, idx) + '1' + wsAddrTmp.substring(idx + 1);
                idx++;
                writeToAddrRecursive();
                idx--;
            } else {
                idx++;
                writeToAddrRecursive();
                idx--;
            }
        } else {
            wsAddrBin = wsAddrTmp;
            binToDec();
            writeToMem();
        }
    }

    private void writeToMem() {
        for (j = 0; j < wsMemSize; j++) {
            if (wsMemAddr[j] == wsAddrDec) {
                wsMemVal[j] = wsVal;
                return;
            }
        }
        if (j >= wsMemSize) {
            wsMemSize++;
            wsMemAddr[j] = wsAddrDec;
            wsMemVal[j] = wsVal;
        }
    }

    private void sumMemory() {
        for (i = 0; i < MEM_SIZE; i++) {
            result += wsMemVal[i];
        }
    }
}