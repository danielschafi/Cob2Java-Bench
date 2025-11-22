import java.io.*;

public class Lrecl80 {
    public static void main(String[] args) {
        String infileName = "infile.dat";
        String outfileName = "outfile.dat";

        try (BufferedReader infile = new BufferedReader(new FileReader(infileName));
             BufferedWriter outfile = new BufferedWriter(new FileWriter(outfileName))) {

            String inputText;
            while ((inputText = infile.readLine()) != null) {
                if (inputText.length() > 80) {
                    inputText = inputText.substring(0, 80);
                } else {
                    inputText = String.format("%-80s", inputText);
                }

                String outputText = new StringBuilder(inputText).reverse().toString();
                outfile.write(outputText);
                outfile.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error opening or reading file: " + e.getMessage());
            return;
        }

        try (BufferedReader outfile = new BufferedReader(new FileReader(outfileName))) {
            String outputText;
            while ((outputText = outfile.readLine()) != null) {
                outputText = outputText.trim();
                System.out.println(outputText);
            }
        } catch (IOException e) {
            System.err.println("Error opening or reading file: " + e.getMessage());
        }
    }
}