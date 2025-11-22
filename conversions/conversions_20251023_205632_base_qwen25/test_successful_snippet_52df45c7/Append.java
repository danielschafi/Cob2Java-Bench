import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Append {
    private static final String PASS_FILENAME = "passfile";
    private static final String COLON = ":";
    private static final String COMMA_MARK = ",";
    private static final String NEWLINE = "\n";

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

    private static String fdPassRecord;
    private static int passLength;
    private static int totalLength;

    public static void main(String[] args) {
        initialFill();

        appendRecord();

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

    private static void checkPassFile(String fileAction) throws IOException {
        if (!Files.exists(Paths.get(PASS_FILENAME))) {
            fileError(fileAction);
        }
    }

    private static void fileError(String fileAction) {
        System.err.println("error " + fileAction + " " + PASS_FILENAME);
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PASS_FILENAME))) {
            // File is opened for output
        } catch (IOException e) {
            fileError("open output");
        }
    }

    private static void openExtendPassFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PASS_FILENAME, true))) {
            // File is opened for append
        } catch (IOException e) {
            fileError("open extend");
        }
    }

    private static void closePassFile() {
        // No need to explicitly close in Java as try-with-resources handles it
    }

    private static void writePassRecord() {
        StringBuilder sb = new StringBuilder();
        sb.append(account).append(COLON)
          .append(password).append(COLON)
          .append(String.format("%d", uid)).append(COLON)
          .append(String.format("%d", gid)).append(COLON)
          .append(fullname.trim()).append(COMMA_MARK)
          .append(office.trim()).append(COMMA_MARK)
          .append(extension.trim()).append(COMMA_MARK)
          .append(homephone.trim()).append(COMMA_MARK)
          .append(email).append(COLON)
          .append(homedir.trim()).append(COLON)
          .append(shell.trim());

        fdPassRecord = sb.toString();
        passLength = fdPassRecord.length();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PASS_FILENAME, true))) {
            writer.write(fdPassRecord);
            writer.newLine();
        } catch (IOException e) {
            fileError("writing");
        }
    }

    private static void verifyAppend() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PASS_FILENAME))) {
            String line;
            int tally = 0;
            boolean keyAccountFound = false;

            while ((line = reader.readLine()) != null) {
                tally++;
                String[] parts = line.split(COLON);
                account = parts[0];
                if (account.equals("xyz")) {
                    keyAccountFound = true;
                    break;
                }
            }

            if (!(keyAccountFound && tally == 3)) {
                System.err.println("error: appended record not found in correct position");
            } else {
                System.out.print("Appended record: " + line);
            }
        } catch (IOException e) {
            fileError("reading");
        }
    }
}