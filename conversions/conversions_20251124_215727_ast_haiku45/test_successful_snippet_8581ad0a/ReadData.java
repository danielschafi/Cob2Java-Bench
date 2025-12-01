import java.io.*;
import java.nio.file.*;

public class ReadData {
    static class Employee {
        String fname = "";
        String lname = "";
        int A = 0;
        int B = 0;
        int C = 0;
        String city = "";
    }

    static String fileStatus = "00";
    static Employee employee = new Employee();
    static boolean endOfFile = false;

    public static void main(String[] args) {
        String filePath = "data.cobol.dat";
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            fileStatus = "00";
            
            System.out.println("ERROR CODE : " + fileStatus);
            
            if (fileStatus.equals("35")) {
                System.out.println("ERROR : File NOT found");
                System.exit(0);
            }
            
            if (fileStatus.equals("10")) {
                System.out.println("File Empty");
            }
            
            if (fileStatus.equals("00")) {
                System.out.println("No errors. Reading...");
            }
            
            if (!fileStatus.equals("00")) {
                System.out.println("You are not catching this error!");
            }
            
            String line;
            while (!fileStatus.equals("10")) {
                line = reader.readLine();
                if (line == null) {
                    endOfFile = true;
                    fileStatus = "10";
                } else {
                    parseEmployeeLine(line);
                    System.out.println(employee.fname + "," + " " + employee.lname + 
                                     " SSN: " + employee.A + "-" + employee.B + "-" + employee.C);
                }
            }
            
            reader.close();
            
            System.out.println("ERROR CODE : " + fileStatus);
            System.out.println(employee.fname + "," + " " + employee.lname + 
                             " SSN: " + employee.A + "-" + employee.B + "-" + employee.C);
            
            if (fileStatus.equals("10")) {
                System.out.println("End of File Reached");
            }
            
        } catch (FileNotFoundException e) {
            fileStatus = "35";
            System.out.println("ERROR CODE : " + fileStatus);
            System.out.println("ERROR : File NOT found");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void parseEmployeeLine(String line) {
        String[] parts = line.split(",");
        if (parts.length >= 5) {
            employee.fname = parts[0].trim();
            employee.lname = parts[1].trim();
            try {
                employee.A = Integer.parseInt(parts[2].trim());
                employee.B = Integer.parseInt(parts[3].trim());
                employee.C = Integer.parseInt(parts[4].trim());
            } catch (NumberFormatException e) {
                employee.A = 0;
                employee.B = 0;
                employee.C = 0;
            }
            if (parts.length >= 6) {
                employee.city = parts[5].trim();
            }
        }
    }
}