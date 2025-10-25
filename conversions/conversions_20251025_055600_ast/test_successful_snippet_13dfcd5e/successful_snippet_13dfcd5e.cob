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

       77 WRK-TIPO                       PIC A(1) VALUE SPACES.
       77 WRK-LITROS                     PIC 9(3) VALUE ZEROS.
       77 WRK-TOTAL                      PIC 9(3)V9(2) VALUE ZEROS.
       77 WRK-ETANOL                     PIC 9(1)V9(2) VALUE 1,90.
       77 WRK-GASOLINA                   PIC 9(1)V9(2) VALUE 2,50.

      *=================================================================
       PROCEDURE                                       DIVISION.
      *=================================================================
           ACCEPT WRK-TIPO
           ACCEPT WRK-LITROS

           IF WRK-TIPO EQUAL 'E' AND WRK-LITROS NOT GREATER 20
               COMPUTE WRK-TOTAL EQUAL (WRK-LITROS * WRK-ETANOL) * 0,97
               DISPLAY 'MENOS DE 20 LITROS, DESCONTO DE 3% ' WRK-TOTAL
           END-IF

           IF WRK-TIPO EQUAL 'E' AND WRK-LITROS GREATER 20
               COMPUTE WRK-TOTAL EQUAL (WRK-LITROS * WRK-ETANOL) * 0,95
               DISPLAY 'MAIS DE 20 LITROS, DESCONTO DE 5% ' WRK-TOTAL
           END-IF

           IF WRK-TIPO EQUAL 'G' AND WRK-LITROS NOT GREATER 20
               COMPUTE WRK-TOTAL EQUAL (WRK-LITROS * WRK-GASOLINA) * 0,9
      -    6
               DISPLAY 'MENOS DE 20 LITROS, DESCONTO DE 4% ' WRK-TOTAL
           END-IF

           IF WRK-TIPO EQUAL 'G' AND WRK-LITROS GREATER 20
               COMPUTE WRK-TOTAL EQUAL (WRK-LITROS * WRK-GASOLINA) * 0,9
      -    4
               DISPLAY 'MAIS DE 20 LITROS, DESCONTO DE 6% ' WRK-TOTAL
           END-IF






           STOP RUN.
