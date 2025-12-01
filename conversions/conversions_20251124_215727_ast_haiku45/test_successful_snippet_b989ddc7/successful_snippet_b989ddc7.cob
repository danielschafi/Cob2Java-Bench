       IDENTIFICATION DIVISION.
       PROGRAM-ID. cobol-3d.

       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01  cobol-area.
           03  cobol-text-data PIC X(1030) VALUE "________/\\\\\\\\\____
      -        "____/\\\\\________/\\\\\\\\\\\\\__________/\\\\\________
      -        "/\\\_____________         _____/\\\////////_______/\\\//
      -        "/\\\_____\/\\\/////////\\\______/\\\///\\\_____\/\\\____
      -        "_________         ___/\\\/______________/\\\/__\///\\\__
      -        "_\/\\\_______\/\\\____/\\\/__\///\\\___\/\\\____________
      -        "_         __/\\\_______________/\\\______\//\\\__\/\\\\\
      -        "\\\\\\\\\____/\\\______\//\\\__\/\\\_____________       
      -      "  _\/\\\______________\/\\\_______\/\\\__\/\\\/////////\\\
      -        "__\/\\\_______\/\\\__\/\\\_____________         _\//\\\_
      -        "____________\//\\\______/\\\___\/\\\_______\/\\\__\//\\\
      -        "______/\\\___\/\\\_____________         __\///\\\_______
      -        "_____\///\\\__/\\\_____\/\\\_______\/\\\___\///\\\__/\\\
      -        "_____\/\\\_____________         ____\////\\\\\\\\\_____\
      -        "///\\\\\/______\/\\\\\\\\\\\\\/______\///\\\\\/______\/\
      -        "\\\\\\\\\\\\\\_         _______\/////////________\/////_
      -        "_______\/////////////__________\/////________\//////////
      -        "/////__" 
               .
           03  cobol-text-table REDEFINES cobol-text-data.
               05  cobol-text  PIC X(103) OCCURS 10 TIMES.

       01  i                   PIC 99.
       01  j                   PIC 9(4).

       PROCEDURE DIVISION.
           
           PERFORM VARYING i FROM 1 BY 1 UNTIL 10 < i
               MOVE 1 TO j
               PERFORM UNTIL 103 < j
                   
                   
                   IF cobol-text (i) (j:4) = "\\\/"
                       DISPLAY cobol-text (i) (j:3) AT LINE i COL j
                           WITH FOREGROUND-COLOR 7, HIGHLIGHT
                           
                       ADD 3 TO j
                       DISPLAY cobol-text (i) (j:1) AT LINE i COL j
                           WITH FOREGROUND-COLOR 0, HIGHLIGHT

                       ADD 1 TO j
                           
                       EXIT PERFORM CYCLE
                   END-IF
                       
                   
                   
                   IF cobol-text (i) (j:1) = "/" 
                          OR cobol-text (i) (FUNCTION SUM(j, 1):1) = "/"
                          OR cobol-text (i) (FUNCTION SUM(j, 1):2)
                              = "\/"
                       DISPLAY cobol-text (i) (j:1)  AT LINE i COL j
                           WITH FOREGROUND-COLOR 0, HIGHLIGHT
                   
                   ELSE
                       DISPLAY cobol-text (i) (j:1) AT LINE i COL j
                           WITH FOREGROUND-COLOR 7 , HIGHLIGHT
                   END-IF

                   ADD 1 TO j
               END-PERFORM
           END-PERFORM

           
           
           
           DISPLAY "Press enter to stop appreciating COBOL in 3D."
               AT LINE 11 COL 1
           ACCEPT i AT LINE 11 COL 46

           GOBACK
           .
