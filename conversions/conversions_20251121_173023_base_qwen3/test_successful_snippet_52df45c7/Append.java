import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Append {
    private static final int LRECL = 2048;
    private static final String PASS_FILENAME = "passfile";
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
    }

    private static class FileStatus {
        String status;
        boolean ok;
        boolean eof;

        public FileStatus(String status) {
            this.status = status;
            this.ok = status.compareTo("00") >= 0 && status.compareTo("09") <= 0;
            this.eof = "10".equals(status);
        }
    }

    private static int returnCode = 0;
    private static String fileAction = "";
    private static FileStatus passStatus = new FileStatus("00");
    private static int passLength = 0;
    private static int totalLength = 0;
    private static int tally = 0;
    private static final char COLON = ':';
    private static final char COMMA = ',';
    private static final String RECORD_SEPARATOR = "\n";

    private static List<String> passFileContent = new ArrayList<>();
    private static List<PassRecord> records = new ArrayList<>();

    public static void main(String[] args) {
        initialFill();
        
        #ifdef DEBUG
        System.out.println("Initial data:");
        showRecords();
        #endif

        appendRecord();

        #ifdef DEBUG
        System.out.println(NEWLINE + "After append:");
        showRecords();
        #endif

        verifyAppend();
        System.exit(returnCode);
    }

    private static void initialFill() {
        openOutputPassFile();

        PassRecord record1 = new PassRecord();
        record1.account = "jsmith";
        record1.password = "x";
        record1.uid = 1001;
        record1.gid = 1000;
        record1.fullname = "Joe Smith";
        record1.office = "Room 1007";
        record1.extension = "(234)555-8917";
        record1.homephone = "(234)555-0077";
        record1.email = "jsmith@rosettacode.org";
        record1.homedir = "/home/jsmith";
        record1.shell = "/bin/bash";
        records.add(record1);
        writePassRecord(record1);

        PassRecord record2 = new PassRecord();
        record2.account = "jdoe";
        record2.password = "x";
        record2.uid = 1002;
        record2.gid = 1000;
        record2.fullname = "Jane Doe";
        record2.office = "Room 1004";
        record2.extension = "(234)555-8914";
        record2.homephone = "(234)555-0044";
        record2.email = "jdoe@rosettacode.org";
        record2.homedir = "/home/jdoe";
        record2.shell = "/bin/bash";
        records.add(record2);
        writePassRecord(record2);

        closePassFile();
    }

    private static void checkPassFile() {
        if (!passStatus.ok) {
            fileError();
        }
    }

    private static void checkPassWithEof() {
        if (!passStatus.ok && !passStatus.eof) {
            fileError();
        }
    }

    private static void fileError() {
        System.err.println("error " + fileAction + " " + PASS_FILENAME + " " + passStatus.status);
        returnCode = 1;
        System.exit(returnCode);
    }

    private static void appendRecord() {
        PassRecord record = new PassRecord();
        record.account = "xyz";
        record.password = "x";
        record.uid = 1003;
        record.gid = 1000;
        record.fullname = "X Yz";
        record.office = "Room 1003";
        record.extension = "(234)555-8913";
        record.homephone = "(234)555-0033";
        record.email = "xyz@rosettacode.org";
        record.homedir = "/home/xyz";
        record.shell = "/bin/bash";
        records.add(record);

        openExtendPassFile();
        writePassRecord(record);
        closePassFile();
    }

    private static void openOutputPassFile() {
        try {
            Files.deleteIfExists(Paths.get(PASS_FILENAME));
        } catch (IOException ignored) {}
        fileAction = "open output";
        passStatus = new FileStatus("00");
        checkPassFile();
    }

    private static void openExtendPassFile() {
        fileAction = "open extend";
        passStatus = new FileStatus("00");
        checkPassFile();
    }

    private static void openInputPassFile() {
        fileAction = "open input";
        passStatus = new FileStatus("00");
        checkPassFile();
    }

    private static void closePassFile() {
        fileAction = "closing";
        passStatus = new FileStatus("00");
        checkPassFile();
    }

    private static void writePassRecord(PassRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append(record.account).append(COLON)
          .append(record.password).append(COLON)
          .append(String.format("%d", record.uid)).append(COLON)
          .append(String.format("%d", record.gid)).append(COLON)
          .append(record.fullname).append(COMMA)
          .append(record.office).append(COMMA)
          .append(record.extension).append(COMMA)
          .append(record.homephone).append(COMMA)
          .append(record.email).append(COLON)
          .append(record.homedir).append(COLON)
          .append(record.shell);

        String line = sb.toString();
        passFileContent.add(line);
        passLength = line.length();
        totalLength = passLength;

        try {
            Files.write(Paths.get(PASS_FILENAME), Collections.singleton(line), 
                       StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("error: fd-pass-record truncated at " + totalLength);
        }
    }

    private static void readPassFile() {
        // This method would read from the file but we're simulating it with our list
        if (tally < passFileContent.size()) {
            String content = passFileContent.get(tally);
            tally++;
            passStatus = new FileStatus("00");
        } else {
            passStatus = new FileStatus("10"); // EOF
        }
        checkPassWithEof();
    }

    private static void showRecords() {
        openInputPassFile();
        tally = 0;
        readPassFile();
        while (!passStatus.eof) {
            showPassRecord();
            readPassFile();
        }
        closePassFile();
    }

    private static void showPassRecord() {
        if (tally > 0 && tally <= passFileContent.size()) {
            System.out.println(passFileContent.get(tally - 1));
        }
    }

    private static void verifyAppend() {
        openInputPassFile();
        tally = 0;
        readPassFile();
        while (!passStatus.eof) {
            String content = passFileContent.get(tally - 1);
            String[] parts = content.split(":");
            if (parts.length > 0 && "xyz".equals(parts[0])) {
                if (tally <= 2) {
                    System.out.println("Appended record: ");
                    showPassRecord();
                } else {
                    System.err.println("error: appended record not found in correct position");
                }
                break;
            }
            tally++;
            readPassFile();
        }
        closePassFile();
    }
}