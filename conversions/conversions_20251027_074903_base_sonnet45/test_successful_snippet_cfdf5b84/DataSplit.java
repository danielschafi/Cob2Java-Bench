import java.io.*;
import java.text.DecimalFormat;

public class DataSplit {
    
    private static class InputLine {
        char transactionCode;
        double transactionAmount;
        String paymentType;
        int storeNumber;
        String invoiceNumber;
        String skuCode;
        
        public static InputLine parse(String line) {
            if (line == null || line.length() < 36) {
                return null;
            }
            InputLine il = new InputLine();
            il.transactionCode = line.charAt(0);
            il.transactionAmount = Double.parseDouble(line.substring(1, 8)) / 100.0;
            il.paymentType = line.substring(8, 10);
            il.storeNumber = Integer.parseInt(line.substring(10, 12));
            il.invoiceNumber = line.substring(12, 21);
            il.skuCode = line.substring(21, 36);
            return il;
        }
    }
    
    private String wsEof = "N";
    private int wsSaleCount = 0;
    private double wsSaleTamount = 0.0;
    private int wsLayawayCount = 0;
    private double wsLayawayTamount = 0.0;
    private double wsSLStore01Tamount = 0.0;
    private double wsSLStore02Tamount = 0.0;
    private double wsSLStore03Tamount = 0.0;
    private double wsSLStore07Tamount = 0.0;
    private double wsSLTamount = 0.0;
    private int wsReturnCount = 0;
    private double wsReturnTamount = 0.0;
    private double wsRStore01Tamount = 0.0;
    private double wsRStore02Tamount = 0.0;
    private double wsRStore03Tamount = 0.0;
    private double wsRStore07Tamount = 0.0;
    
    public static void main(String[] args) {
        new DataSplit().run();
    }
    
    public void run() {
        try (BufferedReader inputFile = new BufferedReader(new FileReader("valid.out"));
             BufferedWriter slFile = new BufferedWriter(new FileWriter("SL.out"));
             BufferedWriter returnFile = new BufferedWriter(new FileWriter("returns.out"));
             BufferedWriter countsFile = new BufferedWriter(new FileWriter("counts.out"))) {
            
            String line = inputFile.readLine();
            if (line == null) {
                wsEof = "Y";
            }
            
            while (!wsEof.equals("Y")) {
                InputLine inputLine = InputLine.parse(line);
                if (inputLine != null) {
                    checkSLorR(inputLine, line, slFile, returnFile);
                }
                
                line = inputFile.readLine();
                if (line == null) {
                    wsEof = "Y";
                }
            }
            
            calcAndMoveSLTotals();
            writeSL(countsFile);
            
            calcAndMoveRTotals();
            writeR(countsFile);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void checkSLorR(InputLine il, String line, BufferedWriter slFile, BufferedWriter returnFile) throws IOException {
        if (il.transactionCode == 'S' || il.transactionCode == 'L') {
            slCountAndAdd(il);
            slFile.write(line);
            slFile.newLine();
        } else {
            rCountAndAdd(il);
            returnFile.write(line);
            returnFile.newLine();
        }
    }
    
    private void slCountAndAdd(InputLine il) {
        if (il.transactionCode == 'S') {
            wsSaleCount++;
            wsSaleTamount += il.transactionAmount;
        }
        
        if (il.transactionCode == 'L') {
            wsLayawayCount++;
            wsLayawayTamount += il.transactionAmount;
        }
        
        if (il.storeNumber == 1) {
            wsSLStore01Tamount += il.transactionAmount;
        }
        
        if (il.storeNumber == 2) {
            wsSLStore02Tamount += il.transactionAmount;
        }
        
        if (il.storeNumber == 3) {
            wsSLStore03Tamount += il.transactionAmount;
        }
        
        if (il.storeNumber == 7) {
            wsSLStore07Tamount += il.transactionAmount;
        }
    }
    
    private void rCountAndAdd(InputLine il) {
        wsReturnCount++;
        wsReturnTamount += il.transactionAmount;
        
        if (il.storeNumber == 1) {
            wsRStore01Tamount += il.transactionAmount;
        }
        
        if (il.storeNumber == 2) {
            wsRStore02Tamount += il.transactionAmount;
        }
        
        if (il.storeNumber == 3) {
            wsRStore03Tamount += il.transactionAmount;
        }
        
        if (il.storeNumber == 7) {
            wsRStore07Tamount += il.transactionAmount;
        }
    }
    
    private void calcAndMoveSLTotals() {
        wsSLTamount = wsSaleTamount + wsLayawayTamount;
    }
    
    private void calcAndMoveRTotals() {
        // Calculations are done in the write methods
    }
    
    private void writeSL(BufferedWriter countsFile) throws IOException {
        DecimalFormat moneyFormat = new DecimalFormat("$###,##0.00");
        DecimalFormat percentFormat = new DecimalFormat("##0.00");
        DecimalFormat countFormat = new DecimalFormat("###");
        
        int wsSLCombineTotal = wsSaleCount + wsLayawayCount;
        double wsSPerc = wsSLCombineTotal > 0 ? Math.round((wsSaleCount / (double)wsSLCombineTotal) * 10000.0) / 100.0 : 0.0;
        double wsLPerc = wsSLCombineTotal > 0 ? Math.round((wsLayawayCount / (double)wsSLCombineTotal) * 10000.0) / 100.0 : 0.0;
        
        countsFile.write(padLeft("Sale & Layway Report", 41));
        countsFile.newLine();
        countsFile.write(" ");
        countsFile.newLine();
        countsFile.write("Total Sale records     Total Sale amount    Sale Percentage: ");
        countsFile.newLine();
        countsFile.write(String.format("       %3s                    %12s    %5s%%",
            formatCount(wsSaleCount), moneyFormat.format(wsSaleTamount), percentFormat.format(wsSPerc)));
        countsFile.newLine();
        countsFile.write(" ");
        countsFile.newLine();
        countsFile.write("Total Layaway records  Total Layway amount  Layaway Percentage: ");
        countsFile.newLine();
        countsFile.write(String.format("       %3s                    %12s    %5s%%",
            formatCount(wsLayawayCount), moneyFormat.format(wsLayawayTamount), percentFormat.format(wsLPerc)));
        countsFile.newLine();
        countsFile.write(" ");
        countsFile.newLine();
        countsFile.write(String.format("Total Sale and Layway record: %3d   Total Sale and Layway amount: %12s",
            wsSLCombineTotal, moneyFormat.format(wsSLTamount)));
        countsFile.newLine();
        countsFile.write(" ");
        countsFile