import java.io.*;
import java.util.*;

public class AutoAbbreviations {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("days-of-week.txt"));
            String dowLine;
            int lineNum = 1;
            
            while ((dowLine = reader.readLine()) != null) {
                if (dowLine.isEmpty()) {
                    System.out.println();
                } else {
                    int charAmt = 0;
                    int largestCharAmt = 0;
                    int endo2 = 0;
                    
                    while (endo2 == 0) {
                        charAmt++;
                        String[] abbrList = new String[7];
                        Arrays.fill(abbrList, "");
                        
                        String[] days = dowLine.trim().split("\\s+");
                        
                        if (days.length < 7) {
                            endo2 = 3;
                            break;
                        }
                        
                        int abrestart = 0;
                        
                        for (int dayNum = 0; dayNum < 7; dayNum++) {
                            String curDay = days[dayNum];
                            
                            int dayLength = curDay.length();
                            if (dayLength > largestCharAmt) {
                                largestCharAmt = dayLength;
                            }
                            
                            if (charAmt > dayLength) {
                                endo2 = 3;
                                break;
                            }
                            
                            String curAbbr = curDay.substring(0, Math.min(charAmt, curDay.length()));
                            
                            for (int i = 0; i < dayNum; i++) {
                                if (abbrList[i].equals(curAbbr)) {
                                    abrestart = 1;
                                }
                            }
                            
                            abbrList[dayNum] = curAbbr;
                        }
                        
                        if (abrestart == 0 && endo2 == 0) {
                            endo2 = 1;
                        }
                        
                        if (charAmt > largestCharAmt && endo2 == 0) {
                            endo2 = 2;
                        }
                        
                        if (endo2 == 1) {
                            System.out.print("Line " + lineNum + ": ");
                            System.out.print(charAmt + ": ");
                            
                            for (int i = 0; i < 7; i++) {
                                System.out.print(abbrList[i]);
                                System.out.print(".");
                                if (i < 6) {
                                    System.out.print(" ");
                                }
                            }
                            System.out.println();
                            break;
                        } else if (endo2 == 2) {
                            System.out.println("Line " + lineNum + ": Error: identical days");
                            break;
                        } else if (endo2 == 3) {
                            System.out.println("Line " + lineNum + ": Error: not enough days");
                            break;
                        }
                    }
                }
                lineNum++;
            }
            
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}