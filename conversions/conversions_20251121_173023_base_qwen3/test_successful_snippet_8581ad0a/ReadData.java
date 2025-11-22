import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadData {
    private static class Employee {
        String fname = "";
        String lname = "";
        int A = 0;
        int B = 0;
        int C = 0;
        String city = "";
        boolean endOfFile = false;
    }

    public static void main(String[] args) {
        Employee employee = new Employee();
        String fileStatus = "00";
        String fileName = "data.cobol.dat";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("ERROR CODE : " + fileStatus);

            if (fileStatus.equals("35")) {
                System.out.println("ERROR : File NOT found");
                return;
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

            while ((line = br.readLine()) != null) {
                if (line.length() >= 34) {
                    employee.fname = line.substring(0, 10).trim();
                    employee.lname = line.substring(10, 20).trim();
                    employee.A = Integer.parseInt(line.substring(20, 23));
                    employee.B = Integer.parseInt(line.substring(23, 25));
                    employee.C = Integer.parseInt(line.substring(25, 29));
                    employee.city = line.substring(29, 39).trim();

                    System.out.println(employee.fname + "," + " " + employee.lname + " SSN: " + employee.A + "-" + employee.B + "-" + employee.C);
                } else {
                    break;
                }
            }

            fileStatus = "10";
            System.out.println("ERROR CODE : " + fileStatus);
            System.out.println(employee.fname + "," + " " + employee.lname + " SSN: " + employee.A + "-" + employee.B + "-" + employee.C);

            if (fileStatus.equals("10")) {
                System.out.println("End of File Reached");
            }

        } catch (IOException e) {
            System.out.println("ERROR CODE : 35");
            System.out.println("ERROR : File NOT found");
        }
    }
}