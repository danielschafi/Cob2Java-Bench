import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        int currentYear;
        int currentMonth;
        int currentDay;
        int currentHours;
        int currentMinute;
        int currentSecond;
        int currentMilliseconds;
    }
    
    private static BufferedReader visitFileReader;
    private static BufferedWriter transactionsWriter;
    private static int endOfFile = 0;
    private static String inputRecord = "";
    private static String outputRecord = "";
    private static CurrentDateData wsCurrentDateData = new CurrentDateData();
    
    public static void main(String[] args) {
        mainRtn();
    }
    
    private static void mainRtn() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("! BATCH 1   :                                    !");
        System.out.println("! TRAITEMENT BACH STATISTIQUES VISITES           !");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        
        mainExt();
    }
    
    private static void mainExt() {
        trtFonc001();
        System.exit(0);
    }
    
    private static void trtFonc001() {
        getCurrentDate();
        System.out.println("PARAGRAPHE TRAITEMENT 1");
        System.out.println("CURRENT DATE " + formatCurrentDate(wsCurrentDateData));
        
        try {
            openInputVisitFile();
            readVisitFile();
            
            if (endOfFile == 1) {
                closeVisitFile();
            }
            
            endOfFile = 0;
            openOutputTransactions();
            
            while (endOfFile != 1) {
                outputRecord = inputRecord;
                writeTransactions(outputRecord);
                readVisitFile();
            }
            
            closeTransactions();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        wsCurrentDateData.currentYear = now.getYear();
        wsCurrentDateData.currentMonth = now.getMonthValue();
        wsCurrentDateData.currentDay = now.getDayOfMonth();
        wsCurrentDateData.currentHours = now.getHour();
        wsCurrentDateData.currentMinute = now.getMinute();
        wsCurrentDateData.currentSecond = now.getSecond();
        wsCurrentDateData.currentMilliseconds = now.getNano() / 1000000;
    }
    
    private static String formatCurrentDate(CurrentDateData data) {
        return String.format("%04d%02d%02d%02d%02d%02d%02d",
                data.currentYear,
                data.currentMonth,
                data.currentDay,
                data.currentHours,
                data.currentMinute,
                data.currentSecond,
                data.currentMilliseconds);
    }
    
    private static void openInputVisitFile() throws IOException {
        visitFileReader = new BufferedReader(new FileReader("data.txt"));
    }
    
    private static void readVisitFile() throws IOException {
        String line = visitFileReader.readLine();
        if (line == null) {
            endOfFile = 1;
        } else {
            inputRecord = line;
        }
    }
    
    private static void closeVisitFile() throws IOException {
        if (visitFileReader != null) {
            visitFileReader.close();
        }
    }
    
    private static void openOutputTransactions() throws IOException {
        transactionsWriter = new BufferedWriter(new FileWriter("transactions.txt"));
    }
    
    private static void writeTransactions(String record) throws IOException {
        transactionsWriter.write(record);
        transactionsWriter.newLine();
    }
    
    private static void closeTransactions() throws IOException {
        if (transactionsWriter != null) {
            transactionsWriter.close();
        }
    }
}