       IDENTIFICATION DIVISION.
      *=======================*
       PROGRAM-ID.   CGPRG006.
       AUTHOR.       ANA CAROLINA GOMES DA SILVA.
       DATE-WRITTEN. 03/09/2019.
       DATE-COMPILED. 11/04/2021.
      *--------------------------------------------------------------*
      * DISCIPLINA PROGRAMACAO MAINFRAME
      *--------------------------------------------------------------*
      * OBJETIVO: RECEBER DADOS DA SYSIN(ACCEPT)
      *           CALCULAR A MEDIA ARITMETICA BIMESTRAL
      *--------------------------------------------------------------*
      *------------------> HISTORICO - MANUTENCAO <------------------*
      * VERSAO  MES/ANO  NR.DOC  IDENT.  DESCRICAO
      * ------  -------  ------  ------  -------------------------   *
      *  V01    FEV/2013 010001  SISTEMA MOSTRA SYSOUT
      *--------------------------------------------------------------*
       ENVIRONMENT DIVISION.
      *====================*
       CONFIGURATION SECTION.
      *---------------------*
       SPECIAL-NAMES.
           DECIMAL-POINT IS COMMA
           CURRENCY SIGN IS "R$ " WITH PICTURE SYMBOL "$"
           .
       INPUT-OUTPUT SECTION.
      *---------------------*
       DATA DIVISION.
      *=============*
       FILE SECTION.
      *------------*
       WORKING-STORAGE SECTION.
      *-----------------------*
       01  FILLER                 PIC X(35)        VALUE
           '**** INICIO DA WORKING-STORAGE ****'.

      *-----> VARIAVEIS AUXILIARES UTILIZADA NO PROCESSAMENTO
       01  WS-AREA-AUX.
           05  WS-FIM                 PIC X(01).
           05  WS-CTLIDO              PIC 9(02).
           05  MEDIA-SP               PIC 9(04).
           05  TOTAL-SP               PIC 9(04).
           05  QTD-CID-SP             PIC 9(04).
           05  PCT-ACID               PIC 9(04).
           05  CID-MAIOR              PIC 9(04).
           05  ACID-MAIOR-TOTAL       PIC 9(04).
           05  QTD-CID-MAIOR          PIC 9(04).
           05  CID-MENOR              PIC 9(04).
           05  PCT-MENOR              PIC 9(04).
           05  CID-MENOR-VEICS        PIC 9(04).
           05  CID-MENOR-OBITOS       PIC 9(04).
      *-----> ENTRADA - DADOS VIA SYSIN (NO JCL DE EXECUCAO)
       01  WS-REG-SYSIN.
           05 WS-CIDADE           PIC 9(05).
           05 WS-ESTADO           PIC X(02).
           05 WS-QTD-VEICULOS     PIC 9(07).
           05 WS-BAFOMETRO        PIC X(01).
           05 WS-QTD-ACIDENTES    PIC 9(04).
           05 WS-QTD-OBITOS       PIC 9(04).

       01  WS-REG-SYSOUT.
           05 CID                 PIC 99999.
           05 FILLER              PIC X(02)         VALUE SPACES.
           05 UF                  PIC XX.
           05 FILLER              PIC X(04)        VALUE SPACES.
           05 VEICS               PIC Z.ZZZ.ZZ9.
           05 FILLER              PIC X(04)        VALUE SPACES.
           05 BAFO                PIC X.
           05 FILLER              PIC X(04)        VALUE SPACES.
           05 ACIDS               PIC Z.ZZ9.
           05 FILLER              PIC X(04)        VALUE SPACES.
           05 OBITOS              PIC Z.ZZ9.
           05 FILLER              PIC X(04)        VALUE SPACES.
           05 PORC-ACIDS          PIC ZZ9,99.

       01  FILLER                 PIC X(35)        VALUE


           '****** FIM DA WORKING-STORAGE *****'.
      *
       PROCEDURE DIVISION.
      *==================*
      *--------------------------------------------------------------*
      *    PROCESSO PRINCIPAL
      *--------------------------------------------------------------*
       000-CGPRG006.

           PERFORM 010-INICIAR
           PERFORM 030-PROCESSAR UNTIL WS-FIM = 'S'
           PERFORM 040-PROCESSAR-SP
           PERFORM 045-PROCESSAR-MAIOR
           PERFORM 047-PROCESSAR-MENOR
           PERFORM 090-TERMINAR
           STOP RUN
           .
      *--------------------------------------------------------------*
      *    PROCEDIMENTOS INICIAIS
      *--------------------------------------------------------------*
       010-INICIAR.

           DISPLAY "ANA CAROLINA GOMES DA SILVA"
           DISPLAY "ATIVIDADE 6"
           DISPLAY "ESTATISTICAS - DATA DO CALCULO: 11/04/2021"

           PERFORM 025-LER-SYSIN
           .
      *--------------------------------------------------------------*
      *    LEITURA DADOS DA SYSIN
      *--------------------------------------------------------------*
       025-LER-SYSIN.

           ACCEPT WS-REG-SYSIN  FROM SYSIN

           IF WS-REG-SYSIN = ALL '9'
              MOVE   'S'     TO  WS-FIM
           ELSE
              ADD 1  TO WS-CTLIDO
           END-IF
           .
      *--------------------------------------------------------------*
      *    PROCESSAR DADOS RECEBIDOS DA SYSIN ATE FIM DOS REGISTROS
      *--------------------------------------------------------------*
       030-PROCESSAR.

           COMPUTE PCT-ACID = WS-QTD-ACIDENTES*WS-QTD-OBITOS/100.
           MOVE WS-ESTADO       TO UF.
           MOVE WS-QTD-VEICULOS TO VEICS.
           MOVE WS-BAFOMETRO    TO BAFO.
           MOVE WS-QTD-OBITOS   TO ACIDS.
           MOVE WS-QTD-OBITOS   TO OBITOS.
           MOVE PCT-ACID        TO PORC-ACIDS.

           PERFORM 025-LER-SYSIN
           .
      *--------------------------------------------------------------*
      *    DADOS DE SAO PAULO
      *--------------------------------------------------------------*
       040-PROCESSAR-SP.

           IF WS-CIDADE = "SAO PAULO" THEN
               ADD 1 TO QTD-CID-SP

           END-IF

           COMPUTE TOTAL-SP = QTD-CID-SP*WS-QTD-ACIDENTES.
           COMPUTE MEDIA-SP = TOTAL-SP/PCT-ACID.

           DISPLAY "------------------------"
           DISPLAY "Media de porcentagens de SP..............:"MEDIA-SP.
           DISPLAY "Qtde. de acidentes totais em SP.........:"TOTAL-SP.
           DISPLAY "Qtde. de cidades de SP pesquisadas......:"QTD-CID-SP.
           .
      *--------------------------------------------------------------*
      *    PROCESSAR DADOS MAIORES QUANTIDADES DE ACIDENTES
      *--------------------------------------------------------------*
       045-PROCESSAR-MAIOR.

           IF WS-QTD-ACIDENTES > 15000 OR WS-QTD-OBITOS > 5000 THEN
               ADD 1 TO CID-MAIOR
               MOVE WS-CIDADE TO CID
           END-IF

           COMPUTE ACID-MAIOR-TOTAL = CID-MAIOR/WS-QTD-ACIDENTES.
           COMPUTE QTD-CID-MAIOR = WS-QTD-ACIDENTES*CID-MAIOR.

           DISPLAY "------------------------"
           DISPLAY "Cidade com MAIOR quantidade de acidentes:" CID.
           DISPLAY "Qtde. de acidentes desta cidade..:" ACID-MAIOR-TOTAL.
           DISPLAY "Qtde. TOTAL de cidades pesquisadas..:" QTD-CID-MAIOR.
           .

      *--------------------------------------------------------------*
      *    PROCESSAR DADOS MENORES QUANTIDADES DE ACIDENTES
      *--------------------------------------------------------------*
       047-PROCESSAR-MENOR.

           IF WS-QTD-ACIDENTES < 5000 OR WS-QTD-OBITOS < 500 THEN
               ADD 1 TO CID-MENOR
               MOVE WS-CIDADE TO CID
               MOVE WS-QTD-VEICULOS TO CID-MENOR-VEICS
               MOVE WS-QTD-OBITOS TO CID-MENOR-OBITOS
           END-IF

           COMPUTE PCT-MENOR = CID-MENOR-VEICS*CID-MENOR-OBITOS/100.

           DISPLAY "------------------------"
           DISPLAY "Cidade com MENOR porcentagem de obitos..:"CID.
           DISPLAY "Porcentagem obitos/acidente desta cidade:" PCT-MENOR.
           .
      *--------------------------------------------------------------*
      *    PROCEDIMENTOS FINAIS
      *--------------------------------------------------------------*
       090-TERMINAR.

           DISPLAY ' *========================================*'
           DISPLAY ' *   TOTAIS DE CONTROLE - CGPRG006        *'
           DISPLAY ' *----------------------------------------*'
           DISPLAY ' * REGISTROS LIDOS    - SYSIN  = ' WS-CTLIDO
           DISPLAY ' *========================================*'
           DISPLAY ' *----------------------------------------*'
           DISPLAY ' *      TERMINO NORMAL DO CGPRG006        *'
           DISPLAY ' *----------------------------------------*'
           .
      *---------------> FIM DO PROGRAMA CGPRG006 <-------------------*
