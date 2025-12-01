import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Lab3Program {
    private static class InputRecord {
        String itemNumber;
        char productClass;
        String itemDescription;
        int quantity;
        double pricePerUnit;
    }

    private static class OutputRecord {
        String itemDescription;
        String extendedPrices;
        String discount;
        String netPrice;
        char productClass;
        double transPercent;
        String transCharge;
    }

    private String eofFlag = "n";
    private String newLine = " ";
    private String myName = String.format("%-71s%s ", "", "Henry Zheng, Lab 3");
    private String heading2 = String.format(" %-4s%-15s%-8s%-8s%-8s%-6s%-9s%-2s%-5s%-2s%-5s%-3s%-14s",
            "ITEM", "", "EXTENDED", "", "DISCOUNT", "", "NET PRICE", "", "CLASS", "", "TRANS", "", "TRANSPORTATION");
    private String heading3 = String.format("%-22s%-5s%-10s%-6s%-26s%-1s%-10s%-6s%-5s",
            "", "PRICE", "", "AMOUNT", "", "%", "", "CHARGE", "");

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
    private double transChecker = 0;
    private int transAdder = 65;

    private double extendedTotalCalc = 0;
    private double netTotalCalc = 0;
    private double transTotalCalc = 0;

    private double discountCounter = 0;
    private double noDiscountCounter = 0;
    private double discountCount = 0;
    private double discountCountCalc = 0;

    public static void main(String[] args) {
        Lab3Program program = new Lab3Program();
        program.run();
    }

    public void run() {
        try (BufferedReader inputReader = new BufferedReader(new FileReader("lab3.dat"));
             PrintWriter outputWriter = new PrintWriter(new FileWriter("lab3.out"))) {

            outputWriter.println(myName);
            outputWriter.println(newLine);
            outputWriter.println(newLine);
            outputWriter.println(heading2);
            outputWriter.println(heading3);
            outputWriter.println(newLine);
            outputWriter.println(newLine);

            String line;
            while ((line = inputReader.readLine()) != null) {
                if (line.length() < 27) continue;

                InputRecord input = parseInputLine(line);
                OutputRecord output = new OutputRecord();

                extendedPricesCalc = input.quantity * input.pricePerUnit;
                output.extendedPrices = formatNumber(extendedPricesCalc);
                output.itemDescription = input.itemDescription;

                discountCalc = 0;
                if (input.productClass == 'A' && extendedPricesCalc > 199.00) {
                    discountCounter++;
                    discountCalc = discount * extendedPricesCalc;
                } else if (input.productClass == 'D' && input.quantity > 10) {
                    discountCounter++;
                    discountCalc = discount * extendedPricesCalc;
                } else if (input.productClass != 'A' && input.productClass != 'D' &&
                           extendedPricesCalc <= 19.90 && input.quantity <= 10) {
                    noDiscountCounter++;
                }

                output.discount = formatNumber(discountCalc);

                netCalc = extendedPricesCalc - discountCalc;
                output.netPrice = formatNumber(netCalc);
                extendedTotalCalc += netCalc;
                netTotalCalc += netCalc;

                transPercent = 0;
                if (input.productClass == 'A') {
                    output.transPercent = transAPercent;
                    transPercent = transAPercent;
                } else if (input.productClass == 'B') {
                    output.transPercent = transBPercent;
                    transPercent = transBPercent;
                } else if (input.productClass != 'A' && input.productClass != 'B' && input.quantity < 144) {
                    output.transPercent = transOtherPercent;
                    transPercent = transOtherPercent;
                }

                transCalc = Math.round(extendedPricesCalc * transPercent * 100.0) / 100.0;
                if (transPercent == transChecker) {
                    transCalc += transAdder;
                }

                output.transCharge = formatNumber(transCalc);
                transTotalCalc += transCalc;
                output.productClass = input.productClass;

                outputWriter.println(formatOutputLine(output));
                outputWriter.println(newLine);
            }

            outputWriter.println(formatTotalLine());
            outputWriter.println(newLine);
            outputWriter.println(newLine);

            discountCount = discountCounter + noDiscountCounter;
            if (discountCount > 0) {
                discountCountCalc = (discountCounter / discountCount) * 100;
            }

            String tailingLine = String.format(" %-28s%6.1f%%",
                    "ITEMS WITH OUT A DISCOUNT = ", discountCountCalc);
            outputWriter.println(tailingLine);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InputRecord parseInputLine(String line) {
        InputRecord record = new InputRecord();
        record.itemNumber = line.substring(0, 4);
        record.productClass = line.charAt(4);
        record.itemDescription = line.substring(5, 18);
        record.quantity = Integer.parseInt(line.substring(18, 21).trim());
        record.pricePerUnit = Double.parseDouble(line.substring(21, 27)) / 100.0;
        return record;
    }

    private String formatNumber(double value) {
        DecimalFormat df = new DecimalFormat("##,##0.00");
        return df.format(value);
    }

    private String formatOutputLine(OutputRecord output) {
        return String.format(" %-15s%-15s%-15s%-16s%-1s%-5s%5.1f%-5s%-16s",
                output.itemDescription,
                output.extendedPrices,
                output.discount,
                output.netPrice,
                output.productClass,
                "",
                output.transPercent * 100,
                "",
                output.transCharge);
    }

    private String formatTotalLine() {
        DecimalFormat df = new DecimalFormat("$##,##0.00");
        return String.format("%-15s%-15s%-16s%-17s%-15s",
                "",
                df.format(extendedTotalCalc),
                "",
                df.format(netTotalCalc),
                "",
                df.format(transTotalCalc));
    }
}