      *================================================================*
       IDENTIFICATION                                  DIVISION.
      *================================================================*
       PROGRAM-ID. "NivelVariavel".
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
       01 CLIENTE.
           02 NOME      PIC X(13) VALUE "Lucas Kurata".
           02 RG        PIC X(10) VALUE "559810386".
           02 CPF       PIC X(12) VALUE "50885168860".
           02 ENDERECO  PIC X(18) VALUE "Av Sousa Bandeira".
           02 DATA_NASC.
               03 DIA  PIC X(3) VALUE "23/".
               03 MES  PIC X(3) VALUE "06/".
               03 ANO  PIC X(4) VALUE "2000".
               66 DIA-MES RENAMES DIA THRU MES.
               78 CONSTANTES VALUE "CONST".

       01 BOOLEANO PIC X.
           88 MASCULINO VALUE "M".
           88 FEMININO VALUE "F".

      *-----------------------------------------------------------------
      *=================================================================
       PROCEDURE                                       DIVISION.
      *=================================================================
           DISPLAY CLIENTE.

           DISPLAY DATA_NASC.

           DISPLAY DIA-MES.

           DISPLAY CONSTANTES.

      *    MOVE "ALTERA_CONST" TO CONSTANTES.  NÃO É POSSÍVEL ALTERAR O 78 PORQUE ELE É UMA CONSTANTE
           SET FEMININO TO TRUE.
           IF FEMININO
               DISPLAY BOOLEANO.

           STOP RUN.
