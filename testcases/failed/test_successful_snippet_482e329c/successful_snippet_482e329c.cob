       IDENTIFICATION DIVISION.
       PROGRAM-ID. "SSS".
      *AUTHOR. ERIC CONRAD.
      *DESCRIPTION. A program to solve a triangle given the length
      *    of two sides and the measure of the angle contained by
      *    them.

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
       77  ANGLE-MEASURE                PIC X VALUE SPACE.
           88  VALID-MEASURE                VALUES "r", "d".
           88  RADIAN-MEASURE               VALUE "r".
           88  DEGREE-MEASURE               VALUE "d".

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

           MOVE "Enter the measure of angle C:" TO INPUT-PROMPT.
           PERFORM 150-GET-ANGLE.
           MOVE XI TO GAMMA.

           MOVE A TO X.
           MOVE B TO Y.
           MOVE GAMMA TO ZETA.
           PERFORM 210-LAW-OF-COSINES-SIDE.
           MOVE Z TO C.

           MOVE B TO X.
           MOVE C TO Y.
           MOVE A TO Z.
           PERFORM 200-LAW-OF-COSINES-ANGLE.
           MOVE ZETA TO ALPHA.

           MOVE C TO X.
           MOVE A TO Y.
           MOVE B TO Z.
           PERFORM 200-LAW-OF-COSINES-ANGLE.
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

       150-GET-ANGLE.
           PERFORM 155-GET-MEASURE UNTIL VALID-MEASURE.
           DISPLAY INPUT-PROMPT.
           ACCEPT XI.
           MOVE XI TO XVAL.
           DISPLAY "  entered: " XVAL.
           IF XI IS NOT GREATER THAN 0.0 THEN
               DISPLAY "  angle measure must be positive..."
               GO TO 150-GET-ANGLE
           END-IF.
           IF RADIAN-MEASURE AND XI IS NOT LESS THAN PI THEN
               DISPLAY "  angles must be less than straight..."
               GO TO 150-GET-ANGLE
           END-IF.
           IF DEGREE-MEASURE AND XI IS NOT LESS THAN 180 THEN
               DISPLAY "  angles must be less than straight..."
               GO TO 150-GET-ANGLE
           END-IF.
           IF DEGREE-MEASURE THEN
               COMPUTE XI ROUNDED = XI * PI / 180.

       155-GET-MEASURE.
           DISPLAY "Enter units for angle measure "
               "(r-radians, d-degrees):".
           ACCEPT ANGLE-MEASURE.
           IF NOT VALID-MEASURE THEN
               DISPLAY "ERROR: Valid responses are 'r' and 'd'".

       200-LAW-OF-COSINES-ANGLE.
      *    This computes an angle given three sides
           COMPUTE ZETA ROUNDED =
               ACOS((X * X + Y * Y - Z * Z) / (2 * X * Y)).

       210-LAW-OF-COSINES-SIDE.
      *    This computes a side given an angle and the sides
      *    that enclose it.
           COMPUTE Z ROUNDED =
               SQRT(X * X + Y * Y - 2 * X * Y * COS(ZETA)).

       250-LAW-OF-SINES.
      *    Given two angles (xi and zeta) and the side opposite
      *    angle xi (i.e. side x), find side z.  The law of sines
      *    can also be used to find an angle, but special care
      *    must be taken as there may be no solution, a unique
      *    solution, a double solution (specifically a right angle),
      *    or two solutions (one acute, one obtuse).
           COMPUTE Z ROUNDED = X * SIN(ZETA) / SIN(Z).

       300-TO-DEGREES.
           COMPUTE ZETA ROUNDED = 180 * XI / PI.

