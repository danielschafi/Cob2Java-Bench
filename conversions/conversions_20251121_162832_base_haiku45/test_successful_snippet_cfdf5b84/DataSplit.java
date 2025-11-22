import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class DataSplit {
    
    private static class InputLine {
        char transactionCode;
        BigDecimal transactionAmount;
        String paymentType;
        int storeNumber;
        String invoiceNumber1;
        String invoiceNumber2;
        String invoiceNumber3;
        String skuCode;
        String rawLine;
    }
    
    private static int wsSaleCount = 0;
    private static BigDecimal wsSaleTamount = BigDecimal.ZERO;
    private static int wsLayawayCount = 0;
    private static BigDecimal wsLayawayTamount = BigDecimal.ZERO;
    private static BigDecimal wsSLStore01Tamount = BigDecimal.ZERO;
    private static BigDecimal wsSLStore02Tamount = BigDecimal.ZERO;
    private static BigDecimal wsSLStore03Tamount = BigDecimal.ZERO;
    private static BigDecimal wsSLStore07Tamount = BigDecimal.ZERO;
    private static BigDecimal wsSLTamount = BigDecimal.ZERO;
    
    private static int wsReturnCount = 0;
    private static BigDecimal wsReturnTamount = BigDecimal.ZERO;
    private static BigDecimal wsRStore01Tamount = BigDecimal.ZERO;
    private static BigDecimal wsRStore02Tamount = BigDecimal.ZERO;
    private static BigDecimal wsRStore03Tamount = BigDecimal.ZERO;
    private static BigDecimal wsRStore07Tamount = BigDecimal.ZERO;
    
    private static int wsSLCombineTotal = 0;
    private static BigDecimal wsSLTamountFinal = BigDecimal.ZERO;
    private static BigDecimal wsSPerc = BigDecimal.ZERO;
    private static BigDecimal wsLPerc = BigDecimal.ZERO;
    private static BigDecimal wsRCalcTotal = BigDecimal.ZERO;
    
    private static int olSRecord = 0;
    private static BigDecimal olSTotal = BigDecimal.ZERO;
    private static int olLRecord = 0;
    private static BigDecimal olLTotal = BigDecimal.ZERO;
    private static BigDecimal olSLStore01 = BigDecimal.ZERO;
    private static BigDecimal olSLStore02 = BigDecimal.ZERO;
    private static BigDecimal olSLStore03 = BigDecimal.ZERO;
    private static BigDecimal olSLStore07 = BigDecimal.ZERO;
    
    private static int olRRecord = 0;
    private static BigDecimal olRTotal = BigDecimal.ZERO;
    private static BigDecimal olRStore01 = BigDecimal.ZERO;
    private static BigDecimal olRStore02 = BigDecimal.ZERO;
    private static BigDecimal olRStore03 = BigDecimal.ZERO;
    private static BigDecimal olRStore07 = BigDecimal.ZERO;
    
    public static void main(String[] args) {
        try {
            BufferedReader inputReader = new BufferedReader(new FileReader("valid.out"));
            BufferedWriter slWriter = new BufferedWriter(new FileWriter("SL.out"));
            BufferedWriter returnWriter = new BufferedWriter(new FileWriter("returns.out"));
            BufferedWriter countsWriter = new BufferedWriter(new FileWriter("counts.out"));
            
            String line;
            while ((line = inputReader.readLine()) != null) {
                InputLine inputLine = parseLine(line);
                checkSLOrR(inputLine, slWriter, returnWriter);
            }
            
            inputReader.close();
            
            calcAndMoveSLTotals();
            writeSL(countsWriter);
            
            calcAndMoveRTotals();
            wroteR(countsWriter);
            
            slWriter.close();
            returnWriter.close();
            countsWriter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static InputLine parseLine(String line) {
        InputLine il = new InputLine();
        il.rawLine = line;
        
        if (line.length() >= 36) {
            il.transactionCode = line.charAt(0);
            String amountStr = line.substring(1, 8);
            il.transactionAmount = new BigDecimal(amountStr).divide(new BigDecimal(100));
            il.paymentType = line.substring(8, 10);
            il.storeNumber = Integer.parseInt(line.substring(10, 12));
            il.invoiceNumber1 = line.substring(12, 14);
            il.invoiceNumber2 = line.substring(14, 15);
            il.invoiceNumber3 = line.substring(15, 21);
            il.skuCode = line.substring(21, 36);
        }
        
        return il;
    }
    
    private static void checkSLOrR(InputLine il, BufferedWriter slWriter, BufferedWriter returnWriter) throws IOException {
        if (il.transactionCode == 'S' || il.transactionCode == 'L') {
            slCountAndAdd(il);
            slWriter.write(il.rawLine);
            slWriter.newLine();
        } else {
            rCountAndAdd(il);
            returnWriter.write(il.rawLine);
            returnWriter.newLine();
        }
    }
    
    private static void slCountAndAdd(InputLine il) {
        if (il.transactionCode == 'S') {
            wsSaleCount++;
            wsSaleTamount = wsSaleTamount.add(il.transactionAmount);
        }
        
        if (il.transactionCode == 'L') {
            wsLayawayCount++;
            wsLayawayTamount = wsLayawayTamount.add(il.transactionAmount);
        }
        
        if (il.storeNumber == 1) {
            wsSLStore01Tamount = wsSLStore01Tamount.add(il.transactionAmount);
        }
        
        if (il.storeNumber == 2) {
            wsSLStore02Tamount = wsSLStore02Tamount.add(il.transactionAmount);
        }
        
        if (il.storeNumber == 3) {
            wsSLStore03Tamount = wsSLStore03Tamount.add(il.transactionAmount);
        }
        
        if (il.storeNumber == 7) {
            wsSLStore07Tamount = wsSLStore07Tamount.add(il.transactionAmount);
        }
    }
    
    private static void rCountAndAdd(InputLine il) {
        wsReturnCount++;
        wsReturnTamount = wsReturnTamount.add(il.transactionAmount);
        
        if (il.storeNumber == 1) {
            wsRStore01Tamount = wsRStore01Tamount.add(il.transactionAmount);
        }
        
        if (il.storeNumber == 2) {
            wsRStore02Tamount = wsRStore02Tamount.add(il.transactionAmount);
        }
        
        if (il.storeNumber == 3) {
            wsRStore03Tamount = wsRStore03Tamount.add(il.transactionAmount);
        }
        
        if (il.storeNumber == 7) {
            wsRStore07Tamount = wsRStore07Tamount.add(il.transactionAmount);
        }
    }
    
    private static void calcAndMoveSLTotals() {
        olSRecord = wsSaleCount;
        olSTotal = wsSaleTamount;
        olLRecord = wsLayawayCount;
        olLTotal = wsLayawayTamount;
        olSLStore01 = wsSLStore01Tamount;
        olSLStore02 = wsS