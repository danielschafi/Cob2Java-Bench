import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC2021161 {
    private static final String INPUT_FILE = "d16.input";
    private static List<Integer> wsBits = new ArrayList<>();
    private static int result = 0;
    private static int j = 1;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println(result);
    }

    private static void processRecord(String inputRecord) {
        int n = inputRecord.length();
        for (int jj = 0; jj < n; jj++) {
            char x = inputRecord.charAt(jj);
            int y;
            if (x >= 'A' && x <= 'F') {
                y = x - 'A' + 10;
            } else {
                y = x - '0';
            }
            
            int valDec = y;
            String valBin = decToBin(valDec, 4);
            
            for (int k = 0; k < 4; k++) {
                wsBits.add(Character.getNumericValue(valBin.charAt(k)));
            }
        }
        
        j = 1;
        while (j <= n * 4 - 11) {
            processPacket();
        }
    }

    private static void processPacket() {
        int len = 3;
        int valDec = 0;
        StringBuilder valBin = new StringBuilder();
        
        for (int i = 0; i < 3; i++) {
            valBin.append(wsBits.get(j - 1 + i));
        }
        valDec = binToDec(valBin.toString());
        result += valDec;
        j += 3;
        
        valBin.setLength(0);
        for (int i = 0; i < 3; i++) {
            valBin.append(wsBits.get(j - 1 + i));
        }
        valDec = binToDec(valBin.toString());
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
            y = wsBits.get(j - 1);
            j += 5;
        }
    }

    private static void processPacketOperator() {
        int t = wsBits.get(j - 1);
        j += 1;
        
        int len;
        if (t == 0) {
            len = 15;
        } else {
            len = 11;
        }
        
        j += len;
    }

    private static String decToBin(int valDec, int len) {
        StringBuilder valBin = new StringBuilder();
        for (int i = len; i >= 1; i--) {
            valBin.insert(0, valDec % 2);
            valDec /= 2;
        }
        return valBin.toString();
    }

    private static int binToDec(String valBin) {
        int valDec = 0;
        for (int i = 0; i < valBin.length(); i++) {
            valDec = valDec * 2 + Character.getNumericValue(valBin.charAt(i));
        }
        return valDec;
    }
}