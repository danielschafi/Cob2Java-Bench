import java.io.*;
import java.nio.file.*;

public class EquipmentdbShow {
    private int itemCounter = 0;
    private String statusEquipmentListData = " ";
    private String internalSerial = "";
    private String itemStatus = "";
    private String prodName = "";
    private BufferedReader reader;

    public static void main(String[] args) {
        EquipmentdbShow program = new EquipmentdbShow();
        program.mainProcedure();
    }

    private void mainProcedure() {
        statusEquipmentListData = " ";
        openInputFile();
        displayEquipments();
        closeInputFile();
    }

    private void openInputFile() {
        try {
            reader = new BufferedReader(new FileReader("equipments.data"));
            statusEquipmentListData = "00";
        } catch (FileNotFoundException e) {
            statusEquipmentListData = "30";
        } catch (IOException e) {
            statusEquipmentListData = "30";
        }
    }

    private void closeInputFile() {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayEquipments() {
        try {
            String line;
            while (!statusEquipmentListData.equals("00")) {
                line = reader.readLine();
                if (line == null) {
                    statusEquipmentListData = "00";
                    break;
                }

                if (line.length() >= 2 && !line.substring(0, 2).equals("* ")) {
                    if (itemCounter == 0) {
                        displayHeaderLine();
                    }
                    displayItem(line);
                } else if (line.length() >= 3) {
                    System.out.println(line.substring(2));
                }
            }
        } catch (IOException e) {
            statusEquipmentListData = "30";
        }
    }

    private void displayItem(String line) {
        if (line.length() >= 70) {
            internalSerial = line.substring(0, 10);
            itemStatus = line.substring(10, 20);
            prodName = line.substring(20, 70);
        } else {
            internalSerial = line.length() > 0 ? line.substring(0, Math.min(10, line.length())) : "";
            itemStatus = line.length() > 10 ? line.substring(10, Math.min(20, line.length())) : "";
            prodName = line.length() > 20 ? line.substring(20) : "";
        }

        System.out.println(internalSerial + " | " + itemStatus + " | " + prodName);
        itemCounter++;
    }

    private void displayHeaderLine() {
        System.out.println("SERIAL     | STATUS     | PRODUCT NAME");
    }
}