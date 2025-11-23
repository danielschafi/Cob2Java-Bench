import java.io.*;

public class GRICE {
    private static final String OUTPUT_FILENAME = "OUTPUT1";
    
    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_FILENAME))) {
            // Define the source facsimile data
            String[] sourceFacsimile = {
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

            // Write first 19 lines
            for (int i = 0; i < 19; i++) {
                writer.println(sourceFacsimile[i]);
            }

            // Write lines 20 and process the table entries
            int subX = 1;
            writer.println(sourceFacsimile[19]); // Line 20
            writer.println(sourceFacsimile[19]); // Line 20 again

            // Process the table entries (sourceFacsimileOne and sourceFacsimileTwo)
            StringBuilder fillerImage = new StringBuilder();
            fillerImage.append("               ");
            fillerImage.append('"');
            fillerImage.append(' ');
            fillerImage.append('"');
            fillerImage.append('.');
            fillerImage.append("               ");
            fillerImage.append('"');
            fillerImage.append(' ');
            fillerImage.append('"');
            fillerImage.append('.');
            fillerImage.append("               ");
            fillerImage.append('"');
            fillerImage.append('.');
            fillerImage.append("               ");
            fillerImage.append('"');
            fillerImage.append(' ');
            fillerImage.append('"');

            while (subX <= 68) {
                // First part: sourceFacsimileOne
                String line = sourceFacsimile[19]; // Line 20
                String fillerData = sourceFacsimile[subX + 1];
                if (fillerData.length() > 40) {
                    fillerData = fillerData.substring(0, 40);
                }
                String formattedLine = line.substring(0, 20) + fillerData + line.substring(60);
                writer.println(formattedLine);

                // Second part: sourceFacsimileTwo
                writer.println(sourceFacsimile[19]); // Line 20 again
                String secondFillerData = sourceFacsimile[subX + 1];
                if (secondFillerData.length() > 40) {
                    secondFillerData = secondFillerData.substring(0, 40);
                }
                String secondFormattedLine = line.substring(0, 20) + secondFillerData + line.substring(60);
                writer.println(secondFormattedLine);

                subX++;
                if (subX > 68) break;
            }

            // Reset subX to 21 and continue with ML-4 section
            subX = 21;
            while (subX <= 68) {
                writer.println(sourceFacsimile[subX]);
                subX++;
            }

            // Final closing statements
            writer.println("           CLOSE OUTPUT-FILE.           ");
            writer.println("                                        ");
            writer.println("           STOP RUN.                    ");
            writer.println("                                        ");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}