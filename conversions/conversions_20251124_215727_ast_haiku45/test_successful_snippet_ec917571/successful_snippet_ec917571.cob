      *================================================================*
       IDENTIFICATION                                  DIVISION.
      *================================================================*
       PROGRAM-ID. "Comandos basicos".
       AUTHOR. "LUCAS KURATA".
      *================================================================*
       ENVIRONMENT                                     DIVISION.
      *=================================================================
      *-----------------------------------------------------------------
       CONFIGURATION                          SECTION.
      *-----------------------------------------------------------------

       SPECIAL-NAMES.


      *-----------------------------------------------------------------
       INPUT-OUTPUT                           SECTION.
      *=================================================================

      *=================================================================
      *-----------------------------------------------------------------
       FILE-CONTROL.
      *=================================================================
       DATA                                            DIVISION.
      *=================================================================
      *-----------------------------------------------------------------
       FILE                                   SECTION.
      *-----------------------------------------------------------------
      *-----------------------------------------------------------------
       WORKING-STORAGE                     SECTION.
       77  WRK-NOMES                           PIC X(12) VALUE SPACES.



       77  WRK-TELEFONE                        PIC 9(9) VALUE 975591454.

       77  WRK-COPIA_TELEFONE                  PIC 9(9).

       77  WRK-SENHA                          PIC X(8) VALUE "27455518".

       77  WS-IMSILIN                          PIC X(18) VALUE SPACES.
       77  CH-CODIMSILIN                        PIC X(15) VALUE SPACES.

       77  CH-CCCDES                           PIC 9(04).
       77  CH-ERBDES                           PIC 9(04).

       77  WS-DATA                             PIC X(2) VALUE SPACES.
       77  WS-HORA                             PIC 9(2) VALUE ZEROS.
       77  TNI-DTAHORINI                       PIC 9(14) VALUE ZEROS.

      *-----------------------------------------------------------------
      *=================================================================
       PROCEDURE                                       DIVISION.
      *=================================================================

           MOVE 4568 TO CH-CCCDES.
           MOVE 0000 TO CH-ERBDES.

           IF CH-ERBDES = ZEROS
               DISPLAY "AQUI"
               MOVE ZEROS TO CH-ERBDES
           END-IF.
           DISPLAY "CH-ERBDES: " CH-ERBDES.


           IF  CH-CCCDES IS NUMERIC AND CH-CCCDES > ZEROS
               IF  CH-ERBDES IS NUMERIC AND CH-ERBDES > ZEROS
                   DISPLAY "CH-CCCDES: " CH-CCCDES
                   DISPLAY "CH-ERBDES: " CH-ERBDES
               END-IF
           END-IF
           MOVE "09" TO WS-DATA.
           MOVE 11 TO WS-HORA.
           MOVE 1 TO TNI-DTAHORINI.

      *     MOVE PGW-CODINS TO RD-CODIMSI.

      *     MOVE PGW-DATAHOR (1:14) TO WS-DATACONEX.
           IF WS-DATA  GREATER THAN WS-HORA
               DISPLAY "BELLZEBOSS"
           END-IF.



           STOP RUN.
