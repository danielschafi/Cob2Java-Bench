import java.io.*;
import java.util.Scanner;

public class Chapt18a {
    private static final String TRANSACTION_FILE = "TRANS.TXT";
    private static final int DATE_LENGTH = 8;
    private static final int TYPE_LENGTH = 4;
    private static final int DEALER_LENGTH = 8;
    private static final int PRICE_LENGTH = 10;
    private static final int QTY_LENGTH = 3;
    private static final int FILLER_LENGTH = 40;
    private static final int RECORD_LENGTH = DATE_LENGTH + TYPE_LENGTH + DEALER_LENGTH + PRICE_LENGTH + QTY_LENGTH + FILLER_LENGTH;

    private static String transactionDate = "00000000";
    private static String transactionType = "    ";
    private static String transactionDealer = "        ";
    private static String transactionPrice = "0000000000";
    private static String transactionQty = "000";
    private static String errorMessage = "                                                  ";
    private static String fileStatus = "  ";
    private static boolean fileError = false;

    public static void main(String[] args) {
        openFile();
        if (!fileError) {
            initializeTransactionRecord();
            processInput();
            closeFile();
        }
    }

    private static void openFile() {
        try (RandomAccessFile file = new RandomAccessFile(TRANSACTION_FILE, "rw")) {
            file.seek(file.length());
        } catch (IOException e) {
            fileError = true;
            errorMessage = "Error Opening Transaction File " + e.getMessage();
            displayAndAcceptError();
        }
    }

    private static void processInput() {
        Scanner scanner = new Scanner(System.in);
        while (!fileError) {
            displayDataEntryScreen();
            acceptDataEntryScreen(scanner);
            errorMessage = "                                                  ";
            if (isF1Pressed()) {
                writeRecord();
            } else if (isF4Pressed()) {
                initializeTransactionRecord();
            } else if (isF3Pressed()) {
                break;
            }
        }
        scanner.close();
    }

    private static void writeRecord() {
        try (RandomAccessFile file = new RandomAccessFile(TRANSACTION_FILE, "rw")) {
            file.seek(file.length());
            file.writeBytes(formatRecord());
            fileError = false;
            errorMessage = "Record Written";
        } catch (IOException e) {
            fileError = true;
            errorMessage = "Error Writing Transaction File " + e.getMessage();
            displayAndAcceptError();
        }
    }

    private static void displayAndAcceptError() {
        fileError = true;
        displayDataEntryScreen();
        acceptDataEntryScreen(new Scanner(System.in));
    }

    private static void closeFile() {
        try (RandomAccessFile file = new RandomAccessFile(TRANSACTION_FILE, "rw")) {
            // File is automatically closed by the try-with-resources statement
        } catch (IOException e) {
            // Handle exception if necessary
        }
    }

    private static void initializeTransactionRecord() {
        transactionDate = "00000000";
        transactionType = "    ";
        transactionDealer = "        ";
        transactionPrice = "0000000000";
        transactionQty = "000";
    }

    private static void displayDataEntryScreen() {
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out.println("                                                  ");
        System.out