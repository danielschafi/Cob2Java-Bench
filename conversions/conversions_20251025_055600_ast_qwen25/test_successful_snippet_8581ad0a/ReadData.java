import java.io.*;
import java.nio.file.*;

public class ReadData {
    public static void main(String[] args) {
        String fileName = "data.cobol.dat";
        String fileStatus = "00";
        boolean fileNotFound = false;
        boolean reachEndOfFile = false;
        boolean everythingOK = true;
        boolean endOfFile = false;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("**********")) {
                    endOfFile = true;
                    break;
                }
                String fname = line.substring(0, 10).trim();
                String lname = line.substring(10, 20).trim();
                String ssnA = line.substring(20, 23).trim();
                String ssnB = line.substring(23, 25).trim();
                String ssnC = line.substring(25, 29).trim();
                String city = line.substring(29, 39).trim();

                System.out.println(fname + ", " + lname + " SSN: " + ssnA + "-" + ssnB + "-" + ssnC);
            }
        } catch (IOException e) {
            fileStatus = "35";
            fileNotFound = true;
        }

        System.out.println("ERROR CODE : " + fileStatus);

        if (fileNotFound) {
            System.out.println("ERROR : File NOT found");
            System.exit(1);
        }

        if (endOfFile) {
            System.out.println("File Empty");
        }

        if (everythingOK) {
            System.out.println("No errors. Reading...");
        }

        if (!fileStatus.equals("00")) {
            System.out.println("You are not catching this error!");
        }

        if (endOfFile) {
            System.out.println("End of File Reached");
        }
    }
}