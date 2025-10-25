import java.io.*;

public class ediParser {
    public static void main(String[] args) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        boolean endOfFile = false;
        String ediDetails = new String(new char[70]);
        String ediSeg = new String(new char[70]);
        String displayEdiSeg = new String(new char[70]);
        int stringEnd;

        try {
            reader = new BufferedReader(new FileReader("Team3_EDI_Data.txt"));
            writer = new BufferedWriter(new FileWriter("Team3_EDI_FINAL.txt"));

            if ((ediDetails = reader.readLine()) == null) {
                endOfFile = true;
            }

            while (!endOfFile) {
                stringEnd = 70;
                while (stringEnd > 0 && ediDetails.charAt(stringEnd - 1) == ' ') {
                    stringEnd--;
                }

                ediSeg = ediDetails.substring(0, stringEnd).trim();
                displayEdiSeg = String.format("%-70s", ediSeg);

                writer.write(displayEdiSeg);
                writer.newLine();
                writer.newLine();

                if ((ediDetails = reader.readLine()) == null) {
                    endOfFile = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}