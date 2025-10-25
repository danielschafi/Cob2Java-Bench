import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class Chapt18a {
    private static String transactionDate;
    private static String transactionType;
    private static String transactionDealer;
    private static double transactionPrice;
    private static int transactionQty;
    private static String fileErrorFlag;
    private static String transFileStatus;
    private static String errorMessage;
    private static String openErrorMessage;
    private static String writeErrorMessage;
    private static Scanner scanner;
    private static BufferedWriter writer;

    public static void main(String[] args) {
        openFile();
        if (!fileErrorFlag.equals("Y")) {
            initializeTransRecord();
            processInput();
            closeFile();
        }
    }

    private static void openFile() {
        try {
            writer = Files.newBufferedWriter(Paths.get("TRANS.TXT"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            fileErrorFlag = "Y";
            transFileStatus = e.getMessage();
            openErrorMessage = "Error Opening Transaction File " + transFileStatus;
            displayAndAcceptError();
        }
    }

    private static void processInput() {
        while (true) {
            displayDataEntryScreen();
            acceptDataEntryScreen();
            errorMessage = " ";
            if (scanner.hasNext()) {
                String functionKey = scanner.next();
                switch (functionKey) {
                    case "\u0001":
                        writeRecord();
                        break;
                    case "\u0003":
                        return;
                    case "\u0004":
                        initializeTransRecord();
                        break;
                    default:
                        continue;
                }
            }
        }
    }

    private static void writeRecord() {
        try {
            writer.write(String.format("%8s%4s%8s%010.2f%03d%40s", transactionDate, transactionType, transactionDealer, transactionPrice, transactionQty, " "));
            writer.newLine();
            writer.flush();
            initializeTransRecord();
            errorMessage = "Record Written";
        } catch (IOException e) {
            fileErrorFlag = "Y";
            transFileStatus = e.getMessage();
            writeErrorMessage = "Error Writing Transaction File " + transFileStatus;
            displayAndAcceptError();
        }
    }

    private static void displayAndAcceptError() {
        fileErrorFlag = "Y";
        displayDataEntryScreen();
        acceptDataEntryScreen();
    }

    private static void closeFile() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initializeTransRecord() {
        transactionDate = "        ";
        transactionType = "    ";
        transactionDealer = "        ";
        transactionPrice = 0.0;
        transactionQty = 0;
    }

    private static void displayDataEntryScreen() {
        System.out.println("                                Darlene's Treasures");
        System.out.println("                          Transaction Entry Program");
        System.out.println("Date: " + transactionDate.substring(0, 2) + "/" + transactionDate.substring(2, 4) + "/" + transactionDate.substring(4));
        System.out.println("Category: " + transactionType);
        System.out.println("Dealer Number: " + transactionDealer);
        System.out.printf("Price: %,10.2f%n", transactionPrice);
        System.out.printf("Quantity: %3d%n", transactionQty);
        System.out.println("F1-Save Record F3-Exit F4-Clear");
        System.out.println(errorMessage);
    }

    private static void acceptDataEntryScreen() {
        scanner = new Scanner(System.in);
        System.out.print("Enter Date (MMDDYYYY): ");
        transactionDate = scanner.next();
        System.out.print("Enter Category (4 chars): ");
        transactionType = scanner.next();
        System.out.print("Enter Dealer Number (8 chars): ");
        transactionDealer = scanner.next();
        System.out.print("Enter Price: ");
        transactionPrice = scanner.nextDouble();
        System.out.print("Enter Quantity: ");
        transactionQty = scanner.nextInt();
    }
}