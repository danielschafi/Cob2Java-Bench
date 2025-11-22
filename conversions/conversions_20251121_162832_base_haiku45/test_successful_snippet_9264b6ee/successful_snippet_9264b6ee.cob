      *================================================================*
       IDENTIFICATION                                  DIVISION.
      *================================================================*
       PROGRAM-ID. "ConverteMetrosParaCentimetros".
       AUTHOR. "LUCAS KURATA".
      *================================================================*
       ENVIRONMENT                                     DIVISION.
      *=================================================================
      *-----------------------------------------------------------------
       CONFIGURATION                          SECTION.
      *-----------------------------------------------------------------

       SPECIAL-NAMES. DECIMAL-POINT IS COMMA.


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
       77 WRK-NUM                      PIC 9(4) VALUE ZEROS.
       77 WRK-REPETICAO                PIC 9(1) VALUE ZEROS.
       77 WRK-RESULTADO                PIC 9(4) VALUE ZEROS.
       77 WRK-RESTO                    PIC 9(4) VALUE ZEROS.
       77 WRK-CENTENA                  PIC 9(2) VALUE ZEROS.
       77 WRK-DEZENA                   PIC 9(2) VALUE ZEROS.
       77 WRK-UNIDADE                  PIC 9(2) VALUE ZEROS.


      *=================================================================
       PROCEDURE                                       DIVISION.
      *=================================================================
           ACCEPT WRK-NUM

           PERFORM UNTIL WRK-REPETICAO EQUAL 1
           IF WRK-NUM GREATER 0 AND WRK-NUM LESS 1000

              DIVIDE WRK-NUM BY 100 GIVING WRK-RESULTADO
                                    REMAINDER WRK-RESTO
                                    ADD WRK-RESULTADO TO WRK-CENTENA

              IF WRK-RESTO GREATER 0
                  MOVE ZEROS TO WRK-RESULTADO
                  DIVIDE WRK-RESTO BY 10 GIVING WRK-RESULTADO
                                       REMAINDER WRK-RESTO
                                       ADD WRK-RESULTADO TO WRK-DEZENA
              END-IF

              IF WRK-RESTO GREATER 0
                  MOVE ZEROS TO WRK-RESULTADO
                  DIVIDE WRK-RESTO BY 1 GIVING WRK-RESULTADO
                                      REMAINDER WRK-RESTO
                                      ADD WRK-RESULTADO TO WRK-UNIDADE

              DISPLAY 'CENTENA: ' WRK-CENTENA
              DISPLAY 'DEZENA: ' WRK-DEZENA
              DISPLAY 'UNIDADE: ' WRK-UNIDADE

              ADD 1 TO WRK-REPETICAO
              END-IF

           END-PERFORM
           STOP RUN.
