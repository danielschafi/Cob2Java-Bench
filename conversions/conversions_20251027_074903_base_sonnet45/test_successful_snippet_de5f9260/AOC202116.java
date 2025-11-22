import java.io.*;
import java.nio.file.*;

public class AOC202116 {
    private static final int MAX_BITS = 5272;
    private static int[] wsBits = new int[MAX_BITS];
    private static int valDec = 0;
    private static char[] valBin = new char[16];
    private static int j = 1;
    private static int len = 4;
    private static int result = 0;

    public static void main(String[] args) {
        try {
            String input = new String(Files.readAllBytes(Paths.get("d16.input"))).trim();
            processRecord(input);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processRecord(String inputRecord) {
        int n = inputRecord.length();
        int bitIndex = 1;

        for (int idx = 0; idx < n; idx++) {
            char x = inputRecord.charAt(idx);
            int y = (int) x;

            if (y > 65) {
                y -= 56;
            } else {
                y -= 49;
            }

            valDec = y;
            decToBin();

            for (int k = 1; k <= 4; k++) {
                wsBits[4 * (idx + 1) - 4 + k] = valBin[k - 1] - '0';
            }
        }

        j = 1;
        while (j <= n * 4 - 11) {
            processPacket(n);
        }
    }

    private static void processPacket(int n) {
        len = 3;
        clearValBin();
        valDec = 0;

        StringBuilder sb = new StringBuilder();
        sb.append(wsBits[j]).append(wsBits[j + 1]).append(wsBits[j + 2]);
        for (int i = 0; i < sb.length(); i++) {
            valBin[i] = sb.charAt(i);
        }
        binToDec();
        result += valDec;
        j += 3;

        sb = new StringBuilder();
        sb.append(wsBits[j]).append(wsBits[j + 1]).append(wsBits[j + 2]);
        for (int i = 0; i < sb.length(); i++) {
            valBin[i] = sb.charAt(i);
        }
        binToDec();
        j += 3;

        if (valDec == 4) {
            processPacketLiteral();
        } else {
            processPacketOperator();
        }
    }

    private static void processPacketLiteral() {
        int y = 1;
        while (y != 0) {
            y = wsBits[j];
            j += 5;
        }
    }

    private static void processPacketOperator() {
        clearValBin();
        int t = wsBits[j];
        j += 1;

        if (t == 0) {
            len = 15;
        } else {
            len = 11;
        }
        j += len;
    }

    private static void decToBin() {
        for (int i = 0; i < valBin.length; i++) {
            valBin[i] = ' ';
        }

        int tempDec = valDec;
        for (int i = len - 1; i >= 0; i--) {
            int d = tempDec % 2;
            tempDec = tempDec / 2;
            valBin[i] = (char) ('0' + d);
        }
        valDec = tempDec;
    }

    private static void binToDec() {
        valDec = 0;
        for (int i = 0; i < len; i++) {
            valDec = valDec * 2;
            if (valBin[i] == '1') {
                valDec = valDec + 1;
            }
        }
    }

    private static void clearValBin() {
        for (int i = 0; i < valBin.length; i++) {
            valBin[i] = '0';
        }
    }
}