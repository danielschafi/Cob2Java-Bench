import java.io.*;
import java.util.Scanner;

public class Chapt13e {
    private static final String FILE_NAME = "NAMES.SEQ";
    private static String fullName = "                              ";
    private static String keyboardStatus = " ";
    private static boolean f1Pressed = false;
    private static String fileErrorFlag = " ";
    private static boolean fileError = false;
    private static String nameFileStatus = "  ";
    private static boolean nameFileSuccess = false;
    private static String errorMessage = "                                                  ";

    public static void main(String[] args) {
        openFile();
        if (!fileError) {
            while (!f1Pressed && !fileError) {
                processInput();
            }
            closeFile();
        }
    }

    private static void openFile() {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            nameFileSuccess = true;
        } catch (IOException e) {
            nameFileSuccess = false;
            errorMessage = "Open Error " + e.getMessage();
            displayAndAcceptError();
        }
    }

    private static void processInput() {
        fullName = "                              ";
        displayScreen();
        acceptInput();
        errorMessage = "                                                  ";
        if (!f1Pressed) {
            writeRecord();
        }
    }

    private static void writeRecord() {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            file.seek(file.length());
            file.writeBytes(fullName);
            nameFileSuccess = true;
            errorMessage = "Record Written";
        } catch (IOException e) {
            nameFileSuccess = false;
            errorMessage = "Write Error " + e.getMessage();
            displayAndAcceptError();
        }
    }

    private static void displayAndAcceptError() {
        fileError = true;
        displayScreen();
        acceptInput();
    }

    private static void closeFile() {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            // File is closed automatically
        } catch (IOException e) {
            // Handle exception if needed
        }
    }

    private static void displayScreen() {
        System.out.println(" Enter Name: " + fullName);
        System.out.println(errorMessage);
        System.out.println("Press F1 to Exit");
    }

    private static void acceptInput() {
        Scanner scanner = new Scanner(System.in);
        keyboardStatus = scanner.nextLine();
        if (keyboardStatus.equals("F1")) {
            f1Pressed = true;
        } else {
            fullName = keyboardStatus;
        }
    }
}