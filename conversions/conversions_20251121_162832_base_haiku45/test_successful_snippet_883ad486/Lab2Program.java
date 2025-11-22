import java.io.*;
import java.nio.file.*;

public class Lab2Program {
    private static final String INPUT_FILE = "lab2.dat";
    private static final String OUTPUT_FILE = "lab2.out";
    private static final int CURRENT_YEAR = 2018;
    
    public static void main(String[] args) {
        try (BufferedReader inputReader = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter outputWriter = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
            
            String myName = String.format("%-71s%-18s%s", "", "Henry Zheng, Lab 3", " ");
            String heading = "Student ID+------+Name----" + 
                           "------------+" + 
                           "--------+-----Enrolled---+";
            String description = String.format("%-48s%-8s%-3s%-5s", "", "Starting", "", "Years");
            
            outputWriter.write(myName);
            outputWriter.newLine();
            outputWriter.write(heading);
            outputWriter.newLine();
            outputWriter.write(description);
            outputWriter.newLine();
            
            String inputLine;
            while ((inputLine = inputReader.readLine()) != null) {
                if (inputLine.length() < 35) {
                    continue;
                }
                
                String studentNumber = inputLine.substring(0, 9);
                String studentName = inputLine.substring(9, 29);
                String enrollYearStr = inputLine.substring(29, 33);
                
                int enrollYear = Integer.parseInt(enrollYearStr.trim());
                int yearsEnrolled = enrollYear - CURRENT_YEAR;
                
                String outputLine = formatOutputLine(studentNumber, studentName, enrollYear, yearsEnrolled);
                outputWriter.write(outputLine);
                outputWriter.newLine();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static String formatOutputLine(String studentNumber, String studentName, 
                                          int enrollYear, int yearsEnrolled) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-9s", studentNumber));
        sb.append(String.format("%-9s", ""));
        sb.append(String.format("%-20s", studentName));
        sb.append(String.format("%-10s", ""));
        sb.append(String.format("%4d", enrollYear));
        sb.append(String.format("%-6s", ""));
        sb.append(String.format("%2d", yearsEnrolled));
        sb.append(String.format("%-5s", ""));
        
        return sb.toString();
    }
}