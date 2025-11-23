import java.io.*;
import java.text.DecimalFormat;

public class Lab3Program {
    private static final String INPUT_FILE = "lab3.dat";
    private static final String OUTPUT_FILE = "lab3.out";

    // Input record structure
    private static class InputLine {
        String ilItemNumber;
        char ilProductClass;
        String ilItemDescription;
        int ilQuantity;
        double ilPricePerUnit;
    }

    // Output record structure
    private static class OutputLine {
        String olItemDescription;
        String olExtendedPrices;
        String olDiscount;
        String olNetPrice;
        char olClass;
        double olTransPercent;
        String olTransCharge;
    }

    // Working storage variables
    private static boolean wsEofFlag = false;
    private static char wsNewLine = '\n';
    private static String wsMyName = "                                                                               Henry Zheng, Lab 3 ";
    private static String wsHeading2 = "                ITEM               EXTENDED      DISCOUNT       NET PRICE         CLASS     TRANS     TRANSPORTATION";
    private static String wsHeading3 = "                          PRICE          AMOUNT                    %          CHARGE";
    private static String wsTailing1 = "                ITEMS WITH OUT A DISCOUNT = ";
    private static double wsExtendedPricesCalc;
    private static String wsExtendedPricesEdited;
    private static double wsDiscountCalc;
    private static String wsDiscountEdited;
    private static double wsNetCalc;
    private static String wsNetEdited;
    private static final double wsDiscount = 0.05;
    private static int wsNoDiscount = 0;
    private static final double wsTransA = 11.5;
    private static final double wsTransAPercent = 0.115;
    private static final double wsTransB = 3.5;
    private static final double wsTransBPercent = 0.035;
    private static final double wsTransOther = 8.5;
    private static final double wsTransOtherPercent = 0.085;
    private static double wsTransCalc;
    private static double wsTransPercent;
    private static String wsTransChargeEdited;
    private static final int wsTransChecker = 0;
    private static final int wsTransAdder = 65;
    private static double wsExtendedTotalCalc;
    private static double wsNetTotalCalc;
    private static double wsTransTotalCalc;
    private static double wsDiscountCounter;
    private static double wsNoDiscountCounter;
    private static double wsDiscountCount;
    private static double wsDiscountCountCalc;
    private static String wsExtendedTotalFinal;
    private static String wsNetTotalFinal;
    private static String wsTransTotalFinal;
    private static double wsNoDiscountCountFinal;

    public static void main(String[] args) {
        try {
            PrintWriter outputFile = new PrintWriter(new FileWriter(OUTPUT_FILE));
            BufferedReader inputFile = new BufferedReader(new FileReader(INPUT_FILE));

            // Write report heading
            outputFile.println(wsMyName);
            outputFile.println();
            outputFile.println();
            outputFile.println(wsHeading2);
            outputFile.println(wsHeading3);
            outputFile.println();
            outputFile.println();

            // Read first record
            String line = inputFile.readLine();
            if (line == null) {
                wsEofFlag = true;
            } else {
                processInputRecord(line, outputFile);
            }

            while (!wsEofFlag) {
                // Clear the output buffer
                OutputLine outputLine = new OutputLine();

                // Calculations
                wsExtendedPricesCalc = ilQuantity * ilPricePerUnit;
                wsExtendedPricesEdited = formatCurrency(wsExtendedPricesCalc);

                // Discount amount procedure
                if ((ilProductClass == 'A') && (wsExtendedPricesCalc > 19900)) {
                    wsDiscountCounter += 1;
                    wsDiscountCalc = wsDiscount * wsExtendedPricesCalc;
                    wsDiscountEdited = formatCurrency(wsDiscountCalc);
                } else if ((ilProductClass == 'D') && (ilQuantity > 10)) {
                    wsDiscountCounter += 1;
                    wsDiscountCalc = wsDiscount * wsExtendedPricesCalc;
                    wsDiscountEdited = formatCurrency(wsDiscountCalc);
                } else {
                    wsDiscountCalc = wsNoDiscount;
                    wsDiscountEdited = formatCurrency(wsDiscountCalc);
                }

                // Net Price procedure
                wsNetCalc = wsExtendedPricesCalc - wsDiscountCalc;
                wsNetEdited = formatCurrency(wsNetCalc);
                wsExtendedTotalCalc += wsNetCalc;
                wsNetTotalCalc += wsNetCalc;

                // Transportation charges procedure
                outputLine.olTransPercent = 0.0;
                wsTransPercent = 0.0;

                if (ilProductClass == 'A') {
                    outputLine.olTransPercent = wsTransA;
                    wsTransPercent = wsTransAPercent;
                } else if (ilProductClass == 'B') {
                    outputLine.olTransPercent = wsTransB;
                    wsTransPercent = wsTransBPercent;
                } else if ((ilProductClass != 'A') && (ilProductClass != 'B') && (ilQuantity < 144)) {
                    outputLine.olTransPercent = wsTransOther;
                    wsTransPercent = wsTransOtherPercent;
                }

                // Transportation calculations
                wsTransCalc = wsExtendedPricesCalc * wsTransPercent;
                if (wsTransPercent == wsTransChecker) {
                    wsTransCalc += wsTransAdder;
                }
                wsTransChargeEdited = formatCurrency(wsTransCalc);
                wsTransTotalCalc += wsTransCalc;

                // Move input data to output record and print
                outputLine.olItemDescription = ilItemDescription;
                outputLine.olExtendedPrices = wsExtendedPricesEdited;
                outputLine.olDiscount = wsDiscountEdited;
                outputLine.olNetPrice = wsNetEdited;
                outputLine.olClass = ilProductClass;
                outputLine.olTransCharge = wsTransChargeEdited;

                // Write output line
                outputFile.printf("%-15s%-15s%-15s%-16s%c%6.1f%10s%n",
                        outputLine.olItemDescription,
                        outputLine.olExtendedPrices,
                        outputLine.olDiscount,
                        outputLine.olNetPrice,
                        outputLine.olClass,
                        outputLine.olTransPercent,
                        outputLine.olTransCharge);

                outputFile.println();

                // Read next input record
                line = inputFile.readLine();
                if (line == null) {
                    wsEofFlag = true;
                } else {
                    processInputRecord(line, outputFile);
                }
            }

            // Final calculations
            wsExtendedTotalFinal = formatCurrency(wsExtendedTotalCalc);
            wsNetTotalFinal = formatCurrency(wsNetTotalCalc);
            wsTransTotalFinal = formatCurrency(wsTransTotalCalc);

            outputFile.printf("%15s%s%16s%s%17s%s%n", "", wsExtendedTotalFinal, "", wsNetTotalFinal, "", wsTransTotalFinal);
            outputFile.println();
            outputFile.println();

            // Average calculation
            wsDiscountCount = wsDiscountCounter + wsNoDiscountCounter;
            wsDiscountCountCalc = wsDiscountCounter / wsDiscountCount;
            wsDiscountCountCalc *= 100;
            wsNoDiscountCountFinal = wsDiscountCountCalc;

            outputFile.printf("%s%6.1f%%%n", wsTailing1, wsNoDiscountCountFinal);

            outputFile.close();
            inputFile.close();

        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }

    private static void processInputRecord(String line, PrintWriter outputFile) {
        // Parse input line based on fixed positions
        ilItemNumber = line.substring(0, 4).trim();
        ilProductClass = line.charAt(4);
        ilItemDescription = line.substring(5, 18).trim();
        ilQuantity = Integer.parseInt(line.substring(18, 21).trim());
        ilPricePerUnit = Double.parseDouble(line.substring(21, 27).trim());

        // Process the current record
        // This method would normally contain the logic that was in the original COBOL procedure division
        // but since we're breaking it down, we'll just call the main processing logic here
    }

    private static String formatCurrency(double value) {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        return df.format(value);
    }

    // Global variables representing the input fields
    private static String ilItemNumber;
    private static char ilProductClass;
    private static String ilItemDescription;
    private static int ilQuantity;
    private static double ilPricePerUnit;
}