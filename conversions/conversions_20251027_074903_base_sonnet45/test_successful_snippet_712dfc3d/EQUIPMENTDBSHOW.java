import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EQUIPMENTDBSHOW {
    private static int itemCounter = 0;
    private static String statusEquipmentListData = "  ";
    private static String internalSerial;
    private static String itemStatus;
    private static String prodName;

    public static void main(String[] args) {
        statusEquipmentListData = "  ";
        
        try (BufferedReader reader = new BufferedReader(new FileReader("equipments.data"))) {
            displayEquipments(reader);
        } catch (IOException e) {
            statusEquipmentListData = "35";
        }
    }

    private static void displayEquipments(BufferedReader reader) {
        try {
            String equipmentListDataRec;
            statusEquipmentListData = "00";
            
            while (statusEquipmentListData.equals("00")) {
                equipmentListDataRec = reader.readLine();
                
                if (equipmentListDataRec != null) {
                    equipmentListDataRec = padRight(equipmentListDataRec, 70);
                    
                    if (!equipmentListDataRec.substring(0, 2).equals("* ")) {
                        if (itemCounter == 0) {
                            displayHeaderLine();
                        }
                        displayItem(equipmentListDataRec);
                    } else {
                        if (equipmentListDataRec.length() >= 3) {
                            String displayText = equipmentListDataRec.substring(2, Math.min(70, equipmentListDataRec.length()));
                            System.out.println(displayText);
                        }
                    }
                } else {
                    statusEquipmentListData = "10";
                }
            }
        } catch (IOException e) {
            statusEquipmentListData = "90";
        }
    }

    private static void displayItem(String equipmentListDataRec) {
        internalSerial = equipmentListDataRec.substring(0, 10);
        itemStatus = equipmentListDataRec.substring(10, 20);
        prodName = equipmentListDataRec.substring(20, 70);
        
        System.out.println(internalSerial + " | " + itemStatus + " | " + prodName);
        itemCounter++;
    }

    private static void displayHeaderLine() {
        System.out.println("SERIAL     | STATUS     | PRODUCT NAME");
    }

    private static String padRight(String str, int length) {
        if (str.length() >= length) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < length) {
            sb.append(' ');
        }
        return sb.toString();
    }
}