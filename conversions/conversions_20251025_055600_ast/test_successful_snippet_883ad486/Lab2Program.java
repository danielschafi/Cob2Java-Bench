import java.io.*;

public class Lab2Program {
    public static void main(String[] args) {
        String inputFileName = "lab2.dat";
        String outputFileName = "lab2.out";
        String wsMyName = "Henry Zheng, Lab 3";
        String wsHeading = "Student ID+------+Name----------------+--------+-----Enrolled---+";
        String wsDescription = "Starting   Years";
        int wsCurrentYear = 2018;
        char wsEofFlag = 'n';

        try (BufferedReader inputFile = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter outputFile = new BufferedWriter(new FileWriter(outputFileName))) {

            outputFile.write(String.format("%-79s%n", wsMyName));
            outputFile.write(String.format("%-79s%n", wsHeading));
            outputFile.write(String.format("%-79s%n", wsDescription));

            String inputLine;
            while ((inputLine = inputFile.readLine()) != null) {
                String ilStudentNumber = inputLine.substring(0, 9).trim();
                String ilStudentName = inputLine.substring(9, 29).trim();
                int ilStudentEnrollYear = Integer.parseInt(inputLine.substring(29, 33).trim());
                int ilStudentYear = wsCurrentYear - ilStudentEnrollYear;

                String outputLine = String.format("%-9s%-9s%-20s%-10s%-4d%-6s%-1d%-5s",
                        ilStudentNumber, "", ilStudentName, "", ilStudentEnrollYear, "", ilStudentYear, "");
                outputFile.write(String.format("%-79s%n", outputLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}