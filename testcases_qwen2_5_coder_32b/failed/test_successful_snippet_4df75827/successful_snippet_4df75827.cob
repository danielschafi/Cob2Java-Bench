      * -------------------------------------------------------------- *
       IDENTIFICATION DIVISION. 
       PROGRAM-ID. Caloric-Calculator.
       AUTHOR. Kai Lyons.
      * -------------------------------------------------------------- *
       ENVIRONMENT DIVISION. 
       INPUT-OUTPUT SECTION.
       FILE-CONTROL. 
      *    Logfile
           SELECT CaloriesLogfile ASSIGN TO "calories.log"
           ORGANIZATION IS LINE SEQUENTIAL
           ACCESS IS SEQUENTIAL.

      *    Report File
           SELECT CaloricReport ASSIGN TO "caloric-report.rpt"
           ORGANIZAtION IS LINE SEQUENTIAL.
       DATA DIVISION.
       FILE SECTION. 
       FD CaloriesLogfile.
      * Logfile write variables
       01 LOG-PrintLine            PIC X(75).
       88 LOG-EndOfFile            VALUE 'Y' FALSE 'N'.
      
      * Logfile file descriptors
       01 FD-CalorieEntry.
           02 FD-EatenAtTime.
              03 FD-Hour           PIC 99.
              03 FD-Minute         PIC 99.
           02 FD-CaloricCount      PIC 9(3).
           02 FD-EntryDescription  PIC X(50).
       FD CaloricReport.
       01 RPT-PrintLine            PIC X(75).
       WORKING-STORAGE SECTION.
      * Generic variables
       01 WS-UserAction            PIC X.
       01 WS-LineCount             PIC 99   VALUE ZERO.
       01 WS-CaloricTotal          PIC 9(5) VALUE ZERO.

      * Report variables
       01 RPT-Heading.
           02 FILLER               PIC X(20) 
              VALUE "Caloric Report For: ".
           02 RPT-NAME             PIC X(25).
       01 RPT-ValueHeader.
           02 FILLER               PIC X(7)  VALUE "TIME  |".
           02 FILLER               PIC X(12) VALUE " CALORIES  |".
           02 FILLER               PIC X(12) VALUE " DESCRIPTION".
       01 RPT-LINE.
           02 FILLER               PIC X(5)  VALUE "-----".
           02 FILLER               PIC X(3)  VALUE "-+-".
           02 FILLER               PIC X(9)  VALUE "---------".
           02 FILLER               PIC X(3)  VALUE "-+-".
           02 FILLER               PIC X(51) VALUE 
           "---------------------------------------------------".
       01 RPT-CalorieEntry.  
           02 RPT-EatenAtTime.
              03 RPT-Hour          PIC 99.
              03 FILLER            PIC X     VALUE ":".
              03 RPT-Minute        PIC 99.
           02 FILLER               PIC X(3)  VALUE " | ".
           02 RPT-CaloricCount     PIC Z(9).
           02 FILLER               PIC X(3)  VALUE " | ".
           02 RPT-EntryDescription PIC X(50).
       01 RPT-Footer.
           02 FILLER               PIC X(20)
              VALUE "Total Calories: ".
           02 RPT-CaloricTotal     PIC ZZZ,ZZZ.
      
      * Logfile variables
       01 LOG-CalorieEntry.
           02 LOG-EatenAtTime.
              03 LOG-Hour          PIC 99.
              03 LOG-Minute        PIC 99.
           02 LOG-CaloricCount     PIC 9(3).
           02 LOG-EntryDescription PIC X(50).
       PROCEDURE DIVISION.
      *    Accept User Action
           DISPLAY "[1] Create Entry".
           DISPLAY "[2] Create Report".
           ACCEPT WS-UserAction.

      *    Evaluate user action
           EVALUATE WS-UserAction
              WHEN '1'
                 PERFORM 0100-CreateEntry
              WHEN '2'
                 PERFORM 0200-CreateReport
           END-EVALUATE.
           STOP RUN.
       
      * Create logfile and add entry
       0100-CreateEntry.
           DISPLAY "Create Entry".
           OPEN EXTEND CaloriesLogfile.
              DISPLAY "What hour was this eaten? (0-59)".
              ACCEPT LOG-Hour.
              DISPLAY "What minute of that hour? (0-24)".
              ACCEPT LOG-Minute.
              DISPLAY "How many calories? (0-999)".
              ACCEPT LOG-CaloricCount.
              DISPLAY "Enter description (Up to 50 characters long)".
              ACCEPT LOG-EntryDescription.
              WRITE LOG-PrintLine FROM LOG-CalorieEntry.
           CLOSE CaloriesLogfile.

      * Create the report from the logfile
       0200-CreateReport.
           DISPLAY "Enter name: " WITH NO ADVANCING. 
           ACCEPT RPT-NAME.
           DISPLAY "Creating Report".
           OPEN INPUT CaloriesLogfile.
              OPEN OUTPUT CaloricReport.
                 PERFORM 0210-PrintHeading.
                 PERFORM 0220-PrintValueHeader.
                 READ CaloriesLogfile
                    AT END SET LOG-EndOfFile TO TRUE
                 END-READ.
                 PERFORM 0230-PrintCalorieEntry UNTIL LOG-EndOfFile.
                 WRITE RPT-PrintLine FROM RPT-Footer AFTER 
                 ADVANCING 5 LINE.
           CLOSE CaloriesLogfile,CaloricReport.

      * Create report title
       0210-PrintHeading.
           WRITE RPT-PrintLine FROM RPT-Heading.
       
      * Print header for entries in report
       0220-PrintValueHeader.
           WRITE RPT-PrintLine FROM RPT-ValueHeader AFTER 3 LINES.
           WRITE RPT-PrintLine FROM RPT-LINE AFTER 1 LINE.
      
      * Create entries in report
       0230-PrintCalorieEntry.
           MOVE FD-Hour TO RPT-Hour.
           MOVE FD-Minute TO RPT-Minute.
           MOVE FD-CaloricCount TO RPT-CaloricCount.
           COMPUTE WS-CaloricTotal = WS-CaloricTotal + FD-CaloricCount.
           MOVE WS-CaloricTotal TO RPT-CaloricTotal.
           MOVE FD-EntryDescription TO RPT-EntryDescription.
           WRITE RPT-PrintLine FROM RPT-CalorieEntry AFTER 
           ADVANCING 1 LINE.
           ADD 1 TO WS-LineCount.
           READ CaloriesLogfile
              AT END SET LOG-EndOfFile TO TRUE
           END-READ.
