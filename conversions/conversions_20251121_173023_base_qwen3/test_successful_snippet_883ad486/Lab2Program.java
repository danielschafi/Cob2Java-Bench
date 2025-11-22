import java.io.*;
import java.nio.file.*;

public class Lab2Program {
    private static final String INPUT_FILE = "lab2.dat";
    private static final String OUTPUT_FILE = "lab2.out";
    private static final int CURRENT_YEAR = 2018;
    
    public static void main(String[] args) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(INPUT_FILE));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(OUTPUT_FILE))) {
            
            // Write the report heading
            StringBuilder myName = new StringBuilder();
            for (int i = 0; i < 71; i++) myName.append(' ');
            myName.append("Henry Zheng, Lab 3 ");
            writer.write(myName.toString());
            writer.newLine();
            
            StringBuilder heading = new StringBuilder();
            heading.append("Student ID+------+Name----");
            heading.append("------------+");
            heading.append("--------+-----Enrolled---+");
            writer.write(heading.toString());
            writer.newLine();
            
            StringBuilder description = new StringBuilder();
            for (int i = 0; i < 48; i++) description.append(' ');
            description.append("Starting");
            description.append("   ");
            description.append("Years");
            writer.write(description.toString());
            writer.newLine();
            
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() >= 35) {
                    String studentNumber = line.substring(0, 9).trim();
                    String studentName = line.substring(9, 29).trim();
                    String enrollYearStr = line.substring(29, 33).trim();
                    int enrollYear = Integer.parseInt(enrollYearStr);
                    
                    // Clear the output buffer
                    StringBuilder outputLine = new StringBuilder();
                    
                    // Move input data to output record and print
                    outputLine.append(String.format("%-9s", studentNumber));
                    outputLine.append("         ");
                    outputLine.append(String.format("%-20s", studentName));
                    outputLine.append("          ");
                    outputLine.append(String.format("%-4d", enrollYear));
                    outputLine.append("      ");
                    
                    int years = CURRENT_YEAR - enrollYear;
                    outputLine.append(String.format("%2d", years));
                    outputLine.append("     ");
                    
                    writer.write(outputLine.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}