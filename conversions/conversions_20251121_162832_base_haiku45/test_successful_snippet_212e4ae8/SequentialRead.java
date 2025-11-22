import java.io.*;
import java.nio.file.*;

public class SequentialRead {
    static class Details {
        int detailsId;
        String detailsSurname;
        String initials;
        String detailsBirthday;
        String someCode;
    }

    static String fileStatus = "00";
    static boolean eofFlag = false;
    static RandomAccessFile dataFile = null;

    public static void main(String[] args) {
        mainProcedure();
    }

    static void mainProcedure() {
        try {
            dataFile = new RandomAccessFile("database.dat", "r");
            fileStatus = "00";
        } catch (IOException e) {
            fileStatus = "01";
        }

        if (!fileStatus.equals("00")) {
            System.out.println("Error opening the DB file, program will exit.");
            System.exit(0);
        }

        Details details = readRecord();
        if (details == null) {
            eofFlag = true;
        } else {
            eofFlag = false;
            displayDetails(details);
        }

        while (!eofFlag) {
            details = readRecord();
            if (details == null) {
                eofFlag = true;
            } else {
                displayDetails(details);
            }
        }

        try {
            if (dataFile != null) {
                dataFile.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

    static Details readRecord() {
        try {
            byte[] buffer = new byte[32];
            int bytesRead = dataFile.read(buffer);

            if (bytesRead == -1) {
                return null;
            }

            Details details = new Details();
            String line = new String(buffer).trim();
            String[] parts = line.split("\\|");

            if (parts.length >= 5) {
                details.detailsId = Integer.parseInt(parts[0].trim());
                details.detailsSurname = parts[1].trim();
                details.initials = parts[2].trim();
                details.detailsBirthday = parts[3].trim();
                details.someCode = parts[4].trim();
            }

            return details;
        } catch (IOException e) {
            return null;
        }
    }

    static void displayDetails(Details details) {
        System.out.println(details.detailsId + "|" + details.detailsSurname + "|" + 
                          details.initials + "|" + details.detailsBirthday + "|" + details.someCode);
        System.out.println("DETAILS-ID: " + details.detailsId);
        System.out.println("DETAILS-NAME: " + details.detailsSurname);
        System.out.println("DETAILS-BIRTHDAY: " + details.detailsBirthday);
    }
}