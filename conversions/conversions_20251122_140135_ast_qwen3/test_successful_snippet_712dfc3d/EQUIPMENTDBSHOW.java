import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EQUIPMENTDBSHOW {
    private static int itemCounter = 0;
    private static String statusEquipmentListData = "";
    private static String internalSerial = "";
    private static String itemStatus = "";
    private static String prodName = "";
    private static final String FILE_PATH = "equipments.data";

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            statusEquipmentListData = " ";
            openInput();
            
            displayEquipments(reader);
            
            closeFile();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static void openInput() {
        // Open input is handled by BufferedReader
    }

    private static void displayEquipments(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (!statusEquipmentListData.equals("00")) {
                if (!line.substring(0, 2).equals("* ")) {
                    if (itemCounter == 0) {
                        displayHeaderLine();
                    }
                    displayItem(line);
                } else {
                    System.out.println(line.substring(2));
                }
            }
        }
    }

    private static void displayItem(String record) {
        internalSerial = record.substring(0, 10);
        itemStatus = record.substring(10, 20);
        prodName = record.substring(20, 70);
        System.out.println(internalSerial + " | " + itemStatus + " | " + prodName);
        itemCounter++;
    }

    private static void displayHeaderLine() {
        System.out.println("SERIAL     | STATUS     | PRODUCT NAME");
    }

    private static void closeFile() {
        // Close is handled by try-with-resources
    }
}