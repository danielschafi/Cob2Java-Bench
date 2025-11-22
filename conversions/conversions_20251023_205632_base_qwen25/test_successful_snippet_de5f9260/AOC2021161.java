import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021161 {
    private static final int MAX_BITS = 5272;
    private static int fileStatus = 0;
    private static int recLen;
    private static int n = 1;
    private static int[] wsBits = new int[MAX_BITS];
    private static int valDec = 0;
    private static String valBin = "                ";
    private static int d;
    private static int j = 1;
    private static int k;
    private static int len;
    private static int t;
    private static char x;
    private static int y = 1;
    private static int result = 0;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d16.input"))) {
            String inputRecord;
            while ((inputRecord = reader.readLine()) != null) {
                recLen = inputRecord.length();
                n = recLen;
                processRecord(inputRecord);
            }
        } catch (IOException e) {
            fileStatus = 1;
        }
        System.out.println(result);
    }

    private static void processRecord(String inputRecord) {
        for (j = 1; j <= n; j++) {
            x = inputRecord.charAt(j - 1);
            y = Character.getNumericValue(x);
            if (y > 65) {
                y -= 56;
            } else {
                y -= 49;
            }
            valDec = y;
            decToBin();
            for (k = 1; k <= 4; k++) {
                wsBits[4 * j - 4 + k] = valBin.charAt(k - 1) - '0';
            }
        }
        j = 1;
        while (j <= n * 4 - 11) {
            processPacket();
        }
    }

    private static void processPacket() {
        len = 3;
        valBin = "               ";
        valDec = 0;
        for (int i = 0; i < len; i++) {
            valBin = valBin.substring(0, i) + wsBits[j + i - 1] + valBin.substring(i + 1);
        }
        binToDec();
        result += valDec;
        j += 3;
        for (int i = 0; i < len; i++) {
            valBin = valBin.substring(0, i) + wsBits[j + i - 1] + valBin.substring(i + 1);
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
        y = 1;
        while (y != 0) {
            y = wsBits[j];
            j += 5;
        }
    }

    private static void processPacketOperator() {
        valBin = "               ";
        t = wsBits[j];
        j++;
        if (t == 0) {
            len = 15;
        } else {
            len = 11;
        }
        j += len;
    }

    private static void decToBin() {
        valBin = "                ";
        for (i = len; i > 0; i--) {
            d = valDec % 2;
            valDec /= 2;
            valBin = valBin.substring(0, i - 1) + d + valBin.substring(i);
        }
    }

    private static void binToDec() {
        valDec = 0;
        for (i = 1; i <= len; i++) {
            valDec *= 2;
            if (valBin.charAt(i - 1) == '1') {
                valDec += 1;
            }
        }
    }
}