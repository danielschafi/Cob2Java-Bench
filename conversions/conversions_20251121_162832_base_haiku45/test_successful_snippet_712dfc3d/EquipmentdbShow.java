import java.io.*;
import java.nio.file.*;

public class EquipmentdbShow {
    private static int itemCounter = 0;
    private static String statusEquipmentListData = "00";
    private static String internalSerial;
    private static String itemStatus;
    private static String prodName;

    public static void main(String[] args) {
        mainProcedure();
    }

    private static void mainProcedure() {
        statusEquipmentListData = "  ";
        
        try {
            displayEquipments();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayEquipments() throws IOException {
        String filePath = "equipments.data";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            statusEquipmentListData = "00";
            
            while ((line = reader.readLine()) != null && statusEquipmentListData.equals("00")) {
                if (line.length() >= 2 && !line.substring(0, 2).equals("* ")) {
                    if (itemCounter == 0) {
                        displayHeaderLine();
                    }
                    
                    if (line.length() >= 70) {
                        displayItem(line);
                    }
                } else if (line.length() >= 3) {
                    System.out.println(line.substring(2));
                }
            }
        } catch (FileNotFoundException e) {
            statusEquipmentListData = "35";
        } catch (IOException e) {
            statusEquipmentListData = "30";
        }
    }

    private static void displayItem(String line) {
        int endIdx;
        
        endIdx = Math.min(10, line.length());
        internalSerial = line.substring(0, endIdx);
        if (internalSerial.length() < 10) {
            internalSerial = String.format("%-10s", internalSerial);
        }
        
        endIdx = Math.min(21, line.length());
        int startIdx = 10;
        itemStatus = line.substring(startIdx, endIdx);
        if (itemStatus.length() < 10) {
            itemStatus = String.format("%-10s", itemStatus);
        }
        
        endIdx = Math.min(70, line.length());
        startIdx = 20;
        prodName = line.substring(startIdx, endIdx);
        if (prodName.length() < 50) {
            prodName = String.format("%-50s", prodName);
        }
        
        System.out.println(internalSerial + " | " + itemStatus + " | " + prodName);
        itemCounter++;
    }

    private static void displayHeaderLine() {
        System.out.println("SERIAL     | STATUS     | PRODUCT NAME");
    }
}