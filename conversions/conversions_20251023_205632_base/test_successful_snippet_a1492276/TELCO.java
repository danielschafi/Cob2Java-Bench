import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TELCO {
    private static final String IN_FILE = "expon180.1e6";
    private static final String OUT_FILE = "TELCO.TXT";
    private static final String HEADER_1 = "  Time  Rate |        Price         Btax         Dtax |        Output";
    private static final String HEADER_2 = "-------------+----------------------------------------+------------";
    private static final String DATE_FORMAT = "HHmmssddMMyy";

    private static boolean eof = false;
    private static boolean doCalc = true;
    private static String startTime;
    private static String endTime;
    private static double priceDec5;
    private static double priceTot = 0;
    private static double bTaxTot = 0;
    private static double dTaxTot = 0;
    private static double outputTot = 0;
    private static double tempPrice;
    private static double tempBTax;
    private static double tempDTax;

    public static void main(String[] args) {
        init();
        try (BufferedReader reader = new BufferedReader(new FileReader(IN_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUT_FILE))) {
            writer.write(HEADER_1);
            writer.newLine();
            writer.write(HEADER_2);
            writer.newLine();
            String line;
            while ((line = reader.readLine()) != null && !eof) {
                if (!doCalc) {
                    continue;
                }
                calcPara(line);
                writer.write(formatDetailLine());
                writer.newLine();
            }
            windUp(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void init() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter 'N' to skip calculations:");
        String input = scanner.nextLine().trim();
        doCalc = !"N".equalsIgnoreCase(input);
        startTime = new SimpleDateFormat(DATE_FORMAT).format(new Date());
    }

    private static void calcPara(String line) {
        long inRec = Long.parseLong(line.substring(0, 15).trim());
        int rate = Integer.parseInt(line.substring(15, 16).trim());
        boolean premiumRate = rate == 1 || rate == 3 || rate == 5 || rate == 7 || rate == 9;

        if (premiumRate) {
            tempPrice = Math.round(inRec * 0.00894 * 100.0) / 100.0;
            tempDTax = Math.round(tempPrice * 0.0341 * 100.0) / 100.0;
            dTaxTot += tempDTax;
        } else {
            tempPrice = Math.round(inRec * 0.00130 * 100.0) / 100.0;
            tempDTax = 0;
        }

        if (isEvenRound(tempPrice)) {
            tempPrice -= 0.01;
        }

        tempBTax = Math.round(tempPrice * 0.0675 * 100.0) / 100.0;
        outputTot += tempPrice + tempBTax + tempDTax;
        bTaxTot += tempBTax;
        priceTot += tempPrice;
    }

    private static boolean isEvenRound(double price) {
        int[] evenRounds = {5000, 25000, 45000, 65000, 85000};
        int priceInt = (int) (price * 100);
        for (int evenRound : evenRounds) {
            if (priceInt == evenRound) {
                return true;
            }
        }
        return false;
    }

    private static String formatDetailLine() {
        StringBuilder detailLine = new StringBuilder();
        detailLine.append(String.format("%05d", (int) tempPrice));
        detailLine.append("  | ");
        detailLine.append(String.format("%,10.2f", tempPrice));
        detailLine.append(" ");
        detailLine.append(String.format("%,10.2f", tempBTax));
        detailLine.append(" ");
        detailLine.append(String.format("%,10.2f", tempDTax));
        detailLine.append(" | ");
        detailLine.append(String.format("%,10.2f", tempPrice + tempBTax + tempDTax));
        return detailLine.toString();
    }

    private static void windUp(BufferedWriter writer) throws IOException {
        endTime = new SimpleDateFormat(DATE_FORMAT).format(new Date());
        writer.write(HEADER_2);
        writer.newLine();
        writer.write(String.format("%-12s%,10.2f", "   Totals:", priceTot));
        writer.newLine();
        writer.write(String.format("%-12s%,10.2f", "", bTaxTot));
        writer.newLine();
        writer.write(String.format("%-12s%,10.2f", "", dTaxTot));
        writer.newLine();
        writer.write(String.format("%-12s%,10.2f", "", outputTot));
        writer.newLine();
        writer.write(String.format("  Start-Time: %s:%s:%s.%s", startTime.substring(8, 10), startTime.substring(10, 12), startTime.substring(12, 14), startTime.substring(14, 16)));
        writer.newLine();
        writer.write(String.format("    End-Time: %s:%s:%s.%s", endTime.substring(8, 10), endTime.substring(10, 12), endTime.substring(12, 14), endTime.substring(14, 16)));
        writer.newLine();
    }
}