import java.io.*;
import java.nio.file.*;

public class ediParser {
    private static final String INPUT_FILE = "Team3_EDI_Data.txt";
    private static final String OUTPUT_FILE = "Team3_EDI_FINAL.txt";
    
    private String ediDetails;
    private boolean endOfFile;
    private String ediSeg;
    private int stringEnd;
    private String displayEdiSeg;
    private BufferedReader incomingEdi;
    private BufferedWriter doneEdi;
    
    public static void main(String[] args) {
        ediParser parser = new ediParser();
        parser.run();
    }
    
    public void run() {
        begin0100();
    }
    
    private void begin0100() {
        try {
            incomingEdi = new BufferedReader(new FileReader(INPUT_FILE));
            doneEdi = new BufferedWriter(new FileWriter(OUTPUT_FILE));
            
            readIncomingEdi();
            
            while (!endOfFile) {
                processEdi0200();
            }
            
            stopRun0300();
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private void readIncomingEdi() throws IOException {
        String line = incomingEdi.readLine();
        if (line == null) {
            endOfFile = true;
            ediDetails = "";
        } else {
            endOfFile = false;
            ediDetails = String.format("%-70s", line);
            if (ediDetails.length() > 70) {
                ediDetails = ediDetails.substring(0, 70);
            }
        }
    }
    
    private void processEdi0200() throws IOException {
        stringEnd = 70;
        
        while (stringEnd > 0 && ediDetails.charAt(stringEnd - 1) == ' ') {
            stringEnd--;
        }
        
        if (stringEnd > 0) {
            ediSeg = ediDetails.substring(0, stringEnd);
        } else {
            ediSeg = "";
        }
        
        displayEdiSeg = String.format("%-70s", "");
        
        displayEdiSeg = String.format("%-70s", ediSeg);
        if (displayEdiSeg.length() > 70) {
            displayEdiSeg = displayEdiSeg.substring(0, 70);
        }
        
        writeDoneEdi(displayEdiSeg);
        writeDoneEdi(" ");
        
        readIncomingEdi();
    }
    
    private void writeDoneEdi(String data) throws IOException {
        doneEdi.write(data);
        doneEdi.newLine();
    }
    
    private void stopRun0300() {
        try {
            if (incomingEdi != null) {
                incomingEdi.close();
            }
            if (doneEdi != null) {
                doneEdi.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}