import java.io.*;
import java.text.DecimalFormat;

public class Lab3Program {
    private static final String INPUT_FILE = "lab3.dat";
    private static final String OUTPUT_FILE = "lab3.out";
    
    // Input record structure
    private static class InputLine {
        String itemNumber;
        char productClass;
        String itemDescription;
        int quantity;
        double pricePerUnit;
    }
    
    // Output record structure
    private static class OutputLine {
        String itemDescription;
        String extendedPrices;
        String discount;
        String netPrice;
        char classType;
        double transPercent;
        String transCharge;
    }
    
    private static BufferedReader inputFile;
    private static PrintWriter outputFile;
    
    // Working storage variables
    private static boolean eofFlag = false;
    private static String newLine = "\n";
    private static String myName = "                                                                                                                                     Henry Zheng, Lab 3 ";
    private static String heading2 = "     ITEM             EXTENDED      DISCOUNT        NET PRICE      CLASS   TRANS      TRANSPORTATION";
    private static String heading3 = "                        PRICE        AMOUNT                         %             CHARGE";
    private static String tailing1 = "     ITEMS WITH OUT A DISCOUNT = ";
    private static int noDiscountCountFinal = 0;
    
    // Calculation variables
    private static double extendedPricesCalc;
    private static String extendedPricesEdited;
    private static double discountCalc;
    private static String discountEdited;
    private static double netCalc;
    private static String netEdited;
    private static double discount = 0.05;
    private static int noDiscount = 0;
    private static double transA = 11.5;
    private static double transAPercent = 0.115;
    private static double transB = 3.5;
    private static double transBPercent = 0.035;
    private static double transOther = 8.5;
    private static double transOtherPercent = 0.085;
    private static double transCalc;
    private static double transPercent;
    private static String transChargeEdited;
    private static int transChecker = 0;
    private static int transAdder = 65;
    private static double extendedTotalCalc = 0;
    private static double netTotalCalc = 0;
    private static double transTotalCalc = 0;
    private static double discountCounter = 0;
    private static double noDiscountCounter = 0;
    private static double discountCount = 0;
    private static double discountCountCalc = 0;
    private static String totalLine = "              ";
    private static String extendedTotalFinal;
    private static String netTotalFinal;
    private static String transTotalFinal;
    
    public static void main(String[] args) {
        try {
            inputFile = new BufferedReader(new FileReader(INPUT_FILE));
            outputFile = new PrintWriter(new FileWriter(OUTPUT_FILE));
            
            // Write report heading
            outputFile.println(myName);
            outputFile.println();
            outputFile.println();
            outputFile.println(heading2);
            outputFile.println(heading3);
            outputFile.println();
            outputFile.println();
            
            InputLine inputLine = readInputFile();
            while (!eofFlag && inputLine != null) {
                // Clear output buffer
                OutputLine outputLine = new OutputLine();
                
                // Calculations
                extendedPricesCalc = inputLine.quantity * inputLine.pricePerUnit;
                extendedPricesEdited = formatCurrency(extendedPricesCalc);
                outputLine.extendedPrices = extendedPricesEdited;
                
                // Discount amount procedure
                if ((inputLine.productClass == 'A') && (extendedPricesCalc > 19900)) {
                    discountCounter++;
                    discountCalc = discount * extendedPricesCalc;
                    discountEdited = formatCurrency(discountCalc);
                } else {
                    discountCalc = noDiscount;
                    discountEdited = formatCurrency(discountCalc);
                }
                
                if ((inputLine.productClass == 'D') && (inputLine.quantity > 10)) {
                    discountCounter++;
                    discountCalc = discount * extendedPricesCalc;
                    discountEdited = formatCurrency(discountCalc);
                }
                
                if (((inputLine.productClass != 'A') && (inputLine.productClass != 'D')) && 
                    !(extendedPricesCalc > 1990) && !(inputLine.quantity > 10)) {
                    noDiscountCounter++;
                }
                
                // Net price procedure
                netCalc = extendedPricesCalc - discountCalc;
                netEdited = formatCurrency(netCalc);
                extendedTotalCalc += netCalc;
                netTotalCalc += netCalc;
                
                // Transportation charges procedure
                outputLine.transPercent = 0.0;
                transPercent = 0.0;
                
                if (inputLine.productClass == 'A') {
                    outputLine.transPercent = transA;
                    transPercent = transAPercent;
                }
                
                if (inputLine.productClass == 'B') {
                    outputLine.transPercent = transB;
                    transPercent = transBPercent;
                }
                
                if ((inputLine.productClass != 'A') && (inputLine.productClass != 'B') && 
                    (inputLine.quantity < 144)) {
                    outputLine.transPercent = transOther;
                    transPercent = transOtherPercent;
                }
                
                // Transportation calculations
                transCalc = extendedPricesCalc * transPercent;
                if (transPercent == transChecker) {
                    transCalc += transAdder;
                }
                
                transChargeEdited = formatCurrency(transCalc);
                transTotalCalc += transCalc;
                
                // Move input data to output record and print
                outputLine.itemDescription = inputLine.itemDescription;
                outputLine.discount = discountEdited;
                outputLine.netPrice = netEdited;
                outputLine.classType = inputLine.productClass;
                outputLine.transCharge = transChargeEdited;
                
                // Write output line
                outputFile.printf("%-15s%-15s%-15s%-16s%c%8.1f%-15s%n", 
                    outputLine.itemDescription,
                    outputLine.extendedPrices,
                    outputLine.discount,
                    outputLine.netPrice,
                    outputLine.classType,
                    outputLine.transPercent,
                    outputLine.transCharge);
                outputFile.println();
                
                // Read input for next iteration
                inputLine = readInputFile();
            }
            
            // Final calculations
            extendedTotalFinal = formatCurrency(extendedTotalCalc);
            netTotalFinal = formatCurrency(netTotalCalc);
            transTotalFinal = formatCurrency(transTotalCalc);
            
            totalLine += extendedTotalFinal + "              " + netTotalFinal + "              " + transTotalFinal;
            outputFile.println(totalLine);
            outputFile.println();
            outputFile.println();
            
            // Average calculation
            discountCount = discountCounter + noDiscountCounter;
            if (discountCount > 0) {
                discountCountCalc = discountCounter / discountCount;
                discountCountCalc *= 100;
                noDiscountCountFinal = (int) discountCountCalc;
            }
            
            outputFile.println(tailing1 + noDiscountCountFinal + "%");
            
            outputFile.close();
            inputFile.close();
            
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
    
    private static InputLine readInputFile() {
        try {
            String line = inputFile.readLine();
            if (line == null) {
                eofFlag = true;
                return null;
            }
            
            InputLine inputLine = new InputLine();
            inputLine.itemNumber = line.substring(0, 4);
            inputLine.productClass = line.charAt(4);
            inputLine.itemDescription = line.substring(5, 18);
            inputLine.quantity = Integer.parseInt(line.substring(18, 21));
            inputLine.pricePerUnit = Double.parseDouble(line.substring(21, 27));
            
            return inputLine;
        } catch (IOException | NumberFormatException e) {
            eofFlag = true;
            return null;
        }
    }
    
    private static String formatCurrency(double value) {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        return df.format(value);
    }
}