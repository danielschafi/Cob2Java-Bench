       *> MAIN INDEX Controller
       IDENTIFICATION DIVISION.
       PROGRAM-ID. INDEX.
       DATA DIVISION.

       WORKING-STORAGE SECTION.
            01 CREATER.
                03 CREATER_ID PIC x.
                03 CREATER_NAME PIC x(20).
            01 VAR-STATUS-CODE PIC 999.
            01 DEFAULT-STATUS-CODE PIC 999 VALUE 200.
            01 ERROR-CLIENT-CODE PIC 999 VALUE 400.
            01 ERROR-SERVER-CODE PIC 999 VALUE 500.
            01 NO-ERROR-CODE PIC 999 VALUE 300.
            01 LOOP-COUNTER PIC 99999 VALUE 1.
            01 VAR-IDX PIC 99999 VALUE 0.
       PROCEDURE DIVISION.
           *> CREATER Accept

           DISPLAY "What`s your Creater ID ? ".
           ACCEPT CREATER_ID.

           DISPLAY "What`s your Creater Name ? ".
           ACCEPT CREATER_NAME.

           DISPLAY "Accept " CREATER_ID " : " CREATER_NAME.

           DISPLAY "Type Code".

           ACCEPT VAR-STATUS-CODE.

           *> StatusCode = 200
           IF VAR-STATUS-CODE = DEFAULT-STATUS-CODE THEN
               DISPLAY "Status Code Accept".
           *> StatusCOde = 300
           IF VAR-STATUS-CODE = NO-ERROR-CODE THEN
               DISPLAY "Status Code No Error".
           *> StatusCode = 400
           IF VAR-STATUS-CODE = ERROR-CLIENT-CODE THEN
               DISPLAY "Status Code Client Error".
           *> StatusCode - 500
           IF VAR-STATUS-CODE = ERROR-SERVER-CODE THEN
               DISPLAY "Status Code Server Error".

           DISPLAY "Type Loop Counter "
           ACCEPT LOOP-COUNTER.

           DISPLAY "Start Loop Run".

           PERFORM UNTIL VAR-IDX > LOOP-COUNTER

               DISPLAY "Counter : " VAR-IDX
               ADD 1 TO VAR-IDX

           END-PERFORM.

           DISPLAY "Stop Loop Run".

           STOP RUN.


