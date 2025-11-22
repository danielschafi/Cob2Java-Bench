import java.io.*;
import java.util.*;

public class TABLEDEMO {
    static String demoEof = "N";
    static String dvdr = "--------";
    static String userVal = "";
    static String[] personId = new String[10];
    static String[][] personDatum = new String[10][3];
    
    public static void main(String[] args) {
        System.out.println("Table Demo");
        
        // Initialize data from the COBOL move statement
        String peopleData = "001KEVINDATAK002JACOBDATAJ";
        initializePeople(peopleData);
        
        getName();
        
        // Simulate reading from file
        // Since we don't have actual file, we'll use the initialized data
        // In real implementation, you would read from file here
        
        // Display records
        int personIndex = 1;
        while (personIndex <= 3) {
            displayPersonB(personIndex);
            personIndex++;
        }
        
        // Search for specific record
        personIndex = 1;
        boolean found = false;
        while (personIndex <= 10 && !found) {
            for (int dataIndex = 0; dataIndex < 3; dataIndex++) {
                if ("9993334444".equals(personDatum[personIndex-1][dataIndex])) {
                    System.out.println("FOUND KIRK");
                    displayPersonB(personIndex);
                    found = true;
                    break;
                }
            }
            personIndex++;
        }
        
        if (!found) {
            System.out.println("NOT FOUND");
        }
    }
    
    static void initializePeople(String data) {
        // Parse the data string into the arrays
        // Format: 001KEVINDATAK002JACOBDATAJ
        int pos = 0;
        for (int i = 0; i < 2; i++) { // Only first 2 records for demo
            personId[i] = data.substring(pos, pos+3);
            pos += 3;
            personDatum[i][0] = data.substring(pos, pos+6);
            pos += 6;
            personDatum[i][1] = data.substring(pos, pos+6);
            pos += 6;
            personDatum[i][2] = data.substring(pos, pos+6);
            pos += 6;
        }
    }
    
    static void displayPerson(int index) {
        System.out.println(personId[index-1]);
        System.out.println(personDatum[index-1][0]);
        System.out.println(personDatum[index-1][1]);
        System.out.println(personDatum[index-1][2]);
        System.out.println(dvdr);
    }
    
    static void displayPersonB(int index) {
        System.out.println(personId[index-1] + ":" + 
                          personDatum[index-1][0] + " " + 
                          personDatum[index-1][1] + " " + 
                          personDatum[index-1][2]);
        System.out.println(dvdr);
    }
    
    static void getName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("WHAT IS YOUR NAME? ");
        userVal = scanner.nextLine();
        System.out.println("HELLO " + userVal);
        System.out.println();
    }
}