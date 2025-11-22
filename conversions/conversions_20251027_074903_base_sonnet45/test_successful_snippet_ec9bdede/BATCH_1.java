import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;

public class BATCH_1 {
    
    private static class VisitStruct {
        String ip1;
        String ip2;
        String ip3;
        String ip4;
        String vday;
        String vmonth;
        String vyear;
    }
    
    private static class CurrentDateData {
        String currentYear;
        String currentMonth;
        String currentDay;
        String currentHours;
        String currentMinute;
        String currentSecond;
        String currentMilliseconds;
    }
    
    private String inputRecord;
    private String outputRecord;
    private int endOfFile;
    private VisitStruct visitStruct;
    private CurrentDateData wsCurrentDateData;
    
    public BATCH_1() {
        inputRecord = "";
        outputRecord = "";
        endOfFile = 0;
        visitStruct = new VisitStruct();
        wsCurrentDateData = new CurrentDateData();
    }
    
    public static void main(String[] args) {
        BATCH_1 batch = new BATCH_1();
        batch.mainRtn();
    }
    
    private void mainRtn() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("! BATCH 1   :                                    !");
        System.out.println("! TRAITEMENT BACH STATISTIQUES VISITES           !");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        
        mainExt();
    }
    
    private void mainExt() {
        trtFonc001();
        System.exit(0);
    }
    
    private void trtFonc001() {
        getCurrentDate();
        System.out.println("PARAGRAPHE TRAITEMENT 1");
        System.out.println("CURRENT DATE " + formatCurrentDate());
        
        BufferedReader visitFileReader = null;
        BufferedWriter transactionsWriter = null;
        
        try {
            visitFileReader = new BufferedReader(new FileReader("data.txt"));
            
            inputRecord = visitFileReader.readLine();
            if (inputRecord == null) {
                endOfFile = 1;
            } else {
                if (inputRecord.length() < 50) {
                    inputRecord = String.format("%-50s", inputRecord);
                } else if (inputRecord.length() > 50) {
                    inputRecord = inputRecord.substring(0, 50);
                }
            }
            
            if (endOfFile == 1) {
                visitFileReader.close();
                return;
            }
            
            endOfFile = 0;
            transactionsWriter = new BufferedWriter(new FileWriter("transactions.txt"));
            
            while (endOfFile != 1) {
                outputRecord = inputRecord;
                transactionsWriter.write(outputRecord);
                transactionsWriter.newLine();
                
                inputRecord = visitFileReader.readLine();
                if (inputRecord == null) {
                    endOfFile = 1;
                } else {
                    if (inputRecord.length() < 50) {
                        inputRecord = String.format("%-50s", inputRecord);
                    } else if (inputRecord.length() > 50) {
                        inputRecord = inputRecord.substring(0, 50);
                    }
                }
            }
            
            transactionsWriter.close();
            visitFileReader.close();
            
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
            try {
                if (visitFileReader != null) visitFileReader.close();
                if (transactionsWriter != null) transactionsWriter.close();
            } catch (IOException ex) {
                System.err.println("Error closing files: " + ex.getMessage());
            }
        }
    }
    
    private void getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH");
        DateTimeFormatter minuteFormatter = DateTimeFormatter.ofPattern("mm");
        DateTimeFormatter secondFormatter = DateTimeFormatter.ofPattern("ss");
        DateTimeFormatter millisecondFormatter = DateTimeFormatter.ofPattern("SS");
        
        wsCurrentDateData.currentYear = now.format(yearFormatter);
        wsCurrentDateData.currentMonth = now.format(monthFormatter);
        wsCurrentDateData.currentDay = now.format(dayFormatter);
        wsCurrentDateData.currentHours = now.format(hourFormatter);
        wsCurrentDateData.currentMinute = now.format(minuteFormatter);
        wsCurrentDateData.currentSecond = now.format(secondFormatter);
        wsCurrentDateData.currentMilliseconds = now.format(millisecondFormatter);
    }
    
    private String formatCurrentDate() {
        return wsCurrentDateData.currentYear + 
               wsCurrentDateData.currentMonth + 
               wsCurrentDateData.currentDay + 
               wsCurrentDateData.currentHours + 
               wsCurrentDateData.currentMinute + 
               wsCurrentDateData.currentSecond + 
               wsCurrentDateData.currentMilliseconds;
    }
}