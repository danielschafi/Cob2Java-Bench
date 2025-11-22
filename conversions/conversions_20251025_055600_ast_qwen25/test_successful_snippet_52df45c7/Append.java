import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Append {
    private static final String PASS_FILENAME = "passfile";
    private static final int LRECL = 2048;
    private static final String COLON = ":";
    private static final String COMMA_MARK = ",";
    private static final String NEWLINE = "\n";

    private static String passStatus;
    private static boolean okStatus;
    private static boolean eofPass;
    private static int passLength;
    private static int totalLength;
    private static String fileAction;

    private static String account;
    private static String password;
    private static int uid;
    private static int gid;
    private static String fullname;
    private static String office;
    private static String extension;
    private static String homephone;
    private static String email;
    private static String homedir;
    private static String shell;

    public static void main(String[] args) {
        initialFill();

        if (System.getenv("DEBUG") != null) {
            System.out.println("Initial data:");
            showRecords();
        }

        appendRecord();

        if (System.getenv("DEBUG") != null) {
            System.out.println(NEWLINE + "After append:");
            showRecords();
        }

        verifyAppend();
    }

    private static void initialFill() {
        openOutputPassFile();

        account = "jsmith";
        password = "x";
        uid = 1001;
        gid = 1000;
        fullname = "Joe Smith";
        office = "Room 1007";
        extension = "(234)555-8917";
        homephone = "(234)555-0077";
        email = "jsmith@rosettacode.org";
        homedir = "/home/jsmith";
        shell = "/bin/bash";
        writePassRecord();

        account = "jdoe";
        password = "x";
        uid = 1002;
        gid = 1000;
        fullname = "Jane Doe";
        office = "Room 1004";
        extension = "(234)555-8914";
        homephone = "(234)555-0044";
        email = "jdoe@rosettacode.org";
        homedir = "/home/jdoe";
        shell = "/bin/bash";
        writePassRecord();

        closePassFile();
    }

    private static void checkPassFile() {
        if (!okStatus) {
            fileError();
        }
    }

    private static void checkPassWithEof() {
        if (!okStatus && !eofPass) {
            fileError();
        }
    }

    private static void fileError() {
        System.err.println("error " + fileAction + " " + PASS_FILENAME + " " + passStatus);
        System.exit(1);
    }

    private static void appendRecord() {
        account = "xyz";
        password = "x";
        uid = 1003;
        gid = 1000;
        fullname = "X Yz";
        office = "Room 1003";
        extension = "(234)555-8913";
        homephone = "(234)555-0033";
        email = "xyz@rosettacode.org";
        homedir = "/home/xyz";
        shell = "/bin/bash";

        openExtendPassFile();
        writePassRecord();
        closePassFile();
    }

    private static void openOutputPassFile() {
        try (RandomAccessFile file = new RandomAccessFile(PASS_FILENAME, "rw")) {
            fileAction = "open output";
            passStatus = "00";
            okStatus = true;
            checkPassFile();
        } catch (IOException e) {
            passStatus = "99";
            okStatus = false;
            checkPassFile();
        }
    }

    private static void openExtendPassFile() {
        try (RandomAccessFile file = new RandomAccessFile(PASS_FILENAME, "rw")) {
            file.seek(file.length());
            fileAction = "open extend";
            passStatus = "00";
            okStatus = true;
            checkPassFile();
        } catch (IOException e) {
            passStatus = "99";
            okStatus = false;
            checkPassFile();
        }
    }

    private static void openInputPassFile() {
        try (RandomAccessFile file = new RandomAccessFile(PASS_FILENAME, "r")) {
            fileAction = "open input";
            passStatus = "00";
            okStatus = true;
            checkPassFile();
        } catch (IOException e) {
            passStatus = "99";
            okStatus = false;
            checkPassFile();
        }
    }

    private static void closePassFile() {
        try (RandomAccessFile file = new RandomAccessFile(PASS_FILENAME, "rw")) {
            fileAction = "closing";
            passStatus = "00";
            okStatus = true;
            checkPassFile();
        } catch (IOException e) {
            passStatus = "99";
            okStatus = false;
            checkPassFile();
        }
    }

    private static void writePassRecord() {
        StringBuilder sb = new StringBuilder();
        sb.append(account).append(COLON)
          .append(password).append(COLON)
          .append(String.format("%04d", uid)).append(COLON)
          .append(String.format("%04d", gid)).append(COLON)
          .append(fullname.trim()).append(COMMA_MARK)
          .append(office.trim()).append(COMMA_MARK)
          .append(extension.trim()).append(COMMA_MARK)
          .append(homephone.trim()).append(COMMA_MARK)
          .append(email).append(COLON)
          .append(homedir.trim()).append(COLON)
          .append(shell.trim());

        String record = sb.toString();
        totalLength = record.length();
        passLength = totalLength;

        try (RandomAccessFile file = new RandomAccessFile(PASS_FILENAME, "rw")) {
            file.seek(file.length());
            file.writeBytes(record);
            fileAction = "writing";
            passStatus = "00";
            okStatus = true;
            checkPassFile();
        } catch (IOException e) {
            passStatus = "99";
            okStatus = false;
            checkPassFile();
        }
    }

    private static void readPassFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PASS_FILENAME))) {
            if (reader.ready()) {
                String line = reader.readLine();
                if (line != null) {
                    fileAction = "reading";
                    passStatus = "00";
                    okStatus = true;
                    eofPass = false;
                    checkPassWithEof();
                } else {
                    eofPass = true;
                }
            } else {
                eofPass = true;
            }
        } catch (IOException e) {
            passStatus = "99";
            okStatus = false;
            checkPassWithEof();
        }
    }

    private static void showRecords() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PASS_FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            passStatus = "99";
            okStatus = false;
            checkPassFile();
        }
    }

    private static void verifyAppend() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PASS_FILENAME))) {
            int tally = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                tally++;
                String[] parts = line.split(COLON);
                account = parts[0];
                if (account.equals("xyz")) {
                    break;
                }
            }
            if ((account.equals("xyz") && tally <= 2) || !account.equals("xyz")) {
                System.err.println("error: appended record not found in correct position");
            } else {
                System.out.print("Appended record: " + line);
            }
        } catch (IOException e) {
            passStatus = "99";
            okStatus = false;
            checkPassFile();
        }
    }
}