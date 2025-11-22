import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TeamRecordsRB {
    public static void main(String[] args) {
        System.out.println("PROGRAM COMPILED: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println("START: REFORMAT LEAGUE DATA");
        formatRecords();
        System.out.println("END: REFORMAT LEAGUE DATA");
    }

    private static void formatRecords() {
        String inputFilePath = "league.dat";
        String outputFilePath = "leagueRfmtd.dat";

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputFilePath));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                processLeagueFile(line, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processLeagueFile(String leagueRecord, BufferedWriter writer) throws IOException {
        String[] parts = leagueRecord.split(",");
        if (parts.length < 3) {
            System.out.println("OVERFLOW 1");
            return;
        }

        String divisionCode = parts[0].trim();
        String city = parts[1].trim();
        String record = parts[2].trim();

        String[] recordParts = record.split("-");
        if (recordParts.length < 3) {
            System.out.println("OVERFLOW");
            return;
        }

        System.out.println("NO OVERFLOW");

        int wins = Integer.parseInt(recordParts[0].trim());
        int losses = Integer.parseInt(recordParts[1].trim());
        int ties = Integer.parseInt(recordParts[2].trim());

        int totalPtsAvailable = (losses + wins + ties) * 2;
        int totalPtsEarned = (wins * 2) + ties;
        double winningPct = (double) totalPtsEarned / totalPtsAvailable;

        String formattedRecord = String.format("%02d-%02d-%02d", wins, losses, ties);
        String outputRecord = String.format("%-2s%-15s%-6s%6.2f", divisionCode, city, formattedRecord, winningPct);

        writer.write(outputRecord);
        writer.newLine();
    }
}