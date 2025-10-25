import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TableDemo {
    private static final String DVDR = "--------";
    private static final int MAX_PERSONS = 10;
    private static final int MAX_DATA = 3;

    private static String demoEof = "N";
    private static String userVal = new String(new char[10]);
    private static String[][] people = new String[MAX_PERSONS][MAX_DATA + 1];

    public static void main(String[] args) {
        System.out.println("Table Demo");
        people[0][0] = "001";
        people[0][1] = "KEVINDATAK";
        people[1][0] = "002";
        people[1][1] = "JACOBDATAJ";

        getName();

        try (BufferedReader br = new BufferedReader(new FileReader("PEOPLE.ONELINE.DATA"))) {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null && index < MAX_PERSONS) {
                people[index][0] = line.substring(0, 3);
                people[index][1] = line.substring(3, 108);
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int personIndex = 0;
        while (personIndex < 3) {
            displayPersonB(personIndex);
            personIndex++;
        }

        personIndex = 0;
        int dataIndex = 0;
        boolean found = false;
        while (personIndex < MAX_PERSONS && !found) {
            if (people[personIndex][1].substring(dataIndex * 10, (dataIndex + 1) * 10).equals("9993334444")) {
                System.out.println("FOUND KIRK");
                displayPersonB(personIndex);
                found = true;
            }
            personIndex++;
        }
    }

    private static void displayPerson(int personIndex) {
        System.out.println(people[personIndex][0]);
        System.out.println(people[personIndex][1].substring(0, 10));
        System.out.println(people[personIndex][1].substring(10, 20));
        System.out.println(people[personIndex][1].substring(20, 30));
        System.out.println(DVDR);
    }

    private static void displayPersonB(int personIndex) {
        System.out.println(people[personIndex][0] + ": " +
                people[personIndex][1].substring(0, 10) + " " +
                people[personIndex][1].substring(10, 20) + " " +
                people[personIndex][1].substring(20, 30));
        System.out.println(DVDR);
    }

    private static void getName() {
        System.out.print("WHAT IS YOUR NAME?");
        try {
            BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
            userVal = reader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("HELLO " + userVal);
        System.out.println();
    }
}