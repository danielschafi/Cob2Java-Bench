import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Lab3Program {
    private static final String INPUT_FILE = "lab3.dat";
    private static final String OUTPUT_FILE = "lab3.out";
    
    private static BufferedReader inputFile;
    private static BufferedWriter outputFile;
    
    private static String wsEofFlag = "n";
    private static String wsNewLine = " ";
    
    private static BigDecimal wsExtendedPricesCalc = BigDecimal.ZERO;
    private static BigDecimal wsDiscountCalc = BigDecimal.ZERO;
    private static BigDecimal wsNetCalc = BigDecimal.ZERO;
    private static BigDecimal wsDiscount = new BigDecimal("0.05");
    private static BigDecimal wsNoDiscount = BigDecimal.ZERO;
    
    private static BigDecimal wsTransA = new BigDecimal("11.5");
    private static BigDecimal wsTransAPercent = new BigDecimal("0.115");
    private static BigDecimal wsTransB = new BigDecimal("3.5");
    private static BigDecimal wsTransBPercent = new BigDecimal("0.035");
    private static BigDecimal wsTransOther = new BigDecimal("8.5");
    private static BigDecimal wsTransOtherPercent = new BigDecimal("0.085");
    private static BigDecimal wsTransCalc = BigDecimal.ZERO;
    private static BigDecimal wsTransPercent = BigDecimal.ZERO;
    private static BigDecimal wsTransChecker = BigDecimal.ZERO;
    private static BigDecimal wsTransAdder = new BigDecimal("65");
    
    private static BigDecimal wsExtendedTotalCalc = BigDecimal.ZERO;
    private static BigDecimal wsNetTotalCalc = BigDecimal.ZERO;
    private static BigDecimal wsTransTotalCalc = BigDecimal.ZERO;
    
    private static BigDecimal wsDiscountCounter = BigDecimal.ZERO;
    private static BigDecimal wsNoDiscountCounter = BigDecimal.ZERO;
    private static BigDecimal wsDiscountCount = BigDecimal.ZERO;
    private static BigDecimal wsDiscountCountCalc = BigDecimal.ZERO;
    
    private static String ilItemNumber;
    private static String ilProductClass;
    private static String ilItemDescription;
    private static int ilQuantity;
    private static BigDecimal ilPricePerUnit;
    
    public static void main(String[] args) {
        try {
            inputFile = new BufferedReader(new FileReader(INPUT_FILE));
            outputFile = new BufferedWriter(new FileWriter(OUTPUT_FILE));
            
            writeOutputLine(getMyName());
            writeOutputLine(wsNewLine);
            writeOutputLine(wsNewLine);
            writeOutputLine(getHeading2());
            writeOutputLine(getHeading3());
            writeOutputLine(wsNewLine);
            writeOutputLine(wsNewLine);
            
            readInputFile();
            
            while (!wsEofFlag.equals("y")) {
                String outputLine = processRecord();
                
                writeOutputLine(outputLine);
                writeOutputLine(wsNewLine);
                
                readInputFile();
                
                String totalLine = getTotalLine();
                writeOutputLine(totalLine);
                writeOutputLine(wsNewLine);
                writeOutputLine(wsNewLine);
                
                wsDiscountCount = wsDiscountCounter.add(wsNoDiscountCounter);
                
                if (wsDiscountCount.compareTo(BigDecimal.ZERO) != 0) {
                    wsDiscountCountCalc = wsDiscountCounter.divide(wsDiscountCount, 6, RoundingMode.HALF_UP);
                } else {
                    wsDiscountCountCalc = BigDecimal.ZERO;
                }
                
                wsDiscountCountCalc = wsDiscountCountCalc.multiply(new BigDecimal("100"));
                
                String tailingLine = getTailing1(wsDiscountCountCalc);
                writeOutputLine(tailingLine);
            }
            
            inputFile.close();
            outputFile.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void readInputFile() throws IOException {
        String line = inputFile.readLine();
        if (line == null) {
            wsEofFlag = "y";
        } else {
            if (line.length() >= 27) {
                ilItemNumber = line.substring(0, 4);
                ilProductClass = line.substring(4, 5);
                ilItemDescription = line.substring(5, 18);
                ilQuantity = Integer.parseInt(line.substring(18, 21).trim());
                String priceStr = line.substring(21, 27);
                ilPricePerUnit = new BigDecimal(priceStr.substring(0, 4) + "." + priceStr.substring(4, 6));
            }
        }
    }
    
    private static String processRecord() {
        wsExtendedPricesCalc = ilPricePerUnit.multiply(new BigDecimal(ilQuantity));
        
        wsDiscountCalc = BigDecimal.ZERO;
        
        if (ilProductClass.equals("A") && wsExtendedPricesCalc.compareTo(new BigDecimal("19900")) > 0) {
            wsDiscountCounter = wsDiscountCounter.add(BigDecimal.ONE);
            wsDiscountCalc = wsDiscount.multiply(wsExtendedPricesCalc);
        } else {
            wsDiscountCalc = wsNoDiscount;
        }
        
        if (ilProductClass.equals("D") && ilQuantity > 10) {
            wsDiscountCounter = wsDiscountCounter.add(BigDecimal.ONE);
            wsDiscountCalc = wsDiscount.multiply(wsExtendedPricesCalc);
        }
        
        if ((!ilProductClass.equals("A") && !ilProductClass.equals("D")) ||
            (wsExtendedPricesCalc.compareTo(new BigDecimal("1990")) <= 0 && ilQuantity <= 10)) {
            wsNoDiscountCounter = wsNoDiscountCounter.add(BigDecimal.ONE);
        }
        
        wsNetCalc = wsExtendedPricesCalc.subtract(wsDiscountCalc);
        wsExtendedTotalCalc = wsExtendedTotalCalc.add(wsNetCalc);
        wsNetTotalCalc = wsNetTotalCalc.add(wsNetCalc);
        
        BigDecimal olTransPercent = BigDecimal.ZERO;
        wsTransPercent = BigDecimal.ZERO;
        
        if (ilProductClass.equals("A")) {
            olTransPercent = wsTransA;
            wsTransPercent = wsTransAPercent;
        }
        
        if (ilProductClass.equals("B")) {
            olTransPercent = wsTransB;
            wsTransPercent = wsTransBPercent;
        }
        
        if (!ilProductClass.equals("A") && !ilProductClass.equals("B") && ilQuantity < 144) {
            olTransPercent = wsTransOther;
            wsTransPercent = wsTransOtherPercent;
        }
        
        wsTransCalc = wsExtendedPricesCalc.multiply(wsTransPercent).setScale(2, RoundingMode.HALF_UP);
        
        if (wsTransPercent.compareTo(wsTransChecker) == 0) {
            wsTransCalc = wsTransCalc.add(wsTransAdder);
        }
        
        wsTransTotalCalc = wsTransTotalCalc.add(wsTransCalc);
        
        System.out.println(wsTransCalc);
        
        String extendedPricesEdited = formatAmount(wsExtendedPricesCalc);
        String discountEdited = formatAmount(wsDiscountCalc);
        String netEdited = formatAmount(wsNetCalc);
        String transChargeEdited = formatAmount(wsTransCal