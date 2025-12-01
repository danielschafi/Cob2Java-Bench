import java.io.*;
import java.nio.file.*;

public class ediParser {
    private BufferedReader incomingEdiReader;
    private BufferedWriter doneEdiWriter;
    private boolean endOfFile = false;
    private String ediDetails;
    private int stringEnd;
    private String ediSeg;
    private String displayEdiSeg;

    public static void main(String[] args) {
        ediParser parser = new ediParser();
        parser.begin();
    }

    private void begin() {
        try {
            openInputFile();
            openOutputFile();
            readIncomingEdi();

            while (!endOfFile) {
                processEdi();
            }

            stopRun();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openInputFile() throws IOException {
        incomingEdiReader = new BufferedReader(new FileReader("Team3_EDI_Data.txt"));
    }

    private void openOutputFile() throws IOException {
        doneEdiWriter = new BufferedWriter(new FileWriter("Team3_EDI_FINAL.txt"));
    }

    private void readIncomingEdi() throws IOException {
        ediDetails = incomingEdiReader.readLine();
        if (ediDetails == null) {
            endOfFile = true;
        }
    }

    private void processEdi() throws IOException {
        if (ediDetails == null) {
            endOfFile = true;
            return;
        }

        stringEnd = ediDetails.length();
        while (stringEnd > 0 && ediDetails.charAt(stringEnd - 1) == ' ') {
            stringEnd--;
        }

        if (stringEnd > 0) {
            ediSeg = ediDetails.substring(0, stringEnd);
        } else {
            ediSeg = "";
        }

        displayEdiSeg = ediSeg;

        doneEdiWriter.write(displayEdiSeg);
        doneEdiWriter.newLine();
        doneEdiWriter.write(" ");
        doneEdiWriter.newLine();

        readIncomingEdi();
    }

    private void stopRun() throws IOException {
        if (incomingEdiReader != null) {
            incomingEdiReader.close();
        }
        if (doneEdiWriter != null) {
            doneEdiWriter.close();
        }
    }
}