import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UnstringExample {
    static String randomString = "123ABCDEFGHIJKL";
    static String ch1 = "";
    static String ch2 = "";
    static String ch3 = "";
    static String delich1 = "";
    static String delich2 = "";
    static String delich3 = "";
    static int countch1 = 0;
    static int countch2 = 0;
    static int countch3 = 0;
    static int ptr = 4;
    static int countsplit = 0;
    static String todayDate = "";
    static String todayYear = "";
    static String todayMonth = "";
    static String todayDay = "";

    public static void main(String[] args) {
        unstringWithDelimiters();
        unstringDate();
    }

    static void unstringWithDelimiters() {
        String input = randomString;
        List<String> parts = new ArrayList<>();
        List<String> delimiters = new ArrayList<>();
        
        int startIdx = ptr - 1;
        int currentIdx = startIdx;
        StringBuilder currentPart = new StringBuilder();
        String foundDelimiter = "";
        int partCount = 0;
        
        while (currentIdx < input.length() && partCount < 3) {
            String remaining = input.substring(currentIdx);
            
            if (remaining.startsWith("ABC")) {
                if (currentPart.length() > 0 || partCount == 0) {
                    parts.add(currentPart.toString());
                    delimiters.add("ABC");
                    currentPart = new StringBuilder();
                    partCount++;
                }
                currentIdx += 3;
            } else if (remaining.startsWith("GHI")) {
                if (currentPart.length() > 0 || partCount == 0) {
                    parts.add(currentPart.toString());
                    delimiters.add("GHI");
                    currentPart = new StringBuilder();
                    partCount++;
                }
                currentIdx += 3;
            } else {
                currentPart.append(remaining.charAt(0));
                currentIdx++;
            }
        }
        
        if (currentPart.length() > 0 && partCount < 3) {
            parts.add(currentPart.toString());
            delimiters.add("");
            partCount++;
        }
        
        while (parts.size() < 3) {
            parts.add("");
            delimiters.add("");
        }
        
        ch1 = padString(parts.get(0), 10);
        ch2 = padString(parts.get(1), 10);
        ch3 = padString(parts.get(2), 10);
        
        delich1 = padString(delimiters.get(0), 3);
        delich2 = padString(delimiters.get(1), 3);
        delich3 = padString(delimiters.get(2), 3);
        
        countch1 = parts.get(0).length();
        countch2 = parts.get(1).length();
        countch3 = parts.get(2).length();
        
        countsplit = partCount;
        
        System.out.println("Content 1 : " + ch1 + " (delimiter : " + delich1 + ", size : " + countch1 + ")");
        System.out.println("Content 2 : " + ch2 + " (delimiter : " + delich2 + ", size : " + countch2 + ")");
        System.out.println("Content 3 : " + ch3 + " (delimiter : " + delich3 + ", size : " + countch3 + ")");
        System.out.println("Split number : " + countsplit);
    }

    static void unstringDate() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        todayDate = now.format(formatter);
        
        todayYear = todayDate.substring(0, 4);
        todayMonth = todayDate.substring(4, 6);
        todayDay = todayDate.substring(6, 8);
        
        System.out.println("Today: " + todayDay + "/" + todayMonth + "/" + todayYear);
    }

    static String padString(String s, int length) {
        if (s.length() >= length) {
            return s.substring(0, length);
        }
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < length) {
            sb.append(" ");
        }
        return sb.toString();
    }
}