import java.io.*;
import java.util.*;

public class ReportTest {
    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "report.txt";
    
    private static class StudentRecord {
        int studentId;
        String studentName;
        String major;
        int numCourses;
        
        public StudentRecord(int studentId, String studentName, String major, int numCourses) {
            this.studentId = studentId;
            this.studentName = studentName;
            this.major = major;
            this.numCourses = numCourses;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Starting test report program.");
        
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_FILE))) {
            
            // Report header
            writer.println("                     Customer Order Report");
            writer.println("                              PAGE   1");
            writer.println();
            
            // Detail lines
            String line;
            int pageNumber = 1;
            int currentLine = 0;
            
            while ((line = reader.readLine()) != null) {
                if (currentLine >= 36) { // Page limit is 66, but detail starts at 6 and ends at 42
                    // Move to next page
                    pageNumber++;
                    writer.println();
                    writer.println("                              PAGE   " + pageNumber);
                    writer.println();
                    currentLine = 0;
                }
                
                String[] fields = line.split("\\s+");
                if (fields.length >= 4) {
                    try {
                        int studentId = Integer.parseInt(fields[0]);
                        String studentName = fields[1];
                        String major = fields[2];
                        int numCourses = Integer.parseInt(fields[3]);
                        
                        // Format and write detail line
                        StringBuilder detailLine = new StringBuilder();
                        detailLine.append(String.format("%6d", studentId));
                        detailLine.append(String.format("%-20s", studentName));
                        detailLine.append(String.format("%-3s", major));
                        detailLine.append(String.format("%3d", numCourses));
                        
                        writer.println(detailLine.toString());
                        currentLine++;
                    } catch (NumberFormatException e) {
                        // Skip invalid records
                    }
                }
            }
            
            // Report footer
            writer.println();
            writer.println("Report generation completed.");
            
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
        
        System.out.println("Done.");
    }
}