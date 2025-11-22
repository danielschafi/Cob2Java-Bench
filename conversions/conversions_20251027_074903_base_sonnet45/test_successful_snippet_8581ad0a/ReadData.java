import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadData {
    
    private static class Employee {
        String fname;
        String lname;
        String ssnA;
        String ssnB;
        String ssnC;
        String city;
        boolean endOfFile;
        
        public Employee() {
            this.fname = "";
            this.lname = "";
            this.ssnA = "";
            this.ssnB = "";
            this.ssnC = "";
            this.city = "";
            this.endOfFile = false;
        }
    }
    
    private static String fileStatus = "00";
    private static Employee employee = new Employee();
    
    public static void main(String[] args) {
        BufferedReader reader = null;
        
        try {
            reader = new BufferedReader(new FileReader("data.cobol.dat"));
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
            
            while (!fileStatus.equals("10")) {
                String line = reader.readLine();
                
                if (line == null) {
                    fileStatus = "10";
                    employee.endOfFile = true;
                } else {
                    if (line.length() >= 39) {
                        employee.fname = line.substring(0, 10);
                        employee.lname = line.substring(10, 20);
                        employee.ssnA = line.substring(20, 23);
                        employee.ssnB = line.substring(23, 25);
                        employee.ssnC = line.substring(25, 29);
                        employee.city = line.substring(29, Math.min(39, line.length()));
                    } else {
                        employee.fname = "";
                        employee.lname = "";
                        employee.ssnA = "";
                        employee.ssnB = "";
                        employee.ssnC = "";
                        employee.city = "";
                    }
                    
                    System.out.println(employee.fname + ", " + employee.lname + 
                                     " SSN: " + employee.ssnA + "-" + employee.ssnB + "-" + employee.ssnC);
                }
            }
            
            System.out.println("ERROR CODE : " + fileStatus);
            System.out.println(employee.fname + ", " + employee.lname + 
                             " SSN: " + employee.ssnA + "-" + employee.ssnB + "-" + employee.ssnC);
            
            if (fileStatus.equals("10")) {
                System.out.println("End of File Reached");
            }
            
        } catch (IOException e) {
            fileStatus = "35";
            System.out.println("ERROR CODE : " + fileStatus);
            System.out.println("ERROR : File NOT found");
            System.exit(0);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}