import java.io.*;
import java.nio.file.*;

public class ReadData {
    static class Employee {
        String fname;
        String lname;
        String A;
        String B;
        String C;
        String city;
        boolean endOfFile;
    }

    static String fileStatus = "00";
    static Employee employee = new Employee();

    public static void main(String[] args) {
        String filename = "data.cobol.dat";
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(filename));
            fileStatus = "00";
        } catch (FileNotFoundException e) {
            fileStatus = "35";
        } catch (IOException e) {
            fileStatus = "99";
        }

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

        if (reader != null) {
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        fileStatus = "10";
                        employee.endOfFile = true;
                        break;
                    }

                    parseEmployeeLine(line);

                    if (!employee.endOfFile) {
                        System.out.println(employee.fname + "," + " " + employee.lname +
                                " SSN: " + employee.A + "-" + employee.B + "-" + employee.C);
                    }
                }

                if (reader.readLine() == null) {
                    fileStatus = "10";
                    employee.endOfFile = true;
                }
            } catch (IOException e) {
                fileStatus = "99";
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("ERROR CODE : " + fileStatus);
        System.out.println(employee.fname + "," + " " + employee.lname +
                " SSN: " + employee.A + "-" + employee.B + "-" + employee.C);

        if (fileStatus.equals("10")) {
            System.out.println("End of File Reached");
        }

        System.exit(0);
    }

    static void parseEmployeeLine(String line) {
        if (line.length() >= 44) {
            employee.fname = line.substring(0, 10).trim();
            employee.lname = line.substring(10, 20).trim();
            employee.A = line.substring(20, 23).trim();
            employee.B = line.substring(23, 25).trim();
            employee.C = line.substring(25, 29).trim();
            employee.city = line.substring(29, 39).trim();
            employee.endOfFile = false;
        }
    }
}