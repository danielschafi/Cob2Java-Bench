import java.io.*;

public class dowtest {
    
    public static void main(String[] args) {
        String wsInpDate = "00001225";
        int[] wsInpYear = new int[1];
        int[] wsDow = new int[1];
        
        wsInpYear[0] = Integer.parseInt(wsInpDate.substring(0, 4));
        
        for (wsInpYear[0] = 2008; wsInpYear[0] <= 2121; wsInpYear[0]++) {
            String currentDate = String.format("%04d", wsInpYear[0]) + wsInpDate.substring(4);
            todow(currentDate, wsDow);
            if (wsDow[0] == 1) {
                System.out.println("year=" + String.format("%04d", wsInpYear[0]));
            }
        }
    }
    
    public static void todow(String lkipDate, int[] lkopDow) {
        int lkipDateYear = Integer.parseInt(lkipDate.substring(0, 4));
        int lkipDateMonth = Integer.parseInt(lkipDate.substring(4, 6));
        int lkipDateDay = Integer.parseInt(lkipDate.substring(6, 8));
        
        int wmsYear;
        int wmsMonth;
        int wmsCsys = 1;
        int wmsSum;
        
        if (lkipDateMonth < 3) {
            wmsMonth = lkipDateMonth + 12;
            wmsYear = lkipDateYear - 1;
        } else {
            wmsMonth = lkipDateMonth;
            wmsYear = lkipDateYear;
        }
        
        wmsSum = lkipDateDay + 2 * wmsMonth + wmsYear
                + (int)Math.floor(6 * (wmsMonth + 1) / 10.0)
                + (int)Math.floor(wmsYear / 4.0)
                - (int)Math.floor(wmsYear / 100.0)
                + (int)Math.floor(wmsYear / 400.0)
                + wmsCsys;
        
        lkopDow[0] = (wmsSum % 7) + 1;
    }
}