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
    
    private static String todayDate = "";
    private static int todayYear = 0;
    private static int todayMonth = 0;
    private static int todayDay = 0;
    
    public static void main(String[] args) {
        // First unstring operation
        unstringOperation1();
        
        System.out.println("Content 1 : " + ch1 + " (delimiter : " + delich1 + ", size : " + countch1 + ")");
        System.out.println("Content 2 : " + ch2 + " (delimiter : " + delich2 + ", size : " + countch2 + ")");
        System.out.println("Content 3 : " + ch3 + " (delimiter : " + delich3 + ", size : " + countch3 + ")");
        System.out.println("Split number : " + countsplit);
        
        // Second unstring operation
        unstringOperation2();
        
        System.out.println("Today: " + todayDay + "/" + todayMonth + "/" + todayYear);
    }
    
    private static void unstringOperation1() {
        // Reset variables
        ch1 = "";
        ch2 = "";
        ch3 = "";
        delich1 = "";
        delich2 = "";
        delich3 = "";
        countch1 = 0;
        countch2 = 0;
        countch3 = 0;
        countsplit = 0;
        
        String input = randomString;
        int currentPtr = ptr - 1; // Convert to 0-based index
        
        // Find first delimiter "ABC"
        int pos1 = input.indexOf("ABC", currentPtr);
        if (pos1 != -1) {
            ch1 = input.substring(currentPtr, pos1);
            delich1 = "ABC";
            countch1 = pos1 - currentPtr;
            currentPtr = pos1 + 3; // Move past delimiter
            
            // Find second delimiter "GHI"
            int pos2 = input.indexOf("GHI", currentPtr);
            if (pos2 != -1) {
                ch2 = input.substring(currentPtr, pos2);
                delich2 = "GHI";
                countch2 = pos2 - currentPtr;
                currentPtr = pos2 + 3; // Move past delimiter
                
                // Remaining part
                ch3 = input.substring(currentPtr);
                delich3 = "";
                countch3 = input.length() - currentPtr;
                countsplit = 3;
            } else {
                // No second delimiter found
                ch2 = input.substring(currentPtr);
                delich2 = "";
                countch2 = input.length() - currentPtr;
                countsplit = 2;
            }
        } else {
            // No first delimiter found
            ch1 = input.substring(currentPtr);
            delich1 = "";
            countch1 = input.length() - currentPtr;
            countsplit = 1;
        }
    }
    
    private static void unstringOperation2() {
        // Get current date as string (YYYYMMDD format)
        java.time.LocalDate now = java.time.LocalDate.now();
        todayDate = String.format("%08d", now.getYear() * 10000 + now.getMonthValue() * 100 + now.getDayOfMonth());
        
        // Extract year, month, day from the date string
        todayYear = Integer.parseInt(todayDate.substring(0, 4));
        todayMonth = Integer.parseInt(todayDate.substring(4, 6));
        todayDay = Integer.parseInt(todayDate.substring(6, 8));
    }
}