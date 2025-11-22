import java.io.*;
import java.nio.file.*;

public class TableDemo {
    static class Person {
        String personId;
        String[] personData;
        
        Person() {
            personId = "";
            personData = new String[3];
            for (int i = 0; i < 3; i++) {
                personData[i] = "";
            }
        }
    }
    
    static Person[] people = new Person[10];
    static String demoEof = "N";
    static String dvdr = "--------";
    static String userVal = "";
    static int personIndex = 0;
    static int dataIndex = 0;
    
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            people[i] = new Person();
        }
        
        System.out.println("Table Demo");
        
        String initialData = "001KEVINDATAK002JACOBDATAJ";
        moveToPeople(initialData);
        
        getName();
        
        try {
            openInputFile();
            readFile();
            closeInputFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        personIndex = 1;
        displayPersonB();
        if (personIndex <= 3) {
            personIndex = 2;
            displayPersonB();
            if (personIndex <= 3) {
                personIndex = 3;
                displayPersonB();
            }
        }
        
        personIndex = 1;
        dataIndex = 1;
        searchPerson();
    }
    
    static void moveToPeople(String data) {
        int pos = 0;
        for (int i = 0; i < 10 && pos < data.length(); i++) {
            if (pos + 3 <= data.length()) {
                people[i].personId = data.substring(pos, pos + 3);
                pos += 3;
            }
            for (int j = 0; j < 3 && pos < data.length(); j++) {
                int endPos = Math.min(pos + 10, data.length());
                people[i].personData[j] = data.substring(pos, endPos);
                if (people[i].personData[j].length() < 10) {
                    people[i].personData[j] = String.format("%-10s", people[i].personData[j]);
                }
                pos += 10;
            }
        }
    }
    
    static void getName() {
        System.out.println("WHAT IS YOUR NAME?");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            userVal = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("HELLO " + userVal);
        System.out.println(" ");
    }
    
    static void openInputFile() throws IOException {
    }
    
    static void readFile() throws IOException {
        try {
            String content = new String(Files.readAllBytes(Paths.get("PEOPLE.ONELINE.DATA")));
            moveToPeople(content);
        } catch (NoSuchFileException e) {
            demoEof = "Y";
        }
    }
    
    static void closeInputFile() throws IOException {
    }
    
    static void displayPersonB() {
        if (personIndex >= 1 && personIndex <= 10) {
            System.out.println(people[personIndex - 1].personId + ":" + 
                             people[personIndex - 1].personData[0].trim() + " " + 
                             people[personIndex - 1].personData[1].trim() + " " + 
                             people[personIndex - 1].personData[2].trim());
            System.out.println(dvdr);
            personIndex++;
        }
    }
    
    static void searchPerson() {
        boolean found = false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                if (people[i].personData[j].trim().equals("9993334444")) {
                    System.out.println("FOUND KIRK");
                    personIndex = i + 1;
                    displayPersonB();
                    found = true;
                    break;
                }
            }
            if (found) break;
        }
        if (!found) {
            System.out.println("NOT FOUND");
        }
    }
}