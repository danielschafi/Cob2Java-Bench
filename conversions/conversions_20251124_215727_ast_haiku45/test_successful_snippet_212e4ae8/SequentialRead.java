import java.io.*;
import java.nio.file.*;

public class SequentialRead {
    static class Details {
        long detailsId;
        String detailsSurname;
        String initials;
        String detailsBirthday;
        String someCode;
    }

    static String fileStatus = "";
    static String eof = "N";
    static Details details = new Details();
    static RandomAccessFile dataFile;

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
            return;
        }

        if (readRecord()) {
            eof = "N";
            displayDetS();
        } else {
            eof = "Y";
        }

        while (!eof.equals("Y")) {
            if (readRecord()) {
                displayDetS();
            } else {
                eof = "Y";
            }
        }

        try {
            dataFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean readRecord() {
        try {
            StringBuilder line = new StringBuilder();
            String input;
            
            if ((input = readLine()) == null) {
                return false;
            }

            if (input.length() >= 7) {
                details.detailsId = Long.parseLong(input.substring(0, 7).trim());
            }
            if (input.length() >= 15) {
                details.detailsSurname = input.substring(7, 15);
            }
            if (input.length() >= 17) {
                details.initials = input.substring(15, 17);
            }
            if (input.length() >= 25) {
                details.detailsBirthday = input.substring(17, 25);
            }
            if (input.length() >= 30) {
                details.someCode = input.substring(25, 30);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static String readLine() {
        try {
            byte[] buffer = new byte[1024];
            int bytesRead = dataFile.read(buffer);
            if (bytesRead == -1) {
                return null;
            }
            String line = new String(buffer, 0, bytesRead).trim();
            if (line.isEmpty()) {
                return null;
            }
            return line;
        } catch (IOException e) {
            return null;
        }
    }

    static void displayDetS() {
        System.out.println(details.detailsId + " " + details.detailsSurname + " " + 
                         details.initials + " " + details.detailsBirthday + " " + details.someCode);
        System.out.println("DETAILS-ID: " + details.detailsId);
        System.out.println("DETAILS-NAME: " + details.detailsSurname);
        System.out.println("DETAILS-BIRTHDAY: " + details.detailsBirthday);
    }
}