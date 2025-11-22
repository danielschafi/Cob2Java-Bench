import java.io.*;
import java.nio.file.*;
import java.text.DecimalFormat;
import java.util.*;

public class Lab3Program {
    private static final String INPUT_FILE = "lab3.dat";
    private static final String OUTPUT_FILE = "lab3.out";
    
    private String eofFlag = "n";
    private String newLine = " ";
    private String myName = String.format("%71s%s ", "", "Henry Zheng, Lab 3");
    
    private String heading2 = String.format(" %-4s%-15s%-8s%-8s%-8s%-6s%-9s%-2s%-5s%-2s%-5s%-3s%-14s",
        "ITEM", "", "EXTENDED", "", "DISCOUNT", "", "NET PRICE", "", "CLASS", "", "TRANS", "", "TRANSPORTATION");
    
    private String heading3 = String.format("%-22s%-5s%-10s%-6s%-26s%-1s%-10s%-6s%-5s",
        "", "PRICE", "", "AMOUNT", "", "%", "", "CHARGE", "");
    
    private String tailingFormat = " ITEMS WITH OUT A DISCOUNT = %6.1f%%";
    
    private double extendedPricesCalc = 0;
    private double discountCalc = 0;
    private double netCalc = 0;
    private double discount = 0.05;
    private int noDiscount = 0;
    
    private double transA = 11.5;
    private double transAPercent = 0.115;
    private double transB = 3.5;
    private double transBPercent = 0.035;
    private double transOther = 8.5;
    private double transOtherPercent = 0.085;
    
    private double transCalc = 0;
    private double transPercent = 0;
    
    private double extendedTotalCalc = 0;
    private double netTotalCalc = 0;
    private double transTotalCalc = 0;
    
    private double discountCounter = 0;
    private double noDiscountCounter = 0;
    private double discountCount = 0;
    private double discountCountCalc = 0;
    
    private DecimalFormat currencyFormat = new DecimalFormat("$$$,$$9.99");
    private DecimalFormat decimalFormat = new DecimalFormat("zz,zzz,zz9.99");
    private DecimalFormat percentFormat = new DecimalFormat("99.9");
    
    public static void main(String[] args) {
        Lab3Program program = new Lab3Program();
        program.run();
    }
    
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
            
            writer.write(myName);
            writer.newLine();
            writer.newLine();
            writer.newLine();
            writer.write(heading2);
            writer.newLine();
            writer.write(heading3);
            writer.newLine();
            writer.newLine();
            writer.newLine();
            
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() < 27) {
                    continue;
                }
                
                String itemNumber = line.substring(0, 4);
                char productClass = line.charAt(4);
                String itemDescription = line.substring(5, 18);
                int quantity = Integer.parseInt(line.substring(18, 21).trim());
                double pricePerUnit = Double.parseDouble(line.substring(21, 27)) / 100.0;
                
                extendedPricesCalc = quantity * pricePerUnit;
                String extendedPricesEdited = formatDecimal(extendedPricesCalc);
                
                discountCalc = 0;
                if (productClass == 'A' && extendedPricesCalc > 199.00) {
                    discountCounter++;
                    discountCalc = discount * extendedPricesCalc;
                } else if (productClass == 'D' && quantity > 10) {
                    discountCounter++;
                    discountCalc = discount * extendedPricesCalc;
                } else if (productClass != 'A' && productClass != 'D' && 
                           extendedPricesCalc <= 19.90 && quantity <= 10) {
                    noDiscountCounter++;
                }
                
                String discountEdited = formatDecimal(discountCalc);
                
                netCalc = extendedPricesCalc - discountCalc;
                String netEdited = formatDecimal(netCalc);
                extendedTotalCalc += netCalc;
                netTotalCalc += netCalc;
                
                transPercent = 0;
                if (productClass == 'A') {
                    transPercent = transAPercent;
                } else if (productClass == 'B') {
                    transPercent = transBPercent;
                } else if (productClass != 'A' && productClass != 'B' && quantity < 144) {
                    transPercent = transOtherPercent;
                }
                
                transCalc = Math.round(extendedPricesCalc * transPercent * 100.0) / 100.0;
                if (transPercent == 0) {
                    transCalc += 65;
                }
                String transChargeEdited = formatDecimal(transCalc);
                transTotalCalc += transCalc;
                
                System.out.println(transCalc);
                
                writer.write(String.format(" %-15s%-15s%-15s%-16s%-1s%-5s%-5.1f%-5s%-16s",
                    itemDescription, extendedPricesEdited, discountEdited, netEdited,
                    productClass, "", transPercent * 100, "", transChargeEdited));
                writer.newLine();
                writer.newLine();
            }
            
            String totalLine = String.format("%-15s%-15s%-16s%-17s%-15s",
                "", formatCurrency(extendedTotalCalc), "", formatCurrency(netTotalCalc), 
                formatCurrency(transTotalCalc));
            writer.write(totalLine);
            writer.newLine();
            writer.newLine();
            writer.newLine();
            
            discountCount = discountCounter + noDiscountCounter;
            if (discountCount > 0) {
                discountCountCalc = (discountCounter / discountCount) * 100;
            }
            
            String tailingLine = String.format(" ITEMS WITH OUT A DISCOUNT = %6.1f%%", 
                (noDiscountCounter / discountCount) * 100);
            writer.write(tailingLine);
            writer.newLine();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String formatDecimal(double value) {
        if (value == 0) {
            return "0.00";
        }
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value);
    }
    
    private String formatCurrency(double value) {
        DecimalFormat df = new DecimalFormat("$$$,$$9.99");
        return df.format(value);
    }
}