import java.io.*;
import java.util.*;

public class Lab2Program {
    private static final String INPUT_FILE = "lab2.dat";
    private static final String OUTPUT_FILE = "lab2.out";
    private static final int CURRENT_YEAR = 2018;
    
    private static class InputRecord {
        String studentNumber;
        String studentName;
        int enrollYear;
        int studentYear;
    }
    
    private static class OutputRecord {
        String studentNumber;
        String studentName;
        int enrollYear;
        int studentYears;
        
        public String format() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-9s", studentNumber == null ? "" : studentNumber));
            sb.append(String.format("%-9s", ""));
            sb.append(String.format("%-20s", studentName == null ? "" : studentName));
            sb.append(String.format("%-10s", ""));
            sb.append(String.format("%04d", enrollYear));
            sb.append(String.format("%-6s", ""));
            sb.append(String.format("%2d", studentYears));
            sb.append(String.format("%-5s", ""));
            return sb.toString();
        }
    }
    
    public static void main(String[] args) {
        BufferedReader inputReader = null;
        BufferedWriter outputWriter = null;
        
        try {
            inputReader = new BufferedReader(new FileReader(INPUT_FILE));
            outputWriter = new BufferedWriter(new FileWriter(OUTPUT_FILE));
            
            String myName = String.format("%-71s%s ", "", "Henry Zheng, Lab 3");
            String heading = "Student ID+------+Name----" + "------------+" + "--------+-----Enrolled---+";
            String description = String.format("%-48s%-8s%-3s%-5s", "", "Starting", "", "Years");
            
            outputWriter.write(myName);
            outputWriter.newLine();
            outputWriter.write(heading);
            outputWriter.newLine();
            outputWriter.write(description);
            outputWriter.newLine();
            
            String line = inputReader.readLine();
            
            while (line != null) {
                if (line.length() >= 35) {
                    InputRecord inputRecord = new InputRecord();
                    inputRecord.studentNumber = line.substring(0, 9);
                    inputRecord.studentName = line.substring(9, 29);
                    inputRecord.enrollYear = Integer.parseInt(line.substring(29, 33).trim());
                    
                    int yearsEnrolled = inputRecord.enrollYear - CURRENT_YEAR;
                    
                    OutputRecord outputRecord = new OutputRecord();
                    outputRecord.studentNumber = inputRecord.studentNumber;
                    outputRecord.studentName = inputRecord.studentName;
                    outputRecord.enrollYear = inputRecord.enrollYear;
                    outputRecord.studentYears = yearsEnrolled;
                    
                    outputWriter.write(outputRecord.format());
                    outputWriter.newLine();
                }
                
                line = inputReader.readLine();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputReader != null) {
                    inputReader.close();
                }
                if (outputWriter != null) {
                    outputWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}