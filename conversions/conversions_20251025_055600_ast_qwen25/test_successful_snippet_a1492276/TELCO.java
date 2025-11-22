import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TELCO {
    private static final String IN_FILE = "expon180.1e6";
    private static final String OUT_FILE = "TELCO.TXT";
    private static final String HEADER_1 = "  Time  Rate |        Price         Btax         Dtax |      Output";
    private static final String HEADER_2 = "-------------+----------------------------------------+------------";
    private static final String DATE_FORMAT = "yyMMddHHmmss";

    private boolean eof = false;
    private boolean doCalc = true;
    private String startTime;
    private String endTime;
    private double priceDec5;
    private double priceTot = 0;
    private double bTaxTot = 0;
    private double dTaxTot = 0;
    private double outputTot = 0;
    private double tempPrice;
    private double tempBTax;
    private double tempDTax;

    public static void main(String[] args) {
        TELCO telco = new TELCO();
        telco.init();
        telco.mainline();
        telco.windUp();
    }

    private void init() {
        try (BufferedReader reader = new BufferedReader(new FileReader(IN_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUT_FILE))) {
            writer.write(HEADER_1);
            writer.newLine();
            writer.write(HEADER_2);
            writer.newLine();

            System.out.print("Enter 'N' to skip calculations:");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            doCalc = !input.equalsIgnoreCase("N");

            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            startTime = dateFormat.format(new Date());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mainline() {
        try (BufferedReader reader = new BufferedReader(new FileReader(IN_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUT_FILE, true))) {
            String line;
            while ((line = reader.readLine()) != null && !eof) {
                if (!doCalc) continue;

                long inRec = Long.parseLong(line.trim());
                calcPara(inRec);

                String detailLine = String.format("%5d %1s  | %,12.2f %,12.2f %,12.2f | %,12.2f",
                        inRec, line.charAt(7), tempPrice, tempBTax, tempDTax, tempPrice + tempBTax + tempDTax);
                writer.write(detailLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calcPara(long inRec) {
        String rateOut;
        if (isPremiumRate(line.charAt(7))) {
            rateOut = "D";
            tempPrice = Math.round(inRec * 0.00894 * 100.0) / 100.0;
            tempDTax = Math.round(tempPrice * 0.0341 * 100.0) / 100.0;
            dTaxTot += tempDTax;
        } else {
            rateOut = "L";
            tempPrice = Math.round(inRec * 0.00130 * 100.0) / 100.0;
            tempDTax = 0;
        }

        if (isEvenRound(tempPrice)) {
            tempPrice -= 0.01;
        }

        tempBTax = Math.round(tempPrice * 0.0675 * 100.0) / 100.0;
        double outputOut = tempPrice + tempBTax + tempDTax;
        bTaxTot += tempBTax;
        priceTot += tempPrice;
        outputTot += outputOut;
    }

    private boolean isPremiumRate(char rate) {
        return rate == '1' || rate == '3' || rate == '5' || rate == '7' || rate == '9';
    }

    private boolean isEvenRound(double price) {
        int priceInt = (int) (price * 100);
        return priceInt == 5000 || priceInt == 25000 || priceInt == 45000 || priceInt == 65000 || priceInt == 85000;
    }

    private void windUp() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUT_FILE, true))) {
            writer.write(HEADER_2);
            writer.newLine();

            String detailLine = String.format("   Totals: %,12.2f %,12.2f %,12.2f | %,12.2f",
                    priceTot, bTaxTot, dTaxTot, outputTot);
            writer.write(detailLine);
            writer.newLine();

            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            endTime = dateFormat.format(new Date());

            writer.write(String.format("  Start-Time: %s:%s:%s.%s",
                    endTime.substring(2, 4), endTime.substring(4, 6), endTime.substring(6, 8), endTime.substring(8, 10)));
            writer.newLine();

            writer.write(String.format("    End-Time: %s:%s:%s.%s",
                    endTime.substring(2, 4), endTime.substring(4, 6), endTime.substring(6, 8), endTime.substring(8, 10)));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}