import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021161 {
    private static final int MAX_BITS = 5272;
    private static final int INPUT_RECORD_LENGTH = 1318;
    private int fileStatus = 0;
    private int recLen = 0;
    private int n = 1;
    private int[] wsBits = new int[MAX_BITS];
    private int valDec = 0;
    private char[] valBin = new char[16];
    private int d = 0;
    private int i = 1;
    private int j = 1;
    private int k = 1;
    private int len = 4;
    private int t = 0;
    private char x = ' ';
    private int y = 1;
    private int result = 0;

    public static void main(String[] args) {
        AOC2021161 program = new AOC2021161();
        program.run();
    }

    private void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d16.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recLen = line.length();
                if (recLen > 0) {
                    processRecord(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    private void processRecord(String inputRecord) {
        n = recLen;
        for (j = 0; j < n; j++) {
            x = inputRecord.charAt(j);
            y = (x > 'F') ? x - 56 : x - 49;
            valDec = y;
            decToBin();
            for (k = 0; k < 4; k++) {
                wsBits[4 * j + k] = valBin[k] - '0';
            }
        }

        j = 0;
        while (j < n * 4 - 11) {
            processPacket();
        }
    }

    private void processPacket() {
        len = 3;
        valBin = new char[16];
        valDec = 0;
        stringToBin(j, 3);
        binToDec();
        result += valDec;
        j += 3;

        stringToBin(j, 3);
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
        valBin = new char[16];
        t = wsBits[j];
        j++;

        if (t == 0) {
            len = 15;
        } else {
            len = 11;
        }
        j += len;
    }

    private void decToBin() {
        valBin = new char[16];
        for (i = len - 1; i >= 0; i--) {
            d = valDec % 2;
            valDec /= 2;
            valBin[i] = (char) (d + '0');
        }
    }

    private void binToDec() {
        valDec = 0;
        for (i = 0; i < len; i++) {
            valDec *= 2;
            if (valBin[i] == '1') {
                valDec += 1;
            }
        }
    }

    private void stringToBin(int start, int length) {
        valBin = new char[16];
        for (int m = 0; m < length; m++) {
            valBin[m] = (char) (wsBits[start + m] + '0');
        }
    }
}