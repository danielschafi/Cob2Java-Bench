import java.io.*;

public class ReadCSV {
    public static void main(String[] args) {
        String inputFile = "info.csv";
        String outputFile = "output.txt";
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(outputFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String lastName = String.format("%-25s", parts[0].trim()).substring(0, 25);
                    String firstName = String.format("%-15s", parts[1].trim()).substring(0, 15);
                    String streetAddr = String.format("%-30s", parts[2].trim()).substring(0, 30);
                    String city = String.format("%-15s", parts[3].trim()).substring(0, 15);
                    String state = String.format("%-3s", parts[4].trim()).substring(0, 3);
                    String zip = String.format("%-10s", parts[5].trim()).substring(0, 10);

                    String outputRecord = lastName + "     " + firstName + "     " + streetAddr + "     " + city + "     " + state + "     " + zip;
                    writer.write(outputRecord);
                    writer.newLine();
                }
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