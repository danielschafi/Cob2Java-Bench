import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;

public class ReadData {
    public static void main(String[] args) {
        String fileName = "data.cobol.dat";
        Path path = Paths.get(fileName);
        BufferedReader reader = null;
        String line;
        String[] parts;
        String fname, lname, ssnA, ssnB, ssnC;
        boolean endOfFile = false;

        try {
            reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("ERROR CODE : 35");
            System.out.println("ERROR : File NOT found");
            return;
        }

        System.out.println("ERROR CODE : 00");
        System.out.println("No errors. Reading...");

        try {
            while ((line = reader.readLine()) != null) {
                parts = line.split(" ");
                if (parts.length < 6) {
                    continue;
                }
                fname = parts[0].trim();
                lname = parts[1].trim();
                ssnA = parts[2].trim();
                ssnB = parts[3].trim();
                ssnC = parts[4].trim();
                System.out.println(fname + " " + lname + " SSN: " + ssnA + "-" + ssnB + "-" + ssnC);
            }
        } catch (IOException e) {
            System.out.println("ERROR CODE : 99");
            System.out.println("You are not catching this error!");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("ERROR CODE : 99");
                System.out.println("You are not catching this error!");
            }
        }

        System.out.println("ERROR CODE : 10");
        System.out.println("End of File Reached");
    }
}