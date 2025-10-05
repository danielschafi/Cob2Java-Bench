   IDENTIFICATION DIVISION.
   PROGRAM-ID. INVPROC.
   AUTHOR. ChatGPT.
   INSTALLATION. GNUCOBOL DEMO.
   DATE-WRITTEN. 2025-09-04.
   REMARKS.
  * A self-contained inventory management & transaction processor.
  * - All inputs via command-line: key=value pairs (no prompts).
  * - Demonstrates: command-line parsing, file I/O, validation,
  *   arithmetic, conditionals, OCCURS tables, SEARCH, STRING/UNSTRING,
  *   error handling, simple sorting, report generation.
  * - CSV files are simple (no embedded commas or quotes).
  * - Compile:  cobc -x invproc.cob
  * - Run examples:
  *   ./invproc MODE=REPORT INV=inventory.csv OUT=report.txt MAX=200 REORDER=5
  *   ./invproc MODE=PROCESS INV=inventory.csv TX=tx.csv OUT=report.txt
  *               OUTINV=updated_inventory.csv MAX=500 REORDER=5 WRITEBACK=YES

   ENVIRONMENT DIVISION.
   CONFIGURATION SECTION.
   SOURCE-COMPUTER. GNUCOBOL.
   OBJECT-COMPUTER. GNUCOBOL.

   INPUT-OUTPUT SECTION.
   FILE-CONTROL.
       SELECT INV-FILE ASSIGN TO DYNAMIC INV-PATH
           ORGANIZATION IS LINE SEQUENTIAL
           FILE STATUS IS INV-STATUS.
       SELECT TX-FILE  ASSIGN TO DYNAMIC TX-PATH
           ORGANIZATION IS LINE SEQUENTIAL
           FILE STATUS IS TX-STATUS.
       SELECT OUT-FILE ASSIGN TO DYNAMIC OUT-PATH
           ORGANIZATION IS LINE SEQUENTIAL
           FILE STATUS IS OUT-STATUS.
       SELECT OUTINV-FILE ASSIGN TO DYNAMIC OUTINV-PATH
           ORGANIZATION IS LINE SEQUENTIAL
           FILE STATUS IS OUTINV-STATUS.

   DATA DIVISION.
   FILE SECTION.
   FD  INV-FILE.
   01  INV-REC              PIC X(4096).

   FD  TX-FILE.
   01  TX-REC               PIC X(4096).

   FD  OUT-FILE.
   01  OUT-REC              PIC X(4096).

   FD  OUTINV-FILE.
   01  OUTINV-REC           PIC X(4096).

   WORKING-STORAGE SECTION.
  * --------- File status codes ----------
   01  INV-STATUS           PIC XX.
   01  TX-STATUS            PIC XX.
   01  OUT-STATUS           PIC XX.
   01  OUTINV-STATUS        PIC XX.

  * --------- Command-line parsing --------
   01  RAW-CMD              PIC X(4096) VALUE SPACES.
   01  CMD                  PIC X(4096) VALUE SPACES.
   01  CMD-LEN              PIC 9(4)    COMP.
   01  I                    PIC 9(4)    COMP.
   01  J                    PIC 9(4)    COMP.
   01  K                    PIC 9(4)    COMP.
   01  CUR-CH               PIC X.
   01  IN-QUOTE             PIC X VALUE "N".
   01  TOKEN-COUNT          PIC 9(4)    COMP VALUE 0.

  * Token table (key=value tokens split by spaces, honoring simple ")
   01  TOKENS.
       05  TOK-ENTRY OCCURS 1 TO 300 TIMES
           DEPENDING ON TOKEN-COUNT
           INDEXED BY TOK-IX.
           10 TOK-TEXT       PIC X(256).

  * Key/Value storage after splitting "KEY=VALUE"
   01  ARG-COUNT            PIC 9(4) COMP VALUE 0.
   01  ARGS.
       05 ARG-ENTRY OCCURS 1 TO 300 TIMES
          DEPENDING ON ARG-COUNT
          INDEXED BY ARG-IX.
          10 ARG-KEY        PIC X(32).
          10 ARG-VAL        PIC X(512).

  * Parsed/validated parameters
   01  PARMS.
       05 P-MODE            PIC X(16)  VALUE SPACES.
       05 P-INV-PATH        PIC X(256) VALUE SPACES.
       05 P-TX-PATH         PIC X(256) VALUE SPACES.
       05 P-OUT-PATH        PIC X(256) VALUE SPACES.
       05 P-OUTINV-PATH     PIC X(256) VALUE SPACES.
       05 P-MAX             PIC 9(6)   VALUE 0.
       05 P-REORDER         PIC 9(6)   VALUE 0.
       05 P-WRITEBACK       PIC X(3)   VALUE "NO".

  * Dynamic file ASSIGN targets
   01  INV-PATH             PIC X(256).
   01  TX-PATH              PIC X(256).
   01  OUT-PATH             PIC X(256).
   01  OUTINV-PATH          PIC X(256).

  * --------- Inventory in-memory table ----------
  * CSV Layout: SKU,Name,Qty,UnitCost,ReorderPoint
   01  MAX-SIZE             PIC 9(6) VALUE 0.
   01  INV-SIZE             PIC 9(6) VALUE 0.

   01  INV-TABLE.
       05 INV-ROW OCCURS 1 TO 5000 TIMES
           DEPENDING ON MAX-SIZE
           INDEXED BY INV-IX.
          10 SKU            PIC X(24).
          10 NAME           PIC X(60).
          10 QTY            PIC S9(9) COMP-3.
          10 UNIT-COST      PIC S9(7)V99 COMP-3.
          10 REORDER-PT     PIC 9(9) COMP-3.

  * For sorting & swapping
   01  TMP-ROW.
       05 T-SKU             PIC X(24).
       05 T-NAME            PIC X(60).
       05 T-QTY             PIC S9(9) COMP-3.
       05 T-UNIT-COST       PIC S9(7)V99 COMP-3.
       05 T-REORDER-PT      PIC 9(9) COMP-3.

  * --------- Transaction parsing ----------
  * TX CSV: TYPE,SKU,Qty,UnitPrice,Name?,UnitCost?
  * TYPE in {SALE,RESTOCK,ADJUST}
   01  TX-TYPE              PIC X(10).
   01  TX-SKU               PIC X(24).
   01  TX-QTY               PIC S9(9) COMP-3.
   01  TX-UPRICE            PIC S9(7)V99 COMP-3.
   01  TX-NAME              PIC X(60).
   01  TX-UCOST             PIC S9(7)V99 COMP-3.

  * --------- Totals & metrics ----------
   01  TOTAL-LINES          PIC 9(9)   VALUE 0.
   01  TOTAL-ERRORS         PIC 9(9)   VALUE 0.
   01  TOTAL-SALES-QTY      PIC S9(11) VALUE 0.
   01  TOTAL-RESTOCK-QTY    PIC S9(11) VALUE 0.
   01  TOTAL-ADJUST-QTY     PIC S9(11) VALUE 0.
   01  TOTAL-REVENUE        PIC S9(11)V99 COMP-3 VALUE 0.
   01  TOTAL-COGS           PIC S9(11)V99 COMP-3 VALUE 0.
   01  INVENTORY-VALUE      PIC S9(13)V99 COMP-3 VALUE 0.
   01  REORDER-COUNT        PIC 9(9) VALUE 0.

  * --------- Misc helpers ----------
   01  LINE                 PIC X(4096).
   01  F1                   PIC X(512).
   01  F2                   PIC X(512).
   01  F3                   PIC X(512).
   01  F4                   PIC X(512).
   01  F5                   PIC X(512).
   01  F6                   PIC X(512).
   01  WS-TXT               PIC X(256).

   01  NUM-OK               PIC X VALUE "Y".
   01  FOUND                PIC X VALUE "N".
   01  IDX                  PIC 9(6) COMP VALUE 0.

   01  NL                   PIC X VALUE X"0A".

   LOCAL-STORAGE SECTION.
   01  L-I                  PIC 9(6) COMP.

   PROCEDURE DIVISION.
  * ===========================================================
  * Entry: Parse command line, validate, then execute MODE.
  * ===========================================================
   MAIN.
       PERFORM INIT-CMD.
       PERFORM PARSE-CMDLINE.
       PERFORM SPLIT-KEYVAL.
       PERFORM VALIDATE-PARMS.
       IF TOTAL-ERRORS > 0
          PERFORM SHOW-USAGE
          STOP RUN
       END-IF

  * Map dynamic paths
       MOVE P-INV-PATH     TO INV-PATH
       MOVE P-TX-PATH      TO TX-PATH
       MOVE P-OUT-PATH     TO OUT-PATH
       MOVE P-OUTINV-PATH  TO OUTINV-PATH
       MOVE P-MAX          TO MAX-SIZE

  * Load inventory
       PERFORM LOAD-INVENTORY.

       IF P-MODE = "PROCESS"
          PERFORM PROCESS-TRANSACTIONS
          IF TOTAL-ERRORS = 0 AND
             (P-WRITEBACK = "YES" OR P-WRITEBACK = "Y")
             PERFORM WRITE-UPDATED-INVENTORY
          END-IF
       END-IF

  * Compute metrics and write report (console + optional file)
       PERFORM COMPUTE-INVENTORY-VALUE
       PERFORM GENERATE-REPORT

       GOBACK.

  * -----------------------------------------------------------
  * Initialize & grab command line.
  * -----------------------------------------------------------
   INIT-CMD.
       ACCEPT RAW-CMD FROM COMMAND-LINE
       MOVE FUNCTION TRIM(RAW-CMD) TO CMD
       MOVE FUNCTION LENGTH(FUNCTION TRIM(RAW-CMD))
         TO CMD-LEN
       IF CMD-LEN = 0
          DISPLAY "ERROR: No command-line arguments provided."
          ADD 1 TO TOTAL-ERRORS
       END-IF
       EXIT PARAGRAPH.

  * -----------------------------------------------------------
  * Tokenize command line into TOKENS[] by spaces, honoring
  * double quotes (") around values. Quotes are removed.
  * Example: MODE=REPORT OUT="my report.txt"
  * -----------------------------------------------------------
   PARSE-CMDLINE.
       MOVE 0 TO TOKEN-COUNT
       MOVE SPACE TO WS-TXT
       MOVE "N" TO IN-QUOTE
       MOVE 0 TO I
       PERFORM VARYING I FROM 1 BY 1 UNTIL I > CMD-LEN
          MOVE CMD(I:1) TO CUR-CH
          EVALUATE TRUE
             WHEN CUR-CH = '"'
                IF IN-QUOTE = "N"
                   MOVE "Y" TO IN-QUOTE
                ELSE
                   MOVE "N" TO IN-QUOTE
                END-IF
             WHEN CUR-CH = " " AND IN-QUOTE = "N"
                IF FUNCTION TRIM(WS-TXT) NOT = SPACES
                   ADD 1 TO TOKEN-COUNT
                   MOVE FUNCTION TRIM(WS-TXT)
                        TO TOK-TEXT (TOKEN-COUNT)
                   MOVE SPACES TO WS-TXT
                END-IF
             WHEN OTHER
                STRING WS-TXT DELIMITED BY SIZE
                       CUR-CH  DELIMITED BY SIZE
                  INTO WS-TXT
                END-STRING
          END-EVALUATE
       END-PERFORM
       IF FUNCTION TRIM(WS-TXT) NOT = SPACES
          ADD 1 TO TOKEN-COUNT
          MOVE FUNCTION TRIM(WS-TXT)
               TO TOK-TEXT (TOKEN-COUNT)
       END-IF
       EXIT PARAGRAPH.

  * -----------------------------------------------------------
  * Split each token KEY=VALUE into ARGS table.
  * Keys normalized to upper-case; surrounding quotes removed.
  * -----------------------------------------------------------
   SPLIT-KEYVAL.
       MOVE 0 TO ARG-COUNT
       IF TOKEN-COUNT = 0
          DISPLAY "ERROR: No key=value pairs detected."
          ADD 1 TO TOTAL-ERRORS
          EXIT PARAGRAPH
       END-IF
       PERFORM VARYING TOK-IX FROM 1 BY 1
               UNTIL TOK-IX > TOKEN-COUNT
          MOVE TOK-TEXT (TOK-IX) TO LINE
          UNSTRING LINE DELIMITED BY "="
             INTO F1 F2
          END-UNSTRING
          IF FUNCTION TRIM(F1) = SPACES OR
             FUNCTION TRIM(F2) = SPACES
             DISPLAY "WARNING: Ignoring token (not key=value): "
                     LINE
             MOVE SPACES TO F1 F2
          ELSE
             ADD 1 TO ARG-COUNT
             MOVE FUNCTION UPPER-CASE(
                    FUNCTION TRIM(F1)) TO ARG-KEY(ARG-COUNT)
             MOVE FUNCTION TRIM(
                    REMOVE-QUOTES(F2)) TO ARG-VAL(ARG-COUNT)
          END-IF
          MOVE SPACES TO F1 F2
       END-PERFORM
       EXIT PARAGRAPH.

  * -----------------------------------------------------------
  * Helper: remove surrounding double quotes if present.
  * -----------------------------------------------------------
   DECLARATIVES.
   END DECLARATIVES.

   LINKAGE SECTION.
   01  L-IN  PIC X(512).
   01  L-OUT PIC X(512).

   PROCEDURE DIVISION.
   REMOVE-QUOTES SECTION.
   ENTRY "REMOVE-QUOTES" USING L-IN RETURNING L-OUT.
       IF L-IN(1:1) = '"' AND
          L-IN(FUNCTION LENGTH(FUNCTION TRIM(L-IN)):1) = '"'
          COMPUTE J = FUNCTION LENGTH(FUNCTION TRIM(L-IN)) - 2
          IF J < 0 THEN
             MOVE SPACES TO L-OUT
          ELSE
             MOVE L-IN(2:J) TO L-OUT
          END-IF
       ELSE
          MOVE FUNCTION TRIM(L-IN) TO L-OUT
       END-IF
       GOBACK.

  * -----------------------------------------------------------
  * Validate parameters and set PARMS.
  * Required:
  *   MODE=REPORT|PROCESS
  *   INV=path
  *   MAX=positive
  *   REORDER=non-negative (default reorder point if CSV omits)
  * PROCESS requires: TX=path
  * Optional:
  *   OUT=path (report). If absent, prints only to console.
  *   OUTINV=path (write updated inventory snapshot)
  *   WRITEBACK=YES to allow writing OUTINV or overwrite INV
  * -----------------------------------------------------------
   PROCEDURE DIVISION.
   VALIDATE-PARMS.
       PERFORM GET-ARG USING "MODE"      GIVING P-MODE
       PERFORM GET-ARG USING "INV"       GIVING P-INV-PATH
       PERFORM GET-ARG USING "TX"        GIVING P-TX-PATH
       PERFORM GET-ARG USING "OUT"       GIVING P-OUT-PATH
       PERFORM GET-ARG USING "OUTINV"    GIVING P-OUTINV-PATH
       PERFORM GET-ARG USING "MAX"       GIVING WS-TXT
          IF FUNCTION TRIM(WS-TXT) NOT = SPACES
             IF IS-NUMERIC(WS-TXT) = "Y"
                MOVE FUNCTION NUMVAL(WS-TXT) TO P-MAX
             ELSE
                DISPLAY "ERROR: MAX must be a positive integer."
                ADD 1 TO TOTAL-ERRORS
             END-IF
          ELSE
             DISPLAY "ERROR: Missing MAX (table capacity)."
             ADD 1 TO TOTAL-ERRORS
          END-IF
       PERFORM GET-ARG USING "REORDER"   GIVING WS-TXT
          IF FUNCTION TRIM(WS-TXT) NOT = SPACES
             IF IS-NUMERIC(WS-TXT) = "Y"
                MOVE FUNCTION NUMVAL(WS-TXT) TO P-REORDER
             ELSE
                DISPLAY "ERROR: REORDER must be integer >= 0."
                ADD 1 TO TOTAL-ERRORS
             END-IF
          ELSE
             DISPLAY "ERROR: Missing REORDER (default reorder point)."
             ADD 1 TO TOTAL-ERRORS
          END-IF
       PERFORM GET-ARG USING "WRITEBACK" GIVING P-WRITEBACK
          MOVE FUNCTION UPPER-CASE(P-WRITEBACK) TO P-WRITEBACK

  * Validate mode and dependencies
       MOVE FUNCTION UPPER-CASE(P-MODE) TO P-MODE
       IF P-MODE NOT = "REPORT" AND P-MODE NOT = "PROCESS"
          DISPLAY "ERROR: MODE must be REPORT or PROCESS."
          ADD 1 TO TOTAL-ERRORS
       END-IF
       IF FUNCTION TRIM(P-INV-PATH) = SPACES
          DISPLAY "ERROR: INV=<path> is required."
          ADD 1 TO TOTAL-ERRORS
       END-IF
       IF P-MODE = "PROCESS" AND FUNCTION TRIM(P-TX-PATH) = SPACES
          DISPLAY "ERROR: TX=<path> is required for MODE=PROCESS."
          ADD 1 TO TOTAL-ERRORS
       END-IF

  * Sanity checks
       IF P-MAX <= 0
          DISPLAY "ERROR: MAX must be > 0."
          ADD 1 TO TOTAL-ERRORS
       END-IF
       IF P-REORDER < 0
          DISPLAY "ERROR: REORDER must be >= 0."
          ADD 1 TO TOTAL-ERRORS
       END-IF
       EXIT PARAGRAPH.

  * -----------------------------------------------------------
  * Retrieve a value from ARGS for a given key (upper-cased).
  * Returns spaces if not found.
  * -----------------------------------------------------------
   GET-ARG.
   PROCEDURE DIVISION USING BY CONTENT WS-TXT
                     RETURNING WS-TXT.
       MOVE SPACES TO WS-TXT
       PERFORM VARYING ARG-IX FROM 1 BY 1 UNTIL ARG-IX > ARG-COUNT
          IF ARG-KEY(ARG-IX) = FUNCTION UPPER-CASE(WS-TXT)
             MOVE ARG-VAL(ARG-IX) TO WS-TXT
             EXIT PERFORM
          END-IF
       END-PERFORM
       GOBACK.

  * -----------------------------------------------------------
  * Show usage help (on validation failure).
  * -----------------------------------------------------------
   SHOW-USAGE.
       DISPLAY "USAGE:"
       DISPLAY "  MODE=REPORT|PROCESS INV=<inventory.csv> MAX=<n> REORDER=<n>"
       DISPLAY "  [OUT=<report.txt>] [OUTINV=<updated.csv>] [WRITEBACK=YES]"
       DISPLAY "  (PROCESS requires TX=<transactions.csv>)"
       DISPLAY "CSV formats:"
       DISPLAY "  Inventory: SKU,Name,Qty,UnitCost,ReorderPoint"
       DISPLAY "  Transactions: TYPE,SKU,Qty,UnitPrice,Name?,UnitCost?"
       DISPLAY "  TYPE one of SALE,RESTOCK,ADJUST"
       EXIT PARAGRAPH.

  * -----------------------------------------------------------
  * Load inventory CSV -> INV-TABLE (bounded by MAX-SIZE).
  * Missing ReorderPoint -> default P-REORDER
  * -----------------------------------------------------------
   LOAD-INVENTORY.
       MOVE 0 TO INV-SIZE
       OPEN INPUT INV-FILE
       IF INV-STATUS NOT = "00"
          DISPLAY "ERROR: Cannot open inventory file: " P-INV-PATH
                  " (status=" INV-STATUS ")"
          ADD 1 TO TOTAL-ERRORS
          EXIT PARAGRAPH
       END-IF
       PERFORM UNTIL 1 = 2
          READ INV-FILE
             AT END EXIT PERFORM
             NOT AT END
                MOVE INV-REC TO LINE
                PERFORM PARSE-INV-LINE
          END-READ
       END-PERFORM
       CLOSE INV-FILE
       IF INV-SIZE = 0
          DISPLAY "WARNING: Inventory is empty."
       END-IF
  * Optional: simple alpha sort by SKU (bubble for demo)
       IF INV-SIZE > 1
          PERFORM SORT-INVENTORY
       END-IF
       EXIT PARAGRAPH.

   PARSE-INV-LINE.
  * Skip blank or comment lines (starting with #)
       IF FUNCTION TRIM(LINE) = SPACES
          EXIT PARAGRAPH
       END-IF
       IF LINE(1:1) = "#"
          EXIT PARAGRAPH
       END-IF
       MOVE SPACES TO F1 F2 F3 F4 F5
       UNSTRING LINE DELIMITED BY ","
          INTO F1 F2 F3 F4 F5
       END-UNSTRING
       IF FUNCTION TRIM(F1) = SPACES
          EXIT PARAGRAPH
       END-IF
       IF INV-SIZE >= MAX-SIZE
          DISPLAY "ERROR: Inventory exceeds MAX=" MAX-SIZE
          ADD 1 TO TOTAL-ERRORS
          EXIT PARAGRAPH
       END-IF
       ADD 1 TO INV-SIZE
       MOVE FUNCTION TRIM(F1) TO SKU      (INV-SIZE)
       MOVE FUNCTION TRIM(F2) TO NAME     (INV-SIZE)
       IF IS-NUMERIC(F3) = "Y"
          MOVE FUNCTION NUMVAL(F3) TO QTY (INV-SIZE)
       ELSE
          DISPLAY "WARNING: Bad Qty for SKU=" FUNCTION TRIM(F1)
          MOVE 0 TO QTY (INV-SIZE)
       END-IF
       IF IS-DECIMAL(F4) = "Y"
          MOVE FUNCTION NUMVAL(F4) TO UNIT-COST (INV-SIZE)
       ELSE
          DISPLAY "WARNING: Bad UnitCost for SKU=" FUNCTION TRIM(F1)
          MOVE 0 TO UNIT-COST (INV-SIZE)
       END-IF
       IF IS-NUMERIC(F5) = "Y"
          MOVE FUNCTION NUMVAL(F5) TO REORDER-PT (INV-SIZE)
       ELSE
          MOVE P-REORDER TO REORDER-PT (INV-SIZE)
       END-IF
       EXIT PARAGRAPH.

   SORT-INVENTORY.
       PERFORM VARYING I FROM 1 BY 1 UNTIL I >= INV-SIZE
          PERFORM VARYING J FROM 1 BY 1 UNTIL J > INV-SIZE - I
             IF SKU(J) > SKU(J + 1)
                PERFORM SWAP-ROWS USING J
             END-IF
          END-PERFORM
       END-PERFORM
       EXIT PARAGRAPH.

   SWAP-ROWS.
   PROCEDURE DIVISION USING BY CONTENT L-I.
       MOVE SKU(L-I)         TO T-SKU
       MOVE NAME(L-I)        TO T-NAME
       MOVE QTY(L-I)         TO T-QTY
       MOVE UNIT-COST(L-I)   TO T-UNIT-COST
       MOVE REORDER-PT(L-I)  TO T-REORDER-PT

       MOVE SKU(L-I + 1)        TO SKU(L-I)
       MOVE NAME(L-I + 1)       TO NAME(L-I)
       MOVE QTY(L-I + 1)        TO QTY(L-I)
       MOVE UNIT-COST(L-I + 1)  TO UNIT-COST(L-I)
       MOVE REORDER-PT(L-I + 1) TO REORDER-PT(L-I)

       MOVE T-SKU         TO SKU(L-I + 1)
       MOVE T-NAME        TO NAME(L-I + 1)
       MOVE T-QTY         TO QTY(L-I + 1)
       MOVE T-UNIT-COST   TO UNIT-COST(L-I + 1)
       MOVE T-REORDER-PT  TO REORDER-PT(L-I + 1)
       GOBACK.

  * -----------------------------------------------------------
  * Linear search by SKU (table is sorted, but we keep it simple).
  * FOUND="Y" and IDX holds position if found.
  * -----------------------------------------------------------
   FIND-SKU.
   PROCEDURE DIVISION USING BY CONTENT TX-SKU.
       MOVE "N" TO FOUND
       MOVE 0   TO IDX
       IF INV-SIZE = 0
          GOBACK
       END-IF
       PERFORM VARYING INV-IX FROM 1 BY 1 UNTIL INV-IX > INV-SIZE
          IF SKU(INV-IX) = TX-SKU
             MOVE "Y" TO FOUND
             MOVE INV-IX TO IDX
             EXIT PERFORM
          END-IF
       END-PERFORM
       GOBACK.

  * -----------------------------------------------------------
  * Process transaction file.
  * -----------------------------------------------------------
   PROCESS-TRANSACTIONS.
       OPEN INPUT TX-FILE
       IF TX-STATUS NOT = "00"
          DISPLAY "ERROR: Cannot open TX file: " P-TX-PATH
                  " (status=" TX-STATUS ")"
          ADD 1 TO TOTAL-ERRORS
          EXIT PARAGRAPH
       END-IF
       PERFORM UNTIL 1 = 2
          READ TX-FILE
             AT END EXIT PERFORM
             NOT AT END
                MOVE TX-REC TO LINE
                PERFORM PARSE-TX-LINE
          END-READ
       END-PERFORM
       CLOSE TX-FILE
       EXIT PARAGRAPH.

   PARSE-TX-LINE.
  * Skip blank/comment
       IF FUNCTION TRIM(LINE) = SPACES
          EXIT PARAGRAPH
       END-IF
       IF LINE(1:1) = "#"
          EXIT PARAGRAPH
       END-IF

       MOVE SPACES TO F1 F2 F3 F4 F5 F6
       UNSTRING LINE DELIMITED BY ","
          INTO F1 F2 F3 F4 F5 F6
       END-UNSTRING

       MOVE FUNCTION UPPER-CASE(FUNCTION TRIM(F1)) TO TX-TYPE
       MOVE FUNCTION TRIM(F2) TO TX-SKU
       IF IS-NUMERIC(F3) = "Y"
          MOVE FUNCTION NUMVAL(F3) TO TX-QTY
       ELSE
          DISPLAY "WARNING: TX bad Qty: " LINE
          ADD 1 TO TOTAL-ERRORS
          EXIT PARAGRAPH
       END-IF
       IF IS-DECIMAL(F4) = "Y"
          MOVE FUNCTION NUMVAL(F4) TO TX-UPRICE
       ELSE
          MOVE 0 TO TX-UPRICE
       END-IF
       MOVE FUNCTION TRIM(F5) TO TX-NAME
       IF IS-DECIMAL(F6) = "Y"
          MOVE FUNCTION NUMVAL(F6) TO TX-UCOST
       ELSE
          MOVE 0 TO TX-UCOST
       END-IF

  * Validate type
       IF TX-TYPE NOT = "SALE"
       AND TX-TYPE NOT = "RESTOCK"
       AND TX-TYPE NOT = "ADJUST"
          DISPLAY "WARNING: Unknown TX TYPE: " TX-TYPE
          ADD 1 TO TOTAL-ERRORS
          EXIT PARAGRAPH
       END-IF

  * Find SKU
       PERFORM FIND-SKU USING TX-SKU
       IF FOUND = "N"
          IF TX-TYPE = "RESTOCK"
             IF INV-SIZE >= MAX-SIZE
                DISPLAY "ERROR: Cannot add new SKU, MAX reached: "
                        TX-SKU
                ADD 1 TO TOTAL-ERRORS
                EXIT PARAGRAPH
             END-IF
             ADD 1 TO INV-SIZE
             MOVE TX-SKU       TO SKU(INV-SIZE)
             IF FUNCTION TRIM(TX-NAME) = SPACES
                MOVE "UNKNOWN" TO NAME(INV-SIZE)
             ELSE
                MOVE TX-NAME   TO NAME(INV-SIZE)
             END-IF
             MOVE 0           TO QTY(INV-SIZE)
             IF TX-UCOST > 0
                MOVE TX-UCOST TO UNIT-COST(INV-SIZE)
             ELSE
                MOVE 0        TO UNIT-COST(INV-SIZE)
             END-IF
             IF P-REORDER > 0
                MOVE P-REORDER TO REORDER-PT(INV-SIZE)
             ELSE
                MOVE 0         TO REORDER-PT(INV-SIZE)
             END-IF
             MOVE INV-SIZE TO IDX
          ELSE
             DISPLAY "WARNING: Unknown SKU in TX (ignored): " TX-SKU
             ADD 1 TO TOTAL-ERRORS
             EXIT PARAGRAPH
          END-IF
       END-IF

  * Apply transaction
       EVALUATE TX-TYPE
         WHEN "SALE"
           IF TX-QTY <= 0
              DISPLAY "WARNING: SALE qty must be > 0 for " TX-SKU
              ADD 1 TO TOTAL-ERRORS
           ELSE
              IF QTY(IDX) < TX-QTY
                 DISPLAY "WARNING: SALE exceeds stock for " TX-SKU
                 ADD 1 TO TOTAL-ERRORS
              ELSE
                 SUBTRACT TX-QTY FROM QTY(IDX)
                 ADD TX-QTY TO TOTAL-SALES-QTY
                 ADD TX-UPRICE * TX-QTY TO TOTAL-REVENUE
                 ADD UNIT-COST(IDX) * TX-QTY TO TOTAL-COGS
              END-IF
           END-IF
         WHEN "RESTOCK"
           IF TX-QTY <= 0
              DISPLAY "WARNING: RESTOCK qty must be > 0 for " TX-SKU
              ADD 1 TO TOTAL-ERRORS
           ELSE
              ADD TX-QTY TO QTY(IDX)
              ADD TX-QTY TO TOTAL-RESTOCK-QTY
              IF TX-UCOST > 0
                 MOVE TX-UCOST TO UNIT-COST(IDX)
              END-IF
           END-IF
         WHEN "ADJUST"
           ADD TX-QTY TO QTY(IDX)
           ADD TX-QTY TO TOTAL-ADJUST-QTY
       END-EVALUATE

       ADD 1 TO TOTAL-LINES
       EXIT PARAGRAPH.

  * -----------------------------------------------------------
  * Compute current inventory valuation and reorder items.
  * -----------------------------------------------------------
   COMPUTE-INVENTORY-VALUE.
       MOVE 0 TO INVENTORY-VALUE
       MOVE 0 TO REORDER-COUNT
       PERFORM VARYING INV-IX FROM 1 BY 1 UNTIL INV-IX > INV-SIZE
          ADD (QTY(INV-IX) * UNIT-COST(INV-IX)) TO INVENTORY-VALUE
          IF QTY(INV-IX) <= REORDER-PT(INV-IX)
             ADD 1 TO REORDER-COUNT
          END-IF
       END-PERFORM
       EXIT PARAGRAPH.

  * -----------------------------------------------------------
  * Write updated inventory snapshot if requested.
  * Requires WRITEBACK=YES and OUTINV or (overwrites INV if OUTINV blank).
  * -----------------------------------------------------------
   WRITE-UPDATED-INVENTORY.
       IF FUNCTION TRIM(P-OUTINV-PATH) = SPACES
          MOVE P-INV-PATH TO OUTINV-PATH
       END-IF
       OPEN OUTPUT OUTINV-FILE
       IF OUTINV-STATUS NOT = "00"
          DISPLAY "ERROR: Cannot open OUTINV for write: "
                  OUTINV-PATH " (status=" OUTINV-STATUS ")"
          ADD 1 TO TOTAL-ERRORS
          EXIT PARAGRAPH
       END-IF
       PERFORM VARYING INV-IX FROM 1 BY 1 UNTIL INV-IX > INV-SIZE
          MOVE SPACES TO OUTINV-REC
          STRING SKU(INV-IX) DELIMITED BY SIZE
                 ","        DELIMITED BY SIZE
                 NAME(INV-IX) DELIMITED BY SIZE
                 "," DELIMITED BY SIZE
                 FUNCTION NUMVAL-C(QTY(INV-IX)) DELIMITED BY SIZE
                 "," DELIMITED BY SIZE
                 FUNCTION NUMVAL-C(UNIT-COST(INV-IX)) DELIMITED BY SIZE
                 "," DELIMITED BY SIZE
                 FUNCTION NUMVAL-C(REORDER-PT(INV-IX)) DELIMITED BY SIZE
             INTO OUTINV-REC
          END-STRING
          WRITE OUTINV-REC
       END-PERFORM
       CLOSE OUTINV-FILE
       DISPLAY "INFO: Updated inventory written to " OUTINV-PATH
       EXIT PARAGRAPH.

  * -----------------------------------------------------------
  * Generate report to console and optional OUT file.
  * -----------------------------------------------------------
   GENERATE-REPORT.
       PERFORM BUILD-REPORT-HEADER
       PERFORM OUTPUT-LINE USING LINE
       IF FUNCTION TRIM(P-OUT-PATH) NOT = SPACES
          OPEN OUTPUT OUT-FILE
          IF OUT-STATUS NOT = "00"
             DISPLAY "ERROR: Cannot open OUT file: " P-OUT-PATH
                     " (status=" OUT-STATUS ")"
             ADD 1 TO TOTAL-ERRORS
          ELSE
             WRITE OUT-REC FROM LINE
          END-IF
       END-IF

  * Summary metrics
       PERFORM BUILD-SUMMARY
       PERFORM OUTPUT-LINE USING LINE
       IF OUT-STATUS = "00"
          WRITE OUT-REC FROM LINE
       END-IF

  * Reorder list header
       MOVE "---- Reorder Suggestions (Qty <= ReorderPoint) ----"
         TO LINE
       PERFORM OUTPUT-LINE USING LINE
       IF OUT-STATUS = "00" WRITE OUT-REC FROM LINE END-IF

  * Each reorder item
       PERFORM VARYING INV-IX FROM 1 BY 1 UNTIL INV-IX > INV-SIZE
          IF QTY(INV-IX) <= REORDER-PT(INV-IX)
             MOVE SPACES TO LINE
             STRING "SKU=" DELIMITED BY SIZE
                    SKU(INV-IX) DELIMITED BY SIZE
                    " | Name=" DELIMITED BY SIZE
                    NAME(INV-IX) DELIMITED BY SIZE
                    " | Qty=" DELIMITED BY SIZE
                    FUNCTION NUMVAL-C(QTY(INV-IX)) DELIMITED BY SIZE
                    " | ReorderPoint=" DELIMITED BY SIZE
                    FUNCTION NUMVAL-C(REORDER-PT(INV-IX)) DELIMITED BY SIZE
                    " | UnitCost=" DELIMITED BY SIZE
                    FUNCTION NUMVAL-C(UNIT-COST(INV-IX)) DELIMITED BY SIZE
               INTO LINE
             END-STRING
             PERFORM OUTPUT-LINE USING LINE
             IF OUT-STATUS = "00" WRITE OUT-REC FROM LINE END-IF
          END-IF
       END-PERFORM

       IF OUT-STATUS = "00"
          CLOSE OUT-FILE
          DISPLAY "INFO: Report written to " P-OUT-PATH
       END-IF
       EXIT PARAGRAPH.

   BUILD-REPORT-HEADER.
       MOVE SPACES TO LINE
       STRING
         "Inventory Processor Report" DELIMITED BY SIZE NL
         "Mode: " DELIMITED BY SIZE P-MODE DELIMITED BY SIZE NL
         "Inventory File: " DELIMITED BY SIZE P-INV-PATH DELIMITED BY SIZE NL
         "Transactions File: " DELIMITED BY SIZE
             (FUNCTION TRIM(P-TX-PATH) = SPACES
               ? "(none)" : P-TX-PATH) DELIMITED BY SIZE NL
         "Max Items: " DELIMITED BY SIZE FUNCTION NUMVAL-C(P-MAX)
             DELIMITED BY SIZE NL
         "Default Reorder: " DELIMITED BY SIZE
             FUNCTION NUMVAL-C(P-REORDER) DELIMITED BY SIZE NL
         "Totals so far -> SalesQty: " DELIMITED BY SIZE
             FUNCTION NUMVAL-C(TOTAL-SALES-QTY) DELIMITED BY SIZE
             " | RestockQty: " DELIMITED BY SIZE
             FUNCTION NUMVAL-C(TOTAL-RESTOCK-QTY) DELIMITED BY SIZE
             " | AdjustQty: " DELIMITED BY SIZE
             FUNCTION NUMVAL-C(TOTAL-ADJUST-QTY) DELIMITED BY SIZE NL
         "Revenue: " DELIMITED BY SIZE FUNCTION NUMVAL-C(TOTAL-REVENUE)
             DELIMITED BY SIZE
             " | COGS: " DELIMITED BY SIZE
             FUNCTION NUMVAL-C(TOTAL-COGS) DELIMITED BY SIZE NL
         "Current Inventory Value: " DELIMITED BY SIZE
             FUNCTION NUMVAL-C(INVENTORY-VALUE) DELIMITED BY SIZE NL
         "Items at/below Reorder Point: " DELIMITED BY SIZE
             FUNCTION NUMVAL-C(REORDER-COUNT) DELIMITED BY SIZE NL
         "Errors/Warnings: " DELIMITED BY SIZE
             FUNCTION NUMVAL-C(TOTAL-ERRORS) DELIMITED BY SIZE NL
         "------------------------------------------------------" DELIMITED BY SIZE
         INTO LINE
       END-STRING
       EXIT PARAGRAPH.

   BUILD-SUMMARY.
       MOVE SPACES TO LINE
       STRING "SUMMARY: Items=" DELIMITED BY SIZE
              FUNCTION NUMVAL-C(INV-SIZE) DELIMITED BY SIZE
              " | InventoryValue=" DELIMITED BY SIZE
              FUNCTION NUMVAL-C(INVENTORY-VALUE) DELIMITED BY SIZE
              " | Revenue=" DELIMITED BY SIZE
              FUNCTION NUMVAL-C(TOTAL-REVENUE) DELIMITED BY SIZE
              " | COGS=" DELIMITED BY SIZE
              FUNCTION NUMVAL-C(TOTAL-COGS) DELIMITED BY SIZE
              " | Errors=" DELIMITED BY SIZE
              FUNCTION NUMVAL-C(TOTAL-ERRORS) DELIMITED BY SIZE
         INTO LINE
       END-STRING
       EXIT PARAGRAPH.

   OUTPUT-LINE.
   PROCEDURE DIVISION USING BY CONTENT LINE.
       PERFORM VARYING K FROM 1 BY 1 UNTIL K > FUNCTION LENGTH(LINE)
          IF LINE(K:1) = X"0A"
             CONTINUE
          END-IF
       END-PERFORM
       DISPLAY LINE
       GOBACK.

  * -----------------------------------------------------------
  * Intrinsic-ish helpers implemented in COBOL
  * -----------------------------------------------------------
   IS-NUMERIC.
   PROCEDURE DIVISION USING BY CONTENT WS-TXT RETURNING NUM-OK.
       MOVE "Y" TO NUM-OK
       IF FUNCTION TRIM(WS-TXT) = SPACES
          MOVE "N" TO NUM-OK
          GOBACK
       END-IF
       PERFORM VARYING I FROM 1 BY 1
               UNTIL I > FUNCTION LENGTH(FUNCTION TRIM(WS-TXT))
          MOVE FUNCTION TRIM(WS-TXT)(I:1) TO CUR-CH
          IF CUR-CH < "0" OR CUR-CH > "9"
             MOVE "N" TO NUM-OK
             EXIT PERFORM
          END-IF
       END-PERFORM
       GOBACK.

   IS-DECIMAL.
   PROCEDURE DIVISION USING BY CONTENT WS-TXT RETURNING NUM-OK.
       MOVE "Y" TO NUM-OK
       IF FUNCTION TRIM(WS-TXT) = SPACES
          MOVE "N" TO NUM-OK
          GOBACK
       END-IF
       MOVE 0 TO J
       PERFORM VARYING I FROM 1 BY 1
               UNTIL I > FUNCTION LENGTH(FUNCTION TRIM(WS-TXT))
          MOVE FUNCTION TRIM(WS-TXT)(I:1) TO CUR-CH
          EVALUATE TRUE
            WHEN CUR-CH = "-" OR CUR-CH = "+"
               IF I > 1
                  MOVE "N" TO NUM-OK
                  EXIT PERFORM
               END-IF
            WHEN CUR-CH = "."
               ADD 1 TO J
               IF J > 1
                  MOVE "N" TO NUM-OK
                  EXIT PERFORM
               END-IF
            WHEN CUR-CH >= "0" AND CUR-CH <= "9"
               CONTINUE
            WHEN OTHER
               MOVE "N" TO NUM-OK
               EXIT PERFORM
          END-EVALUATE
       END-PERFORM
       GOBACK.
