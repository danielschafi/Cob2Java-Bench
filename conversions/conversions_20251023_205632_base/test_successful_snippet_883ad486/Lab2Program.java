import java.io.*;

public class Lab2Program {
    public static void main(String[] args) {
        String inputFileName = "lab2.dat";
        String outputFileName = "lab2.out";
        String wsMyName = "Henry Zheng, Lab 3";
        String wsHeading = "Student ID+------+Name------------+--------+-----Enrolled---+";
        String wsDescription = "Starting   Years";
        int wsCurrentYear = 2018;
        String wsEofFlag = "n";

        try (BufferedReader inputFile = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter outputFile = new BufferedWriter(new FileWriter(outputFileName))) {

            outputFile.write(wsMyName);
            outputFile.newLine();
            outputFile.write(wsHeading);
            outputFile.newLine();
            outputFile.write(wsDescription);
            outputFile.newLine();

            String inputLine;
            while ((inputLine = inputFile.readLine()) != null) {
                String ilStudentNumber = inputLine.substring(0, 9).trim();
                String ilStudentName = inputLine.substring(9, 29).trim();
                int ilStudentEnrollYear = Integer.parseInt(inputLine.substring(29, 33).trim());
                int ilStudentYear = wsCurrentYear - ilStudentEnrollYear;

                StringBuilder outputLine = new StringBuilder();
                outputLine.append(String.format("%-9s", ilStudentNumber));
                outputLine.append("         ");
                outputLine.append(String.format("%-20s", ilStudentName));
                outputLine.append("          ");
                outputLine.append(String.format("%4d", ilStudentEnrollYear));
                outputLine.append("      ");
                outputLine.append(String.format("%1d", ilStudentYear));
                outputLine.append("     ");

                outputFile.write(outputLine.toString());
                outputFile.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}