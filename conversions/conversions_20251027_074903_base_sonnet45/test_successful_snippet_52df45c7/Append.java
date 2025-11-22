import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Append {
    private static final int LRECL = 2048;
    private static final String PASS_FILENAME = "passfile";
    
    private static class PassRecord {
        String account = "";
        String password = "";
        int uid = 0;
        int gid = 0;
        String fullname = "";
        String office = "";
        String extension = "";
        String homephone = "";
        String email = "";
        String homedir = "";
        String shell = "";
        
        String toFileString() {
            return account.trim() + ":" +
                   password.trim() + ":" +
                   uid + ":" +
                   gid + ":" +
                   fullname.trim() + "," +
                   office.trim() + "," +
                   extension.trim() + "," +
                   homephone.trim() + "," +
                   email.trim() + ":" +
                   homedir.trim() + ":" +
                   shell.trim();
        }
        
        void fromFileString(String line) {
            String[] parts = line.split(":", -1);
            if (parts.length >= 7) {
                account = parts[0];
                password = parts[1];
                uid = parts[2].trim().isEmpty() ? 0 : Integer.parseInt(parts[2].trim());
                gid = parts[3].trim().isEmpty() ? 0 : Integer.parseInt(parts[3].trim());
                
                String[] details = parts[4].split(",", -1);
                fullname = details.length > 0 ? details[0] : "";
                office = details.length > 1 ? details[1] : "";
                extension = details.length > 2 ? details[2] : "";
                homephone = details.length > 3 ? details[3] : "";
                email = details.length > 4 ? details[4] : "";
                
                homedir = parts[5];
                shell = parts[6];
            }
        }
    }
    
    private static PassRecord currentRecord = new PassRecord();
    
    public static void main(String[] args) {
        try {
            initialFill();
            appendRecord();
            verifyAppend();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
    
    private static void initialFill() throws IOException {
        List<String> lines = new ArrayList<>();
        
        currentRecord.account = "jsmith";
        currentRecord.password = "x";
        currentRecord.uid = 1001;
        currentRecord.gid = 1000;
        currentRecord.fullname = "Joe Smith";
        currentRecord.office = "Room 1007";
        currentRecord.extension = "(234)555-8917";
        currentRecord.homephone = "(234)555-0077";
        currentRecord.email = "jsmith@rosettacode.org";
        currentRecord.homedir = "/home/jsmith";
        currentRecord.shell = "/bin/bash";
        lines.add(currentRecord.toFileString());
        
        currentRecord.account = "jdoe";
        currentRecord.password = "x";
        currentRecord.uid = 1002;
        currentRecord.gid = 1000;
        currentRecord.fullname = "Jane Doe";
        currentRecord.office = "Room 1004";
        currentRecord.extension = "(234)555-8914";
        currentRecord.homephone = "(234)555-0044";
        currentRecord.email = "jdoe@rosettacode.org";
        currentRecord.homedir = "/home/jdoe";
        currentRecord.shell = "/bin/bash";
        lines.add(currentRecord.toFileString());
        
        Files.write(Paths.get(PASS_FILENAME), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
    
    private static void appendRecord() throws IOException {
        currentRecord.account = "xyz";
        currentRecord.password = "x";
        currentRecord.uid = 1003;
        currentRecord.gid = 1000;
        currentRecord.fullname = "X Yz";
        currentRecord.office = "Room 1003";
        currentRecord.extension = "(234)555-8913";
        currentRecord.homephone = "(234)555-0033";
        currentRecord.email = "xyz@rosettacode.org";
        currentRecord.homedir = "/home/xyz";
        currentRecord.shell = "/bin/bash";
        
        List<String> lines = new ArrayList<>();
        lines.add(currentRecord.toFileString());
        Files.write(Paths.get(PASS_FILENAME), lines, StandardOpenOption.APPEND);
    }
    
    private static void verifyAppend() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(PASS_FILENAME));
        int tally = 0;
        boolean found = false;
        String foundLine = "";
        
        for (String line : lines) {
            tally++;
            String[] parts = line.split(":", -1);
            if (parts.length > 0 && parts[0].equals("xyz")) {
                found = true;
                foundLine = line;
                break;
            }
        }
        
        if (!found || tally <= 2) {
            System.err.println("error: appended record not found in correct position");
        } else {
            System.out.print("Appended record: ");
            System.out.println(foundLine);
        }
    }
}