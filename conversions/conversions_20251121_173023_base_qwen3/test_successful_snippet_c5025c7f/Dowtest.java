import java.util.*;

public class Dowtest {
    public static void main(String[] args) {
        String wsInpDate = "00001225";
        int wsInpYear = 2008;
        int wsDow = 0;
        
        while (wsInpYear <= 2121) {
            wsInpDate = String.format("%04d", wsInpYear) + wsInpDate.substring(4);
            wsDow = toDow(wsInpDate);
            if (wsDow == 1) {
                System.out.println("year=" + wsInpYear);
            }
            wsInpYear += 1;
        }
    }
    
    private static int toDow(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6));
        int day = Integer.parseInt(date.substring(6, 8));
        
        int wmsMonth;
        int wmsYear;
        
        if (month < 3) {
            wmsMonth = month + 12;
            wmsYear = year - 1;
        } else {
            wmsMonth = month;
            wmsYear = year;
        }
        
        int wmsCsys = 1;
        int wmsSum = day + 2 * wmsMonth + wmsYear +
                    (int)(6 * (wmsMonth + 1) / 10) +
                    (int)(wmsYear / 4) -
                    (int)(wmsYear / 100) +
                    (int)(wmsYear / 400) +
                    wmsCsys;
        
        return (wmsSum % 7) + 1;
    }
}