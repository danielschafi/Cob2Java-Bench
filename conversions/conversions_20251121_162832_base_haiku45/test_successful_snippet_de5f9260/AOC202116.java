import java.io.*;
import java.nio.file.*;

public class AOC202116 {
    private static int[] wsBits = new int[5272];
    private static int valDec = 0;
    private static String valBin = "";
    private static int j = 0;
    private static int result = 0;
    private static int len = 4;

    public static void main(String[] args) {
        try {
            String content = new String(Files.readAllBytes(Paths.get("d16.input")));
            String inputRecord = content.trim();
            int recLen = inputRecord.length();
            
            processRecord(inputRecord, recLen);
            
            j = 1;
            processPackets(recLen);
            
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processRecord(String inputRecord, int recLen) {
        for (int jIdx = 0; jIdx < recLen; jIdx++) {
            char x = inputRecord.charAt(jIdx);
            int y = (int) x;
            
            if (y > 65) {
                y -= 56;
            } else {
                y -= 49;
            }
            
            valDec = y;
            decToBin(4);
            
            for (int k = 0; k < 4; k++) {
                wsBits[4 * jIdx + k] = Character.getNumericValue(valBin.charAt(k));
            }
        }
    }

    private static void processPackets(int recLen) {
        int maxJ = recLen * 4 - 11;
        while (j <= maxJ) {
            processPacket();
        }
    }

    private static void processPacket() {
        len = 3;
        valBin = "";
        valDec = 0;
        
        valBin = String.valueOf(wsBits[j - 1]) + wsBits[j] + wsBits[j + 1];
        binToDec();
        result += valDec;
        j += 3;
        
        valBin = String.valueOf(wsBits[j - 1]) + wsBits[j] + wsBits[j + 1];
        binToDec();
        int typeId = valDec;
        j += 3;
        
        if (typeId == 4) {
            processPacketLiteral();
        } else {
            processPacketOperator();
        }
    }

    private static void processPacketLiteral() {
        int y = 1;
        while (y != 0) {
            y = wsBits[j - 1];
            j += 5;
        }
    }

    private static void processPacketOperator() {
        int t = wsBits[j - 1];
        j += 1;
        
        if (t == 0) {
            len = 15;
        } else {
            len = 11;
        }
        j += len;
    }

    private static void decToBin(int length) {
        valBin = "";
        int tempVal = valDec;
        for (int i = length - 1; i >= 0; i--) {
            int d = tempVal % 2;
            tempVal = tempVal / 2;
            valBin = d + valBin;
        }
        while (valBin.length() < length) {
            valBin = "0" + valBin;
        }
    }

    private static void binToDec() {
        valDec = 0;
        for (int i = 0; i < len; i++) {
            valDec = valDec * 2;
            if (valBin.charAt(i) == '1') {
                valDec = valDec + 1;
            }
        }
    }
}