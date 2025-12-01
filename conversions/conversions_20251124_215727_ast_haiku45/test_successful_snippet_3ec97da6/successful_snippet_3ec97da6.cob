      ******************************************************************
      * Author:
      * Date:
      * Purpose:
      * Tectonics: cobc
      ******************************************************************
       IDENTIFICATION DIVISION.
       PROGRAM-ID. HAMMING.
       DATA DIVISION.
       FILE SECTION.
       WORKING-STORAGE SECTION.
           01 WKS-OPTION       PIC 9(1) VALUE 0.
           01 BinaryWord PIC X(7) VALUE '1101000'.
           01 HammingArray.
               02 HammingArrayBit PIC 9(1) OCCURS 11 TIMES.

           01 WrongHammingArray.
               02 WrongHammingArrayBit PIC 9(1) OCCURS 11 TIMES.

           01 BinaryWordArray.
               02 BitBinaryWord pic 9(1) OCCURS 7 times.

           01 Parity1 pic 9(1).
           01 Parity2 pic 9(1).
           01 Parity4 pic 9(1).
           01 Parity8 pic 9(1).

           01 Letra PIC X(1).

           01 ResidueParity pic 9(1).
           01 Result pic 9(10)V99.

           01 ErrorPosition pic 9(2) VALUE 0.

       PROCEDURE DIVISION.
       MAIN-PROCEDURE.
           PERFORM MAIN-MENU UNTIL WKS-OPTION = 2.
           STOP RUN.


       MAIN-MENU.
           DISPLAY "Hamming (11,7)".
           DISPLAY "1. Calcular Hamming".
           DISPLAY "2. Salir".
           DISPLAY "Elige una opcion: ".
           ACCEPT WKS-OPTION
           IF WKS-OPTION = 1
               DISPLAY "--------------------------------------------"
               DISPLAY "Introduce letra en minuscula a evaluar"
               DISPLAY "Ejemplo => 'h'"
               DISPLAY "--------------------------------------------"

               ACCEPT Letra
               PERFORM TRANS-LET-BIN

               PERFORM FILL-HAMMING

               PERFORM CALC-PARITY-BITS
               DISPLAY "-----------------------------------------"
               DISPLAY "Su hamming es: "
               DISPLAY HammingArray
               DISPLAY "-----------------------------------------"
               DISPLAY "Introduce el hamming a evaluar"
               DISPLAY "Deben ser 11 bits !!! "
               ACCEPT WrongHammingArray

               PERFORM CALC-PARITY-BITS-WRONG

               PERFORM FIND-ERROR-POSITION

           ELSE IF WKS-OPTION = 2
               DISPLAY "Adios..."
           ELSE
               DISPLAY "Opcion no valida".

       TRANS-LET-BIN.
            IF Letra='a'
                move '1100001' to BinaryWordArray
            ELSE IF Letra='b'
                move '1100010' to BinaryWordArray
            ELSE IF Letra='c'
                move '1100011' to BinaryWordArray
            ELSE IF Letra='d'
                move '1100100' to BinaryWordArray
            ELSE IF Letra='e'
                move '1100101' to BinaryWordArray
            ELSE IF Letra='f'
                move '1100110' to BinaryWordArray
            ELSE IF Letra='g'
                move '1100111' to BinaryWordArray
            ELSE IF Letra='h'
                move '1101000' to BinaryWordArray
            ELSE IF Letra='i'
                move '1101001' to BinaryWordArray
            ELSE IF Letra='j'
                move '1101010' to BinaryWordArray
            ELSE IF Letra='k'
                move '1101011' to BinaryWordArray
            ELSE IF Letra='l'
                move '1101100' to BinaryWordArray
            ELSE IF Letra='m'
                move '1101101' to BinaryWordArray
            ELSE IF Letra='n'
                move '1101110' to BinaryWordArray
            ELSE IF Letra='o'
                move '1101111' to BinaryWordArray
            ELSE IF Letra='p'
                move '1110000' to BinaryWordArray
            ELSE IF Letra='q'
                move '1110001' to BinaryWordArray
            ELSE IF Letra='r'
                move '1110010' to BinaryWordArray
            ELSE IF Letra='s'
                move '1110011' to BinaryWordArray
            ELSE IF Letra='t'
                move '1110100' to BinaryWordArray
            ELSE IF Letra='u'
                move '1110101' to BinaryWordArray
            ELSE IF Letra='v'
                move '1110110' to BinaryWordArray
            ELSE IF Letra='w'
                move '1110111' to BinaryWordArray
            ELSE IF Letra='x'
                move '1111000' to BinaryWordArray
            ELSE IF Letra='y'
                move '1111001' to BinaryWordArray
            ELSE IF Letra='z'
                move '1111010' to BinaryWordArray
            ELSE
               DISPLAY "Opcion no valida".

       FILL-HAMMING.
           MOVE ALL SPACES TO HammingArray
           MOVE 0 TO HammingArrayBit(1).
           move 0 to HammingArrayBit(2).
           move BitBinaryWord(1) to HammingArrayBit(3).
           move 0 to HammingArrayBit(4).
           move BitBinaryWord(2) to HammingArrayBit(5).
           move BitBinaryWord(3) to HammingArrayBit(6).
           move BitBinaryWord(4) to HammingArrayBit(7).
           move 0 to HammingArrayBit(8).
           move BitBinaryWord(5) to HammingArrayBit(9).
           move BitBinaryWord(6) to HammingArrayBit(10).
           move BitBinaryWord(7) to HammingArrayBit(11).

       CALC-PARITY-BITS.
           MOVE 0 TO Parity1.
           COMPUTE Parity1 = HammingArrayBit(3) + HammingArrayBit(5)
           + HammingArrayBit(7) + HammingArrayBit(9)
           + HammingArrayBit(11).

           MOVE 0 TO Parity2.
           COMPUTE Parity2 = HammingArrayBit(3) + HammingArrayBit(6)
           + HammingArrayBit(7) + HammingArrayBit(10)
           + HammingArrayBit(11).

           MOVE 0 TO Parity4.
           COMPUTE Parity4 = HammingArrayBit(5) + HammingArrayBit(6)
           + HammingArrayBit(7).

           MOVE 0 TO Parity8.
           COMPUTE Parity8 = HammingArrayBit(9)
           + HammingArrayBit(10) + HammingArrayBit(11).

           IF Parity1 = 1 OR Parity1 = 3 OR Parity1 = 5 OR Parity1 = 7
               move 1 to HammingArrayBit(1)
           ELSE
               move 0 to HammingArrayBit(1).

           IF Parity2 = 1 OR Parity2 = 3 OR Parity2 = 5 OR Parity2 = 7
               move 1 to HammingArrayBit(2)
           ELSE
               move 0 to HammingArrayBit(2).

           IF Parity4 = 1 OR Parity4 = 3 OR Parity4 = 5 OR Parity4 = 7
               move 1 to HammingArrayBit(4)
           ELSE
               move 0 to HammingArrayBit(4).

           IF Parity8 = 1 OR Parity8 = 3 OR Parity8 = 5 OR Parity8 = 7
               move 1 to HammingArrayBit(8)
           ELSE
               move 0 to HammingArrayBit(8).

       CALC-PARITY-BITS-WRONG.
           DISPLAY "Hamming a evaluar: " WrongHammingArray
           MOVE 0 TO Parity1.
           COMPUTE Parity1 = WrongHammingArrayBit(3)
           + WrongHammingArrayBit(5)
           + WrongHammingArrayBit(7)
           + WrongHammingArrayBit(9)
           + WrongHammingArrayBit(11).

           MOVE 0 TO Parity2.
           COMPUTE Parity2 = WrongHammingArrayBit(3)
           + WrongHammingArrayBit(6)
           + WrongHammingArrayBit(7)
           + WrongHammingArrayBit(10)
           + WrongHammingArrayBit(11).

           MOVE 0 TO Parity4.
           COMPUTE Parity4 = WrongHammingArrayBit(5)
           + WrongHammingArrayBit(6)
           + WrongHammingArrayBit(7).

           MOVE 0 TO Parity8.
           COMPUTE Parity8 = WrongHammingArrayBit(9)
           + WrongHammingArrayBit(10)
           + WrongHammingArrayBit(11).

           IF Parity1 = 1 OR Parity1 = 3 OR Parity1 = 5 OR Parity1 = 7
               move 1 to Parity1
           ELSE
               move 0 to Parity1.

           IF Parity2 = 1 OR Parity2 = 3 OR Parity2 = 5 OR Parity2 = 7
               move 1 to Parity2
           ELSE
               move 0 to Parity2.

           IF Parity4 = 1 OR Parity4 = 3 OR Parity4 = 5 OR Parity4 = 7
               move 1 to Parity4
           ELSE
               move 0 to Parity4.

           IF Parity8 = 1 OR Parity8 = 3 OR Parity8 = 5 OR Parity8 = 7
               move 1 to Parity8
           ELSE
               move 0 to Parity8.

       FIND-ERROR-POSITION.

           MOVE 0 TO ErrorPosition.
           IF Parity1 <> WrongHammingArrayBit(1)
               ADD 1 TO ErrorPosition GIVING ErrorPosition
           END-IF.

           IF Parity2 <> WrongHammingArrayBit(2)
               ADD 2 TO ErrorPosition GIVING ErrorPosition
           END-IF.

           IF Parity4 <> WrongHammingArrayBit(4)
               ADD 4 TO ErrorPosition GIVING ErrorPosition
           END-IF.

           IF Parity8 <> WrongHammingArrayBit(8)
               ADD 8 TO ErrorPosition GIVING ErrorPosition
           END-IF.

           IF ErrorPosition = 0
               DISPLAY "*****************************************"
               DISPLAY "La cadena no tiene error"
               DISPLAY "*****************************************"
           ELSE
               DISPLAY "*****************************************"
               DISPLAY "El error esta en la posicion: " ErrorPosition
               DISPLAY "*****************************************".

       END PROGRAM HAMMING.
