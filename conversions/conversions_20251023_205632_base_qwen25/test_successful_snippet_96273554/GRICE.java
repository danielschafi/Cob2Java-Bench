import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GRICE {
    private static final String[] SOURCE_FACSIMILE = {
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
        "                                        ",
        "           OPEN OUTPUT OUTPUT-FILE.     ",
        "                                        ",
        "           MOVE 1 TO SUB-X.             ",
        "                                        ",
        "       ML-2.                            ",
        "                                        ",
        "           MOVE SOURCE-FACSIMILE (SUB-X)",
        " TO OUTPUT-RECORD.                      ",
        "           WRITE OUTPUT-RECORD.         ",
        "                                        ",
        "           IF  SUB-X < 19               ",
        "                                        ",
        "               ADD 1 TO SUB-X           ",
        "                                        ",
        "               GO TO ML-2.              ",
        "                                        ",
        "           MOVE 1 TO SUB-X.             ",
        "                                        ",
        "       ML-3.                            ",
        "                                        ",
        "           MOVE SOURCE-FACSIMILE (20) TO",
        " OUTPUT-RECORD.                         ",
        "           WRITE OUTPUT-RECORD.         ",
        "                                        ",
        "           MOVE SOURCE-FACSIMILE-ONE (SU",
        "B-X) TO FILLER-DATA.                    ",
        "           MOVE FILLER-IMAGE TO OUTPUT-R",
        "ECORD.                                  ",
        "           WRITE OUTPUT-RECORD.         ",
        "                                        ",
        "           MOVE SOURCE-FACSIMILE (20) TO",
        " OUTPUT-RECORD.                         ",
        "           WRITE OUTPUT-RECORD.         ",
        "                                        ",
        "           MOVE SOURCE-FACSIMILE-TWO (SU",
        "B-X) TO FILLER-DATA.                    ",
        "           MOVE FILLER-IMAGE TO OUTPUT-R",
        "ECORD.                                  ",
        "           WRITE OUTPUT-RECORD.         ",
        "                                        ",
        "           IF  SUB-X < 68               ",
        "                                        ",
        "               ADD 1 TO SUB-X           ",
        "                                        ",
        "               GO TO ML-3.              ",
        "                                        ",
        "           MOVE 21 TO SUB-X.            ",
        "                                        ",
        "       ML-4.                            ",
        "                                        ",
        "           MOVE SOURCE-FACSIMILE (SUB-X)",
        " TO OUTPUT-RECORD.                      ",
        "           WRITE OUTPUT-RECORD.         ",
        "                                        ",
        "           IF  SUB-X < 68               ",
        "                                        ",
        "               ADD 1 TO SUB-X           ",
        "                                        ",
        "               GO TO ML-4.              ",
        "                                        ",
        "       ML-99.                           ",
        "                                        ",
        "           CLOSE OUTPUT-FILE.           ",
        "                                        ",
        "           STOP RUN.                    ",
        "                                        "
    };

    private static final String FILLER_IMAGE = "               \"                                        .                      \"";

    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("OUTPUT1"))) {
            int subX = 1;

            while (subX <= 19) {
                writer.write(SOURCE_FACSIMILE[subX - 1]);
                writer.newLine();
                subX++;
            }

            subX = 1;
            while (subX <= 68) {
                writer.write(SOURCE_FACSIMILE[19]);
                writer.newLine();

                String fillerData = SOURCE_FACSIMILE[subX + 19];
                writer.write(String.format("               \"%-40s.                      \"", fillerData));
                writer.newLine();

                writer.write(SOURCE_FACSIMILE[19]);
                writer.newLine();

                fillerData = SOURCE_FACSIMILE[subX + 87];
                writer.write(String.format("               \"%-40s.                      \"", fillerData));
                writer.newLine();

                subX++;
            }

            subX = 21;
            while (subX <= 68) {
                writer.write(SOURCE_FACSIMILE[subX - 1]);
                writer.newLine();
                subX++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}