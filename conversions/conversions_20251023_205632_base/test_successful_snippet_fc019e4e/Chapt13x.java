import java.io.*;
import java.util.Scanner;

public class Chapt13x {
    private static final String FILE_NAME = "NAMES.SEQ";
    private static RandomAccessFile nameFile;
    private static String fullName = new String(new char[30]).replace('\0', ' ');
    private static String keyboardStatus = new String(new char[2]).replace('\0', ' ');
    private static boolean f1Pressed = false;
    private static boolean f2Pressed = false;
    private static boolean f3Pressed = false;
    private static boolean fileError = false;
    private static String nameFileStatus = new String(new char[2]).replace('\0', ' ');
    private static boolean nameFileSuccess = false;
    private static boolean endOfFile = false;
    private static String errorMessage = new String(new char[50]).replace('\0', ' ');

    public static void main(String[] args) {
        openFile();
        if (!fileError) {
            while (!f1Pressed && !fileError && !endOfFile) {
                processFile();
            }
            closeFile();
        }
    }

    private static void openFile() {
        try {
            nameFile = new RandomAccessFile(FILE_NAME, "rw");
            nameFileSuccess = true;
        } catch (FileNotFoundException e) {
            nameFileStatus = "01";
            fileError = true;
            errorMessage = "Open Error " + nameFileStatus;
            displayAndAcceptError();
        }
    }

    private static void openExtend() {
        try {
            nameFile = new RandomAccessFile(FILE_NAME, "rw");
            nameFile.seek(nameFile.length());
            nameFileSuccess = true;
        } catch (FileNotFoundException e) {
            nameFileStatus = "01";
            fileError = true;
            errorMessage = "Open Extend Error " + nameFileStatus;
            displayAndAcceptError();
        } catch (IOException e) {
            nameFileStatus = "02";
            fileError = true;
            errorMessage = "Open Extend Error " + nameFileStatus;
            displayAndAcceptError();
        }
    }

    private static void processFile() {
        fullName = new String(new char[30]).replace('\0', ' ');
        readFile();
        if (!fileError) {
            displayAndAcceptInput();
            errorMessage = new String(new char[50]).replace('\0', ' ');
            if (f2Pressed && !endOfFile) {
                rewriteRecord();
            }
            if (f3Pressed) {
                addRecord();
            }
        }
    }

    private static void readFile() {
        try {
            if (nameFile.getFilePointer() < nameFile.length()) {
                fullName = String.format("%-30s", nameFile.readLine()).substring(0, 30);
            } else {
                endOfFile = true;
                errorMessage = "End Of File";
            }
        } catch (IOException e) {
            nameFileStatus = "03";
            fileError = true;
            errorMessage = "Read Error " + nameFileStatus;
            displayAndAcceptError();
        }
    }

    private static void rewriteRecord() {
        try {
            nameFile.seek(nameFile.getFilePointer() - 30);
            nameFile.writeBytes(String.format("%-30s", fullName));
            errorMessage = "Prior Record Updated";
        } catch (IOException e) {
            nameFileStatus = "04";
            fileError = true;
            errorMessage = "Rewrite Error " + nameFileStatus;
        }
    }

    private static void writeRecord() {
        try {
            nameFile.writeBytes(String.format("%-30s", fullName));
            errorMessage = "Prior Record Added";
        } catch (IOException e) {
            nameFileStatus = "05";
            fileError = true;
            errorMessage = "Write Error " + nameFileStatus;
        }
    }

    private static void displayAndAcceptInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Enter Name: " + fullName);
        System.out.println(errorMessage);
        System.out.println("Press F1 to Exit    Press F2 to Update");
        System.out.println("Press F3 to Add to End");
        String input = scanner.nextLine();
        fullName = String.format("%-30s", input.substring(0, Math.min(input.length(), 30)));
        keyboardStatus = input.length() > 30 ? input.substring(30, 32) : "  ";
        f1Pressed = keyboardStatus.equals("\u0001");
        f2Pressed = keyboardStatus.equals("\u0002");
        f3Pressed = keyboardStatus.equals("\u0003");
    }

    private static void displayAndAcceptError() {
        fileError = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Enter Name: " + fullName);
        System.out.println(errorMessage);
        System.out.println("Press F1 to Exit    Press F2 to Update");
        System.out.println("Press F3 to Add to End");
        scanner.nextLine();
    }

    private static void addRecord() {
        closeFile();
        openExtend();
        if (nameFileSuccess) {
            writeRecord();
            if (nameFileSuccess) {
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
            nameFileStatus = "06";
            fileError = true;
            errorMessage = "Close Error " + nameFileStatus;
        }
    }
}