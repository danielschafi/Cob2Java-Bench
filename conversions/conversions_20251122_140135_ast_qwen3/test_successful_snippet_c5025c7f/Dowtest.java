public class Dowtest {
    public static void main(String[] args) {
        String wsInpDate = "00001225";
        int wsInpYear = 2008;
        int wsDow = 0;
        
        while (wsInpYear <= 2121) {
            Todow.todow(wsInpDate, wsDow);
            if (wsDow == 1) {
                System.out.println("year=" + wsInpYear);
            }
            wsInpYear++;
        }
    }
}

class Todow {
    public static void todow(String lkipDate, int lkopDow) {
        int lkipDateYear = Integer.parseInt(lkipDate.substring(0, 4));
        int lkipDateMonth = Integer.parseInt(lkipDate.substring(4, 6));
        int lkipDateDay = Integer.parseInt(lkipDate.substring(6, 8));
        
        int wmsYear;
        int wmsMonth;
        int wmsSum;
        int wmsCsys = 1;
        
        if (lkipDateMonth < 3) {
            wmsMonth = lkipDateMonth + 12;
            wmsYear = lkipDateYear - 1;
        } else {
            wmsMonth = lkipDateMonth;
            wmsYear = lkipDateYear;
        }
        
        wmsSum = lkipDateDay + 2 * wmsMonth + wmsYear +
                 (int)(6 * (wmsMonth + 1) / 10.0) +
                 (int)(wmsYear / 4.0) -
                 (int)(wmsYear / 100.0) +
                 (int)(wmsYear / 400.0) +
                 wmsCsys;
        
        lkopDow = wmsSum % 7 + 1;
    }
}