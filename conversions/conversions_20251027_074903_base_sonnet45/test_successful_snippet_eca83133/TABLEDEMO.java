import java.io.*;
import java.util.Scanner;

public class TABLEDEMO {
    
    private static String DEMO_EOF = "N";
    private static String DVDR = "--------";
    private static String USER_VAL = "";
    
    private static class PersonData {
        String personId;
        String[] personDatum;
        
        PersonData() {
            personId = "   ";
            personDatum = new String[3];
            for (int i = 0; i < 3; i++) {
                personDatum[i] = "          ";
            }
        }
    }
    
    private static PersonData[] PERSON = new PersonData[10];
    private static int PERSON_INDEX = 0;
    private static int DATA_INDEX = 0;
    private static String PERSON_RECORD = "";
    
    static {
        for (int i = 0; i < 10; i++) {
            PERSON[i] = new PersonData();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Table Demo");
        
        moveToPeople("001KEVINDATAK002JACOBDATAJ");
        
        GET_NAME();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("PEOPLE.ONELINE.DATA"));
            DEMO_EOF = "N";
            
            while (!DEMO_EOF.equals("Y")) {
                String line = reader.readLine();
                if (line == null) {
                    DEMO_EOF = "Y";
                } else {
                    PERSON_RECORD = line;
                }
            }
            reader.close();
        } catch (IOException e) {
            DEMO_EOF = "Y";
        }
        
        moveRecordToPeople(PERSON_RECORD);
        
        PERSON_INDEX = 0;
        while (PERSON_INDEX < 3) {
            DISPLAY_PERSON_B();
        }
        
        PERSON_INDEX = 0;
        DATA_INDEX = 0;
        boolean found = false;
        
        for (int i = 0; i < 10; i++) {
            PERSON_INDEX = i;
            if (PERSON[PERSON_INDEX].personDatum[DATA_INDEX].equals("9993334444")) {
                System.out.println("FOUND KIRK");
                DISPLAY_PERSON_B();
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("NOT FOUND");
        }
    }
    
    private static void DISPLAY_PERSON() {
        System.out.println(PERSON[PERSON_INDEX].personId);
        System.out.println(PERSON[PERSON_INDEX].personDatum[0]);
        System.out.println(PERSON[PERSON_INDEX].personDatum[1]);
        System.out.println(PERSON[PERSON_INDEX].personDatum[2]);
        System.out.println(DVDR);
        PERSON_INDEX++;
    }
    
    private static void DISPLAY_PERSON_B() {
        System.out.println(PERSON[PERSON_INDEX].personId + ":" + 
                         PERSON[PERSON_INDEX].personDatum[0] + " " + 
                         PERSON[PERSON_INDEX].personDatum[1] + " " + 
                         PERSON[PERSON_INDEX].personDatum[2]);
        System.out.println(DVDR);
        PERSON_INDEX++;
    }
    
    private static void GET_NAME() {
        System.out.println("WHAT IS YOUR NAME?");
        Scanner scanner = new Scanner(System.in);
        USER_VAL = scanner.nextLine();
        if (USER_VAL.length() > 10) {
            USER_VAL = USER_VAL.substring(0, 10);
        }
        System.out.println("HELLO " + USER_VAL);
        System.out.println(" ");
    }
    
    private static void moveToPeople(String data) {
        int pos = 0;
        for (int i = 0; i < 10 && pos < data.length(); i++) {
            if (pos + 3 <= data.length()) {
                PERSON[i].personId = data.substring(pos, pos + 3);
                pos += 3;
            }
            for (int j = 0; j < 3 && pos < data.length(); j++) {
                if (pos + 10 <= data.length()) {
                    PERSON[i].personDatum[j] = data.substring(pos, pos + 10);
                    pos += 10;
                } else {
                    int remaining = data.length() - pos;
                    PERSON[i].personDatum[j] = data.substring(pos);
                    for (int k = remaining; k < 10; k++) {
                        PERSON[i].personDatum[j] += " ";
                    }
                    pos = data.length();
                }
            }
        }
    }
    
    private static void moveRecordToPeople(String record) {
        if (record == null || record.isEmpty()) {
            return;
        }
        
        String paddedRecord = record;
        while (paddedRecord.length() < 330) {
            paddedRecord += " ";
        }
        
        moveToPeople(paddedRecord);
    }
}