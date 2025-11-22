import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UnstringExample {
    
    private String randomString = "123ABCDEFGHIJKL";
    
    private String ch1 = "";
    private String ch2 = "";
    private String ch3 = "";
    
    private String delich1 = "";
    private String delich2 = "";
    private String delich3 = "";
    
    private int countch1 = 0;
    private int countch2 = 0;
    private int countch3 = 0;
    
    private int ptr = 4;
    private int countsplit = 0;
    
    private String todayDate = "";
    private String todayYear = "";
    private String todayMonth = "";
    private String todayDay = "";
    
    public static void main(String[] args) {
        UnstringExample program = new UnstringExample();
        program.run();
    }
    
    public void run() {
        unstringWithDelimiters();
        unstringBySize();
    }
    
    private void unstringWithDelimiters() {
        String source = randomString;
        int pointer = ptr - 1;
        countsplit = 0;
        boolean overflow = false;
        
        ch1 = "";
        ch2 = "";
        ch3 = "";
        delich1 = "";
        delich2 = "";
        delich3 = "";
        countch1 = 0;
        countch2 = 0;
        countch3 = 0;
        
        if (pointer < 0 || pointer >= source.length()) {
            overflow = true;
        }
        
        if (!overflow) {
            String remaining = source.substring(pointer);
            
            int pos1 = findDelimiter(remaining, "ABC", "GHI");
            if (pos1 >= 0) {
                ch1 = padRight(remaining.substring(0, pos1), 10);
                countch1 = pos1;
                String delim1 = getDelimiterAt(remaining, pos1, "ABC", "GHI");
                delich1 = padRight(delim1, 3);
                remaining = remaining.substring(pos1 + delim1.length());
                countsplit++;
                
                int pos2 = findDelimiter(remaining, "ABC", "GHI");
                if (pos2 >= 0) {
                    ch2 = padRight(remaining.substring(0, pos2), 10);
                    countch2 = pos2;
                    String delim2 = getDelimiterAt(remaining, pos2, "ABC", "GHI");
                    delich2 = padRight(delim2, 3);
                    remaining = remaining.substring(pos2 + delim2.length());
                    countsplit++;
                    
                    int pos3 = findDelimiter(remaining, "ABC", "GHI");
                    if (pos3 >= 0) {
                        ch3 = padRight(remaining.substring(0, pos3), 10);
                        countch3 = pos3;
                        String delim3 = getDelimiterAt(remaining, pos3, "ABC", "GHI");
                        delich3 = padRight(delim3, 3);
                        countsplit++;
                    } else {
                        ch3 = padRight(remaining, 10);
                        countch3 = remaining.length();
                        delich3 = padRight("", 3);
                        countsplit++;
                    }
                } else {
                    ch2 = padRight(remaining, 10);
                    countch2 = remaining.length();
                    delich2 = padRight("", 3);
                    countsplit++;
                }
            } else {
                ch1 = padRight(remaining, 10);
                countch1 = remaining.length();
                delich1 = padRight("", 3);
                countsplit++;
            }
        }
        
        if (overflow) {
            System.out.println("Pointer too low/high or not enough variables :(");
        }
        
        System.out.println("Content 1 : " + ch1 + " (delimiter : " + delich1 + ", size : " + countch1 + ")");
        System.out.println("Content 2 : " + ch2 + " (delimiter : " + delich2 + ", size : " + countch2 + ")");
        System.out.println("Content 3 : " + ch3 + " (delimiter : " + delich3 + ", size : " + countch3 + ")");
        System.out.println("Split number : " + countsplit);
    }
    
    private int findDelimiter(String str, String... delimiters) {
        int minPos = -1;
        for (String delim : delimiters) {
            int pos = str.indexOf(delim);
            if (pos >= 0) {
                if (minPos < 0 || pos < minPos) {
                    minPos = pos;
                }
            }
        }
        return minPos;
    }
    
    private String getDelimiterAt(String str, int pos, String... delimiters) {
        for (String delim : delimiters) {
            if (str.startsWith(delim, pos)) {
                return delim;
            }
        }
        return "";
    }
    
    private void unstringBySize() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        todayDate = today.format(formatter);
        
        if (todayDate.length() >= 8) {
            todayYear = todayDate.substring(0, 4);
            todayMonth = todayDate.substring(4, 6);
            todayDay = todayDate.substring(6, 8);
        }
        
        System.out.println("Today: " + todayDay + "/" + todayMonth + "/" + todayYear);
    }
    
    private String padRight(String str, int length) {
        if (str.length() >= length) {
            return str.substring(0, length);
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < length) {
            sb.append(' ');
        }
        return sb.toString();
    }
}