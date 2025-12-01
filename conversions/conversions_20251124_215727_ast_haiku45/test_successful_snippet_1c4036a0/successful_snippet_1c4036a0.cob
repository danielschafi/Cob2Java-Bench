      *================================================================*
       IDENTIFICATION                                  DIVISION.
      *================================================================*
       PROGRAM-ID. "Comandos aritmeticos".
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
       77  WRK-CONTADOR                    PIC 9(3) VALUE ZEROS.

       77  WRK-REPLACE                     PIC 9(2) VALUE 10.

       77  WRK-COMPUTE                     PIC 9(3) VALUE 100.

       77  WRK-RECEBE_COMPUTE              PIC 9(3) VALUE ZEROS.

       77  WRK-SALDO                       PIC 9(4) VALUE ZEROS.

       77  WRK-BOLETO                      PIC 9(4) VALUE ZEROS.

       77  WRK-RESTANTE                    PIC 9(4) VALUE ZEROS.

       77  WRK-DIVIDENDO                   PIC 9(4) VALUE 1001.

       77  WRK-RESULTADO_DIVISAO           PIC 9(4) VALUE ZEROS.

       77  WRK-RESTO_DIVISAO               PIC 9(4) VALUE ZEROS.

       77  WRK-RESULTADO_MULTIPLICACAO     PIC 9(4) VALUE ZEROS.

      *-----------------------------------------------------------------
      *=================================================================
       PROCEDURE                                       DIVISION.
      *=================================================================
           ADD 10 TO WRK-CONTADOR.
           ADD 90 TO WRK-CONTADOR.
           DISPLAY WRK-CONTADOR.

           ADD 900 TO WRK-CONTADOR
           ON SIZE ERROR
               DISPLAY 'VALOR DE 3 BYTES EXCEDIDO!'
           END-ADD.

           ADD 50 GIVING WRK-REPLACE
           DISPLAY 'GIVING ' WRK-REPLACE.

           COMPUTE WRK-COMPUTE EQUAL WRK-COMPUTE * 2
           DISPLAY WRK-COMPUTE.

           COMPUTE WRK-RECEBE_COMPUTE EQUAL WRK-COMPUTE
           DISPLAY WRK-RECEBE_COMPUTE.

           COMPUTE WRK-COMPUTE EQUAL (WRK-COMPUTE  + 2)*10
           ON SIZE ERROR
               DISPLAY 'ERRO NA FORMULA, BYTES EXCEDIDO'
           END-COMPUTE.

           MOVE 1260 TO WRK-SALDO.
           MOVE 1000 TO WRK-BOLETO.
           SUBTRACT WRK-BOLETO FROM WRK-SALDO GIVING WRK-RESTANTE
           DISPLAY 'Saldo restante: ' WRK-RESTANTE.

           DIVIDE WRK-DIVIDENDO BY 2 GIVING WRK-RESULTADO_DIVISAO
           REMAINDER WRK-RESTO_DIVISAO
           DISPLAY 'RESULTADO DA DIVISAO ' WRK-RESULTADO_DIVISAO
           DISPLAY 'RESTO DA DIVISAO ' WRK-RESTO_DIVISAO.

           MULTIPLY 3 BY WRK-SALDO GIVING WRK-RESULTADO_MULTIPLICACAO
           DISPLAY 'RESULTADO DA MULTIPLICACAO: ' WRK-RESULTADO_MULTIPLI
      -    CACAO.

           STOP RUN.
