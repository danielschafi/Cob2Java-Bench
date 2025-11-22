import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UnstringExample {
    private static String randomString = "123ABCDEFGHIJKL";
    
    private static String ch1 = "";
    private static String ch2 = "";
    private static String ch3 = "";
    
    private static String delich1 = "";
    private static String delich2 = "";
    private static String delich3 = "";
    
    private static int countch1 = 0;
    private static int countch2 = 0;
    private static int countch3 = 0;
    
    private static int ptr = 4;
    private static int countsplit = 0;
    
    private static int todayDate = 0;
    private static int todayYear = 0;
    private static int todayMonth = 0;
    private static int todayDay = 0;
    
    public static void main(String[] args) {
        unstringExample();
    }
    
    private static void unstringExample() {
        // First unstring example
        String[] result = unstring(randomString, new String[]{"ABC", "GHI"}, ptr);
        ch1 = result[0];
        ch2 = result[1];
        ch3 = result[2];
        delich1 = result[3];
        delich2 = result[4];
        delich3 = result[5];
        countch1 = result[6].length();
        countch2 = result[7].length();
        countch3 = result[8].length();
        countsplit = Integer.parseInt(result[9]);
        
        System.out.println("Content 1 : " + ch1 + " (delimiter : " + delich1 + ", size : " + countch1 + ")");
        System.out.println("Content 2 : " + ch2 + " (delimiter : " + delich2 + ", size : " + countch2 + ")");
        System.out.println("Content 3 : " + ch3 + " (delimiter : " + delich3 + ", size : " + countch3 + ")");
        System.out.println("Split number : " + countsplit);
        
        // Second unstring example
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateStr = currentDate.format(formatter);
        todayDate = Integer.parseInt(dateStr);
        
        unstring(dateStr, new String[]{}, 0);
        System.out.println("Today: " + todayDay + "/" + todayMonth + "/" + todayYear);
    }
    
    private static String[] unstring(String input, String[] delimiters, int pointer) {
        String[] result = new String[10];
        StringBuilder content1 = new StringBuilder();
        StringBuilder content2 = new StringBuilder();
        StringBuilder content3 = new StringBuilder();
        String delim1 = "";
        String delim2 = "";
        String delim3 = "";
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int splitCount = 0;
        
        int start = 0;
        int pos = 0;
        int i = 0;
        
        for (int j = 0; j < delimiters.length; j++) {
            if (input.indexOf(delimiters[j], start) != -1) {
                pos = input.indexOf(delimiters[j], start);
                if (i == 0) {
                    content1.append(input.substring(start, pos));
                    delim1 = delimiters[j];
                    count1 = pos - start;
                    i++;
                } else if (i == 1) {
                    content2.append(input.substring(start, pos));
                    delim2 = delimiters[j];
                    count2 = pos - start;
                    i++;
                } else {
                    content3.append(input.substring(start, pos));
                    delim3 = delimiters[j];
                    count3 = pos - start;
                    i++;
                }
                start = pos + delimiters[j].length();
                splitCount++;
            }
        }
        
        if (start < input.length()) {
            if (i == 0) {
                content1.append(input.substring(start));
                count1 = input.length() - start;
            } else if (i == 1) {
                content2.append(input.substring(start));
                count2 = input.length() - start;
            } else if (i == 2) {
                content3.append(input.substring(start));
                count3 = input.length() - start;
            }
            splitCount++;
        }
        
        result[0] = content1.toString();
        result[1] = content2.toString();
        result[2] = content3.toString();
        result[3] = delim1;
        result[4] = delim2;
        result[5] = delim3;
        result[6] = content1.toString();
        result[7] = content2.toString();
        result[8] = content3.toString();
        result[9] = String.valueOf(splitCount);
        
        return result;
    }
    
    private static void unstring(String input, String[] empty, int zero) {
        todayYear = Integer.parseInt(input.substring(0, 4));
        todayMonth = Integer.parseInt(input.substring(4, 6));
        todayDay = Integer.parseInt(input.substring(6, 8));
    }
}