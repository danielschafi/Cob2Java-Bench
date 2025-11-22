import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TeamRecordsRB {
    private static final String LEAGUE_FILE = "league.dat";
    private static final String LEAGUE_FRMTD_FILE = "leagueRfmtd.dat";
    
    private String wsDivisionCode;
    private String wsCity;
    private String wsRecord6;
    private double wsPct;
    
    private int wsRfmtWinsNum;
    private int wsRfmtLossesNum;
    private int wsRfmtTiesNum;
    
    private int wsTotalPtsAvailable;
    private int wsTotalPtsEarned;
    private double wsWinningPct;
    
    private String wsLeagueFileStatus;
    private BufferedReader leagueFileReader;
    private BufferedWriter leagueFrmtdFileWriter;
    private String leagueRecord;
    
    public static void main(String[] args) {
        TeamRecordsRB program = new TeamRecordsRB();
        program.mainline();
    }
    
    private void mainline() {
        displayWhenCompiled();
        System.out.println("START: REFORMAT LEAGUE DATA");
        formatRecords();
        System.out.println("END: REFORMAT LEAGUE DATA");
    }
    
    private void displayWhenCompiled() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
        String compiledTime = sdf.format(new Date());
        System.out.println("PROGRAM COMPILED: " + compiledTime);
    }
    
    private void formatRecords() {
        try {
            leagueFileReader = new BufferedReader(new FileReader(LEAGUE_FILE));
            leagueFrmtdFileWriter = new BufferedWriter(new FileWriter(LEAGUE_FRMTD_FILE));
            
            readLeagueFile();
            
            while ("00".equals(wsLeagueFileStatus)) {
                processLeagueFile();
                readLeagueFile();
            }
            
            leagueFileReader.close();
            leagueFrmtdFileWriter.close();
            
        } catch (IOException e) {
            wsLeagueFileStatus = "99";
            e.printStackTrace();
        }
    }
    
    private void processLeagueFile() {
        String[] fields = leagueRecord.split(",", 3);
        
        if (fields.length >= 3) {
            wsDivisionCode = fields[0];
            wsCity = fields[1];
            wsRecord6 = fields[2].trim();
        }
        
        String[] recordParts = wsRecord6.split("-", 3);
        
        if (recordParts.length >= 3) {
            String winsStr = recordParts[0].trim();
            String lossesStr = recordParts[1].trim();
            String tiesStr = recordParts[2].trim();
            
            wsRfmtWinsNum = numval(winsStr);
            wsRfmtLossesNum = numval(lossesStr);
            wsRfmtTiesNum = numval(tiesStr);
        }
        
        wsTotalPtsAvailable = (wsRfmtLossesNum + wsRfmtWinsNum + wsRfmtTiesNum) * 2;
        wsTotalPtsEarned = (wsRfmtWinsNum * 2) + wsRfmtTiesNum;
        
        if (wsTotalPtsAvailable > 0) {
            wsWinningPct = (double) wsTotalPtsEarned / wsTotalPtsAvailable;
        } else {
            wsWinningPct = 0.0;
        }
        
        wsPct = wsWinningPct;
        
        String formattedRecord = String.format("%02d-%02d-%02d", 
            wsRfmtWinsNum, wsRfmtLossesNum, wsRfmtTiesNum);
        
        String wsLeagueRecord = String.format("%-2s%-15s%-8s%4.3f", 
            wsDivisionCode, wsCity, formattedRecord, wsPct);
        
        try {
            leagueFrmtdFileWriter.write(wsLeagueRecord);
            leagueFrmtdFileWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void readLeagueFile() {
        try {
            leagueRecord = leagueFileReader.readLine();
            if (leagueRecord == null) {
                wsLeagueFileStatus = "10";
            } else {
                wsLeagueFileStatus = "00";
            }
        } catch (IOException e) {
            wsLeagueFileStatus = "99";
            e.printStackTrace();
        }
    }
    
    private int numval(String str) {
        if (str == null || str.trim().isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(str.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}