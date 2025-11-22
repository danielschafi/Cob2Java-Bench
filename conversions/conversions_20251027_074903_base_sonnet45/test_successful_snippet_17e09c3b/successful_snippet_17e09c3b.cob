      **************************                                                
       IDENTIFICATION DIVISION.                                                 
      **************************                                                
                                                                                
       PROGRAM-ID.     FIZZBUZZ.                                                 
       INSTALLATION.   PT-SI.                                                   
       AUTHOR.         Edgar Mateus.                                            
       DATE-WRITTEN.   Out 2014.                                                
                                                                                
      *----------------------------------------------------------------         
      * COBOL SAMPLES - FIZZ BUZZ Algorithm                 
      * ========================================================                
      *  #Fizz Buzz - Iterate From 1 to 200                               
      *    -> Print Fizz for all multiples of three
      *    -> Print Buzz for all multiples of five
      *    -> Print Fizz Buzz for all multiples of both three and five
      *----------------------------------------------------------------         
      *   DATE         AUTHOR               ALTER                               
      *   ----         ------            ----------                             
      * xx/xx/xx    xxxxxxxxxxxx       xxxxxxxxxxxxxxxxxxxxxxxx                 
      *----------------------------------------------------------------         
                                                                                
      **************************                                                
       ENVIRONMENT DIVISION.                                                    
      **************************                                                
         CONFIGURATION SECTION.                                                 
          SOURCE-COMPUTER. IBM-3090.                                            
      *   SOURCE-COMPUTER. IBM-3090 WITH DEBUGGING MODE.                        
                                                                                
      **************************                                                
       DATA DIVISION.                                                           
      **************************                                                
      ***************************                                               
         WORKING-STORAGE SECTION.                                               
      *************************** 
       01 CNST-MAX                          PIC 999 VALUE 200.
       01 WS-COUNTER                        PIC 999 VALUE ZERO.
       01 WS-FIZZ-AUX                       PIC 9.
       01 WS-BUZZ-AUX                       PIC 9.        
       
       01  WS-TIME.                                                             
           05 WS-HH                    PIC 99.                                  
           05 WS-MM                    PIC 99.                                  
           05 WS-SS                    PIC 99.                                  
                                                                                
      **********************************************                            
       PROCEDURE DIVISION.                                                      
      **********************************************                            
       MAINLINE SECTION.          
           DISPLAY '+----------------------------------------------+'           
           DISPLAY '|                FIZZ BUZZ                     |'           
           DISPLAY '+----------------------------------------------+'           
           
           MOVE FUNCTION CURRENT-DATE (9:6) TO WS-TIME                          
           DISPLAY '[Current Time] - '                                       
                    WS-HH ':' WS-MM ':' WS-SS       
                    
           PERFORM VARYING WS-COUNTER FROM 1 BY 1
                     UNTIL WS-COUNTER > CNST-MAX
               COMPUTE WS-FIZZ-AUX = FUNCTION MOD(WS-COUNTER, 3)
               COMPUTE WS-BUZZ-AUX = FUNCTION MOD(WS-COUNTER, 5)
               
               IF (WS-FIZZ-AUX = ZERO AND WS-BUZZ-AUX = ZERO)
                  DISPLAY 'Fizz Buzz'
               ELSE
                  IF WS-FIZZ-AUX = ZERO
                     DISPLAY 'Fizz'
                  ELSE
                     IF WS-BUZZ-AUX = ZERO
                        DISPLAY 'Buzz'
                     ELSE
                        DISPLAY WS-COUNTER
                     END-IF
                  END-IF
               END-IF
           END-PERFORM
                                        
           DISPLAY '+----------------------------------------------+'           
           DISPLAY '|        FIZZ BUZZ - END BATCH                 |'           
           DISPLAY '+----------------------------------------------+'.          
                      
           MOVE 0 TO RETURN-CODE.
                      
           STOP RUN.                     
  