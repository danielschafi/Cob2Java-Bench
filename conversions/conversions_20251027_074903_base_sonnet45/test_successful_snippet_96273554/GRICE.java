import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GRICE {
    private static final char FULL_STOP = 'L';
    private static final char QUOTE = '"';
    
    private static short subX;
    private static String[] sourceFacsimileOne = new String[68];
    private static String[] sourceFacsimileTwo = new String[68];
    private static BufferedWriter outputFile;
    
    public static void main(String[] args) {
        initializeSourceFacsimile();
        
        try {
            outputFile = new BufferedWriter(new FileWriter("OUTPUT1"));
            
            subX = 1;
            
            for (subX = 1; subX <= 19; subX++) {
                writeOutputRecord(getSourceFacsimile(subX));
            }
            
            subX = 1;
            
            while (subX <= 68) {
                writeOutputRecord(getSourceFacsimile(20));
                writeOutputRecord(formatFillerImage(sourceFacsimileOne[subX - 1]));
                writeOutputRecord(getSourceFacsimile(20));
                writeOutputRecord(formatFillerImage(sourceFacsimileTwo[subX - 1]));
                subX++;
            }
            
            subX = 21;
            
            while (subX <= 68) {
                writeOutputRecord(getSourceFacsimile(subX));
                subX++;
            }
            
            outputFile.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void initializeSourceFacsimile() {
        String[] data = {
            "       IDENTIFICATION DIVISION.         ", "                                        ",
            "       PROGRAM-ID. GRICE.               ", "                                        ",
            "       ENVIRONMENT DIVISION.            ", "                                        ",
            "       CONFIGURATION SECTION.           ", "                                        ",
            "       SPECIAL-NAMES.                   ", "                                        ",
            "           SYMBOLIC CHARACTERS FULL-STOP", " IS 76.                                 ",
            "       INPUT-OUTPUT SECTION.            ", "                                        ",
            "       FILE-CONTROL.                    ", "                                        ",
            "           SELECT OUTPUT-FILE ASSIGN TO ", "OUTPUT1.                                ",
            "       DATA DIVISION.                   ", "                                        ",
            "       FILE SECTION.                    ", "                                        ",
            "       FD  OUTPUT-FILE                  ", "                                        ",
            "           RECORDING MODE F             ", "                                        ",
            "           LABEL RECORDS OMITTED.       ", "                                        ",
            "       01  OUTPUT-RECORD                ", "     PIC X(80).                         ",
            "       WORKING-STORAGE SECTION.         ", "                                        ",
            "       01  SUB-X                        ", "     PIC S9(4) COMP.                    ",
            "       01  SOURCE-FACSIMILE-AREA.       ", "                                        ",
            "           02  SOURCE-FACSIMILE-DATA.   ", "                                        ",
            "               03  FILLER               ", "     PIC X(40) VALUE                    ",
            "           02  SOURCE-FACSIMILE-TABLE RE", "DEFINES                                 ",
            "                   SOURCE-FACSIMILE-DATA", ".                                       ",
            "               03  SOURCE-FACSIMILE OCCU", "RS 68.                                  ",
            "                   04  SOURCE-FACSIMILE-", "ONE  PIC X(40).                         ",
            "                   04  SOURCE-FACSIMILE-", "TWO  PIC X(40).                         ",
            "       01  FILLER-IMAGE.                ", "                                        ",
            "           02  FILLER                   ", "     PIC X(15) VALUE SPACES.            ",
            "           02  FILLER                   ", "     PIC X     VALUE QUOTE.             ",
            "           02  FILLER-DATA              ", "     PIC X(40).                         ",
            "           02  FILLER                   ", "     PIC X     VALUE QUOTE.             ",
            "           02  FILLER                   ", "     PIC X     VALUE FULL-STOP.         ",
            "           02  FILLER                   ", "     PIC X(22) VALUE SPACES.            ",
            "       PROCEDURE DIVISION.              ", "                                        ",
            "       MAIN-LINE SECTION.               ", "                                        ",
            "       ML-1.                            ", "                                        ",
            "           OPEN OUTPUT OUTPUT-FILE.     ", "                                        ",
            "           MOVE 1 TO SUB-X.             ", "                                        ",
            "       ML-2.                            ", "                                        ",
            "           MOVE SOURCE-FACSIMILE (SUB-X)", " TO OUTPUT-RECORD.                      ",
            "           WRITE OUTPUT-RECORD.         ", "                                        ",
            "           IF  SUB-X < 19               ", "                                        ",
            "               ADD 1 TO SUB-X           ", "                                        ",
            "               GO TO ML-2.              ", "                                        ",
            "           MOVE 1 TO SUB-X.             ", "                                        ",
            "       ML-3.                            ", "                                        ",
            "           MOVE SOURCE-FACSIMILE (20) TO", " OUTPUT-RECORD.                         ",
            "           WRITE OUTPUT-RECORD.         ", "                                        ",
            "           MOVE SOURCE-FACSIMILE-ONE (SU", "B-X) TO FILLER-DATA.                    ",
            "           MOVE FILLER-IMAGE TO OUTPUT-R", "ECORD.                                  ",
            "           WRITE OUTPUT-RECORD.         ", "                                        ",
            "           MOVE SOURCE-FACSIMILE (20) TO", " OUTPUT-RECORD.                         ",
            "           WRITE OUTPUT-RECORD.         ", "                                        ",
            "           MOVE SOURCE-FACSIMILE-TWO (SU", "B-X) TO FILLER-DATA.                    ",
            "           MOVE FILLER-IMAGE TO OUTPUT-R", "ECORD.                                  ",
            "           WRITE OUTPUT-RECORD.         ", "                                        ",
            "           IF  SUB-X < 68               ", "                                        ",
            "               ADD 1 TO SUB-X           ", "                                        ",
            "               GO TO ML-3.              ", "                                        ",
            "           MOVE 21 TO SUB-X.            ", "                                        ",
            "       ML-4.                            ", "                                        ",
            "           MOVE SOURCE-FACSIMILE (SUB-X)", " TO OUTPUT-RECORD.                      ",
            "           WRITE OUTPUT-RECORD.         ", "                                        ",
            "           IF  SUB-X < 68               ", "                                        ",
            "               ADD 1 TO SUB-X           ", "                                        ",
            "               GO TO ML-4.              ", "                                        ",
            "       ML-99.                           ", "                                        ",
            "           CLOSE OUTPUT-FILE.           ", "                                        ",
            "           STOP RUN.                    ", "                                        "
        };
        
        for (int i = 0; i < 68; i++) {
            sourceFacsimileOne[i] = data[i * 2];
            sourceFacsimileTwo[i] = data[i * 2 + 1];
        }
    }
    
    private static String getSourceFacsimile(int index) {
        return sourceFacsimileOne[index - 1] + sourceFacsimileTwo[index - 1];
    }
    
    private static String formatFillerImage(String fillerData) {
        StringBuilder sb = new StringBuilder();
        sb.append("               ");
        sb.append(QUOTE);
        sb.append(fillerData);
        sb.appen