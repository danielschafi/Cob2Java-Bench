import java.io.*;
import java.util.*;

public class AutoAbbreviations {
    private static final String FILE_NAME = "days-of-week.txt";
    
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int lineNumber = 1;
            
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    System.out.println();
                } else {
                    processLine(line, lineNumber);
                }
                lineNumber++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    private static void processLine(String inputLine, int lineNumber) {
        // Initialize variables
        int endo = 0;
        int endo2 = 0;
        int charAmt = 1;
        int largestCharAmt = 0;
        int dayNum = 1;
        int abRestart = 0;
        String curAbbr = "";
        int tIndex = 1;
        
        // Array to store abbreviations (size 7)
        String[] abbrList = new String[7];
        for (int i = 0; i < 7; i++) {
            abbrList[i] = "";
        }
        
        // Process until endo2 > 0
        while (endo2 == 0) {
            int abptr = 1;
            dayNum = 1;
            abRestart = 0;
            
            charAmt++;
            
            // Reset abbrList
            for (int i = 0; i < 7; i++) {
                abbrList[i] = "";
            }
            
            // Process each word in the line
            int wordsProcessed = 0;
            int pos = 0;
            
            while (pos < inputLine.length() && endo2 == 0) {
                // Skip spaces
                while (pos < inputLine.length() && inputLine.charAt(pos) == ' ') {
                    pos++;
                }
                
                if (pos >= inputLine.length()) break;
                
                // Extract word
                int start = pos;
                while (pos < inputLine.length() && inputLine.charAt(pos) != ' ') {
                    pos++;
                }
                String curDay = inputLine.substring(start, pos);
                
                // Count characters (excluding spaces)
                int tmp1 = 0;
                int tmp2 = 0;
                for (int i = 0; i < curDay.length(); i++) {
                    if (curDay.charAt(i) != ' ') {
                        tmp1++;
                    }
                }
                
                // Count spaces
                for (int i = 0; i < curDay.length(); i++) {
                    if (curDay.charAt(i) == ' ') {
                        tmp2++;
                    }
                }
                
                tmp1 -= tmp2;
                
                if (tmp1 > largestCharAmt) {
                    largestCharAmt = tmp1;
                }
                
                if (curDay.isEmpty()) {
                    endo2 = 3;
                    break;
                }
                
                // Get abbreviation of first charAmt characters
                if (charAmt <= curDay.length()) {
                    curAbbr = curDay.substring(0, charAmt);
                } else {
                    curAbbr = curDay;
                }
                
                // Check if this abbreviation already exists
                abRestart = 0;
                tIndex = 0;
                for (int i = 0; i < 7; i++) {
                    if (abbrList[i].equals(curAbbr)) {
                        abRestart = 1;
                        break;
                    }
                    tIndex++;
                }
                
                // Store abbreviation
                if (dayNum <= 7) {
                    abbrList[dayNum - 1] = curAbbr;
                }
                
                dayNum++;
                wordsProcessed++;
                
                // If we've processed all words, break
                if (wordsProcessed >= 7) break;
            }
            
            // Check if we need to restart or continue
            if (abRestart == 0) {
                endo2 = 1;
            }
            
            if (charAmt > largestCharAmt) {
                endo2 = 2;
            }
        }
        
        // Display results
        System.out.print("Line " + lineNumber + ": ");
        
        if (endo2 == 3) {
            System.out.print("Error: not enough ");
            System.out.println("days");
        } else if (endo2 == 2) {
            System.out.print("Error: identical ");
            System.out.println("days");
        } else {
            System.out.print(charAmt + ": ");
            
            // Display abbreviations
            for (int i = 0; i < 7; i++) {
                String currentAbbr = abbrList[i];
                
                if (!currentAbbr.isEmpty()) {
                    // Count non-space characters
                    int tmp1 = 0;
                    int tmp2 = 0;
                    for (int j = 0; j < currentAbbr.length(); j++) {
                        if (currentAbbr.charAt(j) != ' ') {
                            tmp1++;
                        }
                    }
                    
                    // Count trailing spaces
                    for (int j = currentAbbr.length() - 1; j >= 0; j--) {
                        if (currentAbbr.charAt(j) == ' ') {
                            tmp2++;
                        } else {
                            break;
                        }
                    }
                    
                    tmp1 -= tmp2;
                    
                    if (tmp1 > 0) {
                        System.out.print(currentAbbr.substring(0, tmp1));
                    }
                    System.out.print(".");
                    
                    if (i < 6) {
                        System.out.print(" ");
                    } else {
                        System.out.println();
                    }
                } else {
                    if (i < 6) {
                        System.out.print(" ");
                    } else {
                        System.out.println();
                    }
                }
            }
        }
    }
}