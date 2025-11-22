import java.io.*;
import java.text.DecimalFormat;

public class Lab3Program {
    public static void main(String[] args) {
        String inputFileName = "lab3.dat";
        String outputFileName = "lab3.out";
        String eofFlag = "n";
        String newLine = " ";
        String myName = "Henry Zheng, Lab 3";
        String heading2 = "    ITEM              EXTENDED      DISCOUNT    NET PRICE  CLASS  TRANS  TRANSPORTATION";
        String heading3 = "                    PRICE         AMOUNT       %        CHARGE";
        String tailing1 = "ITEMS WITH OUT A DISCOUNT = ";
        double extendedPricesCalc = 0.0;
        String extendedPricesEdited = "";
        double discountCalc = 0.0;
        String discountEdited = "";
        double netCalc = 0.0;
        String netEdited = "";
        double discount = 0.05;
        int noDiscount = 0;
        double transA = 11.5;
        double transAPercent = 0.115;
        double transB = 3.5;
        double transBPercent = 0.035;
        double transOther = 8.5;
        double transOtherPercent = 0.085;
        double transCalc = 0.0;
        double transPercent = 0.0;
        String transChargeEdited = "";
        int transChecker = 0;
        int transAdder = 65;
        double extendedTotalCalc = 0.0;
        double netTotalCalc = 0.0;
        double transTotalCalc = 0.0;
        int discountCounter = 0;
        int noDiscountCounter = 0;
        int discountCount = 0;
        double discountCountCalc = 0.0;
        String extendedTotalFinal = "";
        String netTotalFinal = "";
        String transTotalFinal = "";
        String noDiscountCountFinal = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

            writer.write(myName);
            writer.newLine();
            writer.newLine();
            writer.write(heading2);
            writer.newLine();
            writer.write(heading3);
            writer.newLine();
            writer.newLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String itemNumber = line.substring(0, 4).trim();
                String productClass = line.substring(4, 5).trim();
                String itemDescription = line.substring(5, 18).trim();
                int quantity = Integer.parseInt(line.substring(18, 21).trim());
                double pricePerUnit = Double.parseDouble(line.substring(21, 27).trim());

                extendedPricesCalc = quantity * pricePerUnit;
                DecimalFormat extendedPricesFormat = new DecimalFormat("###,###,###.00");
                extendedPricesEdited = extendedPricesFormat.format(extendedPricesCalc);

                if (productClass.equals("A") && extendedPricesCalc > 19900) {
                    discountCounter++;
                    discountCalc = discount * extendedPricesCalc;
                } else if (productClass.equals("D") && quantity > 10) {
                    discountCounter++;
                    discountCalc = discount * extendedPricesCalc;
                } else {
                    noDiscountCounter++;
                    discountCalc = 0;
                }

                DecimalFormat discountFormat = new DecimalFormat("###,###,###.00");
                discountEdited = discountFormat.format(discountCalc);

                netCalc = extendedPricesCalc - discountCalc;
                DecimalFormat netFormat = new DecimalFormat("###,###,###.00");
                netEdited = netFormat.format(netCalc);

                extendedTotalCalc += netCalc;
                netTotalCalc += netCalc;

                if (productClass.equals("A")) {
                    transPercent = transAPercent;
                } else if (productClass.equals("B")) {
                    transPercent = transBPercent;
                } else if (quantity < 144) {
                    transPercent = transOtherPercent;
                } else {
                    transPercent = 0;
                }

                transCalc = extendedPricesCalc * transPercent;
                if (transPercent == transChecker) {
                    transCalc += transAdder;
                }

                DecimalFormat transChargeFormat = new DecimalFormat("###,###,###.00");
                transChargeEdited = transChargeFormat.format(transCalc);

                transTotalCalc += transCalc;

                String outputLine = String.format("%-15s%-15s%-15s%-16s%-5s%-5.2f%-16s", 
                    itemDescription, extendedPricesEdited, discountEdited, netEdited, productClass, transPercent, transChargeEdited);
                writer.write(outputLine);
                writer.newLine();
                writer.newLine();
            }

            DecimalFormat totalFormat = new DecimalFormat("$###,###,###.00");
            extendedTotalFinal = totalFormat.format(extendedTotalCalc);
            netTotalFinal = totalFormat.format(netTotalCalc);
            transTotalFinal = totalFormat.format(transTotalCalc);

            String totalLine = String.format("%-15s%-15s%-17s%-15s", extendedTotalFinal, netTotalFinal, transTotalFinal);
            writer.write(totalLine);
            writer.newLine();
            writer.newLine();

            discountCount = discountCounter + noDiscountCounter;
            if (discountCount != 0) {
                discountCountCalc = (double) discountCounter / discountCount * 100;
            }

            DecimalFormat noDiscountFormat = new DecimalFormat("##0.0");
            noDiscountCountFinal = noDiscountFormat.format(discountCountCalc);

            String tailingLine = tailing1 + noDiscountCountFinal + "%";
            writer.write(tailingLine);
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}