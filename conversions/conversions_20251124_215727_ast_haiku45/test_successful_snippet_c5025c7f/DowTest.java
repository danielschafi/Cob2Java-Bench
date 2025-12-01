```java
public class DowTest {
    private String wsInpDate = "        ";
    private int wsDow = 0;

    public static void main(String[] args) {
        DowTest dowtest = new DowTest();
        dowtest.run();
    }

    public void run() {
        wsInpDate = "00001225";
        
        for (int wsInpYear = 2008; wsInpYear <= 2121; wsInpYear++) {
            String dateStr = String.format("%04d%s", wsInpYear, wsInpDate.substring(4));
            wsDow = todow(dateStr);
            if (wsDow == 1) {
                System.out.println("year=" + wsInpYear);
            }
        }
    }

    public int todow(String lkipDate) {
        int lkipDateYear = Integer.parseInt(lkipDate.substring(0, 4));
        int lkipDateMonth = Integer.parseInt(lkipDate.substring(4, 6));
        int lkipDateDay = Integer.parseInt(lkipDate.substring(6, 8));
        
        int wmsYear;
        int wmsMonth;
        int wmsCsys = 1;
        
        if (lkipDateMonth < 3) {
            wmsMonth = lkipDateMonth + 12;
            wmsYear = lkipDateYear - 1;
        } else {
            wmsMonth = lkipDateMonth;
            wmsYear = lkipDateYear;
        }
        
        int wmsSum = lkipDateDay + 2 * wmsMonth + wmsYear
                + (int)(6 * (wmsMonth + 1) / 10.0)
                + (int)(wmsYear / 4.0)
                - (int)(wmsYear / 100.0)
                + (int)(wmsYear / 400.0)
                + wmsCsys;
        
        int lkopDow = (wmsSum % 7) + 1;
        
        return lkopDow;
    }
}