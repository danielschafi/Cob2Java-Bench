import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EquipmentDBShow {
    private static int itemCounter = 0;
    private static String statusEquipmentListData = "  ";
    private static String internalSerial = "          ";
    private static String itemStatus = "          ";
    private static String prodName = "                                                  ";

    public static void main(String[] args) {
        statusEquipmentListData = "  ";
        try (BufferedReader reader = new BufferedReader(new FileReader("equipments.data"))) {
            displayEquipments(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayEquipments(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
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

    private static void displayItem(String line) {
        internalSerial = line.substring(0, 10).trim();
        itemStatus = line.substring(10, 20).trim();
        prodName = line.substring(20, 70).trim();
        System.out.println(internalSerial + " | " + itemStatus + " | " + prodName);
        itemCounter++;
    }

    private static void displayHeaderLine() {
        System.out.println("SERIAL     | STATUS     | PRODUCT NAME");
    }
}