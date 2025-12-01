       IDENTIFICATION DIVISION.
       PROGRAM-ID. "SSS".
      *AUTHOR. ERIC CONRAD.
      *DESCRIPTION. A program to solve a triangle given its three
      *    sides.
      *HISTORY.
      *    4Jan2021 - Apply rounding in computations.

       ENVIRONMENT DIVISION.
       CONFIGURATION SECTION.
       REPOSITORY.
           FUNCTION ALL INTRINSIC.

       DATA DIVISION.
       WORKING-STORAGE SECTION.
       77  X                            PIC S9(5)V9(8)
               SIGN TRAILING SEPARATE CHARACTER.
       77  Y                            PIC S9(5)V9(8)
               SIGN TRAILING SEPARATE CHARACTER.
       77  Z                            PIC S9(5)V9(8)
               SIGN TRAILING SEPARATE CHARACTER.
       77  XI                           PIC S9(5)V9(8)
               SIGN TRAILING SEPARATE CHARACTER.
       77  ZETA                         PIC S9(5)V9(8)
               SIGN TRAILING SEPARATE CHARACTER.
 
      * Sides
       77  A                            PIC S9(5)V9(8)
               SIGN TRAILING SEPARATE CHARACTER.
       77  B                            PIC S9(5)V9(8)
               SIGN TRAILING SEPARATE CHARACTER.
       77  C                            PIC S9(5)V9(8)
               SIGN TRAILING SEPARATE CHARACTER.

      * Semiperimeter
       77  S                            PIC S9(5)V9(8)
               SIGN TRAILING SEPARATE CHARACTER.
      * Angles
       77  ALPHA                        PIC S9(5)V9(8)
               SIGN TRAILING SEPARATE CHARACTER.
       77  BETA                         PIC S9(5)V9(8)
               SIGN TRAILING SEPARATE CHARACTER.
       77  GAMMA                        PIC S9(5)V9(8)
               SIGN TRAILING SEPARATE CHARACTER.
       77  XVAL                         PIC Z(5).9(4)-.
       77  INPUT-PROMPT                 PIC X(72).

       01  OUTPUT-1.
           02  OLABEL                       PIC X(15)
                   JUSTIFIED RIGHT.
           02  AVAL                         PIC Z(10).9(4).
           02  BVAL                         PIC Z(10).9(4).
           02  CVAL                         PIC Z(10).9(4).
           02  OUNITS                       PIC X(15)
                   JUSTIFIED RIGHT.

       PROCEDURE DIVISION.
       000-MAIN.
           MOVE "Enter the length of side a:" TO INPUT-PROMPT.
           PERFORM 100-GET-SIDE.
           MOVE X TO A.

           MOVE "Enter the length of side b:" TO INPUT-PROMPT.
           PERFORM 100-GET-SIDE.
           MOVE X TO B.

           MOVE "Enter the length of side c:" TO INPUT-PROMPT.
           PERFORM 100-GET-SIDE.
           MOVE X TO C.

           COMPUTE S ROUNDED = (A + B + C) / 2
               ON SIZE ERROR
                   DISPLAY "Semiperimeter: Arithmetic overflow"
                   STOP RUN.
           IF MAX(A, B, C) IS GREATER THAN S THEN
               DISPLAY "The triangle inequality is violated..."
               DISPLAY "The triangle has no real solution."
               STOP RUN
           END-IF.

           MOVE A TO X.
           MOVE B TO Y.
           MOVE C TO Z.
           PERFORM 200-LAW-OF-COSINES.
           MOVE ZETA TO GAMMA.

           MOVE B TO X.
           MOVE C TO Y.
           MOVE A TO Z.
           PERFORM 200-LAW-OF-COSINES.
           MOVE ZETA TO ALPHA.

           MOVE C TO X.
           MOVE A TO Y.
           MOVE B TO Z.
           PERFORM 200-LAW-OF-COSINES.
           MOVE ZETA TO BETA.

           DISPLAY "Solution:".

           MOVE "Sides:" TO OLABEL.
           MOVE A TO AVAL.
           MOVE B TO BVAL.
           MOVE C TO CVAL.
           MOVE SPACES TO OUNITS.
           DISPLAY OUTPUT-1.

           MOVE "Angles:" TO OLABEL.
           MOVE ALPHA TO AVAL.
           MOVE BETA TO BVAL.
           MOVE GAMMA TO CVAL.
           MOVE "radians" TO OUNITS.
           DISPLAY OUTPUT-1.

           MOVE SPACES TO OLABEL.
           MOVE ALPHA TO XI.
           PERFORM 300-TO-DEGREES.
           MOVE ZETA TO AVAL.
           MOVE BETA TO XI.
           PERFORM 300-TO-DEGREES.
           MOVE ZETA TO BVAL.
           MOVE GAMMA TO XI.
           PERFORM 300-TO-DEGREES.
           MOVE ZETA TO CVAL.
           MOVE "degrees" TO OUNITS.
           DISPLAY OUTPUT-1.

           GOBACK.

       100-GET-SIDE.
           DISPLAY INPUT-PROMPT.
           ACCEPT X.
           MOVE X TO XVAL.
           DISPLAY "  entered: " XVAL.
           IF X IS NOT GREATER THAN 0.0 THEN
               DISPLAY "  lengths must be positive, try again..."
               GO TO 100-GET-SIDE
           END-IF.

       200-LAW-OF-COSINES.
           COMPUTE ZETA ROUNDED =
               ACOS((X * X + Y * Y - Z * Z) / (2 * X * Y)).

       300-TO-DEGREES.
           COMPUTE ZETA ROUNDED = 180 * XI / PI.

