import java.io.*;
import java.nio.file.*;

public class AOC202116 {
    private static final int MAX_BITS = 5272;
    
    private int[] wsBits = new int[MAX_BITS];
    private int valDec = 0;
    private String valBin = "";
    private int d = 0;
    private int i = 1;
    private int j = 1;
    private int k = 1;
    private int len = 4;
    private int t = 0;
    private char x = ' ';
    private int y = 1;
    private int result = 0;
    private int n = 1;
    private String inputRecord = "";
    private int recLen = 0;

    public static void main(String[] args) {
        AOC202116 program = new AOC202116();
        program.run();
    }

    private void run() {
        try {
            readFile();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile() throws IOException {
        String filePath = "d16.input";
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            inputRecord = content.trim();
            recLen = inputRecord.length();
            processRecord();
        } catch (IOException e) {
            throw e;
        }
    }

    private void processRecord() {
        n = recLen;
        
        for (j = 0; j < n; j++) {
            x = inputRecord.charAt(j);
            
            int charCode = (int) x;
            if (charCode > 65) {
                y = charCode - 56;
            } else {
                y = charCode - 49;
            }
            
            valDec = y;
            decToBin();
            
            for (k = 0; k < 4; k++) {
                if (k < valBin.length()) {
                    wsBits[4 * j + k] = Character.getNumericValue(valBin.charAt(k));
                } else {
                    wsBits[4 * j + k] = 0;
                }
            }
        }
        
        j = 0;
        while (j <= n * 4 - 12) {
            processPacket();
        }
    }

    private void processPacket() {
        len = 3;
        valBin = "";
        valDec = 0;
        
        valBin = "" + wsBits[j] + wsBits[j + 1] + wsBits[j + 2];
        binToDec();
        result += valDec;
        j += 3;
        
        valBin = "" + wsBits[j] + wsBits[j + 1] + wsBits[j + 2];
        binToDec();
        j += 3;
        
        if (valDec == 4) {
            processPacketLiteral();
        } else {
            processPacketOperator();
        }
    }

    private void processPacketLiteral() {
        y = 1;
        while (y != 0) {
            y = wsBits[j];
            j += 5;
        }
    }

    private void processPacketOperator() {
        valBin = "";
        t = wsBits[j];
        j += 1;
        
        if (t == 0) {
            len = 15;
        } else {
            len = 11;
        }
        j += len;
    }

    private void decToBin() {
        valBin = "";
        int temp = valDec;
        for (i = len - 1; i >= 0; i--) {
            d = temp % 2;
            temp = temp / 2;
            valBin = d + valBin;
        }
    }

    private void binToDec() {
        valDec = 0;
        for (i = 0; i < len && i < valBin.length(); i++) {
            valDec = valDec * 2;
            if (valBin.charAt(i) == '1') {
                valDec = valDec + 1;
            }
        }
    }
}