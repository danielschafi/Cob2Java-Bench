import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TableDemo {
    private static final String DVDR = "--------";
    private static final int MAX_PERSONS = 10;
    private static final int MAX_DATA = 3;

    private String demoEof = "N";
    private String userVal = new String(new char[10]);
    private String[][] people = new String[MAX_PERSONS][MAX_DATA + 1];

    public static void main(String[] args) {
        TableDemo tableDemo = new TableDemo();
        tableDemo.run();
    }

    private void run() {
        System.out.println("Table Demo");
        people[0][0] = "001";
        people[0][1] = "KEVIN";
        people[0][2] = "DATA";
        people[0][3] = "K";
        people[1][0] = "002";
        people[1][1] = "JACOB";
        people[1][2] = "DATA";
        people[1][3] = "J";

        getName();

        readPeopleFile();

        int personIndex = 1;
        while (personIndex <= 3) {
            displayPersonB(personIndex);
            personIndex++;
        }

        personIndex = 1;
        int dataIndex = 1;
        boolean found = false;
        for (int i = 0; i < MAX_PERSONS; i++) {
            for (int j = 1; j <= MAX_DATA; j++) {
                if (people[i][j].equals("9993334444")) {
                    System.out.println("FOUND KIRK");
                    displayPersonB(i + 1);
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

    private void readPeopleFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("PEOPLE.ONELINE.DATA"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() >= 108) {
                    for (int i = 0; i < MAX_PERSONS; i++) {
                        people[i][0] = line.substring(i * 10, i * 10 + 3);
                        for (int j = 1; j <= MAX_DATA; j++) {
                            people[i][j] = line.substring(i * 10 + 3 + (j - 1) * 10, i * 10 + 3 + j * 10);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayPersonB(int personIndex) {
        System.out.println(people[personIndex - 1][0] + ":" + people[personIndex - 1][1] + " " + people[personIndex - 1][2] + " " + people[personIndex - 1][3]);
        System.out.println(DVDR);
    }

    private void getName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("WHAT IS YOUR NAME?");
        userVal = scanner.nextLine().trim();
        System.out.println("HELLO " + userVal);
        System.out.println(" ");
    }
}