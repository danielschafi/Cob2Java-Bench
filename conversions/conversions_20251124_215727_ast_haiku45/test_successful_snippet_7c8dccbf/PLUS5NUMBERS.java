import java.io.*;
import java.nio.file.*;
import java.util.*;

public class PLUS5NUMBERS {
    private static String eofSwitch = "N";
    private static int result = 0;
    private static String neResult = "";
    private static int originalNumber = 0;
    private static int outNumber = 0;
    private static BufferedReader inFileReader;
    private static BufferedWriter outFileWriter;

    public static void main(String[] args) {
        initialize();
        while (!eofSwitch.equals("Y")) {
            processRecords();
        }
        terminate();
    }

    private static void initialize() {
        try {
            inFileReader = new BufferedReader(new FileReader("numbers.txt"));
            outFileWriter = new BufferedWriter(new FileWriter("newNumbers.txt", true));
            readNextLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processRecords() {
        try {
            result = originalNumber + 5;
            neResult = String.format("%5d", result).replace(' ', '0');
            outNumber = result;
            outFileWriter.write(String.valueOf(outNumber));
            outFileWriter.newLine();
            System.out.println(neResult);
            readNextLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readNextLine() {
        try {
            String line = inFileReader.readLine();
            if (line == null) {
                eofSwitch = "Y";
            } else {
                originalNumber = Integer.parseInt(line.trim());
            }
        } catch (IOException e) {
            eofSwitch = "Y";
        }
    }

    private static void terminate() {
        try {
            if (inFileReader != null) {
                inFileReader.close();
            }
            if (outFileWriter != null) {
                outFileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}