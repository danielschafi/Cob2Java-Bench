public class dowtest {
    static class DateInfo {
        char[] date = new char[8];
        
        void setDate(String s) {
            for (int i = 0; i < 8; i++) {
                date[i] = s.charAt(i);
            }
        }
        
        String getDate() {
            return new String(date);
        }
        
        int getYear() {
            return Integer.parseInt(new String(date, 0, 4));
        }
        
        void setYear(int year) {
            String yearStr = String.format("%04d", year);
            for (int i = 0; i < 4; i++) {
                date[i] = yearStr.charAt(i);
            }
        }
        
        int getMonth() {
            return Integer.parseInt(new String(date, 4, 2));
        }
        
        int getDay() {
            return Integer.parseInt(new String(date, 6, 2));
        }
    }
    
    static class DowResult {
        int dow;
    }
    
    public static void main(String[] args) {
        DateInfo inpDate = new DateInfo();
        inpDate.setDate("00001225");
        DowResult dow = new DowResult();
        
        for (int year = 2008; year <= 2121; year++) {
            inpDate.setYear(year);
            todow(inpDate, dow);
            if (dow.dow == 1) {
                System.out.println("year=" + year);
            }
        }
    }
    
    static void todow(DateInfo lkipDate, DowResult lkopDow) {
        int lkipDateYear = lkipDate.getYear();
        int lkipDateMonth = lkipDate.getMonth();
        int lkipDateDay = lkipDate.getDay();
        
        int wmsYear;
        int wmsMonth;
        
        if (lkipDateMonth < 3) {
            wmsMonth = lkipDateMonth + 12;
            wmsYear = lkipDateYear - 1;
        } else {
            wmsMonth = lkipDateMonth;
            wmsYear = lkipDateYear;
        }
        
        int wmsCsys = 1;
        
        int wmsSum = lkipDateDay + 2 * wmsMonth + wmsYear
                + (int)(6 * (wmsMonth + 1) / 10.0)
                + (int)(wmsYear / 4.0)
                - (int)(wmsYear / 100.0)
                + (int)(wmsYear / 400.0)
                + wmsCsys;
        
        lkopDow.dow = (wmsSum % 7) + 1;
    }
}