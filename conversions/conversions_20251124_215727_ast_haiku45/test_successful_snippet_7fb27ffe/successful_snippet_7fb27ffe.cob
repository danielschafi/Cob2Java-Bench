      *================================================================*
       IDENTIFICATION                                  DIVISION.
      *================================================================*
       PROGRAM-ID. "AULA03".
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
       01 WRK-CADASTRO.
           02 WRK-NOME            PIC A(15) VALUE SPACES.
           02 WRK-IDADE           PIC 9(3)  VALUE ZEROS.
           02 WRK-CPF             PIC 9(11) VALUE ZEROS.
           02 WRK-ENDERECO        PIC A(20) VALUE SPACES.

       77 WRK-PERFORM             PIC 9(1)  VALUE ZEROS.

       77 WRK-A                   PIC 9(2) VALUE ZEROS.
       77 WRK-B                   PIC 9(2) VALUE ZEROS.
       77 WRK-C                   PIC 9(2) VALUE ZEROS.
       77 WRK-DELTA               PIC 9(3) VALUE 1.

       77 WRK-QUADRADO            PIC S9(2) VALUE 2.

       77 WRK-AREA-QUADRADO       PIC 9(3) VALUE ZEROS.

       77 WRK-RAIO                PIC 9(2) VALUE ZEROS.
      *-----------------------------------------------------------------
      *=================================================================
       PROCEDURE                                       DIVISION.
      *=================================================================
      *    MOVE 'Lucas Kurata' TO WRK-NOME
      *    MOVE 19 TO WRK-IDADE
      *    MOVE 50885168860 TO WRK-CPF
      *    MOVE ' AV SOUSA BANDEIRA' TO WRK-ENDERECO
      *    DISPLAY WRK-CADASTRO.

           ACCEPT WRK-A
           ACCEPT WRK-B
           ACCEPT WRK-C

           COMPUTE WRK-DELTA EQUAL (WRK-B * WRK-B) - (4*WRK-A*WRK-C)
           DISPLAY 'DELTA EH: ' WRK-DELTA.

           MOVE 2 TO WRK-RAIO.
           COMPUTE WRK-AREA-QUADRADO EQUAL 3.14*(WRK-RAIO * WRK-RAIO)
           DISPLAY 'AREA DO CIRCULO EH: ' WRK-AREA-QUADRADO.

           STOP RUN.
