import java.io.*;
import java.util.Scanner;

public class Chapt13e {
    private static final String FILE_NAME = "NAMES.SEQ";
    private static String fullName = new String(new char[30]).replace('\0', ' ');
    private static String keyboardStatus = new String(new char[3]).replace('\0', ' ');
    private static boolean f1Pressed = false;
    private static boolean fileError = false;
    private static String nameFileStatus = new String(new char[2]).replace('\0', ' ');
    private static String errorMessage = new String(new char[50]).replace('\0', ' ');

    public static void main(String[] args) {
        openFile();
        if (!fileError) {
            processInput();
            closeFile();
        }
    }

    private static void openFile() {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            nameFileStatus = "00";
        } catch (IOException e) {
            nameFileStatus = "99";
            errorMessage = "Open Error " + nameFileStatus;
            displayAndAcceptError();
        }
    }

    private static void processInput() {
        Scanner scanner = new Scanner(System.in);
        while (!f1Pressed && !fileError) {
            fullName = new String(new char[30]).replace('\0', ' ');
            System.out.print(" Enter Name: " + fullName.trim() + "\nPress F1 to Exit\n");
            fullName = scanner.nextLine();
            if (fullName.equalsIgnoreCase("F1")) {
                f1Pressed = true;
            } else {
                writeRecord();
            }
        }
        scanner.close();
    }

    private static void writeRecord() {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            file.seek(file.length());
            file.writeBytes(fullName.trim() + "\n");
            nameFileStatus = "00";
            errorMessage = "Record Written";
        } catch (IOException e) {
            nameFileStatus = "99";
            errorMessage = "Write Error " + nameFileStatus;
            displayAndAcceptError();
        }
    }

    private static void displayAndAcceptError() {
        fileError = true;
        System.out.print(" Enter Name: " + fullName.trim() + "\n" + errorMessage + "\nPress F1 to Exit\n");
        Scanner scanner = new Scanner(System.in);
        fullName = scanner.nextLine();
        if (fullName.equalsIgnoreCase("F1")) {
            f1Pressed = true;
        }
        scanner.close();
    }

    private static void closeFile() {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            nameFileStatus = "00";
        } catch (IOException e) {
            nameFileStatus = "99";
        }
    }
}