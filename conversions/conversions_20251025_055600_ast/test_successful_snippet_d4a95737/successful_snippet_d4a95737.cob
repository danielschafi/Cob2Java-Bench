      *================================================================*
       IDENTIFICATION                                  DIVISION.
      *================================================================*
       PROGRAM-ID. "007-Tabuada".
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
       77 WRK-USUARIO                  PIC 9(2) VALUE ZEROS.
       77 WRK-NUM1                     PIC 9(2) VALUE ZEROS.
       77 WRK-RESULTADO                PIC 9(2) VALUE ZEROS.
       77 WRK-REPETICAO                PIC 9(1) VALUE ZEROS.
       77 WRK-NUM2                     PIC 9(2) VALUE ZEROS.
       77 WRK-INC                      PIC 9(2) VALUE ZEROS.

      *=================================================================
       PROCEDURE                                       DIVISION.
      *=================================================================
           DISPLAY 'Digite o numero desejado: '
           ACCEPT WRK-NUM1
           PERFORM UNTIL WRK-REPETICAO EQUAL 1
               IF WRK-NUM1 GREATER 0 AND WRK-NUM1 LESS 11
                   COMPUTE WRK-RESULTADO EQUAL WRK-NUM1 * WRK-NUM2
                   DISPLAY WRK-NUM1 ' * ' WRK-NUM2 ' = ' WRK-RESULTADO
                   ADD 1 TO WRK-NUM2
                   IF WRK-NUM2 EQUAL 11
                       ADD 1 TO WRK-REPETICAO
                   END-IF
                   IF WRK-NUM1 LESS 0 OR WRK-NUM1 GREATER 10
                       DISPLAY 'Erro, tente novamente!'
                       ADD 1 TO WRK-REPETICAO
                   END-IF
               END-IF




           END-PERFORM

           STOP RUN.
