import java.io.*;
import java.util.Scanner;

public class Chapt13x {
    private static final String FILE_NAME = "NAMES.SEQ";
    private static RandomAccessFile nameFile;
    private static String fullName = "                              ";
    private static String keyboardStatus = "0 ";
    private static boolean fileError = false;
    private static String nameFileStatus = "  ";
    private static String errorMessage = "                                                  ";

    public static void main(String[] args) {
        openFile();
        if (!fileError) {
            while (!keyboardStatus.equals("01") && !fileError && !nameFileStatus.equals("10")) {
                processFile();
            }
            closeFile();
        }
    }

    private static void openFile() {
        try {
            nameFile = new RandomAccessFile(FILE_NAME, "rw");
        } catch (FileNotFoundException e) {
            fileError = true;
            errorMessage = "Open Error " + e.getMessage();
            displayAndAcceptError();
        }
    }

    private static void openExtend() {
        try {
            nameFile = new RandomAccessFile(FILE_NAME, "rw");
            nameFile.seek(nameFile.length());
        } catch (FileNotFoundException e) {
            fileError = true;
            errorMessage = "Open Extend Error " + e.getMessage();
            displayAndAcceptError();
        } catch (IOException e) {
            fileError = true;
            errorMessage = "Open Extend Error " + e.getMessage();
            displayAndAcceptError();
        }
    }

    private static void processFile() {
        fullName = "                              ";
        readFile();
        if (!fileError) {
            displayScreen();
            acceptInput();
            if (keyboardStatus.equals("02") && !nameFileStatus.equals("10")) {
                rewriteRecord();
            }
            if (keyboardStatus.equals("03")) {
                addRecord();
            }
        }
    }

    private static void readFile() {
        try {
            if (nameFile.getFilePointer() < nameFile.length()) {
                fullName = String.format("%-30s", nameFile.readLine());
            } else {
                errorMessage = "End Of File";
            }
        } catch (IOException e) {
            fileError = true;
            errorMessage = "Read Error " + e.getMessage();
        }
    }

    private static void rewriteRecord() {
        try {
            nameFile.seek(nameFile.getFilePointer() - 31);
            nameFile.writeBytes(String.format("%-30s", fullName));
            errorMessage = "Prior Record Updated";
        } catch (IOException e) {
            fileError = true;
            errorMessage = "Rewrite Error " + e.getMessage();
        }
    }

    private static void writeRecord() {
        try {
            nameFile.writeBytes(String.format("%-30s", fullName));
            errorMessage = "Prior Record Added";
        } catch (IOException e) {
            fileError = true;
            errorMessage = "Write Error " + e.getMessage();
        }
    }

    private static void displayAndAcceptError() {
        fileError = true;
        displayScreen();
        acceptInput();
    }

    private static void addRecord() {
        closeFile();
        openExtend();
        if (!fileError) {
            writeRecord();
            if (!fileError) {
                closeFile();
                openFile();
            }
        }
    }

    private static void closeFile() {
        try {
            if (nameFile != null) {
                nameFile.close();
            }
        } catch (IOException e) {
            fileError = true;
            errorMessage = "Close Error " + e.getMessage();
        }
    }

    private static void displayScreen() {
        System.out.println(" Enter Name: " + fullName);
        System.out.println("                                                  " + errorMessage);
        System.out.println("Press F1 to Exit    Press F2 to Update");
        System.out.println("Press F3 to Add to End");
    }

    private static void acceptInput() {
        Scanner scanner = new Scanner(System.in);
        fullName = scanner.nextLine().trim();
        if (fullName.length() > 30) {
            fullName = fullName.substring(0, 30);
        } else {
            fullName = String.format("%-30s", fullName);
        }
        keyboardStatus = fullName.substring(29, 30);
    }
}