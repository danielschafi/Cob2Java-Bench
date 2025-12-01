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
       77 WRK-NUM1                     PIC 9(2) VALUE ZEROS.
       77 WRK-NUM2                     PIC 9(2) VALUE ZEROS.
       77 WRK-NUM3                     PIC 9(2) VALUE ZEROS.
       77 WRK-TOTAL                    PIC 9(2) VALUE ZEROS.
       77 WRK-MEDIA                    PIC 9(2) VALUE ZEROS.

      *=================================================================
       PROCEDURE                                       DIVISION.
      *=================================================================
           DISPLAY 'Digite as 3 notas do aluno referido: '
           ACCEPT WRK-NUM1
           ACCEPT WRK-NUM2
           ACCEPT WRK-NUM3

           ADD WRK-NUM1 WRK-NUM2 WRK-NUM3 TO WRK-TOTAL
           DIVIDE WRK-TOTAL BY 3 GIVING WRK-TOTAL

           IF WRK-TOTAL NOT LESS 7
               DISPLAY 'APROVADO'
           END-IF

           IF WRK-TOTAL LESS 7
               DISPLAY 'REPROVADO'
           END-IF

           IF WRK-TOTAL EQUAL 10
               DISPLAY 'APROVADO COM SUCESSO'
           END-IF

           STOP RUN.
