import java.io.*;
import java.nio.file.*;

public class ReadData {
    private static class Employee {
        private String fname;
        private String lname;
        private int A;
        private int B;
        private int C;
        private String city;
        
        public Employee() {
            this.fname = "";
            this.lname = "";
            this.A = 0;
            this.B = 0;
            this.C = 0;
            this.city = "";
        }
        
        public String getFname() { return fname; }
        public void setFname(String fname) { this.fname = fname; }
        
        public String getLname() { return lname; }
        public void setLname(String lname) { this.lname = lname; }
        
        public int getA() { return A; }
        public void setA(int A) { this.A = A; }
        
        public int getB() { return B; }
        public void setB(int B) { this.B = B; }
        
        public int getC() { return C; }
        public void setC(int C) { this.C = C; }
        
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
    }
    
    public static void main(String[] args) {
        String filename = "data.cobol.dat";
        Employee emp = new Employee();
        String fileStatus = "  ";
        boolean endOfFile = false;
        
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            // Check for errors
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
            
            // Read the file until you reach the end:
            while (!endOfFile && fileStatus.equals("00")) {
                String line = reader.readLine();
                if (line == null) {
                    endOfFile = true;
                    fileStatus = "10";
                } else {
                    // Parse the line according to the structure
                    // Assuming fixed format: fname(10) + lname(10) + SSN(13) + city(10)
                    if (line.length() >= 43) {
                        emp.setFname(line.substring(0, 10).trim());
                        emp.setLname(line.substring(10, 20).trim());
                        
                        // Parse SSN components
                        String ssnPart = line.substring(20, 33).trim();
                        if (ssnPart.length() >= 9) {
                            emp.setA(Integer.parseInt(ssnPart.substring(0, 3)));
                            emp.setB(Integer.parseInt(ssnPart.substring(3, 5)));
                            emp.setC(Integer.parseInt(ssnPart.substring(5, 9)));
                        }
                        
                        emp.setCity(line.substring(33, 43).trim());
                        
                        System.out.println(emp.getFname() + "," + " " + emp.getLname() + 
                                         " SSN: " + emp.getA() + "-" + emp.getB() + "-" + emp.getC());
                    } else {
                        endOfFile = true;
                        fileStatus = "10";
                    }
                }
            }
            
            System.out.println("ERROR CODE : " + fileStatus);
            
            if (!endOfFile) {
                System.out.println(emp.getFname() + "," + " " + emp.getLname() + 
                                 " SSN: " + emp.getA() + "-" + emp.getB() + "-" + emp.getC());
            }
            
            if (endOfFile) {
                System.out.println("End of File Reached");
            }
            
        } catch (IOException e) {
            System.out.println("ERROR : File NOT found");
            return;
        }
    }
}