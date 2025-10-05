       IDENTIFICATION DIVISION.
       PROGRAM-ID. EMPMGMT.
       
       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
           SELECT EMPLOYEE-FILE ASSIGN TO "employees.txt"
               ORGANIZATION IS LINE SEQUENTIAL.
           SELECT REPORT-FILE ASSIGN TO "report.txt"
               ORGANIZATION IS LINE SEQUENTIAL.
       
       DATA DIVISION.
       FILE SECTION.
       FD  EMPLOYEE-FILE.
       01  EMPLOYEE-RECORD         PIC X(80).
       
       FD  REPORT-FILE.
       01  REPORT-RECORD           PIC X(80).
       
       WORKING-STORAGE SECTION.
       01  WS-EOF-FLAG             PIC X(1) VALUE 'N'.
       01  WS-CHOICE               PIC X(1).
       01  WS-COUNTER              PIC 9(2) VALUE 1.
       01  WS-TOTAL-SALARY         PIC 9(8)V99 VALUE ZEROS.
       01  WS-EMPLOYEE-COUNT       PIC 9(3) VALUE ZEROS.
       
       01  WS-EMPLOYEE-TABLE.
           05  WS-EMPLOYEE-ENTRY   OCCURS 10 TIMES.
               10  WS-EMP-ID       PIC 9(5).
               10  WS-EMP-NAME     PIC X(25).
               10  WS-EMP-SALARY   PIC 9(6)V99.
               10  WS-EMP-DEPT     PIC X(15).
       
       01  WS-INPUT-DATA.
           05  WS-INPUT-ID         PIC 9(5).
           05  WS-INPUT-NAME       PIC X(25).
           05  WS-INPUT-SALARY     PIC 9(6)V99.
           05  WS-INPUT-DEPT       PIC X(15).
       
       01  WS-HEADERS.
           05  WS-MAIN-HEADER      PIC X(50) VALUE
               "EMPLOYEE MANAGEMENT SYSTEM".
           05  WS-COL-HEADER       PIC X(70) VALUE
               "ID     NAME                     SALARY    DEPT".
           05  WS-SEPARATOR        PIC X(70) VALUE
               "-----  -----------------------  --------  ----".
       
       01  WS-MESSAGES.
           05  WS-MENU-MSG1        PIC X(30) VALUE
               "1. Add Employee".
           05  WS-MENU-MSG2        PIC X(30) VALUE
               "2. Display All Employees".
           05  WS-MENU-MSG3        PIC X(30) VALUE
               "3. Generate Report".
           05  WS-MENU-MSG4        PIC X(30) VALUE
               "4. Exit".
           05  WS-PROMPT           PIC X(20) VALUE
               "Enter choice: ".
       
       01  WS-OUTPUT-LINE          PIC X(80).
       
       PROCEDURE DIVISION.
       
       MAIN-PARA.
           DISPLAY WS-MAIN-HEADER
           DISPLAY " "
           PERFORM MENU-LOOP UNTIL WS-CHOICE = '4'
           DISPLAY "Program ended."
           STOP RUN.
       
       MENU-LOOP.
           DISPLAY WS-MENU-MSG1
           DISPLAY WS-MENU-MSG2
           DISPLAY WS-MENU-MSG3
           DISPLAY WS-MENU-MSG4
           DISPLAY WS-PROMPT WITH NO ADVANCING
           ACCEPT WS-CHOICE
           
           EVALUATE WS-CHOICE
               WHEN '1'
                   PERFORM ADD-EMPLOYEE
               WHEN '2'
                   PERFORM DISPLAY-EMPLOYEES
               WHEN '3'
                   PERFORM GENERATE-REPORT
               WHEN '4'
                   CONTINUE
               WHEN OTHER
                   DISPLAY "Invalid choice. Try again."
           END-EVALUATE
           EXIT.
       
       ADD-EMPLOYEE.
           IF WS-EMPLOYEE-COUNT >= 10
               DISPLAY "Employee table full!"
           ELSE
               PERFORM GET-EMPLOYEE-DATA
               IF WS-INPUT-ID IS NUMERIC AND WS-INPUT-ID > ZEROS
                   ADD 1 TO WS-EMPLOYEE-COUNT
                   MOVE WS-EMPLOYEE-COUNT TO WS-COUNTER
                   MOVE WS-INPUT-ID TO 
                       WS-EMP-ID OF WS-EMPLOYEE-ENTRY (WS-COUNTER)
                   MOVE WS-INPUT-NAME TO
                       WS-EMP-NAME OF WS-EMPLOYEE-ENTRY (WS-COUNTER)
                   MOVE WS-INPUT-SALARY TO
                       WS-EMP-SALARY OF WS-EMPLOYEE-ENTRY (WS-COUNTER)
                   MOVE WS-INPUT-DEPT TO
                       WS-EMP-DEPT OF WS-EMPLOYEE-ENTRY (WS-COUNTER)
                   DISPLAY "Employee added successfully!"
               ELSE
                   DISPLAY "Invalid employee ID entered."
               END-IF
           END-IF
           EXIT.
       
       GET-EMPLOYEE-DATA.
           DISPLAY "Enter Employee ID: " WITH NO ADVANCING
           ACCEPT WS-INPUT-ID
           DISPLAY "Enter Employee Name: " WITH NO ADVANCING
           ACCEPT WS-INPUT-NAME
           DISPLAY "Enter Salary: " WITH NO ADVANCING
           ACCEPT WS-INPUT-SALARY
           DISPLAY "Enter Department: " WITH NO ADVANCING
           ACCEPT WS-INPUT-DEPT
           EXIT.
       
       DISPLAY-EMPLOYEES.
           IF WS-EMPLOYEE-COUNT = ZEROS
               DISPLAY "No employees to display."
           ELSE
               DISPLAY " "
               DISPLAY WS-COL-HEADER
               DISPLAY WS-SEPARATOR
               PERFORM VARYING WS-COUNTER FROM 1 BY 1
                   UNTIL WS-COUNTER > WS-EMPLOYEE-COUNT
                   PERFORM SHOW-EMPLOYEE
               END-PERFORM
           END-IF
           EXIT.
       
       SHOW-EMPLOYEE.
           STRING WS-EMP-ID OF WS-EMPLOYEE-ENTRY (WS-COUNTER)
                  DELIMITED BY SIZE
                  "     " DELIMITED BY SIZE
                  WS-EMP-NAME OF WS-EMPLOYEE-ENTRY (WS-COUNTER)
                  DELIMITED BY SPACE
                  "  $" DELIMITED BY SIZE
                  WS-EMP-SALARY OF WS-EMPLOYEE-ENTRY (WS-COUNTER)
                  DELIMITED BY SIZE
                  INTO WS-OUTPUT-LINE
           END-STRING
           DISPLAY WS-OUTPUT-LINE
           MOVE SPACES TO WS-OUTPUT-LINE
           EXIT.
       
       GENERATE-REPORT.
           IF WS-EMPLOYEE-COUNT = ZEROS
               DISPLAY "No employees for report."
           ELSE
               OPEN OUTPUT REPORT-FILE
               PERFORM WRITE-REPORT-HEADER
               PERFORM WRITE-EMPLOYEE-DETAILS
               PERFORM WRITE-REPORT-SUMMARY
               CLOSE REPORT-FILE
               DISPLAY "Report generated in report.txt"
           END-IF
           EXIT.
       
       WRITE-REPORT-HEADER.
           MOVE WS-MAIN-HEADER TO WS-OUTPUT-LINE
           WRITE REPORT-RECORD FROM WS-OUTPUT-LINE
           MOVE SPACES TO WS-OUTPUT-LINE
           WRITE REPORT-RECORD FROM WS-OUTPUT-LINE
           MOVE WS-COL-HEADER TO WS-OUTPUT-LINE
           WRITE REPORT-RECORD FROM WS-OUTPUT-LINE
           MOVE WS-SEPARATOR TO WS-OUTPUT-LINE
           WRITE REPORT-RECORD FROM WS-OUTPUT-LINE
           EXIT.
       
       WRITE-EMPLOYEE-DETAILS.
           MOVE ZEROS TO WS-TOTAL-SALARY
           PERFORM VARYING WS-COUNTER FROM 1 BY 1
               UNTIL WS-COUNTER > WS-EMPLOYEE-COUNT
               PERFORM WRITE-EMPLOYEE-LINE
               ADD WS-EMP-SALARY OF WS-EMPLOYEE-ENTRY (WS-COUNTER)
                   TO WS-TOTAL-SALARY
           END-PERFORM
           EXIT.
       
       WRITE-EMPLOYEE-LINE.
           STRING WS-EMP-ID OF WS-EMPLOYEE-ENTRY (WS-COUNTER)
                  DELIMITED BY SIZE
                  "     " DELIMITED BY SIZE
                  WS-EMP-NAME OF WS-EMPLOYEE-ENTRY (WS-COUNTER)
                  DELIMITED BY SPACE
                  "  $" DELIMITED BY SIZE
                  WS-EMP-SALARY OF WS-EMPLOYEE-ENTRY (WS-COUNTER)
                  DELIMITED BY SIZE
                  "  " DELIMITED BY SIZE
                  WS-EMP-DEPT OF WS-EMPLOYEE-ENTRY (WS-COUNTER)
                  DELIMITED BY SIZE
                  INTO WS-OUTPUT-LINE
           END-STRING
           WRITE REPORT-RECORD FROM WS-OUTPUT-LINE
           MOVE SPACES TO WS-OUTPUT-LINE
           EXIT.
       
       WRITE-REPORT-SUMMARY.
           MOVE SPACES TO WS-OUTPUT-LINE
           WRITE REPORT-RECORD FROM WS-OUTPUT-LINE
           STRING "Total Employees: " DELIMITED BY SIZE
                  WS-EMPLOYEE-COUNT DELIMITED BY SIZE
                  INTO WS-OUTPUT-LINE
           END-STRING
           WRITE REPORT-RECORD FROM WS-OUTPUT-LINE
           STRING "Total Payroll: $" DELIMITED BY SIZE
                  WS-TOTAL-SALARY DELIMITED BY SIZE
                  INTO WS-OUTPUT-LINE
           END-STRING
           WRITE REPORT-RECORD FROM WS-OUTPUT-LINE
           EXIT.
       
       END PROGRAM EMPMGMT.
