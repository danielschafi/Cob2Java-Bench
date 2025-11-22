import java.io.*;
import java.text.DecimalFormat;

public class Lab3Program {
    public static void main(String[] args) {
        String inputFileName = "lab3.dat";
        String outputFileName = "lab3.out";
        BufferedReader inputFile = null;
        BufferedWriter outputFile = null;

        try {
            inputFile = new BufferedReader(new FileReader(inputFileName));
            outputFile = new BufferedWriter(new FileWriter(outputFileName));

            String wsMyName = "                                                                 Henry Zheng, Lab 3 ";
            String wsHeading2 = " ITEM              EXTENDED      DISCOUNT    NET PRICE     CLASS  TRANS  TRANSPORTATION";
            String wsHeading3 = "                     PRICE         AMOUNT                           %       CHARGE";
            String wsNewLine = " ";
            String wsTailing1 = " ITEMS WITH OUT A DISCOUNT = ";

            double wsDiscount = 0.05;
            int wsNoDiscount = 0;
            double wsTransA = 11.5;
            double wsTransAPercent = 0.115;
            double wsTransB = 3.5;
            double wsTransBPercent = 0.035;
            double wsTransOther = 8.5;
            double wsTransOtherPercent = 0.085;
            double wsTransChecker = 0.0;
            int wsTransAdder = 65;

            double wsExtendedPricesCalc = 0.0;
            double wsDiscountCalc = 0.0;
            double wsNetCalc = 0.0;
            double wsTransCalc = 0.0;
            double wsTransPercent = 0.0;

            double wsExtendedTotalCalc = 0.0;
            double wsNetTotalCalc = 0.0;
            double wsTransTotalCalc = 0.0;

            int wsDiscountCounter = 0;
            int wsNoDiscountCounter = 0;
            int wsDiscountCount = 0;
            double wsDiscountCountCalc = 0.0;

            DecimalFormat df = new DecimalFormat("###,###,##0.00");
            DecimalFormat dfPercent = new DecimalFormat("0.00");

            outputFile.write(wsMyName);
            outputFile.newLine();
            outputFile.newLine();
            outputFile.write(wsHeading2);
            outputFile.newLine();
            outputFile.write(wsHeading3);
            outputFile.newLine();
            outputFile.newLine();

            String line;
            while ((line = inputFile.readLine()) != null) {
                String ilItemNumber = line.substring(0, 4).trim();
                String ilProductClass = line.substring(4, 5).trim();
                String ilItemDescription = line.substring(5, 18).trim();
                int ilQuantity = Integer.parseInt(line.substring(18, 21).trim());
                double ilPricePerUnit = Double.parseDouble(line.substring(21, 27).trim());

                wsExtendedPricesCalc = ilQuantity * ilPricePerUnit;
                String wsExtendedPricesEdited = df.format(wsExtendedPricesCalc);

                if (ilProductClass.equals("A") && wsExtendedPricesCalc > 19900) {
                    wsDiscountCounter++;
                    wsDiscountCalc = wsDiscount * wsExtendedPricesCalc;
                } else if (ilProductClass.equals("D") && ilQuantity > 10) {
                    wsDiscountCounter++;
                    wsDiscountCalc = wsDiscount * wsExtendedPricesCalc;
                } else {
                    wsNoDiscountCounter++;
                    wsDiscountCalc = 0.0;
                }

                wsNetCalc = wsExtendedPricesCalc - wsDiscountCalc;
                wsExtendedTotalCalc += wsNetCalc;
                wsNetTotalCalc += wsNetCalc;

                if (ilProductClass.equals("A")) {
                    wsTransPercent = wsTransAPercent;
                } else if (ilProductClass.equals("B")) {
                    wsTransPercent = wsTransBPercent;
                } else if (ilQuantity < 144) {
                    wsTransPercent = wsTransOtherPercent;
                }

                wsTransCalc = wsExtendedPricesCalc * wsTransPercent;
                if (wsTransPercent == wsTransChecker) {
                    wsTransCalc += wsTransAdder;
                }

                wsTransTotalCalc += wsTransCalc;

                String wsDiscountEdited = df.format(wsDiscountCalc);
                String wsNetEdited = df.format(wsNetCalc);
                String wsTransChargeEdited = df.format(wsTransCalc);
                String wsTransPercentEdited = dfPercent.format(wsTransPercent * 100);

                String outputLine = String.format("%-15s%-15s%-15s%-16s%-5s%-5s%-16s", ilItemDescription, wsExtendedPricesEdited, wsDiscountEdited, wsNetEdited, ilProductClass, wsTransPercentEdited, wsTransChargeEdited);
                outputFile.write(outputLine);
                outputFile.newLine();
                outputFile.newLine();
            }

            String wsExtendedTotalFinal = df.format(wsExtendedTotalCalc);
            String wsNetTotalFinal = df.format(wsNetTotalCalc);
            String wsTransTotalFinal = df.format(wsTransTotalCalc);

            String wsTotalLine = String.format("%-15s%-16s%-17s%-14s", wsExtendedTotalFinal, wsNetTotalFinal, wsTransTotalFinal);
            outputFile.write(wsTotalLine);
            outputFile.newLine();
            outputFile.newLine();

            wsDiscountCount = wsDiscountCounter + wsNoDiscountCounter;
            if (wsDiscountCount != 0) {
                wsDiscountCountCalc = ((double) wsDiscountCounter / wsDiscountCount) * 100;
            }
            String wsNoDiscountCountFinal = dfPercent.format(wsDiscountCountCalc);

            String wsTailing1Final = wsTailing1 + wsNoDiscountCountFinal + "%";
            outputFile.write(wsTailing1Final);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputFile != null) inputFile.close();
                if (outputFile != null) outputFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}