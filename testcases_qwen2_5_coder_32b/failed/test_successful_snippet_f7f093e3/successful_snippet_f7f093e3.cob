      ******************************************************************
      * Author:Elisabete Monteiro
      * Date:Abril/2021
      ******************************************************************
       IDENTIFICATION DIVISION.
       PROGRAM-ID. SALARIOS.

       DATA DIVISION.
       WORKING-STORAGE SECTION.

        77 TEMP                  PIC XX           VALUE " ".
        77 ANOS-CASA             PIC 99           VALUE ZEROS.
        77 SAIDA-ANOSCASA        PIC Z9.
        77 NOME-VENDEDOR         PIC A(30)        VALUE SPACES.
        77 MES-SALARIO           PIC 99           VALUE ZEROS.
        77 SAIDA-MESSALARIO      PIC Z9.
        77 SALARIO-BASE          PIC 9(4)         VALUE ZEROS.
        77 SAIDA-SALARIOBASE     PIC Z9(3).
        77 TEMP-VOLUMEVENDAS     PIC XXXXX        VALUE "     ".
        77 VOLUME-VENDAS         PIC 9(5)         VALUE ZEROS.
        77 SAIDA-VOLUMEVENDAS    PIC ZZZZ9.
        77 COMISSAO              PIC 99           VALUE ZEROS.
        77 SAIDA-COMISSAO        PIC Z9.
        77 TOTAL                 PIC 9(4)V99      VALUE ZEROS.
        77 SAIDA-TOTAL           PIC Z9(3).99.
        77 SEGURANCA-SOCIAL      PIC 9(3)V99      VALUE ZEROS.
        77 SAIDA-SSOCIAL         PIC ZZ9(2).99.
        77 IRS                   PIC 9(4)V99      VALUE ZEROS.
        77 SAIDA-IRS             PIC ZZZ9.99.
        77 TOTAL-DESCONTOS       PIC 9(4)V99      VALUE ZEROS.
        77 SAIDA-TOTALDESCONTOS  PIC Z9(3).99.
        77 SALARIO-LIQUIDO       PIC 9(4)V99      VALUE ZEROS.
        77 SAIDA-SALARIOLIQUIDO  PIC ZZ9(3).99.
        77 RESPOSTA              PIC A            VALUE SPACES.
        77 LIMPACAMPOS           PIC X(60)
           VALUE
           "                                                          ".

       SCREEN SECTION.

       01 TITULO.

        05  COL 01 LINE 02 FOREGROUND-COLOR 1 HIGHLIGHT VALUE
           '__________________________________________________________'.
        05  COL 03 LINE 04 FOREGROUND-COLOR 3 HIGHLIGHT VALUE
            "Empresa Programadores".
        05  COL 01 LINE 05 FOREGROUND-COLOR 1 HIGHLIGHT VALUE
           '__________________________________________________________'.


       01 CABECALHO.


        05  COL 03 LINE 07 FOREGROUND-COLOR 6 HIGHLIGHT VALUE
           "             Anos de Casa: ".
        05  COL 03 LINE 09 FOREGROUND-COLOR 6 HIGHLIGHT VALUE
           "            Nome Vendedor: ".
        05  COL 03 LINE 11 FOREGROUND-COLOR 6 HIGHLIGHT VALUE
           "                      Mes: ".
        05  COL 03 LINE 13 FOREGROUND-COLOR 6 HIGHLIGHT VALUE
           "         Volume de Vendas: ".
        05  COL 39 LINE 13 HIGHLIGHT VALUE "EUR".
        05  COL 03 LINE 15 FOREGROUND-COLOR 6 HIGHLIGHT VALUE
           "          Vencimento Base: ".
        05  COL 03 LINE 17 FOREGROUND-COLOR 6 HIGHLIGHT VALUE
           "           Valor Comissao: ".
        05  COL 03 LINE 19 FOREGROUND-COLOR 6 HIGHLIGHT VALUE
           "  Total (Base + Comissao): ".
        05  COL 03 LINE 21 FOREGROUND-COLOR 6 HIGHLIGHT VALUE
           " Seguranca Social (11.5%): ".
        05  COL 03 LINE 23 FOREGROUND-COLOR 6 HIGHLIGHT VALUE
           "              I.R.S (25%): ".
        05  COL 03 LINE 25 FOREGROUND-COLOR 6 HIGHLIGHT VALUE
           "Total Descontos (S.S+IRS): ".
        05  COL 03 LINE 27 FOREGROUND-COLOR 6 HIGHLIGHT VALUE
           "          Salario Liquido: ".





       PROCEDURE DIVISION.

       INICIO.
             DISPLAY " ", LINE 1, POSITION 1, ERASE EOS.
             DISPLAY TITULO.
             DISPLAY CABECALHO.

       DADOS-PRE-DEFINIDOS.

           MOVE 30000         TO VOLUME-VENDAS.
           MOVE VOLUME-VENDAS TO SAIDA-VOLUMEVENDAS.
           MOVE 2             TO MES-SALARIO.
           MOVE "Paula Silva" TO NOME-VENDEDOR.

           DISPLAY NOME-VENDEDOR                                AT 0930.
           DISPLAY SAIDA-MESSALARIO                             AT 1129.
           PERFORM CALCULAR-MES-SALARIO.
           DISPLAY SAIDA-VOLUMEVENDAS                           AT 1330.
           PERFORM LER-ANOS-CASA.

       CALCULAR-MES-SALARIO.

           MOVE MES-SALARIO TO SAIDA-MESSALARIO.
           IF (MES-SALARIO < 1 OR MES-SALARIO > 12) THEN
               PERFORM LER-MES-SALARIO
           ELSE
                   IF (MES-SALARIO = 1)  THEN
                   DISPLAY "1-JANEIRO"                           AT 1130
                   ELSE
                   IF (MES-SALARIO = 2)  THEN
                   DISPLAY "2-FEVEREIRO"                         AT 1130
                   ELSE
                   IF (MES-SALARIO = 3)  THEN
                   DISPLAY "3-MARCO"                             AT 1130
                   ELSE
                   IF (MES-SALARIO = 4)  THEN
                   DISPLAY "4-ABRIL"                             AT 1130
                   ELSE
                   IF (MES-SALARIO = 5)  THEN
                   DISPLAY "5-MAIO"                              AT 1130
                   ELSE
                   IF (MES-SALARIO = 6)  THEN
                   DISPLAY "6-JUNHO"                             AT 1130
                   ELSE
                   IF (MES-SALARIO = 7)  THEN
                   DISPLAY "7-JULHO"                             AT 1130
                   ELSE
                   IF (MES-SALARIO = 8)  THEN
                   DISPLAY "8-AGOSTO"                            AT 1130
                   ELSE
                   IF (MES-SALARIO = 9)  THEN
                   DISPLAY "9-SETEMBRO"                          AT 1130
                   ELSE
                   IF (MES-SALARIO = 10) THEN
                   DISPLAY "10-OUTUBRO"                          AT 1130
                   ELSE
                   IF (MES-SALARIO = 11) THEN
                   DISPLAY "11-NOVEMBRO"                         AT 1130
                   ELSE
                   IF (MES-SALARIO = 12) THEN
                   DISPLAY "12-DEZEMBRO"                         AT 1130
           END-IF.

       CALCULAR-SALARIO-BASE.

           MOVE 0 TO SALARIO-BASE.
                  IF (ANOS-CASA < 5) THEN
                      ADD 800 TO SALARIO-BASE
                      MOVE SALARIO-BASE TO SAIDA-SALARIOBASE
                      DISPLAY SAIDA-SALARIOBASE                 AT 1529
                  ELSE
                      IF (ANOS-CASA >= 5 AND ANOS-CASA <= 10) THEN
                      ADD 1000 TO SALARIO-BASE
                      MOVE SALARIO-BASE TO SAIDA-SALARIOBASE
                      DISPLAY SAIDA-SALARIOBASE                 AT 1530
                  ELSE
                      ADD 1200 TO SALARIO-BASE
                      MOVE SALARIO-BASE TO SAIDA-SALARIOBASE
                      DISPLAY SAIDA-SALARIOBASE                 AT 1530
           END-IF.
           DISPLAY "EUR"                                        AT 1539
           HIGHLIGHT.

       CALCULAR-COMISSAO.

           MOVE 0 TO COMISSAO.
           IF (VOLUME-VENDAS EQUAL 0) THEN
               MOVE COMISSAO TO SAIDA-COMISSAO
           ELSE
                  IF (VOLUME-VENDAS < 10000) THEN
                      MOVE 5 TO COMISSAO
                      MOVE COMISSAO TO SAIDA-COMISSAO
                  ELSE
                      IF (VOLUME-VENDAS >= 10000  AND
                          VOLUME-VENDAS <= 20000) THEN
                       MOVE 10 TO COMISSAO
                       MOVE COMISSAO TO SAIDA-COMISSAO
                      ELSE
                       MOVE 15 TO COMISSAO
                       MOVE COMISSAO TO SAIDA-COMISSAO
           END-IF.
           DISPLAY SAIDA-COMISSAO                               AT 1730.
           DISPLAY "%"                                          AT 1733
           HIGHLIGHT.

       CALCULAR-TOTAL.

           IF  (MES-SALARIO = 6 OR MES-SALARIO = 12) THEN
               COMPUTE TOTAL =
               (SALARIO-BASE * 2) + (VOLUME-VENDAS * (COMISSAO/100))
           ELSE
               COMPUTE TOTAL =
               SALARIO-BASE + (VOLUME-VENDAS * (COMISSAO/100))
           END-IF.
           MOVE TOTAL TO SAIDA-TOTAL.
           DISPLAY SAIDA-TOTAL                                  AT 1930.
           DISPLAY "EUR"                                        AT 1939
           HIGHLIGHT.

       CALCULAR-SEGURANCA-SOCIAL.

           COMPUTE SEGURANCA-SOCIAL = (TOTAL * 0.115).
           MOVE SEGURANCA-SOCIAL TO SAIDA-SSOCIAL.
           DISPLAY SAIDA-SSOCIAL                                AT 2130.
           DISPLAY "EUR"                                        AT 2139
           HIGHLIGHT.

       CALCULAR-IRS.

           COMPUTE IRS = (TOTAL * 0.25).
           MOVE IRS TO SAIDA-IRS.
           DISPLAY SAIDA-IRS                                    AT 2330.
           DISPLAY "EUR"                                        AT 2339
           HIGHLIGHT.

       CALCULAR-TOTAL-DESCONTOS.

           COMPUTE TOTAL-DESCONTOS = (SEGURANCA-SOCIAL + IRS).
           MOVE TOTAL-DESCONTOS TO SAIDA-TOTALDESCONTOS.
           DISPLAY SAIDA-TOTALDESCONTOS                         AT 2530.
           DISPLAY "EUR"                                        AT 2539
           HIGHLIGHT.

       CALCULAR-SALARIO-LIQUIDO.

           COMPUTE SALARIO-LIQUIDO = (TOTAL - TOTAL-DESCONTOS).
           MOVE SALARIO-LIQUIDO TO SAIDA-SALARIOLIQUIDO.
           DISPLAY SAIDA-SALARIOLIQUIDO                         AT 2729.
           DISPLAY "EUR"                                        AT 2739
           HIGHLIGHT.

      *------------------------------------------------------------------------
       NOVOCALCULO.

           DISPLAY "NOVO CALCULO? S/N: "                        AT 3003
           FOREGROUND-COLOR 3 HIGHLIGHT.
           ACCEPT RESPOSTA                                      AT 3022.
           DISPLAY LIMPACAMPOS                                  AT 3022
           IF (RESPOSTA = "S" OR RESPOSTA = "s") THEN
               PERFORM LIMPAR-CAMPOS
               PERFORM LER-ANOS-CASA
               PERFORM LER-NOME-VENDEDOR
               PERFORM LER-MES-SALARIO
               PERFORM LER-VOLUME-VENDAS
               PERFORM CALCULAR-COMISSAO
               PERFORM CALCULAR-SALARIO-BASE
               PERFORM CALCULAR-TOTAL
               PERFORM CALCULAR-SEGURANCA-SOCIAL
               PERFORM CALCULAR-IRS
               PERFORM CALCULAR-TOTAL-DESCONTOS
               PERFORM CALCULAR-SALARIO-LIQUIDO
               GO NOVOCALCULO
           ELSE
               IF (RESPOSTA = "N" OR RESPOSTA = "n") THEN
               DISPLAY " ", LINE 1, POSITION 1, ERASE EOS
               STOP RUN
               ELSE
                   DISPLAY "Insira S ou N." AT 3024
                   FOREGROUND-COLOR 4 HIGHLIGHT
                   GO NOVOCALCULO
           END-IF.

      *------------------------------------------------------------------------

       LER-ANOS-CASA.

           ACCEPT  TEMP      PROMPT                             AT 0730.
           MOVE TEMP TO ANOS-CASA.
           MOVE ANOS-CASA TO SAIDA-ANOSCASA.
           DISPLAY LIMPACAMPOS                                  AT 0730.
           IF (ANOS-CASA EQUALS 0) THEN
           DISPLAY "Ainda nao tem 1 ano de casa."               AT 0750
           FOREGROUND-COLOR 3
           END-IF.
           DISPLAY SAIDA-ANOSCASA                               AT 0730.

       LER-NOME-VENDEDOR.
           ACCEPT NOME-VENDEDOR                                 AT 0930.
           IF (NOME-VENDEDOR NOT ALPHABETIC) THEN
           DISPLAY "Insira apenas letras ou deixe o campo em branco."
                                                                AT 0960
               FOREGROUND-COLOR 4
           PERFORM LER-NOME-VENDEDOR
           END-IF.
           DISPLAY LIMPACAMPOS                                  AT 0960.

       LER-MES-SALARIO.

           ACCEPT  TEMP      PROMPT                             AT 1130.
           MOVE TEMP TO MES-SALARIO.
           PERFORM CALCULAR-MES-SALARIO.

       LER-VOLUME-VENDAS.

           DISPLAY "EUR"                                        AT 1339
           HIGHLIGHT.
           ACCEPT  TEMP-VOLUMEVENDAS  PROMPT                    AT 1330.
           MOVE TEMP-VOLUMEVENDAS TO VOLUME-VENDAS.
           MOVE VOLUME-VENDAS TO SAIDA-VOLUMEVENDAS.
           DISPLAY SAIDA-VOLUMEVENDAS                           AT 1330.

      *------------------------------------------------------------------------
       LIMPAR-CAMPOS.

           DISPLAY LIMPACAMPOS AT 0729.
           DISPLAY LIMPACAMPOS AT 0929.
           DISPLAY LIMPACAMPOS AT 1129.
           DISPLAY LIMPACAMPOS AT 1329.
           DISPLAY LIMPACAMPOS AT 1529.
           DISPLAY LIMPACAMPOS AT 1729.
           DISPLAY LIMPACAMPOS AT 1929.
           DISPLAY LIMPACAMPOS AT 2129.
           DISPLAY LIMPACAMPOS AT 2329.
           DISPLAY LIMPACAMPOS AT 2529.
           DISPLAY LIMPACAMPOS AT 2729.

       END PROGRAM SALARIOS.
