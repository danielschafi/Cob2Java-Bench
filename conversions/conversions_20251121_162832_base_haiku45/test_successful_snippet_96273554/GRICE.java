import java.io.*;
import java.nio.file.*;

public class GRICE {
    private static final char FULL_STOP = (char) 76;
    private static final String QUOTE = "\"";
    
    private static int SUB_X;
    
    private static String[] SOURCE_FACSIMILE_ONE = new String[68];
    private static String[] SOURCE_FACSIMILE_TWO = new String[68];
    
    static {
        String[] sourceFacsimileData = {
            "       IDENTIFICATION DIVISION.         ",
            "                                        ",
            "       PROGRAM-ID. GRICE.               ",
            "                                        ",
            "       ENVIRONMENT DIVISION.            ",
            "                                        ",
            "       CONFIGURATION SECTION.           ",
            "                                        ",
            "       SPECIAL-NAMES.                   ",
            "                                        ",
            "           SYMBOLIC CHARACTERS FULL-STOP",
            " IS 76.                                 ",
            "       INPUT-OUTPUT SECTION.            ",
            "                                        ",
            "       FILE-CONTROL.                    ",
            "                                        ",
            "           SELECT OUTPUT-FILE ASSIGN TO ",
            "OUTPUT1.                                ",
            "       DATA DIVISION.                   ",
            "                                        ",
            "       FILE SECTION.                    ",
            "                                        ",
            "       FD  OUTPUT-FILE                  ",
            "                                        ",
            "           RECORDING MODE F             ",
            "                                        ",
            "           LABEL RECORDS OMITTED.       ",
            "                                        ",
            "       01  OUTPUT-RECORD                ",
            "     PIC X(80).                         ",
            "       WORKING-STORAGE SECTION.         ",
            "                                        ",
            "       01  SUB-X                        ",
            "     PIC S9(4) COMP.                    ",
            "       01  SOURCE-FACSIMILE-AREA.       ",
            "                                        ",
            "           02  SOURCE-FACSIMILE-DATA.   ",
            "                                        ",
            "               03  FILLER               ",
            "     PIC X(40) VALUE                    ",
            "           02  SOURCE-FACSIMILE-TABLE RE",
            "DEFINES                                 ",
            "                   SOURCE-FACSIMILE-DATA",
            ".                                       ",
            "               03  SOURCE-FACSIMILE OCCU",
            "RS 68.                                  ",
            "                   04  SOURCE-FACSIMILE-",
            "ONE  PIC X(40).                         ",
            "                   04  SOURCE-FACSIMILE-",
            "TWO  PIC X(40).                         ",
            "       01  FILLER-IMAGE.                ",
            "                                        ",
            "           02  FILLER                   ",
            "     PIC X(15) VALUE SPACES.            ",
            "           02  FILLER                   ",
            "     PIC X     VALUE QUOTE.             ",
            "           02  FILLER-DATA              ",
            "     PIC X(40).                         ",
            "           02  FILLER                   ",
            "     PIC X     VALUE QUOTE.             ",
            "           02  FILLER                   ",
            "     PIC X     VALUE FULL-STOP.         ",
            "           02  FILLER                   ",
            "     PIC X(22) VALUE SPACES.            ",
            "       PROCEDURE DIVISION.              ",
            "                                        ",
            "       MAIN-LINE SECTION.               ",
            "                                        ",
            "       ML-1.                            ",
            "                                        "
        };
        
        for (int i = 0; i < 68; i++) {
            if (i < sourceFacsimileData.length) {
                SOURCE_FACSIMILE_ONE[i] = sourceFacsimileData[i * 2];
                if (i * 2 + 1 < sourceFacsimileData.length) {
                    SOURCE_FACSIMILE_TWO[i] = sourceFacsimileData[i * 2 + 1];
                } else {
                    SOURCE_FACSIMILE_TWO[i] = "                                        ";
                }
            } else {
                SOURCE_FACSIMILE_ONE[i] = "                                        ";
                SOURCE_FACSIMILE_TWO[i] = "                                        ";
            }
        }
    }
    
    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("OUTPUT1"))) {
            SUB_X = 1;
            
            while (SUB_X < 20) {
                writer.println(SOURCE_FACSIMILE_ONE[SUB_X - 1]);
                SUB_X++;
            }
            
            SUB_X = 1;
            while (SUB_X <= 68) {
                writer.println(SOURCE_FACSIMILE_ONE[19]);
                String fillerData = SOURCE_FACSIMILE_ONE[SUB_X - 1];
                String fillerImage = "               " + QUOTE + fillerData + QUOTE + FULL_STOP + "                      ";
                writer.println(fillerImage);
                writer.println(SOURCE_FACSIMILE_ONE[19]);
                fillerData = SOURCE_FACSIMILE_TWO[SUB_X - 1];
                fillerImage = "               " + QUOTE + fillerData + QUOTE + FULL_STOP + "                      ";
                writer.println(fillerImage);
                SUB_X++;
            }
            
            SUB_X = 21;
            while (SUB_X <= 68) {
                writer.println(SOURCE_FACSIMILE_ONE[SUB_X - 1]);
                SUB_X++;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}