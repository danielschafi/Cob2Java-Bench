import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdventOfCode2019_2a {
    public static void main(String[] args) {
        String inputFilePath = "2.input";
        String inputRec = "";
        int[] opTable = new int[10000];
        int sepNumber = 0;
        int ptr = 0;
        int addr = 0;
        int val = 0;
        int res = 0;
        String resFmt = "";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            inputRec = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < inputRec.length(); i++) {
            if (inputRec.charAt(i) == ',') {
                sepNumber++;
            }
        }
        if (sepNumber < 9999) {
            sepNumber++;
        } else {
            sepNumber = 9999;
        }

        ptr = 0;
        for (int i = 0; i < sepNumber; i++) {
            int commaIndex = inputRec.indexOf(',', ptr);
            if (commaIndex == -1) {
                commaIndex = inputRec.length();
            }
            val = Integer.parseInt(inputRec.substring(ptr, commaIndex));
            opTable[i] = val;
            ptr = commaIndex + 1;
        }

        opTable[2] = 12;
        opTable[3] = 2;

        ptr = 0;
        while (ptr <= sepNumber) {
            switch (opTable[ptr]) {
                case 1:
                    ptr++;
                    addr = opTable[ptr] + 1;
                    res = opTable[addr];
                    ptr++;
                    addr = opTable[ptr] + 1;
                    res += opTable[addr];
                    ptr++;
                    addr = opTable[ptr] + 1;
                    opTable[addr] = res;
                    break;
                case 2:
                    ptr++;
                    addr = opTable[ptr] + 1;
                    res = opTable[addr];
                    ptr++;
                    addr = opTable[ptr] + 1;
                    res *= opTable[addr];
                    ptr++;
                    addr = opTable[ptr] + 1;
                    opTable[addr] = res;
                    break;
                case 99:
                    resFmt = String.format("%015d", opTable[1]);
                    System.out.println(resFmt);
                    return;
            }
            ptr++;
        }
    }
}