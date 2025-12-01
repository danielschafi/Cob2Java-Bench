import java.io.*;
import java.util.Scanner;

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
        
        readPeopleFile();
        
        personIndex = 1;
        displayPersonB();
        while (personIndex <= 3) {
            displayPersonB();
        }
        
        personIndex = 1;
        dataIndex = 1;
        search();
        
        System.exit(0);
    }
    
    static void moveToPeople(String data) {
        int pos = 0;
        for (int i = 0; i < 10 && pos < data.length(); i++) {
            if (pos + 3 <= data.length()) {
                people[i].personId = data.substring(pos, pos + 3);
                pos += 3;
            }
            for (int j = 0; j < 3 && pos < data.length(); j++) {
                if (pos + 10 <= data.length()) {
                    people[i].personData[j] = data.substring(pos, pos + 10);
                    pos += 10;
                } else {
                    people[i].personData[j] = data.substring(pos);
                    pos = data.length();
                }
            }
        }
    }
    
    static void readPeopleFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("PEOPLE.ONELINE.DATA"));
            String line;
            StringBuilder fileContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                fileContent.append(line);
            }
            reader.close();
            moveToPeople(fileContent.toString());
        } catch (IOException e) {
            demoEof = "Y";
        }
    }
    
    static void displayPerson() {
        System.out.println(people[personIndex - 1].personId);
        System.out.println(people[personIndex - 1].personData[0]);
        System.out.println(people[personIndex - 1].personData[1]);
        System.out.println(people[personIndex - 1].personData[2]);
        System.out.println(dvdr);
        personIndex++;
    }
    
    static void displayPersonB() {
        System.out.println(people[personIndex - 1].personId + ":" + 
                         people[personIndex - 1].personData[0] + " " + 
                         people[personIndex - 1].personData[1] + " " + 
                         people[personIndex - 1].personData[2]);
        System.out.println(dvdr);
        personIndex++;
    }
    
    static void getName() {
        System.out.println("WHAT IS YOUR NAME?");
        Scanner scanner = new Scanner(System.in);
        userVal = scanner.nextLine();
        System.out.println("HELLO " + userVal);
        System.out.println(" ");
    }
    
    static void search() {
        boolean found = false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                if (people[i].personData[j].equals("9993334444")) {
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