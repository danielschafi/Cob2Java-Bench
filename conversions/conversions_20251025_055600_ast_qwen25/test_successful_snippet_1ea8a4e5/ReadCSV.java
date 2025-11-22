import java.io.*;

public class ReadCSV {
    public static void main(String[] args) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader("info.csv"));
            writer = new BufferedWriter(new FileWriter("output.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String lastName = String.format("%-25s", parts[0]).substring(0, 25);
                String firstName = String.format("%-15s", parts[1]).substring(0, 15);
                String street = String.format("%-30s", parts[2]).substring(0, 30);
                String city = String.format("%-15s", parts[3]).substring(0, 15);
                String state = String.format("%-3s", parts[4]).substring(0, 3);
                String zip = String.format("%-10s", parts[5]).substring(0, 10);
                writer.write(lastName + "     " + firstName + "     " + street + "     " + city + "     " + state + "     " + zip + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}