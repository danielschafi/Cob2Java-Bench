import java.io.*;
import java.nio.file.*;
import java.util.*;

public class append {
    private static final int LRECL = 2048;
    private static final String passFilename = "passfile";
    
    private String passStatus = "00";
    private int passLength = 0;
    private int totalLength = 0;
    private String fileAction = "";
    
    private String account = "";
    private String password = "";
    private int uid = 0;
    private int gid = 0;
    private String fullname = "";
    private String office = "";
    private String extension = "";
    private String homephone = "";
    private String email = "";
    private String homedir = "";
    private String shell = "";
    
    private String colon = ":";
    private String commaMark = ",";
    private String newline = "\n";
    
    private PrintWriter writer;
    private BufferedReader reader;
    private int tally = 0;
    
    public static void main(String[] args) {
        append app = new append();
        app.mainRoutine();
    }
    
    private void mainRoutine() {
        initialFill();
        appendRecord();
        verifyAppend();
    }
    
    private void initialFill() {
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
    
    private void checkPassFile() {
        if (!passStatus.equals("00") && !passStatus.equals("01") && 
            !passStatus.equals("02") && !passStatus.equals("03") && 
            !passStatus.equals("04") && !passStatus.equals("05") && 
            !passStatus.equals("06") && !passStatus.equals("07") && 
            !passStatus.equals("08") && !passStatus.equals("09")) {
            fileError();
        }
    }
    
    private void checkPassWithEof() {
        if (!passStatus.equals("00") && !passStatus.equals("01") && 
            !passStatus.equals("02") && !passStatus.equals("03") && 
            !passStatus.equals("04") && !passStatus.equals("05") && 
            !passStatus.equals("06") && !passStatus.equals("07") && 
            !passStatus.equals("08") && !passStatus.equals("09") && 
            !passStatus.equals("10")) {
            fileError();
        }
    }
    
    private void fileError() {
        System.err.println("error " + fileAction + " " + passFilename + " " + passStatus);
        System.exit(1);
    }
    
    private void appendRecord() {
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
    
    private void openOutputPassFile() {
        try {
            writer = new PrintWriter(new FileWriter(passFilename, false));
            fileAction = "open output";
            passStatus = "00";
            checkPassFile();
        } catch (IOException e) {
            passStatus = "30";
            fileError();
        }
    }
    
    private void openExtendPassFile() {
        try {
            writer = new PrintWriter(new FileWriter(passFilename, true));
            fileAction = "open extend";
            passStatus = "00";
            checkPassFile();
        } catch (IOException e) {
            passStatus = "30";
            fileError();
        }
    }
    
    private void openInputPassFile() {
        try {
            reader = new BufferedReader(new FileReader(passFilename));
            fileAction = "open input";
            passStatus = "00";
            checkPassFile();
        } catch (IOException e) {
            passStatus = "30";
            fileError();
        }
    }
    
    private void closePassFile() {
        try {
            if (writer != null) {
                writer.close();
            }
            if (reader != null) {
                reader.close();
            }
            fileAction = "closing";
            passStatus = "00";
            checkPassFile();
        } catch (IOException e) {
            passStatus = "30";
            fileError();
        }
    }
    
    private void writePassRecord() {
        totalLength = 1;
        passLength = LRECL;
        
        StringBuilder sb = new StringBuilder();
        sb.append(account.trim()).append(colon);
        sb.append(password.trim()).append(colon);
        sb.append(String.valueOf(uid).trim()).append(colon);
        sb.append(String.valueOf(gid).trim()).append(colon);
        sb.append(fullname.trim()).append(commaMark);
        sb.append(office.trim()).append(commaMark);
        sb.append(extension.trim()).append(commaMark);
        sb.append(homephone.trim()).append(commaMark);
        sb.append(email.trim()).append(colon);
        sb.append(homedir.trim()).append(colon);
        sb.append(shell.trim());
        
        String record = sb.toString();
        if (record.length() > LRECL) {
            System.err.println("error: fd-pass-record truncated at " + totalLength);
        }
        
        writer.println(record);
        fileAction = "writing";
        passStatus = "00";
        checkPassFile();
    }
    
    private void readPassFile() {
        try {
            String line = reader.readLine();
            if (line == null) {
                passStatus = "10";
            } else {
                account = line;
                passStatus = "00";
            }
            fileAction = "reading";
            checkPassWithEof();
        } catch (IOException e) {
            passStatus = "30";
            fileError();
        }
    }
    
    private void showRecords() {
        openInputPassFile();
        readPassFile();
        while (passStatus.equals("00")) {
            showPassRecord();
            readPassFile();
        }
        closePassFile();
    }
    
    private void showPassRecord() {
        System.out.println(account);
    }
    
    private void verifyAppend() {
        openInputPassFile();
        tally = 0;
        readPassFile();
        
        while (passStatus.equals("00")) {
            tally++;
            String[] parts = account.split(":");
            if (parts.length > 0) {
                String acct = parts[0];
                if (acct.equals("xyz")) {
                    break;
                }
            }
            readPassFile();
        }
        
        boolean keyAccount = false;
        if (account.length() > 0) {
            String[] parts = account.split(":");
            if (parts.length > 0