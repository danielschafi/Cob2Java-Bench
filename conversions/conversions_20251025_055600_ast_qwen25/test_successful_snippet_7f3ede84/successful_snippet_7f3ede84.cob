      *================================================================*
       IDENTIFICATION                                  DIVISION.
      *================================================================*
       PROGRAM-ID. "010 - Media aritmetica".
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
       77 WRK-IDADE                    PIC 9(3) VALUE ZEROS.
       77 WRK-SOMA                     PIC 9(4) VALUE ZEROS.
       77 WRK-REPETICAO                PIC 9(1) VALUE ZEROS.
       77 WRK-CONTADOR                 PIC 9(2) VALUE ZEROS.
       77 WRK-MEDIA                    PIC 9(3) VALUE ZEROS.

      *=================================================================
       PROCEDURE                                       DIVISION.
      *=================================================================
           PERFORM UNTIL WRK-REPETICAO EQUAL 1
               DISPLAY 'Digite N idades, e 0 para calcular: '
               ACCEPT WRK-IDADE
               IF WRK-IDADE NOT EQUAL 0
                   ADD WRK-IDADE TO WRK-SOMA
                   ADD 1 TO WRK-CONTADOR
               END-IF

               IF WRK-IDADE EQUAL 0
                   ADD 1 TO WRK-REPETICAO
                   COMPUTE WRK-MEDIA EQUAL WRK-SOMA / WRK-CONTADOR
                   IF WRK-MEDIA GREATER 0 AND WRK-MEDIA NOT GREATER 25
                       DISPLAY 'Turma jovem!'
                   END-IF

                   IF WRK-MEDIA GREATER 25 AND WRK-MEDIA NOT GREATER 60
                       DISPLAY 'Turma adulta'
                   END-IF

                   IF WRK-MEDIA GREATER 60
                       DISPLAY 'Turma idosa'
                   END-IF
               END-IF

           END-PERFORM
           STOP RUN.
