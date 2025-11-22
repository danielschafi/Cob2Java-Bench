```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UnstringExample {
    
    static class UnstringResult {
        String[] contents;
        String[] delimiters;
        int[] counts;
        int splitCount;
        boolean overflow;
    }
    
    public static void main(String[] args) {
        String randomString = "123ABCDEFGHIJKL";
        
        String ch1 = "";
        String ch2 = "";
        String ch3 = "";
        
        String delich1 = "";
        String delich2 = "";
        String delich3 = "";
        
        int countch1 = 0;
        int countch2 = 0;
        int countch3 = 0;
        
        int ptr = 4;
        int countsplit = 0;
        
        UnstringResult result = unstringWithDelimiters(randomString, ptr, new String[]{"ABC", "GHI"});
        
        if (result.overflow) {
            System.out.println("Pointer too low/high" + "or not enough variables :(");
        } else {
            ch1 = result.contents.length > 0 ? result.contents[0] : "";
            ch2 = result.contents.length > 1 ? result.contents[1] : "";
            ch3 = result.contents.length > 2 ? result.contents[2] : "";
            
            delich1 = result.delimiters.length > 0 ? result.delimiters[0] : "";
            delich2 = result.delimiters.length > 1 ? result.delimiters[1] : "";
            delich3 = result.delimiters.length > 2 ? result.delimiters[2] : "";
            
            countch1 = result.counts.length > 0 ? result.counts[0] : 0;
            countch2 = result.counts.length > 1 ? result.counts[1] : 0;
            countch3 = result.counts.length > 2 ? result.counts[2] : 0;
            
            countsplit = result.splitCount;
        }
        
        System.out.println("Content 1 : " + ch1 + " (delimiter : " + delich1 + ", size : " + countch1 + ")");
        System.out.println("Content 2 : " + ch2 + " (delimiter : " + delich2 + ", size : " + countch2 + ")");
        System.out.println("Content 3 : " + ch3 + " (delimiter : " + delich3 + ", size : " + countch3 + ")");
        System.out.println("Split number : " + countsplit);
        
        String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        
        String todayYear = todayDate.substring(0, 4);
        String todayMonth = todayDate.substring(4, 6);
        String todayDay = todayDate.substring(6, 8);
        
        System.out.println("Today: " + todayDay + "/" + todayMonth + "/" + todayYear);
    }
    
    static UnstringResult unstringWithDelimiters(String input, int startPtr, String[] delimiters) {
        UnstringResult result = new UnstringResult();
        result.contents = new String[3];
        result.delimiters = new String[3];
        result.counts = new int[3];
        result.overflow = false;
        result.splitCount = 0;
        
        int pos = startPtr - 1;
        if (pos < 0 || pos >= input.length()) {
            result.overflow = true;
            return result;
        }
        
        int contentIndex = 0;
        StringBuilder currentContent = new StringBuilder();
        
        while (pos < input.length() && contentIndex < 3) {
            boolean foundDelimiter = false;
            String foundDelimiterStr = "";
            
            for (String delim : delimiters) {
                if (pos + delim.length() <= input.length() && 
                    input.substring(pos, pos + delim.length()).equals(delim)) {
                    foundDelimiter = true;
                    foundDelimiterStr = delim;
                    
                    result.contents[contentIndex] = currentContent.toString();
                    result.delimiters[contentIndex] = delim;
                    result.counts[contentIndex] = currentContent.length();
                    result.splitCount++;
                    
                    pos += delim.length();
                    currentContent = new StringBuilder();
                    contentIndex++;
                    break;
                }
            }
            
            if (!foundDelimiter) {
                currentContent.append(input.charAt(pos));
                pos++;
            }
        }
        
        if (contentIndex < 3 && currentContent.length() > 0) {
            result.contents[contentIndex] = currentContent.toString();
            result.delimiters[contentIndex] = "";
            result.counts[contentIndex] = currentContent.length();
            result.splitCount++;
        }
        
        return result;
    }
}