import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EQUIPMENTDBSHOW {
    private static final String EQUIPMENT_LIST_DATA_FILE = "equipments.data";
    private static int itemCounter = 0;
    private static String statusEquipmentListData = "";
    private static String internalSerial = "";
    private static String itemStatus = "";
    private static String prodName = "";

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(EQUIPMENT_LIST_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
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
            e.printStackTrace();
        }
    }

    private static void displayItem(String record) {
        internalSerial = record.length() >= 10 ? record.substring(0, 10) : record;
        itemStatus = record.length() >= 20 ? record.substring(10, 20) : "";
        prodName = record.length() >= 70 ? record.substring(20, 70) : record.substring(20);

        System.out.println(internalSerial + " | " + itemStatus + " | " + prodName);
        itemCounter++;
    }

    private static void displayHeaderLine() {
        System.out.println("SERIAL     | STATUS     | PRODUCT NAME");
    }
}