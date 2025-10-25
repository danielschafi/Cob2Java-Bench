import java.io.*;

public class ediParser {
    public static void main(String[] args) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader("Team3_EDI_Data.txt"));
            writer = new BufferedWriter(new FileWriter("Team3_EDI_FINAL.txt"));
            String ediDetails;
            while ((ediDetails = reader.readLine()) != null) {
                int stringEnd = ediDetails.length();
                while (stringEnd > 0 && ediDetails.charAt(stringEnd - 1) == ' ') {
                    stringEnd--;
                }
                String ediSeg = ediDetails.substring(0, stringEnd);
                writer.write(ediSeg);
                writer.newLine();
                writer.newLine();
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