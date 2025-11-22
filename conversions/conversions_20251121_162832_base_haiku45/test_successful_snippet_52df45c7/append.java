import java.io.*;
import java.nio.file.*;
import java.util.*;

public class append {
    private static final String PASS_FILENAME = "passfile";
    private static final int LRECL = 2048;
    
    private String passStatus = "00";
    private RandomAccessFile passFile;
    private int passLength;
    private int totalLength;
    private String fileAction;
    
    private String account;
    private String password;
    private int uid;
    private int gid;
    private String fullname;
    private String office;
    private String extension;
    private String homephone;
    private String email;
    private String homedir;
    private String shell;
    
    private int tally;
    private byte[] fdPassRecord;
    
    public static void main(String[] args) {
        append program = new append();
        program.mainRoutine();
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
        System.err.println("error " + fileAction + " " + PASS_FILENAME + " " + passStatus);
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
            passFile = new RandomAccessFile(PASS_FILENAME, "rw");
            passFile.setLength(0);
            fileAction = "open output";
            passStatus = "00";
            checkPassFile();
        } catch (IOException e) {
            passStatus = "99";
            fileError();
        }
    }
    
    private void openExtendPassFile() {
        try {
            passFile = new RandomAccessFile(PASS_FILENAME, "rw");
            passFile.seek(passFile.length());
            fileAction = "open extend";
            passStatus = "00";
            checkPassFile();
        } catch (IOException e) {
            passStatus = "99";
            fileError();
        }
    }
    
    private void openInputPassFile() {
        try {
            passFile = new RandomAccessFile(PASS_FILENAME, "r");
            fileAction = "open input";
            passStatus = "00";
            checkPassFile();
        } catch (IOException e) {
            passStatus = "99";
            fileError();
        }
    }
    
    private void closePassFile() {
        try {
            if (passFile != null) {
                passFile.close();
            }
            fileAction = "closing";
            passStatus = "00";
            checkPassFile();
        } catch (IOException e) {
            passStatus = "99";
            fileError();
        }
    }
    
    private void writePassRecord() {
        StringBuilder sb = new StringBuilder();
        sb.append(account.trim()).append(":");
        sb.append(password.trim()).append(":");
        sb.append(String.valueOf(uid).trim()).append(":");
        sb.append(String.valueOf(gid).trim()).append(":");
        sb.append(fullname.trim()).append(",");
        sb.append(office.trim()).append(",");
        sb.append(extension.trim()).append(",");
        sb.append(homephone.trim()).append(",");
        sb.append(email.trim()).append(":");
        sb.append(homedir.trim()).append(":");
        sb.append(shell.trim());
        
        try {
            passFile.writeBytes(sb.toString() + "\n");
            fileAction = "writing";
            passStatus = "00";
            checkPassFile();
        } catch (IOException e) {
            passStatus = "99";
            fileError();
        }
    }
    
    private void readPassFile() {
        try {
            String line = passFile.readLine();
            if (line == null) {
                passStatus = "10";
                fdPassRecord = new byte[0];
            } else {
                passStatus = "00";
                fdPassRecord = line.getBytes();
            }
            fileAction = "reading";
            checkPassWithEof();
        } catch (IOException e) {
            passStatus = "99";
            fileError();
        }
    }
    
    private void showRecords() {
        openInputPassFile();
        
        readPassFile();
        while (!passStatus.equals("10")) {
            showPassRecord();
            readPassFile();
        }
        
        closePassFile();
    }
    
    private void showPassRecord() {
        System.out.println(new String(fdPassRecord));
    }
    
    private void verifyAppend() {
        openInputPassFile();
        
        tally = 0;
        readPassFile();
        while (!passStatus.equals("10")) {
            tally++;
            String line = new String(fdPassRecord);
            String[] parts = line.split(":");
            if (parts.length > 0) {
                account = parts[0];
            }
            if (account.equals("xyz")) {
                break;
            }
            readPassFile();
        }
        
        if ((account.equals("xyz") && tally <= 2) || !account.equals("xyz")) {
            System.err.println("error: appended record not found in correct position");
        } else {
            System.out.print("Appended record: ");
            showPassRecord();
        }
        
        closePassFile();
    }
}