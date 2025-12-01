import java.io.*;
import java.util.*;

public class AOC202014 {
    private String wsMask = "";
    private long wsAddr = 0;
    private long wsVal = 0;
    private long wsValDec = 0;
    private String wsValBin = "";
    private long[] wsMem = new long[65537];
    private long result = 0;
    private int wsD = 0;
    private int fileStatus = 0;
    private int i = 0;

    public static void main(String[] args) {
        AOC202014 program = new AOC202014();
        program.run();
    }

    private void run() {
        try {
            readFile();
            sumMemory();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("d14.input"));
        String line;
        while ((line = reader.readLine()) != null) {
            processRecord(line);
        }
        reader.close();
    }

    private void processRecord(String inputRecord) {
        if (inputRecord.length() >= 4 && inputRecord.substring(0, 4).equals("mask")) {
            if (inputRecord.length() >= 8) {
                wsMask = inputRecord.substring(7, Math.min(43, inputRecord.length()));
            }
        } else {
            String[] parts = inputRecord.split("=");
            if (parts.length == 2) {
                wsAddr = Long.parseLong(parts[0].trim().replaceAll("[^0-9]", ""));
                wsVal = Long.parseLong(parts[1].trim());
                wsValDec = wsVal;
                decToBin();
                applyMask();
                binToDec();
                wsMem[(int) wsAddr] = wsValDec;
            }
        }
    }

    private void applyMask() {
        for (i = 0; i < 36; i++) {
            if (i < wsMask.length() && wsMask.charAt(i) != 'X') {
                if (i < wsValBin.length()) {
                    wsValBin = wsValBin.substring(0, i) + wsMask.charAt(i) + wsValBin.substring(i + 1);
                }
            }
        }
    }

    private void decToBin() {
        wsValBin = "";
        for (int j = 0; j < 36; j++) {
            wsValBin = "0" + wsValBin;
        }
        long temp = wsValDec;
        for (i = 35; i >= 0; i--) {
            wsD = (int) (temp % 2);
            temp = temp / 2;
            wsValBin = wsValBin.substring(0, i) + wsD + wsValBin.substring(i + 1);
        }
    }

    private void binToDec() {
        wsValDec = 0;
        for (i = 0; i < 36; i++) {
            wsValDec = wsValDec * 2;
            if (i < wsValBin.length() && wsValBin.charAt(i) == '1') {
                wsValDec = wsValDec + 1;
            }
        }
    }

    private void sumMemory() {
        for (i = 1; i <= 65536; i++) {
            result += wsMem[i];
        }
    }
}