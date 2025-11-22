import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AutoAbbreviations {
    public static void main(String[] args) {
        int endo = 0;
        int lineNum = 1;
        
        try (BufferedReader reader = new BufferedReader(new FileReader("days-of-week.txt"))) {
            String dowLine;
            
            while (endo == 0) {
                dowLine = reader.readLine();
                
                if (dowLine == null) {
                    endo = 1;
                } else {
                    if (dowLine.trim().isEmpty()) {
                        System.out.println();
                    } else {
                        int endo2 = 0;
                        int charAmt = 0;
                        int largestCharAmt = 0;
                        
                        while (endo2 == 0) {
                            int abrestart = 0;
                            charAmt++;
                            
                            String[] abbrList = new String[7];
                            for (int i = 0; i < 7; i++) {
                                abbrList[i] = "";
                            }
                            
                            String[] days = dowLine.trim().split("\\s+");
                            
                            if (days.length < 7 || (days.length == 1 && days[0].isEmpty())) {
                                endo2 = 3;
                            } else {
                                for (int dayNum = 0; dayNum < 7 && dayNum < days.length; dayNum++) {
                                    String curDay = days[dayNum];
                                    
                                    int tmp1 = curDay.length();
                                    if (tmp1 > largestCharAmt) {
                                        largestCharAmt = tmp1;
                                    }
                                    
                                    if (curDay.isEmpty()) {
                                        endo2 = 3;
                                        break;
                                    }
                                    
                                    String curAbbr = curDay.substring(0, Math.min(charAmt, curDay.length()));
                                    
                                    for (int tindex = 0; tindex < dayNum; tindex++) {
                                        if (abbrList[tindex].equals(curAbbr)) {
                                            abrestart = 1;
                                            break;
                                        }
                                    }
                                    
                                    abbrList[dayNum] = curAbbr;
                                }
                                
                                if (abrestart == 0) {
                                    endo2 = 1;
                                }
                                
                                if (charAmt > largestCharAmt) {
                                    endo2 = 2;
                                }
                            }
                        }
                        
                        System.out.print("Line " + lineNum + ": ");
                        
                        if (endo2 == 3) {
                            System.out.println("Error: not enough days");
                        } else if (endo2 == 2) {
                            System.out.println("Error: identical days");
                        } else {
                            System.out.print(charAmt + ": ");
                            
                            String[] days = dowLine.trim().split("\\s+");
                            String[] abbrList = new String[7];
                            
                            for (int i = 0; i < 7 && i < days.length; i++) {
                                String curDay = days[i];
                                abbrList[i] = curDay.substring(0, Math.min(charAmt, curDay.length()));
                            }
                            
                            for (int tindex = 0; tindex < 7; tindex++) {
                                System.out.print(abbrList[tindex] + ".");
                                if (tindex < 6) {
                                    System.out.print(" ");
                                } else {
                                    System.out.println();
                                }
                            }
                        }
                    }
                    
                    lineNum++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}