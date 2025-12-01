      *================================================================*
       IDENTIFICATION                                  DIVISION.
      *================================================================*
       PROGRAM-ID. "005-caixa eletronico".
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

       77 WRK-VALOR                    PIC 9(5) VALUE ZEROS.
       77 WRK-RESULTADO                PIC 9(5) VALUE ZEROS.
       77 WRK-RESTO                    PIC 9(5) VALUE ZEROS.
       77 WRK-100                      PIC 9(3) VALUE ZEROS.
       77 WRK-50                       PIC 9(3) VALUE ZEROS.
       77 WRK-10                       PIC 9(3) VALUE ZEROS.
       77 WRK-5                        PIC 9(3) VALUE ZEROS.
       77 WRK-2                        PIC 9(3) VALUE ZEROS.
       77 WRK-REPETICAO                PIC 9(1) VALUE ZEROS.

      *=================================================================
       PROCEDURE                                       DIVISION.
      *=================================================================

           PERFORM UNTIL WRK-REPETICAO EQUAL 1
               DISPLAY 'Digite um valor entre 10 e 1000: '
               ACCEPT WRK-VALOR

               IF WRK-VALOR NOT LESS 10 AND WRK-VALOR NOT GREATER 1000
                   DIVIDE WRK-VALOR BY 100 GIVING WRK-RESULTADO
                                           REMAINDER WRK-RESTO
                                          ADD WRK-RESULTADO TO WRK-100

                   IF WRK-RESTO GREATER 0
                       MOVE ZEROS TO WRK-RESULTADO
                       DIVIDE WRK-RESTO BY 50 GIVING WRK-RESULTADO
                                              REMAINDER WRK-RESTO
                                             ADD WRK-RESULTADO TO WRK-50
                   END-IF

                   IF WRK-RESTO GREATER 0
                       MOVE ZEROS TO WRK-RESULTADO
                       DIVIDE WRK-RESTO BY 10 GIVING WRK-RESULTADO
                                              REMAINDER WRK-RESTO
                                             ADD WRK-RESULTADO TO WRK-10
                   END-IF

                   IF WRK-RESTO GREATER 0
                       MOVE ZEROS TO WRK-RESULTADO
                       DIVIDE WRK-RESTO BY 5 GIVING WRK-RESULTADO
                                             REMAINDER WRK-RESTO
                                             ADD WRK-RESULTADO TO WRK-5
                   END-IF

                   IF WRK-RESTO GREATER 0
                       MOVE ZEROS TO WRK-RESULTADO
                       DIVIDE WRK-RESTO BY 2 GIVING WRK-RESULTADO
                                             REMAINDER WRK-RESTO
                                             ADD WRK-RESULTADO TO WRK-2
                   END-IF

                   DISPLAY 'NOTAS DE 100: ' WRK-100
                   DISPLAY 'NOTAS DE 50: ' WRK-50
                   DISPLAY 'NOTAS DE 10: ' WRK-10
                   DISPLAY 'NOTAS DE 5: ' WRK-5
                   DISPLAY 'NOTAS DE 2: ' WRK-2

               ADD 1 TO WRK-REPETICAO

               END-IF

               IF WRK-VALOR LESS 10 OR WRK-VALOR GREATER 1000
                   DISPLAY 'VALOR INVALIDO, DIGITE NOVAMENTE'
               END-IF

           END-PERFORM
           STOP RUN.
