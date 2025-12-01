import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BATCH_1 {
    private static String inputRecord = "";
    private static String outputRecord = "";
    private static int endOfFile = 0;
    private static String wsCurrentDateData = "";
    private static String wsCurrentYear = "";
    private static String wsCurrentMonth = "";
    private static String wsCurrentDay = "";
    private static String wsCurrentHours = "";
    private static String wsCurrentMinute = "";
    private static String wsCurrentSecond = "";
    private static String wsCurrentMilliseconds = "";
    
    private static BufferedReader visitFileReader = null;
    private static BufferedWriter transactionsWriter = null;

    public static void main(String[] args) {
        mainRtn();
    }

    private static void mainRtn() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("! BATCH 1   :                                    !");
        System.out.println("! TRAITEMENT BACH STATISTIQUES VISITES           !");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        
        mainExt();
    }

    private static void mainExt() {
        trtFonc001();
        System.exit(0);
    }

    private static void trtFonc001() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        wsCurrentDateData = now.format(formatter);
        
        System.out.println("PARAGRAPHE TRAITEMENT 1");
        System.out.println("CURRENT DATE " + wsCurrentDateData);
        
        try {
            visitFileReader = new BufferedReader(new FileReader("data.txt"));
            
            String line = visitFileReader.readLine();
            if (line == null) {
                endOfFile = 1;
            } else {
                inputRecord = line;
            }
            
            if (endOfFile == 1) {
                visitFileReader.close();
            }
            
            endOfFile = 0;
            transactionsWriter = new BufferedWriter(new FileWriter("transactions.txt"));
            
            while (endOfFile != 1) {
                outputRecord = inputRecord;
                transactionsWriter.write(outputRecord);
                transactionsWriter.newLine();
                
                line = visitFileReader.readLine();
                if (line == null) {
                    endOfFile = 1;
                } else {
                    inputRecord = line;
                }
            }
            
            transactionsWriter.close();
            visitFileReader.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}