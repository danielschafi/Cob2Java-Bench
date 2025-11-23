import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Append {
    private static final String PASS_FILENAME = "passfile";
    private static final int LRECL = 2048;
    private static final String COLON = ":";
    private static final String COMMA = ",";
    private static final String NEWLINE = "\n";

    private static class PassRecord {
        String account;
        String password;
        int uid;
        int gid;
        String fullname;
        String office;
        String extension;
        String homephone;
        String email;
        String homedir;
        String shell;

        public PassRecord(String account, String password, int uid, int gid, String fullname, String office,
                         String extension, String homephone, String email, String homedir, String shell) {
            this.account = account;
            this.password = password;
            this.uid = uid;
            this.gid = gid;
            this.fullname = fullname;
            this.office = office;
            this.extension = extension;
            this.homephone = homephone;
            this.email = email;
            this.homedir = homedir;
            this.shell = shell;
        }
    }

    private static List<PassRecord> records = new ArrayList<>();
    private static boolean debug = false;

    public static void main(String[] args) {
        if (args.length > 0 && "DEBUG".equals(args[0])) {
            debug = true;
        }

        initialFill();
        
        if (debug) {
            System.out.println("Initial data:");
            showRecords();
        }

        appendRecord();

        if (debug) {
            System.out.println(NEWLINE + "After append:");
            showRecords();
        }

        verifyAppend();
    }

    private static void initialFill() {
        openOutputPassFile();
        
        records.add(new PassRecord("jsmith", "x", 1001, 1000, "Joe Smith", "Room 1007",
                "(234)555-8917", "(234)555-0077", "jsmith@rosettacode.org", "/home/jsmith", "/bin/bash"));
        
        records.add(new PassRecord("jdoe", "x", 1002, 1000, "Jane Doe", "Room 1004",
                "(234)555-8914", "(234)555-0044", "jdoe@rosettacode.org", "/home/jdoe", "/bin/bash"));
        
        closePassFile();
    }

    private static void appendRecord() {
        PassRecord record = new PassRecord("xyz", "x", 1003, 1000, "X Yz", "Room 1003",
                "(234)555-8913", "(234)555-0033", "xyz@rosettacode.org", "/home/xyz", "/bin/bash");
        
        openExtendPassFile();
        writePassRecord(record);
        closePassFile();
    }

    private static void openOutputPassFile() {
        try {
            Files.deleteIfExists(Paths.get(PASS_FILENAME));
        } catch (IOException e) {
            System.err.println("Error deleting file: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void openExtendPassFile() {
        // File is opened in append mode when writing
    }

    private static void closePassFile() {
        // No explicit close needed for file operations in this implementation
    }

    private static void writePassRecord(PassRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append(record.account).append(COLON)
          .append(record.password).append(COLON)
          .append(String.valueOf(record.uid)).append(COLON)
          .append(String.valueOf(record.gid)).append(COLON)
          .append(record.fullname.trim()).append(COMMA)
          .append(record.office.trim()).append(COMMA)
          .append(record.extension.trim()).append(COMMA)
          .append(record.homephone.trim()).append(COMMA)
          .append(record.email).append(COLON)
          .append(record.homedir.trim()).append(COLON)
          .append(record.shell.trim());

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(PASS_FILENAME), 
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(sb.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void showRecords() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(PASS_FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void verifyAppend() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(PASS_FILENAME))) {
            String line;
            int tally = 0;
            boolean foundKeyAccount = false;
            
            while ((line = reader.readLine()) != null) {
                tally++;
                String[] parts = line.split(COLON, -1);
                if (parts.length > 0 && "xyz".equals(parts[0])) {
                    foundKeyAccount = true;
                    break;
                }
            }
            
            if ((foundKeyAccount && tally <= 2) || !foundKeyAccount) {
                System.err.println("error: appended record not found in correct position");
            } else {
                System.out.print("Appended record: ");
                // Re-read the file to find and display the appended record
                try (BufferedReader reader2 = Files.newBufferedReader(Paths.get(PASS_FILENAME))) {
                    int count = 0;
                    String line2;
                    while ((line2 = reader2.readLine()) != null) {
                        count++;
                        if (count == tally) {
                            System.out.println(line2);
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
    }
}