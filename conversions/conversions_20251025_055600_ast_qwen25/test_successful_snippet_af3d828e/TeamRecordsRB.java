import java.io.*;
import java.nio.file.*;
import java.text.DecimalFormat;

public class TeamRecordsRB {
    public static void main(String[] args) {
        System.out.println("PROGRAM COMPILED: " + new java.util.Date());
        System.out.println("START: REFORMAT LEAGUE DATA");
        formatRecords();
        System.out.println("END: REFORMAT LEAGUE DATA");
    }

    public static void formatRecords() {
        String inputFileName = "league.dat";
        String outputFileName = "leagueRfmtd.dat";

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputFileName));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                processLeagueFile(line, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processLeagueFile(String leagueRecord, BufferedWriter writer) {
        String[] parts = leagueRecord.split(",");
        if (parts.length < 3) return;

        String divisionCode = parts[0].trim();
        String city = parts[1].trim();
        String record = parts[2].trim();

        String[] recordParts = record.split("-");
        if (recordParts.length < 3) return;

        int wins = Integer.parseInt(recordParts[0].trim());
        int losses = Integer.parseInt(recordParts[1].trim());
        int ties = Integer.parseInt(recordParts[2].trim());

        int totalPtsAvailable = (wins + losses + ties) * 2;
        int totalPtsEarned = (wins * 2) + ties;
        double winningPct = (double) totalPtsEarned / totalPtsAvailable;

        DecimalFormat df = new DecimalFormat("0.000");
        String formattedRecord = String.format("%02d-%02d-%02d", wins, losses, ties);
        String formattedLine = String.format("%-2s%-15s%-6s%7s", divisionCode, city, formattedRecord, df.format(winningPct));

        try {
            writer.write(formattedLine);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}