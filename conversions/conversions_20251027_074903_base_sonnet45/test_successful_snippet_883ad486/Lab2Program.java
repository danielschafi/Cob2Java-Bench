import java.io.*;
import java.nio.file.*;

public class Lab2Program {
    
    private static final int CURRENT_YEAR = 2018;
    
    public static void main(String[] args) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("lab2.dat"));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get("lab2.out"))) {
            
            writeHeader(writer);
            
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                if (inputLine.length() >= 35) {
                    processRecord(inputLine, writer);
                }
            }
            
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
            System.exit(1);
        }
    }
    
    private static void writeHeader(BufferedWriter writer) throws IOException {
        StringBuilder myName = new StringBuilder();
        for (int i = 0; i < 71; i++) {
            myName.append(' ');
        }
        myName.append("Henry Zheng, Lab 3");
        myName.append(' ');
        writer.write(myName.toString());
        writer.newLine();
        
        String heading = "Student ID+------+Name----" + 
                        "------------+" + 
                        "--------+-----Enrolled---+";
        writer.write(heading);
        writer.newLine();
        
        StringBuilder description = new StringBuilder();
        for (int i = 0; i < 48; i++) {
            description.append(' ');
        }
        description.append("Starting");
        for (int i = 0; i < 3; i++) {
            description.append(' ');
        }
        description.append("Years");
        writer.write(description.toString());
        writer.newLine();
    }
    
    private static void processRecord(String inputLine, BufferedWriter writer) throws IOException {
        String studentNumber = inputLine.substring(0, 9);
        String studentName = inputLine.substring(9, 29);
        String enrollYearStr = inputLine.substring(29, 33);
        
        int enrollYear = Integer.parseInt(enrollYearStr);
        int years = CURRENT_YEAR - enrollYear;
        
        StringBuilder outputLine = new StringBuilder();
        outputLine.append(studentNumber);
        for (int i = 0; i < 9; i++) {
            outputLine.append(' ');
        }
        outputLine.append(studentName);
        for (int i = 0; i < 10; i++) {
            outputLine.append(' ');
        }
        outputLine.append(enrollYearStr);
        for (int i = 0; i < 6; i++) {
            outputLine.append(' ');
        }
        
        String yearsFormatted = String.format("%2d", years).replace(' ', ' ');
        if (years < 10) {
            outputLine.append(' ');
        }
        outputLine.append(years);
        
        for (int i = 0; i < 5; i++) {
            outputLine.append(' ');
        }
        
        writer.write(outputLine.toString());
        writer.newLine();
    }
}