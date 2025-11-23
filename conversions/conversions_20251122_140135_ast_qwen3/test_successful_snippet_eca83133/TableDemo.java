import java.io.*;
import java.util.*;

public class TableDemo {
    static class Person {
        String personId;
        String[] personData;
        
        public Person() {
            this.personData = new String[3];
        }
    }
    
    static Person[] people = new Person[10];
    static String demoEof = "N";
    static String dvdr = "--------";
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("Table Demo");
        
        // Initialize people array
        for (int i = 0; i < 10; i++) {
            people[i] = new Person();
        }
        
        // Move data to people
        String data = "001KEVINDATAK002JACOBDATAJ";
        int index = 0;
        
        for (int i = 0; i < 2; i++) {
            people[i].personId = data.substring(index, index + 3);
            index += 3;
            
            for (int j = 0; j < 3; j++) {
                people[i].personData[j] = data.substring(index, index + 10);
                index += 10;
            }
        }
        
        getName();
        
        // Simulate file reading
        String personRecord = "001KEVINDATAK002JACOBDATAJ";
        // In a real implementation, you would read from a file
        
        // Move person record to people
        index = 0;
        for (int i = 0; i < 2; i++) {
            people[i].personId = personRecord.substring(index, index + 3);
            index += 3;
            for (int j = 0; j < 3; j++) {
                people[i].personData[j] = personRecord.substring(index, index + 10);
                index += 10;
            }
        }
        
        // Display first 3 persons
        int personIndex = 1;
        while (personIndex <= 3) {
            displayPersonB(personIndex);
            personIndex++;
        }
        
        // Search for specific data
        personIndex = 1;
        int dataIndex = 1;
        boolean found = false;
        
        while (personIndex <= 2 && !found) {
            if (people[personIndex - 1].personData[dataIndex - 1].equals("9993334444")) {
                System.out.println("FOUND KIRK");
                displayPersonB(personIndex);
                found = true;
            } else {
                personIndex++;
            }
        }
        
        if (!found) {
            System.out.println("NOT FOUND");
        }
    }
    
    static void getName() {
        System.out.println("WHAT IS YOUR NAME?");
        String userVal = scanner.nextLine();
        System.out.println("HELLO " + userVal);
        System.out.println();
    }
    
    static void displayPersonB(int index) {
        System.out.println(people[index - 1].personId + ":" + 
                          people[index - 1].personData[0] + " " +
                          people[index - 1].personData[1] + " " +
                          people[index - 1].personData[2]);
        System.out.println(dvdr);
    }
}