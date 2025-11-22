import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NthRoot {
    
    private int root;
    private double num;
    private double precision;
    private double temp0;
    private double temp1;
    
    public static void main(String[] args) {
        NthRoot program = new NthRoot();
        program.mainProgram();
    }
    
    private void mainProgram() {
        while (true) {
            getInput();
            
            if (precision == 0.0) {
                precision = 0.001;
            }
            
            computeRoot();
            
            displayResults();
        }
    }
    
    private void getInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            System.out.print("Input Base Number: ");
            String numInput = reader.readLine();
            num = Double.parseDouble(numInput);
            
            if (num == 0.0) {
                System.out.println("Good Bye.");
                System.exit(0);
            }
            
            System.out.print("Input Root: ");
            String rootInput = reader.readLine();
            root = Integer.parseInt(rootInput);
            
            System.out.print("Input Desired Precision: ");
            String precisionInput = reader.readLine();
            if (precisionInput.trim().isEmpty()) {
                precision = 0.0;
            } else {
                precision = Double.parseDouble(precisionInput);
            }
            
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
            System.exit(1);
        }
    }
    
    private void computeRoot() {
        temp0 = root;
        temp1 = num / root;
        
        while (Math.abs(temp0 - temp1) >= precision) {
            temp0 = temp1;
            temp1 = ((root - 1.0) * temp1 + num / Math.pow(temp1, root - 1.0)) / root;
        }
    }
    
    private void displayResults() {
        DecimalFormat numFormat = new DecimalFormat("#####.#####");
        numFormat.setMinimumFractionDigits(5);
        numFormat.setMaximumFractionDigits(5);
        
        DecimalFormat rootFormat = new DecimalFormat("#####");
        rootFormat.setMinimumIntegerDigits(5);
        
        DecimalFormat precisionFormat = new DecimalFormat("0.000000000");
        precisionFormat.setMinimumFractionDigits(9);
        precisionFormat.setMaximumFractionDigits(9);
        
        DecimalFormat resultFormat = new DecimalFormat("#####.#####");
        resultFormat.setMinimumFractionDigits(5);
        resultFormat.setMaximumFractionDigits(5);
        
        String field1 = String.format("%11s", numFormat.format(num));
        String field2 = String.format("%5s", rootFormat.format(root));
        String field3 = precisionFormat.format(precision);
        
        System.out.println("   Number           Root           Precision.");
        System.out.println(field1 + "     " + field2 + "              " + field3);
        System.out.println(" ");
        
        String dispRoot = String.format("%11s", resultFormat.format(temp1));
        System.out.println("The Root is: " + dispRoot);
    }
}