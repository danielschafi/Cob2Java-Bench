       ID Division.
      * 
      * Copyright (C) 2021 Craig Schneiderwent.  All rights reserved.
      * 
      * I accept no liability for damages of any kind resulting 
      * from the use of this software.  Use at your own risk.
      *
      * This software may be modified and distributed under the terms
      * of the MIT license. See the LICENSE file for details.
      *
      * Demonstrate allocation, traversal, and deallocation of a
      * doubly-linked list.
      *
       Program-ID. lldemo1.
       Environment Division.
       Data Division.
       Working-Storage Section.
       01  CONSTANTS.
           05  MYNAME             PIC X(008) VALUE 'lldemo1'.

       01  WORK-AREAS.
           05  CURR-NODE-PTR      POINTER            VALUE NULL.
           05  WORK-NODE-PTR      POINTER            VALUE NULL.
           05  TEMP-NODE-PTR      POINTER            VALUE NULL.

       01  A-LIST.
           05  LIST-HEAD-PTR      POINTER            VALUE NULL.
           05  LIST-TAIL-PTR      POINTER            VALUE NULL.

       Linkage Section.
      *
      * OpenCOBOL 1.1 (now GnuCOBOL) requires these dynamically
      * allocated items be defined with the BASED keyword.  IBM
      * Enterprise COBOL does not implement the BASED keyword,
      * but does require these items to be located in the Linkage
      * Section.
      *
       01  CURR-NODE BASED.
           05  PREV-NODE-PTR      POINTER.
           05  NEXT-NODE-PTR      POINTER.
           05  NODE-DATA          PIC X(008)         VALUE SPACES.

       01  WORK-NODE BASED.
           05  PREV-NODE-PTR      POINTER.
           05  NEXT-NODE-PTR      POINTER.
           05  NODE-DATA          PIC X(008)         VALUE SPACES.

       Procedure Division.

           PERFORM 8010-ALLOCATE-NODE
           MOVE 'NODE3' TO NODE-DATA OF CURR-NODE
           SET LIST-HEAD-PTR TO CURR-NODE-PTR
           SET LIST-TAIL-PTR TO CURR-NODE-PTR
           SET TEMP-NODE-PTR TO CURR-NODE-PTR

           PERFORM 8030-ADD-NODE-TO-TAIL-OF-LIST
           MOVE 'NODE4' TO NODE-DATA OF CURR-NODE

           PERFORM 8040-ADD-NEW-NODE-AFTER-CURR
           MOVE 'NODE5' TO NODE-DATA OF CURR-NODE

           PERFORM 8020-ADD-NODE-TO-HEAD-OF-LIST
           MOVE 'NODE2' TO NODE-DATA OF CURR-NODE

           PERFORM 8050-ADD-NEW-NODE-BEFORE-CURR
           MOVE 'NODE1' TO NODE-DATA OF CURR-NODE

           DISPLAY 'five nodes allocated'
           PERFORM 1000-PRINT-LIST

           DISPLAY 'removing list tail'
           SET CURR-NODE-PTR TO LIST-TAIL-PTR
           PERFORM 8060-REMOVE-CURR-NODE

           DISPLAY 'four nodes allocated'
           PERFORM 1000-PRINT-LIST

           DISPLAY 'removing list head'
           SET CURR-NODE-PTR TO LIST-HEAD-PTR
           PERFORM 8060-REMOVE-CURR-NODE

           DISPLAY 'three nodes allocated'
           PERFORM 1000-PRINT-LIST

           DISPLAY 'removing middle entry from list'
           SET CURR-NODE-PTR TO TEMP-NODE-PTR
           PERFORM 8060-REMOVE-CURR-NODE

           DISPLAY 'free the rest of the list'

           SET CURR-NODE-PTR TO LIST-HEAD-PTR
           PERFORM UNTIL CURR-NODE-PTR = NULL
             PERFORM 8060-REMOVE-CURR-NODE
             SET CURR-NODE-PTR TO LIST-HEAD-PTR
           END-PERFORM

           GOBACK.

       1000-PRINT-LIST.
           SET CURR-NODE-PTR TO LIST-HEAD-PTR

           PERFORM UNTIL CURR-NODE-PTR = NULL
             SET ADDRESS OF CURR-NODE TO CURR-NODE-PTR
             DISPLAY
               CURR-NODE-PTR
               ' ' NODE-DATA OF CURR-NODE
               ' ' PREV-NODE-PTR OF CURR-NODE
               ' ' NEXT-NODE-PTR OF CURR-NODE
             SET CURR-NODE-PTR TO NEXT-NODE-PTR OF CURR-NODE
           END-PERFORM
           .

       8010-ALLOCATE-NODE.
           ALLOCATE CURR-NODE
             INITIALIZED
             RETURNING CURR-NODE-PTR

           DISPLAY 'CURR-NODE-PTR = ' CURR-NODE-PTR
           .

       8020-ADD-NODE-TO-HEAD-OF-LIST.
           PERFORM 8010-ALLOCATE-NODE

           SET WORK-NODE-PTR TO LIST-HEAD-PTR
           SET ADDRESS OF WORK-NODE TO WORK-NODE-PTR
           SET PREV-NODE-PTR OF WORK-NODE TO CURR-NODE-PTR
           SET NEXT-NODE-PTR OF CURR-NODE TO LIST-HEAD-PTR
           SET LIST-HEAD-PTR TO CURR-NODE-PTR
           .

       8030-ADD-NODE-TO-TAIL-OF-LIST.
           PERFORM 8010-ALLOCATE-NODE

           SET WORK-NODE-PTR TO LIST-TAIL-PTR
           SET ADDRESS OF WORK-NODE TO WORK-NODE-PTR
           SET NEXT-NODE-PTR OF WORK-NODE TO CURR-NODE-PTR
           SET PREV-NODE-PTR OF CURR-NODE TO LIST-TAIL-PTR
           SET LIST-TAIL-PTR TO CURR-NODE-PTR
           .

       8040-ADD-NEW-NODE-AFTER-CURR.
           SET WORK-NODE-PTR TO CURR-NODE-PTR
           SET ADDRESS OF WORK-NODE TO WORK-NODE-PTR

           PERFORM 8010-ALLOCATE-NODE
           SET PREV-NODE-PTR OF CURR-NODE TO WORK-NODE-PTR
           SET NEXT-NODE-PTR OF CURR-NODE TO NEXT-NODE-PTR OF WORK-NODE
           SET NEXT-NODE-PTR OF WORK-NODE TO CURR-NODE-PTR

           IF LIST-TAIL-PTR = WORK-NODE-PTR
               SET LIST-TAIL-PTR TO CURR-NODE-PTR
           END-IF
           .

       8050-ADD-NEW-NODE-BEFORE-CURR.
           SET WORK-NODE-PTR TO CURR-NODE-PTR
           SET ADDRESS OF WORK-NODE TO WORK-NODE-PTR

           PERFORM 8010-ALLOCATE-NODE
           SET NEXT-NODE-PTR OF CURR-NODE TO WORK-NODE-PTR
           SET PREV-NODE-PTR OF CURR-NODE TO PREV-NODE-PTR OF WORK-NODE
           SET PREV-NODE-PTR OF WORK-NODE TO CURR-NODE-PTR

           IF LIST-HEAD-PTR = WORK-NODE-PTR
               SET LIST-HEAD-PTR TO CURR-NODE-PTR
           END-IF
           .

      *
      * node1 prev-node-ptr = null   next-node-ptr = @node2
      * node2 prev-node-ptr = @node1 next-node-ptr = @node3
      * node3 prev-node-ptr = @node2 next-node-ptr = null
      * aList list-head-ptr = @node1 list-tail-ptr = @node3
      *
      * if curr-node-ptr = @node1
      *     node2 prev-node-ptr = null   next-node-ptr = @node3
      *     node3 unchanged
      *     aList list-head-ptr = @node2 list-tail-ptr = @node3
      *     free @node1
      * end-if
      *
      * if curr-node-ptr = @node2
      *     node1 prev-node-ptr = null   next-node-ptr = @node3
      *     node3 prev-node-ptr = @node1 next-node-ptr = null
      *     aList list-head-ptr = @node1 list-tail-ptr = @node3
      *     free @node2
      * end-if
      *
      * if curr-node-ptr = @node3
      *     node1 unchanged
      *     node2 prev-node-ptr = @node1 next-node-ptr = null
      *     aList list-head-ptr = @node1 list-tail-ptr = @node2
      *     free @node3
      * end-if
      *
      *
      *
      *
       8060-REMOVE-CURR-NODE.
           SET ADDRESS OF CURR-NODE TO CURR-NODE-PTR

           IF NEXT-NODE-PTR OF CURR-NODE = NULL
               CONTINUE
           ELSE
               SET ADDRESS OF WORK-NODE TO NEXT-NODE-PTR OF CURR-NODE
               SET PREV-NODE-PTR OF WORK-NODE
                TO PREV-NODE-PTR OF CURR-NODE
           END-IF

           IF PREV-NODE-PTR OF CURR-NODE = NULL
               CONTINUE
           ELSE
               SET ADDRESS OF WORK-NODE TO PREV-NODE-PTR OF CURR-NODE
               SET NEXT-NODE-PTR OF WORK-NODE
                TO NEXT-NODE-PTR OF CURR-NODE
           END-IF

           IF LIST-HEAD-PTR = CURR-NODE-PTR
               SET LIST-HEAD-PTR TO NEXT-NODE-PTR OF CURR-NODE
           END-IF

           IF LIST-TAIL-PTR = CURR-NODE-PTR
               SET LIST-TAIL-PTR TO PREV-NODE-PTR OF CURR-NODE
           END-IF

           DISPLAY 'freeing ' CURR-NODE-PTR
           FREE CURR-NODE-PTR
           .
